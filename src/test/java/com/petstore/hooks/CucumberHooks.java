package com.petstore.hooks;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class CucumberHooks {

    @Before
    public void setStage() {
        OnStage.setTheStage(new OnlineCast());
    }
}
