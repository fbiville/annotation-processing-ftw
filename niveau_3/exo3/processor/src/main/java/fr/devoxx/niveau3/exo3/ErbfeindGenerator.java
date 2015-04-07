package fr.devoxx.niveau3.exo3;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Stream;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import com.google.auto.service.AutoService;

@AutoService(Processor.class)
/**
 * Grâce à la détection implémentée dans AbstractProcessor,
 * il est possible de déclarer par annotation au niveau du type
 * du processor quelle(s) annotation(s) ce dernier supporte.
 *
 * Il faut donc le préciser ici.
 * @see {@link javax.annotation.processing.AbstractProcessor#getSupportedAnnotationTypes()}
 */
public class ErbfeindGenerator extends AbstractProcessor {

  private ProcessingEnvironment processingEnv;
  private Elements elementUtils;

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latest();
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return Collections.emptySet();
  }

  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);
    this.processingEnv = processingEnv;
    this.elementUtils = processingEnv.getElementUtils();
  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    System.out.println("What's going on here?");

    extractClassElements(annotations, roundEnv)
      .stream()
      .forEach(this::write);

    return false;
  }

  /*
   * visible for testing
   * don't do this at home! :o)
   */
  public Collection<Element> extractClassElements(Set<? extends TypeElement> annotations,
                                           RoundEnvironment roundEnv) {

    //TODO: extrait les éléments annotés par les annotations spécifiées
    return Collections.emptyList();
  }

  String packageName(Element element) {
    //TODO: extrait le nom de package d'un élément
    return "";
  }

  private void write(Element element) {
    /*
     * TODO: génère une classe par nom d'Erbfeind spécifié par l'Element donné
     */
  }

  private Stream<String> erbfeindNames(Element element) {
    /*
     * TODO: extrait les noms d'Erbfeind de l'élément spécifié
     */
    return Stream.empty();
  }
}
