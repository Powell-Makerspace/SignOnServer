package org.powellmakerspace.sign_on_server.models.membership.second_attempt;

import org.powellmakerspace.sign_on_server.models.Member;
import org.powellmakerspace.sign_on_server.models.enums.RentalType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class RegularRenters extends AccessMechanism {

    private LocalDate startDate;
    private LocalDate endDate;
    private RentalType rentalType;

    public RegularRenters(){}

    public RegularRenters(Member member, LocalDate startDate, LocalDate endDate){
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

    public RentalType getRentalType(){
        return rentalType;
    }

    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
    }

    public void setRentalType(RentalType rentalType){
        this.rentalType = rentalType;
    }



    @Override
    public boolean canVisit() {
        LocalDate nowDate = LocalDate.now();
        return (startDate.isBefore(nowDate) || startDate.isEqual(nowDate)) &&
                (endDate.isAfter(nowDate) || endDate.isEqual(nowDate));
    }
}
