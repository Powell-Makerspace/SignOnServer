package org.powellmakerspace.sign_on_server.models.membership.second_attempt.membership;

import org.powellmakerspace.sign_on_server.models.Member;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class FamilyMember {

    @OneToOne(mappedBy = "accessMechanism")
    private Member member;
    @ManyToOne
    private Family familyMembership;


    public FamilyMember(){}


    public FamilyMember(Member member, Family familyMembership){
        this.member = member;
        this.familyMembership = familyMembership;
    }



    public Member getMember(){
        return member;
    }

    public Family getFamilyMembership() {
        return familyMembership;
    }

    public void setMember(Member member){
        this.member = member;
    }

    public void setFamilyMembership(Family familyMembership){
        this.familyMembership = familyMembership;
    }
}
