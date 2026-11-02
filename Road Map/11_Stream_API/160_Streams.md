# 160. Streams

[Coding practice →](../../code_practice/11_Stream_API/160_Streams.md)

**Section:** Stream API

**Level:** Beginner → Advanced

**Version:** Runnable examples use Java 8-compatible APIs. Later additions are labeled explicitly.

## Learning goals

- Explain a stream pipeline and distinguish streams from collections and I/O streams.
- Choose intermediate and terminal operations for a data-processing task.
- Use single-use streams safely, including primitive streams and resource-backed sources.

**Example conventions:** Short snippets are independent and belong inside a method. Assume `java.util.*`, `java.util.function.*`, and `java.util.stream.*` imports unless shown otherwise. Inputs are non-null unless a snippet handles null explicitly. Numeric examples use small values that do not overflow.

## 1. What is a stream?

A `Stream<T>` describes a sequence of values processed through a pipeline. It is an execution abstraction, not another collection storing a permanent copy of the data. A pipeline has a source, zero or more intermediate operations, and one terminal operation.

```text
Source → filter → map → terminal result
List<Integer> → selected integers → transformed values → List / sum / action
```

Collection streams do not automatically run on background threads. They are sequential unless a parallel mode is requested. `java.util.stream.Stream` is different from byte-oriented `InputStream` and `OutputStream` in `java.io`.

## 2. A first pipeline

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
List<Integer> squares = numbers.stream()
        .filter(n -> n % 2 == 0)
        .map(n -> n * n)
        .collect(Collectors.toList());
System.out.println(squares); // [4, 16, 36]
System.out.println(numbers); // [1, 2, 3, 4, 5, 6]
```

`filter` selects values; `map` transforms retained values; `collect` produces the result. These operations do not replace elements in `numbers`. A callback that mutates an element could still change that object, so a stream is not an immutability guarantee.

## 3. Intermediate and terminal operations

| Category | Examples | Result |
|---|---|---|
| Stateless intermediate | `filter`, `map`, `flatMap` | Another stream stage |
| Stateful intermediate | `sorted`, `distinct`, `skip`, `limit` | A stage that needs cross-element information |
| Terminal aggregation | `count`, `reduce`, `collect`, primitive `sum` | A value or container |
| Short-circuiting terminal | `findFirst`, `anyMatch`, `allMatch` | A result that may need only part of the input |
| Terminal action | `forEach`, `forEachOrdered` | `void` |

Intermediate work is lazy. A terminal operation initiates processing, but implementations can avoid work that cannot affect its result. Do not place required actions in `map`, `filter`, or `peek`.

## 4. Primitive streams

Use `IntStream`, `LongStream`, and `DoubleStream` for the supported primitive shapes. They expose operations such as `sum`, `average`, and `summaryStatistics` without boxing each result.

```java
int total = Arrays.asList("Ada", "Grace").stream()
        .mapToInt(String::length)
        .sum();
System.out.println(total); // 8
System.out.println(IntStream.empty().average().isPresent()); // false
```

`average` returns an optional primitive result because empty input has no arithmetic mean. `boxed()` adapts a primitive stream to wrapper objects.

## 5. Single use and resource ownership

A stream and its linked stages must not be reused. Create a new stream from the source for another calculation; do not keep one consumed stream as reusable data. Implementations can reject detected reuse with `IllegalStateException`.

```java
List<Integer> values = Arrays.asList(1, 2, 3);
System.out.println(values.stream().count());        // 3
System.out.println(values.stream().mapToInt(n -> n).sum()); // 6
```

For an I/O-backed stream such as `Files.lines(path)`, use try-with-resources. A terminal operation does not automatically close that source. Most in-memory collection streams do not own a resource requiring closure.

## 6. Behavior contracts and order

Callbacks should be non-interfering: avoid modifying the source during traversal. Prefer stateless functions that do not depend on changing shared state. Encounter order comes from the source and pipeline: a list has a defined order, while an ordinary `HashSet` does not provide a stable encounter-order contract.

Parallel execution is an optional execution mode, not an automatic speed improvement. Associative reductions and correct collector designs allow partitioned computation. See [178. Parallel streams](178_Parallel_streams.md).

## 7. Complete runnable example

Save as `StreamLesson160.java`.

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamLesson160 {
    public static void main(String[] args) throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> squares = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println(squares);
        System.out.println(numbers);
        System.out.println(numbers.stream().mapToInt(Integer::intValue).sum());
        System.out.println(Stream.<String>empty().count());
    }
}
```

Compile and run:

```shell
javac StreamLesson160.java
java StreamLesson160
```

Expected output:

```text
[4, 16, 36]
[1, 2, 3, 4, 5, 6]
21
0
```

## 8. Common mistakes

| Mistake | Better approach |
|---|---|
| Treating a stream as reusable storage | Store the source or result and request a fresh stream for each pipeline. |
| Forgetting a terminal operation | End the pipeline with an operation that produces the required result. |
| Modifying the traversed source | Use a separate result or a dedicated collection operation. |
| Using shared mutable totals | Prefer sum, reduce, or collect. |
| Assuming streams always outperform loops | Choose clarity first and measure relevant workloads. |

## 9. Practice exercises

1. Filter positive numbers and double them. **Hint:** Combine filter, map, and collect.
2. Find the sum of name lengths. **Hint:** mapToInt followed by sum.
3. Compare an empty stream's count and average. **Hint:** Zero versus an empty optional.
4. Run two calculations from one list without reusing a stream. **Hint:** Call stream twice.
5. Explain which stages need a terminal operation. **Hint:** All intermediate stages are lazy.

## 10. Interview questions and answers

### 1. Does a stream store its own elements?

It conveys values from a source through operations; the source may store data, generate it, or read it from I/O.

### 2. Does a pipeline necessarily examine every element?

No. Short-circuiting and legal optimizations can avoid unnecessary processing.

### 3. Are streams immutable or thread-safe by definition?

No. Callback state, source interference, mutable elements, and execution mode all need deliberate handling.

## Completion checklist

- [ ] I can identify source, intermediate stages, and terminal operation.
- [ ] I can distinguish selection, transformation, aggregation, and actions.
- [ ] I can use primitive streams and handle empty results.
- [ ] I can explain single use, ordering, and resource ownership.

## Official references

- [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html)
- [Stream package guide](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html)

---

[← 159. Higher-order style programming](../10_Functional_Programming/159_Higher-order_style_programming.md) | [Course Index](../README.md) | [161. Creating streams →](161_Creating_streams.md)
