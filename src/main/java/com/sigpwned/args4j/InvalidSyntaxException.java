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
package com.sigpwned.args4j;

import static java.util.Objects.requireNonNull;

/**
 * Thrown when an argument cannot be parsed.
 */
@SuppressWarnings("serial")
public class InvalidSyntaxException extends IllegalArgumentException {
  private final int index;
  private final String argument;

  public InvalidSyntaxException(int index, String argument) {
    super("invalid syntax at index " + index + ": " + argument);
    if (index < 0)
      throw new IllegalArgumentException("index must be non-negative");
    this.index = index;
    this.argument = requireNonNull(argument);
  }

  public int getIndex() {
    return index;
  }

  public String getArgument() {
    return argument;
  }
}
