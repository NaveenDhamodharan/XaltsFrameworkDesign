package XaltsAutomationFramework;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.github.bonigarcia.wdm.WebDriverManager;

public class InvokeBrowser {
	
	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeBrowser() throws IOException {

		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//XaltsAutomationFrameworkProperties//Framework.properties");
		properties.load(fileInputStream);
		String invokingBrowser = properties.getProperty("browser"); // to retrieve data from properties file

		if (invokingBrowser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (invokingBrowser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.get("https://xaltsocnportal.web.app/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
	
	@BeforeClass(alwaysRun=true)
	public void launchApplication() throws IOException {
		driver = initializeBrowser();
		landingPage = new LandingPage(driver);
	}
	
	@AfterClass(alwaysRun=true)
	public void quitDriver() {
		driver.quit();
	}
}