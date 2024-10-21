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

import java.util.List;
import com.sigpwned.args4j.Dialect;
import com.sigpwned.args4j.impl.DefaultArgsParser;
import com.sigpwned.args4j.model.Token;

public final class Args {
  private Args() {}

  /**
   * Parses the given arguments using the given dialect. This is the core method for parsing program
   * command-line arguments.
   * 
   * @param dialect the dialect to use for parsing
   * @param args the arguments to parse, typically from the main function
   * @return the parsed tokens
   * @throws IllegalArgumentException if the arguments are invalid
   */
  public static List<Token> parse(Dialect dialect, List<String> args) {
    return new DefaultArgsParser(dialect).parseArguments(args);
  }
}
