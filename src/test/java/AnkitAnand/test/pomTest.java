package AnkitAnand.test;

import java.io.IOException;	

import org.testng.Assert;
import org.testng.annotations.Test;

import AnkitAnand.baseTest.initializeDriver;
import AnkitAnand.pageObjects.addressPage_finalPage;
import AnkitAnand.pageObjects.cartPage;
import AnkitAnand.pageObjects.loginPage;
import AnkitAnand.pageObjects.productPage;

public class pomTest extends initializeDriver {

	@Test
	public void submitOrder() throws IOException, InterruptedException
	{
		
		String prodName = "ADIDAS ORIGINAL";
		String country = "india";
		
		//initDriver();
		
		loginPage login = launchApplication();
		
		//Login Page
		login.loginApplication("ankitanp@gmail.com", "Ankitanp1@gmail.com");
		
		//Products Page
		productPage product = new productPage(driver);
		//List<WebElement> products = product.getProductList(); 
		product.addProductToCart(prodName);
		product.goToCartHeader();
		
		
		//Validate Cart and Click on Checkout
		cartPage cart = new cartPage(driver);
		Boolean match = cart.isPresentIncart(prodName);
		Assert.assertTrue(match);
		cart.checkOut();
		
		//Enter Country Name, select Country and Submit
		addressPage_finalPage address = new addressPage_finalPage(driver);
		address.submitCart(country);
		
		
		
		
		
		
		
	}
}
