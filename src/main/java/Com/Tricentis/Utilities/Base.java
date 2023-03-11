package Com.Tricentis.Utilities;


import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.apache.commons.text.RandomStringGenerator;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Base {
	static Logger logger = Logger.getLogger(Base.class);
	public static WebDriver driver;
	public String File_TestData = "";
	@BeforeClass
	public void beforeclassinitilization()
	{
		File_TestData = "src/test/resources/DemoWebShop_TestData.xlsx";

	}
	public static void initialization(String browserName,String URL) {
	
     if(browserName.equals("Chrome")){
    	 
    	 WebDriverManager.chromedriver().setup();//Driver setup
         driver = new ChromeDriver(); 
     }
     else if(browserName.equals("Firefox")){
    	 WebDriverManager.firefoxdriver().setup();
         driver = new FirefoxDriver(); 
     }
     else if(browserName.equals("Edge")){
    	WebDriverManager.edgedriver().setup();
    	driver = new EdgeDriver();
     }
	 
     driver.manage().window().maximize();
     driver.manage().deleteAllCookies();
     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
     
     driver.get(URL);

     
     
	}
	
@BeforeTest
public void loadlog4J() { 

	
	PropertyConfigurator.configure("log4j.properties"); 
 

} 

public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
TakesScreenshot ts = (TakesScreenshot) driver;
File source = ts.getScreenshotAs(OutputType.FILE);
//after execution, you could see a folder "FailedTestsScreenshots" under src folder
String destination = System.getProperty("user.dir") + "/Screenshots/"+screenshotName+dateName+".png";
File finalDestination = new File(destination);
FileUtils.copyFile(source, finalDestination);
return destination;
}	

public static String generateRandomStr() {
String randomLetters = "";
try {
RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
randomLetters = generator.generate(10);
return randomLetters; } catch (Exception e) {
e.printStackTrace();
logger.error(">>>>>>>>>>>>>>>>>>>>> Generate random string did not work >>>>>>>>>>>>>>>>>>>>");
return randomLetters;
} }

public static  void Select_dropdown(WebElement element,String text) {
	Select slct = new Select(element);
	slct.selectByValue(text);
	}
}

