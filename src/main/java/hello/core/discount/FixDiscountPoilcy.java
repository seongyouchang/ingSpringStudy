package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPoilcy implements DiscountPolicy{

    private int discountFixAmount = 1000; //1000원 할인

    // Vip면 1000원 할인 아니면 할인 없음
    @Override
    public int discount(Member member, int price) {
        //enum type은 == 쓰는게 맞다
        if(member.getGrade() == Grade.VIP){
            return discountFixAmount;
        }else{
            return 0;
        }
    }
}
