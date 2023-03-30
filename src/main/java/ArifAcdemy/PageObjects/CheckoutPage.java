package ArifAcdemy.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import ArifAcademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);

}
	By countryOption=By.xpath("//button[@class='ta-item list-group-item ng-star-inserted'][2]");
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement Country;
	
	@FindBy(xpath="//*[@class='actions']/a")
	WebElement placeOrderButton;
	
	@FindBy(xpath="//button[@class='ta-item list-group-item ng-star-inserted'][2]")
	WebElement selectCountry;
	
	public void SelectCountry(String countryName) {
		Actions a= new Actions(driver);
		a.sendKeys(Country, countryName).build().perform();
		waitForElementToAppear(countryOption);
		selectCountry.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
	}
	
	public ConfirmationPage placeOrder() {
		placeOrderButton.click();
		return new ConfirmationPage(driver);
	}
}
