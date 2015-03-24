package fr.devoxx.niveau2.exo1.etape3;

import java.util.List;
import java.util.stream.Collectors;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;

import fr.devoxx.niveau2.exo1.DontLookAtThisClass;

/**
 * DontLookAtThisClass_ClassAExplorerTest -
 *
 * @author Sébastien Lesaint
 */
abstract class DontLookAtThisClass_ClassInterfacesExplorerTest extends DontLookAtThisClass {

  public DontLookAtThisClass_ClassInterfacesExplorerTest() {
    super("ImplementsInterfacesClass");
  }

  protected List<? extends TypeMirror> expectedExtractInterfaces(TypeElement typeElement) {
    return typeElement.getInterfaces();
  }

  protected List<TypeElement> expectedExtractInterfacesAsTypeElements(Types typeUtils, TypeElement typeElement) {
    return typeElement.getInterfaces()
                      .stream()
                      .map(typeUtils::asElement)
                      .map(this::asTypeElementImpl)
                      .map(TypeElement.class::cast)
                      .collect(Collectors.toList());
  }

  protected TypeMirror expectedExtractSerializableInterface(Types typeUtils, TypeElement typeElement) {
    return typeElement.getInterfaces()
                      .stream()
                      .filter(
                          s -> {
                            Element element = typeUtils.asElement(s);
                            TypeElement typeElement1 = asTypeElementImpl(element);
                            return typeElement1.getQualifiedName().contentEquals("java.io.Serializable");
                          }
                      )
                      .findFirst()
                      .get();
  }

}
