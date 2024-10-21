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

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Test;
import com.sigpwned.args4j.model.SwitchName;
import com.sigpwned.args4j.model.Token;
import com.sigpwned.args4j.model.token.SwitchNameToken;
import com.sigpwned.args4j.model.token.ValueToken;
import com.sigpwned.args4j.util.Args;
import com.sigpwned.args4j.util.Lists;

public class MsDosDialectTest {
  @Test
  public void givenArgsWithAllValidSyntax_whenParse_thenSucceed() {
    // This is a general smoke test for all supported syntax.

    List<Token> tokens = Args.parse(MsDosDialect.INSTANCE, Lists.of("/a", // switch
        "/alpha", // switch
        "value")); // value

    assertEquals(
        Lists.of(new SwitchNameToken(SwitchName.of("a"), false),
            new SwitchNameToken(SwitchName.of("alpha"), false), new ValueToken("value", false)),
        tokens);
  }
}
