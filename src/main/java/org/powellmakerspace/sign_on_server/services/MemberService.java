package org.powellmakerspace.sign_on_server.services;

import org.powellmakerspace.sign_on_server.exception.ResourceNotFoundException;
import org.powellmakerspace.sign_on_server.models.Member;
import org.powellmakerspace.sign_on_server.models.access_mechanism.AccessMechanism;
import org.powellmakerspace.sign_on_server.repositories.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private static Logger logger = LoggerFactory.getLogger(MemberService.class);

    private EntityManager entityManager;
    private MemberRepository memberRepository;

    /**
     * Constructor of the Member Service
     * @param memberRepository member repository layer
     */
    public MemberService(EntityManager entityManager, MemberRepository memberRepository){
        this.entityManager = entityManager;
        this.memberRepository = memberRepository;
    }



    /**
     * Adds new member into repository
     * @param member member object to add
     */
    public void createMember(Member member) {
        memberRepository.save(member);
    }



    /**
     * Updates member in repository
     * @param member member object to update
     */
    public void updateMember(Member member) {
        memberRepository.save(member);
    }



    /**
     * Gets member in repository with given memberId.
     * Throws ResourceNotFoundException if no Member by that memberId is present.
     * @param memberId memberId to be searched
     * @return member object with searched memberId
     */
    public Member getMember(long memberId) throws ResourceNotFoundException{
        Optional<Member> member = memberRepository.findById(memberId);
        if (member.isPresent()){
            return member.get();
        }
        else {
            ResourceNotFoundException resourceNotFoundException = new ResourceNotFoundException();
            logger.debug("No member found by {}", memberId, resourceNotFoundException);
            throw resourceNotFoundException;
        }
    }



    /**
     * Gets list of member objects with given filters
     * @param memberName name or name fragment to search by
     * @param membershipType membershipType to filter by
     * @return Iterable list of member objects with given filters
     */
    public Iterable<Member> getMembers(
            String memberName, // query parameter
            AccessMechanism membershipType, // query parameter 
            int pageOffset,
            int pageSize
    ) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Member> query = builder.createQuery(Member.class);

        query.select(query.from(Member.class));

        TypedQuery<Member> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(pageOffset);
        typedQuery.setMaxResults(pageSize);


        // TODO the @JsonIgnore is bing ignored here resulting in all the visits for each member being sent in the
        // request as well. The temporary fix is to just remove them, but we should figure out why @jsonignore is being
        // ignored
        // return typedQuery.getResultList()
        return typedQuery.getResultList()
                .stream()
                .map(
                    member -> {
                        member.setVisits(Collections.emptyList());
                        return member;
                    }
                ).collect(Collectors.toList());
    }
}
