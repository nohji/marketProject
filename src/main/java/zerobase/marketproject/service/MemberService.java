package zerobase.marketproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.marketproject.domain.Member;
import zerobase.marketproject.repository.MemberRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    // 유저 id로 User 찾기
    public Member findMemeber(String id) {
        return memberRepository.findById(id).get();
    }
    public boolean registerMember(String userid, String username, String password, String tel){
        //userid 중복 체크
        Optional<Member> optionalMember = memberRepository.findById(userid);
        if(optionalMember.isPresent()) {
            return false;
        }

        //비밀번호 암호화
        //보안 설정 시 security 모듈 이슈로 일단 주석.
        //String encPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        Member member = new Member();
        member.setUserid(userid);
        member.setUsername(username);
        member.setPassword(password);
        member.setTel(tel);
        memberRepository.save(member);

        return true;
    }

    public String login(String userid, String password) {
        //id 있는지 확인.
        Optional<Member> optionalMember = memberRepository.findById(userid);
        if(optionalMember.isPresent()) { //있다.
            Member member = optionalMember.get();
            //비밀번호 일치한가?
            if (member.getPassword().equals(password)) {
                //비밀번호 일치.
                return "로그인 성공";
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
