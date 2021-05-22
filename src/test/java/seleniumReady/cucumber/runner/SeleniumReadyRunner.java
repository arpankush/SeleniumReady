package seleniumReady.cucumber.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty","html:target/site/cucumber-pretty","json:target/cucumber.json"},
		glue = {"seleniumReady.cucumber.steps","managers"},
		features = {"classpath:features"},
		tags = "@UploadResume",
		dryRun = false, //for getting snippets set it to true
		snippets = SnippetType.UNDERSCORE,
		monochrome = true //for readable output on console
		)


public class SeleniumReadyRunner {

}
