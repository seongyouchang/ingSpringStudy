package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPoilcy;
import hello.core.discount.RateDiscountPoilcy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//스프링은 설정정보에 @Configuration이라고 적게 되어있다 이후 @Bean을 다 붙혀주면 스프링 컨테이너에 다 등록이 된다
@Configuration
public class AppConfig {

    // 싱글톤이 깨졌을까?
    //@Bean memberService -> new MemoryMemberRepository
    //@Bean orderService -> new MemoryMemberRepository

    // 원래라면 memberRepository가 3번 출력이 되야 하지만(memberservice return 1번, @Bean등록한번, orderService 1번)
    // 실제로는 아래와 같이 1번만 조회된다
/*  call AppConfig.memberService
    call AppConfig.memberRepository
    call AppConfig.orderService*/
    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        // 생성자를 주입한다고 해서 생성자주입이라 한다. (DI : Dependency Injection 의존성 주입)
        return new MemberServiceImpl(memberRepository());
    }

    // 컨트롤 알트 m 으로 리펙토링 new MemberRepository나 new FixFiscountPolicy를 메서드로 빼주면 더 역활이 구분되어 보임
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), disCountPoilcy());
    }

    @Bean
    private static DiscountPolicy disCountPoilcy() {
//        return new FixDiscountPoilcy();
        return new RateDiscountPoilcy();
    }
}
