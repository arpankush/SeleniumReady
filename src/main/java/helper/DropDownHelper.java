package helper;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropDownHelper {

	Select select;
	public DropDownHelper(WebDriver driver) {
		System.out.println("DropDownHelper construtor.");
	}
	
	private Select getSelect(WebElement element) {
		return new Select(element);
	}
	
	public void selectByIndex(WebElement element,int index) {
		select = getSelect(element);
		select.selectByIndex(index);
	}
	
	public void selectByValue(WebElement element,String value) {
		select = getSelect(element);
		select.selectByValue(value);
	}
	
	public void selectByVisibleText(WebElement element,String visibleText) {
		select = getSelect(element);
		select.selectByVisibleText(visibleText);
	}
	
	public void deselectByIndex(WebElement element,int index) {
		select = getSelect(element);
		select.deselectByIndex(index);
	}
	
	public void deselectByValue(WebElement element,String value) {
		select = getSelect(element);
		select.deselectByValue(value);
	}
	
	public void deselectByVisibleText(WebElement element,String visibleText) {
		select = getSelect(element);
		select.deselectByVisibleText(visibleText);
	}
	
	public List<String> getOptions(WebElement element){
		select = getSelect(element);
		List<WebElement> optionsElements = select.getOptions();
		List<String> options = new ArrayList<String>();
		for (WebElement e : optionsElements) {
			options.add(e.getText());
		}
		return options;
	}
	
}
