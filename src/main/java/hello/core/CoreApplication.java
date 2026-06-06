package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Web 관련 Bean Scope 실습용
@SpringBootApplication(scanBasePackages = "hello.core.scope")
//@SpringBootApplication
public class CoreApplication {

  public static void main(String[] args) {
    SpringApplication.run(CoreApplication.class, args);
  }

}
