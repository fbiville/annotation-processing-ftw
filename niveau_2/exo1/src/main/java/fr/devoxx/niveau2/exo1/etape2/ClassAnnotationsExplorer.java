package fr.devoxx.niveau2.exo1.etape2;

import java.util.List;
import javax.annotation.Nonnull;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * ClassAnnotationsExplorer -
 *
 * @author Sébastien Lesaint
 */
public class ClassAnnotationsExplorer {
  @Nonnull
  private final Elements elementUtils;
  @Nonnull
  private final Types typeUtils;

  public ClassAnnotationsExplorer(@Nonnull Elements elementUtils, @Nonnull Types typeUtils) {
    this.elementUtils = elementUtils;
    this.typeUtils = typeUtils;
  }

  /**
   * Extrait la liste des annotations de la classe sous forme de {@link AnnotationMirror}.
   *
   * @param annotatedClassElement le {@link Element} de {@link fr.devoxx.niveau2.exo1.etape2.AnnotatedClass}
   *
   * @return une liste contenu un {@link AnnotationMirror} représentant l'annotation {@link fr.devoxx.niveau2.exo1.Flag}
   * puis un représentant l'annotation {@link Meta}
   */
  public List<? extends AnnotationMirror> extractAnnotations(@Nonnull Element annotatedClassElement) {
    throw new UnsupportedOperationException();
  }

  /**
   * Extrait l'annotation {@link Meta} de la classe.
   *
   * @param annotatedClassElement le {@link Element} de {@link fr.devoxx.niveau2.exo1.etape2.AnnotatedClass}
   *
   * @return l'instance de {@link Meta} sur la classe {@link fr.devoxx.niveau2.exo1.etape2.AnnotatedClass}
   */
  public Meta extractMetaAnnotation(@Nonnull Element annotatedClassElement) {
    throw new UnsupportedOperationException();
  }

  /**
   * Extrait la valeur du membre "value" de l'annotation {@link Meta} sur la classe.
   *
   * @param annotatedClassElement le {@link Element} de {@link fr.devoxx.niveau2.exo1.etape2.AnnotatedClass}
   *
   * @return "La robe est blanc et or, moi je te dis !"
   */
  public String extractMetaAnnotationValue(@Nonnull Element annotatedClassElement) {
    throw new UnsupportedOperationException();
  }

  /**
   * Extrait la valeur du membre "id" de l'annotation {@link fr.devoxx.niveau2.exo1.etape2.Meta} sur la classe à partir
   * de l'{@link AnnotationMirror} qui représente l'annotation.
   * <p>
   * Notez qu'aucune valeur n'est donnée au membre "id" sur la classe
   * {@link fr.devoxx.niveau2.exo1.etape2.AnnotatedClass}, la valeur récupérée est donc la valeur par défaut. L'API
   * ne permet pas de savoir si la valeur a été définie explicitement sur la classe
   * {@link fr.devoxx.niveau2.exo1.etape2.AnnotatedClass} ou pas (mais est-ce important ?).
   * </p>
   *
   * @param annotatedClassElement le {@link Element} de {@link AnnotatedClass}
   *
   * @return -99812
   */
  public int extractMetaAnnotationId(@Nonnull Element annotatedClassElement) {
    throw new UnsupportedOperationException();
  }

}
