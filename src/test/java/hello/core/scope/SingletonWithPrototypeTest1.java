package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest1 {

  @Test
  void singletonClientUsePrototype() {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        ClientBean.class,
        PrototypeBean.class);

    ClientBean clientBean = ac.getBean(ClientBean.class);
    int count1 = clientBean.logic();
    System.out.println("prototypeBean = " + clientBean.getPrototypeBean());
    Assertions.assertThat(count1).isEqualTo(1);

    ClientBean clientBean2 = ac.getBean(ClientBean.class);
    int count2 = clientBean2.logic();
    System.out.println("prototypeBean = " + clientBean2.getPrototypeBean());
//    Assertions.assertThat(count2).isEqualTo(2);
    // 원래의 의도
    Assertions.assertThat(count2).isEqualTo(1);

    ac.close();
  }

  @Scope("singleton")
  static class ClientBean {

    //    private final PrototypeBean prototypeBean;
    private PrototypeBean prototypeBean;

//    @Autowired
//    private ObjectProvider<PrototypeBean> springProvider;

    @Autowired
    private Provider<PrototypeBean> jsrProvider;

//    @Autowired
//    public ClientBean(PrototypeBean prototypeBean) {
//      this.prototypeBean = prototypeBean;
//    }

    public PrototypeBean getPrototypeBean() {
      return prototypeBean;
    }

    public int logic() {
//      prototypeBean = springProvider.getObject();
      prototypeBean = jsrProvider.get();
      prototypeBean.addCount();
      return prototypeBean.getCount();
    }

  }

  @Scope("prototype")
  static class PrototypeBean {

    private int count = 0;

    public void addCount() {
      count++;
    }

    public int getCount() {
      return count;
    }

    @PostConstruct
    void init() {
      System.out.println("PrototypeBean.init " + this);
    }

    @PreDestroy
    void destroy() {
      System.out.println("PrototypeBean.destroy " + this);
    }
  }
}
