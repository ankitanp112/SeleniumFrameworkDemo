package AnkitAnand.pageObjects;

import java.util.List;	

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AnkitAnand.AbstractComponents.reusableMethods;

public class orderPage extends reusableMethods{
	
	WebDriver driver;
	
	public orderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
		//to initialize PageFactory and to let them know abt driver
		PageFactory.initElements(driver, this);
	}
	
		
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productsNameInOrderHistory;
	
	
	//Products is already present in same page so no need to import
	public Boolean isPresentInOrderHistory(String prodName)
	{
		Boolean isPresent = productsNameInOrderHistory.stream().anyMatch(cartProduct ->cartProduct.getText().contains(prodName));
		return isPresent;
	}

}
