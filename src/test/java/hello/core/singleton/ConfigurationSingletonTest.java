package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configuration(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        // 원래는 이렇게 구체타입으로 꺼내면 안좋다
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberRepository1 = " + memberRepository1);
        System.out.println("memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        // 결과 : 3개다 같은 인스턴스가 조회됨

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }
    @Test
    void configurationDeep(){
        /* Appconfig.class을 하면 Bean으로 등록시켜준다*/
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean.getClass() = " + bean.getClass());

        // 결과 : bean.getClass() = class hello.core.AppConfig$$SpringCGLIB$$0
        // 순수 클래스였다면 class hello.core.AppConfig 여기까지 나왔어야 함 바이트 코드를 스프링이 조작했기 때문에
        // 따라서 싱글톤이 보장되도록 해준다
        // 만약 Appconfig에서 @Configuration을 주석처리한 뒤에 돌려보면 순수 클래스가 호출이 된다 대신 싱글톤이 깨진다
        // memberRepository가 총 3번 호출 된다 (Appconfig 주석 참조)
    }
}
