package org.powellmakerspace.sign_on_server.models.access_mechanism.membership;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class FamilyAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long membershipId;
    private String name;
    @OneToMany(mappedBy = "familyAccess")
    private List<FamilyMembership> familyMembershipList;
    private LocalDate startDate;
    private LocalDate endDate;

    public FamilyAccess(){}

    public FamilyAccess(String name, List<FamilyMembership> familyMembershipList, LocalDate startDate, LocalDate endDate){
        this.name = name;
        this.familyMembershipList = familyMembershipList;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public long getMembershipId(){
        return membershipId;
    }

    public String getName(){
        return name;
    }

    public List<FamilyMembership> getFamilyMembershipList(){
        return familyMembershipList;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setMembershipId(long membershipId){
        this.membershipId = membershipId;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setFamilyMembershipList(List<FamilyMembership> familyMembershipList){
        this.familyMembershipList = familyMembershipList;
    }

    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
    }

    public boolean canVisit() {
        LocalDate nowDate = LocalDate.now();
        return (startDate.isBefore(nowDate) || startDate.isEqual(nowDate)) &&
                (endDate.isAfter(nowDate) || endDate.isEqual(nowDate));
    }
}
