package fr.devoxx.niveau2.exo1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
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
import org.assertj.core.api.Assertions;
import org.truth0.Truth;

import static java.util.Objects.requireNonNull;

/**
 * DontLookAtThisClass -
 *
 * @author Sébastien Lesaint
 */
public class DontLookAtThisClass {
  private final String classSimpleName;

  public DontLookAtThisClass(String classSimpleName) {
    this.classSimpleName = classSimpleName;
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

  protected ExecutableElement asExecutableElement(@Nonnull Element methodElement) {
    return methodElement.accept(
        new SimpleElementVisitor6<ExecutableElement, Void>() {
          @Override
          public ExecutableElement visitExecutable(ExecutableElement e, Void aVoid) {
            return e;
          }
        }, null
    );
  }

  protected static <T> ProcessorTask<T> processorTask(@Nonnull AllParametersTaskResultComputer<T> computeJob) {
    return new AbstractProcessorTask<T>() {
      @Override
      public void execute(Elements elements, Types typeUtils, TypeElement typeElement) {
        this.taskResult = computeJob.compute(elements, typeUtils, typeElement);
      }
    };
  }

  @FunctionalInterface
  protected static interface AllParametersTaskResultComputer<T> {
    T compute(Elements elements, Types typeUtils, TypeElement typeElement);

  }

  protected static <T> ProcessorTask<T> processorTask(@Nonnull ElementsTaskResultComputer<T> elementsTaskResultComputer) {
    return new AbstractProcessorTask<T>() {

      @Override
      public void execute(Elements elements, Types typeUtils, TypeElement typeElement) {
        this.taskResult = elementsTaskResultComputer.compute(elements, typeElement);
      }
    };
  }

  @FunctionalInterface
  protected static interface ElementsTaskResultComputer<T> {
    T compute(Elements elements, TypeElement typeElement);

  }

  protected static <T> ProcessorTask<T> processorTask(@Nonnull TypeUtilsTaskResultComputer<T> typeUtilsTaskResultComputer) {
    return new AbstractProcessorTask<T>() {
      @Override
      public void execute(Elements elements, Types typeUtils, TypeElement typeElement) {
        this.taskResult = typeUtilsTaskResultComputer.compute(typeUtils, typeElement);
      }
    };
  }

  @FunctionalInterface
  protected static interface TypeUtilsTaskResultComputer<T> {
    T compute(Types typeUtils, TypeElement typeElement);

  }

  protected static <T> ProcessorTask<T> processorTask(@Nonnull TypeElementOnlyTaskResultComputer<T> typeElementOnlyTaskResultComputer) {
    return new AbstractProcessorTask<T>() {
      @Override
      public void execute(Elements elements, Types typeUtils, TypeElement typeElement) {
        this.taskResult = typeElementOnlyTaskResultComputer.compute(typeElement);
      }
    };
  }

  @FunctionalInterface
  protected static interface TypeElementOnlyTaskResultComputer<T> {
    T compute(TypeElement typeElement);

  }

  protected <T> ProcessorTaskAssertion<T> assertThat(ProcessorTask<T> actual) {
    return new ProcessorTaskAssertion<>(this.classSimpleName, actual);
  }

  protected void processWithWrapper(ProcessorTask<?>... processorTasks) {
    Truth.ASSERT.about(JavaSourceSubjectFactory.javaSource())
                .that(loadClass(this.classSimpleName))
                .processedWith(new ProcessorTaskExecutionProcessor(processorTasks))
                .compilesWithoutError();
  }

  protected class ProcessorTaskAssertion<T> {
    @Nonnull
    private final String classSimpleName;

    @Nonnull
    private final ProcessorTask<T> actual;

    public ProcessorTaskAssertion(@Nonnull String classSimpleName, @Nonnull ProcessorTask<T> actual) {
      this.classSimpleName = requireNonNull(classSimpleName);
      this.actual = requireNonNull(actual);
    }

    public void isEqualTo(@Nonnull ProcessorTask<T> expected) {
      requireNonNull(expected);

      processWithWrapper(this.actual, expected);

      Assertions.assertThat(this.actual.getTaskResult()).isEqualTo(expected.getTaskResult());
    }

  }

  private JavaFileObject loadClass(String className) {
    return JavaFileObjects.forResource(getClass().getPackage().getName().replace(".", "/") + '/' + className + ".java");
  }

  protected static interface ProcessorTask<T> {
    void execute(Elements elements, Types typeUtils, TypeElement typeElement);

    T getTaskResult();
  }

  protected static abstract class AbstractProcessorTask<T> implements ProcessorTask<T> {
    protected T taskResult;

    @Override
    public T getTaskResult() {
      return taskResult;
    }
  }


  /**
   * ProcessorTaskExecutionProcessor -
   *
   * @author Sébastien Lesaint
   */
  private static class ProcessorTaskExecutionProcessor extends AbstractProcessor {
    private Elements elementUtils;
    private Types typeUtils;
    private final List<ProcessorTask<?>> processorTasks;

    protected ProcessorTaskExecutionProcessor(ProcessorTask<?>... processorTasks) {
      this.processorTasks = Arrays.asList(processorTasks);
    }

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
        TypeElement typeElement = (TypeElement) roundEnv.getElementsAnnotatedWith(flagTypeElement).iterator().next();

        for (ProcessorTask processorTask : processorTasks) {
          processorTask.execute(this.elementUtils, this.typeUtils, typeElement);
        }
      }
      return false;
    }

  }
}
