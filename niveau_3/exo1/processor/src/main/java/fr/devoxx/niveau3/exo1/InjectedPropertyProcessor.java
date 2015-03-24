package fr.devoxx.niveau3.exo1;

import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import com.google.common.annotations.VisibleForTesting;

import com.google.auto.service.AutoService;

/**
 * Un {@link Processor} qui lève une erreur de compilation s'il existe au moins un champs annoté avec
 * {@link javax.inject.Inject}.
 */
@AutoService(Processor.class)
@SupportedAnnotationTypes("javax.inject.Inject")
public class InjectedPropertyProcessor extends AbstractProcessor {

  @VisibleForTesting
  public static final String COMPILATION_ERROR_MSG = "Fields annotated with @Inject are not allowed";

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    if (!annotations.isEmpty()) {
      // votre code ici
    }

    return false;
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latest();
  }

}
