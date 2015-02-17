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

solution: javac -XprintRounds -processor HelloWorldProcessor SomeDeprecatedClass.java

Constatez qu'il y a deux rounds, ce qui explique l'affichage du "Hello world!" en doublon.

### étape 2: supprimer le doublon avec l'API

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

### étape 3: supprimer le doublon sans l'API

Le cycle de vie d'un objet implémentation l'interface `Processor` est le suivant (citation de la JSR-269, javadoc de l'interface `Processor`):

Each implementation of a Processor must provide a public no-argument constructor to be used by tools to instantiate the processor. The tool infrastructure will interact with classes implementing this interface as follows:
    1. If an existing Processor object is not being used, to create an instance of a processor the tool calls the no-arg constructor of the processor class.
    2. Next, the tool calls the init method with an appropriate ProcessingEnvironment.
    3. Afterwards, the tool calls getSupportedAnnotationTypes, getSupportedOptions, and getSupportedSourceVersion. These methods are only called once per run, not on each round.
    4. As appropriate, the tool calls the process method on the Processor object; a new Processor object is not created for each round. 

En substance, il faut comprendre qu'une seule instance d'un annotation processor est créé par compilation. Il est donc tout à fait possible de traiter ce problème en exploitant l'aspect "statefull" des instances d'annotation processor (ici, c'est pas très propre, mais ça marche, et dans la vraie vie, c'est parfois indispensable).

solution: il suffit d'un flag stockée en propriété de la classe `HelloWorldProcessor`.


