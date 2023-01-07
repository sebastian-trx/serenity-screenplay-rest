package co.com.sofka.stepdefinitions.restfulbooker;

import co.com.sofka.setup.Setup;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;

import static co.com.sofka.questions.jsonplaceholder.ResponseCode.responseCode;
import static co.com.sofka.questions.restfulbooker.CreateBookingQuestion.createBookingQuestion;
import static co.com.sofka.task.restfulbooker.CreateBookingTask.createBookingTask;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class PostCreateBookingStepDefinition extends Setup {

    private static final Logger LOGGER = Logger.getLogger(PostCreateBookingStepDefinition.class);

    @Dado("que el usuario esta en la pagina")
    public void queElUsuarioEstaEnLaPagina() {
        try {
            setUpLog4j();
            actor = Actor.named("sebas");
            actor.whoCan(CallAnApi.at(URL_BASE_RESTFUL_BOOKER));
            headers.put("Content-Type", "application/json");
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            Assertions.fail(e.getMessage(),e);
        }
    }

    @Cuando("crea una reserva con los datos necesarios")
    public void creaUnaReservaConLosDatosNecesarios() {
        try {
            actor.attemptsTo(
                    createBookingTask().usingTheResource(RESOURCE)
                            .withHeaders(headers)
                            .andbodyRequest(bodyRequest())
            );
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            Assertions.fail(e.getMessage(),e);
        }
    }

    @Entonces("el sistema responde con la informacion ingresada")
    public void elSistemaRespondeConLaInformacionIngresada() {
        try {
            LastResponse.received().answeredBy(actor).prettyPrint();
            actor.should(
                    seeThat("status code", responseCode(), equalTo(200))
            );

            var booking = createBookingQuestion().answeredBy(actor).getBooking();
            actor.should(
                    seeThat("nombre del usuario asociado a la reserva",
                            actor1 -> booking.getFirstname(),
                            equalTo(bodyRequest().getFirstname())),

                    seeThat("apellido del usuario asociado a la reserva",
                            actor1 -> booking.getLastname(),
                            equalTo(bodyRequest().getLastname()))
            );
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            Assertions.fail(e.getMessage(),e);
        }
    }
}
