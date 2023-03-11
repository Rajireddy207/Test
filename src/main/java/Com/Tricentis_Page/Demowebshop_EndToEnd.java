package Com.Tricentis_Page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Com.Tricentis.Utilities.Base;

public class Demowebshop_EndToEnd extends Base {
//Locators for Login and Logout
	@FindBy(xpath = "//*[@class='ico-login']")
	WebElement login_Link;
	@FindBy(id = "Email")
	WebElement Email_Tbx;
	@FindBy(id = "Password")
	WebElement Password_Tbx;
	@FindBy(xpath = "//*[@class='button-1 login-button']")
	WebElement Login_Btn;
	@FindBy(xpath = "//*[@class='header-links']/ul/li[1]/a")
	WebElement AccountId_Txt;
	@FindBy(xpath = "//*[@class ='ico-logout']")
	WebElement logout_Link;
//Locators for Remaining process
	@FindBy(xpath = "//*[@id ='topcartlink']/a/span[1]")
	WebElement shoppingMart;
	@FindBy(name="removefromcart")
	List<WebElement> remove_cartcheckbox;
	@FindBy(xpath="//*[@value='Update shopping cart']")
	WebElement updateCart;
	@FindBy(xpath = "//*[@class ='top-menu']/li[2]/a")
	WebElement computers;
	@FindBy(xpath = "//*[@class ='top-menu']/li[2]/ul/li[1]/a")
	WebElement Desktops;
	@FindBy(xpath = "//*[@class ='product-grid']/div/div/div/a[1]//*[@title ='Show details for Build your own cheap computer']")
	WebElement select_computer;
	@FindBy(xpath ="//*[@class='product-price']")
	WebElement get_price;
	@FindBy(id="addtocart_72_EnteredQuantity")
	WebElement enter_Quantity;
	@FindBy(xpath="//*[@class='button-1 add-to-cart-button']")
	WebElement click_Add_tocart;
	@FindBy(xpath ="//*[@class ='content']")
     WebElement AddtoCart_Sucess_Message;
	@FindBy(xpath="//*[@title ='Close']")
	WebElement closeBtt;
	@FindBy(xpath ="//*[@class ='total-info']/table//tbody/tr[1]/td[2]/span/span")
    WebElement sub_Totalprice;
	@FindBy(id ="termsofservice")
    WebElement click_agreebtt;
	@FindBy(xpath="//*[@class='button-1 checkout-button']")
	WebElement Checkout_button;
	@FindBy(name ="billing_address_id")
	WebElement select_newaddress;
	@FindBy(name="BillingNewAddress.City")
	WebElement value_forcity;
	@FindBy(xpath="//*[@id='BillingNewAddress_Address1']")
	WebElement value_Address1;
	@FindBy(name="BillingNewAddress.ZipPostalCode")
	WebElement value_ZipPostalCode;
	@FindBy(name="BillingNewAddress.PhoneNumber")
	WebElement value_PhoneNumber;
	@FindBy(xpath="//*[@id ='billing-buttons-container']/input")
	WebElement continue_click;
	@FindBy(xpath="//*[@id='BillingNewAddress_CountryId']")
	WebElement select_country;
	@FindBy(name ="shipping_address_id")
	WebElement select_shippingaddress;
	@FindBy(xpath="//*[@id ='shipping-buttons-container']/input")
	WebElement shippcontinue_click;
	@FindBy(id ="shippingoption_1")
	WebElement NextDay_Air;
	@FindBy(xpath="//*[@id ='shipping-method-buttons-container']/input")
	WebElement shippingcontinue_click;
	@FindBy(xpath="//*[@id ='payment-method-buttons-container']/input")
	WebElement paymentcontinue_click;
	@FindBy(xpath="//*[@id='checkout-payment-info-load']/div/div/div[1]/table/tbody/tr/td/p")
	WebElement Payment_Information;
	@FindBy(xpath="//*[@id ='payment-info-buttons-container']/input")
	WebElement paymentconfirmation_click;
	@FindBy(xpath="//*[@id ='confirm-order-buttons-container']/input")
	WebElement confirmorder_click;
	@FindBy(xpath="//*[@class ='section order-completed']/div/strong")
	WebElement confimation_validation;
	@FindBy(xpath="//*[@class ='section order-completed']/ul/li[1]")
	WebElement validate_orderno;
	@FindBy(xpath ="//*[@class ='section order-completed']/div[2]/input")
	WebElement continue_end;
//Creating the Constructor
	public Demowebshop_EndToEnd(WebDriver rdriver) {

		rdriver = this.driver;

		PageFactory.initElements(rdriver, this);

	}

//Login 
	public void Demo_WebShop_Login(String Email, String Password) {
		login_Link.click();
		Email_Tbx.sendKeys(Email);
		Password_Tbx.sendKeys(Password);
		Login_Btn.click();
	}

