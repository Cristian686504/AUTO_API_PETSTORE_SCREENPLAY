package com.petstore.tasks;

import com.petstore.config.PetstoreRequestSpec;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import net.serenitybdd.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteUser implements Task {

    private final String username;

    public DeleteUser(String username) {
        this.username = username;
    }

    public static DeleteUser withUsername(String username) {
        return instrumented(DeleteUser.class, username);
    }

    @Override
    @Step("{0} deletes user with username #username")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from("/user/" + username)
                        .with(request -> request
                                .spec(PetstoreRequestSpec.build()))
        );
    }
}
