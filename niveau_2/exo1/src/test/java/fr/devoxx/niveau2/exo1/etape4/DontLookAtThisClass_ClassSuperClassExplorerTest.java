package fr.devoxx.niveau2.exo1.etape4;

import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;

import fr.devoxx.niveau2.exo1.DontLookAtThisClass;

/**
 * DontLookAtThisClass_ClassAExplorerTest -
 *
 * @author Sébastien Lesaint
 */
abstract class DontLookAtThisClass_ClassSuperClassExplorerTest extends DontLookAtThisClass {

  public DontLookAtThisClass_ClassSuperClassExplorerTest() {
    super("MyListClass");
  }

  protected DeclaredType expectedAsDeclaredType(TypeElement typeElement) {
    return asDeclaredTypeImpl(typeElement.getSuperclass());
  }

  protected TypeMirror expectedExtractSuperClass(TypeElement typeElement) {
    return typeElement.getSuperclass();
  }

  protected TypeMirror expectedExtractSuperClassTypeArgument(TypeElement typeElement) {
    DeclaredType declaredType = asDeclaredTypeImpl(typeElement.getSuperclass());
    return declaredType.getTypeArguments().iterator().next();
  }

  protected Name expectedExtractSuperClassTypeParameterName(TypeElement typeElement) {
    DeclaredType declaredType = asDeclaredTypeImpl(typeElement.getSuperclass());
    return asTypeElementImpl(declaredType.asElement()).getTypeParameters().iterator().next().getSimpleName();
  }

}
