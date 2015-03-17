package fr.devoxx.niveau3.exo3.etape2;

import fr.devoxx.niveau3.exo3.ErbfeindGenerator;
import org.junit.Ignore;
import org.junit.Test;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.truth.Truth.assert_;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;
import static fr.devoxx.niveau3.exo3.CustomAssertions.assertThat;
import static fr.devoxx.niveau3.exo3.Sources.sourceFile;
import static org.mockito.Mockito.*;

public class Etape2Test {

  private ErbfeindGenerator generator = new ErbfeindGenerator();

  @Test
  public void generator_extracts_class_elements() {
    TypeElement annotation = annotation();
    Set<? extends Element> classes = classElement("SuperCleverClassName");
    RoundEnvironment roundEnvironment = roundEnvironmentIncluding(annotation, classes);

    Collection<Element> elements = generator.extractClassElements(asSet(annotation), roundEnvironment);

    assertThat(elements).hasSize(1);
    assertThat(elements.iterator().next()).nameContentEquals("SuperCleverClassName");
  }

  @Test
  @Ignore
  public void at_last_erbfeind_generates_simple_erbfeind_classes() {
    assert_().about(javaSource())
      .that(sourceFile("A"))
      .processedWith(generator)
      .compilesWithoutError()
      .and()
      .generatesSources(
        sourceFile("etape2/B"),
        sourceFile("etape2/C")
      );
  }

  private static TypeElement annotation() {
    return mock(TypeElement.class);
  }

  private static Set<? extends Element> classElement(String className) {
    Element element = mock(Element.class);
    Name name = name(className);
    when(element.getSimpleName()).thenReturn(name);
    return asSet(element);
  }

  private static RoundEnvironment roundEnvironmentIncluding(TypeElement annotation, Set<? extends Element> elements) {
    RoundEnvironment roundEnvironment = mock(RoundEnvironment.class);
    doReturn(elements).when(roundEnvironment).getElementsAnnotatedWith(annotation);
    return roundEnvironment;
  }

  private static Name name(String elementName) {
    Name name = mock(Name.class);
    when(name.contentEquals(elementName)).thenReturn(true);
    when(name.toString()).thenReturn(elementName);
    return name;
  }

  private static <T> Set<? extends T> asSet(T item) {
    Set<T> items = new HashSet<>();
    items.add(item);
    return items;
  }

}
