package com.petstore.tasks;

import com.petstore.config.PetstoreRequestSpec;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetUser implements Task {

    private final String username;

    public GetUser(String username) {
        this.username = username;
    }

    public static GetUser withUsername(String username) {
        return instrumented(GetUser.class, username);
    }

    @Override
    @Step("{0} retrieves user with username #username")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/user/" + username)
                        .with(request -> request
                                .spec(PetstoreRequestSpec.build()))
        );
    }
}
