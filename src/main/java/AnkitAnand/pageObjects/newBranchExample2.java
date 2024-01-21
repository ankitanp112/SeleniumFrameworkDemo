package AnkitAnand.pageObjects;

import org.openqa.selenium.WebDriver;	
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AnkitAnand.AbstractComponents.reusableMethods;

public class newBranchExample2 extends reusableMethods {
	
	WebDriver driver;
	
	
	public newBranchExample2(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
		//to initialize PageFactory and to let them know abt driver
		PageFactory.initElements(driver, this);
	}
	
	//This Class is extension of what we added to develop originally

}
