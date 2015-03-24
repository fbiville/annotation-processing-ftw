package fr.devoxx.niveau2.exo1.etape2;

/**
 * Meta -
 *
 * @author Sébastien Lesaint
 */
public @interface Meta {
  String value() default "la valeur par défaut";
  int id() default -99812;

}
