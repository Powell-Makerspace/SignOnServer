package org.powellmakerspace.sign_on_server.models.membership.second_attempt.partnership;

import org.powellmakerspace.sign_on_server.models.Member;

import javax.persistence.*;

@Entity
public class TimeIndependent extends PartnerMember {


//    @ManyToOne
//    private Partnership partnership;


    public TimeIndependent(){}

    public TimeIndependent(Member member, Partnership partnership){
        setMember(member);
//        this.partnership = partnership;
    }

//    public long getTimeIndependentId(){
//        return timeIndependentId;
//    }
//
//    public Partnership getPartnership(){
//        return partnership;
//    }
//
//    public void setTimeIndependentId(long timeIndependentId){
//        this.timeIndependentId = timeIndependentId;
//    }
//
//    public void setPartnership(Partnership partnership){
//        this.partnership = partnership;
//    }

    @Override
    public boolean canVisit() {
        return true; // partnership.canVisit();
    }
}
