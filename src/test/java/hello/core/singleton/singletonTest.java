package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class singletonTest {

    @Test
    @DisplayName("스프링없는 순수한 DI 컨테이너")
    void pureContanier(){
        AppConfig appConfig = new AppConfig();
        //1. 조회: 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        //2. 조회: 호출할 때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참족값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //결과: 호출할 때마다 다른게 생성된다 마지막 @뒤에 번호를 보면 알 수 있다
        /*
        memberService1 = hello.core.member.MemberServiceImpl@7bb58ca3
        memberService2 = hello.core.member.MemberServiceImpl@c540f5a
        */

        // memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴 사용")
    void SingtonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println(singletonService1);
        System.out.println(singletonService2);

        //결과: 같은 객체 인스턴스를 반환 hello.core.singleton.SingletonService@62e136d3
        //                           hello.core.singleton.SingletonService@62e136d3
        //자바가 뜰 때 생성해둔 걸 가져다 쓴거임

        assertThat(singletonService1).isSameAs(singletonService2);
        //same: ==
        //equal: equal이라는 메서드를 override

        //tip: 고치다보면 해당 클래스레벨에서 터질 확률이 높기때문에 클래스 전체 테스트 돌려보기

        //스프링 컨테이너를 쓰면 스프링이 객체를 다 싱글톤으로 관리 해줌
    }

}
