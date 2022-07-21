package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Reusables {

	public Properties properties;
	public WebDriver driver;
	public ExtentReports extent;
	public ExtentTest test;

	public WebDriver browserLaunch() throws Exception {

		properties = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//Data//data.properties");
		properties.load(fis);

		String browserName = properties.getProperty("browser");

		String browserURL = properties.getProperty("url");

		try {

			if (browserName.contains("Google Chrome")) {

				ChromeOptions options = new ChromeOptions();

				if (browserName.contains("incognito"))
					options.addArguments("--incognito");

				if (browserName.contains("headless"))
					options.addArguments("--headless");

				options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });

				driver = WebDriverManager.chromedriver().capabilities(options).create();
			}

			else if (browserName.contains("Mozilla Firefox")) {

				FirefoxOptions options = new FirefoxOptions();

				if (browserName.contains("incognito"))
					options.addArguments("--private");

				if (browserName.contains("headless"))
					options.addArguments("--headless");

				driver = WebDriverManager.firefoxdriver().capabilities(options).create();
			}

			else if (browserName.contains("Microsoft Edge")) {

				EdgeOptions options = new EdgeOptions();

				if (browserName.contains("incognito"))
					options.addArguments("--inprivate");

				if (browserName.contains("headless"))
					options.addArguments("--headless");

				options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });

				driver = WebDriverManager.edgedriver().capabilities(options).create();
			}

			test.pass("Browser Launched Successfully : " + browserName + " with value : " + browserURL);

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		}

		catch (Exception e) {
			test.fail("Exception in launching browser : " + browserName + " with value : " + browserURL);
		}

		return driver;
	}

	public void sendKeys(WebElement element, String fieldName, String keysToSend) {

		try {
			element.sendKeys(keysToSend);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].value='" + keysToSend + "'", element);
		}
		test.pass("Typed in : " + fieldName + " successfully with value : " + keysToSend);
	}

	public void click(WebElement element, String fieldName) {

		try {
			element.click();
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
		}
		test.pass("Clicked on : " + fieldName + " successfully");
	}

	public void verifyText(WebElement element, String expectedResult) {

		try {
			String actualResult = element.getText();

			if (actualResult.equals(expectedResult))
				test.pass("Text is Verified successfully Actual is : " + actualResult + " and Expected is : "
						+ expectedResult);
			else
				test.fail("Text is not Verified Actual is : " + actualResult + " and Expected is : " + expectedResult);
		} catch (Exception e) {
			test.fail("Exception in verifying the text");
		}
	}

	public void switchToFrame(WebElement element, String fieldName) {

		try {
			driver.switchTo().frame(element);
			test.pass("Switched to frame : " + fieldName);
		} catch (Exception e) {
			test.fail("Exception in switching to frame : " + fieldName);
		}
	}

	public void switchToParentFrame() {

		try {
			driver.switchTo().parentFrame();
			test.pass("Switched to Parent Frame Successfully");
		} catch (Exception e) {
			test.fail("Exception in switching to parent frame");
		}
	}

	public void scrollToElement(WebElement element, String fieldName) {

		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
			test.pass("Scrolled to Element : " + fieldName + " successfully");
		} catch (Exception e) {
			test.fail("Exception in scrolling to element : " + fieldName);
		}
	}

	public ExtentReports extentSetup() {

		String reportPath = System.getProperty("user.dir") + "//reports//report.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setReportName("Undostres Reports");
		reporter.config().setDocumentTitle("Undostres Reports");
		reporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Automation Engineer", "Sparsh Tyagi");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("Operating System Version", System.getProperty("os.version"));
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));

		return extent;

	}

	public ExtentTest extentCreateTest(String testName) {
		test = extent.createTest(testName);

		return test;

	}

	public void extentFlush() {
		extent.flush();
	}

	public static void extentReportOpen() throws IOException {
		Desktop.getDesktop().browse(new File(System.getProperty("user.dir") + "//reports//report.html").toURI());
	}

}