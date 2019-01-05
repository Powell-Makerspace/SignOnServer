package org.powellmakerspace.sign_on_server.models.membership.second_attempt.partnership;

import org.powellmakerspace.sign_on_server.models.Member;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDate;

public class TimeDependent implements PartnerMember {

    @OneToOne()
    private Member member;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToOne
    private Partnership partnership;


    public TimeDependent(){}

    public TimeDependent(Member member, LocalDate startDate, LocalDate endDate, Partnership partnership){
        this.member = member;
        this.startDate = startDate;
        this.endDate = endDate;
        this.partnership = partnership;
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

    public Partnership getPartnership(){
        return partnership;
    }

    public void setMember(Member member){
        this.member = member;
    }

    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }

    public void setEndDate (LocalDate endDate){
        this.endDate = endDate;
    }

    public void setPartnership(Partnership partnership){
        this.partnership = partnership;
    }

    public boolean isActive(){
        LocalDate nowDate = LocalDate.now();
        return (startDate.isBefore(nowDate) || startDate.isEqual(nowDate)) &&
                (endDate.isAfter(nowDate) || endDate.isEqual(nowDate));
    }


}
