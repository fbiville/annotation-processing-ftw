import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * Un {@link javax.annotation.processing.Processor} qui écrit simplement "Hello world!" dans les logs de compilation si
 * au moins un élément dans le code est annoté avec {@link Deprecated}.
 * 
 * Celui-ci n'implémente pas directement {@link javax.annotation.processing.Processor} mais étend la class abstraite
 * {@link javax.annotation.processing.AbstractProcessor}.
 * 
 * Outre que cela permet de n'implémenter que la méthode
 * {@link javax.annotation.processing.Processor#process(java.util.Set, javax.annotation.processing.RoundEnvironment)}
 * dans cette classe, étendre {@link AbstractProcessor} permet d'utiliser des annotations, lue via réflection par {@link AbstractProcessor}, pour déclarer que l'annotation {@link java.lang.Deprecated} est supportée.
 * 
 */
@SupportedAnnotationTypes("java.lang.Deprecated")
public class HelloWorldProcessor extends AbstractProcessor {
  /***
   * En principe, lorsque l'on étend AbstractProcessor, on devrait utiliser l'annotation {@link javax.annotation.processing.SupportedSourceVersion} pour spécifier la version des sources Java supportée.
   * 
   * Malheureusement, on ne peut utiliser {@link javax.lang.model.SourceVersion#latest()} dans une annotation et on doit
   * donc spécifier une version en dur. Cela peut provoquer l'affichage de WARNING lors de la compilation ou une erreur
   * de compilation suivant la version de javac utilisée.
   * 
   * Par conséquent, pour une compatibilité maximale dans le cadre de cet exercice (mais c'est une bonne pratique
   * en général), on surcharge la méthode {@link AbstractProcessor#getSupportedSourceVersion()} (qui lie l'annotation
   * {@link javax.annotation.processing.SupportedSourceVersion} si présente) pour retourner
   * {@link javax.lang.model.SourceVersion#latest()} systématiquement.
   * 
   */
  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latest();
  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Hello world!");
    return false;
  }
}

