package org.powellmakerspace.sign_on_server.models.membership.first_attempt;

import org.powellmakerspace.sign_on_server.models.Member;

import java.time.LocalDate;
import java.util.List;

public interface Membership {

    // Check to find out whether this membership is active.
    boolean isActive();

    // Getters and setter for most properties
    LocalDate getStartDate();
    void setStartDate(LocalDate startDate);
    LocalDate getEndDate();
    void setEndDate(LocalDate endDate);

    // Getter for the member list
    List<Member> getMemberList();

}
