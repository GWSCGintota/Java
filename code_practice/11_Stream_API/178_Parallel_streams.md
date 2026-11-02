[← 177. joining()](177_joining.md) | [Course Index](../README.md) | [179. Lazy evaluation →](179_Lazy_evaluation.md)

# Java Parallel streams Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/11_Stream_API/178_Parallel_streams.md)

These exercises follow the corresponding Road Map lesson, progressing from basic operations to composition, boundary cases, and safe execution. Each coding question includes a model solution, expected output, and concept explanation.

**Java version:** Java 8+. Short snippets are independent and belong inside `main()`; assume imports from `java.util.*`, `java.util.function.*`, and `java.util.stream.*`. Complete programs include their helper types and should be saved as `Main.java`. Run questions separately. Inputs are non-null unless explicitly handled, and arithmetic uses small values that do not overflow. For checked I/O, `main()` must declare `throws Exception` or handle the failure.

## Question 1 — Sequential and parallel sums

**Task:** Compare sums of longs 1 through 1000 using fresh sequential and parallel streams.

```java
System.out.println(LongStream.rangeClosed(1, 1000).sum());
System.out.println(LongStream.rangeClosed(1, 1000).parallel().sum());
```

**Output:**
```text
500500
500500
```

**Concept:** Correct associative aggregation can preserve the intended result under partitioning.

## Question 2 — Ordered result collection

**Task:** Uppercase [Ada, Grace, Alan] with a parallel stream and collect in source order.

```java
System.out.println(Arrays.asList("Ada", "Grace", "Alan").parallelStream()
        .map(s -> s.toUpperCase(Locale.ROOT))
        .collect(Collectors.toList()));
```

**Output:**
```text
[ADA, GRACE, ALAN]
```

**Concept:** A standard ordered collector merges isolated partial containers correctly.

## Question 3 — Ordered terminal output

**Task:** Print Number 1 through Number 3 from a parallel ordered range.

```java
IntStream.rangeClosed(1, 3).parallel().forEachOrdered(n -> System.out.println("Number " + n));
```

**Output:**
```text
Number 1
Number 2
Number 3
```

**Concept:** Terminal action order does not imply one particular worker thread.

## Question 4 — Short-circuiting match

**Task:** Ask in parallel whether integers 1 through 100 include 42.

```java
System.out.println(IntStream.rangeClosed(1, 100).parallel().anyMatch(n -> n == 42));
```

**Output:**
```text
true
```

**Concept:** Matching needs the Boolean result, not a fixed callback schedule.

## Question 5 — Find any without assuming which

**Task:** Use parallel findAny on integers 1 through 20 and verify the returned value belongs to the source range.

```java
int found = IntStream.rangeClosed(1, 20).parallel().findAny().orElse(-1);
System.out.println(found >= 1 && found <= 20);
```

**Output:**
```text
true
```

**Concept:** findAny permits any element and does not promise a particular or random value.

## Question 6 — Inspect execution mode

**Task:** Print the mode after parallel(), and separately after parallel().sequential().

```java
System.out.println(IntStream.range(0, 3).parallel().isParallel());
System.out.println(IntStream.range(0, 3).parallel().sequential().isParallel());
```

**Output:**
```text
true
false
```

**Concept:** Mode selection applies to pipeline execution rather than creating separately scheduled stage regions.

## Question 7 — Concurrent grouped reduction

**Task:** Count integers 1 through 9 by remainder modulo three using groupingByConcurrent; print groups 0, 1, and 2.

```java
java.util.concurrent.ConcurrentMap<Integer, Long> counts = IntStream.rangeClosed(1, 9).parallel()
        .boxed()
        .collect(Collectors.groupingByConcurrent(n -> n % 3, Collectors.counting()));
System.out.println(counts.get(0));
System.out.println(counts.get(1));
System.out.println(counts.get(2));
```

**Output:**
```text
3
3
3
```

**Concept:** A concurrent collector has explicit accumulation and unordered-result semantics.

## Question 8 — Correct a non-neutral offset

**Task:** Compute the parallel sum of [1, 2, 3, 4] plus ten without using ten as the reduce identity.

```java
int sum = Stream.of(1, 2, 3, 4).parallel().reduce(0, Integer::sum);
System.out.println(sum + 10);
```

**Output:**
```text
20
```

**Concept:** Independent partitions require a neutral identity; a final offset belongs outside reduction.

## Question 9 — Replace a shared result list

**Task:** Produce doubled values of 1 through 5 in parallel without adding into a captured ArrayList.

```java
List<Integer> doubled = IntStream.rangeClosed(1, 5).parallel()
        .map(n -> n * 2)
        .boxed()
        .collect(Collectors.toList());
System.out.println(doubled);
```

**Output:**
```text
[2, 4, 6, 8, 10]
```

**Concept:** Result construction belongs in a correct collector rather than unsafe shared side effects.

## Question 10 — Relax order only when irrelevant

**Task:** Remove the ordering requirement from a parallel range 1 through 100, limit to seven values, and print only the resulting count.

```java
System.out.println(IntStream.rangeClosed(1, 100).parallel().unordered().limit(7).count());
```

**Output:**
```text
7
```

**Concept:** An unordered limit can satisfy a size-only requirement without promising a specific retained prefix.

---

## Interview Questions and Answers

### Interview Question 1 — Does parallel mode promise a speedup?

**Answer:** No. Splitting, scheduling, buffering, combining, and contention have costs.

### Interview Question 2 — Does a parallel collector require every partial container to be thread-safe?

**Answer:** Not for a correct ordinary non-concurrent reduction with isolated containers. Shared or concurrent accumulation has different requirements.

### Interview Question 3 — Does forEachOrdered serialize the whole pipeline?

**Answer:** No. It orders terminal actions; upstream stages can still execute in parallel.

### Interview Question 4 — Does parallelStream provide a dedicated executor?

**Answer:** No. Common JDK implementations often involve fork/join facilities and the common pool, potentially with caller participation. Use explicit task/executor APIs when scheduling control is required.

### Interview Question 5 — What evidence is needed to claim a speedup?

**Answer:** A representative benchmark with warmup, realistic input, correct result consumption, and relevant resource constraints. A single tiny wall-clock timing is not reliable evidence.

---

## Key Takeaways

| Skill | What to remember |
|---|---|
| Sequential and parallel sums | Correct associative aggregation can preserve the intended result under partitioning. |
| Ordered result collection | A standard ordered collector merges isolated partial containers correctly. |
| Ordered terminal output | Terminal action order does not imply one particular worker thread. |
| Short-circuiting match | Matching needs the Boolean result, not a fixed callback schedule. |
| Find any without assuming which | findAny permits any element and does not promise a particular or random value. |

[← Road Map lesson](../../Road%20Map/11_Stream_API/178_Parallel_streams.md)

[← 177. joining()](177_joining.md) | [Course Index](../README.md) | [179. Lazy evaluation →](179_Lazy_evaluation.md)
