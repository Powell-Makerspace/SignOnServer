package org.powellmakerspace.sign_on_server.models.membership.second_attempt.membership;

import org.powellmakerspace.sign_on_server.models.Member;
import org.powellmakerspace.sign_on_server.models.membership.second_attempt.AccessMechanism;

import javax.persistence.*;

@Entity
public class FamilyMember extends AccessMechanism {

//    @ManyToOne
//    private Family familyMembership;


    public FamilyMember(){}


    public FamilyMember(Member member, Family familyMembership){
        setMember(member);
//        this.familyMembership = familyMembership;
    }

//    public Family getFamilyMembership() {
//        return familyMembership;
//    }
//
//    public void setFamilyMembership(Family familyMembership){
//        this.familyMembership = familyMembership;
//    }

    @Override
    public boolean canVisit() {
        return true;
//        return familyMembership.canVisit();
    }
}
