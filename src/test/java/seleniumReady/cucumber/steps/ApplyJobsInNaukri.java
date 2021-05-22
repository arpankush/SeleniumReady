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

public class ApplyJobsInNaukri extends Initializer{

	private static Logger log = LoggerHelper.getLogger(ApplyJobsInNaukri.class);
	private WebDriver driver;
	public TestContext testContext;
	Scenario scenario;
	WaitHelper waitHelper;
	JSHelper jsHelper;
	NaukriCommonPage naukriCommonPage;
	NaukriHomePage naukriHomePage;
	MyNaukriDashboardPage myNaukriDashboardPage;
	
	public ApplyJobsInNaukri(TestContext context) {
		testContext = context;
		scenario = testContext.getScenario();
		driver = testContext.getDriverManager().getDriver();
		waitHelper = testContext.getHelperManager().getWaitHelper();
		naukriCommonPage = testContext.getPageObjectManager().getCommonObjects();
		naukriHomePage = testContext.getPageObjectManager().getNaukriHomePage();
		myNaukriDashboardPage = testContext.getPageObjectManager().getMyNaukriDashboard();
	}

	@Given("User clicks on login page")
	public void user_clicks_on_login_page() {
	    naukriCommonPage.clickLogin();
	}

	@When("User enter username as {string}")
	public void user_enter_username_as(String emailID) {
	   naukriHomePage.enterEmailID(emailID);
	}

	@When("User enter password as {string}")
	public void user_enter_password_as(String password) {
		naukriHomePage.enterPassword(password);
	}

	@When("User click on login button")
	public void user_click_on_login_button() {
		naukriHomePage.clickLogin();
	}
	
	@Given("User clicks on View All in New Jobs in My Job Alerts")
	public void user_clicks_on_view_all_in_new_jobs_in_my_job_alerts() {
		myNaukriDashboardPage.clickViewAllInMyJobAlerts();
	}
	
	@And("User selects checkboxes and Apply Jobs")
	public void user_selects_checkboxes_and_apply_job() {
		myNaukriDashboardPage.selectCheckBoxesAndApplyJobs();
	}
		
}
