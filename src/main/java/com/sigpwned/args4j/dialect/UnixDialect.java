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
package com.sigpwned.args4j.dialect;

import com.sigpwned.args4j.ArgTokenizer;
import com.sigpwned.args4j.Dialect;
import com.sigpwned.args4j.dialect.unix.tokenizer.LongSwitchUnixArgTokenizer;
import com.sigpwned.args4j.dialect.unix.tokenizer.SeparatorUnixArgTokenizer;
import com.sigpwned.args4j.dialect.unix.tokenizer.ShortSwitchPrefixUnixArgTokenizer;
import com.sigpwned.args4j.dialect.unix.tokenizer.ShortSwitchUnixArgTokenizer;
import com.sigpwned.args4j.dialect.unix.tokenizer.ValueUnixArgTokenizer;
import com.sigpwned.args4j.impl.ArgTokenizerChain;

public final class UnixDialect implements Dialect {
  public static final UnixDialect INSTANCE = new UnixDialect();

  @Override
  public ArgTokenizer newTokenizer() {
    // In theory, these tokenizers are disjoint, so the order doesn't matter here. Note that future
    // tokenizers may not be disjoint, so be careful when adding new tokenizers.
    ArgTokenizerChain result = new ArgTokenizerChain();
    result.addLast(new SeparatorUnixArgTokenizer());
    result.addLast(new ShortSwitchPrefixUnixArgTokenizer());
    result.addLast(new LongSwitchUnixArgTokenizer());
    result.addLast(new ShortSwitchUnixArgTokenizer());
    result.addLast(new ValueUnixArgTokenizer());
    return result;
  }
}
