package managers;

import org.openqa.selenium.WebDriver;

import pom.NaukriCommonPage;
import pom.MyNaukriDashboardPage;
import pom.NaukriHomePage;
import pom.ProfileMyNaukriPage;

public class PageObjectManager {
	
	private WebDriver driver;
	private NaukriCommonPage naukriCommonPage;
	private NaukriHomePage naukriHomePage;
	private MyNaukriDashboardPage myNaukriDashboardPage;
	private ProfileMyNaukriPage profileMyNaukriPage;
	
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}
	
	public NaukriCommonPage getCommonObjects() {
		return (naukriCommonPage == null)? naukriCommonPage = new NaukriCommonPage(driver) : naukriCommonPage;
	}
	
	public NaukriHomePage getNaukriHomePage() {
		return (naukriHomePage == null)? naukriHomePage = new NaukriHomePage(driver) : naukriHomePage;
	}
	
	public MyNaukriDashboardPage getMyNaukriDashboard() {
		return (myNaukriDashboardPage == null)? myNaukriDashboardPage = new MyNaukriDashboardPage(driver) : myNaukriDashboardPage;
	}
	
	public ProfileMyNaukriPage getProfileMyNaukri() {
		return (profileMyNaukriPage == null)? profileMyNaukriPage = new ProfileMyNaukriPage(driver) : profileMyNaukriPage;
	}
}
