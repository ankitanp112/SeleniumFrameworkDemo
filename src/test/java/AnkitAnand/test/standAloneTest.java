package AnkitAnand.test;

import java.time.Duration;	
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class standAloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		String prodName = "ADIDAS ORIGINAL";
		
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.findElement(By.id("userEmail")).sendKeys("ankitanp@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Ankitanp1@gmail.com");
		driver.findElement(By.id("login")).click();
		
		List<WebElement> products = driver.findElements(By.cssSelector("div.offset-md-0"));
		
		/*for(int i=0; i<products.size(); i++)
		{
			if(products.get(i).findElement(By.tagName("b")).getText().equals("ADIDAS ORIGINAL"))
			{
				products.get(i).findElement(By.cssSelector("div.card-body button:last-of-type")).click();
			}		
		}*/
		
		//Using Streams
		WebElement w = products.stream().filter(product->product.findElement(By.tagName("b")).getText().equals(prodName)).findFirst().orElse(null);
		w.findElement(By.cssSelector("div.card-body button:last-of-type")).click();
		w.findElement(By.cssSelector("div.card-body button:last-of-type")).getText();
		
		
		//Explicit Wait and Validate Product is Added
		WebDriverWait waitExplicit = new WebDriverWait(driver, Duration.ofSeconds(10));
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		waitExplicit.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//Validate Selected product is there in Cart or not
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".infoWrap h3"));
		Boolean isPresent = cartProducts.stream().anyMatch(cartProduct ->cartProduct.getText().contains(prodName));
		
		//Assertion - assertTrue --> If isPresent is True then only Pass
		Assert.assertTrue(isPresent);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("//button[contains(@class, 'ta-item')][2]")).sendKeys(Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ENTER);
		
		waitExplicit.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmationMessage = driver.findElement(By.tagName("h1")).getText();
		//This will also work --> Boolean res = confirmationMessage.contentEquals("THANKYOU FOR THE ORDER.");
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
		
	}
}
