//
//
//package Cucumber.Options;
//
//import org.junit.runner.RunWith;
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
//
//@RunWith(Cucumber.class)
//@CucumberOptions(
//    features = "/Users/shibahar/IdeaProjects/APIFramework/src/test/java/Features/Test.feature", // Location of your feature files
//    glue = "StepDefinition.java",  // Location of your step definitions package
//    plugin = {"pretty", "html:target/cucumber-reports"} // Optional: Report format and location
//)
//public class TestRunner {
//}


package Cucumber.Options;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(publish = true,features="src/test/java/features",plugin ="json:target/jsonReports/cucumber-report.json",glue= {"stepdefinition"})
public class TestRunner {

}