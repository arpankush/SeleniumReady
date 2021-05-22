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

public class CommonSteps extends Initializer {

	private static Logger log = LoggerHelper.getLogger(CommonSteps.class);
	private WebDriver driver;
	public TestContext testContext;
	Scenario scenario;
	WaitHelper waitHelper;
	JSHelper jsHelper;
	NaukriCommonPage naukriCommonPage;
	NaukriHomePage naukriHomePage;
	MyNaukriDashboardPage myNaukriDashboardPage;

	public CommonSteps(TestContext context) {
		testContext = context;
		scenario = testContext.getScenario();
		driver = testContext.getDriverManager().getDriver();
		waitHelper = testContext.getHelperManager().getWaitHelper();
		naukriCommonPage = testContext.getPageObjectManager().getCommonObjects();
		naukriHomePage = testContext.getPageObjectManager().getNaukriHomePage();
		myNaukriDashboardPage = testContext.getPageObjectManager().getMyNaukriDashboard();
	}

	@Given("Verify title is exactly {string}")
	public void verify_title_is_exactly(String title) {
		Assert.assertTrue(naukriCommonPage.isExactTitlePresent(title));
	}

	@Given("Get parent window handle")
	public void get_parent_window_handle() {
		naukriCommonPage.getParentWindowHandle();
	}

	@Given("No other windows are open")
	public void no_other_windows_are_open() {
		naukriCommonPage.closeOtherWindows();
	}

	@Given("User clicks on {string} from My Naukri")
	public void user_clicks_on_from_my_naukri(String string) {
		naukriCommonPage.clickLinkInMyNaukriMenu(string);
	}

}
