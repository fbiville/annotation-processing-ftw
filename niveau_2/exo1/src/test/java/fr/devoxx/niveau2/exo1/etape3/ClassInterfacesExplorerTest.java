package fr.devoxx.niveau2.exo1.etape3;

import java.util.List;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import org.testng.annotations.Test;

/**
 * ClassInterfacesExplorerTest -
 *
 * @author Sébastien Lesaint
 */
public class ClassInterfacesExplorerTest extends DontLookAtThisClass_ClassInterfacesExplorerTest {

  @Test
  public void extractInterfaces() {
    ProcessorTask<List<? extends TypeMirror>> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new ClassInterfacesExplorer(elements, typeUtils).extractInterfaces(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractInterfaces));
  }

  @Test
  public void extractInterfaceAsTypeElements() {
    ProcessorTask<List<TypeElement>> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new ClassInterfacesExplorer(elements, typeUtils).extractInterfaceAsTypeElements(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractInterfacesAsTypeElements));
  }

  @Test
  public void extractSerializableInterface() {
    ProcessorTask<TypeMirror> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new ClassInterfacesExplorer(elements, typeUtils).extractSerializableInterface(typeElement.getInterfaces())
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractSerializableInterface));
  }

}