package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {


  @Test
  public void lifeCycleTest() {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
        LifeCycleConfig.class);
    NetworkClient networkClient = applicationContext.getBean(NetworkClient.class);
    applicationContext.close();
  }


  @Configuration
  static class LifeCycleConfig {

     @Bean
//    @Bean(initMethod = "init", destroyMethod = "close")
    public NetworkClient networkClient() {
      NetworkClient networkClient = new NetworkClient();
      networkClient.setUrl("http://hello-spring.dev");
      return networkClient;
    }
  }

}
