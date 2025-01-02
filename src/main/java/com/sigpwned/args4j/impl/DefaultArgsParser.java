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
package com.sigpwned.args4j.impl;

import static java.util.Collections.unmodifiableList;
import static java.util.Objects.requireNonNull;
import java.util.ArrayList;
import java.util.List;
import com.sigpwned.args4j.ArgTokenizer;
import com.sigpwned.args4j.ArgsParser;
import com.sigpwned.args4j.Dialect;
import com.sigpwned.args4j.InvalidSyntaxException;
import com.sigpwned.args4j.model.Token;

public class DefaultArgsParser implements ArgsParser {
  private final Dialect dialect;

  public DefaultArgsParser(Dialect dialect) {
    this.dialect = requireNonNull(dialect);
  }

  @Override
  public List<Token> parseArguments(List<String> args) {
    final List<Token> result = new ArrayList<>();

    final ArgTokenizer tokenizer = dialect.newTokenizer();
    for (int i = 0; i < args.size(); i++) {
      final int index = i;
      final String arg = args.get(index);
      result.addAll(tokenizer.tokenize(arg).orElseThrow(() -> {
        // If none of our tokenizers can handle this argument, then it's invalid syntax, and we
        // should throw an exception.
        return new InvalidSyntaxException(index, arg);
      }));
    }

    return unmodifiableList(result);
  }

  public Dialect getDialect() {
    return dialect;
  }
}
