package co.com.sofka.questions.jsonplaceholder;

import co.com.sofka.models.jsonplaceholder.user.User;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class GetGeoFromUser implements Question<User> {

    @Override
    public User answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(User.class);
    }

    public static GetGeoFromUser getGeoFromUser(){
        return new GetGeoFromUser();
    }
}
