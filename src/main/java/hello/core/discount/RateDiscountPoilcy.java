package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
public class RateDiscountPoilcy implements DiscountPolicy{
    // 단축키 컨트롤 + 쉬프트 + T -> 빠른 테스트 패키지 + 클래스 생성
    private int discountPersent = 10;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPersent / 100;
        } else {
            return 0;
        }
    }
}
