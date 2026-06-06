package hello.core.scope.web;

import hello.core.scope.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

//  private final ObjectProvider<MyLogger> loggerProvider;

  private final MyLogger myLogger;

  public void logic(String id) {
//    MyLogger myLogger = loggerProvider.getObject();
    myLogger.log("Service id = " + id);
  }
}
