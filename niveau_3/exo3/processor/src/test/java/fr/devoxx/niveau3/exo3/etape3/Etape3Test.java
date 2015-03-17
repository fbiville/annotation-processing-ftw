package fr.devoxx.niveau3.exo3.etape3;

import fr.devoxx.niveau3.exo3.ErbfeindGenerator;
import org.junit.Test;

import static com.google.common.truth.Truth.assert_;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;
import static fr.devoxx.niveau3.exo3.Sources.sourceFile;

public class Etape3Test {

  private ErbfeindGenerator generator = new ErbfeindGenerator();

  @Test
  public void at_last_erbfeind_generates_annotated_erbfeind_classes() {
    assert_().about(javaSource())
      .that(sourceFile("A"))
      .processedWith(generator)
      .compilesWithoutError()
      .and()
      .generatesSources(
        sourceFile("etape3/B"),
        sourceFile("etape3/C")
      );
  }
}
