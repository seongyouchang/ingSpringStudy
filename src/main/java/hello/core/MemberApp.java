package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//      스프링은 모든게 어플리케이션컨텍스트로 시작된다 이게 스프링 컨테이너라고 보면 됨 얘가 모든걸 다 관리하게 된다(@Bean이라고 해둔것들)
//        이렇게 하면 AppConfig에 있는 설정정보를 가지고 스프링이 @Bean 붙은것들을 스프링 컨테이너에 넣어서 관리해준다

        // 첫번째 파라미터 name은 @Bean 붙은 메서드 이름으로 넣어준다
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        // 원래는 new MemeberServiceImpl을 생성하고 또 Impl에서 다른 객체를 생성하며 순차적으로 돌듯이 작업했지만
        // 이제는 appConfig로 다 결정을 한다
//        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findmember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findmember.getName());
    }
}
