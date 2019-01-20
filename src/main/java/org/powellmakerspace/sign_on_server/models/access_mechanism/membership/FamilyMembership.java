package org.powellmakerspace.sign_on_server.models.access_mechanism.membership;

import org.powellmakerspace.sign_on_server.models.Member;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class FamilyMembership extends Membership {

    @ManyToOne
    private FamilyAccess familyAccess;

    public FamilyMembership(){}

    public FamilyMembership(Member member, FamilyAccess familyAccess){
        setMember(member); /** This calls the method inherited from Access Mechanism. **/
        this.familyAccess = familyAccess;
    }

    public FamilyAccess getFamilyAccess(){
        return familyAccess;
    }

    public void setFamilyAccess(FamilyAccess familyAccess){
        this.familyAccess = familyAccess;
    }

    public LocalDate getStartDate(){
        return familyAccess.getStartDate();
    }

    public LocalDate getEndDate(){
        return familyAccess.getEndDate();
    }

    public boolean canVisit(){
        return familyAccess.canVisit();
    }
}
