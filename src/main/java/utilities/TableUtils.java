package utilities;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import helper.LoggerHelper;
import pom.NaukriCommonPage;

public class TableUtils {

	private static Logger log = LoggerHelper.getLogger(NaukriCommonPage.class);
	private WebDriver driver;
	
	public TableUtils(WebDriver driver) {
		this.driver = driver;
		log.info("TableUtils has been create. \nTODO TableUtils");
	}
	//TODO
}
