package zerobase.marketproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zerobase.marketproject.domain.Member;
import zerobase.marketproject.service.MemberService;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register/member")
    void registerMember(@RequestBody Member member){
        memberService.registerMember(member.getUserid(), member.getUsername(), member.getPassword(), member.getTel());
    }
}
