package fr.devoxx.niveau2.exo2.etape1;

import java.util.Objects;
import javax.annotation.Nonnull;

import fr.devoxx.niveau2.Flag;

/**
 * Homer -
 *
 * @author Sébastien Lesaint
 */
@Flag
public class Homer {
  private final boolean wearingPants;

  public Homer(boolean wearingPants) {
    this.wearingPants = wearingPants;
  }

  public String speak() {
    return "Doh !";
  }

  public double getWeight() {
    return Double.MAX_VALUE;
  }

  @Deprecated
  public boolean eatDonuts(int count) {
    return true;
  }

  public boolean eatDonuts(int count, @Nonnull Flavor flavor) {
    Objects.requireNonNull(flavor);
    return true;
  }

  private void sleepOnCouch() {
    // always !
  }

  public static enum Flavor {
    CHOCOLAT, STRAWBERRY;
  }

}
