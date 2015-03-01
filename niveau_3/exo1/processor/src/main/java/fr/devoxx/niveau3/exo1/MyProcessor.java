package fr.devoxx.niveau3.exo1;

import java.util.Collections;
import java.util.Set;
import javax.annotation.processing.Completion;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

import com.google.auto.service.AutoService;

/**
 * Un {@link javax.annotation.processing.Processor} qui affiche un citation d'Indiana Johns à la compilation si au
 * moins une annotation {@link Deprecated} est présente dans les sources compilées.
 */
@AutoService(Processor.class)
public class MyProcessor implements Processor {
  private static final Set<String> SUPPORTED_ANNOTATION_TYPES = Collections.singleton(SomeAnnotation.class
          .getCanonicalName());
  private ProcessingEnvironment processingEnv;

  @Override
  public Set<String> getSupportedOptions() {
    return Collections.emptySet();
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return SUPPORTED_ANNOTATION_TYPES;
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latest();
  }

  @Override
  public Iterable<? extends Completion> getCompletions(Element element, AnnotationMirror annotation,
                                                       ExecutableElement member, String userText) {
    return Collections.emptyList();
  }

  @Override
  public void init(ProcessingEnvironment processingEnv) {
    this.processingEnv = processingEnv;
  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    if (roundEnv.processingOver()) {
      processingEnv.getMessager().printMessage(
          Diagnostic.Kind.MANDATORY_WARNING,
          "True rewards await those who choose wisely."
      );
    }
    return false;
  }
}
