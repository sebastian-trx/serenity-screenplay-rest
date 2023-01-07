package co.com.sofka.stepdefinitions.jsonplaceholder;

import co.com.sofka.setup.Setup;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;

import static co.com.sofka.questions.jsonplaceholder.GetGeoFromUser.getGeoFromUser;
import static co.com.sofka.questions.jsonplaceholder.ResponseCode.responseCode;
import static co.com.sofka.task.jsonplaceholder.GetUsers.getUsers;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetUsersJsonPlaceHolderStepDefinition extends Setup {

    private static final Logger LOGGER = Logger.getLogger(GetUsersJsonPlaceHolderStepDefinition.class);

    @Dado("que el usuario entra en la pagina...")
    public void queElUsuarioEntraEnLaPagina() {
        try {
            setUpLog4j();
            actor = Actor.named("sebas");
            actor.whoCan(CallAnApi.at(URL_BASE_JSON_PLACE_HOLDER));
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            Assertions.fail(e.getMessage(),e);
        }
    }

    @Cuando("quiere consultar la informacion de un usuario")
    public void quiereConsultarLaInformacionDeUnUsuario() {
        try {
            actor.attemptsTo(
                            getUsers().usingTheResource(GET_USER_BY_ID)
                    );
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            Assertions.fail(e.getMessage(),e);
        }
    }

    @Entonces("el sistema responde con la informacionn del usuario requerido")
    public void elSistemaRespondeConLaInformacionnDelUsuarioRequerido() {
        try {
            actor.should(
                    seeThat("status code", responseCode(), equalTo(200))
            );

            var geo = getGeoFromUser().answeredBy(actor).getAddress().getGeo();
            actor.should(
                    seeThat("latitud del usuario",
                            act -> geo.getLat(),
                            equalTo("-37.3159")),

                    seeThat("longitud del usuario",
                            act -> geo.getLng(),
                            equalTo("81.1496"))
            );
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            Assertions.fail(e.getMessage(),e);
        }
    }
}
