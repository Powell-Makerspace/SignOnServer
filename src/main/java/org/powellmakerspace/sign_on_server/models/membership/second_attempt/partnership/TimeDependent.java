package org.powellmakerspace.sign_on_server.models.membership.second_attempt.partnership;

import org.powellmakerspace.sign_on_server.models.Member;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class TimeDependent extends PartnerMember {

    private LocalDate startDate;
    private LocalDate endDate;
//    @ManyToOne
//    private Partnership partnership;


    public TimeDependent(){}

    public TimeDependent(Member member, LocalDate startDate, LocalDate endDate, Partnership partnership){
        setMember(member);
        this.startDate = startDate;
        this.endDate = endDate;
//        this.partnership = partnership;
    }

    public LocalDate getStartDate(){
        return startDate;
    }

    public LocalDate getEndDate(){
        return endDate;
    }

//    public Partnership getPartnership(){
//        return partnership;
//    }

    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }

    public void setEndDate (LocalDate endDate){
        this.endDate = endDate;
    }

//    public void setPartnership(Partnership partnership){
//        this.partnership = partnership;
//    }

    public boolean isActive(){
        LocalDate nowDate = LocalDate.now();
        return (startDate.isBefore(nowDate) || startDate.isEqual(nowDate)) &&
                (endDate.isAfter(nowDate) || endDate.isEqual(nowDate));
    }


    /**
     * This method specifically checks that both the Partnership is active,
     * and this particular member's time has not yet expired.
     * @return boolean value of whether this individual can visit.
     */
    @Override
    public boolean canVisit() {
        return true;
//        LocalDate nowDate = LocalDate.now();
//
//        return (startDate.isBefore(nowDate) || startDate.isEqual(nowDate)) &&
//                (endDate.isAfter(nowDate) || endDate.isEqual(nowDate)) &&
//                partnership.canVisit();
    }
}
