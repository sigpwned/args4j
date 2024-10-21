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
