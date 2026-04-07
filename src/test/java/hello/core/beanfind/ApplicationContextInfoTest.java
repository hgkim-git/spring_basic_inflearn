package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

  AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
      AppConfig.class);

  @Test
  @DisplayName("모든 Bean 출력하기")
  void findAllBeans() {
    String[] beanNames = context.getBeanDefinitionNames();
    for (String beanName : beanNames) {
      Object bean = context.getBean(beanName);
      System.out.println(beanName + " = " + bean);
    }
  }

  @Test
  @DisplayName("애플리케이션 빈 출력하기")
  void findApplicationBeans() {
    String[] beanNames = context.getBeanDefinitionNames();
    for (String beanName : beanNames) {
      BeanDefinition beanDefinition = context.getBeanDefinition(beanName);
      Object bean = context.getBean(beanName);
      // BeanDefinition.ROLE_APPLICATION : 직접 등록한 내 애플리케이션 빈
      if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
        System.out.println(beanName + " = " + bean);
      }
    }
    System.out.println("=========================");
    for (String beanName : beanNames) {
      BeanDefinition beanDefinition = context.getBeanDefinition(beanName);
      Object bean = context.getBean(beanName);
      // BeanDefinition.ROLE_APPLICATION :  스프링 내부에서 사용하는 빈
      if (beanDefinition.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE) {
        System.out.println(beanName + " = " + bean);
      }
    }
  }

}
