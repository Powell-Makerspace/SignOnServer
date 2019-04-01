package org.powellmakerspace.sign_on_server.controllers

import io.swagger.annotations.*
import org.powellmakerspace.sign_on_server.exception.ResourceNotFoundException
import org.powellmakerspace.sign_on_server.models.Member
import org.powellmakerspace.sign_on_server.models.access_mechanism.AccessMechanism
import org.powellmakerspace.sign_on_server.services.MemberService
import org.springframework.web.bind.annotation.*

@Api
@RestController
@RequestMapping("/api/members")
class MemberController(private val memberService: MemberService) {

    @ApiOperation(
            value = "createMember",
            notes = "Add new member to the repository"
    )
    @PutMapping(path = arrayOf(""))
    fun createMember(
            @ApiParam(value = "member object to be created", required = true)
            @RequestBody 
            member: Member
    ) {
        memberService.createMember(member)
    }



    @ApiOperation(
            value = "updateMember",
            notes = "Update a given member in the repository"
    )
    @PostMapping(path = arrayOf("/{id}"))
    fun updateMember(
            @ApiParam(value = "memberId of the member to be updated", required = true)
            @PathVariable("id")
            memberId: Long,
            @ApiParam(value = "member object to be updated", required = true)
            @RequestBody
            member: Member
    ) {
        member.memberId = memberId //Make sure the memberId is a part of the member object
        memberService.updateMember(member)
    }



    @ApiOperation(
            value = "getMembers",
            notes = "Retrieves all members given the provided filters",
            response = Member::class,
            responseContainer = "List"
    )
    @GetMapping(path = arrayOf(""))
    fun getMembers(
            @ApiParam("Full or partial member name to filter search")
            @RequestParam(value = "memberName", required = false)
            memberName: String?,
            @ApiParam("membership Type to filter search (enum)")
            @RequestParam(value = "membershipType", required = false)
            membershipType: AccessMechanism?, 
            @RequestParam(value = "page-offset", defaultValue = "1")
            pageOffset: Int,
            @RequestParam(value = "page-size", defaultValue = "20")
            pageSize: Int
    ): Iterable<Member> {
        return memberService.getMembers(memberName, membershipType, pageOffset, pageSize)
    }


    @ApiOperation(
            value = "getMember",
            notes = "Retrieves a member matching the provided memberId",
            response = Member::class
    )
    @GetMapping(path = arrayOf("/{id}"))
    fun getMember(
            @ApiParam(value = "long memberId", required = true)
            @PathVariable("id") 
            memberId: Long
    ): Member {
        return memberService.getMember(memberId)
    }
}
