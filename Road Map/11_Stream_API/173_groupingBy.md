# 173. groupingBy()

[Coding practice →](../../code_practice/11_Stream_API/173_groupingBy.md)

**Section:** Stream API

**Level:** Beginner → Advanced

**Version:** Runnable examples use Java 8-compatible APIs. Later additions are labeled explicitly.

## Learning goals

- Group values by a computed classification key.
- Select downstream lists, counts, sums, and nested groups.
- Define map order, null-key behavior, and parallel strategy.

**Example conventions:** Short snippets are independent and belong inside a method. Assume `java.util.*`, `java.util.function.*`, and `java.util.stream.*` imports unless shown otherwise. Inputs are non-null unless a snippet handles null explicitly. Numeric examples use small values that do not overflow.

## 1. Classification into groups

`Collectors.groupingBy(classifier)` produces a map from each observed key to a list of matching source elements. The classifier is a `Function`, not a predicate unless Boolean keys are actually intended.

```java
Map<Integer, List<String>> byLength = Stream.of("Ada", "Bob", "Grace", "Alan")
        .collect(Collectors.groupingBy(String::length));
System.out.println(byLength.get(3)); // [Ada, Bob]
System.out.println(byLength.get(5)); // [Grace]
```

Only observed classification keys are inserted. Empty input produces an empty map. Multiple elements with the same key belong together rather than causing a duplicate-key failure.

## 2. Downstream values

```java
List<String> names = Arrays.asList("Ada", "Bob", "Grace", "Alan");
Map<Integer, Long> counts = names.stream()
        .collect(Collectors.groupingBy(String::length, Collectors.counting()));
Map<Integer, Integer> totals = names.stream()
        .collect(Collectors.groupingBy(String::length,
                Collectors.summingInt(String::length)));
System.out.println(counts.get(3)); // 2
System.out.println(totals.get(3)); // 6
```

The downstream collector defines each map value's type. `groupingBy(department, summingInt(salary))` expresses a departmental total; `mapping(name, toList())` stores only names instead of complete objects.

## 3. Map factories and nested grouping

```java
Map<Integer, List<String>> ordered = Stream.of("Grace", "Ada", "Alan")
        .collect(Collectors.groupingBy(String::length, TreeMap::new,
                Collectors.toList()));
System.out.println(ordered.keySet()); // [3, 4, 5]
Map<Integer, Map<Character, Long>> nested = Stream.of("Ada", "Bob", "Alan")
        .collect(Collectors.groupingBy(String::length,
                Collectors.groupingBy(s -> s.charAt(0), Collectors.counting())));
System.out.println(nested.get(3).get('A')); // 1
```

Default map iteration order is unspecified. `TreeMap` provides comparator key order; `LinkedHashMap` can be useful for insertion-order requirements. Group list order and key iteration order are distinct concerns. Nested grouping yields nested map types, which should remain readable enough to justify the structure.

## 4. Nulls, equality, and parallel work

The standard `groupingBy` collector rejects a null classifier result. Normalize missing categories, for example to `"Unknown"`, or reject invalid data first. A null source element can fail inside the classifier before a key is returned.

Group keys use the resulting map's equality or comparator rules. Mutable key state can break later lookups, so prefer stable keys. Grouping is not automatically a deep copy: lists normally hold references to the original elements.

Ordinary `groupingBy` is not a concurrent collector; parallel reduction can create partial maps and merge them. `groupingByConcurrent` provides a concurrent, unordered alternative when its semantics fit. It does not promise encounter-ordered lists or make the resulting lists independently thread-safe for arbitrary later mutations.

## 5. Complete runnable example

Save as `StreamLesson173.java`.

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamLesson173 {
    public static void main(String[] args) throws Exception {
        List<String> names = Arrays.asList("Ada", "Bob", "Grace", "Alan");
        Map<Integer, List<String>> groups = names.stream().collect(
                Collectors.groupingBy(String::length, TreeMap::new, Collectors.toList()));
        System.out.println(groups.keySet());
        System.out.println(groups.get(3));
        Map<Integer, Long> counts = names.stream().collect(
                Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println(counts.get(3));
        System.out.println(counts.get(5));
        System.out.println(Stream.<String>empty().collect(
                Collectors.groupingBy(String::length)).isEmpty());
    }
}
```

Compile and run:

```shell
javac StreamLesson173.java
java StreamLesson173
```

Expected output:

```text
[3, 4, 5]
[Ada, Bob]
2
1
true
```

## 6. Common mistakes

| Mistake | Better approach |
|---|---|
| Assuming default key iteration order | Use explicit lookups or a map factory. |
| Returning a null classification key | Define a missing-category policy. |
| Using toMap without a merge when every element should be retained | Group into lists instead. |
| Assuming grouped objects are copied | Define object ownership or map to new immutable summaries. |
| Assuming concurrent grouping preserves encounter order | Choose unordered semantics deliberately. |

## 7. Practice exercises

1. Group words by their first letter. **Hint:** Guard empty words before charAt.
2. Count names per length. **Hint:** counting downstream.
3. Collect sorted group keys. **Hint:** TreeMap factory.
4. Group employees by department and then role. **Hint:** Nested groupingBy.
5. Map missing categories into an Unknown bucket. **Hint:** Return a non-null key.

## 8. Interview questions and answers

### 1. How does groupingBy differ from toMap?

Grouping partitions source elements by a key and reduces each group; toMap maps individual keys and values and needs a collision policy.

### 2. Are absent group keys represented by empty lists?

Not by ordinary groupingBy. Keys with no classified elements are absent unless explicitly added elsewhere.

### 3. Is groupingByConcurrent always better in parallel?

No. It changes ordering semantics, has its own contention and data-distribution costs, and must match the required result contract.

## Completion checklist

- [ ] I can follow classifier and downstream result types.
- [ ] I can choose map order and nested grouping.
- [ ] I can handle missing categories explicitly.
- [ ] I can distinguish grouped references from copies and concurrent results.

## Official references

- [Collectors API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Collectors.html)
- [Collector contract](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Collector.html)
- [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html)

---

[← 172. Collectors](172_Collectors.md) | [Course Index](../README.md) | [174. partitioningBy() →](174_partitioningBy.md)
