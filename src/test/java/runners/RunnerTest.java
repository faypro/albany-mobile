package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

    @RunWith(Cucumber.class)
    @CucumberOptions(
            plugin = {"pretty", "summary", "json:target/cucumber.json"},
            features = "src/test/resources/features",
            glue = {"step_definitions"},
            snippets =CAMELCASE
    )
    public class RunnerTest {

}

