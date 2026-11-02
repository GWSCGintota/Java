[← 014. User input with Scanner](014_User_input_with_Scanner.md) | [Course Index](../README.md) | [016. Strings →](016_Strings.md)

# Java Output with System.out Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/01_Programming_and_Java_Fundamentals/015_Output_with_System.out.md)

These exercises follow [015. Output with System.out](../../Road%20Map/01_Programming_and_Java_Fundamentals/015_Output_with_System.out.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Print a line

**Task:** Print Hello followed by a line terminator.

```java
System.out.println("Hello");
```

**Expected result:**
```text
Hello
```

**Concept:** println appends the platform line terminator.

## Question 2 — Continue one line

**Task:** Print two pieces of text on the same line.

```java
System.out.print("Hello ");
System.out.println("Ada");
```

**Expected result:**
```text
Hello Ada
```

**Concept:** print does not add a line terminator.

## Question 3 — Format an integer

**Task:** Format count=7 using an integer placeholder.

```java
System.out.printf("Count: %d%n", 7);
```

**Expected result:**
```text
Count: 7
```

**Concept:** %d formats an integral value and %n uses the platform line separator.

## Question 4 — Format decimals

**Task:** Print 2.5 with two decimal places and an explicit locale.

```java
System.out.printf(Locale.ROOT, "%.2f%n", 2.5);
```

**Expected result:**
```text
2.50
```

**Concept:** An explicit locale makes the decimal separator predictable.

## Question 5 — Format text

**Task:** Print a name using a string placeholder.

```java
System.out.printf("Hello, %s%n", "Ada");
```

**Expected result:**
```text
Hello, Ada
```

**Concept:** %s formats a string representation.

## Question 6 — Escape characters

**Task:** Print a quote and a backslash.

```java
System.out.println("\"Java\" \\");
```

**Expected result:**
```text
"Java" \
```

**Concept:** Escapes distinguish source syntax from literal output characters.

## Question 7 — Column width

**Task:** Right-align the number 7 in a three-character field and mark the boundaries.

```java
System.out.printf("[%3d]%n", 7);
```

**Expected result:**
```text
[  7]
```

**Concept:** Width is a minimum formatted field width.

## Question 8 — Zero padding

**Task:** Print integer 7 with four digits.

```java
System.out.printf("%04d%n", 7);
```

**Expected result:**
```text
0007
```

**Concept:** Formatting flags can add padding without changing the stored value.

## Question 9 — Percent sign

**Task:** Print a completion percentage.

```java
System.out.printf("Done: %d%%%n", 50);
```

**Expected result:**
```text
Done: 50%
```

**Concept:** %% prints a literal percent sign.

## Question 10 — Null output

**Task:** Print a nullable string without dereferencing it.

```java
String text = null;
System.out.println(text);
```

**Expected result:**
```text
null
```

**Concept:** PrintStream can print a null string; calling a method on that null reference would fail.

---

## Interview Questions and Answers

### Interview Question 1 — How do print, println, and printf differ?

**Answer:** print writes a representation, println adds a line terminator, and printf uses a format string with arguments.

### Interview Question 2 — Why prefer %n in a format string?

**Answer:** It emits the platform-specific line separator. A literal newline may still be appropriate when an external protocol requires a specific delimiter.

### Interview Question 3 — Why supply a locale for deterministic output?

**Answer:** Numeric formatting can use locale-specific decimal separators and grouping rules. An explicit locale avoids depending on the machine default.

### Interview Question 4 — What happens if a format conversion does not match the argument?

**Answer:** A formatting exception can be thrown, such as IllegalFormatConversionException for an incompatible numeric conversion.

### Interview Question 5 — Is console printing a replacement for application logging?

**Answer:** No. A logging framework can provide levels, context, configurable outputs, and structured events. Console printing remains useful for small exercises and command-line output.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Print a line | println appends the platform line terminator. |
| Continue one line | print does not add a line terminator. |
| Format an integer | %d formats an integral value and %n uses the platform line separator. |
| Format decimals | An explicit locale makes the decimal separator predictable. |
| Format text | %s formats a string representation. |

[← 014. User input with Scanner](014_User_input_with_Scanner.md) | [Course Index](../README.md) | [016. Strings →](016_Strings.md)
