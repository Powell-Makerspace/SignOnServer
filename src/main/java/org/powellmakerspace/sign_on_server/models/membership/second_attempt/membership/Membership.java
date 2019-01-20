package org.powellmakerspace.sign_on_server.models.membership.second_attempt.membership;

import org.powellmakerspace.sign_on_server.models.membership.second_attempt.AccessMechanism;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public abstract class Membership extends AccessMechanism {

    public abstract LocalDate getStartDate();

    public abstract LocalDate getEndDate();
}
