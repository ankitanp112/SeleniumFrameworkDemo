package AnkitAnand.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AnkitAnand.AbstractComponents.reusableMethods;

public class cartPage extends reusableMethods{
	
	WebDriver driver;
	
	public cartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".infoWrap h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath = "//button[contains(text(),'Checkout')]")
	WebElement checkOutButton;
	
	
	
	public Boolean isPresentIncart(String prodName)
	{
		Boolean isPresent = cartProducts.stream().anyMatch(cartProduct ->cartProduct.getText().contains(prodName));
		return isPresent;
	}
	
	public void checkOut() throws InterruptedException
	{
		Thread.sleep(5000);
		actionScrollable();
		System.out.println(checkOutButton.getLocation());
		checkOutButton.click();
		
	}
	
	

}
