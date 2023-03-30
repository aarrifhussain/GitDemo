package ArifAcdemy.tests;

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

import ArifAcdemy.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;


public class StandAloneTest {
	public static void main(String[] args) throws InterruptedException
	 {
		String productName="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		//System.setProperty("webdriver.chrome.driver", "D:\\MY selenium\\jars\\chromedriver_win32\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingPage= new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("Kumbh@g.com");
		driver.findElement(By.id("userPassword")).sendKeys("Abhishek@1");
		driver.findElement(By.id("login")).click();
		List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
		products.stream().filter(product-> product.getText().equals("ZARA COAT 3"));
		WebElement prod= products.stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		System.out.println("product found successfullt");
		Thread.sleep(2000);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		System.out.println("clicked on add product to cart");
		
		WebDriverWait wait= new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		//ng-animating
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ng-animating")));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		Thread.sleep(1000);
		List<WebElement> cartProducts= driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		//cartProducts.stream().filter(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Boolean match= cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		//driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
		//List<WebElement> countryOptions= driver.findElements(By.xpath("//span[@class='ng-star-inserted']"));
		//WebElement countryNme=countryOptions.stream().filter(countryOption-> countryOption.getText().equalsIgnoreCase("India"));
		Actions a= new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted'][2]")));
		driver.findElement(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted'][2]")).click();
		System.out.println("India selected");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
		driver.findElement(By.xpath("//*[@class='actions']/a")).click();
		String confirmmessge=driver.findElement(By.xpath("//*[@class='hero-primary']")).getText();
		Assert.assertTrue(confirmmessge.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
		
		
	 }	
}