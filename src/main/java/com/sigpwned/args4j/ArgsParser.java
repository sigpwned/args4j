/*-
 * =================================LICENSE_START==================================
 * args4j
 * ====================================SECTION=====================================
 * Copyright (C) 2024 - 2025 Andy Boothe
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

import java.util.List;
import com.sigpwned.args4j.model.Token;
import com.sigpwned.args4j.util.Lists;

public interface ArgsParser {
  /**
   * Parses the given arguments into {@link Token tokens}.
   * 
   * @param args the arguments to parse, typically from the main function
   * @return the parsed tokens
   * @throws InvalidSyntaxException if the arguments are invalid
   */
  public List<Token> parseArguments(List<String> args);

  /**
   * Parses the given arguments.
   * 
   * @see #parseArguments(List)
   */
  default List<Token> parseArguments(String[] args) {
    return parseArguments(Lists.of(args));
  }
}
