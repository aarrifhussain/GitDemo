package ArifAcdemy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ArifAcademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	//List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	By ProductsBy=By.cssSelector(".mb-3");
	By AddToCart=By.cssSelector(".card-body button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");
	By spinner=By.cssSelector(".ng-animating");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(ProductsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod= products.stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		System.out.println("product found successfullt");
		return prod;
	}
	
	public void AddProductToCart(String productName) throws InterruptedException {
		WebElement prod= getProductByName(productName);
		prod.findElement(AddToCart).click();
		System.out.println("clicked on add product to cart");
		waitForElementToAppear(toastMessage);
		waitForElementToDisAppear(spinner);
		
	}
}

	


