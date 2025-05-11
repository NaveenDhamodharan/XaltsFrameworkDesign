package XaltsAutomationFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage extends ReusableMethods{
	
	public WebDriver driver;
	public OnboardOcnNodePage onboardOcnNodePage;
	public LaunchOCNNetworkPage launchOCNNetworkPage;
	
	private final By byExistingUserLoginXpath = By.xpath("//button[contains(text(), 'Already have an account?')]");
	private final By byEmailInputXpath = By.xpath("//input[@type='text']");
	private final By byPasswordInputXpath = By.xpath("//span[text()='Password']/parent::legend/parent::fieldset/parent::div/input");
	private final By byConfirmPasswordInputXpath = By.xpath("//span[text()='Confirm Password']/parent::legend/parent::fieldset/parent::div/input");
	private final By bySignInButtonXpath = By.xpath("//button[@type='button' and text()='Sign In']");
	private final By bySignUpButtonXpath = By.xpath("//button[@type='button' and text()='Sign Up']");
	private final By byGetStartedTextXpath = By.xpath("//h1[text()='Getting Started']");
	private final By byGetStartedButtonCssSelector = By.cssSelector("div.cta-container button");
	private final By byOnboardOcnNodeXpath = By.xpath("//div[@class='getting-started-option']/h2[text()='Onboard OCN Node']");
	private final By byLaunchOcnNetworkXpath = By.xpath("//div[@class='getting-started-option']/h2[text()='Launch OCN Child Network']");
	private final By bySignOutButtonXpath = By.xpath("//button[@type='button' and text()='Sign Out']");
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public boolean userSignsIn(String userId, String password) {
		driver.findElement(bySignInButtonXpath).click();
		driver.findElement(byExistingUserLoginXpath).click();
		driver.findElement(byEmailInputXpath).sendKeys(userId);
		driver.findElement(byPasswordInputXpath).sendKeys(password);
		driver.findElement(bySignInButtonXpath).click();
		waitTillElementIsPresent(byGetStartedButtonCssSelector);
		driver.findElement(byGetStartedButtonCssSelector).click();
		return driver.findElement(byGetStartedTextXpath).isDisplayed();
	}

	public OnboardOcnNodePage userClicksOnOnboardOCNNode() {
		driver.findElement(byOnboardOcnNodeXpath).click();
		onboardOcnNodePage = new OnboardOcnNodePage(driver);
		return onboardOcnNodePage;
	}
	
	public LaunchOCNNetworkPage userClicksOnLaunchOCNNetwork() {
		driver.findElement(byLaunchOcnNetworkXpath).click();
		launchOCNNetworkPage = new LaunchOCNNetworkPage(driver);
		return launchOCNNetworkPage;
	}

	public boolean userSignsOut() {
		driver.findElement(bySignOutButtonXpath).click();
		return driver.findElement(bySignInButtonXpath).isDisplayed();
	}

	public boolean userSignsUp(String userId, String password) {
		driver.findElement(bySignInButtonXpath).click();
		driver.findElement(byEmailInputXpath).sendKeys(userId);
		driver.findElement(byPasswordInputXpath).sendKeys(password);
		driver.findElement(byConfirmPasswordInputXpath).sendKeys(password);
		driver.findElement(bySignUpButtonXpath).click();
		waitTillElementIsPresent(byGetStartedButtonCssSelector);
		driver.findElement(byGetStartedButtonCssSelector).click();
		return driver.findElement(byGetStartedTextXpath).isDisplayed();
	}

	public boolean isSignedIn() {
		return driver.findElement(bySignOutButtonXpath).isDisplayed();
	}
}