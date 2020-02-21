package warikan.domain.model;

import javax.annotation.Nonnull;

public final class Rate {
  private int value; // 100%

  private Rate(@Nonnull int value) {
    this.value = value;
  }

  public static Rate of(@Nonnull int value) {
    return new Rate(value);
  }

  public int get() {
    return value;
  }
}
