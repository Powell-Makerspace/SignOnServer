package org.powellmakerspace.sign_on_server.controllers;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powellmakerspace.sign_on_server.models.Visit;
import org.powellmakerspace.sign_on_server.services.VisitService;

@RunWith(JMockit.class)
public class VisitControllerTest {

    @Tested
    private VisitController visitController;

    @Injectable
    private VisitService visitService;

    @Test
    public void createVisit_success(){
        Visit testVisit = new Visit();

        new Expectations(){{
            visitService.createVisit(testVisit);
        }};

        visitController.createVisit(testVisit);
    }

    @Test
    public void updateVisit_success(){
        Visit testVisit = new Visit();

        new Expectations(){{
            visitService.updateVisit(testVisit);
        }};

        visitController.updateVisit(1L, testVisit);
    }

    

}
