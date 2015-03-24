package fr.devoxx.niveau2.exo2.etape3;

import fr.devoxx.niveau2.Flag;

/**
 * Employee -
 *
 * @author Sébastien Lesaint
 */
@Flag
public class Employee extends Citizen {
  @Override
  public String sayHi() {
    return "Good morning, sir!";
  }
}
