package ArifAcdemy.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import ArifAcademy.TestComponents.BaseTests;
import ArifAcademy.TestComponents.Retry;
import ArifAcdemy.PageObjects.BasketPage;
import ArifAcdemy.PageObjects.CheckoutPage;
import ArifAcdemy.PageObjects.ConfirmationPage;
import ArifAcdemy.PageObjects.LandingPage;
import ArifAcdemy.PageObjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;


public class ErrorValidation extends BaseTests{
	@Test(groups={"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginEroorVlidation() throws InterruptedException, IOException
	 {
		String productName="ZARA COAT 3";
		landingPage.loginApplication("Kumbh@g.com", "Abhgghshek@1");
		Assert.assertEquals("Incorrect email  password.",landingPage.getErrorMessage());	
	 }	
	@Test
	public void productErrorValidtion() throws InterruptedException, IOException
	 {
		String productName="ZARA COAT 3";
		String countryName="India";
		ProductCatalogue productCatalogue=landingPage.loginApplication("Kumbha@g.com", "Abhishek@1");
		//ProductCatalogue productCatalogue= new ProductCatalogue(driver);
		List<WebElement> products= productCatalogue.getProductList();
		productCatalogue.AddProductToCart(productName);
		BasketPage basketPage= productCatalogue.goToCartPage();
		//BasketPage basketPage= new BasketPage(driver);
		boolean match=basketPage.VerifyProduct("ZARA COAT 333");
		Assert.assertFalse(match);
	
	 }	
	
}