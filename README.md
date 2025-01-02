# ARGS4J [![tests](https://github.com/sigpwned/args4j/actions/workflows/tests.yml/badge.svg)](https://github.com/sigpwned/args4j/actions/workflows/tests.yml)

args4j is a simple library for parsing command-line arguments for Java 8+.

## Quickstart

To parse command line arguments, simply use:

    public static void main(String[] args) {
        List<Token> tokens=Args.parse(args);
    }

For example, in the above code, the commented list of program arguments would result in the following token objects:

    // -a -abc --alpha --alpha=bravo - -- charlie --delta -echo
    Arrays.asList(
        new SwitchNameToken(SwitchName.of("a"), false),
        new SwitchNameToken(SwitchName.of("a"), false),
        new SwitchNameToken(SwitchName.of("b"), true),
        new SwitchNameToken(SwitchName.of("c"), true),
        new SwitchNameToken(SwitchName.of("alpha"), false),
        new SwitchNameToken(SwitchName.of("alpha"), false),
        new ValueToken("bravo", true),
        new ValueToken("-", false),
        new ValueToken("charlie", false),
        new ValueToken("--delta", false),
        new ValueToken("-echo", false));

## Dialects

The `Dialect` object creates the `ArgTokenizer` object that the `ArgsParser` object uses to convert program arguments into `Token` objects.

Users can specify a `Dialect` like this:

    public static void main(String[] args) {
        List<Token> tokens=Args.parse(UnixDialect.INSTANCE, args);
    }

If users do not specify a dialect, then the `UnixDialect` is used.

### Unix Dialect

The default Unix dialect supports the following syntax:

- **Long Switch**:
  - A command-line argument that begins with `--` followed by a name.
  - Example: `--alpha`
  - Represents an option or setting with a descriptive name.

- **Long Switch with Attached Value**:
  - A long switch followed by an `=` and its associated value.
  - Example: `--alpha=bravo`
  - Combines a switch and its value in a single token.

- **Short Switch**:
  - A single-character switch prefixed with `-`.
  - Example: `-a`
  - Provides a shorthand for enabling options.

- **Short Switch Batch**:
  - A single argument starting with `-`, followed by multiple single-character switches concatenated together.
  - Example: `-abc`
  - Represents multiple short switches: `-a`, `-b`, and `-c`.

- **Separator**:
  - A meta-token represented by `--`, which forces all subsequent arguments to be treated as values, even if they look like switches.
  - Example: `--`
  - Ensures arguments like `--delta` and `-echo` are treated as value tokens.

- **Value**:
  - Any other token
  - Example: `alpha`
  - Represents the actual data passed to applications

### MS/DOS Dialect

The MS/DOS dialect supports the following syntax:

- **Switch**:
  - A command-line argument that begins with `/` followed by a name.
  - Examples: `/a`, `/alpha`
  - Represents an option or setting.

- **Value**:
  - An argument that does not start with `/` and is treated as a value rather than a switch.
  - Example: `value`
  - Represents data or parameters passed to the command.

## Other Dialects

Users are free to implement their own `Dialect` objects. See `UnixDialect` and `MsDosDialect` for examples.