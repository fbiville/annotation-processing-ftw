package fr.devoxx.niveau2.exo2.etape1;

import org.testng.annotations.Test;

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

  @Test
  public void extractConstructors() {
    MethodsExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractConstructors()).isEqualTo(expectedExtractConstructors(wrapper));
  }

  @Test
  public void extractPublicMethodsAndConstructors() {
    MethodsExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractPublicMethodsAndConstructors())
        .isEqualTo(expectedExtractPublicMethodsAndConstructors(wrapper));
  }

  @Test
  public void extractPublicFunctions() {
    MethodsExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractPublicFunctions()).isEqualTo(expectedExtractPublicFunctions(wrapper));
  }

  @Test
  public void extractEatDonutsMethods() {
    MethodsExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractEatDonutsMethods()).isEqualTo(expectedExtractEatDonutsMethods(wrapper));
  }

  @Test
  public void extractCurrentEatDonutsMethod() {
    MethodsExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractCurrentEatDonutsMethod()).isEqualTo(expectedExtractCurrentEatDonutsMethod(wrapper));
  }

  @Test
  public void extractTypeOfNullableParameters() {
    MethodsExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractTypeOfNullableParameters()).isEqualTo(expectedExtractTypeOfNullableParameters(wrapper));
  }

}