package org.powellmakerspace.sign_on_server.models.membership.first_attempt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.powellmakerspace.sign_on_server.models.Member;

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

    // @TODO Check with Nick as to whether this should be defined as OneToOne
    @OneToMany(mappedBy = "membership")
    @JsonIgnore
    private List<Member> memberList;

    /**
     * Default Constructor
     */
    public Individual(){}

    /**
     * Constructor which accepts the membership time frame.
     * @param startDate LocalDate marking the beginning of the membership.
     * @param endDate LocalDate marking the ending of the membership.
     */
    public Individual(LocalDate startDate, LocalDate endDate){
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

    /**
     * Method to update the endDate of a membership as defined by the membership type.
     * Adds a given number of months to the individual's endDate.
     * @param monthsAdded int number of months to be added to the membership.
     */
    public void updateTimeframe(int monthsAdded){
        this.endDate = endDate.plusMonths(monthsAdded);
    }

}
