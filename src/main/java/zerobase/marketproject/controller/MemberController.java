package zerobase.marketproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zerobase.marketproject.domain.Member;
import zerobase.marketproject.service.MemberService;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member/new")
    public Boolean registerMember(@RequestBody Member member){

        return memberService.registerMember(member.getUserid(), member.getUsername(), member.getPassword(), member.getTel());

    }

    @PostMapping("/member/login")
    public Boolean login(@RequestBody Member member){
        String loginResult = memberService.login(member.getUserid(),member.getPassword());
        return loginResult != null;
    }
}
