package fr.devoxx.niveau2.exo2.etape1;

import java.util.List;
import java.util.Objects;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeKind;
import javax.lang.model.util.ElementFilter;

import org.testng.annotations.Test;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * MethodsExplorerTest -
 *
 * @author Sébastien Lesaint
 */
public class MethodsExplorerTest extends DontLookAtThisClass_MethodsExplorerTest {
  @Test
  public void extractMethods() {
    MethodsExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractMethods()).isEqualTo(expectedExtractMethods(wrapper));
  }

  private List<ExecutableElement> expectedExtractMethods(MethodsExplorerWrapper wrapper) {
    return ElementFilter.methodsIn(wrapper.getTypeElement().getEnclosedElements());
  }

  @Test
  public void extractConstructors() {
    MethodsExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractConstructors()).isEqualTo(expectedExtractConstructors(wrapper));
  }

  private List<ExecutableElement> expectedExtractConstructors(MethodsExplorerWrapper wrapper) {
    return ElementFilter.constructorsIn(wrapper.getTypeElement().getEnclosedElements());
  }

  @Test
  public void extractPublicMethodsAndConstructors() {
    MethodsExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractPublicMethodsAndConstructors())
        .isEqualTo(expectedExtractPublicMethodsAndConstructors(wrapper));
  }

  private List<ExecutableElement> expectedExtractPublicMethodsAndConstructors(MethodsExplorerWrapper wrapper) {
    return wrapper.getTypeElement().getEnclosedElements()
                           .stream()
                           .filter(s -> s.getModifiers().contains(Modifier.PUBLIC))
                           .map(this::asExecutableElement)
                           .filter(Objects::nonNull)
                           .collect(toList());
  }

  @Test
  public void extractPublicFunctions() {
    MethodsExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractPublicFunctions()).isEqualTo(expectedExtractPublicFunctions(wrapper));
  }

  private List<ExecutableElement> expectedExtractPublicFunctions(MethodsExplorerWrapper wrapper) {
    return expectedExtractPublicMethodsAndConstructors(wrapper)
        .stream()
        .filter(s -> s.getReturnType().getKind() == TypeKind.VOID)
        .collect(toList());
  }

  @Test
  public void extractEatDonutsMethods() {
    MethodsExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractEatDonutsMethods()).isEqualTo(expectedExtractEatDonutsMethods(wrapper));
  }

  private List<ExecutableElement> expectedExtractEatDonutsMethods(MethodsExplorerWrapper wrapper) {
    return wrapper.getTypeElement().getEnclosedElements()
                           .stream()
                           .map(this::asExecutableElement)
                           .filter(Objects::nonNull)
                           .filter(s -> s.getSimpleName().contentEquals("eatDonuts"))
                           .collect(toList());
  }

  @Test
  public void extractCurrentEatDonutsMethod() {
    MethodsExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractCurrentEatDonutsMethod()).isEqualTo(expectedExtractCurrentEatDonutsMethod(wrapper));
  }

  private ExecutableElement expectedExtractCurrentEatDonutsMethod(MethodsExplorerWrapper wrapper) {
    return expectedExtractEatDonutsMethods(wrapper)
        .stream()
        .filter(s -> s.getAnnotation(Deprecated.class) == null)
        .findFirst()
        .get();
  }

}