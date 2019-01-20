package org.powellmakerspace.sign_on_server.services;

import org.powellmakerspace.sign_on_server.exception.ResourceNotFoundException;
import org.powellmakerspace.sign_on_server.models.Member;
import org.powellmakerspace.sign_on_server.models.membership.second_attempt.AccessMechanism;
import org.powellmakerspace.sign_on_server.repositories.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    private static Logger logger = LoggerFactory.getLogger(MemberService.class);

    private MemberRepository memberRepository;

    /**
     * Constructor of the Member Service
     * @param memberRepository member repository layer
     */
    public MemberService(MemberRepository memberRepository){
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
    public Iterable<Member> getMembers(String memberName, AccessMechanism membershipType) {
        if (memberName == null && membershipType == null){
            return memberRepository.findAll();
        }
        return null;
//        else if (memberName != null && membershipType == null) {
//            return memberRepository.findMembersByMemberNameLike(memberName);
//        }
//        else if (memberName == null /* && membershipType != null */) { // Comment not logically needed for functionality
//            return memberRepository.findMembersByMembershipType(membershipType);
//        }
//        else {
//            return memberRepository.findMembersByMemberNameLikeAndMembershipType(memberName, membershipType);
//        }
    }
}
