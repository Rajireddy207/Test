package Com.Tricenties_TestScript;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Com.Tricentis.Utilities.Base;
import Com.Tricentis.Utilities.ExcelUtilities;
import Com.Tricentis_Page.Demowebshop_EndToEnd;

public class EndToEnd_Test extends Base{

	Logger logger = Logger.getLogger(EndToEnd_Test.class);
	public String Browsername;
	public String URL;
	public String methodName;
	public String Email;
    public String password;

	@BeforeMethod
	public void initialdata(Method testMethod) throws IOException {
		methodName = testMethod.getName();
		String[] testData = ExcelUtilities.readExcel(File_TestData, "Testdata");


		// Fetching data from Excel
		Browsername = testData[0];

		URL = testData[1];
        Email = testData[2];
        password = testData[3];
		
		logger.info("Browser Using   " + Browsername);
		
		logger.info("Fetching the URL  " + URL);
		initialization(Browsername, URL);

	}

@Test
public void TC01_Validate_LoginAccountID() throws Exception {
	Demowebshop_EndToEnd lp = new Demowebshop_EndToEnd(driver);
	lp.Demo_WebShop_Login(Email, password);
	String Acutal_EmailID = lp.AccountId_Text();
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	
	if(Acutal_EmailID.equalsIgnoreCase(Email)) {
		Assert.assertTrue(true);
		logger.info("succesfully validated Account Id is " + Acutal_EmailID);
	}
	else {
	logger.error("Account id is not matched with the " +Acutal_EmailID);	
	getScreenshot(driver, "Account_idText_Mismatch");
	Assert.assertTrue(false);
	}
	lp.Demo_WebShop_Logout();
	logger.info("Demo web shop logged out successfully");

	driver.close();
	}

@Test
public void TC02_Demohop_EndToEnd() {
	Demowebshop_EndToEnd sp = new Demowebshop_EndToEnd(driver);
	sp.Demo_WebShop_Login(Email, password);
	sp.Demo_ClearshoppingCart();
	sp.Demo_Computers();
	logger.info("Computers and Destop are clicked successfully ");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	sp.Demo_Selectcomputer();
	String Price = sp.Demo_GetPrice();
	logger.info("fetched the price successfully & price is " +Price);
	sp.Demo_EnterQuantity("2");
	String AddtoCart_Sucess_Message = sp.GetAddtoCart_Sucess_Message();
	logger.info("product Added  to cart successfully and The message is  " +AddtoCart_Sucess_Message);
	sp.click_shoppingMart();
	
	String sub_Totalprice = sp.Get_sub_Totalprice();
	logger.info("Subtotal Price Fetched successfully and the price is   " +sub_Totalprice);
	sp.click_Agreeandcheckout();
	sp.Newaddress_Select();
	sp.Enter_newaddressDetails("hyderabad", "hyderabad1", "5002", "9440123392");
	logger.info("Billing addreess details entered successfully ");
	sp.Enter_shippingDetails();
	sp.shippcontinue_click();
	sp.Shipping_Method();
	String payment_validateString = sp.payment_Validate();
	logger.info("payment validated successfully and the message is  " + payment_validateString);
	sp.Payment_confirm_confirmation_click();
	String validatemessage=sp.validateorderMessage();
	logger.info("order successfully validated and the message is  " + validatemessage);
	String validateorderString =sp.validateorderno();
	logger.info("order successfully validated and the message is  " + validateorderString);
	sp.continue_end();
	sp.Demo_WebShop_Logout();
	driver.close();
}
}
