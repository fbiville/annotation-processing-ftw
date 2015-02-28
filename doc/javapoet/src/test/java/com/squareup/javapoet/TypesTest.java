/*
 * Copyright (C) 2014 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.squareup.javapoet;

import com.google.testing.compile.CompilationRule;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import org.junit.Rule;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.fail;

public final class TypesTest {
  @Rule public final CompilationRule compilation = new CompilationRule();

  private TypeElement getElement(Class<?> clazz) {
    return compilation.getElements().getTypeElement(clazz.getCanonicalName());
  }

  private TypeMirror getMirror(Class<?> clazz) {
    return getElement(clazz).asType();
  }

  @Test public void getBasicTypeMirror() {
    assertThat(TypeName.get(getMirror(Object.class)))
        .isEqualTo(ClassName.get(Object.class));
    assertThat(TypeName.get(getMirror(Charset.class)))
        .isEqualTo(ClassName.get(Charset.class));
    assertThat(TypeName.get(getMirror(TypesTest.class)))
        .isEqualTo(ClassName.get(TypesTest.class));
  }

  @Test public void getParameterizedTypeMirror() {
    DeclaredType setType =
        compilation.getTypes().getDeclaredType(getElement(Set.class), getMirror(Object.class));
    assertThat(TypeName.get(setType))
        .isEqualTo(ParameterizedTypeName.get(ClassName.get(Set.class), ClassName.OBJECT));
  }

  static class Parameterized<
      Simple,
      ExtendsClass extends Number,
      ExtendsInterface extends Runnable,
      ExtendsTypeVariable extends Simple,
      Intersection extends Number & Runnable,
      IntersectionOfInterfaces extends Runnable & Serializable> {}

  @Test public void getTypeVariableTypeMirror() {
    List<? extends TypeParameterElement> typeVariables =
        getElement(Parameterized.class).getTypeParameters();

    // Members of converted types use ClassName and not Class<?>.
    ClassName number = ClassName.get(Number.class);
    ClassName runnable = ClassName.get(Runnable.class);
    ClassName serializable = ClassName.get(Serializable.class);

    assertThat(TypeName.get(typeVariables.get(0).asType()))
        .isEqualTo(TypeVariableName.get("Simple"));
    assertThat(TypeName.get(typeVariables.get(1).asType()))
        .isEqualTo(TypeVariableName.get("ExtendsClass", number));
    assertThat(TypeName.get(typeVariables.get(2).asType()))
        .isEqualTo(TypeVariableName.get("ExtendsInterface", runnable));
    assertThat(TypeName.get(typeVariables.get(3).asType()))
        .isEqualTo(TypeVariableName.get("ExtendsTypeVariable", TypeVariableName.get("Simple")));
    assertThat(TypeName.get(typeVariables.get(4).asType()))
        .isEqualTo(TypeVariableName.get("Intersection", number, runnable));
    assertThat(TypeName.get(typeVariables.get(5).asType()))
        .isEqualTo(TypeVariableName.get("IntersectionOfInterfaces", runnable, serializable));
    assertThat(((TypeVariableName) TypeName.get(typeVariables.get(4).asType())).bounds)
        .containsExactly(number, runnable);
  }

  @Test public void getPrimitiveTypeMirror() {
    assertThat(TypeName.get(compilation.getTypes().getPrimitiveType(TypeKind.BOOLEAN)))
        .isEqualTo(TypeName.BOOLEAN);
    assertThat(TypeName.get(compilation.getTypes().getPrimitiveType(TypeKind.BYTE)))
        .isEqualTo(TypeName.BYTE);
    assertThat(TypeName.get(compilation.getTypes().getPrimitiveType(TypeKind.SHORT)))
        .isEqualTo(TypeName.SHORT);
    assertThat(TypeName.get(compilation.getTypes().getPrimitiveType(TypeKind.INT)))
        .isEqualTo(TypeName.INT);
    assertThat(TypeName.get(compilation.getTypes().getPrimitiveType(TypeKind.LONG)))
        .isEqualTo(TypeName.LONG);
    assertThat(TypeName.get(compilation.getTypes().getPrimitiveType(TypeKind.CHAR)))
        .isEqualTo(TypeName.CHAR);
    assertThat(TypeName.get(compilation.getTypes().getPrimitiveType(TypeKind.FLOAT)))
        .isEqualTo(TypeName.FLOAT);
    assertThat(TypeName.get(compilation.getTypes().getPrimitiveType(TypeKind.DOUBLE)))
        .isEqualTo(TypeName.DOUBLE);
  }

  @Test public void getArrayTypeMirror() {
    assertThat(TypeName.get(compilation.getTypes().getArrayType(getMirror(Object.class))))
        .isEqualTo(ArrayTypeName.of(ClassName.OBJECT));
  }

  @Test public void getVoidTypeMirror() {
    assertThat(TypeName.get(compilation.getTypes().getNoType(TypeKind.VOID)))
        .isEqualTo(TypeName.VOID);
  }

  @Test public void getNullTypeMirror() {
    try {
      TypeName.get(compilation.getTypes().getNullType());
      fail();
    } catch (IllegalArgumentException expected) {
    }
  }

  @Test public void parameterizedType() throws Exception {
    ParameterizedTypeName type = ParameterizedTypeName.get(Map.class, String.class, Long.class);
    assertThat(type.toString()).isEqualTo("java.util.Map<java.lang.String, java.lang.Long>");
  }

  @Test public void arrayType() throws Exception {
    ArrayTypeName type = ArrayTypeName.of(String.class);
    assertThat(type.toString()).isEqualTo("java.lang.String[]");
  }

  @Test public void wildcardExtendsType() throws Exception {
    WildcardTypeName type = WildcardTypeName.subtypeOf(CharSequence.class);
    assertThat(type.toString()).isEqualTo("? extends java.lang.CharSequence");
  }

  @Test public void wildcardExtendsObject() throws Exception {
    WildcardTypeName type = WildcardTypeName.subtypeOf(Object.class);
    assertThat(type.toString()).isEqualTo("?");
  }

  @Test public void wildcardSuperType() throws Exception {
    WildcardTypeName type = WildcardTypeName.supertypeOf(String.class);
    assertThat(type.toString()).isEqualTo("? super java.lang.String");
  }

  @Test public void typeVariable() throws Exception {
    TypeVariableName type = TypeVariableName.get("T", CharSequence.class);
    assertThat(type.toString()).isEqualTo("T"); // (Bounds are only emitted in declaration.)
  }
}
