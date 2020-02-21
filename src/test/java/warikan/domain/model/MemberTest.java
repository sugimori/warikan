package warikan.domain.model;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import warikan.domain.model.members.Member;
import warikan.domain.model.members.MemberName;

public class MemberTest {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Test
  public void expectMember() {
    Member member = Member.of(MemberName.of("杉森"));

    System.out.println(member);
    //    assertTrue(member.name() == "杉森");
  }
}
