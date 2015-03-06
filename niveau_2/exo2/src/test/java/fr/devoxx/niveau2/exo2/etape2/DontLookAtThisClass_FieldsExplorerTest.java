package fr.devoxx.niveau2.exo2.etape2;

import java.util.List;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;

import fr.devoxx.niveau2.exo2.DontLookAtThisClass;

import static java.util.stream.Collectors.toList;

/**
 * DontLookAtThisClass_MethodsExplorerTest -
 *
 * @author Sébastien Lesaint
 */
abstract class DontLookAtThisClass_FieldsExplorerTest
    extends DontLookAtThisClass<DontLookAtThisClass_FieldsExplorerTest.FieldsExplorerWrapper> {

  protected FieldsExplorerWrapper createWrapperAndProcess() {
    return processWithWrapper(new FieldsExplorerWrapper(), "Cow");
  }

  protected List<VariableElement> expectedExtractFields(FieldsExplorerWrapper wrapper) {
    return ElementFilter.fieldsIn(wrapper.getTypeElement().getEnclosedElements());
  }

  protected TypeMirror expectedExtractNameFieldType(FieldsExplorerWrapper wrapper) {
    return expectedExtractFields(wrapper)
        .stream()
        .filter(field -> field.getSimpleName().contentEquals("name"))
        .map(field -> field.asType())
        .findFirst()
        .get();
  }

  protected List<VariableElement> expectedExtractPrivateAndPackageProtectedFields(FieldsExplorerWrapper wrapper) {
    return expectedExtractFields(wrapper)
        .stream()
        .filter(field -> field.getModifiers().isEmpty() || field.getModifiers().contains(Modifier.PRIVATE))
        .collect(toList());
  }

  protected List<VariableElement> expectedExtractDeprecatedField(FieldsExplorerWrapper wrapper) {
    return expectedExtractFields(wrapper)
        .stream()
        .filter(field -> field.getAnnotation(Deprecated.class) != null)
        .collect(toList());
  }

  protected static class FieldsExplorerWrapper extends DontLookAtThisClass.AbstractWrapperProcessor {

    private FieldsExplorer fieldsExplorer;

    @Override
    public void init(ProcessingEnvironment processingEnv) {
      super.init(processingEnv);
      this.fieldsExplorer = new FieldsExplorer(getElementUtils(), getTypeUtils());
    }

    ///////////////////////////////////////////
    // FieldsExplorer delegated methods
    ///////////////////////////////////////////

    public List<VariableElement> extractFields() {
      return fieldsExplorer.extractFields(getTypeElement());
    }

    public TypeMirror extractNameFieldType() {
      return fieldsExplorer.extractNameFieldType(getTypeElement());
    }

    public List<VariableElement> extractPrivateAndPackageProtectedFields() {
      return fieldsExplorer.extractPrivateAndPackageProtectedFields(getTypeElement());
    }

    public List<VariableElement> extractDeprecatedField() {
      return fieldsExplorer.extractDeprecatedField(getTypeElement());
    }
  }
}
