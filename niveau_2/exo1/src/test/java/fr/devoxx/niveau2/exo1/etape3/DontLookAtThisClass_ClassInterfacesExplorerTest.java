package fr.devoxx.niveau2.exo1.etape3;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

import fr.devoxx.niveau2.exo1.DontLookAtThisClass;

/**
 * DontLookAtThisClass_ClassAExplorerTest -
 *
 * @author Sébastien Lesaint
 */
abstract class DontLookAtThisClass_ClassInterfacesExplorerTest
    extends DontLookAtThisClass<DontLookAtThisClass_ClassInterfacesExplorerTest.ClassInterfacesExplorerWrapper> {

  protected ClassInterfacesExplorerWrapper createWrapperAndProcess() {
    return processWithWrapper(new ClassInterfacesExplorerWrapper(), "ImplementsInterfacesClass");
  }

  protected List<? extends TypeMirror> expectedExtractInterfaces(ClassInterfacesExplorerWrapper wrapper) {
    return wrapper.getTypeElement().getInterfaces();
  }

  protected List<Element> expectedExtractInterfacesAsTypeElements(ClassInterfacesExplorerWrapper wrapper) {
    return wrapper.getTypeElement().getInterfaces()
                  .stream()
                  .map(wrapper.getTypeUtils()::asElement)
                  .collect(Collectors.toList());
  }

  protected TypeMirror expectedExtractSerializableInterface(ClassInterfacesExplorerWrapper wrapper) {
    return wrapper.getTypeElement().getInterfaces()
                  .stream()
                  .filter(
                      s -> {
                        Element element = wrapper.getTypeUtils().asElement(s);
                        TypeElement typeElement = asTypeElementImpl(element);
                        return typeElement.getQualifiedName().contentEquals("java.io.Serializable");
                      }
                  )
                  .findFirst()
                  .get();
  }

  protected class ClassInterfacesExplorerWrapper extends DontLookAtThisClass.AbstractWrapperProcessor {

    private ClassInterfacesExplorer classDeclarationExplorer;

    @Override
    public void init(ProcessingEnvironment processingEnv) {
      super.init(processingEnv);
      this.classDeclarationExplorer = new ClassInterfacesExplorer(getElementUtils(), getTypeUtils());
    }

    //////////////////////////////////////////////
    // ClassInterfacesExplorer delegated methods
    //////////////////////////////////////////////

    public List<? extends TypeMirror> extractInterfaces() {
      return classDeclarationExplorer.extractInterfaces(getTypeElement());
    }

    public List<TypeElement> extractInterfaceAsTypeElements() {
      return classDeclarationExplorer.extractInterfaceAsTypeElements(getTypeElement());
    }

    public TypeMirror extractSerializableInterface() {
      return classDeclarationExplorer.extractSerializableInterface(expectedExtractInterfaces(this));
    }
  }
}
