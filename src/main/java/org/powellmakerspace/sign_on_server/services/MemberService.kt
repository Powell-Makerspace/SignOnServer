package org.powellmakerspace.sign_on_server.services;

import org.powellmakerspace.sign_on_server.exception.ResourceNotFoundException;
import org.powellmakerspace.sign_on_server.models.Member;
import org.powellmakerspace.sign_on_server.models.Visit
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
class MemberService(private val entityManager: EntityManager, private val memberRepository: MemberRepository) {

//    private val logger = LoggerFactory.getLogger(MemberService::class)!


    /**
     * Adds new member into repository
     * @param member member object to add
     */
    fun createMember(member: Member) {
        memberRepository.save(member)
    }



    /**
     * Updates member in repository
     * @param member member object to update
     */
    fun updateMember(member: Member) {
        memberRepository.save(member)
    }



    /**
     * Gets member in repository with given memberId.
     * Throws ResourceNotFoundException if no Member by that memberId is present.
     * @param memberId memberId to be searched
     * @return member object with searched memberId
     */
    fun getMember(memberId: Long): Member {
        val member = memberRepository.findById(memberId);
        if (member.isPresent()) {
            return member.get();
        }
        else {
            val resourceNotFoundException = ResourceNotFoundException()
            // logger.debug("No member found by {}", memberId, resourceNotFoundException)
            throw resourceNotFoundException
        }
    }



    /**
     * Gets list of member objects with given filters
     * @param memberName name or name fragment to search by
     * @param membershipType membershipType to filter by
     * @return Iterable list of member objects with given filters
     */
    fun getMembers(
            memberName: String?, // query parameter
            membershipType: AccessMechanism?, // query parameter 
            pageOffset: Int,
            pageSize: Int
    ): Iterable<Member> {
        val builder = entityManager.criteriaBuilder
        val query = builder.createQuery(Member::class.java)

        query.select(query.from(Member::class.java))

        val typedQuery = entityManager.createQuery(query)
        typedQuery.firstResult = pageOffset
        typedQuery.maxResults = pageSize


        // TODO the @JsonIgnore is bing ignored here resulting in all the visits for each member being sent in the
        // request as well. The temporary fix is to just remove them, but we should figure out why @jsonignore is being
        // ignored
        // return typedQuery.getResultList()
        val results = typedQuery.getResultList()
        results.forEach { it.setVisits(listOf<Visit>()) }
        return results
    }
}
