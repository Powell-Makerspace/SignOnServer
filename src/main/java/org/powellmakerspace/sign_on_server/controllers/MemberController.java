package org.powellmakerspace.sign_on_server.controllers;

import io.swagger.annotations.*;
import org.powellmakerspace.sign_on_server.exception.ResourceNotFoundException;
import org.powellmakerspace.sign_on_server.models.Member;
import org.powellmakerspace.sign_on_server.models.access_mechanism.AccessMechanism;
import org.powellmakerspace.sign_on_server.services.MemberService;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("/api/members")
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
    @PutMapping(path = "")
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
    @PostMapping(path = "/{id}")
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
    @GetMapping(path = "")
    public Iterable<Member> getMembers(
            @ApiParam(
                    value = "Full or partial member name to filter search"
            )
            @RequestParam(value = "memberName", required = false)
            String memberName,
            @ApiParam(
                    value = "membership Type to filter search (enum)"
            )
            @RequestParam(value = "membershipType", required = false)
            AccessMechanism membershipType,
            @RequestParam(value = "page-offset", defaultValue = "1")
            int pageOffset,
            @RequestParam(value = "page-size", defaultValue = "20")
            int pageSize
    ) {
        return memberService.getMembers(memberName,  membershipType, pageOffset, pageSize);
    }


    @ApiOperation(
            value = "getMember",
            notes = "Retrieves a member matching the provided memberId",
            response = Member.class
    )
    @GetMapping(path = "/{id}")
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
