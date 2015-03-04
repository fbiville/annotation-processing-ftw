package fr.devoxx.niveau2.exo1.etape4;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Name;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;

import fr.devoxx.niveau2.exo1.DontLookAtThisClass;

/**
 * DontLookAtThisClass_ClassAExplorerTest -
 *
 * @author Sébastien Lesaint
 */
abstract class DontLookAtThisClass_ClassSuperClassExplorerTest
    extends DontLookAtThisClass<DontLookAtThisClass_ClassSuperClassExplorerTest.ClassSuperClassExplorerWrapper> {

  protected ClassSuperClassExplorerWrapper createWrapperAndProcess() {
    return processWithWrapper(new ClassSuperClassExplorerWrapper(), "MyListClass");
  }

  protected DeclaredType expectedAsDeclaredType(ClassSuperClassExplorerWrapper wrapper) {
    return asDeclaredTypeImpl(wrapper.getTypeElement().getSuperclass());
  }

  protected TypeMirror expectedExtractSuperClass(ClassSuperClassExplorerWrapper wrapper) {
    return wrapper.getTypeElement().getSuperclass();
  }

  protected TypeMirror expectedExtractSuperClassTypeArgument(ClassSuperClassExplorerWrapper wrapper) {
    DeclaredType declaredType = asDeclaredTypeImpl(wrapper.getTypeElement().getSuperclass());
    return declaredType.getTypeArguments().iterator().next();
  }

  protected Name expectedExtractSuperClassTypeParameterName(ClassSuperClassExplorerWrapper wrapper) {
    DeclaredType declaredType = asDeclaredTypeImpl(wrapper.getTypeElement().getSuperclass());
    return asTypeElementImpl(declaredType.asElement()).getTypeParameters().iterator().next().getSimpleName();
  }

  protected static class ClassSuperClassExplorerWrapper extends DontLookAtThisClass.AbstractWrapperProcessor {
    private ClassSuperClassExplorer classDeclarationExplorer;

    @Override
    public void init(ProcessingEnvironment processingEnv) {
      super.init(processingEnv);
      this.classDeclarationExplorer = new ClassSuperClassExplorer(getElementUtils(), getTypeUtils());
    }

    /////////////////////////////////////////////
    // ClassSuperClassExplorer delegated methods
    /////////////////////////////////////////////

    public TypeMirror extractSuperClass() {
      return classDeclarationExplorer.extractSuperClass(getTypeElement());
    }

    public DeclaredType asDeclaredType() {
      return classDeclarationExplorer.asDeclaredType(getTypeElement().getSuperclass());
    }

    public TypeMirror extractSuperClassTypeArgument() {
      return classDeclarationExplorer.extractSuperClassTypeArgument(getTypeElement());
    }

    public Name extractSuperClassTypeParameterName() {
      return classDeclarationExplorer.extractSuperClassTypeParameterName(getTypeElement());
    }
  }
}
