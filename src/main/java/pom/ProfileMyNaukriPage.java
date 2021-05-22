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

public class ProfileMyNaukriPage {

	private static Logger log = LoggerHelper.getLogger(ProfileMyNaukriPage.class);
	private WebDriver driver;
	WaitHelper waitHelper;
	JSHelper jsHelper;
	UILibrary uiLibrary;

	public ProfileMyNaukriPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		uiLibrary = new UILibrary(driver);
		jsHelper = new JSHelper(driver);
		waitHelper = new WaitHelper(driver);
	}

	String lnk_QuickLinks = "//parent::li[@class='collection-item']/span[@class='text' and text()='%s']";
	By fin_UpdateResume = By.xpath("//input[@id='attachCV']");
	By txt_UploadedResumeFileName = By
			.xpath("//div[@class='cvPreview']/descendant::div[@class='left']/b[@class='truncate exten']");
	By txt_UploadedResumeDate = By
			.xpath("//div[@class='cvPreview']/descendant::div[@class='left']/span[@class='updateOn']");

	public void clickQuickLink(String link) {
		uiLibrary.clickElementUsingJS(By.xpath(uiLibrary.formatString(lnk_QuickLinks, link)));
	}
	
	public void clickUploadResumeButton() {
		uiLibrary.clickElement(fin_UpdateResume);
	}

}
