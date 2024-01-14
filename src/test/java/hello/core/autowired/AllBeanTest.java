package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {

    @Test
    void findAllBean(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,DisCountService.class);

        DisCountService disCountService = ac.getBean(DisCountService.class);
        Member member = new Member(1L, "usrA", Grade.VIP);
        int disCountPrice = disCountService.discount(member, 10000, "fixDiscountPoilcy");

        assertThat(disCountService).isInstanceOf(DisCountService.class);
        assertThat(disCountPrice).isEqualTo(1000);

        int rateDisCountPrice = disCountService.discount(member, 20000, "rateDiscountPoilcy");
        assertThat(rateDisCountPrice).isEqualTo(2000);

    }

    static class DisCountService{
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        public DisCountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String disCountCode) {
            DiscountPolicy discountPolicy = policyMap.get(disCountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
