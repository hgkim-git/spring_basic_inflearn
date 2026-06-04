package hello.core.autowired;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AllBeanTest {

  @Test
  void findAllBean() {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
        AutoAppConfig.class,
        DiscountService.class);

    DiscountService discountService = applicationContext.getBean(DiscountService.class);
    Member memberA = new Member(1L, "memberA", Grade.VIP);
    int fixedDiscountPolicy = discountService.discount(memberA, 10000, "fixDiscountPolicy");

    assertThat(discountService).isInstanceOf(DiscountService.class);
    assertThat(fixedDiscountPolicy).isEqualTo(1000);

    int rateDiscountPolicy = discountService.discount(memberA, 20000, "rateDiscountPolicy");
    assertThat(rateDiscountPolicy).isEqualTo(2000);

  }

  static class DiscountService {

    private final Map<String, DiscountPolicy> policiyMap;
    private final List<DiscountPolicy> policiyList;

    DiscountService(Map<String, DiscountPolicy> policiyMap,
        List<DiscountPolicy> policiyList) {
      this.policiyMap = policiyMap;
      this.policiyList = policiyList;
      System.out.println("policiyMap = " + policiyMap);
      System.out.println("policiyList = " + policiyList);
    }

    public int discount(Member memberA, int price, String discountCode) {
      DiscountPolicy discountPolicy = policiyMap.get(discountCode);
      if (discountPolicy != null) {
        return discountPolicy.discount(memberA, price);
      }
      return 0;
    }
  }
}
