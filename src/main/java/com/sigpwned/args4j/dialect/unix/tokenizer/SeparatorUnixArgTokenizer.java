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
package com.sigpwned.args4j.dialect.unix.tokenizer;

import java.util.List;
import java.util.Optional;
import com.sigpwned.args4j.ArgTokenizer;
import com.sigpwned.args4j.dialect.unix.UnixDialectElement;
import com.sigpwned.args4j.model.Token;
import com.sigpwned.args4j.model.token.ValueToken;
import com.sigpwned.args4j.util.Lists;

public class SeparatorUnixArgTokenizer implements ArgTokenizer, UnixDialectElement {
  private boolean separatorFound = false;

  /**
   * <p>
   * Handles the special {@link #SEPARATOR separator token}.
   * </p>
   * 
   * <p>
   * Before this tokenizer encounters a separator token, it always returns {@link Optional#empty()
   * empty}. When this tokenizer encounters a separator token for the first time, it will return a
   * list containing zero tokens. After this tokenizer encounters a separator for the first time,
   * all subsequent tokens will be returned as value tokens, even if they equal the separator token,
   * start with {@link ShortSwitchUnixArgTokenizer#SHORT_NAME_PREFIX the short switch prefix}, or
   * start with {@link LongSwitchUnixArgTokenizer#LONG_NAME_PREFIX the long switch prefix}.
   * </p>
   * 
   * <p>
   * This tokenizer is stateful.
   * </p>
   * 
   * 
   * @param arg The token to tokenize.
   */
  @Override
  public Optional<List<Token>> tokenize(String arg) {
    if (isSeparatorFound())
      return Optional.of(Lists.of(new ValueToken(arg, false)));

    if (arg.equals(SEPARATOR)) {
      separatorFound = true;
      return Optional.of(Lists.of());
    }

    return Optional.empty();
  }

  public boolean isSeparatorFound() {
    return separatorFound;
  }
}
