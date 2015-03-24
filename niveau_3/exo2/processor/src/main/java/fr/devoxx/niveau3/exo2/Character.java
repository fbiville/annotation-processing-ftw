package fr.devoxx.niveau3.exo2;

/**
 * Character -
 *
 * @author Sébastien Lesaint
 */
@java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.SOURCE)
@java.lang.annotation.Documented
public @interface Character {
  boolean dead();
}
