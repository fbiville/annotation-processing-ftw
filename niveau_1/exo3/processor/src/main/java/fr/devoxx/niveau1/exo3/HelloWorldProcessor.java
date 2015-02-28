package fr.devoxx.niveau1.exo3;

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
 * Un {@link javax.annotation.processing.Processor} qui écrit simplement "Hello world!" dans les logs de compilation si
 * au moins un élément dans le code est annoté avec {@link java.lang.Deprecated}.
 * <p>
 * Ce processor contient de fait la correction de l'étape 3 de l'exo 2.
 * </p>
 */
public class HelloWorldProcessor implements Processor {
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
      // essayez les différentes valeurs de Diagnostic.Kind et constatez les effets dans les logs Maven
      processingEnv.getMessager().printMessage(Diagnostic.Kind.MANDATORY_WARNING, "Hello world!");
    }
    return false;
  }
}
