package fr.devoxx.niveau1.exo3;

import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * Un {@link javax.annotation.processing.Processor} qui écrit simplement "Hello world!" dans les logs de compilation si
 * au moins un élément dans le code est annoté avec {@link java.lang.Deprecated}.
 * 
 * Ce processor contient de fait la correction de l'étape 3 de l'exo 2 ;)
 * 
 */
@SupportedAnnotationTypes("java.lang.Deprecated")
public class HelloWorldProcessor extends AbstractProcessor {

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latest();
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
