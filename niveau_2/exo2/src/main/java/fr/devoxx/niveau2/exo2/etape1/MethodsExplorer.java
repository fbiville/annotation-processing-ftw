package fr.devoxx.niveau2.exo2.etape1;

import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * MethodsExplorer -
 *
 * @author Sébastien Lesaint
 */
public class MethodsExplorer {
  @Nonnull
  private final Elements elementUtils;
  @Nonnull
  private final Types typeUtils;

  public MethodsExplorer(@Nonnull Elements elementUtils, @Nonnull Types typeUtils) {
    this.elementUtils = elementUtils;
    this.typeUtils = typeUtils;
  }

  /**
   * Extrait la liste des méthodes définies par la classe {@link fr.devoxx.niveau2.exo2.etape1.Homer} à partir du
   * {@link TypeElement} qui la représente.
   * <p>
   * Les méthodes, fonctions, champs sont des elements contenus dans l'élément de la classe (ou de l'interface,
   * d'ailleurs).
   * </p>
   * <p>
   * (astuce: connaissez-vous la classe {@link javax.lang.model.util.ElementFilter} ?)
   * </p>
   *
   * @param homerTypeElement le {@link TypeElement} de la class {@link fr.devoxx.niveau2.exo2.etape1.Homer}
   *
   * @return la liste des méthodes (ie. pas les constructeurs) sous forme de {@link ExecutableElement}
   */
  public List<ExecutableElement> extractMethods(@Nonnull TypeElement homerTypeElement) {
    throw new UnsupportedOperationException();
  }


  /**
   * Extrait la liste des constructeurs (en fait, un seul) définis par la classe
   * {@link fr.devoxx.niveau2.exo2.etape1.Homer} à partir du {@link TypeElement} qui la représente.
   * <p>
   * (astuce: avez-vous bien regardé la classe {@link javax.lang.model.util.ElementFilter} ?)
   * </p>
   *
   * @param homerTypeElement le {@link TypeElement} de la class {@link fr.devoxx.niveau2.exo2.etape1.Homer}
   *
   * @return une liste contenant le constructeur de {@link fr.devoxx.niveau2.exo2.etape1.Homer} sous forme de
   * {@link ExecutableElement}
   */
  public List<ExecutableElement> extractConstructors(@Nonnull TypeElement homerTypeElement) {
    throw new UnsupportedOperationException();
  }

  /**
   * Extrait la liste des constructeurs et méthodes publiques définis par la classe
   * {@link fr.devoxx.niveau2.exo2.etape1.Homer} à partir du {@link TypeElement} qui la représente.
   * <p>
   * (astuce: les attributs sont définis au niveau de l'interface {@link javax.lang.model.element.Element})
   * </p>
   *
   * @param homerTypeElement le {@link TypeElement} de la class {@link fr.devoxx.niveau2.exo2.etape1.Homer}
   *
   * @return une liste contenant toutes les méthodes et constructeurs publiques de
   * {@link fr.devoxx.niveau2.exo2.etape1.Homer} sous forme de {@link ExecutableElement}
   */
  public List<ExecutableElement> extractPublicMethodsAndConstructors(@Nonnull TypeElement homerTypeElement) {
    throw new UnsupportedOperationException();
  }

  /**
   * Extrait la liste des fonctions publiques (ie. méthodes qui ne retourne pas {@code void}) définies par la classe
   * {@link fr.devoxx.niveau2.exo2.etape1.Homer} à partir du {@link TypeElement} qui la représente.
   *
   * @param homerTypeElement le {@link TypeElement} de la class {@link fr.devoxx.niveau2.exo2.etape1.Homer}
   *
   * @return une liste contenant tous les fonctions publiques de {@link fr.devoxx.niveau2.exo2.etape1.Homer} sous forme
   * de {@link ExecutableElement}
   */
  public List<ExecutableElement> extractPublicFunctions(@Nonnull TypeElement homerTypeElement) {
    throw new UnsupportedOperationException();
  }

  /**
   * Extrait les méthodes "eatDonuts" définies par la classe {@link fr.devoxx.niveau2.exo2.etape1.Homer} à partir du
   * {@link TypeElement} qui la représente.
   *
   * @param homerTypeElement le {@link TypeElement} de la class {@link fr.devoxx.niveau2.exo2.etape1.Homer}
   *
   * @return une liste contenant le constructeur de {@link fr.devoxx.niveau2.exo2.etape1.Homer} sous forme de
   * {@link ExecutableElement}
   */
  public List<ExecutableElement> extractEatDonutsMethods(@Nonnull TypeElement homerTypeElement) {
    throw new UnsupportedOperationException();
  }

  /**
   * Extrait la méthode "eatDonuts" non dépréciée définie par la classe {@link fr.devoxx.niveau2.exo2.etape1.Homer} à
   * partir du {@link TypeElement} qui la représente.
   *
   * @param homerTypeElement le {@link TypeElement} de la class {@link fr.devoxx.niveau2.exo2.etape1.Homer}
   *
   * @return le {@link ExecutableElement} qui représente la surcharge non dépréciée de la méthode "eatDonuts"
   */
  public ExecutableElement extractCurrentEatDonutsMethod(@Nonnull TypeElement homerTypeElement) {
    throw new UnsupportedOperationException();
  }

  /**
   * Extrait les paramètres de méthode portant l'annotation {@link javax.annotation.Nullable} définis par la classe
   * {@link fr.devoxx.niveau2.exo2.etape1.Homer} à partir du {@link TypeElement} qui la représente.
   * <p>
   * (indice: on peut obtenir le {@link TypeMirror} de tout {@link Element} via la méthode {@code asType()})
   * </p>
   *
   * @param homerTypeElement le {@link TypeElement} de la class {@link fr.devoxx.niveau2.exo2.etape1.Homer}
   *
   * @return un Set contenant le {@link TypeMirror} de {@link java.lang.String} et
   * {@link fr.devoxx.niveau2.exo2.etape1.Homer.Flavor}
   */
  public Set<TypeMirror> extractTypeOfNullableParameters(@Nonnull TypeElement homerTypeElement) {
    throw new UnsupportedOperationException();
  }

}
