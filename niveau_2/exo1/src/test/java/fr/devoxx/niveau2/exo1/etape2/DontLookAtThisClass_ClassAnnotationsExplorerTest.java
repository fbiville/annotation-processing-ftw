package fr.devoxx.niveau2.exo1.etape2;

import java.util.List;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;

import fr.devoxx.niveau2.exo1.DontLookAtThisClass;

/**
 * DontLookAtThisClass_ClassAExplorerTest -
 *
 * @author Sébastien Lesaint
 */
abstract class DontLookAtThisClass_ClassAnnotationsExplorerTest
extends DontLookAtThisClass<DontLookAtThisClass_ClassAnnotationsExplorerTest.ClassAnnotationsExplorerWrapper> {

  protected ClassAnnotationsExplorerWrapper createWrapperAndProcess() {
    return processWithWrapper(new ClassAnnotationsExplorerWrapper(), "AnnotatedClass");
  }

  protected List<? extends AnnotationMirror> expectedExtractAnnotations(ClassAnnotationsExplorerWrapper wrapper) {
    return wrapper.getTypeElement().getAnnotationMirrors();
  }

  protected Meta expectedExtractMetaAnnotation(ClassAnnotationsExplorerWrapper wrapper) {
    return wrapper.getTypeElement().getAnnotation(Meta.class);
  }

  protected String expectedExtractMetaAnnotationValue(ClassAnnotationsExplorerWrapper wrapper) {
    return expectedExtractMetaAnnotation(wrapper).value();
  }

  protected int expectedExtractMetaAnnotationId(ClassAnnotationsExplorerWrapper wrapper) {
    return expectedExtractMetaAnnotation(wrapper).id();
  }

  protected static class ClassAnnotationsExplorerWrapper extends DontLookAtThisClass.AbstractWrapperProcessor {

    private ClassAnnotationsExplorer classAnnotationsExplorer;

    @Override
    public void init(ProcessingEnvironment processingEnv) {
      super.init(processingEnv);
      this.classAnnotationsExplorer = new ClassAnnotationsExplorer(getElementUtils(), getTypeUtils());
    }

    ///////////////////////////////////////////
    // ClassDeclarationExplorer delegated methods
    ///////////////////////////////////////////

    public List<? extends AnnotationMirror> extractAnnotations() {
      return classAnnotationsExplorer.extractAnnotations(getTypeElement());
    }

    public Meta extractMetaAnnotation() {
      return classAnnotationsExplorer.extractMetaAnnotation(getTypeElement());
    }

    public String extractMetaAnnotationValue() {
      return classAnnotationsExplorer.extractMetaAnnotationValue(getTypeElement());
    }

    public int extractMetaAnnotationId() {
      return classAnnotationsExplorer.extractMetaAnnotationId(getTypeElement());
    }
  }
}
