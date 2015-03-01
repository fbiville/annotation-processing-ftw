package fr.devoxx.niveau3.exo2.etape2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.devoxx.niveau3.exo2.UndertakerProcessor;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CrocMortTest -
 *
 * @author Sébastien Lesaint
 */
public class DeadCharacterFileTest {

  private static final String DEAD_CHARACTERS_TXT_FILENAME = "dead_characters.txt";

  @Test
  public void deadCharacterFile_should_exist_and_contain_the_right_names() throws Exception {
    assertThat(readDeadCharactersTxt()).containsOnly(
        "Joffrey", "Robert", "Renly",
        "Eddard", "Robb"
    );
  }

  private Set<String> readDeadCharactersTxt() {
    InputStream inputStream = UndertakerProcessor.class.getResourceAsStream(DEAD_CHARACTERS_TXT_FILENAME);
    if (inputStream == null) {
      System.err.println(DEAD_CHARACTERS_TXT_FILENAME + " not found.");
      return Collections.emptySet();
    }

    try (Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines()) {
      return lines.collect(Collectors.toSet());
    }
  }
}
