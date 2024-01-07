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

    @Bean
    public MemberService memberService(){
        // 생성자를 주입한다고 해서 생성자주입이라 한다. (DI : Dependency Injection 의존성 주입)
        return new MemberServiceImpl(memberRepository());
    }

    // 컨트롤 알트 m 으로 리펙토링 new MemberRepository나 new FixFiscountPolicy를 메서드로 빼주면 더 역활이 구분되어 보임
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), disCountPoilcy());
    }

    @Bean
    private static DiscountPolicy disCountPoilcy() {
//        return new FixDiscountPoilcy();
        return new RateDiscountPoilcy();
    }
}
