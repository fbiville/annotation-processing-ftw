package fr.devoxx.niveau3.exo1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
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
import javax.tools.FileObject;
import javax.tools.StandardLocation;

import com.google.auto.service.AutoService;

/**
 * Un {@link javax.annotation.processing.Processor} qui écrit dans un fichier les prénoms des personnages du livre Game of Thrones morts en se basant
 * sur les informations disponibles dans l'annotation {@link fr.devoxx.niveau3.exo1.Personnage}.
 */
@AutoService(Processor.class)
public class CrocMortProcessor implements Processor {
  private static final Set<String> SUPPORTED_ANNOTATION_TYPES = Collections.singleton(
      Personnage.class.getCanonicalName()
  );
  private ProcessingEnvironment processingEnv;

  private final Set<String> deadCharacterFirstnames = new HashSet<>();

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return SUPPORTED_ANNOTATION_TYPES;
  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    collectDeadCharactersFirstnames(annotations, roundEnv);

    if (roundEnv.processingOver()) {
      generateListing();
    }
    return false;
  }

  /**
   * Collecte le nom de tous les classes annotées avec {@link fr.devoxx.niveau3.exo1.Personnage} dont la propriété "mort"
   * vaut {@code true} du round courant dans la propriété {@link #deadCharacterFirstnames}.
   */
  private void collectDeadCharactersFirstnames(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    if (annotations.isEmpty()) {
      return;
    }

    TypeElement personnageAnnotationTypeElement = annotations.iterator().next();
    for (Element element : roundEnv.getElementsAnnotatedWith(personnageAnnotationTypeElement)) {
      Personnage annotation = element.getAnnotation(Personnage.class);
      if (annotation.mort()) {
        deadCharacterFirstnames.add(element.getSimpleName().toString());
      }
    }
  }

  /**
   * Génère un fichier personnages_morts.txt dans le package "fr.devoxx.niveau3.exo1" avec le nom des personnages morts (un par ligne)
   * en exploitant le contenu de {@link #deadCharacterFirstnames}.
   */
  private void generateListing() {
    try {
      FileObject listingResource = processingEnv.getFiler().createResource(
          StandardLocation.CLASS_OUTPUT,
          "fr.devoxx.niveau3.exo1",
          "personnages_morts.txt"
      );

      try (BufferedWriter bufferedWriter = new BufferedWriter(listingResource.openWriter())) {
        for (String prenom : deadCharacterFirstnames) {
          bufferedWriter.write(String.valueOf(prenom));
          bufferedWriter.newLine();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Set<String> getSupportedOptions() {
    return Collections.emptySet();
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
}
