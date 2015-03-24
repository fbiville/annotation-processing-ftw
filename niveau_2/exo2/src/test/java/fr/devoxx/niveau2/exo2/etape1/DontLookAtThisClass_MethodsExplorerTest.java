package fr.devoxx.niveau2.exo2.etape1;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;

import fr.devoxx.niveau2.exo2.DontLookAtThisClass;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * DontLookAtThisClass_MethodsExplorerTest -
 *
 * @author Sébastien Lesaint
 */
abstract class DontLookAtThisClass_MethodsExplorerTest extends DontLookAtThisClass {

  public DontLookAtThisClass_MethodsExplorerTest() {
    super("Homer");
  }

  protected List<ExecutableElement> expectedExtractMethods(TypeElement typeElement) {
    return ElementFilter.methodsIn(typeElement.getEnclosedElements());
  }

  protected List<ExecutableElement> expectedExtractConstructors(TypeElement typeElement) {
    return ElementFilter.constructorsIn(typeElement.getEnclosedElements());
  }

  protected List<ExecutableElement> expectedExtractPublicMethodsAndConstructors(TypeElement typeElement) {
    return typeElement.getEnclosedElements()
                      .stream()
                      .filter(s -> s.getModifiers().contains(Modifier.PUBLIC))
                      .map(this::asExecutableElement)
                      .filter(Objects::nonNull)
                      .collect(toList());
  }

  protected List<ExecutableElement> expectedExtractPublicFunctions(TypeElement typeElement) {
    return expectedExtractPublicMethodsAndConstructors(typeElement)
        .stream()
        .filter(s -> s.getReturnType().getKind() != TypeKind.VOID)
        .collect(toList());
  }

  protected List<ExecutableElement> expectedExtractEatDonutsMethods(TypeElement typeElement) {
    return typeElement.getEnclosedElements()
                      .stream()
                      .map(this::asExecutableElement)
                      .filter(Objects::nonNull)
                      .filter(s -> s.getSimpleName().contentEquals("eatDonuts"))
                      .collect(toList());
  }

  protected ExecutableElement expectedExtractCurrentEatDonutsMethod(TypeElement typeElement) {
    return expectedExtractEatDonutsMethods(typeElement)
        .stream()
        .filter(s -> s.getAnnotation(Deprecated.class) == null)
        .findFirst()
        .get();
  }

  protected Set<TypeMirror> expectedExtractTypeOfNullableParameters(TypeElement typeElement) {
    return typeElement.getEnclosedElements()
                      .stream()
                      .map(this::asExecutableElement)
                      .filter(Objects::nonNull)
                      .flatMap(method -> method.getParameters().stream())
                      .filter(parameter -> parameter.getAnnotation(Nullable.class) != null)
                      .map(parameter -> parameter.asType())
                      .collect(toSet());
  }

}
