package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService stateful1 = ac.getBean(StatefulService.class);
        StatefulService stateful2 = ac.getBean(StatefulService.class);

        //ThreadA: 사용자1 10000원 주문
//        stateful1.order("user1", 10000);
        int userPrice1 = stateful1.order("user1", 10000);
        //ThreadB: 사용자2 20000원 주문
//        stateful2.order("user2", 20000);
        int userPrice2 = stateful2.order("user2", 20000);

        //사용자1 주문금액 조회
//        int price = stateful1.getPrice();
        System.out.println("price = " + userPrice1);

//        Assertions.assertThat(stateful1.getPrice()).isEqualTo("20000");
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}