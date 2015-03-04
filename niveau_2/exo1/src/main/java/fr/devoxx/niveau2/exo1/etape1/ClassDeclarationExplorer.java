package fr.devoxx.niveau2.exo1.etape1;

import java.util.Set;
import javax.annotation.Nonnull;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.SimpleElementVisitor6;
import javax.lang.model.util.Types;

/**
 * ClassDeclarationExplorer -
 *
 * @author Sébastien Lesaint
 */
public class ClassDeclarationExplorer {
  @Nonnull
  private final Elements elementUtils;
  @Nonnull
  private final Types typeUtils;

  public ClassDeclarationExplorer(@Nonnull Elements elementUtils, @Nonnull Types typeUtils) {
    this.elementUtils = elementUtils;
    this.typeUtils = typeUtils;
  }

  /**
   * Extrait le nom de la classe à partir du {@link javax.lang.model.element.Element} qui la représente.
   *
   * @param basicClassElement le {@link javax.lang.model.element.Element} de
   *                          {@link fr.devoxx.niveau2.exo1.etape1.BasicClass}
   *
   * @return "BasicClass" sous forme de {@link javax.lang.model.element.Name}
   */
  public Name extractClassName(@Nonnull Element basicClassElement) {
    return basicClassElement.getSimpleName();
  }

  /**
   * Transforme un {@link javax.lang.model.element.Element} en un {@link javax.lang.model.element.TypeElement} si
   * applicable, autrement retourne {@code null}.
   * <p>
   * L'usage de {@code instanceof}, bien que pouvant fonctionner, n'est pas la méthode préconisée par l'API est l'usage
   * d'un {@link javax.lang.model.element.ElementVisitor} et de
   * {@link Element#accept(javax.lang.model.element.ElementVisitor, Object)}
   * (astuce: {@link javax.lang.model.util.SimpleElementVisitor6} fournit un adapteur qui réduira la taille de votre
   * code).
   * </p>
   *
   * @param element le {@link javax.lang.model.element.Element} de {@link fr.devoxx.niveau2.exo1.etape1.BasicClass}
   *
   * @return un {@link javax.lang.model.element.TypeElement} ou {@code null}
   */
  public TypeElement asTypeElement(@Nonnull Element element) {
    return element.accept(
        new SimpleElementVisitor6<TypeElement, Void>() {
          @Override
          public TypeElement visitType(TypeElement e, Void o) {
            return e;
          }
        }, null
    );
  }

  /**
   * Extrait le nom qualifié de la classe à partir du {@link javax.lang.model.element.Element} qui la représente.
   * <p>
   * Tous les {@link javax.lang.model.element.Element} n'ont pas de nom qualifié, mais c'est le cas du type qui
   * représente les classes, {@link TypeElement}.
   * </p>
   *
   * @param basicClassElement le {@link javax.lang.model.element.Element} de
   *                          {@link fr.devoxx.niveau2.exo1.etape1.BasicClass}
   *
   * @return "fr.devoxx.niveau2.exo1.etape1.BasicClass" sous forme de {@link javax.lang.model.element.Name}
   */
  public Name extractClassQualifiedName(@Nonnull Element basicClassElement) {
    return asTypeElement(basicClassElement).getQualifiedName();
  }

  /**
   * Extrait le nom du package de la classe à partir du {@link javax.lang.model.element.TypeElement} qui la
   * représente.
   * <p>
   * Vous pouvez essayer de récupérer le package d'une classe à partir de son {@link TypeElement} en remontant
   * la hiérarchie des élements via le lien {@link Element#getEnclosingElement()} mais ce besoin est tellement
   * commun que {@link Elements} propose une
   * </p>
   *
   * @param basicClassTypeElement le {@link javax.lang.model.element.TypeElement} de
   *                              {@link fr.devoxx.niveau2.exo1.etape1.BasicClass}
   *
   * @return "etape1" sous forme de {@link javax.lang.model.element.Name}
   */
  public PackageElement extractPackage(@Nonnull TypeElement basicClassTypeElement) {
    return elementUtils.getPackageOf(basicClassTypeElement);
  }

  /**
   * Extrait le nom qualifié du package de la classe à partir du {@link javax.lang.model.element.TypeElement} qui la
   * représente.
   *
   * @param basicClassTypeElement le {@link javax.lang.model.element.TypeElement} de
   *                              {@link fr.devoxx.niveau2.exo1.etape1.BasicClass}
   *
   * @return "fr.devoxx.niveau2.exo1.etape1" sous forme de {@link javax.lang.model.element.Name}
   */
  public Name extractPackageName(@Nonnull TypeElement basicClassTypeElement) {
    return extractPackage(basicClassTypeElement).getQualifiedName();
  }

  /**
   * Extrait les "modifiers" de la classe à partir du {@link javax.lang.model.element.TypeElement} qui la
   * représente.
   *
   * @param basicClassTypeElement le {@link javax.lang.model.element.TypeElement} de
   *                              {@link fr.devoxx.niveau2.exo1.etape1.BasicClass}
   *
   * @return "public" et "final" sous la forme de {@link javax.lang.model.element.Modifier} dans un
   * {@link java.util.Set}
   */
  public Set<Modifier> extractModifiers(@Nonnull TypeElement basicClassTypeElement) {
    return basicClassTypeElement.getModifiers();
  }

}
