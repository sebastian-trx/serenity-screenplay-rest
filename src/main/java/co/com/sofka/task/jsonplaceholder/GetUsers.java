package co.com.sofka.task.jsonplaceholder;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import java.util.Map;

public class GetUsers implements Task {

    private String resource;
    private Map<String, Object> headers;
    private String bodyRequest;

    public GetUsers usingTheResource(String resource) {
        this.resource = resource;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(resource)
                        .with(
                                requestSpecification -> requestSpecification
                                        .log()
                                        .all()
                        )
        );
    }

    public static GetUsers getUsers(){
        return new GetUsers();
    }
}
