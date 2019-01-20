package org.powellmakerspace.sign_on_server.models.membership.second_attempt.partnership;

import org.powellmakerspace.sign_on_server.models.membership.second_attempt.AccessMechanism;

import javax.persistence.*;
import java.time.LocalDate;

//@Entity
public class Partnership {

    private LocalDate startDate;
    private LocalDate endDate;
    private String organizationName;

//    @OneToMany(mappedBy = "partnership")
//    private List<PartnerMember> memberList;


    public Partnership(){}


    public Partnership(LocalDate startDate, LocalDate endDate, String organizationName) { // , List<PartnerMember> memberList){
        this.startDate = startDate;
        this.endDate = endDate;
        this.organizationName = organizationName;
//        this.memberList = memberList;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate(){
        return endDate;
    }

    public String getOrganizationName(){
        return organizationName;
    }

//    public List<PartnerMember> getMemberList(){
//        return memberList;
//    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
    }

    public void setOrganizationName(String organizationName){
        this.organizationName = organizationName;
    }

//    public void setMemberList(List<PartnerMember> memberList){
//        this.memberList = memberList;
//    }

//    @Override
//    public boolean canVisit() {
//        LocalDate nowDate = LocalDate.now();
//        return (startDate.isBefore(nowDate) || startDate.isEqual(nowDate)) &&
//                (endDate.isAfter(nowDate) || endDate.isEqual(nowDate));
//    }
}
