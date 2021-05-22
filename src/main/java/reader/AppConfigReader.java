package reader;

import ru.qatools.properties.Property;
import ru.qatools.properties.PropertyLoader;
import ru.qatools.properties.Resource;

@Resource.Classpath("AppConfig.properties")
public class AppConfigReader {
	
	public AppConfigReader() {
		PropertyLoader.newInstance().populate(this);
	}
	
	@Property(value = "browser")
	private String browser;
	
	@Property(value = "appURL")
	private String appURL;
	
	@Property(value = "implicitlyWaitInSeconds")
	private int implicitlyWaitInSeconds;
	
	@Property(value = "overallTimeForTriesInSeconds")
	private int overallTimeForTriesInSeconds;
	
	@Property(value = "fluentWaitTimeoutInSeconds")
	private int fluentWaitTimeoutInSeconds;
	
	@Property(value = "fluentWaitPollingEveryInSeconds")
	private int fluentWaitPollingEveryInSeconds;
	
	@Property(value = "explicitlyWaitInSeconds")
	private int explicitlyWaitInSeconds;

	@Property(value = "environmentType")
	private String environmentType;
	
	@Property(value = "maxPageLoadTimeInSeconds")
	private int maxPageLoadTimeInSeconds;
	
	@Property(value = "downloadPath")
	private String downloadPath;
	
	@Property(value = "scriptTimeoutInSeconds")
	private int scriptTimeoutInSeconds;
	
	public String getBrowser() {
		return browser;
	}
	public String getAppURL() {
		return appURL;
	}
	public int getImplicitlyWaitInSeconds() {
		return implicitlyWaitInSeconds;
	}
	public int getOverallTimeForTriesInSeconds() {
		return overallTimeForTriesInSeconds;
	}
	public int getFluentWaitTimeoutInSeconds() {
		return fluentWaitTimeoutInSeconds;
	}
	public int getFluentWaitPollingEveryInSeconds() {
		return fluentWaitPollingEveryInSeconds;
	}
	public int getExplicitlyWaitInSeconds() {
		return explicitlyWaitInSeconds;
	}
	
	public String getEnvironmentType() {
		return environmentType;
	}
	public int getMaxPageLoadTimeInSeconds() {
		return maxPageLoadTimeInSeconds;
	}
	public String getDownloadPath() {
		return downloadPath;
	}
	public int getScriptTimeoutInSeconds() {
		return scriptTimeoutInSeconds;
	}
	
}