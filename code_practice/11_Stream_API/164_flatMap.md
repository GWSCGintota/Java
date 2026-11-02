[← 163. map()](163_map.md) | [Course Index](../README.md) | [165. forEach() →](165_forEach.md)

# Java flatMap() Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/11_Stream_API/164_flatMap.md)

These exercises follow the corresponding Road Map lesson, progressing from basic operations to composition, boundary cases, and safe execution. Each coding question includes a model solution, expected output, and concept explanation.

**Java version:** Java 8+. Short snippets are independent and belong inside `main()`; assume imports from `java.util.*`, `java.util.function.*`, and `java.util.stream.*`. Complete programs include their helper types and should be saved as `Main.java`. Run questions separately. Inputs are non-null unless explicitly handled, and arithmetic uses small values that do not overflow. For checked I/O, `main()` must declare `throws Exception` or handle the failure.

## Question 1 — Flatten nested lists

**Task:** Flatten [[1, 2], [], [3, 4]] into one list.

```java
List<List<Integer>> groups = Arrays.asList(Arrays.asList(1, 2), Collections.emptyList(), Arrays.asList(3, 4));
System.out.println(groups.stream().flatMap(Collection::stream).collect(Collectors.toList()));
```

**Output:**
```text
[1, 2, 3, 4]
```

**Concept:** Empty mapped streams contribute no elements.

## Question 2 — Flatten and select

**Task:** Flatten [[1, 2], [3, 4], [5, 6]] and retain even values.

```java
System.out.println(Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4), Arrays.asList(5, 6))
        .flatMap(Collection::stream)
        .filter(n -> n % 2 == 0)
        .collect(Collectors.toList()));
```

**Output:**
```text
[2, 4, 6]
```

**Concept:** Downstream selection applies to individual flattened values.

## Question 3 — Unique words

**Task:** Split Java streams and Java functions by single spaces, retaining unique words in encounter order.

```java
System.out.println(Stream.of("Java streams", "Java functions")
        .flatMap(line -> Arrays.stream(line.split(" ")))
        .distinct()
        .collect(Collectors.toList()));
```

**Output:**
```text
[Java, streams, functions]
```

**Concept:** Flattening does not deduplicate unless distinct is added.

## Question 4 — Primitive-array sum

**Task:** Flatten int arrays {1, 2}, {}, and {3, 4} and sum them without boxing.

```java
System.out.println(Stream.of(new int[]{1, 2}, new int[0], new int[]{3, 4})
        .flatMapToInt(Arrays::stream).sum());
```

**Output:**
```text
10
```

**Concept:** flatMapToInt combines primitive mapper results.

## Question 5 — Null groups and elements

**Task:** Flatten groups ["Ada", null], null, and ["Grace"], omitting missing groups and names.

```java
List<List<String>> groups = Arrays.asList(Arrays.asList("Ada", null), null, Arrays.asList("Grace"));
System.out.println(groups.stream()
        .filter(Objects::nonNull)
        .flatMap(Collection::stream)
        .filter(Objects::nonNull)
        .collect(Collectors.toList()));
```

**Output:**
```text
[Ada, Grace]
```

**Concept:** Null groups and null elements require separate policies.

## Question 6 — Present optionals on Java 8

**Task:** Flatten Optional.of(Ada), Optional.empty(), and Optional.of(Grace) without Optional.stream.

```java
List<Optional<String>> values = Arrays.asList(Optional.of("Ada"), Optional.empty(), Optional.of("Grace"));
System.out.println(values.stream()
        .flatMap(value -> value.isPresent() ? Stream.of(value.get()) : Stream.empty())
        .collect(Collectors.toList()));
```

**Output:**
```text
[Ada, Grace]
```

**Concept:** A present-or-empty branch implements zero-to-one flattening on Java 8.

## Question 7 — Map versus flatMap

**Task:** For [[1, 2], [3]], print the number of mapped inner streams and the number of flattened values using fresh pipelines.

```java
List<List<Integer>> groups = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3));
System.out.println(groups.stream().map(Collection::stream).count());
System.out.println(groups.stream().flatMap(Collection::stream).count());
```

**Output:**
```text
2
3
```

**Concept:** map retains two stream results; flatMap exposes their three elements.

## Question 8 — Zero-to-many expansion

**Task:** Expand each integer in [1, 3] into the inclusive range from one to that integer.

```java
System.out.println(Stream.of(1, 3)
        .flatMap(n -> IntStream.rangeClosed(1, n)
        .boxed())
        .collect(Collectors.toList()));
```

**Output:**
```text
[1, 1, 2, 3]
```

**Concept:** Each input can contribute a different number of results.

## Question 9 — Flatten two nested layers

**Task:** Flatten [[[1], [2, 3]], [[4]]] into one integer list.

```java
List<List<List<Integer>>> groups = Arrays.asList(Arrays.asList(Arrays.asList(1), Arrays.asList(2, 3)), Arrays.asList(Arrays.asList(4)));
System.out.println(groups.stream()
        .flatMap(Collection::stream)
        .flatMap(Collection::stream)
        .collect(Collectors.toList()));
```

**Output:**
```text
[1, 2, 3, 4]
```

**Concept:** One flatMap removes one declared layer, not every nesting level recursively.

## Question 10 — Mapped-stream closure

**Task:** Attach an onClose action to each inner stream from [A, B], flatten them, and print collected values plus the number closed.

```java
java.util.concurrent.atomic.AtomicInteger closed = new java.util.concurrent.atomic.AtomicInteger();
List<String> result = Stream.of("A", "B")
        .flatMap(s -> Stream.of(s).onClose(closed::incrementAndGet))
        .collect(Collectors.toList());
System.out.println(result);
System.out.println(closed.get());
```

**Output:**
```text
[A, B]
2
```

**Concept:** flatMap closes each mapped stream after incorporating its contents; the outer source's resource lifetime remains separate.

---

## Interview Questions and Answers

### Interview Question 1 — How does flatMap differ from map?

**Answer:** map retains each mapper result as one element; flatMap expects streams and merges their elements into the output sequence.

### Interview Question 2 — What does an empty mapped stream contribute?

**Answer:** No output elements, allowing zero-to-many mapping.

### Interview Question 3 — Are mapped streams left open after flattening?

**Answer:** flatMap closes each mapped stream after incorporating it. The outer source's own resource lifetime still needs handling.

### Interview Question 4 — What happens if a flatMap mapper returns null?

**Answer:** The null mapped stream is treated as empty, but explicitly returning Stream.empty communicates intent more clearly. Dereferencing a null inner collection before returning a stream still throws.

### Interview Question 5 — Does flatMap automatically execute in parallel?

**Answer:** No. It follows the pipeline's execution mode and does not itself create an asynchronous task for each input.

---

## Key Takeaways

| Skill | What to remember |
|---|---|
| Flatten nested lists | Empty mapped streams contribute no elements. |
| Flatten and select | Downstream selection applies to individual flattened values. |
| Unique words | Flattening does not deduplicate unless distinct is added. |
| Primitive-array sum | flatMapToInt combines primitive mapper results. |
| Null groups and elements | Null groups and null elements require separate policies. |

[← Road Map lesson](../../Road%20Map/11_Stream_API/164_flatMap.md)

[← 163. map()](163_map.md) | [Course Index](../README.md) | [165. forEach() →](165_forEach.md)
