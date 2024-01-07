package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

    // DIP 위반된 코드 FixDiscount에서 RateDiscount로 바꾸게 되면 impl 소스를 수정해야 하기 때문
    // 운전면허증이 있으면 기름차를 타던 전기차를 타던 문제가 안되야 하는데 전기차로 바꾸는 순간 라이센스를 새로 발급받아야 하는 상황인것
    // final은 무조건 값이 할당되어야 한다 (기본으로 할당이 되던 생성자로 할당이 되던)
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 고정할인 금액 (고객사의 요청으로 할인정책이 변경되었음)
//    private final DiscountPolicy discountPolicy = new FixDiscountPoilcy();
    // 변경된 할인정책을 할당해주면 끝
//    private final DiscountPolicy discountPolicy = new RateDiscountPoilcy();

    // 철저히 DIP 원칙을 지키고 있음 세부적인 Class를 전혀 모르는 상태임
    private final MemberRepository memberRepository ;
    // 구현체가 없기 때문에 이렇게만 해두면 에러발생 nullpoint deception (null에 . 찍으면 나는 에러)
    private final DiscountPolicy discountPolicy;


    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // OrderService입장에서는 할인에 대해서는 모르겠다 discountPolicy니가 알아서 해줘 결과만 나한테 던져줘 이렇게 설계된 것
    // 단일 책임 원칙을 잘 지킨것
    // 할인에 대한 변경은 할인쪽만 고치면 되니까 (주문쪽 까지 고칠 필요가 없음)
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        //주문을 만들어서 반환을 해주면 orderService 역활은 끝난다.
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
