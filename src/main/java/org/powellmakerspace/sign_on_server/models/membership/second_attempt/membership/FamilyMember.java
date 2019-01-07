package org.powellmakerspace.sign_on_server.models.membership.second_attempt.membership;

import org.powellmakerspace.sign_on_server.models.Member;
import org.powellmakerspace.sign_on_server.models.membership.second_attempt.AccessMechanism;

import javax.persistence.*;

@Entity
public class FamilyMember implements AccessMechanism {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long familyMemberId;
    @OneToOne(mappedBy = "accessMechanism")
    private Member member;
    @ManyToOne
    private Family familyMembership;


    public FamilyMember(){}


    public FamilyMember(Member member, Family familyMembership){
        this.member = member;
        this.familyMembership = familyMembership;
    }


    public long getFamilyMemberId(){
        return familyMemberId;
    }

    public Member getMember(){
        return member;
    }

    public Family getFamilyMembership() {
        return familyMembership;
    }

    public void setFamilyMemberId(long familyMemberId){
        this.familyMemberId = familyMemberId;
    }

    public void setMember(Member member){
        this.member = member;
    }

    public void setFamilyMembership(Family familyMembership){
        this.familyMembership = familyMembership;
    }

    @Override
    public boolean canVisit() {
        return familyMembership.canVisit();
    }
}
