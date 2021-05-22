package helper;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import initializer.Initializer;

public class WaitHelper {

	private WebDriver driver;

	/* Constructor */
	public WaitHelper(WebDriver driver) {
		this.driver = driver;
		setImplicitWait();
		System.out.println("WaitHelper object created in constructor.");
	}

	/*---Private Methods---*/

	/* Get explicit wait object */
	private WebDriverWait getExplicitWait() {
		WebDriverWait wait = new WebDriverWait(driver, Initializer.explicitlyWaitInSeconds);
		return wait;
	}

	/* Get explicit wait object with exceptions ignored */
	private WebDriverWait getExplicitWaitIgnoringExceptions() {

		WebDriverWait wait = new WebDriverWait(driver, Initializer.explicitlyWaitInSeconds);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);

		return wait;
	}

	/* Get fluent wait object */
	private Wait<WebDriver> getFluentWait() {

		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(Initializer.fluentWaitTimeoutInSeconds))
				.pollingEvery(Duration.ofSeconds(Initializer.fluentWaitPollingEveryInSeconds))
				.ignoring(StaleElementReferenceException.class).ignoring(TimeoutException.class);

		return fluentWait;
	}

	/*---Public Methods---*/

	public void setImplicitWait() {
		driver.manage().timeouts().implicitlyWait(Initializer.implicitlyWaitInSeconds, TimeUnit.SECONDS);
		System.out.println("Implicit Wait set for " + Initializer.implicitlyWaitInSeconds + " seconds.");
	}

	public void resetImplicitWait() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		System.out.println("Implicit Wait set for " + Initializer.implicitlyWaitInSeconds + " seconds.");
	}

	public void pageLoadTime() {
		driver.manage().timeouts().pageLoadTimeout(Initializer.maxPageLoadTimeInSeconds, TimeUnit.SECONDS);
	}

	public void setScriptTimeout() {
		driver.manage().timeouts().setScriptTimeout(Initializer.getScriptTimeoutInSeconds, TimeUnit.SECONDS);
	}

	public WebElement getElementIfPresent(final By locator) {
		System.out.println("Looking for presence of element." + "\n" + "-> " + locator.toString());

		WebElement we = null;

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(Initializer.fluentWaitTimeoutInSeconds))
				.pollingEvery(Duration.ofSeconds(Initializer.fluentWaitPollingEveryInSeconds))
				.ignoring(StaleElementReferenceException.class);

		int tries = 0;
		boolean found = false;

		final long START_TIME = System.currentTimeMillis();
		final long END_TIME;
		final long TOTAL_TIME;

		while (System.currentTimeMillis() - START_TIME < (Initializer.overallTimeForTriesInSeconds * 1000)) {
			System.out.println("Try number " + ++tries + "time(s), within 90 seconds.");
			try {
				we = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				found = true;
				break;
			} catch (StaleElementReferenceException e) {
				System.out.println("StaleElementReferenceException caused by: " + e.getCause());
				System.out.println("StaleElementReferenceException message : " + e.getMessage());
			}
		}

		END_TIME = System.currentTimeMillis();
		TOTAL_TIME = START_TIME - END_TIME;

		if (found) {
			System.out.println("Found element in " + (TOTAL_TIME / 1000) + " seconds.");
		} else {
			System.out.println("Cannot find element after waiting for " + (TOTAL_TIME / 1000) + " seconds.");
		}

		return we;
	}

	/* Element to be click able */

	public void elementToBeClickable(WebElement element) {
		WebDriverWait wait = getExplicitWait();
		wait.until(ExpectedConditions.elementToBeClickable(element));
		System.out.println("Element is clickable -> " + element.toString());
	}

	public void elementToBeClickable(By locator) {
		WebDriverWait wait = getExplicitWait();
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		System.out.println("Element is clickable -> " + locator.toString());
	}

	/* Visibility of element */

	public void visibilityOfElement(WebElement element) {
		WebDriverWait wait = getExplicitWait();
		wait.until(ExpectedConditions.visibilityOf(element));
		System.out.println("Element is visible -> " + element.toString());
	}

	public void visibilityOfElement(By locator) {
		WebDriverWait wait = getExplicitWait();
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		System.out.println("Element is visible -> " + locator.toString());
	}

	/* Invisibility of element */

	public void invisibilityOfElement(WebElement element) {
		WebDriverWait wait = getExplicitWait();
		wait.until(ExpectedConditions.invisibilityOf(element));
		System.out.println("Element is invisible -> " + element.toString());
	}

	public void invisibilityOfElement(By locator) {
		WebDriverWait wait = getExplicitWait();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		System.out.println("Element is invisible -> " + locator.toString());
	}

	/* Wait and switch to frame */

	public void frameToBeAvailableAndSwitchToIt(String frameName) {
		WebDriverWait wait = getExplicitWait();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
	}

	/* Title waits */

	public boolean titleIs(String expectedTitle) {
		WebDriverWait wait = getExplicitWait();
		System.out.println("Current title is -> " + driver.getTitle());
		System.out.println("Waiting for page with title -> " + expectedTitle);
		return wait.until(ExpectedConditions.titleIs(expectedTitle));
	}

	public boolean titleContains(String partialExpectedTitle) {
		WebDriverWait wait = getExplicitWait();
		System.out.println("Loaded page containing partial title -> " + partialExpectedTitle);
		return wait.until(ExpectedConditions.titleContains(partialExpectedTitle));
	}

	public void titleIsNot(String notExpectedTitle) {
		WebDriverWait wait = getExplicitWait();
		wait.until(ExpectedConditions.not(ExpectedConditions.titleIs(notExpectedTitle)));
		System.out.println("Loaded page title NOT being -> " + notExpectedTitle);
	}

	/* Wait for page load */

	public void pageLoadToBeComplete() {

		System.out.println("Waiting for page load.");
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};

		WebDriverWait wait = getExplicitWait();
		wait.until(pageLoadCondition);
	}

	/* Wait for element to be stale */

	public void elementIsStale(WebElement element) {
		System.out.println("Wait for element to be stale.");
		try {
			WebDriverWait wait = getExplicitWait();
			wait.until(ExpectedConditions.stalenessOf(element));
		} catch (NoSuchElementException e) {
			System.out.println("NoSuchElementException thrown.");
		}
	}

	public void elementIsStale(By locator) {
		System.out.println("Wait for element to be stale.");
		try {
			WebDriverWait wait = getExplicitWait();
			wait.until(ExpectedConditions.stalenessOf(driver.findElement(locator)));
		} catch (NoSuchElementException e) {
			System.out.println("NoSuchElementException thrown.");
		}
	}

	/* Wait for attribute to specific value*/

	public void waitForAttributeToBe(WebElement element, String attribute, String value) {
		System.out.println("Check for attribute to be -> " + attribute + ": " + value + "\n in WebElement -> "
				+ element.toString());
		WebDriverWait wait = getExplicitWait();
		wait.until(ExpectedConditions.attributeToBe(element, attribute, value));
	}

	public void waitForAttributeToBe(By locator, String attribute, String value) {
		System.out.println("Check for attribute to be -> " + attribute + ": " + value + "\n in WebElement -> "
				+ locator.toString());
		WebDriverWait wait = getExplicitWait();
		wait.until(ExpectedConditions.attributeToBe(driver.findElement(locator), attribute, value));
	}

	/* Wait for attribute to contain partial value*/

	public void waitForAttributeToContain(WebElement element, String attribute, String value) {
		System.out.println("Check for attribute to be -> " + attribute + ": " + value + "\n in WebElement -> "
				+ element.toString());
		WebDriverWait wait = getExplicitWait();
		wait.until(ExpectedConditions.attributeContains(element, attribute, value));
	}

	public void waitForAttributeToContain(By locator, String attribute, String value) {
		System.out.println("Check for attribute to be -> " + attribute + ": " + value + "\n in WebElement -> "
				+ locator.toString());
		WebDriverWait wait = getExplicitWait();
		wait.until(ExpectedConditions.attributeContains(driver.findElement(locator), attribute, value));
	}

}
