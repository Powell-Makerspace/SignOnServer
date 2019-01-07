package org.powellmakerspace.sign_on_server.models.membership.second_attempt;

import org.powellmakerspace.sign_on_server.models.Member;
import org.powellmakerspace.sign_on_server.models.enums.RentalType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class RegularRenters implements AccessMechanism {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long renterId;
    @OneToOne
    private Member member;
    private LocalDate startDate;
    private LocalDate endDate;
    private RentalType rentalType;

    public RegularRenters(){}

    public RegularRenters(Member member, LocalDate startDate, LocalDate endDate){
        this.member = member;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public long getRenterId(){
        return renterId;
    }

    public Member getMember(){
        return member;
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

    public void setRenterId(long renterId){
        this.renterId = renterId;
    }

    public void setMember(Member member){
        this.member = member;
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
