[← 173. groupingBy()](173_groupingBy.md) | [Course Index](../README.md) | [175. mapping() →](175_mapping.md)

# Java partitioningBy() Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/11_Stream_API/174_partitioningBy.md)

These exercises follow the corresponding Road Map lesson, progressing from basic operations to composition, boundary cases, and safe execution. Each coding question includes a model solution, expected output, and concept explanation.

**Java version:** Java 8+. Short snippets are independent and belong inside `main()`; assume imports from `java.util.*`, `java.util.function.*`, and `java.util.stream.*`. Complete programs include their helper types and should be saved as `Main.java`. Run questions separately. Inputs are non-null unless explicitly handled, and arithmetic uses small values that do not overflow. For checked I/O, `main()` must declare `throws Exception` or handle the failure.

## Question 1 — Parity partitions

**Task:** Partition [1, 2, 3, 4, 5] into even and odd lists, printing the true side first.

```java
Map<Boolean, List<Integer>> parts = Stream.of(1, 2, 3, 4, 5)
        .collect(Collectors.partitioningBy(n -> n % 2 == 0));
System.out.println(parts.get(true));
System.out.println(parts.get(false));
```

**Output:**
```text
[2, 4]
[1, 3, 5]
```

**Concept:** partitioningBy retains both matching and nonmatching elements.

## Question 2 — Positive versus nonpositive

**Task:** Partition [-2, 0, 3, 4] by strict positivity.

```java
Map<Boolean, List<Integer>> parts = Stream.of(-2, 0, 3, 4).collect(Collectors.partitioningBy(n -> n > 0));
System.out.println(parts.get(true));
System.out.println(parts.get(false));
```

**Output:**
```text
[3, 4]
[-2, 0]
```

**Concept:** The exact predicate determines where boundary zero belongs.

## Question 3 — Pass and fail counts

**Task:** Count scores [40, 50, 75, 20] as passing at 50 or above versus failing.

```java
Map<Boolean, Long> counts = Stream.of(40, 50, 75, 20)
        .collect(Collectors.partitioningBy(n -> n >= 50, Collectors.counting()));
System.out.println(counts.get(true));
System.out.println(counts.get(false));
```

**Output:**
```text
2
2
```

**Concept:** A downstream collector reduces each Boolean partition.

## Question 4 — Partition sums

**Task:** Sum even and odd values separately for [1, 2, 3, 4, 5].

```java
Map<Boolean, Integer> totals = Stream.of(1, 2, 3, 4, 5)
        .collect(Collectors.partitioningBy(n -> n % 2 == 0, Collectors.summingInt(Integer::intValue)));
System.out.println(totals.get(true));
System.out.println(totals.get(false));
```

**Output:**
```text
6
9
```

**Concept:** Partition values can be numeric summaries instead of lists.

## Question 5 — Empty partitions retain keys

**Task:** Partition an empty integer stream by positivity and print key presence plus the true-side list.

```java
Map<Boolean, List<Integer>> parts = Stream.<Integer>empty().collect(Collectors.partitioningBy(n -> n > 0));
System.out.println(parts.containsKey(true));
System.out.println(parts.containsKey(false));
System.out.println(parts.get(true));
```

**Output:**
```text
true
true
[]
```

**Concept:** Both Boolean keys are guaranteed even on empty input.

## Question 6 — One-sided input

**Task:** Partition [1, 2, 3] by positivity, printing both sides.

```java
Map<Boolean, List<Integer>> parts = Stream.of(1, 2, 3).collect(Collectors.partitioningBy(n -> n > 0));
System.out.println(parts.get(true));
System.out.println(parts.get(false));
```

**Output:**
```text
[1, 2, 3]
[]
```

**Concept:** A missing side has an empty result rather than an absent key.

## Question 7 — Nullable values

**Task:** Partition [Ada, null, Grace] by presence and print both lists.

