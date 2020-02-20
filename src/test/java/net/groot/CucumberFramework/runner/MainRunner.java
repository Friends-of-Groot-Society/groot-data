package net.groot.CucumberFramework.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
 
@RunWith(Cucumber.class)

@CucumberOptions (
	features = {"src/test/java/net/groot/CucumberFramework/featureFiles"},
	glue = {"CucumberFramework.stepFiles"},
	monochrome = true,
	plugin = {"pretty", "html:target/cucumber", 
			"json:target/cucumber.json", 
			"com.cucumber.listener.ExtentCucumberFormatter:target/report.html"
	}
)

public class MainRunner {

}
