package org.powellmakerspace.SignOnServer.models.Membership;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.powellmakerspace.SignOnServer.models.Member;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Individual implements Membership{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long membershipId;

    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(mappedBy = "membership")
    @JsonIgnore
    private List<Member> memberList;

    /**
     * Default Constructor
     */
    public void Individual(){}

    /**
     * Constructor which accepts the membership time frame.
     * @param startDate LocalDate marking the beginning of the membership.
     * @param endDate LocalDate marking the ending of the membership.
     */
    public void Individual(LocalDate startDate, LocalDate endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean isActive() {

        if((startDate.isEqual(LocalDate.now()) ||
                startDate.isBefore(LocalDate.now())) &&
                        (endDate.isEqual(LocalDate.now()) ||
                                endDate.isAfter(LocalDate.now()))){
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


}
