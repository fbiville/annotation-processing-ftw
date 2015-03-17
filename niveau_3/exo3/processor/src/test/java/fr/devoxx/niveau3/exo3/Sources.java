package fr.devoxx.niveau3.exo3;

import javax.tools.JavaFileObject;

import static com.google.testing.compile.JavaFileObjects.forResource;

public class Sources {

  public static JavaFileObject sourceFile(String className) {
    return forResource(String.format("%s.java", className));
  }
}
