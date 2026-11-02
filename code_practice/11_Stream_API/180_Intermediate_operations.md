[← 179. Lazy evaluation](179_Lazy_evaluation.md) | [Course Index](../README.md) | [181. Terminal operations →](181_Terminal_operations.md)

# Java Intermediate operations Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/11_Stream_API/180_Intermediate_operations.md)

These exercises follow the corresponding Road Map lesson, progressing from basic operations to composition, boundary cases, and safe execution. Each coding question includes a model solution, expected output, and concept explanation.

**Java version:** Java 8+. Short snippets are independent and belong inside `main()`; assume imports from `java.util.*`, `java.util.function.*`, and `java.util.stream.*`. Complete programs include their helper types and should be saved as `Main.java`. Run questions separately. Inputs are non-null unless explicitly handled, and arithmetic uses small values that do not overflow. For checked I/O, `main()` must declare `throws Exception` or handle the failure.

## Question 1 — Follow a type change

**Task:** Trim [Ada, Grace, Bob], retain names longer than three, and map the result to lengths.

```java
List<Integer> lengths = Stream.of("Ada", "Grace", "Bob")
        .map(String::trim)
        .filter(s -> s.length() > 3)
        .map(String::length)
        .collect(Collectors.toList());
System.out.println(lengths);
```

**Output:**
```text
[5]
```

**Concept:** map can change the element type while filter retains it.

## Question 2 — Compose normalization stages

**Task:** Trim and uppercase [" ada ", "Ada", " grace "], deduplicate, and sort.

```java
System.out.println(Stream.of(" ada ", "Ada", " grace ")
        .map(String::trim)
        .map(s -> s.toUpperCase(Locale.ROOT))
        .distinct()
        .sorted()
        .collect(Collectors.toList()));
```

**Output:**
```text
[ADA, GRACE]
```

**Concept:** Stateless normalization can precede equality tracking and stateful ordering.

## Question 3 — Primitive type transition

**Task:** Map Ada and Grace to primitive lengths, double those lengths, and box the results into a list.

```java
System.out.println(Stream.of("Ada", "Grace")
        .mapToInt(String::length)
        .map(n -> n * 2)
        .boxed()
        .collect(Collectors.toList()));
```

**Output:**
```text
[6, 10]
```

**Concept:** mapToInt changes stream kind; IntStream.map stays primitive and boxed adapts back.

## Question 4 — Flatten then summarize

**Task:** Flatten [[3, 1], [2, 3]], deduplicate, sort, and collect.

```java
System.out.println(Stream.of(Arrays.asList(3, 1), Arrays.asList(2, 3))
        .flatMap(Collection::stream)
        .distinct()
        .sorted()
        .collect(Collectors.toList()));
```

**Output:**
```text
[1, 2, 3]
```

**Concept:** Flattening, uniqueness, and sorting express distinct stages.

## Question 5 — Filter versus map

**Task:** From [1, 2, 3, 4], retain evens and convert them into Even n labels.

```java
System.out.println(Stream.of(1, 2, 3, 4)
        .filter(n -> n % 2 == 0)
        .map(n -> "Even " + n)
        .collect(Collectors.toList()));
```

**Output:**
```text
[Even 2, Even 4]
```

**Concept:** Selection discards values; projection changes retained values.

## Question 6 — Prefix slice of ranked data

**Task:** Sort [9, 1, 8, 2, 7], skip one, and take the next two values.

```java
System.out.println(Stream.of(9, 1, 8, 2, 7).sorted().skip(1).limit(2).collect(Collectors.toList()));
```

**Output:**
```text
[2, 7]
```

**Concept:** Stateful stages can define an ordered ranked slice.

## Question 7 — Safe nullable pipeline

**Task:** For [null, " Ada ", "", "Grace"], guard null, trim, remove empty strings, and collect.

```java
System.out.println(Arrays.asList(null, " Ada ", "", "Grace").stream()
        .filter(Objects::nonNull)
        .map(String::trim)
        .filter(s -> !s.isEmpty())
        .collect(Collectors.toList()));
```

**Output:**
```text
[Ada, Grace]
```

**Concept:** Intermediate stages need an intentional order for null and validation policies.

## Question 8 — Finite source from iteration

**Task:** Create positive multiples of two, skip the first two, take three, and sum their values.

```java
System.out.println(IntStream.iterate(2, n -> n + 2).skip(2).limit(3).sum());
```

**Output:**
```text
24
```

**Concept:** skip removes a prefix, limit bounds the remaining source, and sum is terminal.

## Question 9 — Terminal required for a result

**Task:** Store a map/filter pipeline over [1, 2, 3], print Ready, and then collect transformed values greater than three.

```java
Stream<Integer> pipeline = Stream.of(1, 2, 3).map(n -> n * 2).filter(n -> n > 3);
System.out.println("Ready");
System.out.println(pipeline.collect(Collectors.toList()));
```

**Output:**
```text
Ready
[4, 6]
```

**Concept:** Intermediate calls return stream stages rather than completed result containers.

## Question 10 — Preserve source and avoid shared accumulation

**Task:** Square [1, 2, 3] into a returned list using a parallel pipeline, then print the original list.

```java
List<Integer> source = Arrays.asList(1, 2, 3);
List<Integer> result = source.parallelStream().map(n -> n * n).collect(Collectors.toList());
System.out.println(result);
System.out.println(source);
```

**Output:**
```text
[1, 4, 9]
[1, 2, 3]
```

**Concept:** Non-interfering mapping plus a proper terminal collector separates transformation from external mutation.

---

## Interview Questions and Answers

### Interview Question 1 — What makes an operation intermediate?

**Answer:** It returns another stream stage rather than a final result and defers element processing until terminal demand.

### Interview Question 2 — Can a stateless operation have an unsafe callback?

**Answer:** Yes. The operation's category does not make captured user state immutable or thread-safe.

### Interview Question 3 — Why can operation order affect correctness?

**Answer:** Selection, transformation, prefix slicing, sorting, and deduplication operate on different values or candidate sets depending on their position.

### Interview Question 4 — Which Java 8 stages here are stateful?

**Answer:** sorted, distinct, skip, and limit carry cross-element state. Stateful does not mean eager; their work still responds to terminal demand.

### Interview Question 5 — Can a linked intermediate stream be reused as an independent branch?

**Answer:** No. Streams and their linked stages are single use. Keep reusable data or a fresh-stream factory for separate calculations.

---

## Key Takeaways

| Skill | What to remember |
|---|---|
| Follow a type change | map can change the element type while filter retains it. |
| Compose normalization stages | Stateless normalization can precede equality tracking and stateful ordering. |
| Primitive type transition | mapToInt changes stream kind; IntStream.map stays primitive and boxed adapts back. |
| Flatten then summarize | Flattening, uniqueness, and sorting express distinct stages. |
| Filter versus map | Selection discards values; projection changes retained values. |

[← Road Map lesson](../../Road%20Map/11_Stream_API/180_Intermediate_operations.md)

[← 179. Lazy evaluation](179_Lazy_evaluation.md) | [Course Index](../README.md) | [181. Terminal operations →](181_Terminal_operations.md)
