# Niveau 1

## exo 1: en ligne de commande !

### étape 1: compiler un annotation processor

Le première étape de l'exercice consiste à utiliser `javac` en ligne de commande pour compiler un annotation processor. 

La compilation d'un annotation processor est identique à n'importe quelle classe.

Essayez de compiler `StutteringHelloWorldProcessor.java` qui se trouve dans le répertoire exo1 (le plus simple fonctionne).

solution: cd exo1 && javac StutteringHelloWorldProcessor

### étape 2: utiliser un annotation processor

Pour utiliser en annotation processor, il suffit de spécifier à `javac` le nom de la ou les classes à utiliser via un argument de la ligne de commande lors de la compilation.

Essayez de compiler `SomeDeprecatedClass.java` (ici encore, le plus simple fonctionnera).

Vous devez voir s'afficher les 2 lignes suivantes dans la console:

```
Note: Hello world!
Note: Hello world!
```

solution: cd exo1 && javac -processor StutteringHelloWorldProcessor SomeDeprecatedClass.java

Maintenant, compilez en même temps `SomeDeprecatedClass.java` et `SomeOtherDeprecatedClass.java`. Constatez que le résultat dans la console est le même.

### étape 3: bonus

Vous pouvez vérifier qu'aucun message ne s'affiche si vous commentez l'annotation `@Deprecated` des classes que vous compilez (`SomeDeprecatedClass` et/ou `SomeOtherDeprecatedClass.java`).

Vous pouvez vérifier le comportement de `javac` si vous spécifiez le nom d'un annotation processor qui ne se trouve pas dans le class path.

etc...

## exo 2: rounds et cycle de vie

Lors de l'étape 2 de l'exo 1, vous vous êtes sûrement demandé pour quelle raison deux lignes s'affichent et constaté que ce n'est pas lié au nombre de fichiers compilés.

Ce comportement est en réalité lié au mécanisme de "round" de l'annotation processing et à l'implémentation de `HelloWorldProcessor.java` que nous utilisons.

### étape 1: activer les logs des rounds

Une option de `javac` permet d'activer les logs des rounds d'annotations processing. Consultez la doc de `javac` pour l'identifier.

Compilez `HelloWorldProcessor.java` dans le répetoire `exo2` puis `SomeDeprecatedClass` en activant les rounds.

solution: cd exo2 && javac -XprintRounds -processor HelloWorldProcessor SomeDeprecatedClass.java

Constatez qu'il y a deux rounds, ce qui explique l'affichage du "Hello world!" en doublon.

### étape 2: supprimer le doublon à la bourrin

Le cycle de vie d'un objet implémentation l'interface `Processor` est le suivant (citation de la JSR-269, javadoc de l'interface `Processor`):

Each implementation of a Processor must provide a public no-argument constructor to be used by tools to instantiate the processor. The tool infrastructure will interact with classes implementing this interface as follows:
    1. If an existing Processor object is not being used, to create an instance of a processor the tool calls the no-arg constructor of the processor class.
    2. Next, the tool calls the init method with an appropriate ProcessingEnvironment.
    3. Afterwards, the tool calls getSupportedAnnotationTypes, getSupportedOptions, and getSupportedSourceVersion. These methods are only called once per run, not on each round.
    4. As appropriate, the tool calls the process method on the Processor object; a new Processor object is not created for each round. 

En substance, il faut comprendre qu'une seule instance d'un annotation processor est créé par compilation. Il est donc tout à fait possible de traiter ce problème en exploitant l'aspect "statefull" des instances d'annotation processor (ici, c'est pas très propre, mais ça marche, et dans la vraie vie, c'est parfois indispensable).

solution: il suffit d'un flag stockée en propriété de la classe `HelloWorldProcessor`.

### étape 3: supprimer le doublon proprement

Corrigez le doublon en utilisant l'API (astuce: regardez du côté de `RoundEnvironment`).

solution: 

```java
  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    if (roundEnv.processingOver()) {
      processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Hello world!");
    }
    return false;
  }
```

## exo3: la même chose avec Maven

Dans le répertoire `exo3`, vous trouverez deux projets Maven et les classes de l'exo 2:

 * répertoire `processor`: le projet `processor-exo3` produit un `jar` qui contient la classe `fr.devoxx.niveau1.exo3.HelloWorldProcessor`.
 * répertoire `subject`: le projet `subject-exo3` contient la classe `fr.devoxx.niveau1.exo3.SomeDeprecatedClass`

### étape 1: déclarer le processor

Compilez le projet `processor-exo3` (pensez au `install`) puis `subject-exo3` (`compile` suffit), constatez qu'aucune ligne `Hello world!` ne s'affiche dans les traces Maven.

De la même manière qu'en utilisant `javac` à la main il faut ajouter une ligne de commande pour déclarer un annotation processor, avec Maven il faut ajouter quelques lignes dans le `pom.xml`.

Le plugin Maven qui se charge de la compilation (et fait donc l'interface entre Maven et le compilateur) est le `maven-compiler-plugin`.

Trouvez comment déclarer le processor `fr.devoxx.niveau1.exo3.HelloWorldProcessor`, recompilez et consatez que le message suivant s'affiche dans les logs Maven:

```
[WARNING] Hello world!
```

solution:
ajout dans la configuration de `maven-compiler-plugin` des 3 lignes suivantes:
```
<annotationProcessors>
  <annotationProcessor>fr.devoxx.niveau1.exo3.HelloWorldProcessor</annotationProcessor>
</annotationProcessors>
```
