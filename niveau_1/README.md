# Niveau 1

## exo 1: en ligne de commande !

### étape 1: compiler un annotation processor

Le première étape de l'exercice consiste à utiliser `javac` en ligne de commande pour compiler un annotation processor. 

La compilation d'un annotation processor est identique à n'importe quelle classe.

Essayez de compiler `HelloWorldProcessor.java` qui se trouve dans le répertoire exo1 (le plus simple fonctionne).

solution: cd exo1 && javac HelloWorldProcessor

### étape 2: utiliser un annotation processor

Pour utiliser en annotation processor, il suffit de spécifier à `javac` le nom de la ou les classes à utiliser via un argument de la ligne de commande lors de la compilation.

Essayez de compiler `SomeDeprecatedClass.java` (ici encore, le plus simple fonctionnera).

Vous devez voir s'afficher les 2 lignes suivantes dans la console:

```
Note: Hello world!
Note: Hello world!
```

solution: cd exo1 && javac -processor HelloWorldProcessor SomeDeprecatedClass.java

Maintenant, compilez en même temps `SomeDeprecatedClass.java` et `SomeOtherDeprecatedClass.java`. Constatez que le résultat dans la console est le même.

### étape 3: bonus

Vous pouvez vérifier qu'aucun message ne s'affiche si vous commentez l'annotation `@Deprecated` des classes que vous compilez (`SomeDeprecatedClass` et/ou `SomeOtherDeprecatedClass.java`).

Vous pouvez vérifier le comportement de `javac` si vous spécifiez le nom d'un annotation processor qui ne se trouve pas dans le class path.

etc...
