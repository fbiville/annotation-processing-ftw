package fr.devoxx.niveau2.exo2.etape3;

import java.util.List;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import org.testng.annotations.Test;

/**
 * DeepExplorerTest -
 *
 * @author Sébastien Lesaint
 */
public class DeepExplorerTest extends DontLookAtThisClass_DeepExplorerTest {
  @Test
  public void extractAllMethods() {
    ProcessorTask<List<ExecutableElement>> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new DeepExplorer(elements, typeUtils).extractAllMethods(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractAllMethods));
  }

  @Test
  public void extractInheritedMethods() {
    ProcessorTask<List<ExecutableElement>> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new DeepExplorer(elements, typeUtils).extractInheritedMethods(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractInheritedMethods));
  }

  @Test
  public void extractInheritedMethodsButObjects() {
    ProcessorTask<List<ExecutableElement>> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new DeepExplorer(elements, typeUtils).extractInheritedMethodsButObjects(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractInheritedMethodsButObjects));
  }

}