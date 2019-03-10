package org.powellmakerspace.sign_on_server.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.powellmakerspace.sign_on_server.models.access_mechanism.AccessMechanism;
import org.powellmakerspace.sign_on_server.services.AccessMechanismService;
import org.springframework.web.bind.annotation.*;

@Api(
        value = "AccessMechanism API",
        description = "Provides functionality for updating and retrieving AccessMechanism objects."
)
@RestController
@RequestMapping("/api/accessmechanisms")
public class AccessMechanismController {

    private AccessMechanismService accessMechanismService;

    /**
     * Constructor of the Access Mechanism Controller
     * @param accessMechanismService access mechanism service layer
     */
    public AccessMechanismController(AccessMechanismService accessMechanismService) {
        this.accessMechanismService = accessMechanismService;
    }

    @ApiOperation(
            value = "createAccessMechanism",
            notes = "Add new member to the repository"
    )
    @PutMapping(path = "")
    public void createAccessMechanism(
            @ApiParam(
                    value = "accessMechanism object to be created",
                    required = true
            )
            @RequestBody AccessMechanism accessMechanism
    ) {
        accessMechanismService.createAccessMechanism(accessMechanism);
    }
}
