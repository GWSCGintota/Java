# 179. Lazy evaluation

[Coding practice →](../../code_practice/11_Stream_API/179_Lazy_evaluation.md)

**Section:** Stream API

**Level:** Beginner → Advanced

**Version:** Runnable examples use Java 8-compatible APIs. Later additions are labeled explicitly.

## Learning goals

- Explain deferred intermediate processing and terminal demand.
- Trace short-circuiting without depending on callback side effects.
- Distinguish laziness from snapshots, caching, resource opening, and parallelism.

**Example conventions:** Short snippets are independent and belong inside a method. Assume `java.util.*`, `java.util.function.*`, and `java.util.stream.*` imports unless shown otherwise. Inputs are non-null unless a snippet handles null explicitly. Numeric examples use small values that do not overflow.

## 1. Pipeline construction versus traversal

Intermediate operations build a pipeline; they do not immediately process source elements. A terminal operation initiates the work necessary for its result. Laziness is about deferred evaluation, not automatic memoization or asynchronous execution.

```java
Stream<Integer> pipeline = Stream.of(1, 2, 3, 4)
        .filter(n -> n % 2 == 0).map(n -> n * 10);
System.out.println("Pipeline built");
System.out.println(pipeline.collect(Collectors.toList())); // [20, 40]
```

The first printed message precedes traversal. The pipeline does not produce and cache a fully filtered intermediate list when `filter` is called.

## 2. Demand and short-circuiting

```java
Optional<Integer> first = Stream.of(1, 3, 4, 6)
        .filter(n -> n % 2 == 0).findFirst();
System.out.println(first.orElse(-1)); // 4
System.out.println(Stream.iterate(1, n -> n + 1)
        .filter(n -> n % 2 == 0).limit(3).collect(Collectors.toList()));
// [2, 4, 6]
```

In a sequential pipeline, stateless stages can be fused so a source value flows through several operations before the next value is requested. Short-circuiting permits stopping when a result is established. Parallel scheduling may do additional work, so callback counts and worker traces are not a stable application contract.

Not every combination terminates: a filter that never matches cannot satisfy `limit(1)`, and sorting an unbounded source generally cannot yield its globally first sorted element.

## 3. Stateful stages affect when values can flow

`sorted` may need the whole finite input before emitting its first result. `distinct` tracks previously seen values; ordered parallel forms can require substantial coordination. `skip` and `limit` also carry cross-element state.

```java
System.out.println(Stream.of(9, 1, 8, 2).sorted().limit(2)
        .collect(Collectors.toList())); // [1, 2]
System.out.println(Stream.of(9, 1, 8, 2).limit(2).sorted()
        .collect(Collectors.toList())); // [1, 9]
```

Both pipelines are lazy, but the ordering changes the candidate set and the amount of work required. Laziness does not mean constant memory, zero buffering, or the same performance for every stage sequence.

## 4. Optimization and peek

The implementation may elide operations whose results cannot affect the terminal result. For example, a known-size source followed only by size-preserving transformations can let `count()` use size information rather than call each mapper or observer.

```java
long count = Stream.of("Ada", "Grace").map(String::toUpperCase).count();
System.out.println(count); // 2
```

There is no need to compute uppercase strings to know this count. Do not put required database writes, event publication, validation effects, or counters inside `peek`, `map`, or `filter` and then depend on every callback running. `peek` can help observe a particular traversal while debugging; required actions belong in explicit processing or an appropriate terminal action.

## 5. Source binding, resources, and failure timing

A stream is not automatically a source snapshot. For a standard `ArrayList`, changes made before traversal starts can be observed by its stream. Changes during traversal can violate non-interference; concurrency support depends on the source's contract.

```java
List<Integer> values = new ArrayList<>(Arrays.asList(1, 2));
Stream<Integer> pipeline = values.stream().filter(n -> n > 1);
values.add(3); // before traversal, not from a callback
System.out.println(pipeline.collect(Collectors.toList())); // [2, 3]
```

Creating `Files.lines` can open a resource before line processing begins. Laziness does not remove closure obligations. Callback failures usually occur during traversal, not at pipeline declaration, while invalid stage arguments can fail immediately. A stream remains single use: deferred computation is not cached computation.

## 6. Complete runnable example

Save as `StreamLesson179.java`.

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamLesson179 {
    public static void main(String[] args) throws Exception {
        Stream<Integer> pipeline = Stream.of(1, 2, 3, 4)
                .filter(n -> n % 2 == 0).map(n -> n * 10);
        System.out.println("Pipeline built");
        System.out.println(pipeline.collect(Collectors.toList()));
        System.out.println(Stream.iterate(1, n -> n + 1)
                .filter(n -> n % 2 == 0).limit(3).collect(Collectors.toList()));
        System.out.println(Stream.of("Ada", "Grace").map(String::length).count());
        List<Integer> values = new ArrayList<>(Arrays.asList(1, 2));
        Stream<Integer> later = values.stream().filter(n -> n > 1);
        values.add(3);
        System.out.println(later.collect(Collectors.toList()));
    }
}
```

Compile and run:

```shell
javac StreamLesson179.java
java StreamLesson179
```

Expected output:

```text
Pipeline built
[20, 40]
[2, 4, 6]
2
[2, 3]
```

## 7. Common mistakes

| Mistake | Better approach |
|---|---|
| Expecting map to run when declared | A terminal operation initiates required processing. |
| Putting required effects in peek | Use explicit required-action handling. |
| Equating laziness with a cached snapshot | Source binding and reuse are separate contracts. |
| Assuming every short-circuit pipeline terminates | Check whether upstream can produce a result. |
| Ignoring resource opening before traversal | Close resource-backed sources deliberately. |

## 8. Practice exercises

1. Build a pipeline, print a message, then collect it. **Hint:** Separate construction from traversal.
2. Find the first matching integer from ordered input. **Hint:** Filter and findFirst.
3. Generate three even values from an iterate source. **Hint:** Filter then limit.
4. Explain why count can skip a mapping stage. **Hint:** The result may depend only on known size.
5. Compare ArrayList changes before and during traversal. **Hint:** Only the before-traversal example follows the simple non-interfering fixture here.

## 9. Interview questions and answers

### 1. Does lazy mean asynchronous?

No. It means work is deferred until demanded; ordinary sequential traversal runs synchronously.

### 2. Does a terminal operation guarantee every mapper executes?

No. Legal optimizations and short-circuiting can avoid work that is unnecessary for the result.

### 3. Does laziness eliminate resource-management duties?

No. A source can open resources before element traversal, and terminal completion does not automatically close it.

## Completion checklist

- [ ] I can distinguish construction, traversal, and source opening.
- [ ] I can explain short-circuiting and stateful barriers.
- [ ] I can avoid depending on observer side effects.
- [ ] I can distinguish laziness from caching, snapshotting, and concurrency.

## Official references

- [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html)
- [Stream package guide](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html)

---

[← 178. Parallel streams](178_Parallel_streams.md) | [Course Index](../README.md) | [180. Intermediate operations →](180_Intermediate_operations.md)
