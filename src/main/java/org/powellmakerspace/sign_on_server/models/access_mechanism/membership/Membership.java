package org.powellmakerspace.sign_on_server.models.access_mechanism.membership;

import org.powellmakerspace.sign_on_server.models.access_mechanism.AccessMechanism;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public abstract class Membership extends AccessMechanism {

    public abstract LocalDate getStartDate();

    public abstract LocalDate getEndDate();
}
