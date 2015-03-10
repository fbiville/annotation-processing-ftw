package fr.devoxx.niveau2.exo2.etape3;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * DeepExplorer -
 *
 * @author Sébastien Lesaint
 */
public class DeepExplorer {
  @Nonnull
  private final Elements elementUtils;
  @Nonnull
  private final Types typeUtils;

  public DeepExplorer(@Nonnull Elements elementUtils, @Nonnull Types typeUtils) {
    this.elementUtils = elementUtils;
    this.typeUtils = typeUtils;
  }

  /**
   * Extrait la liste des méthodes définies par la classe {@link fr.devoxx.niveau2.exo2.etape3.Employee} ainsi que
   * celles héritées de {@link fr.devoxx.niveau2.exo2.etape3.Citizen} et {@link java.lang.Object}.
   * <p>
   * (astuce: un {@link javax.lang.model.element.ElementVisitor} bien écrit peut résoudre le problème mais il est plus
   * simple de jeter un oeil {@link javax.lang.model.util.Elements})
   * </p>
   *
   * @param employeeTypeElement le {@link TypeElement} de la class {@link fr.devoxx.niveau2.exo2.etape3.Employee}
   *
   * @return la liste des méthodes de {@link fr.devoxx.niveau2.exo2.etape3.Employee} plus celles héritées de
   * {@link fr.devoxx.niveau2.exo2.etape3.Citizen} et {@link java.lang.Object} sous forme de {@link ExecutableElement}
   */
  public List<ExecutableElement> extractAllMethods(@Nonnull TypeElement employeeTypeElement) {
    return ElementFilter.methodsIn(elementUtils.getAllMembers(employeeTypeElement));
  }

  /**
   * Extrait la liste des méthodes héritées par la classe {@link fr.devoxx.niveau2.exo2.etape3.Employee}.
   * <p>
   * (indice: le {@link javax.lang.model.element.Element} retourné par la méthode
   * {@link javax.lang.model.element.ExecutableElement#getEnclosingElement()} d'une méthode est la classe qui la
   * définie)
   * </p>
   * <p>
   * (remarque: comme indiqué dans la Javadoc de {@link javax.lang.model.element.Element}, deux instances doivent
   * être comparée par leur méthode {@link java.lang.Object#equals(Object)}).
   * </p>
   *
   * @param employeeTypeElement le {@link TypeElement} de la class {@link fr.devoxx.niveau2.exo2.etape3.Employee}
   *
   * @return la méthode {@link fr.devoxx.niveau2.exo2.etape3.Citizen#sayGoodbye()} et les méthodes
   * {@link java.lang.Object} sous forme de {@link ExecutableElement}
   */
  public List<ExecutableElement> extractInheritedMethods(@Nonnull TypeElement employeeTypeElement) {
    return elementUtils.getAllMembers(employeeTypeElement)
                       .stream()
                       .filter(executableElement -> executableElement.getKind() == ElementKind.METHOD)
                       .filter(
                           executableElement -> !executableElement.getEnclosingElement().equals(employeeTypeElement)
                       )
                       .map(ExecutableElement.class::cast)
                       .collect(Collectors.toList());
  }

  /**
   * Extrait la liste des méthodes héritées par la classe {@link fr.devoxx.niveau2.exo2.etape3.Employee} à l'exception
   * de celles héritées de {@link java.lang.Object}.
   * <p>
   * (astuce: une instance de {@link javax.lang.model.element.Element} représentant n'importe qu'elle classe/interface
   * disponible dans le class loader du Processor peut être récupérée grâce à la méthode
   * {@link javax.lang.model.util.Elements#getTypeElement(CharSequence)})
   * </p>
   *
   * @param employeeTypeElement le {@link TypeElement} de la class {@link fr.devoxx.niveau2.exo2.etape3.Employee}
   *
   * @return la liste des méthodes de {@link fr.devoxx.niveau2.exo2.etape3.Employee} et celles héritées
   * {@link fr.devoxx.niveau2.exo2.etape3.Citizen} sous forme de {@link ExecutableElement}
   */
  public List<ExecutableElement> extractInheritedMethodsButObjects(@Nonnull TypeElement employeeTypeElement) {
    return extractInheritedMethods(employeeTypeElement)
        .stream()
        .filter(
            executableElement -> {
              TypeElement objectTypeElement = elementUtils.getTypeElement("java.lang.Object");

              return !executableElement.getEnclosingElement().equals(objectTypeElement);
            }
        )
        .collect(Collectors.toList());
  }

}
