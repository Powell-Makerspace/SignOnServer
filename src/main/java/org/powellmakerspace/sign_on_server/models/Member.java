package org.powellmakerspace.sign_on_server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.powellmakerspace.sign_on_server.models.access_mechanism.AccessMechanism;

import javax.persistence.*;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long memberId;
    private String memberName;

    @OneToOne(mappedBy = "member")
    @JsonIgnore
    private AccessMechanism accessMechanism;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Visit> visits;

    /**
     * Constructor for a member object containing no parameters
     */
    public Member() {
    }



    /**
     * Constructor for a member object
     * @param memberName String name for the member
     * @param accessMechanism Class that defines the accessMechanism of the member
     */
    public Member(String memberName, AccessMechanism accessMechanism) {
        this.memberName = memberName;
        this.accessMechanism = accessMechanism;
    }


    /**
     * Getters and Setters for all member attributes
     */


    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public AccessMechanism getAccessMechanism() {
        return accessMechanism;
    }

    public void setAccessMechanism(AccessMechanism accessMechanism) {
        this.accessMechanism = accessMechanism;
    }

    public List<Visit> getVisitList() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }
}
