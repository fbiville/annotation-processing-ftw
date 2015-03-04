package fr.devoxx.niveau2.exo1.etape1;

import javax.lang.model.element.TypeElement;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ClassDeclarationExplorerTest -
 *
 * @author Sébastien Lesaint
 */
public class ClassDeclarationExplorerTest extends DontLookAtThisClass_ClassDeclarationExplorerTest {
  @Test
  public void asTypeElement() {
    ClassDeclarationExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.asTypeElement()).isInstanceOf(TypeElement.class);
  }
  @Test
  public void extractClassName() {
    ClassDeclarationExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractClassName()).isEqualTo(expectedExtractClassName(wrapper));
  }

  @Test
  public void extractClassQualifiedName() {
    ClassDeclarationExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractClassQualifiedName()).isEqualTo(expectedExtractClassQualifiedName(wrapper));
  }

  @Test
  public void extractModifiers() {
    ClassDeclarationExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractModifiers()).isEqualTo(expectedExtractModifiers(wrapper));
  }

  @Test
  public void extractPackage() {
    ClassDeclarationExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractPackage()).isEqualTo(expectedExtractPackage(wrapper));
  }

  @Test
  public void extractPackageName() {
    ClassDeclarationExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractPackageName()).isEqualTo(expectedExtractPackageName(wrapper));
  }

}