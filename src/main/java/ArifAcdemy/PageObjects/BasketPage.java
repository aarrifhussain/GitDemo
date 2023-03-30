package ArifAcdemy.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ArifAcademy.AbstractComponents.AbstractComponent;

public class BasketPage extends AbstractComponent {
	WebDriver driver;
	public BasketPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkoutButton;
	
	public Boolean VerifyProduct(String productName) {
		Boolean match= cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		checkoutButton.click();
		return new CheckoutPage(driver);
	}
}
