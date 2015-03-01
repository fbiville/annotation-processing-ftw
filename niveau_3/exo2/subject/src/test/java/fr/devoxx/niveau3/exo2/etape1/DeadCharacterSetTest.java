package fr.devoxx.niveau3.exo2.etape1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.tools.JavaFileObject;

import com.google.testing.compile.JavaFileObjects;
import com.google.testing.compile.JavaSourcesSubjectFactory;
import fr.devoxx.niveau3.exo2.UndertakerProcessor;
import org.testng.annotations.Test;
import org.truth0.Truth;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DeadCharacterSetTest -
 *
 * @author Sébastien Lesaint
 */
public class DeadCharacterSetTest {
  @Test
  public void dead_character_set_should_contain_the_right_names() throws Exception {
    UndertakerProcessor undertakerProcessor = new UndertakerProcessor();

    Truth.ASSERT.about(JavaSourcesSubjectFactory.javaSources())
                .that(
                    loadClass(
                        "baratheon/Joffrey", "baratheon/Myrcella", "baratheon/Renly", "baratheon/Robert",
                        "baratheon/Stannis", "baratheon/Tommen"
                    )
                )
                .processedWith(undertakerProcessor)
                .compilesWithoutError();

    assertThat(undertakerProcessor.getDeadCharacterFirstNames()).containsOnly("Joffrey", "Robert", "Renly");
  }

  private List<JavaFileObject> loadClass(String... classNames) {
    return Arrays.asList(classNames)
        .stream()
        .map(className -> JavaFileObjects.forResource("fr/devoxx/niveau3/exo2/" + className + ".java"))
        .collect(Collectors.toList());
  }
}
