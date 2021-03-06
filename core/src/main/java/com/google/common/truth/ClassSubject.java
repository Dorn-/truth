/*
 * Copyright (c) 2011 Google, Inc.
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
package com.google.common.truth;

import com.google.common.annotations.GwtIncompatible;

/**
 * Propositions for {@link Class} subjects.
 *
 * @author Kurt Alfred Kluever
 */
public class ClassSubject extends Subject<ClassSubject, Class<?>> {
  ClassSubject(FailureStrategy failureStrategy, Class<?> o) {
    super(failureStrategy, o);
  }

  /**
   * Fails if this class or interface is not the same as or a superclass or superinterface of
   * the given class or interface.
   *
   * @deprecated Use either {@code assertThat(instance).isInstanceOf(clazz)} or {code
   *     assertThat(clazzA).isAssignableTo(clazzB)} instead.
   */
  @GwtIncompatible("isAssignableFrom")
  @Deprecated
  public void isAssignableFrom(Class<?> clazz) {
    if (!getSubject().isAssignableFrom(clazz)) {
      fail("is assignable from", clazz);
    }
  }

  /**
   * Fails if this class or interface is not the same as or a subclass or subinterface of,
   * the given class or interface.
   */
  @GwtIncompatible("isAssignableFrom")
  public void isAssignableTo(Class<?> clazz) {
    if (!clazz.isAssignableFrom(getSubject())) {
      fail("is assignable to", clazz);
    }
  }

  // TODO(user): Create an alternative implementation using JSNI.
  @GwtIncompatible("Reflection")
  public void declaresField(String fieldName) {
    if (getSubject() == null) {
      failureStrategy.fail("Cannot determine a field name from a null class.");
      return; // not all failures throw exceptions.
    }
    try {
      ReflectionUtil.getField(getSubject(), fieldName);
    } catch (NoSuchFieldException e) {
      StringBuilder message = new StringBuilder("Not true that ");
      message.append("<").append(getSubject().getSimpleName()).append(">");
      message.append(" has a field named <").append(fieldName).append(">");
      failureStrategy.fail(message.toString());
    }
  }
}
