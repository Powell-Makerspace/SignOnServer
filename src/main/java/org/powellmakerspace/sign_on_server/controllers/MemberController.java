package org.powellmakerspace.sign_on_server.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.powellmakerspace.sign_on_server.exception.ResourceNotFoundException;
import org.powellmakerspace.sign_on_server.models.Member;
import org.powellmakerspace.sign_on_server.models.enums.MembershipType;
import org.powellmakerspace.sign_on_server.models.membership.second_attempt.AccessMechanism;
import org.powellmakerspace.sign_on_server.services.MemberService;
import org.springframework.web.bind.annotation.*;

@Api(
        value = "Member API",
        description = "Provides functionality for updating and retrieving member objects."
)
@RestController
@RequestMapping("/members")
public class MemberController {

    private MemberService memberService;

    /**
     * Constructor of the Member Controller
     * @param memberService member service layer
     */
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }



    @ApiOperation(
            value = "createMember",
            notes = "Add new member to the repository"
    )
    @RequestMapping(path = "", method = RequestMethod.PUT)
    public void createMember(
            @ApiParam(
                    value = "member object to be created",
                    required = true
            )
            @RequestBody Member member
    ) {
        memberService.createMember(member);
    }



    @ApiOperation(
            value = "updateMember",
            notes = "Update a given member in the repository"
    )
    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    public void updateMember(
            @ApiParam(
                    value = "memberId of the member to be updated",
                    required = true
            )
            @PathVariable("id")
            long memberId,
            @ApiParam(
                    value = "member object to be updated",
                    required = true
            )
            @RequestBody
            Member member
    ) {
        member.setMemberId(memberId); //Make sure the memberId is a part of the member object
        memberService.updateMember(member);
    }



    @ApiOperation(
            value = "getMembers",
            notes = "Retrieves all members given the provided filters",
            response = Member.class,
            responseContainer = "List"
    )
    @RequestMapping(path = "", method = RequestMethod.GET)
    public Iterable<Member> getMembers(
            @ApiParam(
                    value = "Full or partial member name to filter search"
            )
            @RequestParam(value = "memberName", required = false) String memberName,
            @ApiParam(
                    value = "membership Type to filter search (enum)"
            )
            @RequestParam(value = "membershipType", required = false) AccessMechanism membershipType
    ) {
        return memberService.getMembers(memberName,  membershipType);
    }


    @ApiOperation(
            value = "getMember",
            notes = "Retrieves a member matching the provided memberId",
            response = Member.class
    )
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Member getMember(
            @ApiParam(
                    value = "long memberId",
                    required = true
            )
            @PathVariable("id") long memberId
    ) throws ResourceNotFoundException {
        return memberService.getMember(memberId);
    }
}
