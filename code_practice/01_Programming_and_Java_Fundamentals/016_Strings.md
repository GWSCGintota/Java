[← 015. Output with System.out](015_Output_with_System.out.md) | [Course Index](../README.md) | [017. String methods →](017_String_methods.md)

# Java Strings Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/01_Programming_and_Java_Fundamentals/016_Strings.md)

These exercises follow [016. Strings](../../Road%20Map/01_Programming_and_Java_Fundamentals/016_Strings.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Create a string

**Task:** Store Java and print its length.

```java
String text = "Java";
System.out.println(text.length());
```

**Expected result:**
```text
4
```

**Concept:** String is an immutable reference type.

## Question 2 — Concatenate

**Task:** Combine a greeting and name.

```java
String greeting = "Hello, " + "Ada";
System.out.println(greeting);
```

**Expected result:**
```text
Hello, Ada
```

**Concept:** Concatenation produces a string result.

## Question 3 — Immutability

**Task:** Call uppercase without assigning its result, then print the original and returned value.

```java
String text = "java";
text.toUpperCase(Locale.ROOT);
System.out.println(text);
System.out.println(text.toUpperCase(Locale.ROOT));
```

**Expected result:**
```text
java
JAVA
```

**Concept:** String methods do not modify the original string.

## Question 4 — Content comparison

**Task:** Compare separately constructed strings by content.

```java
String a = new String("Java"), b = new String("Java");
System.out.println(a.equals(b));
System.out.println(a == b);
```

**Expected result:**
```text
true
false
```

**Concept:** Content equality and identity are distinct.

## Question 5 — Empty string

**Task:** Distinguish empty text from a non-empty space.

```java
System.out.println("".isEmpty());
System.out.println(" ".isEmpty());
```

**Expected result:**
```text
true
false
```

**Concept:** An empty string contains zero code units.

## Question 6 — Character access

**Task:** Print the first and last code units of Java.

```java
String text = "Java";
System.out.println(text.charAt(0));
System.out.println(text.charAt(text.length()-1));
```

**Expected result:**
```text
J
a
```

**Concept:** String indices are zero-based UTF-16 code-unit positions.

## Question 7 — Substring

**Task:** Extract Java from JavaScript.

```java
System.out.println("JavaScript".substring(0, 4));
```

**Expected result:**
```text
Java
```

**Concept:** The start index is inclusive and the end index exclusive.

## Question 8 — Join words

**Task:** Join three names with a comma and space.

```java
System.out.println(String.join(", ", "Ada", "Grace", "Alan"));
```

**Expected result:**
```text
Ada, Grace, Alan
```

**Concept:** Joining is clearer than manually assembling separators.

## Question 9 — Null-safe content check

**Task:** Compare a nullable string against a known literal.

```java
String text = null;
System.out.println("Java".equals(text));
```

**Expected result:**
```text
false
```

**Concept:** Calling equals on a non-null literal avoids dereferencing the nullable input.

## Question 10 — Unicode length

**Task:** Compare UTF-16 length and code-point count for a supplementary symbol.

```java
String text = "\uD83D\uDE00";
System.out.println(text.length());
System.out.println(text.codePointCount(0, text.length()));
```

**Expected result:**
```text
2
1
```

**Concept:** One Unicode code point can occupy two UTF-16 code units.

---

## Interview Questions and Answers

### Interview Question 1 — Why is String immutable?

**Answer:** Its visible character content cannot change after creation. This supports safe sharing, reliable hash values, and predictable use as keys; it does not make every operation involving a string thread-safe.

### Interview Question 2 — How do == and equals compare strings?

**Answer:** == checks whether references identify the same object. String.equals checks character content.

### Interview Question 3 — What does String.length count?

**Answer:** UTF-16 code units, not necessarily Unicode code points or user-perceived characters.

### Interview Question 4 — Is null an empty string?

**Answer:** No. null is the absence of an object reference; an empty string is a valid String object containing no code units.

### Interview Question 5 — When should StringBuilder be used?

**Answer:** When repeatedly accumulating or editing text in one mutable buffer. Use String for immutable values and choose a joining API when it directly expresses the task.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Create a string | String is an immutable reference type. |
| Concatenate | Concatenation produces a string result. |
| Immutability | String methods do not modify the original string. |
| Content comparison | Content equality and identity are distinct. |
| Empty string | An empty string contains zero code units. |

[← 015. Output with System.out](015_Output_with_System.out.md) | [Course Index](../README.md) | [017. String methods →](017_String_methods.md)
