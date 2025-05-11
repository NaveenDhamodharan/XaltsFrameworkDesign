package XaltsAutomationFramework;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReusableMethods {
WebDriver driver;
	
	public ReusableMethods(WebDriver driver) {
		this.driver = driver;
	}
	
	//explicit wait
	public void waitTillElementIsPresent(By elementToBeFound) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(elementToBeFound));
	}
	
	//take screenshot
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png"));
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
}