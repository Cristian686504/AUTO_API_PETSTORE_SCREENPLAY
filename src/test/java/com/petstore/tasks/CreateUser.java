package com.petstore.tasks;

import com.petstore.config.PetstoreRequestSpec;
import com.petstore.models.User;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreateUser implements Task {

    private final User user;

    public CreateUser(User user) {
        this.user = user;
    }

    public static CreateUser withData(User user) {
        return instrumented(CreateUser.class, user);
    }

    @Override
    @Step("{0} creates a new user")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from("/user/" + user.getUsername())
                        .with(request -> request
                                .spec(PetstoreRequestSpec.build())),
                Post.to("/user")
                        .with(request -> request
                                .spec(PetstoreRequestSpec.build())
                                .body(user))
        );
    }
}
