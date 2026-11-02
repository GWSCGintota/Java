[← 159. Higher-order style programming](../10_Functional_Programming/159_Higher-order_style_programming.md) | [Course Index](../README.md) | [161. Creating streams →](161_Creating_streams.md)

# Java Streams Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/11_Stream_API/160_Streams.md)

These exercises follow the corresponding Road Map lesson, progressing from basic operations to composition, boundary cases, and safe execution. Each coding question includes a model solution, expected output, and concept explanation.

**Java version:** Java 8+. Short snippets are independent and belong inside `main()`; assume imports from `java.util.*`, `java.util.function.*`, and `java.util.stream.*`. Complete programs include their helper types and should be saved as `Main.java`. Run questions separately. Inputs are non-null unless explicitly handled, and arithmetic uses small values that do not overflow. For checked I/O, `main()` must declare `throws Exception` or handle the failure.

## Question 1 — Positive doubled values

**Task:** From [-2, 0, 3, 4], retain positive values and double them into a list.

```java
List<Integer> result = Stream.of(-2, 0, 3, 4).filter(n -> n > 0).map(n -> n * 2).collect(Collectors.toList());
System.out.println(result);
```

**Output:**
```text
[6, 8]
```

**Concept:** A pipeline separates selection, transformation, and terminal result construction.

## Question 2 — Even squares

**Task:** Square the even values in [1, 2, 3, 4, 5, 6].

```java
System.out.println(Stream.of(1, 2, 3, 4, 5, 6)
        .filter(n -> n % 2 == 0)
        .map(n -> n * n)
        .collect(Collectors.toList()));
```

**Output:**
```text
[4, 16, 36]
```

**Concept:** Intermediate operations compose without materializing a list at every stage.

## Question 3 — Primitive length sum

**Task:** Sum the lengths of Ada, Grace, and Alan without boxed length results.

```java
int total = Stream.of("Ada", "Grace", "Alan").mapToInt(String::length).sum();
System.out.println(total);
```

**Output:**
```text
12
```

**Concept:** mapToInt creates a primitive stream with a sum terminal.

## Question 4 — Preserve the source

**Task:** Triple [1, 2, 3] into a new list and print both source and result.

```java
List<Integer> source = Arrays.asList(1, 2, 3);
List<Integer> result = source.stream().map(n -> n * 3).collect(Collectors.toList());
System.out.println(source);
System.out.println(result);
```

**Output:**
```text
[1, 2, 3]
[3, 6, 9]
```

**Concept:** A non-mutating mapper leaves source values unchanged.

## Question 5 — Two fresh pipelines

**Task:** Calculate count and sum from [2, 3, 4] without reusing a consumed stream.

```java
List<Integer> values = Arrays.asList(2, 3, 4);
System.out.println(values.stream().count());
System.out.println(values.stream().mapToInt(Integer::intValue).sum());
```

**Output:**
```text
3
9
```

**Concept:** Reusable source data can create a fresh stream for each terminal.

## Question 6 — Empty numeric results

**Task:** Print an empty IntStream's count, sum, and whether its average is present.

```java
System.out.println(IntStream.empty().count());
System.out.println(IntStream.empty().sum());
System.out.println(IntStream.empty().average().isPresent());
```

**Output:**
```text
0
0
false
```

**Concept:** Count and sum have zero identities; an empty average has no observed value.

## Question 7 — First matching value

**Task:** Find the first number greater than three in [1, 3, 4, 6], displaying -1 if absent.

```java
System.out.println(Stream.of(1, 3, 4, 6).filter(n -> n > 3).findFirst().orElse(-1));
```

**Output:**
```text
4
```

**Concept:** A short-circuiting search can finish once the required first result is established.

## Question 8 — Normalize and deduplicate

**Task:** Trim and uppercase [" ada ", "Ada", "grace"], then retain unique values in encounter order.

```java
System.out.println(Stream.of(" ada ", "Ada", "grace")
        .map(String::trim)
        .map(s -> s.toUpperCase(Locale.ROOT))
        .distinct()
        .collect(Collectors.toList()));
```

**Output:**
```text
[ADA, GRACE]
```

**Concept:** Normalization before deduplication defines which values count as equal.

## Question 9 — Ordered terminal action

**Task:** Print Item 1 through Item 3 using a primitive range and an ordered terminal action.

```java
IntStream.rangeClosed(1, 3).mapToObj(n -> "Item " + n).forEachOrdered(System.out::println);
```

**Output:**
```text
Item 1
Item 2
Item 3
```

**Concept:** forEachOrdered performs terminal actions rather than constructing returned data.

## Question 10 — Bound a generated source

**Task:** Generate the first five positive multiples of three using iterate.

```java
System.out.println(Stream.iterate(3, n -> n + 3).limit(5).collect(Collectors.toList()));
```

**Output:**
```text
[3, 6, 9, 12, 15]
```

**Concept:** An unbounded source needs a suitable bound before full-result collection.

---

## Interview Questions and Answers

### Interview Question 1 — Does a stream store its own elements?

**Answer:** It conveys values from a source through operations; the source may store data, generate it, or read it from I/O.

### Interview Question 2 — Does a pipeline necessarily examine every element?

**Answer:** No. Short-circuiting and legal optimizations can avoid unnecessary processing.

### Interview Question 3 — Are streams immutable or thread-safe by definition?

**Answer:** No. Callback state, source interference, mutable elements, and execution mode all need deliberate handling.

### Interview Question 4 — How are streams different from I/O streams?

**Answer:** java.util.stream represents aggregate data-processing pipelines; java.io input and output streams represent byte-oriented I/O. A pipeline can still use an I/O-backed source.

### Interview Question 5 — Does stream processing guarantee source immutability?

**Answer:** No. Ordinary non-interfering operations do not replace source elements, but callbacks can still mutate objects or violate source-interference rules.

---

## Key Takeaways

| Skill | What to remember |
|---|---|
| Positive doubled values | A pipeline separates selection, transformation, and terminal result construction. |
| Even squares | Intermediate operations compose without materializing a list at every stage. |
| Primitive length sum | mapToInt creates a primitive stream with a sum terminal. |
| Preserve the source | A non-mutating mapper leaves source values unchanged. |
| Two fresh pipelines | Reusable source data can create a fresh stream for each terminal. |

[← Road Map lesson](../../Road%20Map/11_Stream_API/160_Streams.md)

[← 159. Higher-order style programming](../10_Functional_Programming/159_Higher-order_style_programming.md) | [Course Index](../README.md) | [161. Creating streams →](161_Creating_streams.md)
