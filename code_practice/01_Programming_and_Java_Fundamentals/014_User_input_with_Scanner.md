[← 013. Operators](013_Operators.md) | [Course Index](../README.md) | [015. Output with System.out →](015_Output_with_System.out.md)

# Java User input with Scanner Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/01_Programming_and_Java_Fundamentals/014_User_input_with_Scanner.md)

These exercises follow [014. User input with Scanner](../../Road%20Map/01_Programming_and_Java_Fundamentals/014_User_input_with_Scanner.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Read an integer

**Task:** Read one integer from deterministic scanner input.

```java
try (Scanner scanner = new Scanner("42")) { System.out.println(scanner.nextInt()); }
```

**Expected result:**
```text
42
```

**Concept:** Scanner can read from a string as well as standard input.

## Question 2 — Read a line

**Task:** Read a full name including its space.

```java
try (Scanner scanner = new Scanner("Ada Lovelace")) { System.out.println(scanner.nextLine()); }
```

**Expected result:**
```text
Ada Lovelace
```

**Concept:** nextLine reads the rest of a line rather than one token.

## Question 3 — Read tokens

**Task:** Read two whitespace-delimited words.

```java
try (Scanner scanner = new Scanner("Ada Grace")) { System.out.println(scanner.next()); System.out.println(scanner.next()); }
```

**Expected result:**
```text
Ada
Grace
```

**Concept:** next reads one token using the configured delimiter.

## Question 4 — Validate a number

**Task:** Report invalid integer input without calling nextInt blindly.

```java
try (Scanner scanner = new Scanner("oops")) { System.out.println(scanner.hasNextInt() ? scanner.nextInt() : "Invalid integer"); }
```

**Expected result:**
```text
Invalid integer
```

**Concept:** hasNextInt checks whether the next token can be parsed.

## Question 5 — Consume a pending newline

**Task:** Read an age followed by a name on the next line.

```java
try (Scanner scanner = new Scanner(String.join(System.lineSeparator(), "20", "Ada", ""))) { int age = scanner.nextInt(); scanner.nextLine(); String name = scanner.nextLine(); System.out.println(name + ":" + age); }
```

**Expected result:**
```text
Ada:20
```

**Concept:** Token reads can leave the line terminator for a later nextLine call.

## Question 6 — Custom delimiter

**Task:** Read comma-separated integers.

```java
try (Scanner scanner = new Scanner("2,3,4")) { scanner.useDelimiter(","); int sum = 0; while (scanner.hasNextInt()) { sum += scanner.nextInt(); } System.out.println(sum); }
```

**Expected result:**
```text
9
```

**Concept:** Delimiters define token boundaries.

## Question 7 — Explicit locale

**Task:** Read a dot-decimal value using Locale.ROOT.

```java
try (Scanner scanner = new Scanner("2.5")) { scanner.useLocale(Locale.ROOT); System.out.println(scanner.nextDouble()); }
```

**Expected result:**
```text
2.5
```

**Concept:** Numeric parsing depends on the scanner locale.

## Question 8 — End of input

**Task:** Read all available integers without overrunning the input.

```java
try (Scanner scanner = new Scanner("1 2 3")) { int count = 0; while (scanner.hasNextInt()) { scanner.nextInt(); count++; } System.out.println(count); }
```

**Expected result:**
```text
3
```

**Concept:** Availability checks protect token reads at end of input.

## Question 9 — Skip invalid tokens

**Task:** Sum integers while explicitly skipping non-integer tokens.

```java
try (Scanner scanner = new Scanner("2 x 3")) { int total = 0; while (scanner.hasNext()) { if (scanner.hasNextInt()) { total += scanner.nextInt(); } else { scanner.next(); } } System.out.println(total); }
```

**Expected result:**
```text
5
```

**Concept:** A rejected token must be consumed to avoid a loop that never advances.

## Question 10 — Interactive input

**Task:** Write a complete console program that reads and doubles an integer; enter 6.

```java
public class Main { public static void main(String[] args) { java.util.Scanner scanner = new java.util.Scanner(System.in); if (scanner.hasNextInt()) { System.out.println(scanner.nextInt() * 2); } else { System.out.println("Invalid integer"); } } }
```

**Expected result:**
```text
Input: 6
Output: 12
```

**Concept:** An application should define ownership before closing wrappers around the shared System.in stream.

---

## Interview Questions and Answers

### Interview Question 1 — How do next and nextLine differ?

**Answer:** next reads one delimiter-separated token. nextLine reads through the current line terminator and returns the line content without that terminator.

### Interview Question 2 — Why can nextLine return an empty string after nextInt?

**Answer:** nextInt can leave the line terminator unconsumed. A following nextLine reads the remaining portion of that line, which may be empty.

### Interview Question 3 — What happens on invalid nextInt input?

**Answer:** InputMismatchException is thrown and the offending token is not consumed. Check or handle the error and advance the scanner deliberately.

### Interview Question 4 — Why set a scanner locale?

**Answer:** Decimal and grouping conventions vary by locale. Set an explicit locale when the input format has fixed conventions.

### Interview Question 5 — Should every scanner be closed immediately?

**Answer:** Close scanners that own their resources. Closing a scanner wrapping System.in closes that shared stream, so the application must decide ownership and lifetime.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Read an integer | Scanner can read from a string as well as standard input. |
| Read a line | nextLine reads the rest of a line rather than one token. |
| Read tokens | next reads one token using the configured delimiter. |
| Validate a number | hasNextInt checks whether the next token can be parsed. |
| Consume a pending newline | Token reads can leave the line terminator for a later nextLine call. |

[← 013. Operators](013_Operators.md) | [Course Index](../README.md) | [015. Output with System.out →](015_Output_with_System.out.md)
