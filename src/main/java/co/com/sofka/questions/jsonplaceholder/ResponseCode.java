package co.com.sofka.questions.jsonplaceholder;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ResponseCode implements Question<Integer> {

     @Override
    public Integer answeredBy(Actor actor) {
        return SerenityRest.lastResponse().statusCode();
    }

    public static ResponseCode responseCode(){
        return new ResponseCode();
    }
}