```java
Map<Boolean, List<String>> parts = Arrays.asList("Ada", null, "Grace").stream()
        .collect(Collectors.partitioningBy(Objects::nonNull));
System.out.println(parts.get(true));
System.out.println(parts.get(false));
```

**Output:**
```text
[Ada, Grace]
[null]
```

**Concept:** A predicate can classify null safely rather than dereference it.

## Question 8 — Projected partition values

**Task:** Partition [Ada, Grace, Alan] by length above three and join uppercase names in each side.

```java
Map<Boolean, String> parts = Stream.of("Ada", "Grace", "Alan")
        .collect(Collectors.partitioningBy(s -> s.length() > 3, Collectors.mapping(s -> s.toUpperCase(Locale.ROOT), Collectors.joining(", "))));
System.out.println(parts.get(true));
System.out.println(parts.get(false));
```

**Output:**
```text
GRACE, ALAN
ADA
```

**Concept:** Downstream mapping changes representation without changing the classification input.

## Question 9 — Contrast grouping on empty input

**Task:** Compare empty groupingBy with a Boolean classifier against empty partitioningBy, using counts as group values.

```java
Map<Boolean, Long> groups = Stream.<Integer>empty()
        .collect(Collectors.groupingBy(n -> n > 0, Collectors.counting()));
Map<Boolean, Long> parts = Stream.<Integer>empty()
        .collect(Collectors.partitioningBy(n -> n > 0, Collectors.counting()));
System.out.println(groups.containsKey(true));
System.out.println(parts.get(true) + ":" + parts.get(false));
```

**Output:**
```text
false
0:0
```

**Concept:** General grouping has observed keys; partitioning initializes both Boolean reductions.

## Question 10 — Partition custom objects

**Task:** Partition Ada/IT/90, Bob/HR/70, and Grace/IT/100 by salary at least 90; store and print names per side.

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
        Map<Boolean, List<String>> parts = Stream.of(new Employee("Ada", "IT", 90), new Employee("Bob", "HR", 70), new Employee("Grace", "IT", 100))
                .collect(Collectors.partitioningBy(e -> e.salary >= 90, Collectors.mapping(Employee::getName, Collectors.toList())));
        System.out.println(parts.get(true));
        System.out.println(parts.get(false));
    }
}
```

**Output:**
```text
[Ada, Grace]
[Bob]
```

**Concept:** Object predicates and downstream projections compose into one partitioning reduction.

---

## Interview Questions and Answers

### Interview Question 1 — Are both Boolean keys guaranteed?

**Answer:** Yes. A missing side is represented by an empty downstream result rather than an absent key.

### Interview Question 2 — How does filtering differ?

**Answer:** filter retains only matching elements; partitioning retains both sides and reduces each separately.

### Interview Question 3 — Does partitioningBy require a map factory?

**Answer:** It offers predicate-only and predicate-plus-downstream forms rather than the general groupingBy map-factory overload.

### Interview Question 4 — Is the returned partition map guaranteed to be thread-safe?

**Answer:** No. Do not infer arbitrary later concurrent-mutation safety from the stream reduction or from the presence of two keys.

### Interview Question 5 — Does partitioning guarantee a printed key order?

**Answer:** It guarantees both Boolean keys, not a particular map iteration order. Print explicit lookups or format deliberately.

---

## Key Takeaways

| Skill | What to remember |
|---|---|
| Parity partitions | partitioningBy retains both matching and nonmatching elements. |
| Positive versus nonpositive | The exact predicate determines where boundary zero belongs. |
| Pass and fail counts | A downstream collector reduces each Boolean partition. |
| Partition sums | Partition values can be numeric summaries instead of lists. |
| Empty partitions retain keys | Both Boolean keys are guaranteed even on empty input. |

[← Road Map lesson](../../Road%20Map/11_Stream_API/174_partitioningBy.md)

[← 173. groupingBy()](173_groupingBy.md) | [Course Index](../README.md) | [175. mapping() →](175_mapping.md)
