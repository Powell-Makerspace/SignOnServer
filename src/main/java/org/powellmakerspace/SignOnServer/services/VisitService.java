package org.powellmakerspace.SignOnServer.services;

import org.powellmakerspace.SignOnServer.models.Visit;
import org.powellmakerspace.SignOnServer.repositories.VisitRepository;
import org.springframework.stereotype.Service;

@Service
public class VisitService {

    private VisitRepository visitRepository;

    /**
     * Contructor of the Visit Service
     * @param visitRepository visit repository layer
     */
    public VisitService(VisitRepository visitRepository){
        this.visitRepository = visitRepository;
    }



    /**
     * Adds new visit into the repository
     * @param visit visit object to add
     */
    public void createVisit(Visit visit){
        visitRepository.save(visit);

    }



    /**
     * Updates member in repository
     * @param visit visit object to update
     */
    public void updateVisit(Visit visit){
        visitRepository.save(visit);
    }


    /**
     * Gets visit in repository with given visitId
     * @param visitId visitId to be searched
     * @return visit object with searched visitId
     */
    public Visit getVisit(long visitId){
        // This should look for an IsPresent Flag Thingy
        return visitRepository.findById(visitId).get();
    }


    /**
     * Gets visits currently active
     * @return visits in repository with null departure time
     */
    public Iterable<Visit> getActiveVisits(){
        return visitRepository.findVisitsByDepartureTimeIsNull();
    }

    /**
     * Gets all visits in the repository
     * @return Iterable list of all visit objects in the repository
     */
    public Iterable<Visit> getVisits(){
        return visitRepository.findAll();
    }

}
