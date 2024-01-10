import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features= "src/test/resources",
        glue= {"stepdefs"},
        monochrome = true,
        plugin = {"pretty",
                "html:target/cucumber-reports/restAssured.html"})

public class RunnerTest {

}