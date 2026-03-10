package hello.core.member;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.AppConfig;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

  MemberService memberService = new AppConfig().memberService();

  @Test
  void join() {
    // given
    Member member = new Member(1L, "memberA", Grade.VIP);
    // when
    memberService.join(member);
    Member found = memberService.findMember(1L);

    // then
    assertThat(member).isEqualTo(found);
  }
}