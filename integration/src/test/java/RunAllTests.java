import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, monochrome = true, tags = {"~@ExcludeFromIntegrationTest"})
public class RunAllTests {
}