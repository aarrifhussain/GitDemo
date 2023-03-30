package ArifAcdemy.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ArifAcademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//td[2]")
	List<WebElement> orderNames;
	
	
	public Boolean verifyOrderDisply(String productName) {
		Boolean match= orderNames.stream().anyMatch(orderName->orderName.getText().equalsIgnoreCase(productName));
		return match;
	}
	
}
