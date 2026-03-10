package hello.core.order;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

  MemberService memberService = new AppConfig().memberService();
  OrderService orderService = new AppConfig().orderService();


  @Test
  void createOrder() {
    Long memberId = 1L;
    Member member = new Member(memberId, "memberA", Grade.VIP);
    memberService.join(member);

    Order order = orderService.createOrder(memberId, "itemA", 10000);
    assertThat(order.calcPrice()).isEqualTo(9000);
  }
}