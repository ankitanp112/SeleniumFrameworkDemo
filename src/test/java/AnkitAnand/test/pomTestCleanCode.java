package AnkitAnand.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import AnkitAnand.baseTest.initializeDriver;
import AnkitAnand.data.DataReader;
import AnkitAnand.pageObjects.addressPage_finalPage;
import AnkitAnand.pageObjects.cartPage;
import AnkitAnand.pageObjects.orderPage;
import AnkitAnand.pageObjects.productPage;

public class pomTestCleanCode extends initializeDriver {
	
	String prodName = "ADIDAS ORIGINAL", country = "india";
	
	//Using Multidimensional Array
	/*@Test(dataProvider = "getData", groups = "Purchase")
	public void submitOrder(String email, String password, String prodName) throws IOException, InterruptedException
	{
		
		productPage product = login.loginApplication(email, password);
		cartPage cart = product.addProductToCart(prodName);
		product.goToCartHeader();
		Boolean match = cart.isPresentIncart(prodName);
		Assert.assertTrue(match);
		cart.checkOut();
		addressPage_finalPage address = new addressPage_finalPage(driver);
		address.submitCart(country);		
	}*/
	
	@Test(dataProvider = "getDataUsingJSON", groups = "Purchase")
	 //Thats why alwaysrun in baseclass for before and after method because if groups tag is missing in before and after method of base class it will fail
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
		
		productPage product = login.loginApplication(input.get("email"), input.get("password"));
		cartPage cart = product.addProductToCart(input.get("prodName"));
		product.goToCartHeader();
		Boolean match = cart.isPresentIncart(input.get("prodName"));
		Assert.assertTrue(match);
		cart.checkOut();
		addressPage_finalPage address = new addressPage_finalPage(driver);
		address.submitCart(country);		
	}
	
	@Test(dependsOnMethods = "submitOrder")
	public void orderHistory() throws IOException, InterruptedException
	{
		
		productPage product = login.loginApplication("ankitanp@gmail.com", "Ankitanp1@gmail.com");
		orderPage order = product.goToOrderHeader();
		Assert.assertTrue(order.isPresentInOrderHistory(prodName));
	}
	
	
	@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"ankitanp@gmail.com", "Ankitanp1@gmail.com", "ADIDAS ORIGINAL"}, {"ankitanp111@ankitmail.com", "Ankit@ankit123", "ZARA COAT 3"}}; 
	}
	
	@DataProvider
	public Object[][] getDataUsingHashMap()
	{
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "ankitanp@gmail.com");
		map.put("password", "Ankitanp1@gmail.com");
		map.put("prodName", "ADIDAS ORIGINAL");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "ankitanp111@ankitmail.com");
		map1.put("password", "Ankit@ankit123");
		map1.put("prodName", "ZARA COAT 3");
		
		HashMap<String, String> map2 = new HashMap<String, String>();
		
		
		return new Object[][] {{map}, {map1}, {map2}}; 
	}
	

	@DataProvider
	public Object[][] getDataUsingJSON() throws IOException
	{
		DataReader dr = new DataReader();
		List<HashMap<String,String>> listaa = dr.getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//AnkitAnand//data//PurchaseOrder.json");
		
		return new Object[][] {{listaa.get(0)}, {listaa.get(1)}, {listaa.get(2)}}; 
	}
	
	
}
