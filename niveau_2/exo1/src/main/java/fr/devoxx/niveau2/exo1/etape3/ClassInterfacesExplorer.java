package fr.devoxx.niveau2.exo1.etape3;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.SimpleElementVisitor6;
import javax.lang.model.util.Types;

/**
 * ClassInterfacesExplorer -
 *
 * @author Sébastien Lesaint
 */
public class ClassInterfacesExplorer {
  @Nonnull
  private final Elements elementUtils;
  @Nonnull
  private final Types typeUtils;

  public ClassInterfacesExplorer(@Nonnull Elements elementUtils, @Nonnull Types typeUtils) {
    this.elementUtils = elementUtils;
    this.typeUtils = typeUtils;
  }

  /**
   * Extrait les interfaces implémentées par la classe à partir du {@link TypeElement} qui la représente.
   *
   * @param implementsInterfacesClassTypeElement le {@link Element} de
   *                                             {@link fr.devoxx.niveau2.exo1.etape3.ImplementsInterfacesClass}
   *
   * @return une liste contenant les {@link TypeMirror} représentant les interfaces {@link java.io.Serializable} et
   * {@link java.lang.Comparable} implémentées par la classe
   * {@link fr.devoxx.niveau2.exo1.etape3.ImplementsInterfacesClass}
   */
  public List<? extends TypeMirror> extractInterfaces(@Nonnull TypeElement implementsInterfacesClassTypeElement) {
    return implementsInterfacesClassTypeElement.getInterfaces();
  }

  /**
   * Transforme une liste de {@link TypeMirror} en la liste des {@link TypeElement} qu'ils "référencent".
   * <p>
   * (astuce: pour la transformation de {@link TypeMirror} en {@link Element}, la classe {@link Elements} peut
   * vous aider)
   * </p>
   * <p>
   * (rappel: la tranformation de {@link Element} à {@link TypeElement} a été couverte lors de l'étape 1).
   * </p>
   *
   * @param implementsInterfacesClassTypeElement le {@link TypeElement} de
   *                                             {@link fr.devoxx.niveau2.exo1.etape3.ImplementsInterfacesClass}
   *
   * @return une liste contenant les {@link TypeElement} des interfaces {@link java.io.Serializable} et
   * {@link java.lang.Comparable}
   */
  public List<TypeElement> extractInterfaceAsTypeElements(@Nonnull TypeElement implementsInterfacesClassTypeElement) {
    return implementsInterfacesClassTypeElement.getInterfaces()
                                               .stream()
                                               .map(typeUtils::asElement)
                                               .map(this::asTypeElement)
                                               .filter(Objects::nonNull)
                                               .collect(Collectors.toList());
  }

  /**
   * Extrait le {@link TypeMirror} qui représente l'interface {@link java.io.Serializable} à partir du
   * {@link TypeElement} représentant la classe.
   * <p>
   * Pour identifier le bon {@link TypeMirror}, il convient de filtrer sur le nom qualifié de la classe
   * (ici "java.io.Serializable"). Cette information est portée par le {@link TypeElement} "référencé" par le
   * {@link TypeMirror}. Les transformations vues lors des exercices prédécents vous seront donc utiles.
   * </p>
   *
   * @param interfaces la liste des {@link TypeMirror} des interfaces implémentées par
   *                   {@link fr.devoxx.niveau2.exo1.etape3.ImplementsInterfacesClass}
   *
   * @return le {@link TypeElement} qui représente l'interface {@link java.io.Serializable}
   */
  public TypeMirror extractSerializableInterface(@Nonnull List<? extends TypeMirror> interfaces) {
    return interfaces
        .stream()
        .filter(s -> asTypeElement(typeUtils.asElement(s)).getQualifiedName().contentEquals("java.io.Serializable"))
        .findFirst()
        .get();
  }

  private TypeElement asTypeElement(Element implementsInterfacesClassTypeElement) {
    return implementsInterfacesClassTypeElement.accept(
        new SimpleElementVisitor6<TypeElement, Void>() {
          @Override
          public TypeElement visitType(TypeElement e, Void o) {
            return e;
          }
        }, null
    );
  }

}
