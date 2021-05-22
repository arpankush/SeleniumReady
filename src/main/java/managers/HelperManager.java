package managers;

import org.openqa.selenium.WebDriver;

import helper.DropDownHelper;
import helper.JSHelper;
import helper.WaitHelper;

public class HelperManager {
	
	private WebDriver driver;
	private WaitHelper waitHelper;
	private JSHelper jsHelper;
	private DropDownHelper dropDownHelper;
	
	public HelperManager(WebDriver driver) {
		this.driver = driver;
	}
	
	public WaitHelper getWaitHelper() {
		return (waitHelper == null)? waitHelper = new WaitHelper(driver) : waitHelper;
	}
	
	public JSHelper getJSHelper() {
		return (jsHelper == null)? jsHelper = new JSHelper(driver) : jsHelper;
	}
	
	public DropDownHelper getDropDownHelper() {
		return (dropDownHelper == null)? dropDownHelper = new DropDownHelper(driver) : dropDownHelper;
	}

}
