package pom;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.UILibrary;
import context.TestContext;
import helper.JSHelper;
import helper.LoggerHelper;
import helper.WaitHelper;

public class MyNaukriDashboardPage {

	private static Logger log = LoggerHelper.getLogger(MyNaukriDashboardPage.class);
	private WebDriver driver;
	WaitHelper waitHelper;
	JSHelper jsHelper;
	UILibrary uiLibrary;
	NaukriCommonPage naukriCommonPage;
	
	public MyNaukriDashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		uiLibrary = new UILibrary(driver);
		jsHelper = new JSHelper(driver);
		waitHelper = new WaitHelper(driver);
		naukriCommonPage = new NaukriCommonPage(driver);
	}
	
	By lnk_ViewAllInMyJobAlerts = By.xpath("//span[text()='New Jobs in My Job Alerts']/ancestor::div[@class='card dashboard-card']/descendant::div[@class='view-all right-align']/a[text()='View All']");
	By chk_UncheckedJob = By.xpath("//span[@class='action jobType unchecked'][not(contains(@class,'action jobType unchecked checked'))]");
	By lnk_UncheckedJob = By.xpath("//span[@class='action jobType unchecked'][not(contains(@class,'action jobType unchecked checked'))]/preceding-sibling::span[@class='content']/ul/li/a[@id='jdUrl']");
	By lnk_Apply = By.xpath("//button[@id='trig2' and @class='apply blueBtn']");
	By btn_ApplyJob = By.xpath("//div[@class='apply-button-container']/button[text()='Apply']");
	By txt_ApplyConfirmation = By.xpath("//span[@class='apply-message' and starts-with(text(),'You have successfully applied to')]");
	
	public void clickViewAllInMyJobAlerts() {
		uiLibrary.clickElement(lnk_ViewAllInMyJobAlerts);
	}
	
	public void selectCheckBoxesAndApplyJobs() {
		List<WebElement> listOfJobCheckboxes = driver.findElements(lnk_UncheckedJob);
		for (WebElement e : listOfJobCheckboxes) {
			if (uiLibrary.isDisplayed(e)) {
				naukriCommonPage.getParentWindowHandle();
				uiLibrary.clickElementUsingJS(e);
				naukriCommonPage.switchToWindow(e.getText());
				uiLibrary.clickElementUsingJS(btn_ApplyJob);
				waitHelper.visibilityOfElement(txt_ApplyConfirmation);
				naukriCommonPage.closeThisWindow();
				naukriCommonPage.switchToParentWindowHandle();
			}
		}
	}
	
}
