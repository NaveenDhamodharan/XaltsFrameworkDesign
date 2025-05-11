package XaltsAutomationFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OnboardOcnNodePage extends ReusableMethods {
	
	public WebDriver driver;
	
	private final By byNodeIdInputXpath = By.xpath("//span[text()='Node ID']/parent::legend/parent::fieldset/parent::div/input");
	private final By byPublicIpInputXpath = By.xpath("//span[text()='Public IP']/parent::legend/parent::fieldset/parent::div/input");
	private final By byAddNodeButtonXpath = By.xpath("//button[@type='button' and contains(text(), 'Add Node')]");
	private final By bySubmitButtonXpath = By.xpath("//button[@type='button' and contains(text(), 'Submit')]");
	private final By byNextButtonXpath = By.xpath("//button[@type='button' and contains(text(), 'Next')]");
	private final By byAddWalletButtonXpath = By.xpath("//button[@type='button' and contains(text(), 'Add Wallet')]");
	private final By byWalletAddressXpath = By.xpath("//span[contains(text(), 'Wallet Address')]/parent::legend/parent::fieldset/parent::div/input");
	private final By byXaltsLogoXpath = By.xpath("//img[contains(@src, 'static/media')]");
	private final By byGetStartedButtonCssSelector = By.cssSelector("div.cta-container button");
	
	private final String ADDED_NODE_IN_PREVIEW = "//div[@data-field='nodeId']/div[@title='@nodeIdAdded']";
	private final String WALLET_ADDRESS_IN_PREVIEW = "//div[@data-field='walletAddress']/div[@title='@walletAddressAdded']";

	public OnboardOcnNodePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public boolean userProvidesNodeDetails(String nodeId, String publicIp) {
		waitTillElementIsPresent(byNodeIdInputXpath);
		driver.findElement(byNodeIdInputXpath).sendKeys(nodeId);
		driver.findElement(byPublicIpInputXpath).sendKeys(publicIp);
		driver.findElement(byAddNodeButtonXpath).click();
		
		By byAddedNodeInPreviewXpath = By.xpath(ADDED_NODE_IN_PREVIEW.replace("@nodeIdAdded", nodeId));
		boolean isAddedNodePresentInPreview = driver.findElement(byAddedNodeInPreviewXpath).isDisplayed();
		driver.findElement(byNextButtonXpath).click();
		return isAddedNodePresentInPreview;
	}
	
	public String userEntersWalletDetails(String walletAddress) {
		driver.findElement(byWalletAddressXpath).sendKeys(walletAddress);
		driver.findElement(byAddWalletButtonXpath).click();
		
		By byWalletAddressInPreviewXpath = By.xpath(WALLET_ADDRESS_IN_PREVIEW.replace("@walletAddressAdded", walletAddress));
		waitTillElementIsPresent(byWalletAddressInPreviewXpath);
		String addedWalletAddress = driver.findElement(byWalletAddressInPreviewXpath).getText();
		driver.findElement(byNextButtonXpath).click();
		return addedWalletAddress;
	}
	
	public boolean[] userSubmitsNode(String nodeId, String walletAddress) {
		driver.findElement(bySubmitButtonXpath).click();
		By byAddedNodeInPreviewXpath = By.xpath(ADDED_NODE_IN_PREVIEW.replace("@nodeIdAdded", nodeId));
		boolean isAddedNodePresentInPreview = driver.findElement(byAddedNodeInPreviewXpath).isDisplayed();
		By byWalletAddressInPreviewXpath = By.xpath(WALLET_ADDRESS_IN_PREVIEW.replace("@walletAddressAdded", walletAddress));
		boolean isAddedWalletAddressPresent = driver.findElement(byWalletAddressInPreviewXpath).isDisplayed();
		driver.findElement(byXaltsLogoXpath).click();
		driver.findElement(byGetStartedButtonCssSelector).click();
		return new boolean[] { isAddedNodePresentInPreview, isAddedWalletAddressPresent };
	}
}