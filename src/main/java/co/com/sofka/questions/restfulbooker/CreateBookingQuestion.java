package co.com.sofka.questions.restfulbooker;

import co.com.sofka.models.restfulbooker.booking.BookingComplete;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class CreateBookingQuestion implements Question<BookingComplete> {
    @Override
    public BookingComplete answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(BookingComplete.class);
    }

    public static CreateBookingQuestion createBookingQuestion(){
        return new CreateBookingQuestion();
    }
}
