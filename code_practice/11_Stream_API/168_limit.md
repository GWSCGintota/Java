[← 167. distinct()](167_distinct.md) | [Course Index](../README.md) | [169. skip() →](169_skip.md)

# Java limit() Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/11_Stream_API/168_limit.md)

These exercises follow the corresponding Road Map lesson, progressing from basic operations to composition, boundary cases, and safe execution. Each coding question includes a model solution, expected output, and concept explanation.

**Java version:** Java 8+. Short snippets are independent and belong inside `main()`; assume imports from `java.util.*`, `java.util.function.*`, and `java.util.stream.*`. Complete programs include their helper types and should be saved as `Main.java`. Run questions separately. Inputs are non-null unless explicitly handled, and arithmetic uses small values that do not overflow. For checked I/O, `main()` must declare `throws Exception` or handle the failure.

## Question 1 — Take a prefix

**Task:** Retain the first three values of [1, 2, 3, 4, 5].

```java
System.out.println(Stream.of(1, 2, 3, 4, 5).limit(3).collect(Collectors.toList()));
```

**Output:**
```text
[1, 2, 3]
```

**Concept:** Ordered limit selects at most the specified encounter-order prefix.

## Question 2 — Zero limit

**Task:** Apply limit(0) to [1, 2, 3] and collect the result.

```java
System.out.println(Stream.of(1, 2, 3).limit(0).collect(Collectors.toList()));
```

**Output:**
```text
[]
```

**Concept:** Zero is a valid maximum and yields no retained elements.

## Question 3 — Oversized bound

**Task:** Apply limit(10) to [1, 2] without padding the result.

```java
System.out.println(Stream.of(1, 2).limit(10).collect(Collectors.toList()));
```

**Output:**
```text
[1, 2]
```

**Concept:** limit is an upper bound, not a request to create missing values.

## Question 4 — Negative bound

**Task:** Catch the failure from limit(-1) and print Negative limit.

```java
try { Stream.of(1, 2)
        .limit(-1); } catch (IllegalArgumentException e) { System.out.println("Negative limit"); }
```

**Output:**
```text
Negative limit
```

**Concept:** Invalid negative bounds are rejected during stage construction.

## Question 5 — First three positives

**Task:** Select the first three positive values from [-2, 0, 1, -1, 3, 4, 5].

```java
System.out.println(Stream.of(-2, 0, 1, -1, 3, 4, 5).filter(n -> n > 0).limit(3).collect(Collectors.toList()));
```

**Output:**
```text
[1, 3, 4]
```

**Concept:** Filtering before limiting applies the maximum to matching values.

## Question 6 — Filter position

**Task:** Compare filter-even then limit(2) with limit(2) then filter-even on [1, 2, 3, 4].

```java
List<Integer> values = Arrays.asList(1, 2, 3, 4);
System.out.println(values.stream().filter(n -> n % 2 == 0).limit(2).collect(Collectors.toList()));
System.out.println(values.stream().limit(2).filter(n -> n % 2 == 0).collect(Collectors.toList()));
```

**Output:**
```text
[2, 4]
[2]
```

**Concept:** Moving a bound across selection changes its meaning.

## Question 7 — Bound powers of two

**Task:** Generate the first six powers of two from an iterate source.

```java
System.out.println(Stream.iterate(1, n -> n * 2).limit(6).collect(Collectors.toList()));
```

**Output:**
```text
[1, 2, 4, 8, 16, 32]
```

**Concept:** A finite bound makes this generated collection terminate.

## Question 8 — Smallest values

**Task:** Find the two smallest values in [9, 1, 8, 2] using sorting and a limit.

```java
System.out.println(Stream.of(9, 1, 8, 2).sorted().limit(2).collect(Collectors.toList()));
```

**Output:**
```text
[1, 2]
```

**Concept:** Sorting before limiting selects from the complete finite candidate set.

## Question 9 — Ordered parallel prefix

**Task:** Take the first five values of a parallel range from 1 through 20 and collect them in order.

```java
System.out.println(IntStream.rangeClosed(1, 20).parallel().limit(5).boxed().collect(Collectors.toList()));
```

**Output:**
```text
[1, 2, 3, 4, 5]
```

**Concept:** Ordered parallel limiting preserves prefix semantics, potentially with coordination costs.

## Question 10 — Limit after deduplication

**Task:** Return the first three unique values of [1, 1, 2, 2, 3, 4].

```java
System.out.println(Stream.of(1, 1, 2, 2, 3, 4).distinct().limit(3).collect(Collectors.toList()));
```

**Output:**
```text
[1, 2, 3]
```

**Concept:** Bounding after distinct counts retained equality representatives.

---

## Interview Questions and Answers

### Interview Question 1 — Is limit terminal?

**Answer:** No. It is a short-circuiting intermediate stage; a terminal operation is still needed.

### Interview Question 2 — Does limit guarantee termination on every infinite pipeline?

**Answer:** No. Upstream stages must be capable of producing the requested elements without first requiring unbounded work.

### Interview Question 3 — What does limit mean without encounter order?

**Answer:** It constrains the result size, but a particular first-n subset is not promised.

### Interview Question 4 — Does limit close an I/O-backed stream?

**Answer:** No. Even a short-circuiting pipeline must close its owned source explicitly, normally with try-with-resources.

### Interview Question 5 — Does a prefix bound guarantee little upstream work?

**Answer:** No. Upstream sorting, filtering, source characteristics, and parallel scheduling can require more work than the retained output size.

---

## Key Takeaways

| Skill | What to remember |
|---|---|
| Take a prefix | Ordered limit selects at most the specified encounter-order prefix. |
| Zero limit | Zero is a valid maximum and yields no retained elements. |
| Oversized bound | limit is an upper bound, not a request to create missing values. |
| Negative bound | Invalid negative bounds are rejected during stage construction. |
| First three positives | Filtering before limiting applies the maximum to matching values. |

[← Road Map lesson](../../Road%20Map/11_Stream_API/168_limit.md)

[← 167. distinct()](167_distinct.md) | [Course Index](../README.md) | [169. skip() →](169_skip.md)
