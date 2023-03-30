package ArifAcdemy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ArifAcademy.TestComponents.BaseTests;
import ArifAcdemy.PageObjects.BasketPage;
import ArifAcdemy.PageObjects.CheckoutPage;
import ArifAcdemy.PageObjects.ConfirmationPage;
import ArifAcdemy.PageObjects.LandingPage;
import ArifAcdemy.PageObjects.OrderPage;
import ArifAcdemy.PageObjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;


public class SubmitOrderTest extends BaseTests{
	String productName="ZARA COAT 3";
	@Test(dataProvider="getData", groups={"purchase"})
	public void SubmitOrder(HashMap<String,String> input) throws InterruptedException, IOException
	 {
		String countryName="India";
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"),input.get("password"));
		//ProductCatalogue productCatalogue= new ProductCatalogue(driver);
		List<WebElement> products= productCatalogue.getProductList();
		productCatalogue.AddProductToCart(input.get("product"));
		BasketPage basketPage= productCatalogue.goToCartPage();
		//BasketPage basketPage= new BasketPage(driver);
		boolean match=basketPage.VerifyProduct(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=basketPage.goToCheckout();
		checkoutPage.SelectCountry(countryName);
		ConfirmationPage confirmationPage=checkoutPage.placeOrder();
		String confirmationmessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmationmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println("Successfully completed the order");
		
	 }	
	
	@Test(dependsOnMethods= {"SubmitOrder"})
	public void orderHstory() {
		ProductCatalogue productCatalogue=landingPage.loginApplication("Kumbh@g.com", "Abhishek@1");
		OrderPage orderPage= productCatalogue.goToOrdertPage();
		Assert.assertTrue(orderPage.verifyOrderDisply(productName));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		/*
		 * HashMap<String,String> map= new HashMap<String,String>(); map.put("email",
		 * "Kumbh@g.com"); map.put("password", "Abhishek@1");
		 * map.put("product","ZARA COAT 3"); HashMap<String,String> map1= new
		 * HashMap<String,String>(); map1.put("email", "Kumbha@g.com");
		 * map1.put("password", "Abhishek@1"); map1.put("product","ADIDAS ORIGINAL");
		 */
		List<HashMap<String,String>> data= getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\ArifAcademy\\data\\purchaseOrder.json");

		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}