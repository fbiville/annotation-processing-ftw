package fr.devoxx.niveau2.exo2.etape2;

import java.util.List;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;

import fr.devoxx.niveau2.exo2.DontLookAtThisClass;

import static java.util.stream.Collectors.toList;

/**
 * DontLookAtThisClass_FieldsExplorerTest -
 *
 * @author Sébastien Lesaint
 */
abstract class DontLookAtThisClass_FieldsExplorerTest extends DontLookAtThisClass {

  public DontLookAtThisClass_FieldsExplorerTest() {
    super("Cow");
  }

  protected List<VariableElement> expectedExtractFields(TypeElement typeElement) {
    return ElementFilter.fieldsIn(typeElement.getEnclosedElements());
  }

  protected TypeMirror expectedExtractNameFieldType(TypeElement typeElement) {
    return expectedExtractFields(typeElement)
        .stream()
        .filter(field -> field.getSimpleName().contentEquals("name"))
        .map(field -> field.asType())
        .findFirst()
        .get();
  }

  protected List<VariableElement> expectedExtractPrivateAndPackageProtectedFields(TypeElement typeElement) {
    return expectedExtractFields(typeElement)
        .stream()
        .filter(field -> field.getModifiers().isEmpty() || field.getModifiers().contains(Modifier.PRIVATE))
        .collect(toList());
  }

  protected List<VariableElement> expectedExtractDeprecatedField(TypeElement typeElement) {
    return expectedExtractFields(typeElement)
        .stream()
        .filter(field -> field.getAnnotation(Deprecated.class) != null)
        .collect(toList());
  }

}
