package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import helper.JSHelper;
import helper.WaitHelper;

public class UILibrary {
	
	protected WebDriver driver;
	protected WaitHelper waitHelper;
	protected JSHelper jsHelper;
	protected Actions action;
	//AttachFiles attachFile;
	
	public UILibrary(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		jsHelper = new JSHelper(driver);
		action = new Actions(driver);
		//attachFile = new AttachFiles(driver);
	}
	
	/* Click Methods */
	
	public void clickElement(WebElement element) {
		jsHelper.scrollIntoView(element);
		waitHelper.elementToBeClickable(element);
		element.click();
		pause(100);
		
	}
	
	public void clickElement(By locator) {
		waitHelper.getElementIfPresent(locator);
		clickElement(driver.findElement(locator));
	}
	
	public void clickElementUsingJS(WebElement element) {
		jsHelper.scrollIntoView(element);
		waitHelper.elementToBeClickable(element);
		jsHelper.jsClick(element);
		pause(100);
		
	}
	
	public void clickElementUsingJS(By locator) {
		waitHelper.getElementIfPresent(locator);
		clickElementUsingJS(driver.findElement(locator));
	}
	
	public void clickUsingActions(WebElement element) {
		jsHelper.scrollIntoView(element);
		waitHelper.visibilityOfElement(element);
		waitHelper.elementToBeClickable(element);
		action.click(element).perform();
		
	}
	
	public void clickUsingActions(By locator) {
		waitHelper.getElementIfPresent(locator);
		clickUsingActions(driver.findElement(locator));
	}
	
	/* Enter Text Methods */
	
	public void enterText(WebElement element, String text, boolean clear) {
		waitHelper.visibilityOfElement(element);
		waitHelper.elementToBeClickable(element);
		jsHelper.scrollIntoView(element);
		if (clear) {
			element.clear();
		}
		element.sendKeys(text);
		pause(100);
		
	}
	
	public void enterText(By locator, String text, boolean clear) {
		enterText(driver.findElement(locator), text, clear);
	}
	
	public void enterTextUsingActions(WebElement element, String text, boolean clear) {
		waitHelper.visibilityOfElement(element);
		waitHelper.elementToBeClickable(element);
		jsHelper.scrollIntoView(element);
		if (clear) {
			element.clear();
		}
		action.click().pause(100).sendKeys(element, text).pause(100).build().perform();
		pause(100);
		
	}
	
	public void enterTextUsingActions(By locator, String text, boolean clear) {
		enterTextUsingActions(driver.findElement(locator), text, clear);
	}
	
	public void enterTextUsingJS(WebElement element, String data, boolean clear) {
		waitHelper.visibilityOfElement(element);
		jsHelper.scrollIntoView(element);
		if (clear) {
			element.clear();
		}
		jsHelper.setData(element, data);
		
	}
	
	public void enterTextUsingJS(By locator, String data, boolean clear) {
		enterTextUsingJS(driver.findElement(locator), data, clear);
	}
	
	/* Get Text Methods */
	
	public String getText(WebElement element) {
		waitHelper.visibilityOfElement(element);
		jsHelper.scrollIntoView(element);
		pause(100);
		return element.getText().trim();
	}
	
	public String getText(By locator) {
		return getText(driver.findElement(locator));
	}
	
	public void getAttribute(By locator) {
		waitHelper.getElementIfPresent(locator);
		
	}
	
	/* isSelected, is Displayed & isEnabled Methods */
	
	public boolean isSelected(WebElement element) {
		return element.isSelected();
	}
	
	public boolean isSelected(By locator) {
		return isSelected(driver.findElement(locator));
	}
	
	public boolean isEnabled(WebElement element) {
		return element.isEnabled();
	}
	
	public boolean isEnabled(By locator) {
		return isEnabled(driver.findElement(locator));
	}
	
	public boolean isDisplayed(WebElement element) {
		waitHelper.visibilityOfElement(element);
		return element.isEnabled();
	}
	
	public boolean isDisplayed(By locator) {
		return isDisplayed(driver.findElement(locator));
	}
	
	public boolean waitForTitleContaining(String partialExpectedTitle) {
		return waitHelper.titleContains(partialExpectedTitle);
		
	}
	
	public boolean waitForExactTitle(String expectedTitle) {
		return waitHelper.titleIs(expectedTitle);
		
	}
	
	public void waitForStaleness(WebElement element) {
		waitHelper.elementIsStale(element);
		
	}
	
	public void clearText(WebElement element) {
		waitHelper.visibilityOfElement(element);
		element.clear();
		
	}
	
	public void clearText(By locator) {
		clearText(driver.findElement(locator));
	}
	
	public void hoverOver(WebElement element) {
		action.moveToElement(element).perform();
	}
	
	public void hoverOver(By locator) {
		hoverOver(driver.findElement(locator));
	}
	
	/* Miscellaneous Methods */
	
	public void pause(long milliSeconds) {
		action.pause(milliSeconds).perform();
		
	}
	
	public void waitForPageLoad() {
		waitHelper.pageLoadToBeComplete();
		
	}
	
	public int checkElementCount(By locator) {
		waitHelper.pageLoadToBeComplete();
		return driver.findElements(locator).size();
	}
	
	public String formatString(String format, Object... args) {
		return String.format(format, args);
	}
	
}
