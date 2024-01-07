package hello.core.beanFind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPoilcy;
import hello.core.discount.RateDiscountPoilcy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모타입으로 조회시 자식이 둘 이상있으면 중복오류가 난다")
    void findBeanByParentTypeDuplicate(){
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(DiscountPolicy.class));
    }
    @Test
    @DisplayName("부모타입으로 조회시 자식이 둘 이상있으면 빈 이름을 지정하면 된다")
    void findBeanByParentTypeBeanName(){
        DiscountPolicy discountPolicy = ac.getBean("rateDiscountPolicy",DiscountPolicy.class);
        assertThat(discountPolicy).isInstanceOf(RateDiscountPoilcy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType(){
        RateDiscountPoilcy bean = ac.getBean(RateDiscountPoilcy.class);
        assertThat(bean).isInstanceOf(RateDiscountPoilcy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조화")
    void findAllBeanByParentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);

        for (String key : beansOfType.keySet()) {
            // 공부용으로 출력문을 적어두긴 했으나 실제로 작업할 때에는 테스트 코드에 출력문은 되도록이면 남기지 않도록 한다
            // 시스템이 성공 실패를 결정해야 하는데 눈으로 보고있을순 없다 따라서 시스템상 테스트가 통과한다 안한다만 보면 되는 것
            System.out.println("key = " + key + "value = " + beansOfType.get(key));
        }
    }

    @Configuration
    static class TestConfig{
        @Bean
        // 타입을 DiscountPolicy말고 RateDisc... 등 구체적으로 해줘도 되지만 의존성 주입을 할 때에도 DiscountPolicy를 의존하고 있기
        // 때문에 DiscountPolicy로 해두는게 훨신 낫다
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPoilcy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPoilcy();
        }


    }
}
