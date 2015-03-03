package fr.devoxx.niveau3.exo1;

/**
 * SomeAnnotation -
 *
 * @author Sébastien Lesaint
 */
@java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.SOURCE)
@java.lang.annotation.Documented
public @interface Personnage {
  boolean mort();
}
