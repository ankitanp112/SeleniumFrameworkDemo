package AnkitAnand.AbstractComponents;

import java.time.Duration;	

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AnkitAnand.pageObjects.orderPage;

public class reusableMethods {
	
	WebDriver driver;
	WebDriverWait waitExplicit;
	Actions a;
	
	public reusableMethods(WebDriver driver) {
		this.driver = driver;
		a = new Actions(driver);
		waitExplicit = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeaderBtn;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHistoryHeaderBtn;
	
	public void waitToAppear(By findBy)
	{
		
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitToAppearWebElement(WebElement findBy)
	{
		
		waitExplicit.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitToDisapper(By findBy)
	{
		waitExplicit.until(ExpectedConditions.invisibilityOf(driver.findElement(findBy)));
	}
	
	public void elementToBeClickable(By findBy) throws InterruptedException
	{
		Thread.sleep(5);
		waitExplicit.until(ExpectedConditions.elementToBeClickable(findBy));
	}
	
	public void actionSendKeys(By findBy, String countryName)
	{
		a.sendKeys(driver.findElement(findBy),countryName).build().perform();
	}
	
	public void actionScrollable()
	{
		a.scrollByAmount(513, 668).perform();
	}
	
	public void goToCartHeader()
	{
		cartHeaderBtn.click();
	}
	
	public orderPage goToOrderHeader()
	{
		orderHistoryHeaderBtn.click();
		orderPage order = new orderPage(driver);
		return order;
	}
	

}
