package org.powellmakerspace.sign_on_server.models.membership.second_attempt;

import org.powellmakerspace.sign_on_server.models.Member;

import javax.persistence.*;

@Entity
public class Guest implements AccessMechanism {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long guestId;
    @OneToOne
    private Member member;


    public Guest(){}


    public Guest(Member member){
        this.member = member;
    }


    @Override
    public boolean canVisit() {
        return true;
    }
}
