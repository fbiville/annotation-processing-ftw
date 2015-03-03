package fr.devoxx.niveau3.exo1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CrocMortTest -
 *
 * @author Sébastien Lesaint
 */
public class PersonnagesMortsTest {

  private static final String PERSONNAGES_MORTS_TXT_FILENAME = "personnages_morts.txt";

  @Test
  public void name() throws Exception {
    assertThat(personnagesMorts()).containsOnly(
        "Joffrey", "Robert", "Renly",
        "Eddard", "Robb"
    );
  }

  private Set<String> personnagesMorts() {
    InputStream inputStream = getClass().getResourceAsStream(PERSONNAGES_MORTS_TXT_FILENAME);
    if (inputStream == null) {
      System.err.println(PERSONNAGES_MORTS_TXT_FILENAME + " not found.");
      return Collections.emptySet();
    }

    try (Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines()) {
      return lines.collect(Collectors.toSet());
    }
  }
}
