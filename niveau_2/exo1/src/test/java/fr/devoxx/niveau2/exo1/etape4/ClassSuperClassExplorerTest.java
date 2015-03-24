package fr.devoxx.niveau2.exo1.etape4;

import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import org.testng.annotations.Test;

/**
 * ClassSuperClassExplorerTest -
 *
 * @author Sébastien Lesaint
 */
public class ClassSuperClassExplorerTest extends DontLookAtThisClass_ClassSuperClassExplorerTest {

  @Test
  public void extractSuperClass() {
    ProcessorTask<TypeMirror> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new ClassSuperClassExplorer(elements, typeUtils).extractSuperClass(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractSuperClass));
  }

  @Test
  public void asDeclaredType() {
    ProcessorTask<TypeMirror> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new ClassSuperClassExplorer(elements, typeUtils).asDeclaredType(typeElement.getSuperclass())
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedAsDeclaredType));
  }

  @Test
  public void extractSuperClassTypeArgument() {
    ProcessorTask<TypeMirror> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new ClassSuperClassExplorer(elements, typeUtils).extractSuperClassTypeArgument(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractSuperClassTypeArgument));
  }

  @Test
  public void extractSuperClassTypeParameterName() {
    ProcessorTask<Name> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new ClassSuperClassExplorer(elements, typeUtils).extractSuperClassTypeParameterName(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractSuperClassTypeParameterName));
  }

}