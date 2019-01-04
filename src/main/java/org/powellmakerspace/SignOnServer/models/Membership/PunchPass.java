package org.powellmakerspace.SignOnServer.models.Membership;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.powellmakerspace.SignOnServer.models.Member;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

public class PunchPass implements Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long membershipId;

    private LocalDate startDate;
    private LocalDate endDate;
    private int punches;

    @OneToMany(mappedBy = "membership")
    @JsonIgnore
    private List<Member> memberList;

    /**
     * Default Constructor
     */
    public void PunchPass(){}

    /**
     * Constructor which accepts membership time frame and number of punches.
     * @param startDate LocalDate marking the beginning of the membership.
     * @param endDate LocalDate marking the ending of the membership.
     * @param punches int defining the number of day pass punches.
     */
    public void PunchPass(LocalDate startDate, LocalDate endDate, int punches){
        this.startDate = startDate;
        this.endDate = endDate;
        this.punches = punches;
    }


    /**
     * Note, this method incorporates a check that confirms there are punches remaining.
     * @return boolean value of whether the membership is active.
     */
    @Override
    public boolean isActive() {

        if((startDate.isEqual(LocalDate.now()) ||
                startDate.isBefore(LocalDate.now())) &&
                (endDate.isEqual(LocalDate.now()) ||
                        endDate.isAfter(LocalDate.now())) &&
                            punches != 0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public List<Member> getMemberList() {
        return memberList;
    }

    public void addMemberToList(Member member){
        memberList.add(member);
    }

    /**
     * Method to deprecate the number of punches when used.
     * @TODO Determine the best way to pass success or failure.
     */
    public boolean usePunch(){
        if(punches > 0) {
            punches--;
            return true;
        }
        else return false;
    }

    public void addPunches(int newPunches){
        punches += newPunches;
    }
}
