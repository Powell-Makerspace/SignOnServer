package org.powellmakerspace.sign_on_server.controllers;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powellmakerspace.sign_on_server.models.Member;
import org.powellmakerspace.sign_on_server.models.enums.RentalType;
import org.powellmakerspace.sign_on_server.services.MemberService;

import java.io.IOException;


@RunWith(JMockit.class)
public class MemberControllerTest {

    @Tested
    private MemberController memberController;

    @Injectable
    private MemberService memberService;

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
            memberService.getMembers(null, RentalType.INDIVIDUAL);
        }};

        memberController.getMembers(null, RentalType.INDIVIDUAL);
    }

    @Test
    public void getMembersNameType_success(){

        new Expectations(){{
           memberService.getMembers("Bilbo Baggins", RentalType.INDIVIDUAL);
        }};

        memberController.getMembers("Bilbo Baggins", RentalType.INDIVIDUAL);
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