/*-
 * =================================LICENSE_START==================================
 * args4j
 * ====================================SECTION=====================================
 * Copyright (C) 2024 Andy Boothe
 * ====================================SECTION=====================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ==================================LICENSE_END===================================
 */
package com.sigpwned.args4j.util;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.Collections.unmodifiableList;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility functions for lists, mostly just backports of Java 9+ methods for use here in Java 8.
 */
public final class Lists {
  private Lists() {}

  public static <T> List<T> of() {
    return emptyList();
  }

  public static <T> List<T> of(T t) {
    return singletonList(t);
  }

  public static <T> List<T> of(T first, T second) {
    List<T> result = new ArrayList<>(2);
    result.add(first);
    result.add(second);
    return unmodifiableList(result);
  }

  @SuppressWarnings("unchecked")
  public static <T> List<T> of(T... elements) {
    return unmodifiableList(asList(elements));
  }
}
