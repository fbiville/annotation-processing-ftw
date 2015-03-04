package fr.devoxx.niveau2.exo1.etape4;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ClassSuperClassExplorerTest -
 *
 * @author Sébastien Lesaint
 */
public class ClassSuperClassExplorerTest extends DontLookAtThisClass_ClassSuperClassExplorerTest {

  @Test
  public void extractSuperClass() {
    ClassSuperClassExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractSuperClass()).isEqualTo(expectedExtractSuperClass(wrapper));
  }

  @Test
  public void asDeclaredType() {
    ClassSuperClassExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.asDeclaredType()).isEqualTo(expectedAsDeclaredType(wrapper));
  }

  @Test
  public void extractSuperClassTypeArgument() {
    ClassSuperClassExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractSuperClassTypeArgument()).isEqualTo(expectedExtractSuperClassTypeArgument(wrapper));
  }

  @Test
  public void extractSuperClassTypeParameterName() {
    ClassSuperClassExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractSuperClassTypeParameterName())
        .isEqualTo(
            expectedExtractSuperClassTypeParameterName(wrapper)
        );
  }

}