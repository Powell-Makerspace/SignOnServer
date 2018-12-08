package org.powellmakerspace.SignOnServer.services;

import org.powellmakerspace.SignOnServer.exception.ResourceNotFoundException;
import org.powellmakerspace.SignOnServer.models.Visit;
import org.powellmakerspace.SignOnServer.repositories.VisitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;

@Service
public class VisitService {

    private static Logger logger = LoggerFactory.getLogger(VisitService.class);


    private VisitRepository visitRepository;
    private MemberService memberService;

    /**
     * Contructor of the Visit Service
     * @param visitRepository visit repository layer
     */
    public VisitService(VisitRepository visitRepository, MemberService memberService){
        this.visitRepository = visitRepository;
        this.memberService = memberService;
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
    public Visit getVisit(long visitId) throws ResourceNotFoundException{
        Optional<Visit> visit = visitRepository.findById(visitId);
        if (visit.isPresent()){
            return visit.get();
        }
        else{
            ResourceNotFoundException resourceNotFoundException = new ResourceNotFoundException();
            logger.debug("No visit found by {}", visitId, resourceNotFoundException);
            throw resourceNotFoundException;
        }
    }

    /**
     * Gets all visits in the repository
     * @return Iterable list of all visit objects in the repository
     */
    public Iterable<Visit> getVisits(boolean active, long startDate, long endDate, long duration){
        // No parameters entered - find all
        if ( !active && startDate == 0 && endDate == 0 && duration == 0) {
            return visitRepository.findAll();
        }
        // active is true - find all with null departure time
        else if (active && startDate == 0 && endDate == 0 && duration == 0){
            return visitRepository.findAllVisitsByDepartureTimeIsNull();
        }
        // startDate and endDate are non-zero - find all with a startDate between
        else if (!active && startDate != 0 && endDate != 0 && duration == 0){
            return visitRepository.findAllVisitsByArrivalTimeBetween(startDate, endDate);
        }
        // If combination is incorrect, return empty iterable.
        else{
            return new Iterable<Visit>() {
                @Override
                public Iterator<Visit> iterator() {
                    return null;
                }
            };
        }
    }

}
