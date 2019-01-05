package org.powellmakerspace.sign_on_server.models.membership.second_attempt.partnership;

import org.powellmakerspace.sign_on_server.models.Member;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class TimeIndependent implements PartnerMember {

    @OneToOne
    private Member member;
    @ManyToOne
    private Partnership partnership;


    public TimeIndependent(){}

    public TimeIndependent(Member member, Partnership partnership){
        this.member = member;
        this.partnership = partnership;
    }

    public Member getMember(){
        return member;
    }

    public Partnership getPartnership(){
        return partnership;
    }

    public void setMember(Member member){
        this.member = member;
    }

    public void setPartnership(Partnership partnership){
        this.partnership = partnership;
    }
}
