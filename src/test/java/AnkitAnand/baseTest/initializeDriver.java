package AnkitAnand.baseTest;

import java.io.File;		
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.events.ConsoleEvent;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverInfo;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import AnkitAnand.pageObjects.loginPage;

public class initializeDriver {
	
	
	public WebDriver driver; // = new EdgeDriver();
	public loginPage login;
	
	public WebDriver initDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream is = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//AnkitAnand//globalComponents//globalVar.properties");
		prop.load(is);
		
							 //Ternary Operator
							// if condition ? a : b 
						    // if condition is true --> a
							// if condition is false --> b
							// if we are sending chrome from browser --> Condition is true --> b
							// mvn test -DbrowserName=chrome -PRegression
							// browserName --> is the same variable which we are using in our code
		
		String browserName = System.getProperty("browserName")!=null ? System.getProperty("browserName") : prop.getProperty("browserName");
		
		
		if(browserName.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			
			if(browserName.contains("headless"))
			{

				options.addArguments("headless");
			}
			
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));
		}
		
		else if(browserName.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
		}
		
		else if(browserName.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
				
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	@BeforeMethod(alwaysRun = true)
	public loginPage launchApplication() throws IOException
	{
		driver = initDriver();
		login = new loginPage(driver);
		login.goTo();
		return login;		
	}
	
	
	public String getScreenShot(String testCaseName) throws IOException
	{
		//Type cast the driver to screenshot mode
		TakesScreenshot ts = (TakesScreenshot)driver;
		
		//Capture the screenshot and store it in the specified location
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		//Give the file name where to send the above captured screenshot to
		File file = new File(System.getProperty("user.dir")+"//reports//" +testCaseName+ ".png");
		
		//Load the source file to the file created
		FileUtils.copyFile(source, file);
		
		return System.getProperty("user.dir")+"//reports//" +testCaseName+ ".png";
		
	}
	
	

	@AfterMethod(alwaysRun = true)
	public void tearDown()
	{
		driver.close();
	}
}
