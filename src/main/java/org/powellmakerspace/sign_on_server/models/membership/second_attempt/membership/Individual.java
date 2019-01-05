package org.powellmakerspace.sign_on_server.models.membership.second_attempt.membership;

import org.powellmakerspace.sign_on_server.models.Member;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

public class Individual implements Membership{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long membershipId;
    private LocalDate startDate;
    private LocalDate endDate;
    @OneToOne(mappedBy = "accessMechanism")
    private Member member;


    public Individual(){}


    public Individual(LocalDate startDate, LocalDate endDate, Member member){
        this.startDate = startDate;
        this.endDate = endDate;
        this.member = member;
    }



    public long getMembershipId(){
        return membershipId;
    }

    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public LocalDate getEndDate() {
        return endDate;
    }

    public Member getMember(){
        return member;
    }

    public void setMembershipId(long membershipId){
        this.membershipId = membershipId;
    }

    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
    }

    public void setMember(Member member){
        this.member = member;
    }

    @Override
    public boolean canVisit() {
        LocalDate dateNow = LocalDate.now();
        return (startDate.isBefore(dateNow) || startDate.isEqual(dateNow)) &&
                (endDate.isAfter(dateNow) || endDate.isEqual(dateNow));
    }
}
