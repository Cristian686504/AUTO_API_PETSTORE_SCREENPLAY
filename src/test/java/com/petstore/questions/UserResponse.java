package com.petstore.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class UserResponse implements Question<String> {

    private final String field;

    private UserResponse(String field) {
        this.field = field;
    }

    public static UserResponse field(String field) {
        return new UserResponse(field);
    }

    @Override
    public String answeredBy(Actor actor) {
        return LastResponse.received().answeredBy(actor).jsonPath().getString(field);
    }
}
