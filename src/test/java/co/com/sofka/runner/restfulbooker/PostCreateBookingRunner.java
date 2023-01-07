package co.com.sofka.runner.restfulbooker;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/restfulbooker/postCreateBooking.feature"},
        glue = {"co.com.sofka.stepdefinitions.restfulbooker"}
)
public class PostCreateBookingRunner {
}
