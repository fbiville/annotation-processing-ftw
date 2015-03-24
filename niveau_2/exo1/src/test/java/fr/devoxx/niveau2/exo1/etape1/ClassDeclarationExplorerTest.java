package fr.devoxx.niveau2.exo1.etape1;

import java.util.Set;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

/**
 * ClassDeclarationExplorerTest -
 *
 * @author Sébastien Lesaint
 */
public class ClassDeclarationExplorerTest extends DontLookAtThisClass_ClassDeclarationExplorerTest {
  @Test
  public void asTypeElement() {
    ProcessorTask<TypeElement> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new ClassDeclarationExplorer(elements, typeUtils).asTypeElement(typeElement)
    );

    processWithWrapper(actual);

    Assertions.assertThat(actual.getTaskResult()).isInstanceOf(TypeElement.class);
  }
  @Test
  public void extractClassName() {
    ProcessorTask<Name> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new ClassDeclarationExplorer(elements, typeUtils).extractClassName(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractClassName));
  }

  @Test
  public void extractClassQualifiedName() {
    ProcessorTask<Name> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new ClassDeclarationExplorer(elements, typeUtils).extractClassQualifiedName(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractClassQualifiedName));
  }

  @Test
  public void extractModifiers() {
    ProcessorTask<Set<Modifier>> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new ClassDeclarationExplorer(elements, typeUtils).extractModifiers(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractModifiers));
  }

  @Test
  public void extractPackage() {
    ProcessorTask<PackageElement> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new ClassDeclarationExplorer(elements, typeUtils).extractPackage(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractPackage));
  }

  @Test
  public void extractPackageName() {
    ProcessorTask<Name> actual = processorTask(
        (Elements elements, Types typeUtils, TypeElement typeElement) ->
            new ClassDeclarationExplorer(elements, typeUtils).extractPackageName(typeElement)
    );

    assertThat(actual).isEqualTo(processorTask(this::expectedExtractPackageName));
  }

}