package fr.devoxx.niveau2.exo2.etape1;

import javax.annotation.Nullable;

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

  public double getWeight() {
    return Double.MAX_VALUE;
  }

  @Deprecated
  public boolean eatDonuts(int count) {
    return true;
  }

  public boolean eatDonuts(int count, @Nullable Flavor flavor) {
    return true;
  }

  private void strangleBart(@Nullable String blame) {

  }

  private void sleepOnCouch() {
    // always !
  }

  public static enum Flavor {
    CHOCOLAT, STRAWBERRY;
  }

}
