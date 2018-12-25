package org.powellmakerspace.SignOnServer.services;

import org.powellmakerspace.SignOnServer.exception.ResourceNotFoundException;
import org.powellmakerspace.SignOnServer.models.Visit;
import org.powellmakerspace.SignOnServer.repositories.VisitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VisitService {

    private static Logger logger = LoggerFactory.getLogger(VisitService.class);


    private VisitRepository visitRepository;
    private MemberService memberService;
    private EntityManager entityManager;

    /**
     * Contructor of the Visit Service
     * @param visitRepository visit repository layer
     */
    public VisitService(VisitRepository visitRepository, MemberService memberService, EntityManager entityManager){
        this.visitRepository = visitRepository;
        this.memberService = memberService;
        this.entityManager = entityManager;
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

    public Iterable<Visit> searchVisits(boolean active, LocalDateTime startDate, LocalDateTime endDate, long duration){

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Visit> criteriaQuery = criteriaBuilder.createQuery(Visit.class);
        Root<Visit> root = criteriaQuery.from(Visit.class);

        // Base Query
        criteriaQuery = criteriaQuery.select(root);

        // Add clauses based on parameters
        if (active){
            criteriaQuery = criteriaQuery.where(criteriaBuilder.isTrue(root.get("active")));
        }

        if (startDate != null && endDate == null){
            criteriaQuery = criteriaQuery.where(criteriaBuilder.greaterThan(root.get("arrivalTime"), startDate));
        }
        else if (endDate != null && startDate == null){
            criteriaQuery = criteriaQuery.where(criteriaBuilder.lessThan(root.get("arrivalTime"), endDate));
        }
        else if (startDate != null && endDate != null){
            criteriaQuery = criteriaQuery.where(criteriaBuilder.between(root.get("arrivalTime"), startDate, endDate));
        }

        return entityManager.createQuery(criteriaQuery).getResultList();

    }

}
