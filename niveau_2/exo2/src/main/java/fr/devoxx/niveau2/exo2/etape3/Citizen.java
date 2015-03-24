package fr.devoxx.niveau2.exo2.etape3;

/**
 * Citizen -
 *
 * @author Sébastien Lesaint
 */
public class Citizen implements Person {
  @Override
  public String sayHi() {
    return "Hi!";
  }

  @Override
  public String sayGoodbye() {
    return "Goodbye!";
  }

}
