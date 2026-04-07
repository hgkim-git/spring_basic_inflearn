package hello.core.beanfind;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class ApplicationContextExtendsFindTest {

  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

  @Test
  @DisplayName("부모 타입으로 조회시, 자식이 복수면 중복오류")
  void findBeanByParentTypeDuplicate() {
    Throwable e = catchThrowable(() -> ac.getBean(DiscountPolicy.class));
    assertThat(e).isInstanceOf(NoUniqueBeanDefinitionException.class);
  }

  @Test
  @DisplayName("이름을 지정해서 가져오면 가능")
  void findBeanByParentTypeAndNameDuplicate2() {
    DiscountPolicy discountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
    assertThat(discountPolicy).isInstanceOf(RateDiscountPolicy.class);
  }

  @Test
  @DisplayName("모든 자식 타입 가져오기")
  void findAllBeansByType() {
    Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
    // 개발할때 디버깅 용도로 볼 수는 있지만, 실무에서는 이렇게 출력하는 코드를 입력하면 안된다.
    // 실제 프로덕션 레벨에서는 테스트 코드가 너무 많기 때문에 이런 출력 코드를 일일이 볼 수 없음.
    // pass/fail 여부만으로 확인할 수 있게 테스트 코드를 작성.
    beansOfType.forEach((name, bean) -> System.out.println(name + " = " + bean));
  }

  static class TestConfig {

    @Bean
    public DiscountPolicy rateDiscountPolicy() {
      return new RateDiscountPolicy();
    }

    @Bean
    public DiscountPolicy fixDiscountPolicy() {
      return new FixDiscountPolicy();
    }

  }

}
