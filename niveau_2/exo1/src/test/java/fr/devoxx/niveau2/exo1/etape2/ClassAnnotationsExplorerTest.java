package fr.devoxx.niveau2.exo1.etape2;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ClassAnnotationsExplorerTest -
 *
 * @author Sébastien Lesaint
 */
public class ClassAnnotationsExplorerTest extends DontLookAtThisClass_ClassAnnotationsExplorerTest {

  @Test
  public void extractAnnotations() {
    ClassAnnotationsExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractAnnotations()).isEqualTo(expectedExtractAnnotations(wrapper));
  }

  @Test
  public void extractMetaAnnotation() {
    ClassAnnotationsExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractMetaAnnotation()).isEqualTo(expectedExtractMetaAnnotation(wrapper));
  }

  @Test
  public void extractMetaAnnotationValue() throws Exception {
    ClassAnnotationsExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractMetaAnnotationValue()).isEqualTo(expectedExtractMetaAnnotationValue(wrapper));
  }

  @Test
  public void extractMetaAnnotationId() throws Exception {
    ClassAnnotationsExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractMetaAnnotationId()).isEqualTo(expectedExtractMetaAnnotationId(wrapper));
  }

}