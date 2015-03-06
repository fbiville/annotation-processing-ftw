package fr.devoxx.niveau2.exo2.etape1;

import java.util.List;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;

import fr.devoxx.niveau2.exo2.DontLookAtThisClass;

/**
 * DontLookAtThisClass_MethodsExplorerTest -
 *
 * @author Sébastien Lesaint
 */
abstract class DontLookAtThisClass_MethodsExplorerTest
    extends DontLookAtThisClass<DontLookAtThisClass_MethodsExplorerTest.MethodsExplorerWrapper> {

  protected MethodsExplorerWrapper createWrapperAndProcess() {
    return processWithWrapper(new MethodsExplorerWrapper(), "Homer");
  }

  protected static class MethodsExplorerWrapper extends DontLookAtThisClass.AbstractWrapperProcessor {

    private MethodsExplorer classDeclarationExplorer;

    @Override
    public void init(ProcessingEnvironment processingEnv) {
      super.init(processingEnv);
      this.classDeclarationExplorer = new MethodsExplorer(getElementUtils(), getTypeUtils());
    }

    ///////////////////////////////////////////
    // MethodsExplorer delegated methods
    ///////////////////////////////////////////

    public List<ExecutableElement> extractMethods() {
      return classDeclarationExplorer.extractMethods(getTypeElement());
    }

    public List<ExecutableElement> extractConstructors() {
      return classDeclarationExplorer.extractConstructors(getTypeElement());
    }

    public List<ExecutableElement> extractPublicMethodsAndConstructors() {
      return classDeclarationExplorer.extractPublicMethodsAndConstructors(getTypeElement());
    }

    public List<ExecutableElement> extractPublicFunctions() {
      return classDeclarationExplorer.extractPublicFunctions(getTypeElement());
    }

    public List<ExecutableElement> extractEatDonutsMethods() {
      return classDeclarationExplorer.extractEatDonutsMethods(getTypeElement());
    }

    public ExecutableElement extractCurrentEatDonutsMethod() {
      return classDeclarationExplorer.extractCurrentEatDonutsMethod(getTypeElement());
    }
  }
}
