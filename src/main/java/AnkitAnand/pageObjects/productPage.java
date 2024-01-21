package AnkitAnand.pageObjects;

import java.util.List;	

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AnkitAnand.AbstractComponents.reusableMethods;

public class productPage extends reusableMethods{
	
	WebDriver driver;
	
	public productPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
		//to initialize PageFactory and to let them know abt driver
		PageFactory.initElements(driver, this);
	}
	
		
	//Same using PageFactory
	//*********************************************************//
	
	//List<WebElement> products = driver.findElements(By.cssSelector("div.offset-md-0"));
	
	@FindBy(css = "div.offset-md-0")
	List<WebElement> products;
	
	
	
	
	By findBy = By.cssSelector("div.offset-md-0");
	By toastProductAdded = By.cssSelector("#toast-container");
	By hiddenPageDisapper = By.cssSelector(".ng-animating");
	
	
	
	public List<WebElement> getProductList()
	{
		waitToAppear(findBy);
		return products;
	}
	
	//Products is already present in same page so no need to import
	public WebElement filterProductByName(String prodName)
	{
		WebElement prod = products.stream().filter(product->product.findElement(By.tagName("b")).getText().equals(prodName)).findFirst().orElse(null);
		return prod;
	}
	
	
	
	public cartPage addProductToCart(String prodName)
	{
		WebElement prod = filterProductByName(prodName);
		prod.findElement(By.cssSelector("div.card-body button:last-of-type")).click();
		waitToAppear(toastProductAdded);
		waitToDisapper(hiddenPageDisapper);
		
		cartPage cart = new cartPage(driver);
		return cart;
	}
	

}
