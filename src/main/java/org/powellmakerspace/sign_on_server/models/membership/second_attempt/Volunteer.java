package org.powellmakerspace.sign_on_server.models.membership.second_attempt;

import org.powellmakerspace.sign_on_server.models.Member;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

public class Volunteer implements AccessMechanism {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long volunteerId;
    @OneToOne
    private Member member;
    private LocalDate startDate;
    private LocalDate endDate;


    public Volunteer(){}


    public Volunteer(Member member, LocalDate startDate, LocalDate endDate){
        this.member = member;
        this.startDate = startDate;
        this.endDate = endDate;
    }



    public long getVolunteerId(){
        return volunteerId;
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

    public void setVolunteerId(long volunteerId){
        this.volunteerId = volunteerId;
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

    @Override
    public boolean canVisit() {
        LocalDate nowDate = LocalDate.now();
        return (startDate.isBefore(nowDate) || startDate.isEqual(nowDate)) &&
                (endDate.isAfter(nowDate) || endDate.isEqual(nowDate));
    }
}
