package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPoilcyTest {
    RateDiscountPoilcy rateDiscountPoilcy = new RateDiscountPoilcy();

    //할인에 대한 부분을 잘 떼어내서 좋은 테스트가 가능한 것이다

    @Test
    // JUNIT5는 DisplayName을 사용하여 한글로 적을 수 있음
    @DisplayName("VIP는 10% 할인이 되어야 한다")
    void vip_o() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);
        //when
        int discount = rateDiscountPoilcy.discount(member, 10000);
        //then
        //간결하게 만들수 있도록 Static import 해준다 Assertions 찍고 알트 + 엔터
        //Assertions.assertThat(discount).isEqualTo(1000);
        assertThat(discount).isEqualTo(1000);
    }
    // 성공 테스트도 중요하지만 실패테스트도 중요하다
    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다")
    void vip_x(){
        //given
        Member member = new Member(2L, "memberBASIC", Grade.BASIC);
        //when
        int discount = rateDiscountPoilcy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(0);
    }

}