package warikan.domain.model;

import java.util.HashMap;
import javax.annotation.Nonnull;
import warikan.domain.model.members.Member;

public final class 名簿 {

  private final HashMap<Member, PaymentType> meibo = new HashMap<>();
  private Rate rateall;
  private HashMap<PaymentType, Money> warikan = new HashMap<>();

  public 名簿() {}

  public void addMember(@Nonnull Member member, @Nonnull PaymentType paymentType) {
    meibo.put(member, paymentType);
  }

  public void delMember(@Nonnull Member member) {
    meibo.remove(member);
  }

  public int memberSize() {
    return meibo.size();
  }

  public Rate 傾斜合計(HashMap<PaymentType, Rate> rate) {
    this.rateall =
        Rate.of(meibo.values().stream().map(x -> rate.get(x)).mapToInt(x -> x.get()).sum());
    return this.rateall;
  }

  public HashMap<PaymentType, Money> 支払金額(HashMap<PaymentType, Rate> rate, Money 請求金額) {
    rate.forEach((p, r) -> warikan.put(p, 請求金額.times((double) r.get() / this.rateall.get())));
    return warikan;
  }
}
