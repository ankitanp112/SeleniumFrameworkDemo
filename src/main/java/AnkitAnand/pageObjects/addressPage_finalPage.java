package AnkitAnand.pageObjects;

import org.openqa.selenium.By;	
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AnkitAnand.AbstractComponents.reusableMethods;

public class addressPage_finalPage extends reusableMethods {
	
	WebDriver driver;
	
	
	public addressPage_finalPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		
		PageFactory.initElements(driver, this);		
	}
	
	
	@FindBy(xpath = "//button[contains(@class, 'ta-item')][2]")
	WebElement selectDropdownOption;
	
	@FindBy(tagName = "h1")
	WebElement finalMessage;
	
	@FindBy(css = ".action__submit")
	WebElement placeOrder;
	
	By countryInputBox = By.cssSelector("[placeholder='Select Country']");
	By countryDropdown = By.cssSelector(".ta-results");
	By placeOrderValidate = By.cssSelector(".action__submit");
	
	public void submitCart(String countryNameInput) throws InterruptedException
	{
		actionSendKeys(countryInputBox, countryNameInput);
		waitToAppear(countryDropdown);
		selectDropdownOption.sendKeys(Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ENTER);
		elementToBeClickable(placeOrderValidate);
		Thread.sleep(2000);
		placeOrder.click();
		String confirmationMessage = finalMessage.getText();
		//This will also work --> Boolean res = confirmationMessage.contentEquals("THANKYOU FOR THE ORDER.");
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	
	

}
