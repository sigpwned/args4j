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

import static java.util.Collections.unmodifiableList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.sigpwned.args4j.ArgTokenizer;
import com.sigpwned.args4j.dialect.unix.UnixDialectElement;
import com.sigpwned.args4j.model.SwitchName;
import com.sigpwned.args4j.model.Token;
import com.sigpwned.args4j.model.token.SwitchNameToken;

public class ShortSwitchUnixArgTokenizer implements ArgTokenizer, UnixDialectElement {

  /**
   * <p>
   * Handles tokens that start with a {@link #SHORT_NAME_PREFIX short name prefix}, namely:
   * </p>
   * 
   * <ul>
   * <li>A single short switch, e.g., {@code "-x"}</li>
   * <li>A bundle of short switches, e.g., {@code "-xyz"}</li>
   * </ul>
   * 
   * @param arg The token to tokenize.
   */
  @Override
  public Optional<List<Token>> tokenize(String arg) {
    if (!arg.startsWith(SHORT_NAME_PREFIX))
      return Optional.empty();
    if (arg.equals(SHORT_NAME_PREFIX))
      return Optional.empty();

    final String text = arg.substring(SHORT_NAME_PREFIX.length(), arg.length());

    final int[] cps = text.codePoints().toArray();

    final char[] cpbuf = new char[2]; // A code point is 1 char, or a surrogate pair of 2 chars.
    final List<Token> tokens = new ArrayList<>(cps.length);
    for (int i = 0; i < cps.length; i++) {
      final int cp = cps[i];
      final int cpbuflen = Character.toChars(cp, cpbuf, 0);
      final SwitchName name = SwitchName.fromString(String.valueOf(cpbuf, 0, cpbuflen));
      tokens.add(new SwitchNameToken(name, i > 0));
    }

    return Optional.of(unmodifiableList(tokens));
  }
}
