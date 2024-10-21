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
package com.sigpwned.args4j.dialect.msdos.tokenizer;

import java.util.List;
import java.util.Optional;
import com.sigpwned.args4j.ArgTokenizer;
import com.sigpwned.args4j.dialect.msdos.MsDosDialectElement;
import com.sigpwned.args4j.model.SwitchName;
import com.sigpwned.args4j.model.Token;
import com.sigpwned.args4j.model.token.SwitchNameToken;
import com.sigpwned.args4j.util.Lists;

public class SwitchNameMsDosArgTokenizer implements ArgTokenizer, MsDosDialectElement {
  @Override
  public Optional<List<Token>> tokenize(String arg) {
    if (!arg.startsWith(SWITCH_NAME_PREFIX))
      return Optional.empty();
    if (arg.equals(SWITCH_NAME_PREFIX))
      return Optional.empty();

    final String text = arg.substring(SWITCH_NAME_PREFIX.length(), arg.length());

    return Optional.of(Lists.of(new SwitchNameToken(SwitchName.fromString(text), false)));
  }
}
