package co.com.sofka.task.restfulbooker;

import co.com.sofka.models.restfulbooker.booking.Booking;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.Map;

public class CreateBookingTask implements Task {
    private String resource;
    private Map<String, Object> headers;
    private Booking bodyRequest;

    public CreateBookingTask usingTheResource(String resource) {
        this.resource = resource;
        return this;
    }

    public CreateBookingTask withHeaders(Map<String, Object> headers) {
        this.headers = headers;
        return this;
    }

    public CreateBookingTask andbodyRequest(Booking bodyRequest) {
        this.bodyRequest = bodyRequest;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(resource)
                        .with(
                                requestSpecification -> requestSpecification
                                        .headers(headers)
                                        .log()
                                        .all()
                                        .body(bodyRequest)
                        )
        );
    }

    public static CreateBookingTask createBookingTask(){
        return new CreateBookingTask();
    }
}
