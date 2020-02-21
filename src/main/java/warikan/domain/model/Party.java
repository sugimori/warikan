package warikan.domain.model;

import java.util.HashMap;
import javax.annotation.Nonnull;
import warikan.domain.model.members.Member;

public final class Party {
  private final PartyName name;
  private final 名簿 meibo = new 名簿();
  private Money 請求金額;
  private HashMap<PaymentType, Rate> rate = new HashMap<>();

  public Party(@Nonnull PartyName name) {
    this.name = name;
    傾斜(Rate.of(100), Rate.of(100), Rate.of(100));
  }

  public void addMember(@Nonnull Member member, @Nonnull PaymentType paymentType) {
    meibo.addMember(member, paymentType);
  }

  public void delMember(@Nonnull Member member) {
    meibo.delMember(member);
  }

  public void 請求(Money 請求金額) {
    this.請求金額 = 請求金額;
  }

  public void 傾斜(Rate learge, Rate normal, Rate small) {
    rate.put(PaymentType.多め, learge);
    rate.put(PaymentType.普通, normal);
    rate.put(PaymentType.少なめ, small);
  }

  public Rate 傾斜合計() {
    return meibo.傾斜合計(rate);
  }

  public int memberSize() {
    return meibo.memberSize();
  }

  public HashMap<PaymentType, Money> 支払金額() {
    return meibo.支払金額(rate, this.請求金額);
  }
}
