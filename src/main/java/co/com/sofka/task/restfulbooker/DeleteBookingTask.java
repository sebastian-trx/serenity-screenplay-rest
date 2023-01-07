package co.com.sofka.task.restfulbooker;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import java.util.Map;

public class DeleteBookingTask implements Task {

    private String resource;
    private Map<String, Object> headers;

    public DeleteBookingTask usingTheResource(String resource) {
        this.resource = resource;
        return this;
    }

    public DeleteBookingTask withHeaders(Map<String, Object> headers) {
        this.headers = headers;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from(resource)
                        .with(
                                requestSpecification -> requestSpecification
                                        .headers(headers)
                                        .log()
                                        .all()
                        )
        );
    }

    public static DeleteBookingTask deleteBookingTask(){
        return new DeleteBookingTask();
    }
}
