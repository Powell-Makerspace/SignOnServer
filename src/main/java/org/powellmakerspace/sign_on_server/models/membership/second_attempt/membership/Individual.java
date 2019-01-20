package org.powellmakerspace.sign_on_server.models.membership.second_attempt.membership;

import org.powellmakerspace.sign_on_server.models.Member;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Individual extends Membership {

    private LocalDate startDate;
    private LocalDate endDate;

    public Individual(){}


    public Individual(LocalDate startDate, LocalDate endDate, Member member){
        this.startDate = startDate;
        this.endDate = endDate;
        setMember(member);
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
