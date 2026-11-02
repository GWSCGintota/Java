[← 178. Parallel streams](178_Parallel_streams.md) | [Course Index](../README.md) | [180. Intermediate operations →](180_Intermediate_operations.md)

# Java Lazy evaluation Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/11_Stream_API/179_Lazy_evaluation.md)

These exercises follow the corresponding Road Map lesson, progressing from basic operations to composition, boundary cases, and safe execution. Each coding question includes a model solution, expected output, and concept explanation.

**Java version:** Java 8+. Short snippets are independent and belong inside `main()`; assume imports from `java.util.*`, `java.util.function.*`, and `java.util.stream.*`. Complete programs include their helper types and should be saved as `Main.java`. Run questions separately. Inputs are non-null unless explicitly handled, and arithmetic uses small values that do not overflow. For checked I/O, `main()` must declare `throws Exception` or handle the failure.

## Question 1 — Construction before traversal

**Task:** Build an even-number pipeline over [1, 2, 3, 4], print Pipeline built, then collect values multiplied by ten.

```java
Stream<Integer> pipeline = Stream.of(1, 2, 3, 4).filter(n -> n % 2 == 0).map(n -> n * 10);
System.out.println("Pipeline built");
System.out.println(pipeline.collect(Collectors.toList()));
```

**Output:**
```text
Pipeline built
[20, 40]
```

**Concept:** Intermediate stage construction precedes element traversal.

## Question 2 — No terminal means no mapping work

**Task:** Build a map stage whose body increments a counter, without traversing it; print the counter.

```java
java.util.concurrent.atomic.AtomicInteger calls = new java.util.concurrent.atomic.AtomicInteger();
Stream<Integer> pipeline = Stream.of(1, 2, 3).map(n -> { calls.incrementAndGet(); return n * 2; });
System.out.println(calls.get());
```

**Output:**
```text
0
```

**Concept:** Merely creating a mapper stage does not invoke its element callback.

## Question 3 — Demand for a first match

**Task:** Find the first even value in [1, 3, 4, 6] without requesting a full matching list.

```java
System.out.println(Stream.of(1, 3, 4, 6).filter(n -> n % 2 == 0).findFirst().orElse(-1));
```

**Output:**
```text
4
```

**Concept:** The search result needs only enough evidence to establish the first match.

## Question 4 — Bound filtered iteration

**Task:** Generate the first three positive even values from an unbounded positive-integer iterate source.

```java
System.out.println(Stream.iterate(1, n -> n + 1)
        .filter(n -> n % 2 == 0)
        .limit(3)
        .collect(Collectors.toList()));
```

**Output:**
```text
[2, 4, 6]
```

**Concept:** Demand can flow through lazy stateless stages until a finite bound is satisfied.

## Question 5 — Stateful barrier and candidate set

**Task:** Compare sorted().limit(2) and limit(2).sorted() for [9, 1, 8, 2].

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

**Concept:** A lazy stateful sort still needs the finite candidates it is asked to order.

## Question 6 — Move required effects to a terminal action

**Task:** Record A, B, and C in order as required actions, then count a fresh source stream; do not rely on peek before count.

```java
List<String> source = Arrays.asList("A", "B", "C");
List<String> events = new ArrayList<>();
source.stream().forEachOrdered(events::add);
System.out.println(events);
System.out.println(source.stream().count());
```

**Output:**
```text
[A, B, C]
3
```

**Concept:** Required effects belong in an explicit action; count may legally avoid irrelevant intermediate observers.

## Question 7 — Late-bound ArrayList source

**Task:** Create a filtered ArrayList stream from [1, 2], add 3 before traversal begins, then collect values greater than one.

```java
List<Integer> source = new ArrayList<>(Arrays.asList(1, 2));
Stream<Integer> pipeline = source.stream().filter(n -> n > 1);
source.add(3);
System.out.println(pipeline.collect(Collectors.toList()));
```

**Output:**
```text
[2, 3]
```

**Concept:** This before-traversal change is observed by the standard ArrayList source; it is not permission to mutate during traversal.

## Question 8 — Deferred parse failure

**Task:** Build a parser pipeline over ["1", "oops"], print Created, then collect and catch its parsing failure.

```java
Stream<Integer> pipeline = Stream.of("1", "oops").map(Integer::parseInt);
System.out.println("Created");
try { pipeline
        .collect(Collectors.toList()); } catch (NumberFormatException e) { System.out.println("Invalid integer"); }
```

**Output:**
```text
Created
Invalid integer
```

**Concept:** A callback exception occurs when traversal invokes that callback, not when the stage is declared.

## Question 9 — Terminal completion is not closure

**Task:** Attach an onClose counter to [A, B], count its elements, print the closure count, explicitly close, and print the count again.

```java
java.util.concurrent.atomic.AtomicInteger closed = new java.util.concurrent.atomic.AtomicInteger();
Stream<String> stream = Stream.of("A", "B").onClose(closed::incrementAndGet);
System.out.println(stream.count());
System.out.println(closed.get());
stream.close();
System.out.println(closed.get());
```

**Output:**
```text
2
0
1
```

**Concept:** Completing a terminal calculation does not automatically close the stream.

## Question 10 — Laziness does not make streams reusable

**Task:** Use a fresh-stream Supplier for Ada and Grace, first counting and then joining values.

```java
Supplier<Stream<String>> factory = () -> Stream.of("Ada", "Grace");
System.out.println(factory.get().count());
System.out.println(factory.get().collect(Collectors.joining(", ")));
```

**Output:**
```text
2
Ada, Grace
```

**Concept:** Deferred computation and reusable factories are separate from caching and stream reuse.

---

## Interview Questions and Answers

### Interview Question 1 — Does lazy mean asynchronous?

**Answer:** No. It means work is deferred until demanded; ordinary sequential traversal runs synchronously.

### Interview Question 2 — Does a terminal operation guarantee every mapper executes?

**Answer:** No. Legal optimizations and short-circuiting can avoid work that is unnecessary for the result.

### Interview Question 3 — Does laziness eliminate resource-management duties?

**Answer:** No. A source can open resources before element traversal, and terminal completion does not automatically close it.

### Interview Question 4 — Can invalid stage arguments fail before traversal?

**Answer:** Yes. For example, a negative limit can be rejected during stage construction, whereas exceptions from a mapper body normally occur during traversal.

### Interview Question 5 — Does lazy evaluation guarantee constant memory?

**Answer:** No. Stateful stages such as sorting and deduplication can buffer or retain data even though evaluation begins lazily.

---

## Key Takeaways

| Skill | What to remember |
|---|---|
| Construction before traversal | Intermediate stage construction precedes element traversal. |
| No terminal means no mapping work | Merely creating a mapper stage does not invoke its element callback. |
| Demand for a first match | The search result needs only enough evidence to establish the first match. |
| Bound filtered iteration | Demand can flow through lazy stateless stages until a finite bound is satisfied. |
| Stateful barrier and candidate set | A lazy stateful sort still needs the finite candidates it is asked to order. |

[← Road Map lesson](../../Road%20Map/11_Stream_API/179_Lazy_evaluation.md)

[← 178. Parallel streams](178_Parallel_streams.md) | [Course Index](../README.md) | [180. Intermediate operations →](180_Intermediate_operations.md)
