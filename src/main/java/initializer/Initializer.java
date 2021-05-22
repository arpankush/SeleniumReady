package initializer;

import reader.AppConfigReader;

public class Initializer {

	public static AppConfigReader appConfigReader = new AppConfigReader();

	public static String browser = appConfigReader.getBrowser();
	public static String appURL = appConfigReader.getAppURL();
	public static int implicitlyWaitInSeconds = appConfigReader.getImplicitlyWaitInSeconds();
	
	public static int overallTimeForTriesInSeconds = appConfigReader.getOverallTimeForTriesInSeconds();
	public static int fluentWaitTimeoutInSeconds = appConfigReader.getFluentWaitTimeoutInSeconds();
	public static int fluentWaitPollingEveryInSeconds = appConfigReader.getFluentWaitPollingEveryInSeconds();
	
	public static int explicitlyWaitInSeconds = appConfigReader.getExplicitlyWaitInSeconds();
	public static int maxPageLoadTimeInSeconds = appConfigReader.getMaxPageLoadTimeInSeconds();
	public static int getScriptTimeoutInSeconds = appConfigReader.getScriptTimeoutInSeconds();
	
	public static String environmentType = appConfigReader.getEnvironmentType();
	public static String downloadPath = appConfigReader.getDownloadPath();

}
