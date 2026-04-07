package hello.core.beanfind;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextSameBeanFindTest {

  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
      SameBeanConfig.class);

  @Test
  @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다")
  void findBeanByTypeDuplicate() {
    Throwable throwable = Assertions.catchThrowable(() -> ac.getBean(MemberRepository.class));
    assertThat(throwable).isInstanceOf(NoUniqueBeanDefinitionException.class);
  }

  @Test
  @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다")
  void findBeanByTypeDuplicate2() {
    MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
    assertThat(memberRepository).isInstanceOf(MemoryMemberRepository.class);
  }

  @Test
  @DisplayName("특정 타입을 모두 조회하기")
  void findAllBeansByType() {
    Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
    beansOfType.forEach((name, bean) -> System.out.println(name + " = " + bean));
  }

  @Configuration

  static class SameBeanConfig {

    @Bean
    MemberRepository memberRepository1() {
      return new MemoryMemberRepository();
    }

    @Bean
    MemberRepository memberRepository2() {
      return new MemoryMemberRepository();
    }


  }

}
