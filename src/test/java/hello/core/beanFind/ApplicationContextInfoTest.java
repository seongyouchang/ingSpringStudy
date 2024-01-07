package hello.core.beanFind;

import hello.core.AppConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//junit5부터는 public 설정 안해도 됨
class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        //iter + tab 하면 리스트나 배열이 있으면 For문을 자동으로 생성해준다
        for (String beanDefinitionName : beanDefinitionNames) {
            //Type을 지정안했기 때문에 Object로 꺼내진다
            Object bean = ac.getBean(beanDefinitionName);
            //soutm하고 엔터는 메소드 가져오기
            /*System.out.println("ApplicationContextInfoTest.findAllBean");*/
            //name이 key고 object가 value라고 보면 됨
            System.out.println("name = " + beanDefinitionName + "Object = " + bean);

            // 결과값은 스프링이 내부적으로 스프링 자체를 확장하기 위해서 쓰는 기반 bean들과 appconfig을 포함한 설정된 bean들이 같이 나옴
        }
    } @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            // bean하나하나에 대한 메타데이터 정보를 가져올 수 있음
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            // 내부 뭔가를 하기 위해서 등록한 빈들이 아니라 내가 이제 애플리케이션을 개발하기 위해서 등록한 빈들이 나온다
            // 아니면 외부 라이브러리 등
            if(beanDefinition.getRole()== BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + "Object = " + bean);
            }

            // 결과값은 현재 내가 개발을 위해 등록한 bean들만 나온다
            // Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            // Role ROLE_INFRASTRUCTURE: 내부에서 사용하는 빈
        }
    }
}
