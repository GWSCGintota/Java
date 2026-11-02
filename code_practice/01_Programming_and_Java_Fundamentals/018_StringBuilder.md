[← 017. String methods](017_String_methods.md) | [Course Index](../README.md) | [019. Math functions →](019_Math_functions.md)

# Java StringBuilder Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/01_Programming_and_Java_Fundamentals/018_StringBuilder.md)

These exercises follow [018. StringBuilder](../../Road%20Map/01_Programming_and_Java_Fundamentals/018_StringBuilder.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Append text

**Task:** Append a name to a greeting.

```java
StringBuilder text = new StringBuilder("Hello");
text.append(", ").append("Ada");
System.out.println(text);
```

**Expected result:**
```text
Hello, Ada
```

**Concept:** append mutates and returns the same builder for chaining.

## Question 2 — Insert

**Task:** Insert a space between two words.

```java
StringBuilder text = new StringBuilder("HelloJava");
text.insert(5, ' ');
System.out.println(text);
```

**Expected result:**
```text
Hello Java
```

**Concept:** insert shifts later content to make room.

## Question 3 — Delete

**Task:** Remove the substring Script from JavaScript.

```java
StringBuilder text = new StringBuilder("JavaScript");
text.delete(4, 10);
System.out.println(text);
```

**Expected result:**
```text
Java
```

**Concept:** Delete uses an inclusive start and exclusive end.

## Question 4 — Replace

**Task:** Replace World with Java in a builder.

```java
StringBuilder text = new StringBuilder("Hello World");
text.replace(6, 11, "Java");
System.out.println(text);
```

**Expected result:**
```text
Hello Java
```

**Concept:** Replacement can change the buffer length.

## Question 5 — Reverse

**Task:** Reverse abc.

```java
System.out.println(new StringBuilder("abc").reverse());
```

**Expected result:**
```text
cba
```

**Concept:** reverse changes the builder's content in place.

## Question 6 — Change one code unit

**Task:** Change the first character of java to J.

```java
StringBuilder text = new StringBuilder("java");
text.setCharAt(0, 'J');
System.out.println(text);
```

**Expected result:**
```text
Java
```

**Concept:** Indexed mutations require a valid existing index.

## Question 7 — Length versus capacity

**Task:** Construct a capacity-20 builder, append Java, and print length and capacity.

```java
StringBuilder text = new StringBuilder(20);
text.append("Java");
System.out.println(text.length());
System.out.println(text.capacity());
```

**Expected result:**
```text
4
20
```

**Concept:** Length counts content; capacity describes allocated buffer space available before growth.

## Question 8 — Take a snapshot

**Task:** Convert a builder to String, mutate the builder, then print both.

```java
StringBuilder text = new StringBuilder("A");
String snapshot = text.toString();
text.append("B");
System.out.println(snapshot);
System.out.println(text);
```

**Expected result:**
```text
A
AB
```

**Concept:** toString produces a String value unaffected by later builder mutations.

## Question 9 — Clear a buffer

**Task:** Reset a builder and reuse it.

```java
StringBuilder text = new StringBuilder("Old");
text.setLength(0);
text.append("New");
System.out.println(text);
```

**Expected result:**
```text
New
```

**Concept:** Setting length to zero removes content while permitting reuse.

## Question 10 — Accumulate a report

**Task:** Build a comma-separated list without a trailing comma.

```java
StringBuilder text = new StringBuilder();
for (int i = 1; i <= 3; i++) { if (i > 1) { text.append(","); } text.append(i); }
System.out.println(text);
```

**Expected result:**
```text
1,2,3
```

**Concept:** A mutable buffer avoids repeatedly replacing a growing immutable value.

---

## Interview Questions and Answers

### Interview Question 1 — How does StringBuilder differ from String?

**Answer:** StringBuilder supports mutable text operations; String values are immutable. Converting a builder to String gives a string snapshot.

### Interview Question 2 — Is StringBuilder thread-safe?

**Answer:** No. Sharing mutation across threads needs appropriate synchronization or a different design. StringBuffer synchronizes individual methods but not arbitrary multi-step algorithms automatically.

### Interview Question 3 — What is the difference between length and capacity?

**Answer:** Length is the current number of UTF-16 code units. Capacity is available internal storage before additional allocation is needed.

### Interview Question 4 — Does append return a new builder?

**Answer:** It returns the same builder after mutation, supporting method chaining.

### Interview Question 5 — Does StringBuilder use content-based equals?

**Answer:** It does not override Object.equals for text-content equality. Compare appropriate String snapshots when textual equality is required.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Append text | append mutates and returns the same builder for chaining. |
| Insert | insert shifts later content to make room. |
| Delete | Delete uses an inclusive start and exclusive end. |
| Replace | Replacement can change the buffer length. |
| Reverse | reverse changes the builder's content in place. |

[← 017. String methods](017_String_methods.md) | [Course Index](../README.md) | [019. Math functions →](019_Math_functions.md)
