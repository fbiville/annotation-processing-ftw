package fr.devoxx.niveau2.exo1.etape4;

import javax.annotation.Nonnull;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * ClassSuperClassExplorer -
 *
 * @author Sébastien Lesaint
 */
public class ClassSuperClassExplorer {
  @Nonnull
  private final Elements elementUtils;
  @Nonnull
  private final Types typeUtils;

  public ClassSuperClassExplorer(@Nonnull Elements elementUtils, @Nonnull Types typeUtils) {
    this.elementUtils = elementUtils;
    this.typeUtils = typeUtils;
  }

  /**
   * @param myListClassTypeElement le {@link TypeElement} de {@link fr.devoxx.niveau2.exo1.etape4.MyListClass}
   *
   * @return le {@link TypeMirror} de la déclaration de {@link java.util.ArrayList} comme super classe de
   * {@link fr.devoxx.niveau2.exo1.etape4.MyListClass}
   */
  public TypeMirror extractSuperClass(@Nonnull TypeElement myListClassTypeElement) {
    throw new UnsupportedOperationException();
  }

  /**
   * @param arrayListTypeMirror le {@link TypeMirror} de la déclaration de {@link java.util.ArrayList} comme super
   *                            classe de {@link fr.devoxx.niveau2.exo1.etape4.MyListClass}
   *
   * @return
   */
  public DeclaredType asDeclaredType(@Nonnull TypeMirror arrayListTypeMirror) {
    throw new UnsupportedOperationException();
  }

  /**
   * Extrait le {@link TypeMirror} qui représente l'argument générique de la déclaration de super classe de
   * {@link fr.devoxx.niveau2.exo1.etape4.MyListClass}.
   * <p>
   * L'objectif de cette méthode est d'extraire le {@link TypeMirror} qui représente l'argument générique "String" dans
   * la déclaration de super classe de {@link fr.devoxx.niveau2.exo1.etape4.MyListClass} :
   * {@code extends ArrayList<String>"}. Cette information est portée par le {@link TypeMirror} de la super classe
   * de {@link fr.devoxx.niveau2.exo1.etape4.MyListClass}.
   * </p>
   * <p>
   * Cette méthode et la suivante ont pour objectif de mettre en avant la différence entre un type (classe dans le
   * package "javax.lang.model.type") et un element (classe dans le package "javax.lang.model.element"). Et pour cela, les
   * génériques sont l'illustration la plus efficace.
   * </p>
   *
   * @param myListClassTypeElement le {@link TypeElement} de {@link fr.devoxx.niveau2.exo1.etape4.MyListClass}
   *
   * @return le {@link TypeMirror} représentant l'argument générique "String" dans la déclaration de super class
   * {@code extends ArrayList<String>"}
   */
  public TypeMirror extractSuperClassTypeArgument(@Nonnull TypeElement myListClassTypeElement) {
    throw new UnsupportedOperationException();
  }

  /**
   * Extrait le {@link Name} qui représente le paramètre générique de la super classe de
   * {@link fr.devoxx.niveau2.exo1.etape4.MyListClass}: {@link java.util.ArrayList}.
   * <p>
   * L'objectif de cette méthode est d'extraire le {@link TypeParameterElement} qui représente le paramètre générique
   * de la classe {@link java.util.ArrayList}. Cette information est portée par le {@link TypeElement} qui représente la
   * classe {@link java.util.ArrayList}.
   * </p>
   * <p>
   * Cette méthode et la précédente ont pour objectif de mettre en avant la différence entre un type (classe dans le
   * package "javax.lang.model.type") et un element (classe dans le package "javax.lang.model.element"). Et pour cela, les
   * génériques sont l'illustration la plus efficace.
   * </p>
   *
   * @param myListClassTypeElement le {@link TypeElement} de {@link fr.devoxx.niveau2.exo1.etape4.MyListClass}
   *
   * @return {@link Name} représentant le paramètre générique "E" dans la déclaration de classe de
   * {@link java.util.ArrayList}
   */
  public Name extractSuperClassTypeParameterName(@Nonnull TypeElement myListClassTypeElement) {
    throw new UnsupportedOperationException();
  }

}
