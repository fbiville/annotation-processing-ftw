package fr.devoxx.niveau2.exo1.etape3;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ClassInterfacesExplorerTest -
 *
 * @author Sébastien Lesaint
 */
public class ClassInterfacesExplorerTest extends DontLookAtThisClass_ClassInterfacesExplorerTest {

  @Test
  public void extractInterfaces() {
    ClassInterfacesExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractInterfaces()).isEqualTo(expectedExtractInterfaces(wrapper));
  }

  @Test
  public void extractInterfaceAsTypeElements() {
    ClassInterfacesExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractInterfaceAsTypeElements()).isEqualTo(expectedExtractInterfacesAsTypeElements(wrapper));
  }

  @Test
  public void extractSerializableInterface() {
    ClassInterfacesExplorerWrapper wrapper = createWrapperAndProcess();

    assertThat(wrapper.extractSerializableInterface()).isEqualTo(expectedExtractSerializableInterface(wrapper));
  }

}