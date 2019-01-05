package org.powellmakerspace.sign_on_server.models.membership.second_attempt.membership;

import org.powellmakerspace.sign_on_server.models.membership.second_attempt.AccessMechanism;

import java.time.LocalDate;

public interface Membership extends AccessMechanism {

    LocalDate getStartDate();
    LocalDate getEndDate();
}
