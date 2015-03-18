package fr.devoxx.niveau2.exo1.etape2;

import java.util.List;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import org.testng.annotations.Test;

/**
 * ClassAnnotationsExplorerTest -
 *
 * @author Sébastien Lesaint
 */
public class ClassAnnotationsExplorerTest extends DontLookAtThisClass_ClassAnnotationsExplorerTest {

  @Test
  public void extractAnnotations() {
    ProcessorTask<List<? extends AnnotationMirror>> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new ClassAnnotationsExplorer(elements, typeUtils).extractAnnotations(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractAnnotations));
  }

  @Test
  public void extractMetaAnnotation() {
    ProcessorTask<Meta> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new ClassAnnotationsExplorer(elements, typeUtils).extractMetaAnnotation(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractMetaAnnotation));
  }

  @Test
  public void extractMetaAnnotationValue() throws Exception {
    ProcessorTask<String> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new ClassAnnotationsExplorer(elements, typeUtils).extractMetaAnnotationValue(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractMetaAnnotationValue));
  }

  @Test
  public void extractMetaAnnotationId() throws Exception {
    ProcessorTask<Integer> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new ClassAnnotationsExplorer(elements, typeUtils).extractMetaAnnotationId(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractMetaAnnotationId));
  }

}