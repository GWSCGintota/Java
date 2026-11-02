[← 176. counting()](176_counting.md) | [Course Index](../README.md) | [178. Parallel streams →](178_Parallel_streams.md)

# Java joining() Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/11_Stream_API/177_joining.md)

These exercises follow the corresponding Road Map lesson, progressing from basic operations to composition, boundary cases, and safe execution. Each coding question includes a model solution, expected output, and concept explanation.

**Java version:** Java 8+. Short snippets are independent and belong inside `main()`; assume imports from `java.util.*`, `java.util.function.*`, and `java.util.stream.*`. Complete programs include their helper types and should be saved as `Main.java`. Run questions separately. Inputs are non-null unless explicitly handled, and arithmetic uses small values that do not overflow. For checked I/O, `main()` must declare `throws Exception` or handle the failure.

## Question 1 — No delimiter

**Task:** Join A, B, and C with no separator.

```java
System.out.println(Stream.of("A", "B", "C").collect(Collectors.joining()));
```

**Output:**
```text
ABC
```

**Concept:** The no-argument collector concatenates character sequences.

## Question 2 — Custom delimiter

**Task:** Join Ada, Grace, and Alan with semicolons.

```java
System.out.println(Stream.of("Ada", "Grace", "Alan").collect(Collectors.joining(";")));
```

**Output:**
```text
Ada;Grace;Alan
```

**Concept:** Delimiters appear between elements, without a trailing separator.

## Question 3 — Prefix and suffix

**Task:** Join Ada and Grace as a comma-separated list inside parentheses.

```java
System.out.println(Stream.of("Ada", "Grace").collect(Collectors.joining(", ", "(", ")")));
```

**Output:**
```text
(Ada, Grace)
```

**Concept:** Prefix and suffix surround the complete result once.

## Question 4 — Numeric elements

**Task:** Join integers one through five with hyphens after converting them to strings.

```java
System.out.println(IntStream.rangeClosed(1, 5).mapToObj(String::valueOf).collect(Collectors.joining("-")));
```

**Output:**
```text
1-2-3-4-5
```

**Concept:** joining expects character sequences, so primitive values need an explicit text projection.

## Question 5 — Empty input wrappers

**Task:** Join an empty string stream with square-bracket prefix and suffix.

```java
System.out.println(Stream.<String>empty().collect(Collectors.joining(",", "[", "]")));
```

**Output:**
```text
[]
```

**Concept:** Empty joining preserves its chosen prefix and suffix.

## Question 6 — Nullable and blank text

**Task:** Join [" Ada ", null, "", " Grace ", "  "] after omitting nulls and trimmed blanks.

```java
System.out.println(Arrays.asList(" Ada ", null, "", " Grace ", "  ").stream()
        .filter(Objects::nonNull)
        .map(String::trim)
        .filter(s -> !s.isEmpty())
        .collect(Collectors.joining(", ")));
```

**Output:**
```text
Ada, Grace
```

**Concept:** Null, whitespace normalization, and blank policies belong before formatting.

## Question 7 — Deterministic lexical output

**Task:** Sort [Grace, Ada, Bob] and join with a slash.

```java
System.out.println(Stream.of("Grace", "Ada", "Bob").sorted().collect(Collectors.joining(" / ")));
```

**Output:**
```text
Ada / Bob / Grace
```

**Concept:** Sorting defines a desired text order before joining.

## Question 8 — Grouped object fields

**Task:** Group Ada/IT/90, Grace/IT/70, and Bob/HR/80 by department and join names with commas; print IT and HR.

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
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.joining(", "))));
        System.out.println(groups.get("IT"));
        System.out.println(groups.get("HR"));
    }
}
```

**Output:**
```text
Ada, Grace
Bob
```

**Concept:** mapping extracts field text while the outer collector still classifies original objects.

## Question 9 — Ordered parallel joining

**Task:** Join the ordered integer range 1 through 5 in parallel, separated by commas.

```java
System.out.println(IntStream.rangeClosed(1, 5).parallel()
        .mapToObj(String::valueOf)
        .collect(Collectors.joining(",")));
```

**Output:**
```text
1,2,3,4,5
```

**Concept:** A correct joining reduction preserves an ordered source's required encounter order.

## Question 10 — Quote CSV field contents

**Task:** Encode non-null fields A,B and C"D as one CSV record using an all-fields-quoted policy with doubled internal quotes.

```java
String csv = Stream.of("A,B", "C\"D")
        .map(s -> "\"" + s.replace("\"", "\"\"") + "\"")
        .collect(Collectors.joining(","));
System.out.println(csv);
```

**Output:**
```text
"A,B","C""D"
```

**Concept:** joining only inserts delimiters; this explicit field-encoding step handles commas and quotes for the stated simple record contract.

---

## Interview Questions and Answers

### Interview Question 1 — What is returned for empty joining input?

**Answer:** The no-prefix form returns empty text; the prefix/suffix form retains those surrounding strings.

### Interview Question 2 — Does joining escape delimiters contained in values?

**Answer:** No. It only inserts separators; use a real format encoder when field contents require escaping.

### Interview Question 3 — Does parallel joining scramble an ordered source?

**Answer:** A correct joining reduction preserves required encounter order, though unordered sources do not define a stable text order.

### Interview Question 4 — Is joining appropriate for arbitrarily large output?

**Answer:** It creates one final in-memory String, which can be expensive for very large data. Use an output writer or streaming format when ownership and size require it.

### Interview Question 5 — When is String.join simpler?

**Answer:** When values are already a small known set or suitable iterable and no stream transformations or downstream collector composition are needed.

---

## Key Takeaways

| Skill | What to remember |
|---|---|
| No delimiter | The no-argument collector concatenates character sequences. |
| Custom delimiter | Delimiters appear between elements, without a trailing separator. |
| Prefix and suffix | Prefix and suffix surround the complete result once. |
| Numeric elements | joining expects character sequences, so primitive values need an explicit text projection. |
| Empty input wrappers | Empty joining preserves its chosen prefix and suffix. |

[← Road Map lesson](../../Road%20Map/11_Stream_API/177_joining.md)

[← 176. counting()](176_counting.md) | [Course Index](../README.md) | [178. Parallel streams →](178_Parallel_streams.md)
