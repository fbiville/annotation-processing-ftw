package fr.devoxx.niveau2.exo1.etape3;

import java.util.List;
import javax.annotation.Nonnull;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
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
   * @param typeElement le {@link TypeElement} de {@link fr.devoxx.niveau2.exo1.etape3.ImplementsInterfacesClass}
   *
   * @return une liste contenant les {@link TypeMirror} représentant les interfaces {@link java.io.Serializable} et
   * {@link java.lang.Comparable} implémentées par la classe
   * {@link fr.devoxx.niveau2.exo1.etape3.ImplementsInterfacesClass}
   */
  public List<? extends TypeMirror> extractInterfaces(@Nonnull TypeElement typeElement) {
    throw new UnsupportedOperationException();
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
   * @param typeElement le {@link TypeElement} de {@link fr.devoxx.niveau2.exo1.etape3.ImplementsInterfacesClass}
   *
   * @return une liste contenant les {@link TypeElement} des interfaces {@link java.io.Serializable} et
   * {@link java.lang.Comparable}
   */
  public List<TypeElement> extractInterfaceAsTypeElements(@Nonnull TypeElement typeElement) {
    throw new UnsupportedOperationException();
  }

  /**
   * Extrait le {@link TypeMirror} qui représente l'interface {@link java.io.Serializable} à partir du
   * {@link TypeElement} représentant la classe.
   * <p>
   * Pour identifier le bon {@link TypeMirror}, il convient de tester si son {@link Element} est celui de
   * {@link java.io.Serializable}.
   * </p>
   * <p>
   * (remarque: comme indiqué dans la Javadoc de {@link javax.lang.model.element.Element}, deux instances doivent
   * être comparée par leur méthode {@link java.lang.Object#equals(Object)}).
   * </p>
   * <p>
   * (astuce: une instance de {@link javax.lang.model.element.Element} représentant n'importe qu'elle classe/interface
   * disponible dans le class loader du Processor peut être récupérée grâce à la méthode
   * {@link javax.lang.model.util.Elements#getTypeElement(CharSequence)})
   * </p>
   *
   * @param typeMirrors la liste des {@link TypeMirror} des interfaces implémentées par
   *                   {@link fr.devoxx.niveau2.exo1.etape3.ImplementsInterfacesClass}
   *
   * @return le {@link TypeElement} qui représente l'interface {@link java.io.Serializable}
   */
  public TypeMirror extractSerializableInterface(@Nonnull List<? extends TypeMirror> typeMirrors) {
    throw new UnsupportedOperationException();
  }

}
