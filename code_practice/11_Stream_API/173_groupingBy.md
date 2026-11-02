[← 172. Collectors](172_Collectors.md) | [Course Index](../README.md) | [174. partitioningBy() →](174_partitioningBy.md)

# Java groupingBy() Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/11_Stream_API/173_groupingBy.md)

These exercises follow the corresponding Road Map lesson, progressing from basic operations to composition, boundary cases, and safe execution. Each coding question includes a model solution, expected output, and concept explanation.

**Java version:** Java 8+. Short snippets are independent and belong inside `main()`; assume imports from `java.util.*`, `java.util.function.*`, and `java.util.stream.*`. Complete programs include their helper types and should be saved as `Main.java`. Run questions separately. Inputs are non-null unless explicitly handled, and arithmetic uses small values that do not overflow. For checked I/O, `main()` must declare `throws Exception` or handle the failure.

## Question 1 — Group by length

**Task:** Group [Ada, Bob, Grace, Alan] by string length and print groups for lengths three and five.

```java
Map<Integer, List<String>> groups = Stream.of("Ada", "Bob", "Grace", "Alan")
        .collect(Collectors.groupingBy(String::length));
System.out.println(groups.get(3));
System.out.println(groups.get(5));
```

**Output:**
```text
[Ada, Bob]
[Grace]
```

**Concept:** A classifier assigns each original element to one observed key.

## Question 2 — Initial-letter groups

**Task:** Omit empty words from ["", "Ada", "Alan", "Bob"] and group the others by first letter. Print A and B groups.

```java
Map<Character, List<String>> groups = Stream.of("", "Ada", "Alan", "Bob")
        .filter(s -> !s.isEmpty())
        .collect(Collectors.groupingBy(s -> s.charAt(0)));
System.out.println(groups.get('A'));
System.out.println(groups.get('B'));
```

**Output:**
```text
[Ada, Alan]
[Bob]
```

**Concept:** Validate input before a classifier such as charAt can fail.

## Question 3 — Sorted group keys

**Task:** Group [Grace, Ada, Alan, Bob] by length using a TreeMap and print keys and the length-three group.

```java
Map<Integer, List<String>> groups = Stream.of("Grace", "Ada", "Alan", "Bob")
        .collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toList()));
System.out.println(groups.keySet());
System.out.println(groups.get(3));
```

**Output:**
```text
[3, 4, 5]
[Ada, Bob]
```

**Concept:** Key iteration order and order inside each group are separate contracts.

## Question 4 — Counts per department

**Task:** Count Ada/IT/90, Bob/HR/80, and Grace/IT/70 per department.

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
        Map<String, Long> counts = Stream.of(new Employee("Ada", "IT", 90), new Employee("Bob", "HR", 80), new Employee("Grace", "IT", 70))
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
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

**Concept:** counting changes downstream group values to Long.

## Question 5 — Department salary totals

**Task:** Sum salaries for Ada/IT/90, Bob/HR/80, and Grace/IT/70. Print IT and HR totals.

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
        Map<String, Integer> totals = Stream.of(new Employee("Ada", "IT", 90), new Employee("Bob", "HR", 80), new Employee("Grace", "IT", 70))
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summingInt(Employee::getSalary)));
        System.out.println(totals.get("IT"));
        System.out.println(totals.get("HR"));
    }
}
```

**Output:**
```text
160
80
```

**Concept:** The downstream reduction summarizes each classification group independently.

## Question 6 — Store projected names

**Task:** Group Ada/IT/90, Bob/HR/80, and Grace/IT/70 by department, storing only names. Print the IT group.

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
        Map<String, List<String>> names = Stream.of(new Employee("Ada", "IT", 90), new Employee("Bob", "HR", 80), new Employee("Grace", "IT", 70))
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.toList())));
        System.out.println(names.get("IT"));
    }
}
```

**Output:**
```text
[Ada, Grace]
```

**Concept:** Mapping downstream keeps original-object classification while changing stored values.

## Question 7 — Nested classification

**Task:** Group Ada/IT/90, Alan/IT/70, and Bob/HR/80 by department and then first name letter; print the IT/A count.

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
        Map<String, Map<Character, Long>> counts = Stream.of(new Employee("Ada", "IT", 90), new Employee("Alan", "IT", 70), new Employee("Bob", "HR", 80))
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.groupingBy(e -> e.name.charAt(0), Collectors.counting())));
        System.out.println(counts.get("IT").get('A'));
    }
}
```

**Output:**
```text
2
```

**Concept:** A downstream grouping collector produces a nested map.

## Question 8 — Missing category policy

**Task:** Group ["IT", null, "HR", null] by category, mapping missing categories to Unknown, and print that count.

```java
Map<String, Long> counts = Arrays.asList("IT", null, "HR", null).stream()
        .collect(Collectors.groupingBy(s -> s == null ? "Unknown" : s, Collectors.counting()));
System.out.println(counts.get("Unknown"));
```

**Output:**
```text
2
```

**Concept:** Ordinary groupingBy requires non-null classifier keys.

## Question 9 — Empty observed keys

**Task:** Group an empty string stream by length and print map size plus whether key three exists.

```java
Map<Integer, List<String>> groups = Stream.<String>empty().collect(Collectors.groupingBy(String::length));
System.out.println(groups.size());
System.out.println(groups.containsKey(3));
```

**Output:**
```text
0
false
```

**Concept:** Ordinary grouping creates observed keys rather than predefined empty buckets.

## Question 10 — Concurrent grouped counts

**Task:** Count [Ada, Bob, Grace, Alan] by length using parallel groupingByConcurrent; print counts for lengths three and five.

```java
java.util.concurrent.ConcurrentMap<Integer, Long> counts = Stream.of("Ada", "Bob", "Grace", "Alan").parallel()
        .collect(Collectors.groupingByConcurrent(String::length, Collectors.counting()));
System.out.println(counts.get(3));
System.out.println(counts.get(5));
```

**Output:**
```text
2
1
```

**Concept:** Concurrent grouping has unordered semantics; use key lookups instead of expecting iteration order.

---

## Interview Questions and Answers

### Interview Question 1 — How does groupingBy differ from toMap?

**Answer:** Grouping partitions source elements by a key and reduces each group; toMap maps individual keys and values and needs a collision policy.

### Interview Question 2 — Are absent group keys represented by empty lists?

**Answer:** Not by ordinary groupingBy. Keys with no classified elements are absent unless explicitly added elsewhere.

### Interview Question 3 — Is groupingByConcurrent always better in parallel?

**Answer:** No. It changes ordering semantics, has its own contention and data-distribution costs, and must match the required result contract.

### Interview Question 4 — Are grouped objects deep copies?

**Answer:** No. Default grouped lists hold references to source objects. Map to new immutable representations if ownership requires separation.

### Interview Question 5 — Can a mutable key break later group lookup?

**Answer:** Yes. Changing equality- or ordering-relevant key state after insertion can invalidate map lookup assumptions.

---

## Key Takeaways

| Skill | What to remember |
|---|---|
| Group by length | A classifier assigns each original element to one observed key. |
| Initial-letter groups | Validate input before a classifier such as charAt can fail. |
| Sorted group keys | Key iteration order and order inside each group are separate contracts. |
| Counts per department | counting changes downstream group values to Long. |
| Department salary totals | The downstream reduction summarizes each classification group independently. |

[← Road Map lesson](../../Road%20Map/11_Stream_API/173_groupingBy.md)

[← 172. Collectors](172_Collectors.md) | [Course Index](../README.md) | [174. partitioningBy() →](174_partitioningBy.md)
