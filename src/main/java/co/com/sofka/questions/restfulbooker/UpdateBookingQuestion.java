package co.com.sofka.questions.restfulbooker;

import co.com.sofka.models.restfulbooker.booking.Booking;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class UpdateBookingQuestion implements Question<Booking> {
    @Override
    public Booking answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(Booking.class);
    }

    public static UpdateBookingQuestion updateBookingQuestion(){
        return new UpdateBookingQuestion();
    }
}
