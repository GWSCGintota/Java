[← 165. forEach()](165_forEach.md) | [Course Index](../README.md) | [167. distinct() →](167_distinct.md)

# Java sorted() Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/11_Stream_API/166_sorted.md)

These exercises follow the corresponding Road Map lesson, progressing from basic operations to composition, boundary cases, and safe execution. Each coding question includes a model solution, expected output, and concept explanation.

**Java version:** Java 8+. Short snippets are independent and belong inside `main()`; assume imports from `java.util.*`, `java.util.function.*`, and `java.util.stream.*`. Complete programs include their helper types and should be saved as `Main.java`. Run questions separately. Inputs are non-null unless explicitly handled, and arithmetic uses small values that do not overflow. For checked I/O, `main()` must declare `throws Exception` or handle the failure.

## Question 1 — Ascending integers

**Task:** Sort [4, 1, 3, 1] ascending, retaining duplicates.

```java
System.out.println(Stream.of(4, 1, 3, 1).sorted().collect(Collectors.toList()));
```

**Output:**
```text
[1, 1, 3, 4]
```

**Concept:** Sorting orders values without deduplicating them.

## Question 2 — Descending integers

**Task:** Sort boxed values [4, 1, 3] descending.

```java
System.out.println(Stream.of(4, 1, 3).sorted(Comparator.reverseOrder()).collect(Collectors.toList()));
```

**Output:**
```text
[4, 3, 1]
```

**Concept:** Comparator sorting is available for reference streams.

## Question 3 — Length then name

**Task:** Sort [Bob, Grace, Ada, Alan] by length and alphabetically for equal lengths.

```java
Comparator<String> order = Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder());
System.out.println(Stream.of("Bob", "Grace", "Ada", "Alan").sorted(order).collect(Collectors.toList()));
```

**Output:**
```text
[Ada, Bob, Alan, Grace]
```

**Concept:** A secondary comparator specifies a meaningful tie policy.

## Question 4 — Nulls last

**Task:** Sort [Bob, null, Ada] lexically with null last.

```java
System.out.println(Arrays.asList("Bob", null, "Ada").stream()
        .sorted(Comparator.nullsLast(Comparator.naturalOrder()))
        .collect(Collectors.toList()));
```

**Output:**
```text
[Ada, Bob, null]
```

**Concept:** A null-aware comparator defines missing-value placement.

## Question 5 — Preserve the original

**Task:** Sort [3, 1, 2] into a new result and print the unchanged source.

```java
List<Integer> source = Arrays.asList(3, 1, 2);
System.out.println(source.stream().sorted().collect(Collectors.toList()));
System.out.println(source);
```

**Output:**
```text
[1, 2, 3]
[3, 1, 2]
```

**Concept:** Stream sorting does not reorder the original list.

## Question 6 — Sort and limit order

**Task:** Compare sorting then limiting with limiting then sorting for [9, 1, 8, 2], with a bound of two.

```java
List<Integer> values = Arrays.asList(9, 1, 8, 2);
System.out.println(values.stream().sorted().limit(2).collect(Collectors.toList()));
System.out.println(values.stream().limit(2).sorted().collect(Collectors.toList()));
```

**Output:**
```text
[1, 2]
[1, 9]
```

**Concept:** Limiting before sorting changes the candidates, not just performance.

## Question 7 — Stable ties

**Task:** Sort [Bob, Ada, Alan, Grace] by length only and retain equal-length encounter order.

```java
System.out.println(Stream.of("Bob", "Ada", "Alan", "Grace")
        .sorted(Comparator.comparingInt(String::length))
        .collect(Collectors.toList()));
```

**Output:**
```text
[Bob, Ada, Alan, Grace]
```

**Concept:** Sorting an ordered stream is stable for comparator-equal elements.

## Question 8 — Reverse only the primary key

**Task:** Sort [Bob, Grace, Ada, Alan] by descending length, then ascending name.

```java
Comparator<String> order = Comparator.comparingInt(String::length).reversed().thenComparing(Comparator.naturalOrder());
System.out.println(Stream.of("Bob", "Grace", "Ada", "Alan").sorted(order).collect(Collectors.toList()));
```

**Output:**
```text
[Grace, Alan, Ada, Bob]
```

**Concept:** Calling reversed before thenComparing reverses only the primary comparator.

## Question 9 — Primitive sorting

**Task:** Sort primitive values [3, -1, 2] and print an array.

```java
System.out.println(Arrays.toString(IntStream.of(3, -1, 2).sorted().toArray()));
```

**Output:**
```text
[-1, 2, 3]
```

**Concept:** Primitive streams have natural ascending sorting without a Comparator overload.

## Question 10 — Find an extreme without sorting

**Task:** Find the longest name in [Ada, Grace, Alan] using max rather than a full sorted result.

```java
System.out.println(Stream.of("Ada", "Grace", "Alan").max(Comparator.comparingInt(String::length)).orElse("None"));
```

**Output:**
```text
Grace
```

**Concept:** A direct extreme-value reduction avoids requesting a full sorted sequence.

---

## Interview Questions and Answers

### Interview Question 1 — Is stream sorting stable?

**Answer:** For ordered streams, comparator-equal elements retain encounter order. Unordered streams have no such stability guarantee.

### Interview Question 2 — Does sorted remove duplicates?

**Answer:** No. Equality-based duplicate removal is distinct, a separate operation.

### Interview Question 3 — Why can sorted prevent short-circuiting from finishing on infinite input?

**Answer:** Global order cannot generally be determined without exhausting an unbounded source.

### Interview Question 4 — Why use comparingInt instead of subtracting keys?

**Answer:** Comparator subtraction can overflow and yield an incorrect sign. Primitive comparison factories express ordering safely.

### Interview Question 5 — Can sorted().limit(1) finish on an arbitrary infinite source?

**Answer:** Global sorting generally needs to exhaust the source first. A downstream bound does not make that unbounded prerequisite finish.

---

## Key Takeaways

| Skill | What to remember |
|---|---|
| Ascending integers | Sorting orders values without deduplicating them. |
| Descending integers | Comparator sorting is available for reference streams. |
| Length then name | A secondary comparator specifies a meaningful tie policy. |
| Nulls last | A null-aware comparator defines missing-value placement. |
| Preserve the original | Stream sorting does not reorder the original list. |

[← Road Map lesson](../../Road%20Map/11_Stream_API/166_sorted.md)

[← 165. forEach()](165_forEach.md) | [Course Index](../README.md) | [167. distinct() →](167_distinct.md)
