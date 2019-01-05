package org.powellmakerspace.sign_on_server.models.membership.second_attempt.membership;

import org.powellmakerspace.sign_on_server.models.Member;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

public class Family implements Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long membershipId;
    private String name;
    @OneToMany(mappedBy = "familyMembership")
    private List<Member> familyMemberList;
    private LocalDate startDate;
    private LocalDate endDate;

    public Family(){}


    public Family(String name, List<Member> familyMemberList, LocalDate startDate, LocalDate endDate){
        this.name = name;
        this.familyMemberList = familyMemberList;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public long getMembershipId(){
        return membershipId;
    }

    public String getName(){
        return name;
    }

    public List<Member> getFamilyMemberList(){
        return familyMemberList;
    }

    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setMembershipId(long membershipId){
        this.membershipId = membershipId;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setFamilyMemberList(List<Member> familyMemberList){
        this.familyMemberList = familyMemberList;
    }

    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
    }

    @Override
    public boolean canVisit() {
        LocalDate nowDate = LocalDate.now();
        return (startDate.isBefore(nowDate) || startDate.isEqual(nowDate)) &&
                (endDate.isAfter(nowDate) || endDate.isEqual(nowDate));
    }
}
