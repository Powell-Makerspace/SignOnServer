package org.powellmakerspace.sign_on_server.models.membership.second_attempt;

import org.powellmakerspace.sign_on_server.models.Member;

import javax.persistence.*;

@Entity
public class Guest extends AccessMechanism {

    public Guest(){}

    public Guest(Member member){
        setMember(member);
    }

    @Override
    public boolean canVisit() {
        return true;
    }
}
