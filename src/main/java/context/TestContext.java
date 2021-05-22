package context;

import io.cucumber.java.Scenario;
import managers.DriverManager;
import managers.HelperManager;
import managers.PageObjectManager;

public class TestContext {
	
	private DriverManager driverManager;
	private PageObjectManager pageObjectManager;
	private ScenarioContext scenarioContext;
	private HelperManager helperManager;
	private Scenario scenarioInstance;
	
	public TestContext(){
		driverManager = new DriverManager();
		pageObjectManager = new PageObjectManager(driverManager.getDriver());
		scenarioContext = new ScenarioContext();
		helperManager = new HelperManager(driverManager.getDriver());
	}

	public DriverManager getDriverManager() {
		return driverManager;
	}
	
	public PageObjectManager getPageObjectManager() {
		return pageObjectManager;
	}
	
	public ScenarioContext getScenarioContext() {
		return scenarioContext;
	}
	
	public HelperManager getHelperManager() {
		return helperManager;
	}
	
	public Scenario getScenario() {
		return scenarioInstance;
	}
	
	public void setScenarioInstance(Scenario scenario) {
		scenarioInstance = scenario;
	}
	
}
