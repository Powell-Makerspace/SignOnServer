package org.powellmakerspace.SignOnServer.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.powellmakerspace.SignOnServer.models.Visit;
import org.powellmakerspace.SignOnServer.services.VisitService;
import org.springframework.web.bind.annotation.*;

@Api(
        value = "Visit API",
        description = "Provides functionality for updating and retrieving visit objects."
)
@RestController
@RequestMapping("/visits")
public class VisitController {

    private VisitService visitService;

    /**
     * Constructor of the Visit Controller
     * @param visitService visit service layer
     */
    public VisitController(VisitService visitService){
        this.visitService = visitService;
    }



    @ApiOperation(
            value = "createVisit",
            notes = "Add new visit to the repository"
    )
    @RequestMapping(path = "", method = RequestMethod.PUT)
    public void createVisit(
            @ApiParam(
                    value = "visit object to be created",
                    required = true
            )
            @RequestBody Visit visit
    ){
        visitService.createVisit(visit);
    }



    @ApiOperation(
            value = "updateVisit",
            notes = "Update a given visit in the repository"
    )
    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    public void updateVisit(
            @ApiParam(
                    value = "visitId of the visit to be updated",
                    required = true
            )
            @PathVariable("id")
            long visitId,
            @ApiParam(
                    value = "visit object to be updated",
                    required = true
            )
            @RequestBody
            Visit visit
    ){
        visit.setVisitId(visitId); //Make sure the visitId is a part of the visit object
        visitService.updateVisit(visit);
    }



    @ApiOperation(
            value = "getVisit",
            notes = "Retrieves a visit matching the provided memberId"
    )
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Visit getVisit(
            @ApiParam(
                    value = "long visitId",
                    required = true
            )
            @PathVariable("id") long visitId
    ){
        return visitService.getVisit(visitId);
    }

    @ApiOperation(
            value = "getVisits",
            notes = "Retrieves all visits in the repository"
    )
    @RequestMapping(path = "", method = RequestMethod.GET)
    public Iterable<Visit> getVisits(){
        return visitService.getVisits();
    }
}
