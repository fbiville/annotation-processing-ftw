package fr.devoxx.niveau2.exo2.etape2;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * FieldsExplorerTest -
 *
 * @author Sébastien Lesaint
 */
public class FieldsExplorerTest extends DontLookAtThisClass_FieldsExplorerTest {
  @Test
  public void extractFields() {
    FieldsExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractFields()).isEqualTo(expectedExtractFields(wrapper));
  }

  @Test
  public void extractNameFieldType() {
    FieldsExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractNameFieldType()).isEqualTo(expectedExtractNameFieldType(wrapper));
  }

  @Test
  public void extractPrivateAndPackageProtectedFields() {
    FieldsExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractPrivateAndPackageProtectedFields())
        .isEqualTo(expectedExtractPrivateAndPackageProtectedFields(wrapper));
  }

  @Test
  public void extractDeprecatedField() {
    FieldsExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractDeprecatedField()).isEqualTo(expectedExtractDeprecatedField(wrapper));
  }

}