package fr.devoxx.niveau2.exo1.etape1;

import java.util.Set;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import fr.devoxx.niveau2.exo1.DontLookAtThisClass;

/**
 * DontLookAtThisClass_ClassDeclarationExplorerTest -
 *
 * @author Sébastien Lesaint
 */
abstract class DontLookAtThisClass_ClassDeclarationExplorerTest extends DontLookAtThisClass {

  public DontLookAtThisClass_ClassDeclarationExplorerTest() {
    super("BasicClass");
  }

  protected Name expectedExtractClassName(TypeElement typeElement) {
    return typeElement.getSimpleName();
  }

  protected Name expectedExtractClassQualifiedName(TypeElement typeElement) {
    return typeElement.getQualifiedName();
  }

  protected Set<Modifier> expectedExtractModifiers(TypeElement typeElement) {
    return typeElement.getModifiers();
  }

  protected PackageElement expectedExtractPackage(Elements elementUtils, TypeElement typeElement) {
    return elementUtils.getPackageOf(typeElement);
  }

  protected Name expectedExtractPackageName(Elements elementUtils, TypeElement typeElement) {
    return expectedExtractPackage(elementUtils, typeElement).getQualifiedName();
  }

}
