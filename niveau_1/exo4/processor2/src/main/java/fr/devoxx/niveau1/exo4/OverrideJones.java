package fr.devoxx.niveau1.exo4;

import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * Un {@link javax.annotation.processing.Processor} qui affiche une citation  
 * d'Indiana Jones à la compilation si au moins une annotation {@link Deprecated} 
 * est présente dans les sources compilées.
 */
@SupportedAnnotationTypes("java.lang.Override")
public class OverrideJones extends AbstractProcessor {

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latest();
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
