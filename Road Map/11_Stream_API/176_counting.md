# 176. counting()

[Coding practice →](../../code_practice/11_Stream_API/176_counting.md)

**Section:** Stream API

**Level:** Beginner → Advanced

**Version:** Runnable examples use Java 8-compatible APIs. Later additions are labeled explicitly.

## Learning goals

- Use counting as a terminal reduction recipe and downstream aggregate.
- Distinguish element counts from unique and non-null-value counts.
- Predict empty groups, partitions, and numeric result types.

**Example conventions:** Short snippets are independent and belong inside a method. Assume `java.util.*`, `java.util.function.*`, and `java.util.stream.*` imports unless shown otherwise. Inputs are non-null unless a snippet handles null explicitly. Numeric examples use small values that do not overflow.

## 1. Count elements, not numeric values

`Collectors.counting()` reduces the number of input elements to a `Long`. It does not sum the numeric values of those elements. Empty input returns zero.

```java
Long count = Stream.of(10, 20, 30).collect(Collectors.counting());
System.out.println(count); // 3
long direct = Stream.of(10, 20, 30).count();
System.out.println(direct); // 3
System.out.println(Stream.empty().collect(Collectors.counting())); // 0
```

For a top-level count, `Stream.count()` is usually more direct and returns primitive `long`. `counting()` is especially valuable as a downstream collector where a complete collector recipe is required.

## 2. Count per group and partition

```java
Map<Integer, Long> byLength = Stream.of("Ada", "Bob", "Grace", "Alan")
        .collect(Collectors.groupingBy(String::length, Collectors.counting()));
System.out.println(byLength.get(3)); // 2
Map<Boolean, Long> parity = Stream.of(1, 2, 3, 4)
        .collect(Collectors.partitioningBy(n -> n % 2 == 0,
                Collectors.counting()));
System.out.println(parity.get(true));  // 2
System.out.println(parity.get(false)); // 2
```

Group counts describe only observed keys for ordinary grouping. Partition counts include both Boolean keys, including zero counts on empty input. The value type is `Long`, so use `Map<Key, Long>` rather than `Map<Key, Integer>`.

## 3. Occurrences, uniqueness, and nulls

```java
List<String> words = Arrays.asList("Java", "Java", "Ada", null);
System.out.println(words.stream().collect(Collectors.counting())); // 4
System.out.println(words.stream().filter(Objects::nonNull).count()); // 3
System.out.println(words.stream().filter(Objects::nonNull).distinct().count()); // 2
```

Counting includes occurrences and does not inspect field values. Null elements can be counted directly, but a preceding classifier or mapper may reject them. To count distinct values per group, collect a set and finish with its size; that produces an `Integer` size unless another explicit conversion is chosen.

## 4. Frequency tables and interpretation

```java
Map<String, Long> frequencies = Stream.of("java", "lambda", "java")
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
System.out.println(frequencies.get("java")); // 2
System.out.println(frequencies.getOrDefault("stream", 0L)); // 0
```

`getOrDefault` expresses a missing-group display policy rather than adding an entry. SQL `COUNT(column)` usually excludes null column values, so do not assume it has the same meaning as counting stream elements; apply the matching selection rule.

Avoid implementing totals with a captured mutable counter in `forEach`. A reduction expresses the result directly and works with parallel partition/combiner rules. A top-level `count` may derive its answer from source size and legal optimizations without running every intervening mapper or observer; required effects must not depend on such traversal.

## 5. Complete runnable example

Save as `StreamLesson176.java`.

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamLesson176 {
    public static void main(String[] args) throws Exception {
        List<String> names = Arrays.asList("Ada", "Bob", "Grace", "Ada");
        System.out.println(names.stream().collect(Collectors.counting()));
        System.out.println(names.stream().distinct().count());
        Map<Integer, Long> counts = names.stream().collect(
                Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println(counts.get(3));
        System.out.println(counts.get(5));
        System.out.println(counts.getOrDefault(4, 0L));
        System.out.println(Stream.empty().collect(Collectors.counting()));
    }
}
```

Compile and run:

```shell
javac StreamLesson176.java
java StreamLesson176
```

Expected output:

```text
4
3
3
1
0
0
```

## 6. Common mistakes

| Mistake | Better approach |
|---|---|
| Declaring group counts as Integer values | counting produces Long. |
| Confusing count with sum | Count elements or use an explicit numeric summing collector. |
| Assuming duplicates are ignored | Apply distinct or a set-based downstream reduction. |
| Assuming counting ignores null fields | Filter the field policy explicitly. |
| Expecting absent grouping keys to have zero entries | Use getOrDefault or a predefined category initialization. |

## 7. Practice exercises

1. Build a word-frequency map. **Hint:** groupingBy(identity, counting).
2. Count only non-null values. **Hint:** Filter first.
3. Count unique names after normalization. **Hint:** Normalize then distinct then count.
4. Return zero for a missing category without modifying the map. **Hint:** getOrDefault(key, 0L).
5. Compare empty grouped counts with empty partition counts. **Hint:** Missing keys versus two zero-valued keys.

## 8. Interview questions and answers

### 1. What type does counting return?

Its collected result is Long; Stream.count returns primitive long.

### 2. Does counting remove duplicates or nulls?

No. It counts the elements reaching it; upstream rules determine which elements arrive.

### 3. Why use counting instead of count?

It composes as a downstream collector inside grouping, partitioning, or another collector adapter.

## Completion checklist

- [ ] I can distinguish counts, sums, and unique counts.
- [ ] I can use Long group-value types.
- [ ] I can explain missing groups versus zero partitions.
- [ ] I can avoid side-effect-based counters.

## Official references

- [Collectors API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Collectors.html)
- [Collector contract](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Collector.html)
- [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html)

---

[← 175. mapping()](175_mapping.md) | [Course Index](../README.md) | [177. joining() →](177_joining.md)
