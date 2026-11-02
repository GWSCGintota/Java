[← 168. limit()](168_limit.md) | [Course Index](../README.md) | [170. reduce() →](170_reduce.md)

# Java skip() Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/11_Stream_API/169_skip.md)

These exercises follow the corresponding Road Map lesson, progressing from basic operations to composition, boundary cases, and safe execution. Each coding question includes a model solution, expected output, and concept explanation.

**Java version:** Java 8+. Short snippets are independent and belong inside `main()`; assume imports from `java.util.*`, `java.util.function.*`, and `java.util.stream.*`. Complete programs include their helper types and should be saved as `Main.java`. Run questions separately. Inputs are non-null unless explicitly handled, and arithmetic uses small values that do not overflow. For checked I/O, `main()` must declare `throws Exception` or handle the failure.

## Question 1 — Discard a prefix

**Task:** Skip the first two values in [1, 2, 3, 4, 5].

```java
System.out.println(Stream.of(1, 2, 3, 4, 5).skip(2).collect(Collectors.toList()));
```

**Output:**
```text
[3, 4, 5]
```

**Concept:** Ordered skip discards a prefix and forwards its suffix.

## Question 2 — Skip zero

**Task:** Apply skip(0) to [1, 2, 3].

```java
System.out.println(Stream.of(1, 2, 3).skip(0).collect(Collectors.toList()));
```

**Output:**
```text
[1, 2, 3]
```

**Concept:** A zero offset preserves all source elements.

## Question 3 — Exact and oversized offsets

**Task:** Count the suffix after skipping 3 and after skipping 10 from [1, 2, 3], using fresh streams.

```java
List<Integer> values = Arrays.asList(1, 2, 3);
System.out.println(values.stream().skip(3).count());
System.out.println(values.stream().skip(10).count());
```

**Output:**
```text
0
0
```

**Concept:** Skipping the whole source or more leaves an empty suffix.

## Question 4 — Negative offset

**Task:** Catch skip(-1) and print Negative offset.

```java
try { Stream.of(1, 2)
        .skip(-1); } catch (IllegalArgumentException e) { System.out.println("Negative offset"); }
```

**Output:**
```text
Negative offset
```

**Concept:** Offsets must be nonnegative.

## Question 5 — Second page

**Task:** Return zero-based page 1 of size 3 from [1, 2, 3, 4, 5, 6, 7].

```java
int pageIndex = 1, pageSize = 3;
if (pageIndex < 0 || pageSize <= 0) { throw new IllegalArgumentException("Invalid page"); }
long offset = Math.multiplyExact((long) pageIndex, (long) pageSize);
System.out.println(Stream.of(1, 2, 3, 4, 5, 6, 7).skip(offset).limit(pageSize).collect(Collectors.toList()));
```

**Output:**
```text
[4, 5, 6]
```

**Concept:** Validate pagination and widen operands before multiplication.

## Question 6 — Stable sorted page

**Task:** Sort [Grace, Bob, Ada, Alan, Emma] lexically, then take page 1 of size 2.

```java
System.out.println(Stream.of("Grace", "Bob", "Ada", "Alan", "Emma")
        .sorted()
        .skip(2)
        .limit(2)
        .collect(Collectors.toList()));
```

**Output:**
```text
[Bob, Emma]
```

**Concept:** Pagination needs a defined order before slicing.

## Question 7 — Skip matching elements

**Task:** Remove the first even match from [1, 2, 3, 4, 5, 6].

```java
System.out.println(Stream.of(1, 2, 3, 4, 5, 6).filter(n -> n % 2 == 0).skip(1).collect(Collectors.toList()));
```

**Output:**
```text
[4, 6]
```

**Concept:** A skip after filtering counts matches, not original positions.

## Question 8 — Compare skip and limit order

**Task:** Compare skip(2).limit(3) with limit(3).skip(2) on [1, 2, 3, 4, 5, 6].

```java
List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6);
System.out.println(values.stream().skip(2).limit(3).collect(Collectors.toList()));
System.out.println(values.stream().limit(3).skip(2).collect(Collectors.toList()));
```

**Output:**
```text
[3, 4, 5]
[3]
```

**Concept:** Slicing stages are not interchangeable.

## Question 9 — Bound an infinite suffix

**Task:** Generate positive integers, skip the first three, and take the next three.

```java
System.out.println(Stream.iterate(1, n -> n + 1).skip(3).limit(3).collect(Collectors.toList()));
```

**Output:**
```text
[4, 5, 6]
```

**Concept:** skip alone does not bound an infinite remaining suffix.

## Question 10 — Wide offset arithmetic

**Task:** Compute the offset for page index 1,500,000,000 and size 3 without overflowing int arithmetic.

```java
long offset = Math.multiplyExact((long) 1_500_000_000, 3L);
System.out.println(offset);
```

**Output:**
```text
4500000000
```

**Concept:** Widening operands before multiplication prevents an already-overflowed int result.

---

## Interview Questions and Answers

### Interview Question 1 — Does skip remove elements from the collection?

**Answer:** No. It changes which stream elements continue downstream.

### Interview Question 2 — Is skip a short-circuiting operation?

**Answer:** It is stateful but not a terminating bound by itself; an unbounded suffix can remain.

### Interview Question 3 — Why can ordered parallel skip be expensive?

**Answer:** Preserving the correct encounter-order prefix removal across partitions can require coordination.

### Interview Question 4 — Does sorting make pages immune to dataset changes?

**Answer:** No. Insertions, removals, or changed sort keys between requests can shift offsets even when each individual request uses a stable sort.

### Interview Question 5 — Should a database query load every row and then use skip?

**Answer:** Usually pagination should be applied at the query layer when only one page is needed. Java stream slicing does not eliminate retrieval and materialization costs already paid.

---

## Key Takeaways

| Skill | What to remember |
|---|---|
| Discard a prefix | Ordered skip discards a prefix and forwards its suffix. |
| Skip zero | A zero offset preserves all source elements. |
| Exact and oversized offsets | Skipping the whole source or more leaves an empty suffix. |
| Negative offset | Offsets must be nonnegative. |
| Second page | Validate pagination and widen operands before multiplication. |

[← Road Map lesson](../../Road%20Map/11_Stream_API/169_skip.md)

[← 168. limit()](168_limit.md) | [Course Index](../README.md) | [170. reduce() →](170_reduce.md)
