package hello.core.beanFind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findNameByName(){
        // memberService가 memberServiceImpl의 인스턴스면 성공
        // name 파라미터를 제거하면 type으로만 검사해볼 수 있다
        // MerberService말고 impl로도 (구체타입) 조회해볼 수 있다 memberService의 return값으로 가져오기 때문에 그러나
        // 그렇게 하면 구현에 의존하게 되며 역활과 구현을 분리하는것이 이상적이기 때문에 좋은 코드는 아니다
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        // Assertions에 alt+ enter 해서 static으로 import 시켜줄 수 있음
        //Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈이 없을 때")
    void findBeanByNameX(){
        assertThrows(NoSuchBeanDefinitionException.class,
                ()-> ac.getBean("xxxxxx", MemberService.class));

    }
}
