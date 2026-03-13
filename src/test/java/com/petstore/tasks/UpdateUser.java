package com.petstore.tasks;

import com.petstore.config.PetstoreRequestSpec;
import com.petstore.models.User;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;
import net.serenitybdd.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class UpdateUser implements Task {

    private final String username;
    private final User updatedUser;

    public UpdateUser(String username, User updatedUser) {
        this.username = username;
        this.updatedUser = updatedUser;
    }

    public static UpdateUser withData(String username, User updatedUser) {
        return instrumented(UpdateUser.class, username, updatedUser);
    }

    @Override
    @Step("{0} updates user with username #username")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to("/user/" + username)
                        .with(request -> request
                                .spec(PetstoreRequestSpec.build())
                                .body(updatedUser))
        );
    }
}
