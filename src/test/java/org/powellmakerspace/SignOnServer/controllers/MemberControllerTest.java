package org.powellmakerspace.SignOnServer.controllers;

import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powellmakerspace.SignOnServer.models.Member;
import org.powellmakerspace.SignOnServer.models.enums.MembershipType;
import org.powellmakerspace.SignOnServer.services.MemberService;


import java.io.IOException;


@RunWith(JMockit.class)
public class MemberControllerTest {

    private MemberController memberController;

    @Mocked
    private MemberService memberService;

    @Before
    public void init(){
        memberController = new MemberController(memberService);
    }

    @Test
    public void createMember_success(){
        Member testMember = new Member();

        new Expectations(){{
            memberService.createMember(testMember);
        }};

        memberController.createMember(testMember);
    }

    @Test
    public void updateMember_success(){
        Member testMember = new Member();

        new Expectations(){{
            memberService.updateMember(testMember);
        }};

        memberController.updateMember(1L, testMember);
    }

    @Test
    public void getMembersNullNull_success(){

        new Expectations(){{
            memberService.getMembers(null, null);
        }};

        memberController.getMembers(null, null);
    }

    @Test
    public void getMembersNameNull_success(){

        new Expectations(){{
           memberService.getMembers("Bilbo Baggins", null);
        }};

        memberController.getMembers("Bilbo Baggins", null);
    }

    @Test
    public void getMembersNullType_success(){

        new Expectations(){{
            memberService.getMembers(null, MembershipType.INDIVIDUAL);
        }};

        memberController.getMembers(null, MembershipType.INDIVIDUAL);
    }

    @Test
    public void getMembersNameType_success(){

        new Expectations(){{
           memberService.getMembers("Bilbo Baggins", MembershipType.INDIVIDUAL);
        }};

        memberController.getMembers("Bilbo Baggins", MembershipType.INDIVIDUAL);
    }

    @Test
    public void getMember_success(){

        new Expectations(){{
            memberService.getMember(1);
        }};

        memberController.getMember(1);
    }


    @Test(expected = IOException.class)
    public void createMember_IOException(){
        Member testMember = new Member();

        new Expectations(){{
            memberService.createMember(testMember);    result = new IOException("Test Exception");
        }};

        memberController.createMember(testMember);
    }
}