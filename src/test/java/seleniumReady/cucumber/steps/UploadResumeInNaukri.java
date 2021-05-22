package seleniumReady.cucumber.steps;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import context.ScenarioContext;
import context.TestContext;
import helper.JSHelper;
import helper.LoggerHelper;
import helper.WaitHelper;
import initializer.Initializer;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import pom.NaukriCommonPage;
import pom.MyNaukriDashboardPage;
import pom.NaukriHomePage;
import pom.ProfileMyNaukriPage;

public class UploadResumeInNaukri extends Initializer{

	private static Logger log = LoggerHelper.getLogger(UploadResumeInNaukri.class);
	private WebDriver driver;
	public TestContext testContext;
	Scenario scenario;
	WaitHelper waitHelper;
	JSHelper jsHelper;
	NaukriCommonPage naukriCommonPage;
	ProfileMyNaukriPage profileMyNaukriPage;
	
	public UploadResumeInNaukri(TestContext context) {
		testContext = context;
		scenario = testContext.getScenario();
		driver = testContext.getDriverManager().getDriver();
		waitHelper = testContext.getHelperManager().getWaitHelper();
		naukriCommonPage = testContext.getPageObjectManager().getCommonObjects();
		profileMyNaukriPage = testContext.getPageObjectManager().getProfileMyNaukri();
	}

	@Given("User clicks on {string} in Quick Links")
	public void user_clicks_on_in_quick_links(String string) {
	    profileMyNaukriPage.clickQuickLink(string);
	}
	
	@Given("User upload resume after clicking Update Resume button")
	public void user_upload_resume_after_clicking_update_resume_button() {
		profileMyNaukriPage.clickUploadResumeButton();
	}
	
}
