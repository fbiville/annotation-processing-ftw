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
 * au moins un élément dans le code est annoté avec {@link Deprecated}.
 * 
 * Comme vous allez pouvoir le constater en l'utilisant, cet annotation processor affiche 2 fois "Hello world!" au lieu d'une, d'où le nom (Stuttering = bégayant).
 *
 */
public class StutteringHelloWorldProcessor implements Processor {
  private ProcessingEnvironment processingEnv;

  /**
   * Indique que StutteringHelloWorldProcessor est un Processor pour l'annotation {@code @Deprecated}.
   */
  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return Collections.singleton(Deprecated.class.getCanonicalName());
  }

  /**
   * En l'absence de code spécifique à une version de Java, la bonne pratique est de retourner {@code SourceVersion.latest()}.
   */
  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latest();
  }

  /**
   * Cette méthode est garantie d'être appelée une seule et unique fois et avant toutes les autres méthodes de Processor.
   * Ici, on garde une référence vers l'instance {@link ProcessingEnvironment} dans une propriété afin de pouvoir accéder à
   * {@link ProcessingEnvironment#getMessager()} dans la méthode process.
   */
  @Override
  public void init(ProcessingEnvironment processingEnv) {
    this.processingEnv = processingEnv;
  }

  /**
   * Cette méthode est appelée une fois par round.
   * Ici, elle affiche simplement le message "Hello world!".
   */
  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Hello world!");
    return false;
  }

  /**
   * L'usage de cette méthode est présenté au niveau 4.
   */
  @Override
  public Set<String> getSupportedOptions() {
    return Collections.emptySet();
  }

  /**
   * L'usage de cette méthode n'est pas présenté ici.
   */
  @Override
  public Iterable<? extends Completion> getCompletions(Element element, AnnotationMirror annotation,
                                                       ExecutableElement member, String userText) {
    return Collections.emptyList();
  }

}
