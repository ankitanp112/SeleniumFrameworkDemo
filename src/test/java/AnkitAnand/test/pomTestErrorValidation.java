package AnkitAnand.test;

import java.io.IOException;	

import org.testng.Assert;
import org.testng.annotations.Test;

import AnkitAnand.baseTest.initializeDriver;

import AnkitAnand.pageObjects.cartPage;

import AnkitAnand.pageObjects.productPage;

public class pomTestErrorValidation extends initializeDriver {

	@Test(groups = "ErrorHandling")
	public void loginPageErrorValidation() throws IOException, InterruptedException
	{
		
		login.loginApplication("ankitanp@gmail.com", "1Ankitanp1@gmail.com");
		Assert.assertEquals("Incorrect email or password.", login.getErrorMessage());
				
	}
	
	@Test
	public void productPageErrorValidation() throws IOException, InterruptedException
	{
		String prodName = "ADIDAS ORIGINAL";
		productPage product = login.loginApplication("ankitanp111@ankitmail.com", "Ankit@ankit123");
		cartPage cart = product.addProductToCart(prodName);
		product.goToCartHeader();
		Boolean match = cart.isPresentIncart("ADI12321");
		Assert.assertFalse(match);
	}
	
	
}
