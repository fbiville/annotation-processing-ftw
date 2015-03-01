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

/**
 * Un {@link javax.annotation.processing.Processor} qui affiche un warning à la compilation si au moins une annotation
 * {@link java.lang.Deprecated} est présente dans les sources compilées.
 */
public class DeprecatedCodeWhistleblower implements Processor {
  private ProcessingEnvironment processingEnv;

  @Override
  public Set<String> getSupportedOptions() {
    return Collections.emptySet();
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return Collections.singleton(Deprecated.class.getCanonicalName());
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
          "Attention, il y a du code déprécié dans les sources de ce module !"
      );
    }
    return false;
  }
}
