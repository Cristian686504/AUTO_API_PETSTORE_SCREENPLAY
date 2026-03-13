package com.petstore.stepdefinitions;

import com.petstore.models.User;
import com.petstore.questions.ResponseStatusCode;
import com.petstore.questions.UserResponse;
import com.petstore.tasks.CreateUser;
import com.petstore.tasks.DeleteUser;
import com.petstore.tasks.GetUser;
import com.petstore.tasks.UpdateUser;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.model.util.EnvironmentVariables;

import java.util.UUID;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class UserCrudStepDefinitions {

    private Actor actor;

    @Given("la API de Petstore esta disponible")
    public void laAPIEstadDisponible() {
        EnvironmentVariables environmentVariables = Serenity.environmentVariables();
        String baseUrl = environmentVariables.getProperty("restapi.baseurl");
        actor = OnStage.theActorCalled("QA Tester");
        actor.can(CallAnApi.at(baseUrl));
    }

    @When("el usuario crea un nuevo usuario con username {string}, nombre {string}, apellido {string}, correo {string}, password {string}, telefono {string} y estado {int}")
    public void elUsuarioCreaUnNuevoUsuario(String username, String firstName, String lastName,
                                            String email, String password, String phone, int userStatus) {
        actor.attemptsTo(CreateUser.withData(
                buildUser(username, firstName, lastName, email, password, phone, userStatus)
        ));
    }

    @Then("el codigo de estado de la respuesta debe ser {int}")
    public void elCodigoDeEstadoDeLaRespuestaDebeSerXxx(int expectedStatusCode) {
        actor.should(seeThat(ResponseStatusCode.returned(), is(equalTo(expectedStatusCode))));
    }

    @When("el usuario consulta el usuario con nombre de usuario {string}")
    public void elUsuarioConsultaElUsuario(String username) {
        actor.attemptsTo(GetUser.withUsername(username));
    }

    @And("la respuesta debe contener el nombre de usuario {string}")
    public void laRespuestaDebeContenerElNombreDeUsuario(String username) {
        actor.should(seeThat(UserResponse.field("username"), is(equalTo(username))));
    }

    @When("el usuario actualiza el usuario {string} con nombre {string}, apellido {string}, correo {string}, password {string}, telefono {string} y estado {int}")
    public void elUsuarioActualizaElUsuario(String username, String firstName, String lastName,
                                            String newEmail, String password, String phone, int userStatus) {
        actor.attemptsTo(UpdateUser.withData(username,
                buildUser(username, firstName, lastName, newEmail, password, phone, userStatus)
        ));
    }

    @When("el usuario elimina el usuario con nombre de usuario {string}")
    public void elUsuarioEliminaElUsuario(String username) {
        actor.attemptsTo(DeleteUser.withUsername(username));
    }

    private User buildUser(String username, String firstName, String lastName,
                           String email, String password, String phone, int userStatus) {
        return User.builder()
                .id(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE)
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .phone(phone)
                .userStatus(userStatus)
                .build();
    }
}