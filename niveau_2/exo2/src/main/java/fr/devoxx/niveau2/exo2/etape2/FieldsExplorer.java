package fr.devoxx.niveau2.exo2.etape2;

import java.util.List;
import javax.annotation.Nonnull;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import static java.util.stream.Collectors.toList;

/**
 * FieldsExplorer -
 *
 * @author Sébastien Lesaint
 */
public class FieldsExplorer {
  @Nonnull
  private final Elements elementUtils;
  @Nonnull
  private final Types typeUtils;

  public FieldsExplorer(@Nonnull Elements elementUtils, @Nonnull Types typeUtils) {
    this.elementUtils = elementUtils;
    this.typeUtils = typeUtils;
  }

  /**
   * Extrait la liste des champs définis par la classe {@link fr.devoxx.niveau2.exo2.etape2.Cow} à partir du
   * {@link TypeElement} qui la représente.
   * <p>
   * Les méthodes, constructors, champs sont des elements contenus dans l'élément de la classe.
   * </p>
   * <p>
   * (astuce: connaissez-vous la classe {@link javax.lang.model.util.ElementFilter} ?)
   * </p>
   *
   * @param cowTypeElement le {@link TypeElement} de la class {@link fr.devoxx.niveau2.exo2.etape2.Cow}
   *
   * @return la liste des champs de la classe sous forme de {@link javax.lang.model.element.VariableElement}
   */
  public List<VariableElement> extractFields(@Nonnull TypeElement cowTypeElement) {
    return ElementFilter.fieldsIn(cowTypeElement.getEnclosedElements());
  }

  /**
   * Extrait le type du champs "name" définis par la classe {@link fr.devoxx.niveau2.exo2.etape2.Cow} à partir du
   * {@link TypeElement} qui la représente.
   * <p>
   * (astuce: la méthode {@link javax.lang.model.element.Name#contentEquals(CharSequence)} est bien pratique)
   * </p>
   *
   * @param cowTypeElement le {@link TypeElement} de la class {@link fr.devoxx.niveau2.exo2.etape2.Cow}
   *
   * @return le {@link TypeMirror} de {@link java.lang.String} pour le champs "name" dans la classe
   * {@link fr.devoxx.niveau2.exo2.etape2.Cow}.
   */
  public TypeMirror extractNameFieldType(@Nonnull TypeElement cowTypeElement) {
    return extractFields(cowTypeElement)
        .stream()
        .filter(field -> field.getSimpleName().contentEquals("name"))
        .map(field -> field.asType())
        .findFirst()
        .get();
  }

  /**
   * Extrait les champs privés ou sans attribut de visibilité définis par la classe
   * {@link fr.devoxx.niveau2.exo2.etape2.Cow} à partir du {@link TypeElement} qui la représente.
   *
   * @param cowTypeElement le {@link TypeElement} de la class {@link fr.devoxx.niveau2.exo2.etape2.Cow}
   *
   * @return la liste des champs privés ou package protected de la classe sous la forme de {@link VariableElement}s
   */
  public List<VariableElement> extractPrivateAndPackageProtectedFields(@Nonnull TypeElement cowTypeElement) {
    return extractFields(cowTypeElement)
        .stream()
        .filter(field -> field.getModifiers().isEmpty() || field.getModifiers().contains(Modifier.PRIVATE))
        .collect(toList());
  }

  /**
   * Extrait les champs dépréciés définis par la classe {@link fr.devoxx.niveau2.exo2.etape2.Cow} à partir du
   * {@link TypeElement} qui la représente.
   *
   * @param cowTypeElement le {@link TypeElement} de la class {@link fr.devoxx.niveau2.exo2.etape2.Cow}
   *
   * @return la liste des champs déprécié de la classe sous la forme de {@link VariableElement}s
   */
  public List<VariableElement> extractDeprecatedField(@Nonnull TypeElement cowTypeElement) {
    return extractFields(cowTypeElement)
        .stream()
        .filter(field -> field.getAnnotation(Deprecated.class) != null)
        .collect(toList());
  }
}
