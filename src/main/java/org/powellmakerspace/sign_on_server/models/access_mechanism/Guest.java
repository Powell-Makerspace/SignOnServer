package org.powellmakerspace.sign_on_server.models.access_mechanism;

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
