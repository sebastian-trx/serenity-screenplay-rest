package co.com.sofka.runner.jsonplaceholder;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/jsonplaceholder/getUsersJsonPlaceHolder.feature"},
        glue = {"co.com.sofka.stepdefinitions.jsonplaceholder"}
)
public class GetUsersJsonPlaceHolderRunner {
}
