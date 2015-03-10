package fr.devoxx.niveau2.exo2.etape3;

import java.util.List;
import java.util.stream.Collectors;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;

import fr.devoxx.niveau2.exo2.DontLookAtThisClass;

/**
 * DontLookAtThisClass_FieldsExplorerTest -
 *
 * @author Sébastien Lesaint
 */
abstract class DontLookAtThisClass_DeepExplorerTest extends DontLookAtThisClass {

  DontLookAtThisClass_DeepExplorerTest() {
    super("Employee");
  }

  protected List<ExecutableElement> expectedExtractAllMethods(Elements elements, TypeElement typeElement) {
    return ElementFilter.methodsIn(elements.getAllMembers(typeElement));
  }

  protected List<ExecutableElement> expectedExtractInheritedMethods(Elements elements, TypeElement typeElement) {
    return elements.getAllMembers(typeElement)
                   .stream()
                   .filter(executableElement -> executableElement.getKind() == ElementKind.METHOD)
                   .filter(executableElement -> executableElement.getEnclosingElement() != typeElement)
                   .map(ExecutableElement.class::cast)
                   .collect(Collectors.toList());
  }

  protected List<ExecutableElement> expectedExtractInheritedMethodsButObjects(
      Elements elements, TypeElement typeElement) {
    return expectedExtractInheritedMethods(elements, typeElement)
        .stream()
        .filter(
            executableElement -> {
              TypeElement objectTypeElement = elements.getTypeElement("java.lang.Object");
              return !executableElement.getEnclosingElement().equals(objectTypeElement);
            }
        )
        .map(ExecutableElement.class::cast)
        .collect(Collectors.toList());
  }
}
