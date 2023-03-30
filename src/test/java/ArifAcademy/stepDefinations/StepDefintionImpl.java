package ArifAcademy.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import ArifAcademy.TestComponents.BaseTests;
import ArifAcdemy.PageObjects.BasketPage;
import ArifAcdemy.PageObjects.CheckoutPage;
import ArifAcdemy.PageObjects.ConfirmationPage;
import ArifAcdemy.PageObjects.LandingPage;
import ArifAcdemy.PageObjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefintionImpl extends BaseTests {
public LandingPage landingPage;
public ProductCatalogue productCatalogue;
public ConfirmationPage confirmationPage;
	@Given("I landed on Ecoomerce Page")
	public void I_landed_on_Ecoomerce_Page() throws IOException {
		landingPage= launchApplication();
	}	
	
	@Given("^Loggesd in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password){
		productCatalogue=landingPage.loginApplication(username, password);
		
	}
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products= productCatalogue.getProductList();
		productCatalogue.AddProductToCart(productName);
	}
	
	@When("^Checkout (.+) nd submit the order$")
	public void checkout_submit_order(String productName) {
		BasketPage basketPage= productCatalogue.goToCartPage();
		//BasketPage basketPage= new BasketPage(driver);
		boolean match=basketPage.VerifyProduct(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=basketPage.goToCheckout();
		checkoutPage.SelectCountry("India");
		confirmationPage=checkoutPage.placeOrder();
	
	}
	@Then("{string} message is displayed on confirmation")
	public void message_discplayed_on_confirmtion(String string) {
		String confirmationmessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmationmessage.equalsIgnoreCase(string));
		System.out.println("Successfully completed the order");
		driver.close();
	}

    @Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) throws Throwable {
    	Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
    	driver.close();
    }
}

