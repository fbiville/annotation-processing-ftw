package fr.devoxx.niveau1.exo4;

/**
 * SomeOverridingClass - Cette classe override la méthode {@code toString()} de sorte que l'annotation {@code @Override}
 * est présente et déclenche le traitement défini dans le processor {@code OverrideJohns}. Elle définit également
 * une méthode avec l'annotation {@link Deprecated} afin de déclencher le traitement défini dans le processor {@code DeprecatedCodeWhistleblower}.
 */
public class SomeOverridingClass {
  @Override
  public String toString() {
    return "Look Ma! I override !";
  }

  @Deprecated
  public String getJavascript() {
    return "doh!";
  }
}
