# 180. Intermediate operations

[Coding practice →](../../code_practice/11_Stream_API/180_Intermediate_operations.md)

**Section:** Stream API

**Level:** Beginner → Advanced

**Version:** Runnable examples use Java 8-compatible APIs. Later additions are labeled explicitly.

## Learning goals

- Classify intermediate operations by their contracts.
- Track element types, encounter order, and cross-element state.
- Compose stages without reusing or interfering with the pipeline.

**Example conventions:** Short snippets are independent and belong inside a method. Assume `java.util.*`, `java.util.function.*`, and `java.util.stream.*` imports unless shown otherwise. Inputs are non-null unless a snippet handles null explicitly. Numeric examples use small values that do not overflow.

## 1. Intermediate operations produce stream stages

An intermediate operation returns a stream and forms another stage in one pipeline. It does not produce a final collection or scalar result. Its element-processing work is lazy until a terminal operation demands the result.

```java
Stream<String> names = Stream.of(" Ada ", "Bob", "Grace")
        .map(String::trim).filter(s -> s.length() > 3);
System.out.println(names.collect(Collectors.toList())); // [Grace]
```

Do not treat a linked stage as an independent reusable branch. After linking or consuming a stream, build fresh pipelines for other calculations.

## 2. Stateless operations

| Stage | Purpose | Result shape |
|---|---|---|
| `filter` | Keep matching elements | Same element type, possibly fewer values |
| `map` | Transform each value | One reference result per input |
| `flatMap` | Expand and flatten | Zero-to-many reference results |
| `mapToInt` / `mapToLong` / `mapToDouble` | Create primitive values | A primitive stream |
| `peek` | Observe processing | Same values; not a required-effect hook |

Stateless means no cross-element history is needed by the operation's basic contract. A user callback can still violate the intended model by capturing changing shared state. Prefer non-interfering functions whose outputs depend on their inputs and stable configuration.

## 3. Stateful and short-circuiting stages

| Stage | Cross-element requirement | Short-circuiting bound? |
|---|---|---|
| `sorted` | Establish order over input | No |
| `distinct` | Track equality representatives | No |
| `skip` | Discard a specified prefix | Not by itself |
| `limit` | Retain at most a specified prefix | Yes |

Stateful does not mean eager: the stage still waits for terminal demand, but may buffer data or coordinate partitions during execution. `limit` can bound output, while a preceding global sort or impossible filter can prevent completion on unbounded input.

**Java 9+:** ordered-prefix `takeWhile` and `dropWhile`. **Java 16+:** `mapMulti` supports small zero-to-many expansions. Read their separate contracts rather than assuming they are identical to Java 8 filtering or flattening.

## 4. Order and type tracing

```java
List<Integer> lengths = Stream.of(" Ada ", "Grace", "Bob")
        .map(String::trim)                 // Stream<String>
        .filter(s -> s.length() > 3)       // Stream<String>
        .map(String::length)              // Stream<Integer>
        .collect(Collectors.toList());
System.out.println(lengths); // [5]
```

Reordering stages can change semantics. `filter(...).limit(2)` selects the first two matches; `limit(2).filter(...)` only searches the first two inputs. Sorting and deduplication use different comparator/equality rules. `unordered()` removes an encounter-order constraint without permuting elements; `parallel()` and `sequential()` select execution mode rather than dividing the pipeline into independent scheduled regions.

## 5. Safety and design boundaries

Validate nulls before dereferencing or unboxing them, and define whether missing values are removed, replaced, or rejected. Mapper and predicate exceptions propagate during traversal; invalid arguments such as negative skip/limit bounds can fail during pipeline construction.

Avoid source mutation and shared accumulation inside callbacks. Use `map` and `collect` for new data, `removeIf` for appropriate collection mutation, and explicit control flow for required side effects. Keep resource-backed pipelines inside their ownership scope.

Use streams when a sequence of selection, projection, and reduction steps is clearer than a loop. A loop can be preferable for complex state machines, several coordinated mutations, or precise early-exit handling that is awkward to express as a pipeline.

## 6. Complete runnable example

Save as `StreamLesson180.java`.

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamLesson180 {
    public static void main(String[] args) throws Exception {
        List<String> words = Arrays.asList(" ada ", "Bob", "ada", "Grace", "Alan");
        List<String> result = words.stream().map(String::trim)
                .map(s -> s.toUpperCase(Locale.ROOT)).filter(s -> s.length() > 3)
                .distinct().sorted().limit(2).collect(Collectors.toList());
        System.out.println(result);
        System.out.println(words);
        System.out.println(Stream.of("Ada", "Grace").mapToInt(String::length).sum());
        System.out.println(Stream.of(1, 2, 3, 4).filter(n -> n % 2 == 0).limit(1)
                .collect(Collectors.toList()));
    }
}
```

Compile and run:

```shell
javac StreamLesson180.java
java StreamLesson180
```

Expected output:

```text
[ALAN, GRACE]
[ ada , Bob, ada, Grace, Alan]
8
[2]
```

## 7. Common mistakes

| Mistake | Better approach |
|---|---|
| Expecting an intermediate stage to produce results immediately | Add a terminal operation. |
| Reusing the same stream as independent branches | Start fresh pipelines from reusable data. |
| Confusing stateful with eager | Stateful stages are still lazy but may need buffering during traversal. |
| Reordering stages only for imagined speed | Preserve meaning before considering performance. |
| Capturing mutable shared state in callbacks | Prefer stateless non-interfering behavior. |

## 8. Practice exercises

1. Annotate the element type after every stage of a string-length pipeline. **Hint:** map can change type; filter does not.
2. Classify filter, sorted, distinct, skip, and limit. **Hint:** Separate stateless, stateful, and short-circuiting properties.
3. Compare filter/limit order on [1, 2, 3, 4]. **Hint:** Choose an even-number predicate.
4. Flatten two groups, deduplicate, and sort. **Hint:** flatMap then distinct then sorted.
5. Replace a side-effecting mapper with an explicit result reduction. **Hint:** Return new values and collect them.

## 9. Interview questions and answers

### 1. What makes an operation intermediate?

It returns another stream stage rather than a final result and defers element processing until terminal demand.

### 2. Can a stateless operation have an unsafe callback?

Yes. The operation's category does not make captured user state immutable or thread-safe.

### 3. Why can operation order affect correctness?

Selection, transformation, prefix slicing, sorting, and deduplication operate on different values or candidate sets depending on their position.

## Completion checklist

- [ ] I can classify and compose stream stages.
- [ ] I can follow type changes and encounter order.
- [ ] I can distinguish laziness from stateful buffering.
- [ ] I can choose streams or loops based on the actual control-flow need.

## Official references

- [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html)
- [Stream package guide](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html)

---

[← 179. Lazy evaluation](179_Lazy_evaluation.md) | [Course Index](../README.md) | [181. Terminal operations →](181_Terminal_operations.md)
