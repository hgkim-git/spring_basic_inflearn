package hello.core.scan.filter;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class ComponentFilterAppConfigTest {

  @Test
  void filterScan() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        ComponentFilterAppConfig.class);
    BeanA beanA = ac.getBean(BeanA.class);
    assertThat(beanA).isNotNull();

    Throwable throwable = Assertions.catchThrowable(() -> ac.getBean(BeanB.class));
    assertThat(throwable).isInstanceOf(NoSuchBeanDefinitionException.class);
  }


  @Configuration
  @ComponentScan(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
      excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class))
  public static class ComponentFilterAppConfig {

  }

}
