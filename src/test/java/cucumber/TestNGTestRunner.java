package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
//cucumber ->testNG,junit
@CucumberOptions(features="src/test/java/cucumber",glue="ArifAcademy.stepDefinations"
,monochrome=true,tags="@Regression",plugin= {"html:target/cucumber.html"})

public class TestNGTestRunner extends AbstractTestNGCucumberTests {

	
}
