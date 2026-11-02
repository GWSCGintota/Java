[← 166. sorted()](166_sorted.md) | [Course Index](../README.md) | [168. limit() →](168_limit.md)

# Java distinct() Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/11_Stream_API/167_distinct.md)

These exercises follow the corresponding Road Map lesson, progressing from basic operations to composition, boundary cases, and safe execution. Each coding question includes a model solution, expected output, and concept explanation.

**Java version:** Java 8+. Short snippets are independent and belong inside `main()`; assume imports from `java.util.*`, `java.util.function.*`, and `java.util.stream.*`. Complete programs include their helper types and should be saved as `Main.java`. Run questions separately. Inputs are non-null unless explicitly handled, and arithmetic uses small values that do not overflow. For checked I/O, `main()` must declare `throws Exception` or handle the failure.

## Question 1 — Encounter-order uniqueness

**Task:** Remove duplicates from [3, 1, 3, 2, 1], retaining first encounter order.

```java
System.out.println(Stream.of(3, 1, 3, 2, 1).distinct().collect(Collectors.toList()));
```

**Output:**
```text
[3, 1, 2]
```

**Concept:** Ordered distinct retains first encountered equality representatives.

## Question 2 — Sorted unique values

**Task:** Return sorted unique values from [3, 1, 3, 2].

```java
System.out.println(Stream.of(3, 1, 3, 2).distinct().sorted().collect(Collectors.toList()));
```

**Output:**
```text
[1, 2, 3]
```

**Concept:** Deduplication and sorting are separate operations.

## Question 3 — Normalize before distinct

**Task:** Deduplicate [" Ada ", "ada", "Grace"] after trimming and lowercase normalization.

```java
System.out.println(Stream.of(" Ada ", "ada", "Grace")
        .map(String::trim)
        .map(s -> s.toLowerCase(Locale.ROOT))
        .distinct()
        .collect(Collectors.toList()));
```

**Output:**
```text
[ada, grace]
```

**Concept:** The normalized representation defines equality for this requirement.

## Question 4 — Comparator equality is not equals

**Task:** Sort [Ada, ADA] case-insensitively, then apply distinct.

```java
System.out.println(Stream.of("Ada", "ADA")
        .sorted(String.CASE_INSENSITIVE_ORDER)
        .distinct()
        .collect(Collectors.toList()));
```

**Output:**
```text
[Ada, ADA]
```

**Concept:** A sorting comparator does not replace String.equals used by distinct.

## Question 5 — Null duplicates

**Task:** Deduplicate [null, Ada, null, Ada].

```java
System.out.println(Arrays.asList(null, "Ada", null, "Ada").stream().distinct().collect(Collectors.toList()));
```

**Output:**
```text
[null, Ada]
```

**Concept:** One null representative can remain; null handling downstream is a separate concern.

## Question 6 — Custom value equality

**Task:** Deduplicate points (1,2), (1,2), and (2,3) using compatible equals and hashCode.

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Main {
    static final class Point {
        final int x, y;
        Point(int x, int y) { this.x=x; this.y=y; }
        @Override public boolean equals(Object other) {
            if (!(other instanceof Point)) { return false; }
            Point point=(Point)other;
            return x==point.x && y==point.y;
        }
        @Override public int hashCode() { return Objects.hash(x, y); }
        @Override public String toString() { return x + ":" + y; }
    }
    public static void main(String[] args) throws Exception {
        System.out.println(Stream.of(new Point(1, 2), new Point(1, 2), new Point(2, 3))
                .distinct()
                .collect(Collectors.toList()));
    }
}
```

**Output:**
```text
[1:2, 2:3]
```

**Concept:** Custom value uniqueness depends on a correct equality and hash contract.

## Question 7 — Explicit ordered set result

**Task:** Build a LinkedHashSet from [3, 1, 3, 2].

```java
Set<Integer> result = Stream.of(3, 1, 3, 2).collect(Collectors.toCollection(LinkedHashSet::new));
System.out.println(result);
```

**Output:**
```text
[3, 1, 2]
```

**Concept:** A collector can return an explicitly ordered unique container.

## Question 8 — Distinct property projection

**Task:** From [Ada, Bob, Grace, Alan], return unique name lengths.

```java
System.out.println(Stream.of("Ada", "Bob", "Grace", "Alan")
        .map(String::length)
        .distinct()
        .collect(Collectors.toList()));
```

**Output:**
```text
[3, 5, 4]
```

**Concept:** Mapping to a key changes the values on which equality is evaluated.

## Question 9 — Empty and singleton uniqueness

**Task:** Count distinct values in an empty stream and in [7, 7, 7].

```java
System.out.println(Stream.empty().distinct().count());
System.out.println(Stream.of(7, 7, 7).distinct().count());
```

**Output:**
```text
0
1
```

**Concept:** Distinct has ordinary empty-input behavior and counts equality classes.

## Question 10 — Merge duplicate keys explicitly

**Task:** For [Ada, Alan, Bob, Alice], keep the longer name per initial; print A and B lookups.

```java
Map<Character, String> byInitial = Stream.of("Ada", "Alan", "Bob", "Alice")
        .collect(Collectors.toMap(s -> s.charAt(0), Function.identity(), (a, b) -> a.length() >= b.length() ? a : b));
System.out.println(byInitial.get('A'));
System.out.println(byInitial.get('B'));
```

**Output:**
```text
Alice
Bob
```

**Concept:** Key-based object retention needs an explicit collector merge rule rather than a distinct key-extractor overload.

---

## Interview Questions and Answers

### Interview Question 1 — Which duplicate is retained in an ordered stream?

**Answer:** The first encountered representative according to equals, with stable encounter-order behavior.

### Interview Question 2 — Can distinct use an arbitrary key extractor?

**Answer:** Not directly. Map to keys, collect by key with a merge policy, or group values depending on the desired result.

### Interview Question 3 — Why can ordered parallel distinct be costly?

**Answer:** Preserving the first encountered representative across partitions can require coordination and buffering.

### Interview Question 4 — Can equality-relevant fields be changed during distinct?

**Answer:** Avoid it. Equality and hashes need to remain stable while uniqueness is being tracked, particularly for hash-based processing.

### Interview Question 5 — Does unordered() randomly shuffle values?

**Answer:** No. It removes an encounter-order requirement and permits different valid execution choices; it is not a shuffle operation.

---

## Key Takeaways

| Skill | What to remember |
|---|---|
| Encounter-order uniqueness | Ordered distinct retains first encountered equality representatives. |
| Sorted unique values | Deduplication and sorting are separate operations. |
| Normalize before distinct | The normalized representation defines equality for this requirement. |
| Comparator equality is not equals | A sorting comparator does not replace String.equals used by distinct. |
| Null duplicates | One null representative can remain; null handling downstream is a separate concern. |

[← Road Map lesson](../../Road%20Map/11_Stream_API/167_distinct.md)

[← 166. sorted()](166_sorted.md) | [Course Index](../README.md) | [168. limit() →](168_limit.md)
