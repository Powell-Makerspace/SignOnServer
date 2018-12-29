package org.powellmakerspace.SignOnServer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.powellmakerspace.SignOnServer.models.Membership.Membership;

import javax.persistence.*;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long memberId;
    private String memberName;

    private int punchPasses;

    @ManyToOne
    @JsonIgnore
    private Membership membership;

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
     * @param membership Class that defines the membership of the member
     * @param punchPasses Integer number of remaining punch passes
     */
    public Member(String memberName, Membership membership, int punchPasses) {
        this.memberName = memberName;
        this.membership = membership;
        this.punchPasses = punchPasses;
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

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public int getPunchPasses() {
        return punchPasses;
    }

    public void setPunchPasses(int punchPasses) {
        this.punchPasses = punchPasses;
    }

    public List<Visit> getVisitList() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }
}
