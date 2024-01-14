package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// Component Annotations가 붙은 클래스를 다 찾는다
@ComponentScan(
        // 기존에 만들었던 AppConfig Class는 수동으로 생성하도록 되어있기 떄문에 충돌 날 수있음 따라서 Configuration이 붙은 클래스를
        // 제외하겠다는 코드 (실무에서는 설정정보를 제외하진 않으나 예제 코드를 보존하기 위해서 이방법은 사용한다)
        // Configuration을 들어가보면 @Component를 사용하고 있기 때문
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // 어디서 부터 찾을지 지정할 수 있음 (member하위 아래에서만 조회한다)
        // 모든 자바코드 라이브러리 내부를 다 뒤질수도 있음 지정되지 않을 경우
        // 지정되지 않으면 ComponentScan가 시작되는 하위 Class를 다 뒤진다
//        basePackages = "hello.core.member"
        // Class도 지정가능함
//        basePackageClasses = AutoAppConfig.class
)
public class AutoAppConfig {

}
