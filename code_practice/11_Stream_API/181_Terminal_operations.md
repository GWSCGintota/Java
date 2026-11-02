[← 180. Intermediate operations](180_Intermediate_operations.md) | [Course Index](../README.md) | [182. null →](../12_Optional_and_Null_Handling/182_null.md)

# Java Terminal operations Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/11_Stream_API/181_Terminal_operations.md)

These exercises follow the corresponding Road Map lesson, progressing from basic operations to composition, boundary cases, and safe execution. Each coding question includes a model solution, expected output, and concept explanation.

**Java version:** Java 8+. Short snippets are independent and belong inside `main()`; assume imports from `java.util.*`, `java.util.function.*`, and `java.util.stream.*`. Complete programs include their helper types and should be saved as `Main.java`. Run questions separately. Inputs are non-null unless explicitly handled, and arithmetic uses small values that do not overflow. For checked I/O, `main()` must declare `throws Exception` or handle the failure.

## Question 1 — Count sum and maximum

**Task:** Calculate count, integer sum, and maximum of [2, 3, 4] using independent pipelines.

```java
List<Integer> values = Arrays.asList(2, 3, 4);
System.out.println(values.stream().count());
System.out.println(values.stream().mapToInt(Integer::intValue).sum());
System.out.println(values.stream().max(Comparator.naturalOrder()).orElse(-1));
```

**Output:**
```text
3
9
4
```

**Concept:** Independent terminals require fresh streams from reusable data.

## Question 2 — First non-null match

**Task:** Find the first non-null A-prefixed name in [null, Bob, Ada, Alan].

```java
System.out.println(Arrays.asList(null, "Bob", "Ada", "Alan").stream()
        .filter(Objects::nonNull)
        .filter(s -> s.startsWith("A")).findFirst().orElse("None"));
```

**Output:**
```text
Ada
```

**Concept:** Guard nullable elements before a search that returns an Optional.

## Question 3 — Three matching operations

**Task:** For [1, 3, 4, 6], test whether any value exceeds five, all are positive, and none is negative.

```java
List<Integer> values = Arrays.asList(1, 3, 4, 6);
System.out.println(values.stream().anyMatch(n -> n > 5));
System.out.println(values.stream().allMatch(n -> n > 0));
System.out.println(values.stream().noneMatch(n -> n < 0));
```

**Output:**
```text
true
true
true
```

**Concept:** Matching operations return Boolean conclusions and may short-circuit.

## Question 4 — Empty matching truth values

**Task:** Print anyMatch, allMatch, and noneMatch for an empty integer stream using a positivity predicate.

```java
System.out.println(Stream.<Integer>empty().anyMatch(n -> n > 0));
System.out.println(Stream.<Integer>empty().allMatch(n -> n > 0));
System.out.println(Stream.<Integer>empty().noneMatch(n -> n > 0));
```

**Output:**
```text
false
true
true
```

**Concept:** Empty all/none matching reflects absence of a counterexample, not presence of an element.

## Question 5 — Typed array result

**Task:** Convert Ada and Grace into a String[] using an array constructor reference.

```java
String[] names = Stream.of("Ada", "Grace").toArray(String[]::new);
System.out.println(Arrays.toString(names));
```

**Output:**
```text
[Ada, Grace]
```

**Concept:** The generator overload constructs the required runtime array type.

## Question 6 — Untyped array distinction

**Task:** Compare whether toArray() and toArray(String[]::new) return String[] instances.

```java
Object[] untyped = Stream.of("Ada", "Grace").toArray();
Object[] typed = Stream.of("Ada", "Grace").toArray(String[]::new);
System.out.println(untyped instanceof String[]);
System.out.println(typed instanceof String[]);
```

**Output:**
```text
false
true
```

**Concept:** An Object[] of strings is not automatically a String[] runtime object.

## Question 7 — Empty numeric and optional results

**Task:** Print empty IntStream sum, empty average presence, and empty reference-stream findFirst presence.

```java
System.out.println(IntStream.empty().sum());
System.out.println(IntStream.empty().average().isPresent());
System.out.println(Stream.<String>empty().findFirst().isPresent());
```

**Output:**
```text
0
false
false
```

**Concept:** Different terminal result shapes encode empty input differently.

## Question 8 — Ordered terminal actions

**Task:** Filter even values from [1, 2, 3, 4] and print them with forEachOrdered.

```java
Stream.of(1, 2, 3, 4).filter(n -> n % 2 == 0).forEachOrdered(System.out::println);
```

**Output:**
```text
2
4
```

**Concept:** An action terminal returns void rather than a container.

## Question 9 — Detected stream reuse

**Task:** Count one Stream.of(Ada, Grace), then attempt a second count and catch the detected reuse failure.

```java
Stream<String> names = Stream.of("Ada", "Grace");
System.out.println(names.count());
try { names.count(); } catch (IllegalStateException e) { System.out.println("Consumed stream"); }
```

**Output:**
```text
2
Consumed stream
```

**Concept:** Reusing a consumed stream violates the contract; standard stream implementations detect this direct case.

## Question 10 — Short-circuit a managed file source

**Task:** Write Ada and Grace as UTF-8 lines to a temporary file, find the first line in a managed stream, and delete the file afterward.

```java
java.nio.file.Path path = java.nio.file.Files.createTempFile("stream-first-", ".txt");
try {
    java.nio.file.Files.write(path, Arrays.asList("Ada", "Grace"), java.nio.charset.StandardCharsets.UTF_8);
    try (Stream<String> lines = java.nio.file.Files.lines(path, java.nio.charset.StandardCharsets.UTF_8)) {
        System.out.println(lines.findFirst().orElse("Empty"));
    }
} finally {
    java.nio.file.Files.deleteIfExists(path);
}
```

**Output:**
```text
Ada
```

**Concept:** A short-circuiting terminal still needs explicit closure of the owned I/O stream.

---

## Interview Questions and Answers

### Interview Question 1 — Does every terminal consume every element?

**Answer:** No. Short-circuiting, legal optimizations, and traversal escape hatches have different consumption behavior.

### Interview Question 2 — Why is allMatch true for an empty stream?

**Answer:** There is no element that violates the predicate; this is vacuous truth, not proof that at least one matching element exists.

### Interview Question 3 — Does a terminal operation close its source?

**Answer:** Not automatically. Resource ownership must be handled separately, normally with try-with-resources for I/O-backed streams.

### Interview Question 4 — Does findAny mean choose a random value?

**Answer:** No. It permits any element without promising randomness or a different value on repeated runs.

### Interview Question 5 — Can a terminal operation be a traversal escape hatch?

**Answer:** Yes. iterator and spliterator are terminal operations that hand traversal control to the caller rather than eagerly processing every element.

---

## Key Takeaways

| Skill | What to remember |
|---|---|
| Count sum and maximum | Independent terminals require fresh streams from reusable data. |
| First non-null match | Guard nullable elements before a search that returns an Optional. |
| Three matching operations | Matching operations return Boolean conclusions and may short-circuit. |
| Empty matching truth values | Empty all/none matching reflects absence of a counterexample, not presence of an element. |
| Typed array result | The generator overload constructs the required runtime array type. |

[← Road Map lesson](../../Road%20Map/11_Stream_API/181_Terminal_operations.md)

[← 180. Intermediate operations](180_Intermediate_operations.md) | [Course Index](../README.md) | [182. null →](../12_Optional_and_Null_Handling/182_null.md)
