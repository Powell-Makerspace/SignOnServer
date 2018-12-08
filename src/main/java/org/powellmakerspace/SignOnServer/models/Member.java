package org.powellmakerspace.SignOnServer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.powellmakerspace.SignOnServer.models.enums.MembershipType;

import javax.persistence.*;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long memberId;
    private String memberName;
    private MembershipType membershipType;
    private int punchPasses;

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
     * @param membershipType Enum name for the member's membership type
     * @param punchPasses Integer number of remaining punch passes
     */
    public Member(String memberName, MembershipType membershipType, int punchPasses) {
        this.memberName = memberName;
        this.membershipType = membershipType;
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

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    public int getPunchPasses() {
        return punchPasses;
    }

    public void setPunchPasses(int punchPasses) {
        this.punchPasses = punchPasses;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }
}
