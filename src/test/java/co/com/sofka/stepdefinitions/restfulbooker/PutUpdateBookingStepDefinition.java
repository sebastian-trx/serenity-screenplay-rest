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
import static co.com.sofka.questions.restfulbooker.UpdateBookingQuestion.updateBookingQuestion;
import static co.com.sofka.task.restfulbooker.CreateBookingTask.createBookingTask;
import static co.com.sofka.task.restfulbooker.UpdateBookingTask.updateBookingTask;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class PutUpdateBookingStepDefinition extends Setup {

    private static final Logger LOGGER = Logger.getLogger(PutUpdateBookingStepDefinition.class);
    private Integer id ;

    @Dado("que el usuario posee un token por estar registrado")
    public void queElUsuarioPoseeUnTokenPorEstarRegistrado() {
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

    @Cuando("crea una reserva")
    public void creaUnaReserva() {
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

    @Y("el usuario actualiza los datos de la reserva")
    public void elUsuarioActualizaLosDatosDeLaReserva() {
        actor.attemptsTo(
                updateBookingTask().usingTheResource(RESOURCE+id)
                        .withHeaders(headers)
                        .andbodyRequest(bodyRequestUpdate())
        );
    }

    @Entonces("en la respuesta del sistema se observa los datos que fueron actualizados")
    public void enLaRespuestaDelSistemaSeObservaLosDatosQueFueronActualizados() {
        try {
            LastResponse.received().answeredBy(actor).prettyPrint();

            seeThat("status code", responseCode(), equalTo(200));

            var bookingUpdated = updateBookingQuestion().answeredBy(actor);
            actor.should(
                    seeThat("nombre actualizado del usuario asociado a la reserva",
                            actor1 -> bookingUpdated.getFirstname(),
                            equalTo(bodyRequestUpdate().getFirstname())),

                    seeThat("apellido actualizado del usuario asociado a la reserva",
                            actor1 -> bookingUpdated.getLastname(),
                            equalTo(bodyRequestUpdate().getLastname())),

                    seeThat("necesidad adicional del usuario actualizada",
                            actor1 -> bookingUpdated.getAdditionalneeds(),
                            equalTo(bodyRequestUpdate().getAdditionalneeds()))
            );
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            Assertions.fail(e.getMessage(),e);
        }
    }
}
