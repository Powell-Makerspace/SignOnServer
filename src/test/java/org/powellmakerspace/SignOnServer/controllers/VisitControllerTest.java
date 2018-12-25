package org.powellmakerspace.SignOnServer.controllers;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powellmakerspace.SignOnServer.models.Visit;
import org.powellmakerspace.SignOnServer.services.VisitService;

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
