import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


/*
 * The tag looks for the annotated feature inside resources folder.
 * Starting point for the api endpoint tests.
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, monochrome = true,

    tags = {"@game1"}
)
public class RunTests {
}
