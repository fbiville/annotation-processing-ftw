package fr.devoxx.niveau2.exo1.etape3;

import java.io.Serializable;

import fr.devoxx.niveau2.exo1.Flag;

/**
 * MyListClass -
 *
 * @author Sébastien Lesaint
 */
@Flag
public class ImplementsInterfacesClass implements Serializable, Comparable<String> {
  @Override
  public int compareTo(String o) {
    throw new UnsupportedOperationException("Méthode non implémentée");
  }
}
