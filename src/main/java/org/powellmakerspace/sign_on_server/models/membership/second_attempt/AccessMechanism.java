package org.powellmakerspace.sign_on_server.models.membership.second_attempt;

import org.powellmakerspace.sign_on_server.models.Member;

import javax.persistence.*;

@Entity
public abstract class AccessMechanism {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long guestId;

    @OneToOne
    private Member member;

    public final long getGuestId() {
        return guestId;
    }

    public final void setGuestId(long guestId) {
        this.guestId = guestId;
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
