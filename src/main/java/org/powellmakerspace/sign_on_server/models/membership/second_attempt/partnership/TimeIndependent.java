package org.powellmakerspace.sign_on_server.models.membership.second_attempt.partnership;

import org.powellmakerspace.sign_on_server.models.Member;

import javax.persistence.*;

@Entity
public class TimeIndependent implements PartnerMember {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long timeIndependentId;
    @OneToOne(mappedBy = "accessMechanism")
    private Member member;
    @ManyToOne
    private Partnership partnership;


    public TimeIndependent(){}

    public TimeIndependent(Member member, Partnership partnership){
        this.member = member;
        this.partnership = partnership;
    }

    public long getTimeIndependentId(){
        return timeIndependentId;
    }

    public Member getMember(){
        return member;
    }

    public Partnership getPartnership(){
        return partnership;
    }

    public void setTimeIndependentId(long timeIndependentId){
        this.timeIndependentId = timeIndependentId;
    }

    public void setMember(Member member){
        this.member = member;
    }

    public void setPartnership(Partnership partnership){
        this.partnership = partnership;
    }

    @Override
    public boolean canVisit() {
        return partnership.canVisit();
    }
}
