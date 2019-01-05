package org.powellmakerspace.sign_on_server.models.membership.second_attempt.partnership;

import org.powellmakerspace.sign_on_server.models.membership.second_attempt.AccessMechanism;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

public class Partnership implements AccessMechanism {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long partnershipId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String organizationName;
    @OneToMany(mappedBy = "partnership")
    private List<PartnerMember> memberList;


    public Partnership(){}


    public Partnership(LocalDate startDate, LocalDate endDate, String organizationName, List<PartnerMember> memberList){
        this.startDate = startDate;
        this.endDate = endDate;
        this.organizationName = organizationName;
        this.memberList = memberList;
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

    public List<PartnerMember> getMemberList(){
        return memberList;
    }

    public long getPartnershipId(){
        return partnershipId;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
    }

    public void setOrganizationName(String organizationName){
        this.organizationName = organizationName;
    }

    public void setMemberList(List<PartnerMember> memberList){
        this.memberList = memberList;
    }

    public void setPartnershipId(long partnershipId){
        this.partnershipId = partnershipId;
    }

    @Override
    public boolean canVisit() {
        LocalDate nowDate = LocalDate.now();
        return (startDate.isBefore(nowDate) || startDate.isEqual(nowDate)) &&
                (endDate.isAfter(nowDate) || endDate.isEqual(nowDate));
    }
}
