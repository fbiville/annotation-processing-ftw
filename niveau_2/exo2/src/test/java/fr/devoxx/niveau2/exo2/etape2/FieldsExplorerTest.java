package fr.devoxx.niveau2.exo2.etape2;

import java.util.List;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import org.testng.annotations.Test;

/**
 * FieldsExplorerTest -
 *
 * @author Sébastien Lesaint
 */
public class FieldsExplorerTest extends DontLookAtThisClass_FieldsExplorerTest {
  @Test
  public void extractFields() {
    ProcessorTask<List<VariableElement>> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new FieldsExplorer(elements, typeUtils).extractFields(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractFields));
  }

  @Test
  public void extractNameFieldType() {
    ProcessorTask<TypeMirror> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new FieldsExplorer(elements, typeUtils).extractNameFieldType(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractNameFieldType));
  }

  @Test
  public void extractPrivateAndPackageProtectedFields() {
    ProcessorTask<List<VariableElement>> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new FieldsExplorer(elements, typeUtils).extractPrivateAndPackageProtectedFields(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractPrivateAndPackageProtectedFields));
  }

  @Test
  public void extractDeprecatedField() {
    ProcessorTask<List<VariableElement>> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new FieldsExplorer(elements, typeUtils).extractDeprecatedField(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractDeprecatedField));
  }

}