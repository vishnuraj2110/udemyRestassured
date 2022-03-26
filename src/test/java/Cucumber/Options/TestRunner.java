package Cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features ="src/test/java/Features/placeValidation.feature" ,
                 glue = {"StepDefination"},
                // tags= "@deletePlace",
                 monochrome = true,
                 plugin = {"pretty","summary","json:target/jsonReports/cucumber-report.json"},
                 publish = true
                
		)
public class TestRunner {

}
