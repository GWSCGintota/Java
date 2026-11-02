# 175. mapping()

[Coding practice →](../../code_practice/11_Stream_API/175_mapping.md)

**Section:** Stream API

**Level:** Beginner → Advanced

**Version:** Runnable examples use Java 8-compatible APIs. Later additions are labeled explicitly.

## Learning goals

- Adapt collector inputs with Collectors.mapping.
- Preserve original grouping keys while reducing transformed values.
- Distinguish upstream map from downstream mapping and its empty/null behavior.

**Example conventions:** Short snippets are independent and belong inside a method. Assume `java.util.*`, `java.util.function.*`, and `java.util.stream.*` imports unless shown otherwise. Inputs are non-null unless a snippet handles null explicitly. Numeric examples use small values that do not overflow.

## 1. A downstream input adapter

`Collectors.mapping(mapper, downstream)` returns a collector that transforms each input before passing it to another collector. It is a collector factory, not a stream intermediate operation.

```java
Map<Integer, List<String>> byLength = Stream.of("Ada", "Bob", "Grace")
        .collect(Collectors.groupingBy(String::length,
                Collectors.mapping(name -> name.toUpperCase(Locale.ROOT),
                        Collectors.toList())));
System.out.println(byLength.get(3)); // [ADA, BOB]
```

The outer classifier sees each original name. Only the value delivered to the downstream list is changed. This is especially useful when objects should be grouped by one property while their group values expose another property.

## 2. Map versus mapping

```java
List<Integer> lengths = Stream.of("Ada", "Grace")
        .collect(Collectors.mapping(String::length, Collectors.toList()));
System.out.println(lengths); // [3, 5]
List<Integer> same = Stream.of("Ada", "Grace")
        .map(String::length).collect(Collectors.toList());
System.out.println(same); // [3, 5]
```

At the outermost reduction these can express the same result. Inside `groupingBy`, however, moving the transformation upstream changes what the classifier receives. A useful rule is: classify original elements, then adapt the representation stored in each group. Both mapper and downstream collector references must be valid, and mapper exceptions propagate.

## 3. Sets, joined strings, and partitions

```java
Map<Integer, Set<String>> unique = Stream.of("Ada", "Ada", "Bob")
        .collect(Collectors.groupingBy(String::length,
                Collectors.mapping(Function.identity(),
                        Collectors.toCollection(LinkedHashSet::new))));
System.out.println(unique.get(3)); // [Ada, Bob]
Map<Boolean, String> joined = Stream.of("Ada", "Grace", "Alan")
        .collect(Collectors.partitioningBy(s -> s.length() > 3,
                Collectors.mapping(s -> s.toUpperCase(Locale.ROOT),
                        Collectors.joining(", "))));
System.out.println(joined.get(true)); // GRACE, ALAN
System.out.println(joined.get(false)); // ADA
```

The downstream collector decides uniqueness, container type, order, and final formatting. Mapping itself does not filter, deduplicate, or flatten.

## 4. Empty, null, and later downstream adapters

For empty input, mapping delegates to the downstream collector's empty reduction. A mapper returning null passes that null to the downstream collector; whether it is accepted depends on that collector. Mapping to a count still counts mapped inputs, including null results when counting is downstream; it does not mean counting only present values.

**Java 9+:** downstream `filtering` can retain an observed group with a zero-element downstream result, unlike filtering every element out before grouping. `flatMapping` adapts zero-to-many stream outputs per input. Both are distinct from Java 8 `mapping`.

Avoid capturing one mutable result container in the mapper. Let the collector manage accumulation, and use fresh immutable projection objects when the result should not expose original mutable entities.

## 5. Complete runnable example

Save as `StreamLesson175.java`.

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamLesson175 {
    public static void main(String[] args) throws Exception {
        List<String> names = Arrays.asList("Ada", "Bob", "Grace", "Alan");
        Map<Integer, List<String>> upper = names.stream().collect(
                Collectors.groupingBy(String::length,
                        Collectors.mapping(s -> s.toUpperCase(Locale.ROOT), Collectors.toList())));
        System.out.println(upper.get(3));
        System.out.println(upper.get(5));
        Map<Boolean, String> joined = names.stream().collect(
                Collectors.partitioningBy(s -> s.length() > 3,
                        Collectors.mapping(s -> s.toUpperCase(Locale.ROOT), Collectors.joining(", "))));
        System.out.println(joined.get(true));
        System.out.println(joined.get(false));
    }
}
```

Compile and run:

```shell
javac StreamLesson175.java
java StreamLesson175
```

Expected output:

```text
[ADA, BOB]
[GRACE]
GRACE, ALAN
ADA, BOB
```

## 6. Common mistakes

| Mistake | Better approach |
|---|---|
| Calling mapping directly as a stream method | Use map on streams or Collectors.mapping inside a reduction. |
| Moving projection before classification without checking types | Preserve the original input where the classifier needs it. |
| Expecting mapping to flatten nested lists | Choose flatMap or a later-release flatMapping collector. |
| Assuming a null mapping result is discarded | Define downstream null handling explicitly. |
| Mixing projection with external accumulation | Let the downstream collector own the result container. |

## 7. Practice exercises

1. Group names by length and store uppercase strings. **Hint:** groupingBy then mapping toList.
2. Group employees by department and store unique names. **Hint:** mapping a name getter to a set collector.
3. Partition names by length and join each side. **Hint:** mapping with joining downstream.
4. Compare top-level map and mapping for lengths. **Hint:** They can produce equivalent lists here.
5. Predict mapping to null followed by counting. **Hint:** Counting counts inputs, not non-null field values.

## 8. Interview questions and answers

### 1. What does mapping adapt?

The input delivered to a downstream collector while retaining the surrounding collector's original input contract.

### 2. Why use mapping inside groupingBy?

It lets classification use original objects while group values store a selected or transformed representation.

### 3. Does mapping skip null results?

No. It passes mapper results downstream, where acceptance and behavior depend on that collector.

## Completion checklist

- [ ] I can distinguish stream map from collector mapping.
- [ ] I can preserve classification while projecting values.
- [ ] I can compose list, set, and string reductions.
- [ ] I can predict empty/null behavior and later API differences.

## Official references

- [Collectors API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Collectors.html)
- [Collector contract](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Collector.html)
- [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html)

---

[← 174. partitioningBy()](174_partitioningBy.md) | [Course Index](../README.md) | [176. counting() →](176_counting.md)
