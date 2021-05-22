package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSHelper {

	private WebDriver driver;

	public JSHelper(WebDriver driver) {
		this.driver = driver;
	}

	private Object executeThisScript(String script) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		return exe.executeScript(script);
	}
	
	public Object executeThisScript(String script, Object... objects) {
		JavascriptExecutor exe = (JavascriptExecutor)driver;
		return exe.executeScript(script, objects);
	}
	
	public void scrollTo(WebElement e) {
		executeThisScript("window.scrollTo(arguments[0],arguments[1]);", e.getLocation().x,e.getLocation().y);
	}
	
	public void scrollTo(By locator) {
		executeThisScript("window.scrollTo(arguments[0],arguments[1]);", driver.findElement(locator).getLocation().x,driver.findElement(locator).getLocation().y);
	}
	
	public void scrollIntoView(WebElement e) {
		executeThisScript("arguments[0].scrollIntoView();", e);
	}
	
	public void scrollIntoView(By locator) {
		executeThisScript("arguments[0].scrollIntoView();", driver.findElement(locator));
	}
	
	public void jsClick(WebElement e) {
		scrollTo(e);
		executeThisScript("arguments[0].click();", e);
	}
	
	public void jsClick(By locator) {
		scrollTo(driver.findElement(locator));
		executeThisScript("arguments[0].click();", driver.findElement(locator));
	}
	
	public void setData(WebElement element, String data) {
		executeThisScript("arguments[0].value='" + data + "';", element);
	}
	
	public void reloadPage() {
		executeThisScript("location.reload();"); //Remove semicolon if doesn't work.
	}
}
