package fr.devoxx.niveau2.exo2.etape1;

import java.util.List;
import java.util.Set;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import org.testng.annotations.Test;

/**
 * MethodsExplorerTest -
 *
 * @author Sébastien Lesaint
 */
public class MethodsExplorerTest extends DontLookAtThisClass_MethodsExplorerTest {
  @Test
  public void extractMethods() {
    ProcessorTask<List<ExecutableElement>> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new MethodsExplorer(elements, typeUtils).extractMethods(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractMethods));
  }

  @Test
  public void extractConstructors() {
    ProcessorTask<List<ExecutableElement>> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new MethodsExplorer(elements, typeUtils).extractConstructors(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractConstructors));
  }

  @Test
  public void extractPublicMethodsAndConstructors() {
    ProcessorTask<List<ExecutableElement>> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new MethodsExplorer(elements, typeUtils).extractPublicMethodsAndConstructors(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractPublicMethodsAndConstructors));
  }

  @Test
  public void extractPublicFunctions() {
    ProcessorTask<List<ExecutableElement>> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new MethodsExplorer(elements, typeUtils).extractPublicFunctions(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractPublicFunctions));
  }

  @Test
  public void extractEatDonutsMethods() {
    ProcessorTask<List<ExecutableElement>> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new MethodsExplorer(elements, typeUtils).extractEatDonutsMethods(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractEatDonutsMethods));
  }

  @Test
  public void extractCurrentEatDonutsMethod() {
    ProcessorTask<ExecutableElement> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new MethodsExplorer(elements, typeUtils).extractCurrentEatDonutsMethod(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractCurrentEatDonutsMethod));
  }

  @Test
  public void extractTypeOfNullableParameters() {
    ProcessorTask<Set<TypeMirror>> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new MethodsExplorer(elements, typeUtils).extractTypeOfNullableParameters(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractTypeOfNullableParameters));
  }

}