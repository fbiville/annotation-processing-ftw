package fr.devoxx.niveau2.exo2.etape1;

import java.util.List;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.SimpleElementVisitor6;
import javax.lang.model.util.Types;

import static java.util.stream.Collectors.toList;

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
    return ElementFilter.methodsIn(homerTypeElement.getEnclosedElements());
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
    return ElementFilter.constructorsIn(homerTypeElement.getEnclosedElements());
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
    return homerTypeElement.getEnclosedElements()
                                  .stream()
                                  .filter(s -> s.getModifiers().contains(Modifier.PUBLIC))
                                  .map(this::asExecutableElement)
                                  .filter(Objects::nonNull)
                                  .collect(toList());
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
    return extractPublicMethodsAndConstructors(homerTypeElement)
        .stream()
        .filter(s -> s.getReturnType().getKind() == TypeKind.VOID)
        .collect(toList());
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
    return homerTypeElement.getEnclosedElements()
                           .stream()
                           .map(this::asExecutableElement)
                           .filter(Objects::nonNull)
                           .filter(s -> s.getSimpleName().contentEquals("eatDonuts"))
                           .collect(toList());
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
    return extractEatDonutsMethods(homerTypeElement)
        .stream()
        .filter(s -> s.getAnnotation(Deprecated.class) == null)
        .findFirst()
        .get();
  }

  private ExecutableElement asExecutableElement(@Nonnull Element methodElement) {
    return methodElement.accept(
        new SimpleElementVisitor6<ExecutableElement, Void>() {
          @Override
          public ExecutableElement visitExecutable(ExecutableElement e, Void aVoid) {
            return e;
          }
        }, null
    );
  }
}