	public String AccountId_Text() {
		return AccountId_Txt.getText();
	}

	public void Demo_WebShop_Logout() {
		logout_Link.click();
	}

	public void Demo_ClearshoppingCart() {
		shoppingMart.click();
		List<WebElement> checkboxes = remove_cartcheckbox;
		int checkboxcount = checkboxes.size();
		System.out.println(checkboxcount);
     if (checkboxcount>=1) {
		for(int i=0; i<checkboxes.size(); i++)        
	    {   
	  WebElement value = checkboxes.get(i);
     value.click();
	}
		updateCart.click();
	}
     else {
    	System.out.println("There are no products in cart");
     }
	}
	public void Demo_Computers() {
		Actions actions = new Actions(driver);
		// Hovering on Computers
		actions.moveToElement(computers);
		// To mouseover Desktops
		actions.moveToElement(Desktops);
		actions.click().build().perform();
	}
	public void Demo_Selectcomputer() {
		select_computer.click();
	}
	public String Demo_GetPrice() {	
	return get_price.getText();
		}
	public void Demo_EnterQuantity(String quantity) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated((By.id("addtocart_72_EnteredQuantity"))));
		JavascriptExecutor jse = (JavascriptExecutor) driver;//Applying Java script logic to drill down
		jse.executeScript("arguments[0].scrollIntoView()", enter_Quantity);//it scrolls down  till the Submit a Question/Suggestion button identifies
		//jse.executeScript("arguments[0].click();", enter_Quantity);//after that it clicks on Submit a Question/Suggestion button
		enter_Quantity.sendKeys(quantity);
		click_Add_tocart.click();
		
	}
	public String GetAddtoCart_Sucess_Message() {
		return AddtoCart_Sucess_Message.getText();
		
	}
	
	public void click_shoppingMart() {
		closeBtt.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		JavascriptExecutor jse = (JavascriptExecutor) driver;//Applying Java script logic to drill down
		jse.executeScript("scroll(0, -250);");
		shoppingMart.click();
		
	}
	public String Get_sub_Totalprice() {
		return sub_Totalprice.getText();
	}
	public void click_Agreeandcheckout() {
		click_agreebtt.click();
		Checkout_button.click();
	}
	public void Newaddress_Select() {
		Select selectaddress= new Select(select_newaddress);
		selectaddress.selectByVisibleText("New Address");
	}
	public void Enter_newaddressDetails(String City,String Adress1,String Postal,String PhoneNo) {
		value_forcity.sendKeys(City);
		value_Address1.sendKeys(Adress1);
		value_ZipPostalCode.sendKeys(Postal);
		value_PhoneNumber.sendKeys(PhoneNo);
		Select selectcountry= new Select(select_country);
		selectcountry.selectByValue("1");
		continue_click.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}
	public void Enter_shippingDetails() {
		Select selectaddress= new Select(select_shippingaddress);
		selectaddress.selectByVisibleText("planit test, Address1, Hyd 500035, India");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
	}
		public void shippcontinue_click() {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.presenceOfElementLocated((By.id("shipping-please-wait"))));
	        shippcontinue_click.click();
		}
	public void Shipping_Method() {
		NextDay_Air.click();
		shippingcontinue_click.click();
		paymentcontinue_click.click();
		
	}
	public String payment_Validate() {
		return Payment_Information.getText();
	}
	public void Payment_confirm_confirmation_click() {
		
		paymentconfirmation_click.click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;//Applying Java script logic to drill down
		jse.executeScript("arguments[0].scrollIntoView()", confirmorder_click);
		confirmorder_click.click();
	}
	public String validateorderMessage() {
		return confimation_validation.getText();
		
	}
	public String validateorderno() {
		return validate_orderno.getText();
	}
	public void continue_end() {
		continue_end.click();
		
	}
}
