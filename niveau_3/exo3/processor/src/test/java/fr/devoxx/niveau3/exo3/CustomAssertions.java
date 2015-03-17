package fr.devoxx.niveau3.exo3;

import org.assertj.core.api.Assertions;

import javax.lang.model.element.Element;

public class CustomAssertions extends Assertions {

  public static ElementAssert assertThat(Element element) {
    return new ElementAssert(element);
  }
}
