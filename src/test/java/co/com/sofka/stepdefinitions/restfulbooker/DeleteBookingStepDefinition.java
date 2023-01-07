package co.com.sofka.stepdefinitions.restfulbooker;

import co.com.sofka.models.restfulbooker.booking.BookingComplete;
import co.com.sofka.setup.Setup;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;

import static co.com.sofka.questions.jsonplaceholder.ResponseCode.responseCode;
import static co.com.sofka.task.restfulbooker.CreateBookingTask.createBookingTask;
import static co.com.sofka.task.restfulbooker.DeleteBookingTask.deleteBookingTask;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class DeleteBookingStepDefinition extends Setup {

    private static final Logger LOGGER = Logger.getLogger(DeleteBookingStepDefinition.class);

    private Integer id ;

    @Dado("que el usuario esta registrado")
    public void queElUsuarioEstaRegistrado() {
        try {
            setUpLog4j();
            actor = Actor.named("sebas");
            actor.whoCan(CallAnApi.at(URL_BASE_RESTFUL_BOOKER));
            headers.put("Content-Type", "application/json");
            headers.put("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=");
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            Assertions.fail(e.getMessage(),e);
        }
    }

    @Cuando("el usuario crea una reserva")
    public void elUsuarioCreaUnaReserva() {
        try {
            actor.attemptsTo(
                    createBookingTask().usingTheResource(RESOURCE)
                            .withHeaders(headers)
                            .andbodyRequest(bodyRequest())
            );
            id = SerenityRest.lastResponse()
                    .as(BookingComplete.class).getBookingid();
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            Assertions.fail(e.getMessage(),e);
        }
    }
    @Y("el usuario elimina la reserva")
    public void elUsuarioEliminaLaReserva() {
        try {
            actor.attemptsTo(
                    deleteBookingTask().usingTheResource(RESOURCE+id)
                            .withHeaders(headers)
            );
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            Assertions.fail(e.getMessage(),e);
        }
    }

    @Entonces("el sistema responde con un status code {int}")
    public void elSistemaRespondeConUnStatusCode(int statusCode) {
        try {
            LastResponse.received().answeredBy(actor).prettyPrint();
            actor.should(
                seeThat("status code", responseCode(), equalTo(statusCode))
            );
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            Assertions.fail(e.getMessage(),e);
        }
    }
}
