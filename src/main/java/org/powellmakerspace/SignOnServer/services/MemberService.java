package org.powellmakerspace.SignOnServer.services;

import org.powellmakerspace.SignOnServer.models.Member;
import org.powellmakerspace.SignOnServer.models.enums.MembershipType;
import org.powellmakerspace.SignOnServer.repositories.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

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
     * Gets member in repository with given memberId
     * @param memberId memberId to be searched
     * @return member object with searched memberId
     */
    public Member getMember(long memberId) {
        // This should look for an IsPresent Flag Thingy
        return memberRepository.findById(memberId).get();
    }



    /**
     * Gets list of member objects with given filters
     * @param memberName name or name fragment to search by
     * @param membershipType membershipType to filter by
     * @return Iterable list of member objects with given filters
     */
    public Iterable<Member> getMembers(String memberName, MembershipType membershipType) {
        if (memberName == null && membershipType == null){
            return memberRepository.findAll();
        }
        else if (memberName != null && membershipType == null) {
            return memberRepository.findMembersByMemberNameLike(memberName);
        }
        else if (memberName == null && membershipType != null) {
            return memberRepository.findMembersByMembershipType(membershipType);
        }
        else {
            return memberRepository.findMembersByMemberNameLikeAndMembershipType(memberName, membershipType);
        }
    }
}
