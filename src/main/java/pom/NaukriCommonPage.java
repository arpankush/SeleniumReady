package pom;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import common.UILibrary;
import context.ScenarioContext;
import context.TestContext;
import helper.LoggerHelper;
import helper.WaitHelper;
import utilities.TableUtils;

public class NaukriCommonPage {

	private static Logger log = LoggerHelper.getLogger(NaukriCommonPage.class);
	public String parentWindowHandle;
	public Set<String> windowHandles;
	private WebDriver driver;
	UILibrary uiLibrary;
	WaitHelper waitHelper;

	public NaukriCommonPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		uiLibrary = new UILibrary(driver);
		waitHelper = new WaitHelper(driver);
		TableUtils tableutils = new TableUtils(driver);
	}

	By lnk_LoginButton = By.xpath("//div[text()='Login']/parent::a[@id='login_Layer']");
	By lnk_MyNaukri = By.xpath("//div[@class='topIcon user']/parent::a/child::div[@class='mTxt' and text()='My Naukri']");
	String lnk_EditProfile = "//div[@class='subMenu c2']/descendant::a[text()='%s']";
		
	public void clickLinkInMyNaukriMenu(String link) {
		uiLibrary.hoverOver(lnk_MyNaukri);
		uiLibrary.clickElementUsingJS(By.xpath(uiLibrary.formatString(lnk_EditProfile, link))); 
	}
	
	
	/* Generic Methods */
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public boolean isExactTitlePresent(String title) {
		return uiLibrary.waitForExactTitle(title);
	}

	public boolean checkPartialTitle(String partialTitle) {
		return uiLibrary.waitForTitleContaining(partialTitle);
	}

	public void clickLogin() {
		uiLibrary.clickElement(lnk_LoginButton);
	}

	public String getParentWindowHandle() {
		parentWindowHandle = driver.getWindowHandle();
		return parentWindowHandle;
	}

	public void switchToParentWindowHandle() {
		driver.switchTo().window(parentWindowHandle);
	}
	
	public Set<String> getWindowHandles() {
		windowHandles = driver.getWindowHandles();
		return windowHandles;
	}

	public void closeOtherWindows() {
		String title = getTitle();
		System.out.println("Current title is: " + title);
		for (String handle : getWindowHandles()) {
			driver.switchTo().window(handle);
			if (!getTitle().equals(title)) {
				System.out.println("Closing window with title: " + getTitle());
				driver.close();
			}
		}
		getWindowHandles();
		int size = windowHandles.size();
		for (String handle : windowHandles) {
			driver.switchTo().window(handle);
		}
	}

	public void switchToWindow(String title) {
		boolean foundWindow = false;
		getWindowHandles();
		for (String handle : windowHandles) {
			if (driver.switchTo().window(handle).getTitle().contains(title)) {
				System.out.println("Switched to window with title:" + title);
				foundWindow = true;
				break;
			}
		}
		if (!foundWindow) {
			log.info("Couldn't find the window with title -> " + title + "\nSwitching to parent window.");
			switchToParentWindowHandle();
		}
	}
	
	public void closeThisWindow() {
		driver.close();
	}

}
