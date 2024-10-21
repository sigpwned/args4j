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

public class UnixDialectTest {
  @Test
  public void givenArgsWithAllValidSyntax_whenParse_thenSucceed() {
    // This is a general smoke test for all supported syntax.

    List<Token> tokens = Args.parse(UnixDialect.INSTANCE, Lists.of("-a", // short switch
        "-abc", // short switch batch
        "--alpha", // long switch
        "--alpha=bravo", // long switch with value
        "-", // just a dash, but test for it as short switch prefix
        "--", // separator, forces subsequent arguments to be value
        "charlie", // value token
        "--delta", // value token, but looks like long switch.
        "-echo")); // value token, but looks like short switch batch

    // Note that we don't get any tokens representing "--" from above. This is because the Unix
    // dialect treats that as a meta token that indicates that all subsequent arguments are values,
    // even if they look like switches.
    assertEquals(Lists.of(new SwitchNameToken(SwitchName.of("a"), false),
        new SwitchNameToken(SwitchName.of("a"), false),
        new SwitchNameToken(SwitchName.of("b"), true),
        new SwitchNameToken(SwitchName.of("c"), true),
        new SwitchNameToken(SwitchName.of("alpha"), false),
        new SwitchNameToken(SwitchName.of("alpha"), false), new ValueToken("bravo", true),
        new ValueToken("-", false), new ValueToken("charlie", false),
        new ValueToken("--delta", false), new ValueToken("-echo", false)), tokens);
  }
}
