package testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "stepDefinitions",
        plugin = {"pretty",
                "html:target/cucumberReports/api/report.html",
                "json:target/cucumberReports/api/report.json"
        })
public class APITestRunner {
}