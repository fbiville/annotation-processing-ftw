package fr.devoxx.niveau3.exo3;

import org.assertj.core.api.AbstractAssert;

import javax.lang.model.element.Element;
import javax.lang.model.element.Name;

import static java.lang.String.format;

public class ElementAssert extends AbstractAssert<ElementAssert, Element> {

  protected ElementAssert(Element actual) {
    super(actual, ElementAssert.class);
  }

  public ElementAssert nameContentEquals(String name) {
    if (actual == null) {
      throw new AssertionError("Expecting actual to be not null");
    }
    if (name == null) {
      throw new AssertionError("Expecting simple name to be not null");
    }

    Name simpleName = actual.getSimpleName();
    if (!simpleName.contentEquals(name)) {
      throw new AssertionError(
        format("%n\tExpecting actual simple name to be equal to <%s>\tbut was <%s>.%n", name, simpleName)
      );
    }
    return this;
  }
}
