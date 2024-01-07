package Order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    // 컨트롤 + E 누르면 이전 파일로 돌아갈 수 있음
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    //단위테스트란 Spring이나 컨테이너 등의 도움없이 순수 자바 코드로 생성하는 것
    //9000개가 있어도 금방 끝남

    @Test
    void createOrder(){
        //long => primitive type을 사용해도 되지만 null이 못들어감
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);

        memberService.join(member);
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        //org.assertj로 된 것을 사용해야 . 을 사용해서 편하게 테스트 할 수 있음
        //VIP일때 1000원 할인 되는지 검증
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
