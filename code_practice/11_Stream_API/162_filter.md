[← 161. Creating streams](161_Creating_streams.md) | [Course Index](../README.md) | [163. map() →](163_map.md)

# Java filter() Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/11_Stream_API/162_filter.md)

These exercises follow the corresponding Road Map lesson, progressing from basic operations to composition, boundary cases, and safe execution. Each coding question includes a model solution, expected output, and concept explanation.

**Java version:** Java 8+. Short snippets are independent and belong inside `main()`; assume imports from `java.util.*`, `java.util.function.*`, and `java.util.stream.*`. Complete programs include their helper types and should be saved as `Main.java`. Run questions separately. Inputs are non-null unless explicitly handled, and arithmetic uses small values that do not overflow. For checked I/O, `main()` must declare `throws Exception` or handle the failure.

## Question 1 — Positive even numbers

**Task:** Select positive evens from [-4, 0, 3, 4, 6].

```java
System.out.println(Stream.of(-4, 0, 3, 4, 6)
        .filter(n -> n > 0)
        .filter(n -> n % 2 == 0)
        .collect(Collectors.toList()));
```

**Output:**
```text
[4, 6]
```

**Concept:** Successive filters require retained values to satisfy all their conditions.

## Question 2 — Inclusive range

**Task:** Select values in the inclusive range 10–20 from [9, 10, 15, 20, 21].

```java
System.out.println(Stream.of(9, 10, 15, 20, 21).filter(n -> n >= 10 && n <= 20).collect(Collectors.toList()));
```

**Output:**
```text
[10, 15, 20]
```

**Concept:** Boundary tests distinguish inclusive and strict comparisons.

## Question 3 — Null and blank names

**Task:** From [null, "", " Ada ", "  ", "Grace"], retain trimmed nonblank names.

```java
System.out.println(Arrays.asList(null, "", " Ada ", "  ", "Grace").stream()
        .filter(Objects::nonNull)
        .map(String::trim)
        .filter(s -> !s.isEmpty())
        .collect(Collectors.toList()));
```

**Output:**
```text
[Ada, Grace]
```

**Concept:** Guard null before dereferencing and normalize before blank checks.

## Question 4 — Reusable predicates

**Task:** Combine positive and divisible-by-three predicates to filter [-3, 0, 3, 4, 6].

```java
Predicate<Integer> positive = n -> n > 0;
Predicate<Integer> divisibleByThree = n -> n % 3 == 0;
System.out.println(Stream.of(-3, 0, 3, 4, 6)
        .filter(positive.and(divisibleByThree))
        .collect(Collectors.toList()));
```

**Output:**
```text
[3, 6]
```

**Concept:** Predicate composition makes reusable selection rules explicit.

## Question 5 — OR selection

**Task:** Retain names beginning with A or longer than five characters from [Ada, Bob, Grace, Alexander].

```java
System.out.println(Stream.of("Ada", "Bob", "Grace", "Alexander")
        .filter(s -> s.startsWith("A") || s.length() > 5)
        .collect(Collectors.toList()));
```

**Output:**
```text
[Ada, Alexander]
```

**Concept:** OR differs from chaining two filters, which requires both conditions.

## Question 6 — First matching value

**Task:** Find the first even number in [1, 3, 8, 10].

```java
System.out.println(Stream.of(1, 3, 8, 10).filter(n -> n % 2 == 0).findFirst().orElse(-1));
```

**Output:**
```text
8
```

**Concept:** findFirst can stop once the first retained value is established.

## Question 7 — No matches

**Task:** Filter evens from [1, 3, 5], then separately report whether an optional even search succeeds.

```java
List<Integer> values = Arrays.asList(1, 3, 5);
System.out.println(values.stream().filter(n -> n % 2 == 0).collect(Collectors.toList()));
System.out.println(values.stream().filter(n -> n % 2 == 0).findFirst().isPresent());
```

**Output:**
```text
[]
false
```

**Concept:** No match is an empty result, not automatically an exception.

## Question 8 — Filter and limit order

**Task:** Compare selecting two evens with selecting evens from the first two values of [1, 2, 3, 4].

```java
List<Integer> values = Arrays.asList(1, 2, 3, 4);
System.out.println(values.stream().filter(n -> n % 2 == 0).limit(2).collect(Collectors.toList()));
System.out.println(values.stream().limit(2).filter(n -> n % 2 == 0).collect(Collectors.toList()));
```

**Output:**
```text
[2, 4]
[2]
```

**Concept:** A prefix bound before filtering changes the candidate set.

## Question 9 — Filter custom objects

**Task:** Keep IT employees earning above 80 from Ada/IT/90, Bob/HR/95, and Grace/IT/70; print names.

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
        List<Employee> employees = Arrays.asList(new Employee("Ada", "IT", 90), new Employee("Bob", "HR", 95), new Employee("Grace", "IT", 70));
        System.out.println(employees.stream()
                .filter(e -> "IT".equals(e.department) && e.salary > 80)
                .map(Employee::getName)
                .collect(Collectors.toList()));
    }
}
```

**Output:**
```text
[Ada]
```

**Concept:** Object predicates can inspect properties without mutating the objects.

## Question 10 — Negated rule

**Task:** Remove multiples of three from [1, 3, 4, 6, 7] using Predicate.negate.

```java
Predicate<Integer> multipleOfThree = n -> n % 3 == 0;
System.out.println(Stream.of(1, 3, 4, 6, 7).filter(multipleOfThree.negate()).collect(Collectors.toList()));
```

**Output:**
```text
[1, 4, 7]
```

**Concept:** negate reverses a condition while retaining the element type.

---

## Interview Questions and Answers

### Interview Question 1 — Does filter change the stream element type?

**Answer:** No. It selects elements of the same type. Use map for transformations.

### Interview Question 2 — Do multiple filters behave like AND?

**Answer:** Their retained membership requires every predicate to pass, assuming the callbacks obey the stream contracts.

### Interview Question 3 — Can a filter throw?

**Answer:** Yes. A failing predicate propagates its exception through traversal; it is not silently treated as false.

### Interview Question 4 — Can moving a filter across map change the result?

**Answer:** Yes. Checking a prefix before trimming applies a different rule from checking normalized text. Reorder only when semantics are preserved.

### Interview Question 5 — Does filter remove elements from the original collection?

**Answer:** No. It selects downstream elements. For deliberate in-place removal, consider an appropriate collection mutation such as removeIf.

---

## Key Takeaways

| Skill | What to remember |
|---|---|
| Positive even numbers | Successive filters require retained values to satisfy all their conditions. |
| Inclusive range | Boundary tests distinguish inclusive and strict comparisons. |
| Null and blank names | Guard null before dereferencing and normalize before blank checks. |
| Reusable predicates | Predicate composition makes reusable selection rules explicit. |
| OR selection | OR differs from chaining two filters, which requires both conditions. |

[← Road Map lesson](../../Road%20Map/11_Stream_API/162_filter.md)

[← 161. Creating streams](161_Creating_streams.md) | [Course Index](../README.md) | [163. map() →](163_map.md)
