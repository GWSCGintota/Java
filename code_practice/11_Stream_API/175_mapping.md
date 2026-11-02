[← 174. partitioningBy()](174_partitioningBy.md) | [Course Index](../README.md) | [176. counting() →](176_counting.md)

# Java mapping() Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/11_Stream_API/175_mapping.md)

These exercises follow the corresponding Road Map lesson, progressing from basic operations to composition, boundary cases, and safe execution. Each coding question includes a model solution, expected output, and concept explanation.

**Java version:** Java 8+. Short snippets are independent and belong inside `main()`; assume imports from `java.util.*`, `java.util.function.*`, and `java.util.stream.*`. Complete programs include their helper types and should be saved as `Main.java`. Run questions separately. Inputs are non-null unless explicitly handled, and arithmetic uses small values that do not overflow. For checked I/O, `main()` must declare `throws Exception` or handle the failure.

## Question 1 — Top-level mapping collector

**Task:** Collect lengths of Ada and Grace using Collectors.mapping instead of stream map.

```java
List<Integer> lengths = Stream.of("Ada", "Grace")
        .collect(Collectors.mapping(String::length, Collectors.toList()));
System.out.println(lengths);
```

**Output:**
```text
[3, 5]
```

**Concept:** mapping adapts input delivered to a downstream collector.

## Question 2 — Uppercase group values

**Task:** Group [Ada, Bob, Grace] by original length while storing uppercase names.

```java
Map<Integer, List<String>> groups = Stream.of("Ada", "Bob", "Grace")
        .collect(Collectors.groupingBy(String::length, Collectors.mapping(s -> s.toUpperCase(Locale.ROOT), Collectors.toList())));
System.out.println(groups.get(3));
System.out.println(groups.get(5));
```

**Output:**
```text
[ADA, BOB]
[GRACE]
```

**Concept:** Classification sees original inputs while downstream storage sees projected values.

## Question 3 — Unique employee names

**Task:** Group Ada/IT/90, Ada/IT/95, and Bob/HR/80 by department, storing unique names in encounter order.

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
        Map<String, Set<String>> groups = Stream.of(new Employee("Ada", "IT", 90), new Employee("Ada", "IT", 95), new Employee("Bob", "HR", 80))
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.toCollection(LinkedHashSet::new))));
        System.out.println(groups.get("IT"));
        System.out.println(groups.get("HR"));
    }
}
```

**Output:**
```text
[Ada]
[Bob]
```

**Concept:** Mapping plus a set downstream defines uniqueness of projected names.

## Question 4 — Joined names by department

**Task:** For Ada/IT/90, Grace/IT/70, and Bob/HR/80, join names per department with a slash; print IT.

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
        Map<String, String> groups = Stream.of(new Employee("Ada", "IT", 90), new Employee("Grace", "IT", 70), new Employee("Bob", "HR", 80))
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.joining(" / "))));
        System.out.println(groups.get("IT"));
    }
}
```

**Output:**
```text
Ada / Grace
```

**Concept:** Projection and formatting can be nested downstream of object classification.

## Question 5 — Partitioned projected text

**Task:** Partition [Ada, Grace, Alan] by length above three and join uppercase values on both sides.

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

**Concept:** The outer partition rule is independent from the downstream representation.

## Question 6 — Compare map and mapping

**Task:** Produce lengths of Ada and Grace with both top-level map and a mapping collector.

```java
System.out.println(Stream.of("Ada", "Grace").map(String::length).collect(Collectors.toList()));
System.out.println(Stream.of("Ada", "Grace")
        .collect(Collectors.mapping(String::length, Collectors.toList())));
```

**Output:**
```text
[3, 5]
[3, 5]
```

**Concept:** The forms can be equivalent at the top level, though moving projections before grouping can change classifier inputs.

## Question 7 — Empty downstream result

**Task:** Apply mapping(String::length, toList()) to an empty string stream.

```java
System.out.println(Stream.<String>empty().collect(Collectors.mapping(String::length, Collectors.toList())));
```

**Output:**
```text
[]
```

**Concept:** Empty behavior comes from the downstream collector.

## Question 8 — Null mapping results are counted

**Task:** Map all of Ada, Bob, and Grace to null, then count downstream.

```java
Long count = Stream.of("Ada", "Bob", "Grace")
        .collect(Collectors.mapping(s -> (String) null, Collectors.counting()));
System.out.println(count);
```

**Output:**
```text
3
```

**Concept:** mapping does not discard null results; counting counts inputs reaching it.

## Question 9 — No implicit flattening

**Task:** Map [1, 2] to lists [n, n*10] inside a mapping collector, retaining nested lists.

```java
List<List<Integer>> groups = Stream.of(1, 2)
        .collect(Collectors.mapping(n -> Arrays.asList(n, n * 10), Collectors.toList()));
System.out.println(groups);
```

**Output:**
```text
[[1, 10], [2, 20]]
```

**Concept:** mapping delivers one result per input and does not flatten its contents.

## Question 10 — Count unique projections per group

**Task:** Group Ada/IT/90, Ada/IT/95, Grace/IT/70, and Bob/HR/80 by department, counting distinct names.

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
        Map<String, Integer> counts = Stream.of(new Employee("Ada", "IT", 90), new Employee("Ada", "IT", 95), new Employee("Grace", "IT", 70), new Employee("Bob", "HR", 80))
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.collectingAndThen(Collectors.toSet(), Set::size))));
        System.out.println(counts.get("IT"));
        System.out.println(counts.get("HR"));
    }
}
```

**Output:**
```text
2
1
```

**Concept:** A downstream finisher can turn unique projected values into an Integer size.

---

## Interview Questions and Answers

### Interview Question 1 — What does mapping adapt?

**Answer:** The input delivered to a downstream collector while retaining the surrounding collector's original input contract.

### Interview Question 2 — Why use mapping inside groupingBy?

**Answer:** It lets classification use original objects while group values store a selected or transformed representation.

### Interview Question 3 — Does mapping skip null results?

**Answer:** No. It passes mapper results downstream, where acceptance and behavior depend on that collector.

### Interview Question 4 — Can Collectors.mapping perform selection?

**Answer:** It transforms one input into one downstream input. Use a suitable upstream filter or, on Java 9+, a downstream filtering collector for a different selection contract.

### Interview Question 5 — What controls null acceptance after mapping?

**Answer:** The downstream collector's contract. Returning null is not automatically converted to absence.

---

## Key Takeaways

| Skill | What to remember |
|---|---|
| Top-level mapping collector | mapping adapts input delivered to a downstream collector. |
| Uppercase group values | Classification sees original inputs while downstream storage sees projected values. |
| Unique employee names | Mapping plus a set downstream defines uniqueness of projected names. |
| Joined names by department | Projection and formatting can be nested downstream of object classification. |
| Partitioned projected text | The outer partition rule is independent from the downstream representation. |

[← Road Map lesson](../../Road%20Map/11_Stream_API/175_mapping.md)

[← 174. partitioningBy()](174_partitioningBy.md) | [Course Index](../README.md) | [176. counting() →](176_counting.md)
