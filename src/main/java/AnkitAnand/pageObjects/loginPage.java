package AnkitAnand.pageObjects;

import org.openqa.selenium.WebDriver;	
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AnkitAnand.AbstractComponents.reusableMethods;

public class loginPage extends reusableMethods {
	
	WebDriver driver;
	
	
	public loginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
		//to initialize PageFactory and to let them know abt driver
		PageFactory.initElements(driver, this);
	}
	
	//This methods can be written here
	//*********************************************************//
	/*driver.findElement(By.id("userEmail")).sendKeys("ankitanp@gmail.com");
	driver.findElement(By.id("userPassword")).sendKeys("Ankitanp1@gmail.com");
	driver.findElement(By.id("login")).click();*/
	
	//Same using PageFactory
	//*********************************************************//
	
	@FindBy(id = "userEmail")
	WebElement userEmail;
	
	@FindBy(id = "userPassword")
	WebElement userPassword;
	
	@FindBy(id = "login")
	WebElement loginBtn;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;
	
	public productPage loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginBtn.click();
		
		productPage product = new productPage(driver);
		return product;
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		waitToAppearWebElement(errorMessage);
		return errorMessage.getText();
	}

}
