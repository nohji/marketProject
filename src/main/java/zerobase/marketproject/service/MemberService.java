package zerobase.marketproject.service;

import org.springframework.stereotype.Service;
import zerobase.marketproject.domain.Member;
import zerobase.marketproject.repository.MemberRepository;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void registerMember(String userid, String username, String password, String tel){
       Member member = new Member();
       member.setUserid(userid);
       member.setUsername(username);
       member.setPassword(password);
       member.setTel(tel);
       memberRepository.save(member);
    }
}
