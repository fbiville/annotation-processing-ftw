package fr.devoxx.niveau3.exo3;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    HashSet<String> types = new HashSet<>();
    types.add(Erbfeind.class.getName());
    return types;
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

    return annotations.stream()
      .flatMap(annotation -> roundEnv.getElementsAnnotatedWith(annotation).stream())
      .collect(Collectors.toList());
  }

  String packageName(Element element) {
    return elementUtils.getPackageOf(element).getQualifiedName().toString();
  }

  private void write(Element element) {
    erbfeindNames(element).forEach(
      className -> {
        String packageName = packageName(element);
        if (elementUtils.getTypeElement(packageName + "." + className) != null) {
          return;
        }

        try {
          TypeSpec.Builder typeBuilder = TypeSpec.classBuilder(className)
            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
            .addAnnotation(AnnotationSpec.builder(Erbfeind.class)
              .addMember("value", "$S", element.getSimpleName().toString())
              .build());

          JavaFile.builder(packageName, typeBuilder.build())
            .indent("\t")
            .build()
            .writeTo(processingEnv.getFiler());

        } catch (IOException e) {
          throw new RuntimeException(e.getMessage(), e);
        }
      }
    );
  }

  private Stream<String> erbfeindNames(Element element) {
    return Arrays.asList(element.getAnnotation(Erbfeind.class).value()).stream();
  }
}
