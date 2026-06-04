package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient {


  private String url;

  public NetworkClient() {
    System.out.println("생성자 호출, url = " + url);

  }

  public void setUrl(String url) {
    this.url = url;
  }

  // 서비스 시작 시 호출
  public void connect() {
    System.out.println("connect: " + url);
    if (url == null) {
      System.out.println("URL is not set. Cannot connect.");
    }
  }


  public void call(String message) {
    System.out.println("call: " + url + " message = " + message);
  }

  // 서비스 종료시 호출
  public void disconnect() {
    System.out.println("disconnect: " + url);
  }

  // 인터페이스 사용
//  @Override
//  // 의존 관계 주입이 끝나면 실행
//  public void afterPropertiesSet() throws Exception {
//    System.out.println("NetworkClient afterPropertiesSet");
//    connect();
//    call("초기화 연결 메세지");
//  }
//
//  @Override
//  // Bean 이 종료 될때 실행
//  public void destroy() throws Exception {
//    System.out.println("NetworkClient destroy");
//    disconnect();
//  }

  // @Bean 어노테이션에 초기화, 소멸 콜백 지정
//  public void init() throws Exception {
//    System.out.println("NetworkClient afterPropertiesSet");
//    connect();
//    call("초기화 연결 메세지");
//  }
//
//  public void close() throws Exception {
//    System.out.println("NetworkClient destroy");
//    disconnect();
//  }

  @PostConstruct
  public void init() throws Exception {
    System.out.println("NetworkClient afterPropertiesSet");
    connect();
    call("초기화 연결 메세지");
  }

  @PreDestroy
  public void close() throws Exception {
    System.out.println("NetworkClient destroy");
    disconnect();
  }
}
