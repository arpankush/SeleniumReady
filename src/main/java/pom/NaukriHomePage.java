package pom;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.UILibrary;
import context.TestContext;
import helper.JSHelper;
import helper.LoggerHelper;
import helper.WaitHelper;

public class NaukriHomePage {

	private static Logger log = LoggerHelper.getLogger(NaukriHomePage.class);
	private WebDriver driver;
	WaitHelper waitHelper;
	JSHelper jsHelper;
	UILibrary uiLibrary;
	
	public NaukriHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		uiLibrary = new UILibrary(driver);
		jsHelper = new JSHelper(driver);
		waitHelper = new WaitHelper(driver);	
	}
	
	By txt_EmailID = By.xpath("//div[@class='form-row']/label[text()='Email ID / Username']/following-sibling::input[@placeholder='Enter your active Email ID / Username']");
	By txt_Password = By.xpath("//div[@class='form-row']/label[text()='Password']/following-sibling::input[@placeholder='Enter your password']");
	By btn_Login = By.xpath("//button[@class='btn-primary loginButton' and text()='Login']");
	
	public void enterEmailID(String emailID) {
		uiLibrary.enterText(txt_EmailID, emailID, true);
	}
	
	public void enterPassword(String password) {
		uiLibrary.enterText(txt_Password, password, true);
	}
	
	public void clickLogin() {
		uiLibrary.clickElement(btn_Login);
	}
	
}
