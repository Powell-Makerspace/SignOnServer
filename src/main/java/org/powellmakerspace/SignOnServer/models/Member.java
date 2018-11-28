package org.powellmakerspace.SignOnServer.models;

import org.powellmakerspace.SignOnServer.models.enums.MembershipType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long memberId;
    private String memberName;
    private MembershipType membershipType;
    private int punchPasses;

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
}
