package hello.core.member;

public class MemberServiceImpl implements MemberService {

  private final MemberRepository repository;

  // 생성자 주입
  public MemberServiceImpl(MemberRepository repository) {
    this.repository = repository;
  }

  @Override
  public void join(Member member) {
    repository.save(member);
  }

  @Override
  public Member findMember(Long memberId) {
    return repository.findById(memberId);
  }
}
