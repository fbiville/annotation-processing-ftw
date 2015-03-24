package fr.devoxx.niveau2.exo2.etape2;

import fr.devoxx.niveau2.Flag;

/**
 * Cow -
 *
 * @author Sébastien Lesaint
 */
@Flag
public class Cow {
  protected Color headColor = Color.WHITE;
  protected Color bodyColor = Color.BLACK;
  private final String name;
  @Deprecated
  int hornCount = 2;
  @Deprecated
  boolean extraFatInMilk = false;

  public Cow(String name) {
    this.name = name;
  }

  public static enum Color {
    WHITE, BLACK
  }
}
