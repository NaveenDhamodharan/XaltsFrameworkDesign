package XaltsAutomationFramework;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.lang.reflect.Method;

import com.aventstack.extentreports.*;

import XaltsAutomationFrameworkProperties.ExtendReportNG;

public class TestScript extends InvokeBrowser {
	public OnboardOcnNodePage onboardOcnNodePage;
	public LaunchOCNNetworkPage launchOCNNetworkPage;

	public boolean existingUser = true;
	public String walletAddress = "0x88fa61d2faA13aad8Fbd5B030372B4A159BbbDFb";
	public String nodeId = "NodeID-12345";
	public String networkName = "defaultNetwork";
	public String publicIp = "23.98.56.177";
	public String userId = "xaltsautomationtesting12@getnada.com";
	public String password = "P@$$w0rd";

	private ExtentReports extent;
	@BeforeSuite
	public void setupExtent() {
		extent = ExtendReportNG.getExtentReports();
	}

	@BeforeMethod
	public void createTest(Method method) {
		extent.createTest(method.getName());
	}

	@AfterSuite
	public void tearDownExtent() {
		extent.flush();
	}

	@Test(priority = 1, groups = "regression")
	public void userSignsUp() {
		boolean isUserSignedIn = landingPage.userSignsUp(userId, password);
		Assert.assertEquals(isUserSignedIn, true, "User not able to sign up");
	}

	@Test(priority = 1, groups = "regressionOne")
	public void signInAndNavigateTest() {
		boolean isUserSignedIn = landingPage.userSignsIn(userId, password);
		Assert.assertEquals(isUserSignedIn, true, "User not able to sign in");
	}

	@Test(priority = 2, groups = { "regression", "regressionOne" })
	public void userOnboardsNodes() {
		this.ensureSignedIn();
		onboardOcnNodePage = landingPage.userClicksOnOnboardOCNNode();
		boolean isNodeIdAddedSuccessfully = onboardOcnNodePage.userProvidesNodeDetails(nodeId, publicIp);
		Assert.assertEquals(isNodeIdAddedSuccessfully, true, "Node ID was not added successfully");
		String walletAddressAdded = onboardOcnNodePage.userEntersWalletDetails(walletAddress);
		Assert.assertEquals(walletAddressAdded, walletAddress, "Wallet Address is not added successfully");
		boolean[] isAddedDetailsPresent = onboardOcnNodePage.userSubmitsNode(nodeId, walletAddress);
		Assert.assertEquals(isAddedDetailsPresent[0], true, "Node ID not found in preview");
		Assert.assertEquals(isAddedDetailsPresent[1], true, "Wallet address not found in preview");
	}

	@Test(priority = 3, groups = { "regression", "regressionOne" })
	public void userLaunchOcnNetwork() {
		this.ensureSignedIn();
		launchOCNNetworkPage = landingPage.userClicksOnLaunchOCNNetwork();
		launchOCNNetworkPage.userProvidesNetworkDetails(networkName, walletAddress, nodeId, publicIp);
		String[] addedNetworks = launchOCNNetworkPage.userSubmitsNode(nodeId);
		Assert.assertEquals(addedNetworks[0], nodeId, "Node ID not found in preview");
		Assert.assertEquals(addedNetworks[1], networkName, "Network Name not found in preview");
		Assert.assertEquals(addedNetworks[2], walletAddress, "Wallet address not found in preview");
	}

	@Test(priority = 4, groups = { "regression", "regressionOne" })
	public void userSignsOut() {
		boolean isUserSignnedOut = landingPage.userSignsOut();
		Assert.assertEquals(isUserSignnedOut, true, "User is not able to sign out");
	}

	public void ensureSignedIn() {
		if (!landingPage.isSignedIn()) {
			boolean isUserSignedIn = landingPage.userSignsIn(userId, password);
			Assert.assertTrue(isUserSignedIn, "User not able to sign in before proceeding");
		}
	}
	
	@Test(groups = "negativeTest")
	public void userSignsInWithInvalidCredentials() {
		String textInAlert = landingPage.userTriesToSignUpWithExistingUser(userId, password);
		Assert.assertEquals(textInAlert, "Alert is not displayed when attempting to sign up with an already registered email.");
	}
}