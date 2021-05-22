package managers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import initializer.Initializer;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class DriverManager extends Initializer {

	private WebDriver driver;
	public static ChromeOptions chromeOptions = new ChromeOptions();
	public static DesiredCapabilities caps = new DesiredCapabilities();

	public DriverManager() {
	}

	private class BrowserCleanUp implements Runnable {
		public void run() {
			System.out.println("Cleaning up the browser.");
			try {
				quitDriver();
				generateReport();
			} catch (NullPointerException e) {
				System.out.println("No driver found to quit.");
			}
		}
	}

	public WebDriver getDriver() {
		if (driver == null) {
			try {
				driver = createDriver();
			} catch (Exception e) {
				e.getMessage();
				e.getCause();
				e.getStackTrace();
			}
		}
		return driver;
	}

	private WebDriver createDriver() {

		switch (environmentType.toLowerCase()) {
		case "local":
			driver = createLocalDriver();
			break;
		case "remote":
			driver = createRemoteDriver();
		}

		return driver;
	}

	private WebDriver createLocalDriver() {

		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popup", 0);
			chromePrefs.put("download.promp_for_download", false);
			chromePrefs.put("download.default_directory", downloadPath);

			chromeOptions = new ChromeOptions();
			// chromeOptions.addArguments("--ignore-certificate-errors");
			// chromeOptions.setCapability(CapabilityType.PROXY, seleniumProxy);

			caps.setJavascriptEnabled(true);
			caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			caps.setCapability("applicationCacheEnabled", false);
			chromeOptions.setCapability(ChromeOptions.CAPABILITY, caps);
			chromeOptions.addArguments("disbale-infobars");
			chromeOptions.addArguments("start-maximized");
			chromeOptions.setExperimentalOption("prefs", chromePrefs);

			chromeOptions.setCapability("applicationCacheEnabled", false);

			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
			cap.setJavascriptEnabled(true);
			chromeOptions.setCapability(ChromeOptions.CAPABILITY, cap);

			driver = new ChromeDriver(chromeOptions);
			break;

		case "chromeheadless":
			WebDriverManager.chromedriver().setup();
			chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("--disable-extensions");
			chromeOptions.addArguments("--disbale-extensions");
			chromeOptions.addArguments("no-sandbox");
			driver = new ChromeDriver(chromeOptions);
			break;

		case "firefox":
			driver = new FirefoxDriver();
			break;

		default:
			break;
		}

		Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanUp()));
		deleteCookies();
		driver.get("chrome://settings/clearBrowserData");
		driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);

		if (implicitlyWaitInSeconds > 0) {
			setImplicitlyWait(implicitlyWaitInSeconds);
		}
		if (maxPageLoadTimeInSeconds > 0) {
			setMaxPageLoadTime(maxPageLoadTimeInSeconds);
		}
		
		getAppURL();
		return driver;

	}


	private WebDriver createRemoteDriver() {
		// TODO Auto-generated method stub
		return null;
	}

	private void deleteCookies() {
		driver.manage().deleteAllCookies();

	}
	
	private void getAppURL() {
		driver.get(Initializer.appURL);
	}

	private void setImplicitlyWait(int implicitlyWaitInSeconds) {
		driver.manage().timeouts().implicitlyWait(implicitlyWaitInSeconds, TimeUnit.SECONDS);
	}

	private void setMaxPageLoadTime(int maxPageLoadTimeInSeconds) {
		driver.manage().timeouts().pageLoadTimeout(maxPageLoadTimeInSeconds, TimeUnit.SECONDS);
	}
	
	private void quitDriver() {
		driver.close();
		driver.quit();
		driver = null;
	}

	private void generateReport() {
		
		File reportOutputDirectory = new File("target");
		String projectName = "Selenium Ready";
		//String buildNumber = "1.0";

		List<String> jsonFile = new ArrayList<String>();
		jsonFile.add("target/cucumber.json");
		
		Configuration configuration = new Configuration(reportOutputDirectory, projectName);
		//configuration.setBuildNumber(buildNumber);
		
		ReportBuilder reportBuilder = new ReportBuilder(jsonFile, configuration);
		reportBuilder.generateReports();
		
		
	}

}
