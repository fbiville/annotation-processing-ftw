package fr.devoxx.niveau2.exo2.etape1;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
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
abstract class DontLookAtThisClass_MethodsExplorerTest
    extends DontLookAtThisClass<DontLookAtThisClass_MethodsExplorerTest.MethodsExplorerWrapper> {

  protected MethodsExplorerWrapper createWrapperAndProcess() {
    return processWithWrapper(new MethodsExplorerWrapper(), "Homer");
  }

  protected List<ExecutableElement> expectedExtractMethods(MethodsExplorerWrapper wrapper) {
    return ElementFilter.methodsIn(wrapper.getTypeElement().getEnclosedElements());
  }

  protected List<ExecutableElement> expectedExtractConstructors(MethodsExplorerWrapper wrapper) {
    return ElementFilter.constructorsIn(wrapper.getTypeElement().getEnclosedElements());
  }

  protected List<ExecutableElement> expectedExtractPublicMethodsAndConstructors(MethodsExplorerWrapper wrapper) {
    return wrapper.getTypeElement().getEnclosedElements()
                           .stream()
                           .filter(s -> s.getModifiers().contains(Modifier.PUBLIC))
                           .map(this::asExecutableElement)
                           .filter(Objects::nonNull)
                           .collect(toList());
  }

  protected List<ExecutableElement> expectedExtractPublicFunctions(MethodsExplorerWrapper wrapper) {
    return expectedExtractPublicMethodsAndConstructors(wrapper)
        .stream()
        .filter(s -> s.getReturnType().getKind() == TypeKind.VOID)
        .collect(toList());
  }

  protected List<ExecutableElement> expectedExtractEatDonutsMethods(MethodsExplorerWrapper wrapper) {
    return wrapper.getTypeElement().getEnclosedElements()
                           .stream()
                           .map(this::asExecutableElement)
                           .filter(Objects::nonNull)
                           .filter(s -> s.getSimpleName().contentEquals("eatDonuts"))
                           .collect(toList());
  }

  protected ExecutableElement expectedExtractCurrentEatDonutsMethod(MethodsExplorerWrapper wrapper) {
    return expectedExtractEatDonutsMethods(wrapper)
        .stream()
        .filter(s -> s.getAnnotation(Deprecated.class) == null)
        .findFirst()
        .get();
  }

  protected Set<TypeMirror> expectedExtractTypeOfNullableParameters(MethodsExplorerWrapper wrapper) {
    return wrapper.getTypeElement().getEnclosedElements()
                  .stream()
                  .map(this::asExecutableElement)
                  .filter(Objects::nonNull)
                  .flatMap(method -> method.getParameters().stream())
                  .filter(parameter -> parameter.getAnnotation(Nullable.class) != null)
                  .map(parameter -> parameter.asType())
                  .collect(toSet());
  }

  protected static class MethodsExplorerWrapper extends DontLookAtThisClass.AbstractWrapperProcessor {

    private MethodsExplorer methodsExplorer;

    @Override
    public void init(ProcessingEnvironment processingEnv) {
      super.init(processingEnv);
      this.methodsExplorer = new MethodsExplorer(getElementUtils(), getTypeUtils());
    }

    ///////////////////////////////////////////
    // MethodsExplorer delegated methods
    ///////////////////////////////////////////

    public List<ExecutableElement> extractMethods() {
      return methodsExplorer.extractMethods(getTypeElement());
    }

    public List<ExecutableElement> extractConstructors() {
      return methodsExplorer.extractConstructors(getTypeElement());
    }

    public List<ExecutableElement> extractPublicMethodsAndConstructors() {
      return methodsExplorer.extractPublicMethodsAndConstructors(getTypeElement());
    }

    public List<ExecutableElement> extractPublicFunctions() {
      return methodsExplorer.extractPublicFunctions(getTypeElement());
    }

    public List<ExecutableElement> extractEatDonutsMethods() {
      return methodsExplorer.extractEatDonutsMethods(getTypeElement());
    }

    public ExecutableElement extractCurrentEatDonutsMethod() {
      return methodsExplorer.extractCurrentEatDonutsMethod(getTypeElement());
    }

    public Set<TypeMirror> extractTypeOfNullableParameters() {
      return methodsExplorer.extractTypeOfNullableParameters(getTypeElement());
    }
  }
}
