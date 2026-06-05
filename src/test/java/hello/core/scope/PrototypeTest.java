package hello.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

  @Test
  void prototypeBeanFind() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        PrototypeBean.class);
    System.out.println("find prototypeBean");
    PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
    System.out.println("find prototypeBean2");
    PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
    assertThat(prototypeBean).isNotSameAs(prototypeBean2);
    ac.close();
  }

  @Scope("prototype")
  static class PrototypeBean {

    @PostConstruct
    public void init() {
      System.out.println("Prototype. init");
    }

    @PreDestroy
    public void destroy() {
      System.out.println("Prototype. destroy");
    }
  }

}
