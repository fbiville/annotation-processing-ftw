package fr.devoxx.niveau2.exo1;

import java.util.Collections;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.SimpleElementVisitor6;
import javax.lang.model.util.SimpleTypeVisitor6;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;

import com.google.testing.compile.JavaFileObjects;
import com.google.testing.compile.JavaSourceSubjectFactory;
import org.truth0.Truth;

/**
 * DontLookAtThisClass -
 *
 * @author Sébastien Lesaint
 */
public class DontLookAtThisClass<T extends DontLookAtThisClass.AbstractWrapperProcessor> {

  protected T processWithWrapper(T wrapper, String className) {
    Truth.ASSERT.about(JavaSourceSubjectFactory.javaSource())
                .that(loadClass(className))
                .processedWith(wrapper)
                .compilesWithoutError();

    return wrapper;
  }

  private JavaFileObject loadClass(String className) {
    return JavaFileObjects.forResource(getClass().getPackage().getName().replace(".", "/") + '/' + className + ".java");
  }

  protected static DeclaredType asDeclaredTypeImpl(TypeMirror typeMirror) {
    return typeMirror.accept(
        new SimpleTypeVisitor6<DeclaredType, Void>() {
          @Override
          public DeclaredType visitDeclared(DeclaredType t, Void o) {
            return t;
          }
        }, null
    );
  }

  protected TypeElement asTypeElementImpl(Element basicClassTypeElement) {
    return basicClassTypeElement.accept(
        new SimpleElementVisitor6<TypeElement, Void>() {
          @Override
          public TypeElement visitType(TypeElement e, Void o) {
            return e;
          }
        }, null
    );
  }

  /**
   * AbstractWrapperProcessor -
   *
   * @author Sébastien Lesaint
   */
  public abstract static class AbstractWrapperProcessor extends AbstractProcessor {
    private Elements elementUtils;
    private Types typeUtils;
    private TypeElement typeElement;

    @Override
    public SourceVersion getSupportedSourceVersion() {
      return SourceVersion.latest();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
      return Collections.singleton(Flag.class.getCanonicalName());
    }

    @Override
    public void init(ProcessingEnvironment processingEnv) {
      this.elementUtils = processingEnv.getElementUtils();
      this.typeUtils = processingEnv.getTypeUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
      if (!annotations.isEmpty() && !roundEnv.processingOver()) {
        TypeElement flagTypeElement = annotations.iterator().next();
        typeElement = (TypeElement) roundEnv.getElementsAnnotatedWith(flagTypeElement).iterator().next();
      }
      return false;
    }

    public TypeElement getTypeElement() {
      return typeElement;
    }

    public Elements getElementUtils() {
      return elementUtils;
    }

    public Types getTypeUtils() {
      return typeUtils;
    }
  }
}
