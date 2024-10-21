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
import com.sigpwned.args4j.model.SwitchName;
import com.sigpwned.args4j.model.Token;
import com.sigpwned.args4j.model.token.SwitchNameToken;
import com.sigpwned.args4j.model.token.ValueToken;
import com.sigpwned.args4j.util.Lists;

public class LongSwitchUnixArgTokenizer implements ArgTokenizer, UnixDialectElement {
  /**
   * <p>
   * Handles tokens that start with but do not equal {@link #LONG_NAME_PREFIX long name prefix},
   * namely:
   * </p>
   * 
   * <ul>
   * <li>A long switch without attached value, e.g., {@code "--hello"}</li>
   * <li>A long switch with attached value, e.g., {@code "--hello=world"}</li>
   * </ul>
   * 
   * @param arg The token to tokenize.
   */
  @Override
  public Optional<List<Token>> tokenize(String arg) {
    if (!arg.startsWith(LONG_NAME_PREFIX))
      return Optional.empty();
    if (arg.equals(LONG_NAME_PREFIX))
      return Optional.empty();

    final String text = arg.substring(LONG_NAME_PREFIX.length(), arg.length());

    final int sep = text.indexOf(LONG_NAME_VALUE_SEPARATOR);
    if (sep == -1) {
      final SwitchName name = SwitchName.fromString(text);
      return Optional.of(Lists.of(new SwitchNameToken(name, false)));
    } else {
      final SwitchName name = SwitchName.fromString(text.substring(0, sep));
      final String value = text.substring(sep + 1, text.length());
      return Optional.of(Lists.of(new SwitchNameToken(name, false), new ValueToken(value, true)));
    }
  }
}
