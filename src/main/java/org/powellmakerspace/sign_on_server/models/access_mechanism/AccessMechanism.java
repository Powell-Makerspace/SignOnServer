package org.powellmakerspace.sign_on_server.models.access_mechanism;

import org.powellmakerspace.sign_on_server.models.Member;

import javax.persistence.*;

@Entity
public abstract class AccessMechanism {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long accessMechanismId;

    @OneToOne
    private Member member;

    public final long getAccessMechanismId() {
        return accessMechanismId;
    }

    public final void setAccessMechanismId(long accessMechanismId) {
        this.accessMechanismId = accessMechanismId;
    }

    public final Member getMember() {
        return member;
    }

    public final void setMember(Member member) {
        this.member = member;
    }

    // Abstract Methods

    public abstract boolean canVisit();
}
