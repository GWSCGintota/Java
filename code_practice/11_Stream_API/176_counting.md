[← 175. mapping()](175_mapping.md) | [Course Index](../README.md) | [177. joining() →](177_joining.md)

# Java counting() Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/11_Stream_API/176_counting.md)

These exercises follow the corresponding Road Map lesson, progressing from basic operations to composition, boundary cases, and safe execution. Each coding question includes a model solution, expected output, and concept explanation.

**Java version:** Java 8+. Short snippets are independent and belong inside `main()`; assume imports from `java.util.*`, `java.util.function.*`, and `java.util.stream.*`. Complete programs include their helper types and should be saved as `Main.java`. Run questions separately. Inputs are non-null unless explicitly handled, and arithmetic uses small values that do not overflow. For checked I/O, `main()` must declare `throws Exception` or handle the failure.

## Question 1 — Count occurrences

**Task:** Count the elements of [10, 20, 30] using Collectors.counting.

```java
Long count = Stream.of(10, 20, 30).collect(Collectors.counting());
System.out.println(count);
```

**Output:**
```text
3
```

**Concept:** counting counts elements rather than summing their numeric values.

## Question 2 — Primitive versus boxed count

**Task:** Count Ada and Grace with Stream.count and Collectors.counting; print the values and collector result type.

```java
long direct = Stream.of("Ada", "Grace").count();
Long collected = Stream.of("Ada", "Grace").collect(Collectors.counting());
System.out.println(direct);
System.out.println(collected);
System.out.println(collected.getClass().getSimpleName());
```

**Output:**
```text
2
2
Long
```

**Concept:** Stream.count returns primitive long; counting collects a Long.

## Question 3 — Empty count

**Task:** Count an empty string stream using the counting collector.

```java
System.out.println(Stream.<String>empty().collect(Collectors.counting()));
```

**Output:**
```text
0
```

**Concept:** The empty counting reduction has identity zero.

## Question 4 — Counts by length

**Task:** Count [Ada, Bob, Grace, Ada] by name length and print counts for three and five.

```java
Map<Integer, Long> counts = Stream.of("Ada", "Bob", "Grace", "Ada")
        .collect(Collectors.groupingBy(String::length, Collectors.counting()));
System.out.println(counts.get(3));
System.out.println(counts.get(5));
```

**Output:**
```text
3
1
```

**Concept:** counting composes as a per-group downstream reduction.

## Question 5 — Word-frequency table

**Task:** Build frequencies for [java, lambda, java, stream, java] and print java and lambda counts.

```java
Map<String, Long> counts = Stream.of("java", "lambda", "java", "stream", "java")
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
System.out.println(counts.get("java"));
System.out.println(counts.get("lambda"));
```

**Output:**
```text
3
1
```

**Concept:** Identity classification groups equal words before counting occurrences.

## Question 6 — Missing-group display policy

**Task:** Build frequencies for [java, java], display zero for missing lambda, and confirm lambda was not inserted.

```java
Map<String, Long> counts = Stream.of("java", "java")
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
System.out.println(counts.getOrDefault("lambda", 0L));
System.out.println(counts.containsKey("lambda"));
```

**Output:**
```text
0
false
```

**Concept:** A display fallback does not create an observed group.

## Question 7 — Nulls and unique values

**Task:** For [Java, Java, Ada, null], print total occurrences, non-null occurrences, and unique non-null count.

```java
List<String> words = Arrays.asList("Java", "Java", "Ada", null);
System.out.println(words.stream().collect(Collectors.counting()));
System.out.println(words.stream().filter(Objects::nonNull).count());
System.out.println(words.stream().filter(Objects::nonNull).distinct().count());
```

**Output:**
```text
4
3
2
```

**Concept:** Counting, presence filtering, and deduplication answer distinct questions.

## Question 8 — Normalized unique count

**Task:** Count unique names in [" Ada ", "ada", "Grace"] after trim and lowercase normalization.

```java
System.out.println(Stream.of(" Ada ", "ada", "Grace")
        .map(String::trim)
        .map(s -> s.toLowerCase(Locale.ROOT))
        .distinct().count());
```

**Output:**
```text
2
```

**Concept:** Uniqueness applies to the chosen normalized representation.

## Question 9 — Count per Boolean partition

**Task:** Count positives and nonpositives in [-1, 0, 2, 3], then show both zero counts for empty input.

```java
Map<Boolean, Long> counts = Stream.of(-1, 0, 2, 3)
        .collect(Collectors.partitioningBy(n -> n > 0, Collectors.counting()));
System.out.println(counts.get(true) + ":" + counts.get(false));
Map<Boolean, Long> empty = Stream.<Integer>empty()
        .collect(Collectors.partitioningBy(n -> n > 0, Collectors.counting()));
System.out.println(empty.get(true) + ":" + empty.get(false));
```

**Output:**
```text
2:2
0:0
```

**Concept:** Both partition counts remain present even when no source element exists.

## Question 10 — Filtered employee counts

**Task:** Count employees earning at least 80 per department from Ada/IT/90, Grace/IT/70, Bob/HR/80; print IT and HR.

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Main {
    static class Employee {
        final String name, department;
        final int salary;
        Employee(String name, String department, int salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }
        String getName() { return name; }
        String getDepartment() { return department; }
        int getSalary() { return salary; }
    }
    public static void main(String[] args) throws Exception {
        Map<String, Long> counts = Stream.of(new Employee("Ada", "IT", 90), new Employee("Grace", "IT", 70), new Employee("Bob", "HR", 80))
                .filter(e -> e.salary >= 80)
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println(counts.get("IT"));
        System.out.println(counts.get("HR"));
    }
}
```

**Output:**
```text
1
1
```

**Concept:** An upstream filter determines which elements ever reach grouped counting.

---

## Interview Questions and Answers

### Interview Question 1 — What type does counting return?

**Answer:** Its collected result is Long; Stream.count returns primitive long.

### Interview Question 2 — Does counting remove duplicates or nulls?

**Answer:** No. It counts the elements reaching it; upstream rules determine which elements arrive.

### Interview Question 3 — Why use counting instead of count?

**Answer:** It composes as a downstream collector inside grouping, partitioning, or another collector adapter.

### Interview Question 4 — Is counting equivalent to SQL COUNT(nullableColumn)?

**Answer:** Not automatically. Stream counting counts elements reaching the collector; SQL column counts normally exclude null column values. Apply the matching selection policy explicitly.

### Interview Question 5 — Why avoid a captured mutable counter in forEach?

**Answer:** A reduction directly expresses the result and follows the partition/combiner protocol. A shared mutable counter can add races or unnecessary coordination and does not improve the domain contract.

---

## Key Takeaways

| Skill | What to remember |
|---|---|
| Count occurrences | counting counts elements rather than summing their numeric values. |
| Primitive versus boxed count | Stream.count returns primitive long; counting collects a Long. |
| Empty count | The empty counting reduction has identity zero. |
| Counts by length | counting composes as a per-group downstream reduction. |
| Word-frequency table | Identity classification groups equal words before counting occurrences. |

[← Road Map lesson](../../Road%20Map/11_Stream_API/176_counting.md)

[← 175. mapping()](175_mapping.md) | [Course Index](../README.md) | [177. joining() →](177_joining.md)
