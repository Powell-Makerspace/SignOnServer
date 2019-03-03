package org.powellmakerspace.sign_on_server.models.access_mechanism.membership;

import org.powellmakerspace.sign_on_server.models.Member;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class IndividualMembership extends Membership {

    private LocalDate startDate;
    private LocalDate endDate;

    public IndividualMembership(){}


    public IndividualMembership(LocalDate startDate, LocalDate endDate, Member member){
        this.startDate = startDate;
        this.endDate = endDate;
        setMember(member); /** This calls the method inherited from Access Mechanism **/
    }

    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
    }

    @Override
    public boolean canVisit() {
        LocalDate dateNow = LocalDate.now();
        return (startDate.isBefore(dateNow) || startDate.isEqual(dateNow)) &&
                (endDate.isAfter(dateNow) || endDate.isEqual(dateNow));
    }
}
