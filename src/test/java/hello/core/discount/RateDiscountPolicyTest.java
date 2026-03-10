package hello.core.discount;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {

  DiscountPolicy discountPolicy = new RateDiscountPolicy();
  Member vip;
  Member basic;

  @BeforeEach
  void setUp() {
    vip = new Member(1L, "memberA", Grade.VIP);
    basic = new Member(2L, "memberB", Grade.BASIC);
  }

  @Test
  void vip_o() {
    int discountPrice = discountPolicy.discount(vip, 10000);
    assertThat(discountPrice).isEqualTo(1000);
  }

  @Test
  void basic_o() {
    int discountPrice = discountPolicy.discount(basic, 10000);
    assertThat(discountPrice).isEqualTo(0);
  }
}