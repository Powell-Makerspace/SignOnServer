package org.powellmakerspace.sign_on_server.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.powellmakerspace.sign_on_server.exception.ResourceNotFoundException;
import org.powellmakerspace.sign_on_server.models.Visit;
import org.powellmakerspace.sign_on_server.services.VisitService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
    @PutMapping(path = "")
    public void createVisit(
            @ApiParam(
                    value = "visit object to be created",
                    required = true
            )
            @RequestBody Visit visit
    ) {
        visitService.createVisit(visit);
    }



    @ApiOperation(
            value = "updateVisit",
            notes = "Update a given visit in the repository"
    )
    @PostMapping(path = "/{id}")
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
    @GetMapping(path = "/{id}")
    public Visit getVisit(
            @ApiParam(
                    value = "long visitId",
                    required = true
            )
            @PathVariable("id") long visitId
    ) throws ResourceNotFoundException {
        return visitService.getVisit(visitId);
    }

    @ApiOperation(
            value = "searchVisits",
            notes = "Searches repository with the included parameters",
            response = Visit.class,
            responseContainer = "List"
    )
    @GetMapping(path = "/search")
    public Iterable<Visit> searchVisits(
            @RequestParam(value = "active", required = false, defaultValue = "false")
                    boolean active,
            @ApiParam(
                    value = "long starting date filter"
            )
            @RequestParam(value = "startDate", required = false)
                    String startDate,
            @ApiParam(
                    value = "long ending date filter"
            )
            @RequestParam(value = "endDate", required = false)
                    String endDate,
            @ApiParam(
                    value = "long duration of visit filter"
            )
            @RequestParam(value = "duration", required = false, defaultValue = "-1")
                    long duration)
    {
        return visitService.searchVisits(active,
                StringUtils.isNoneBlank(startDate) ?
                LocalDateTime.parse((startDate)) : null,
                StringUtils.isNoneBlank(endDate) ?
                LocalDateTime.parse(endDate) : null,
                duration
        );
    }
}
