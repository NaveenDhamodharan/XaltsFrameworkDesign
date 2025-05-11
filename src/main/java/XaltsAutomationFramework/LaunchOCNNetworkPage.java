package XaltsAutomationFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LaunchOCNNetworkPage extends ReusableMethods{
	
	public WebDriver driver;
	private final By byNetworkNameInputXpath = By.xpath("//span[text()='Network Name']/parent::legend/parent::fieldset/parent::div/input");
	private final By byWalletAddressInputXpath = By.xpath("//span[text()='Wallet Address']/parent::legend/parent::fieldset/parent::div/input");
	private final By byNextButtonXpath = By.xpath("//button[@type='button' and contains(text(), 'Next')]");
	private final By byXaltsLogoXpath = By.xpath("//img[contains(@src, 'static/media')]");
	private final By byGetStartedButtonCssSelector = By.cssSelector("div.cta-container button");
	private final By bySubmitButtonXpath = By.xpath("//button[@type='button' and contains(text(), 'Submit')]");
	private final By byWalletAddressPreviewXpath = By.xpath("//div[contains(@class, 'review-grid')]/div/div/div[text()='Wallet Address']/following-sibling::div");
	private final By byNetworkNamePreviewXpath = By.xpath("//div[contains(@class, 'review-grid')]/div/div/div[text()='Network Name']/following-sibling::div");

	private final String ADDED_NODE_IN_PREVIEW = "//div[@data-field='nodeId']/div[@title='@nodeIdAdded']";

	public LaunchOCNNetworkPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public void userProvidesNetworkDetails(String networkName, String walletAddress, String nodeId, String publicIp) {
		waitTillElementIsPresent(byNetworkNameInputXpath);
		driver.findElement(byNetworkNameInputXpath).sendKeys(networkName);
		driver.findElement(byWalletAddressInputXpath).sendKeys(walletAddress);
		driver.findElement(byNextButtonXpath).click();
		new OnboardOcnNodePage(driver).userProvidesNodeDetails(nodeId, publicIp);
	}
	
	public String[] userSubmitsNode(String nodeId) {
		driver.findElement(bySubmitButtonXpath).click();
		By byAddedNodeInPreviewXpath = By.xpath(ADDED_NODE_IN_PREVIEW.replace("@nodeIdAdded", nodeId));
		String addedNode = driver.findElement(byAddedNodeInPreviewXpath).getText();
		String addedNetworkName = driver.findElement(byNetworkNamePreviewXpath).getText();
		String addedWalletAddress = driver.findElement(byWalletAddressPreviewXpath).getText();

		driver.findElement(byXaltsLogoXpath).click();
		driver.findElement(byGetStartedButtonCssSelector).click();
		return new String[] { addedNode, addedNetworkName, addedWalletAddress };
	}

}