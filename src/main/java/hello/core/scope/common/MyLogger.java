package hello.core.scope.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.util.UUID;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
public class MyLogger {

  private String uuid;
  @Setter
  private String requestURL;

  public void log(String message) {
    System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + message);
  }

  @PostConstruct
  public void init() {
    uuid = UUID.randomUUID().toString();
    System.out.println("[" + uuid + "] init");
  }

  @PreDestroy
  public void destroy() {
    System.out.println("[" + uuid + "] destroy");
  }
}
