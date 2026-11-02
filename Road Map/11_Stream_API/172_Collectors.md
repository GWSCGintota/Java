# 172. Collectors

[Coding practice →](../../code_practice/11_Stream_API/172_Collectors.md)

**Section:** Stream API

**Level:** Beginner → Advanced

**Version:** Runnable examples use Java 8-compatible APIs. Later additions are labeled explicitly.

## Learning goals

- Select standard collectors by result shape.
- Compose downstream collectors to summarize grouped data.
- Understand collector characteristics without assuming thread safety or order.

**Example conventions:** Short snippets are independent and belong inside a method. Assume `java.util.*`, `java.util.function.*`, and `java.util.stream.*` imports unless shown otherwise. Inputs are non-null unless a snippet handles null explicitly. Numeric examples use small values that do not overflow.

## 1. A library of reduction recipes

`Collectors` supplies factory methods returning `Collector` objects. A collector is a reusable recipe for reduction, not the collected data itself. Calling `Collectors.toList()` does not traverse any source; passing it to `stream.collect(...)` initiates a terminal reduction.

| Required result | Collector |
|---|---|
| List | `toList()` or `toCollection(...)` |
| Unique set | `toSet()` or explicit set factory |
| Key/value map | `toMap(keyMapper, valueMapper, merge)` |
| String | `joining(delimiter)` |
| Groups | `groupingBy(classifier, downstream)` |
| Boolean partitions | `partitioningBy(predicate, downstream)` |
| Count or statistics | `counting`, `summingInt`, `averagingInt`, `summarizingInt` |
| Adapted downstream input | `mapping(mapper, downstream)` |
| Finished result adaptation | `collectingAndThen(downstream, finisher)` |

## 2. Numeric summaries

```java
IntSummaryStatistics stats = Stream.of("Ada", "Grace", "Alan")
        .collect(Collectors.summarizingInt(String::length));
System.out.println(stats.getCount()); // 3
System.out.println(stats.getSum());   // 12
System.out.println(stats.getAverage()); // 4.0
```

Summaries collect several related aggregates in one pass. Empty numeric collectors have defined identities: `counting` and integer sums return zero; averages return zero for empty input. Summary minimum and maximum use documented sentinel values on empty input, so check the count before interpreting them as observed data. Choose numeric widths and overflow policies deliberately.

## 3. Downstream composition

```java
Map<Integer, Long> counts = Stream.of("Ada", "Bob", "Grace")
        .collect(Collectors.groupingBy(String::length, Collectors.counting()));
System.out.println(counts.get(3)); // 2
System.out.println(counts.get(5)); // 1
Map<Integer, String> names = Stream.of("Ada", "Bob", "Grace")
        .collect(Collectors.groupingBy(String::length,
                Collectors.mapping(s -> s.toUpperCase(Locale.ROOT),
                        Collectors.joining(", "))));
System.out.println(names.get(3)); // ADA, BOB
```

The outer collector chooses the group. The downstream collector determines what each group stores: a list, count, sum, joined text, or another map. `mapping` transforms values within this reduction without discarding the original input before classification.

## 4. Container and failure contracts

Default collection and map collectors do not guarantee a specific mutable implementation or thread safety. Request a container factory when order or implementation matters. `toMap` needs a deliberate duplicate-key rule; grouping normally collects all elements under each key instead of selecting one value.

Callback exceptions propagate. Do not assume collectors universally support null elements, null keys, or null mapped values: each collector has its own contract. Default `groupingBy` rejects null classification keys, so classify missing data into a deliberate category or reject it earlier.

## 5. Characteristics and release boundaries

A custom collector describes supplier, accumulator, combiner, finisher, and characteristics. `CONCURRENT` permits concurrent accumulation under the collector's contract; `UNORDERED` means encounter order is not part of the result requirement; `IDENTITY_FINISH` indicates that a separate finishing conversion can be omitted.

These flags must describe real behavior. They do not transform unsafe code into safe code. Standard collectors are preferable when they fit.

**Java 9+:** downstream `filtering` and `flatMapping`. **Java 10+:** unmodifiable collection collectors. **Java 12+:** `teeing` combines two reductions with a merger. These are separate additions, not Java 8 APIs. Use a compatible target JDK before incorporating them.

## 6. Complete runnable example

Save as `StreamLesson172.java`.

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamLesson172 {
    public static void main(String[] args) throws Exception {
        List<String> names = Arrays.asList("Ada", "Bob", "Grace");
        IntSummaryStatistics stats = names.stream().collect(Collectors.summarizingInt(String::length));
        System.out.println(stats.getCount());
        System.out.println(stats.getSum());
        Map<Integer, Long> counts = names.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println(counts.get(3));
        System.out.println(counts.get(5));
        System.out.println(names.stream().collect(Collectors.joining(" | ")));
    }
}
```

Compile and run:

```shell
javac StreamLesson172.java
java StreamLesson172
```

Expected output:

```text
3
11
2
1
Ada | Bob | Grace
```

## 7. Common mistakes

| Mistake | Better approach |
|---|---|
| Treating a collector as a container | It is a recipe; collect produces the result. |
| Assuming all collector results are mutable | Read the selected collector's contract. |
| Printing unordered maps as if their order were fixed | Print known lookups or choose a suitable map factory. |
| Marking a custom collector CONCURRENT without a safe accumulator | Characteristics must match the implementation. |
| Using later collector APIs on Java 8 | Check each method's introduction version. |

## 8. Practice exercises

1. Collect count, sum, minimum, and maximum string lengths. **Hint:** summarizingInt and a count check for empty input.
2. Group names by length and count them. **Hint:** groupingBy plus counting.
3. Group by length and join uppercase names. **Hint:** mapping plus joining downstream.
4. Choose a deterministic map key order. **Hint:** Supply TreeMap::new.
5. Compare a map merge with grouping lists. **Hint:** One selected value per key versus all grouped elements.

## 9. Interview questions and answers

### 1. What is a downstream collector?

A reduction used within another collector, such as counting the elements assigned to each grouping key.

### 2. Does using a collector imply concurrent accumulation?

No. Most ordinary collectors use isolated containers and merging; concurrent collectors have explicit contracts.

### 3. What is the benefit of summarizing collectors?

They accumulate related numeric statistics in one reduction with well-defined empty-input behavior.

## Completion checklist

- [ ] I can choose collectors by result shape.
- [ ] I can compose grouping, mapping, and aggregation.
- [ ] I can specify container and failure policies.
- [ ] I can recognize characteristics and version boundaries.

## Official references

- [Collectors API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Collectors.html)
- [Collector contract](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Collector.html)
- [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html)

---

[← 171. collect()](171_collect.md) | [Course Index](../README.md) | [173. groupingBy() →](173_groupingBy.md)
