package org.powellmakerspace.SignOnServer.services;

import org.powellmakerspace.SignOnServer.exception.ResourceNotFoundException;
import org.powellmakerspace.SignOnServer.models.Visit;
import org.powellmakerspace.SignOnServer.repositories.VisitRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class VisitService {

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
    public void createVisit(Visit visit, long memberId) throws ResourceNotFoundException {
        visit.setMember(memberService.getMember(memberId));
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
     * Gets all visits in the repository
     * @return Iterable list of all visit objects in the repository
     */
    public Iterable<Visit> getVisits(long memberId, boolean active, long startDate, long endDate, long duration){
        // No parameters entered - find all
        if (memberId == 0 && !active && startDate == 0 && endDate == 0 && duration == 0) {
            return visitRepository.findAll();
        }
        // memberId entered - find all with that member id
        else if (memberId != 0 && !active && startDate == 0 && endDate == 0 && duration == 0){
            return visitRepository.findAllVisitsByMemberId(memberId);
        }
        // active is true - find all with null departure time
        else if (memberId == 0 && active && startDate == 0 && endDate == 0 && duration == 0){
            return visitRepository.findAllVisitsByDepartureTimeIsNull();
        }
        // startDate and endDate are non-zero - find all with a startDate between
        else if (memberId == 0 && !active && startDate != 0 && endDate != 0 && duration == 0){
            return visitRepository.findAllVisitsByArrivalTimeBetween(startDate, endDate);
        }
//        else if (memberId == 0 && !active && startDate != 0 && endDate != 0 && duration != 0){
//            Fill in here with code for filtered by date and duration
//        }
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
