[← 016. Strings](016_Strings.md) | [Course Index](../README.md) | [018. StringBuilder →](018_StringBuilder.md)

# Java String methods Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/01_Programming_and_Java_Fundamentals/017_String_methods.md)

These exercises follow [017. String methods](../../Road%20Map/01_Programming_and_Java_Fundamentals/017_String_methods.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Trim

**Task:** Remove leading and trailing ordinary spaces.

```java
System.out.println("[" + " Java ".trim() + "]");
```

**Expected result:**
```text
[Java]
```

**Concept:** trim returns a new view of the desired content without mutating the original.

## Question 2 — Case conversion

**Task:** Convert Java to lowercase and uppercase with Locale.ROOT.

```java
System.out.println("Java".toLowerCase(Locale.ROOT));
System.out.println("Java".toUpperCase(Locale.ROOT));
```

**Expected result:**
```text
java
JAVA
```

**Concept:** Explicit locale selection avoids machine-default casing differences.

## Question 3 — Prefix and suffix

**Task:** Check the prefix and suffix of Main.java.

```java
System.out.println("Main.java".startsWith("Main"));
System.out.println("Main.java".endsWith(".java"));
```

**Expected result:**
```text
true
true
```

**Concept:** Prefix and suffix checks are case-sensitive.

## Question 4 — Containment

**Task:** Check whether a sentence contains Java.

```java
System.out.println("Learning Java".contains("Java"));
```

**Expected result:**
```text
true
```

**Concept:** contains checks a literal sequence rather than a regex.

## Question 5 — Search positions

**Task:** Find the first and last a in banana.

```java
System.out.println("banana".indexOf('a'));
System.out.println("banana".lastIndexOf('a'));
```

**Expected result:**
```text
1
5
```

**Concept:** Search methods return zero-based indices, or -1 when absent.

## Question 6 — Literal replacement

**Task:** Replace dots with hyphens without regex.

```java
System.out.println("a.b.c".replace(".", "-"));
```

**Expected result:**
```text
a-b-c
```

**Concept:** replace performs literal replacement for this overload.

## Question 7 — Regex split

**Task:** Split a dot-separated value into tokens.

```java
System.out.println(Arrays.toString("a.b.c".split("\\.")));
```

**Expected result:**
```text
[a, b, c]
```

**Concept:** split accepts a regex, so a literal dot must be escaped in both regex and Java syntax.

## Question 8 — Preserve trailing fields

**Task:** Split a comma-separated row retaining its final empty field.

```java
String[] fields = "A,B,".split(",", -1);
System.out.println(fields.length);
System.out.println("[" + fields[2] + "]");
```

**Expected result:**
```text
3
[]
```

**Concept:** A negative split limit preserves trailing empty tokens.

## Question 9 — Case-insensitive comparison

**Task:** Compare Java and JAVA ignoring case.

```java
System.out.println("Java".equalsIgnoreCase("JAVA"));
```

**Expected result:**
```text
true
```

**Concept:** equalsIgnoreCase is a comparison operation rather than locale-specific collation.

## Question 10 — Validate a substring bound

**Task:** Extract a prefix with a maximum length of three from short and long inputs.

```java
for (String text : Arrays.asList("Hi", "Java")) { System.out.println(text.substring(0, Math.min(3, text.length()))); }
```

**Expected result:**
```text
Hi
Jav
```

**Concept:** Validate bounds before substring access.

---

## Interview Questions and Answers

### Interview Question 1 — Which String methods interpret regular expressions?

**Answer:** Methods such as matches, split, replaceAll, and replaceFirst use regex syntax. contains and replace(CharSequence, CharSequence) use literal sequences.

### Interview Question 2 — What happens if indexOf cannot find a match?

**Answer:** It returns -1. Check that result before using it as a substring or character index.

### Interview Question 3 — Why does split lose a trailing empty token by default?

**Answer:** The zero-limit behavior removes trailing empty strings. Supply a negative limit when every trailing field matters.

### Interview Question 4 — Does trim remove every Unicode whitespace character?

**Answer:** No. Its historical rule is narrower than a general Unicode whitespace policy. Choose normalization rules appropriate to the input domain.

### Interview Question 5 — Why can no-argument uppercase conversion vary by machine?

**Answer:** It uses the default locale. Use an explicit locale such as Locale.ROOT for locale-independent identifiers and examples.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Trim | trim returns a new view of the desired content without mutating the original. |
| Case conversion | Explicit locale selection avoids machine-default casing differences. |
| Prefix and suffix | Prefix and suffix checks are case-sensitive. |
| Containment | contains checks a literal sequence rather than a regex. |
| Search positions | Search methods return zero-based indices, or -1 when absent. |

[← 016. Strings](016_Strings.md) | [Course Index](../README.md) | [018. StringBuilder →](018_StringBuilder.md)
