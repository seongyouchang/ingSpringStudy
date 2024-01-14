package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
// 관례같은 것 구현체(실제 비즈니스 로직이 작성된것) 가 하나일 경우 인터페이스명 + impl을 많이 쓴다.
public class MemberServiceImpl implements MemberService {

    // 회원가입하고 회원조회하려면 필요함
    // 자동완성에서 커멘드 쉬프트 엔터하면 세미콜론까지 작성해줌
    private final MemberRepository memberRepository;

    @Autowired // ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // join에서 save를 호출하면 다형성에 의해서 MemoryMemberRepository에 있는 (memberRepository가 아니라) Override된 save가 호출된다
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
