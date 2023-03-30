package ArifAcademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ArifAcdemy.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTests {

	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver intializeDriver() throws IOException {
		//properties class
		
		Properties prop= new Properties();
		FileInputStream fis= new FileInputStream("C:\\Users\\Arif\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\main\\java\\ArifAcademy\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		//String browserName=prop.getProperty("Browser");
		
		if(browserName.contains("chrome")) {
		ChromeOptions options= new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		if(browserName.contains("headless")) {
		options.addArguments("headless");
		}
		driver= new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1440,900));// run in full screen
	}
		else if(browserName.equalsIgnoreCase("Firefox")){
		//firfox browser
		}
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}
	
		public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
			//read json to string
			String jsonContent= FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
			//String to HashMap jackson binding
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			List<HashMap<String,String>> data =mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
			});
			return data;
		}
		
		public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
			TakesScreenshot ts=(TakesScreenshot)driver;
			File source=ts.getScreenshotAs(OutputType.FILE);
			File file= new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png") ;
			FileUtils.copyFile(source, file);
			return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
		}
		
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver = intializeDriver();
		landingPage= new LandingPage(driver);
		landingPage.goTO();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
		
	}
}
