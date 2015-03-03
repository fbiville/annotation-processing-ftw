package fr.devoxx.niveau3.exo1.etape1;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import com.google.common.collect.ImmutableList;

import com.beust.jcommander.internal.Sets;
import com.google.testing.compile.JavaFileObjects;
import com.google.testing.compile.JavaSourcesSubjectFactory;
import org.truth0.Truth;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ElementExploring -
 *
 * @author Sébastien Lesaint
 */
public class Etape1Test {
  @org.testng.annotations.Test
  public void extract_class_name() throws Exception {
    Etape1Processor processor = new Etape1Processor();
    Truth.ASSERT.about(JavaSourcesSubjectFactory.javaSources())
                .that(
                    ImmutableList.of(
                        JavaFileObjects.forSourceLines("ClassA",
                            "@fr.devoxx.niveau3.exo1.etape1.Flag",
                            "public class ClassA {",
                            "}"
                        ),
                        JavaFileObjects.forSourceLines("ClassB",
                            "public class ClassB {",
                            "}"
                        ),
                        JavaFileObjects.forSourceLines("ClassC",
                            "@fr.devoxx.niveau3.exo1.etape1.Flag",
                            "public class ClassC {",
                            "}"
                        )
                    )
                )
                .processedWith(processor)
                .compilesWithoutError();

    assertThat(processor.getFirstNames()).containsExactly("ClassA", "ClassC");
  }

  @SupportedAnnotationTypes("fr.devoxx.niveau3.exo1.etape1.Flag")
  private static class Etape1Processor extends AbstractProcessor {
    private final Set<String> firstNames = Sets.newHashSet();

    public Set<String> getFirstNames() {
      return firstNames;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
      firstNames.addAll(
        getFlaggedClassSimpleName(annotations, roundEnv)
      );
      return false;
    }

    private Collection<String> getFlaggedClassSimpleName(Set<? extends TypeElement> annotations,
                                                         RoundEnvironment roundEnv) {
      if (annotations.isEmpty()) {
        return Collections.emptyList();
      }

      TypeElement annotationTypeElement = annotations.iterator().next();

      return roundEnv.getElementsAnnotatedWith(annotationTypeElement)
                  .stream()
                  .map(Element::getSimpleName)
                  .map(Name::toString)
                  .collect(Collectors.toSet());
    }
  }
}
