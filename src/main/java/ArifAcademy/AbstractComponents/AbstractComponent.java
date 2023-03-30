package ArifAcademy.AbstractComponents;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ArifAcdemy.PageObjects.BasketPage;
import ArifAcdemy.PageObjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}

	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartHeader;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement ordeHeader;
	
	public OrderPage  goToOrdertPage() {
		ordeHeader.click();
		OrderPage orderPage= new OrderPage(driver);
		return orderPage;
	}
	public BasketPage  goToCartPage() {
		cartHeader.click();
		BasketPage basketPage= new BasketPage(driver);
		return basketPage;
	}
	public void waitForElementToAppear(By FindBy) {
		WebDriverWait wait= new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}
	public void waitForWebElementToAppear(WebElement FindBy) {
		WebDriverWait wait= new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOf(FindBy));
	}
	public void waitForElementToDisAppear(By FindBy) throws InterruptedException {
	//	WebDriverWait wait= new WebDriverWait(driver,2);
	//	wait.until(ExpectedConditions.invisibilityOfElementLocated(FindBy));
		Thread.sleep(5000);
	}


}
