package fr.devoxx.niveau3.exo1.etape1;

import javax.tools.JavaFileObject;

import com.google.testing.compile.JavaFileObjects;
import com.google.testing.compile.JavaSourceSubjectFactory;
import com.google.testing.compile.JavaSourcesSubjectFactory;
import fr.devoxx.niveau3.exo1.InjectedPropertyProcessor;
import org.testng.annotations.Test;
import org.truth0.Truth;

import static java.util.Arrays.asList;

/**
 * Etape1Test -
 *
 * @author Sébastien Lesaint
 */
public class Etape1Test {
  @Test
  public void compilation_should_fail_when_a_property_is_annotated_with_Inject() throws Exception {
    InjectedPropertyProcessor injectedPropertyProcessor = new InjectedPropertyProcessor();

    JavaFileObject someServiceJavaFileObject = loadClass("SomeService");
    JavaFileObject someManagerJavaFileObject = loadClass("SomeManager");
    Truth.ASSERT.about(JavaSourcesSubjectFactory.javaSources())
                .that(
                    asList(someServiceJavaFileObject, someManagerJavaFileObject)
                )
                .processedWith(injectedPropertyProcessor)
                .failsToCompile()
                .withErrorContaining(InjectedPropertyProcessor.COMPILATION_ERROR_MSG);
  }

  @Test
  public void compilation_should_not_fail_when_a_method_is_annotated_with_Inject() throws Exception {
    InjectedPropertyProcessor injectedPropertyProcessor = new InjectedPropertyProcessor();

    Truth.ASSERT.about(JavaSourceSubjectFactory.javaSource())
                .that(
                    loadClass(
                        "SomeManager"
                    )
                )
                .processedWith(injectedPropertyProcessor)
                .compilesWithoutError();
  }

  private JavaFileObject loadClass(String className) {
    return JavaFileObjects.forResource("fr/devoxx/niveau3/exo1/" + className + ".java");
  }
}
