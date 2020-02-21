package warikan.domain.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import warikan.domain.model.members.Member;
import warikan.domain.model.members.MemberName;

public class PartyTest {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Test
  public void expectParty() {
    PartyName partyname = PartyName.of("TEST");

    System.out.println(partyname);
    assertTrue(partyname.asString() == "TEST");
  }

  @Test
  public void 支払い割合設定() {
    Party party = new Party(PartyName.of("割合"));
    System.out.println(party);
    party.傾斜(Rate.of(120), Rate.of(100), Rate.of(80));
  }

  @Test
  public void メンバーを追加できる() {
    Party party = new Party(PartyName.of("メンバー追加"));
    System.out.println(party);
    assertThat(party.memberSize(), is(0));
    party.addMember(Member.of(MemberName.of("杉森")), PaymentType.普通);
    party.addMember(Member.of(MemberName.of("西")), PaymentType.多め);
    party.addMember(Member.of(MemberName.of("鈴木")), PaymentType.少なめ);
    assertThat(party.memberSize(), is(3));
  }

  @Test
  public void 均等割り20000円() {
    Party party = new Party(PartyName.of("均等割り"));
    party.傾斜(Rate.of(110), Rate.of(100), Rate.of(80));
    party.addMember(Member.of(MemberName.of("杉森")), PaymentType.普通);
    party.addMember(Member.of(MemberName.of("西")), PaymentType.普通);
    party.addMember(Member.of(MemberName.of("鈴木")), PaymentType.普通);
    party.請求(Money.of(20000, Money.JPY));
    party.傾斜合計();
    System.out.println(party.支払金額());
  }

  @Test
  public void 請求割合の合計() {
    Party party = new Party(PartyName.of("割合合計"));
    party.傾斜(Rate.of(110), Rate.of(100), Rate.of(80));
    party.addMember(Member.of(MemberName.of("杉森")), PaymentType.普通);
    party.addMember(Member.of(MemberName.of("西")), PaymentType.多め);
    party.addMember(Member.of(MemberName.of("鈴木")), PaymentType.少なめ);

    assertThat(party.傾斜合計().get(), is(290));
  }
}
