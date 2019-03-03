package org.powellmakerspace.sign_on_server.models.access_mechanism;

import org.powellmakerspace.sign_on_server.models.Member;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Volunteer extends AccessMechanism {

    private LocalDate startDate;
    private LocalDate endDate;


    public Volunteer(){}


    public Volunteer(Member member, LocalDate startDate, LocalDate endDate){
        setMember(member);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate(){
        return startDate;
    }

    public LocalDate getEndDate(){
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
        LocalDate nowDate = LocalDate.now();
        return (startDate.isBefore(nowDate) || startDate.isEqual(nowDate)) &&
                (endDate.isAfter(nowDate) || endDate.isEqual(nowDate));
    }
}
