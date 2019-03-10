package org.powellmakerspace.sign_on_server.services;

import org.powellmakerspace.sign_on_server.exception.ResourceNotFoundException;
import org.powellmakerspace.sign_on_server.models.access_mechanism.AccessMechanism;
import org.powellmakerspace.sign_on_server.models.access_mechanism.membership.IndividualMembership;
import org.powellmakerspace.sign_on_server.repositories.AccessMechanismRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccessMechanismService {

    private static Logger logger = LoggerFactory.getLogger(AccessMechanismService.class);


    private AccessMechanismRepository accessMechanismRepository;

    public AccessMechanismService(AccessMechanismRepository accessMechanismRepository){
        this.accessMechanismRepository = accessMechanismRepository;
    }

    public void createAccessMechanism(AccessMechanism accessMechanism){
        accessMechanismRepository.save(accessMechanism);
    }

    public void updateAccessMechanism(AccessMechanism accessMechanism){
        accessMechanismRepository.save(accessMechanism);
    }

    public AccessMechanism getAccessMechanism(long accessMechanismId) throws ResourceNotFoundException{
        Optional<AccessMechanism> accessMechanism = accessMechanismRepository.findById(accessMechanismId);
        if (accessMechanism.isPresent()){
            return accessMechanism.get();
        }
        else {
            ResourceNotFoundException resourceNotFoundException = new ResourceNotFoundException();
            logger.debug("No member found by {}", accessMechanismId, resourceNotFoundException);
            throw resourceNotFoundException;
        }
    }

    public Iterable<IndividualMembership> getAllIndividualMemberships(){
        return accessMechanismRepository.findAllIndividualMemberships();
    }


}
