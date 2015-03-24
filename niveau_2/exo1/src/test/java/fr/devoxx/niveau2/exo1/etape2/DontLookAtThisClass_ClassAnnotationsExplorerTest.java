package fr.devoxx.niveau2.exo1.etape2;

import java.util.List;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.TypeElement;

import fr.devoxx.niveau2.exo1.DontLookAtThisClass;

/**
 * DontLookAtThisClass_ClassAExplorerTest -
 *
 * @author Sébastien Lesaint
 */
abstract class DontLookAtThisClass_ClassAnnotationsExplorerTest extends DontLookAtThisClass {

  public DontLookAtThisClass_ClassAnnotationsExplorerTest() {
    super("AnnotatedClass");
  }

  protected List<? extends AnnotationMirror> expectedExtractAnnotations(TypeElement typeElement) {
    return typeElement.getAnnotationMirrors();
  }

  protected Meta expectedExtractMetaAnnotation(TypeElement typeElement) {
    return typeElement.getAnnotation(Meta.class);
  }

  protected String expectedExtractMetaAnnotationValue(TypeElement typeElement) {
    return expectedExtractMetaAnnotation(typeElement).value();
  }

  protected int expectedExtractMetaAnnotationId(TypeElement typeElement) {
    return expectedExtractMetaAnnotation(typeElement).id();
  }

}
