package fr.devoxx.niveau2.exo1.etape1;

import java.util.Set;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

import fr.devoxx.niveau2.exo1.DontLookAtThisClass;

/**
 * DontLookAtThisClass_ClassDeclarationExplorerTest -
 *
 * @author Sébastien Lesaint
 */
abstract class DontLookAtThisClass_ClassDeclarationExplorerTest
 extends DontLookAtThisClass<DontLookAtThisClass_ClassDeclarationExplorerTest.ClassDeclarationExplorerWrapper> {

  protected ClassDeclarationExplorerWrapper createWrapperAndProcess() {
    return processWithWrapper(new ClassDeclarationExplorerWrapper(), "BasicClass");
  }

  protected Name expectedExtractClassName(ClassDeclarationExplorerWrapper wrapper) {
    return wrapper.getTypeElement().getSimpleName();
  }

  protected Name expectedExtractClassQualifiedName(ClassDeclarationExplorerWrapper wrapper) {
    return wrapper.getTypeElement().getQualifiedName();
  }

  protected Set<Modifier> expectedExtractModifiers(ClassDeclarationExplorerWrapper wrapper) {
    return wrapper.getTypeElement().getModifiers();
  }

  protected PackageElement expectedExtractPackage(ClassDeclarationExplorerWrapper wrapper) {
    return wrapper.getElementUtils().getPackageOf(wrapper.getTypeElement());
  }

  protected Name expectedExtractPackageName(ClassDeclarationExplorerWrapper wrapper) {
    return expectedExtractPackage(wrapper).getQualifiedName();
  }

  protected static class ClassDeclarationExplorerWrapper extends DontLookAtThisClass.AbstractWrapperProcessor {

    private ClassDeclarationExplorer classDeclarationExplorer;

    @Override
    public void init(ProcessingEnvironment processingEnv) {
      super.init(processingEnv);
      this.classDeclarationExplorer = new ClassDeclarationExplorer(getElementUtils(), getTypeUtils());
    }

    ///////////////////////////////////////////
    // ClassDeclarationExplorer delegated methods
    ///////////////////////////////////////////

    public TypeElement asTypeElement() {
      return classDeclarationExplorer.asTypeElement(getTypeElement());
    }

    public Name extractClassName() {
      return classDeclarationExplorer.extractClassName(getTypeElement());
    }

    public Name extractClassQualifiedName() {
      return classDeclarationExplorer.extractClassQualifiedName(getTypeElement());
    }

    public Set<Modifier> extractModifiers() {
      return classDeclarationExplorer.extractModifiers(getTypeElement());
    }

    public PackageElement extractPackage() {
      return classDeclarationExplorer.extractPackage(getTypeElement());
    }

    public Name extractPackageName() {
      return classDeclarationExplorer.extractPackageName(getTypeElement());
    }
  }
}
