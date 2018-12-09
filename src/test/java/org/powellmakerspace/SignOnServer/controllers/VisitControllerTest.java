package org.powellmakerspace.SignOnServer.controllers;

import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.runner.RunWith;
import org.powellmakerspace.SignOnServer.services.VisitService;

@RunWith(JMockit.class)
public class VisitControllerTest {

    private VisitController visitController;

    @Mocked
    private VisitService visitService;
}
