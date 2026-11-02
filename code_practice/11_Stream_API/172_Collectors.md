[← 171. collect()](171_collect.md) | [Course Index](../README.md) | [173. groupingBy() →](173_groupingBy.md)

# Java Collectors Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/11_Stream_API/172_Collectors.md)

These exercises follow the corresponding Road Map lesson, progressing from basic operations to composition, boundary cases, and safe execution. Each coding question includes a model solution, expected output, and concept explanation.

**Java version:** Java 8+. Short snippets are independent and belong inside `main()`; assume imports from `java.util.*`, `java.util.function.*`, and `java.util.stream.*`. Complete programs include their helper types and should be saved as `Main.java`. Run questions separately. Inputs are non-null unless explicitly handled, and arithmetic uses small values that do not overflow. For checked I/O, `main()` must declare `throws Exception` or handle the failure.

## Question 1 — Numeric summary

**Task:** Summarize lengths of Ada, Grace, and Alan; print count, sum, minimum, maximum, and average.

```java
IntSummaryStatistics stats = Stream.of("Ada", "Grace", "Alan")
        .collect(Collectors.summarizingInt(String::length));
System.out.println(stats.getCount());
System.out.println(stats.getSum());
System.out.println(stats.getMin());
System.out.println(stats.getMax());
System.out.println(stats.getAverage());
```

**Output:**
```text
3
12
3
5
4.0
```

**Concept:** One summary collector accumulates several related statistics.

## Question 2 — Empty summary policy

**Task:** Summarize an empty integer stream after boxing; print No values instead of interpreting minimum and maximum sentinels.

```java
IntSummaryStatistics stats = Stream.<Integer>empty().collect(Collectors.summarizingInt(Integer::intValue));
System.out.println(stats.getCount() == 0 ? "No values" : stats.getMin() + ":" + stats.getMax());
```

**Output:**
```text
No values
```

**Concept:** Check the count before interpreting empty summary extremes as observations.

## Question 3 — Average lengths

**Task:** Calculate the average length of Ada and Grace using a collector.

```java
double average = Stream.of("Ada", "Grace").collect(Collectors.averagingInt(String::length));
System.out.println(average);
```

**Output:**
```text
4.0
```

**Concept:** averagingInt produces a Double-valued reduction.

## Question 4 — Grouped counts

**Task:** Count names per length for [Ada, Bob, Grace].

```java
Map<Integer, Long> counts = Stream.of("Ada", "Bob", "Grace")
        .collect(Collectors.groupingBy(String::length, Collectors.counting()));
System.out.println(counts.get(3));
System.out.println(counts.get(5));
```

**Output:**
```text
2
1
```

**Concept:** A downstream collector changes group values from lists to counts.

## Question 5 — Grouped projection and joining

**Task:** Group [Ada, Bob, Grace] by length and join uppercase values with commas; print length-three output.

```java
Map<Integer, String> groups = Stream.of("Ada", "Bob", "Grace")
        .collect(Collectors.groupingBy(String::length, Collectors.mapping(s -> s.toUpperCase(Locale.ROOT), Collectors.joining(", "))));
System.out.println(groups.get(3));
```

**Output:**
```text
ADA, BOB
```

**Concept:** Downstream adapters compose classification, projection, and formatting.

## Question 6 — Sorted unique set

**Task:** Collect [3, 1, 3, 2] into a TreeSet.

```java
Set<Integer> values = Stream.of(3, 1, 3, 2).collect(Collectors.toCollection(TreeSet::new));
System.out.println(values);
```

**Output:**
```text
[1, 2, 3]
```

**Concept:** An explicit collection factory supplies sorted-set semantics.

## Question 7 — Maximum as a collector

**Task:** Find the longest name in [Ada, Grace, Alan] using Collectors.maxBy.

```java
Optional<String> longest = Stream.of("Ada", "Grace", "Alan")
        .collect(Collectors.maxBy(Comparator.comparingInt(String::length)));
System.out.println(longest.orElse("None"));
```

**Output:**
```text
Grace
```

**Concept:** maxBy is useful when an extreme-value reduction must compose as a collector.

## Question 8 — Finished unique count

**Task:** Count distinct words in [java, java, lambda] using toSet and a finisher.

```java
int unique = Stream.of("java", "java", "lambda")
        .collect(Collectors.collectingAndThen(Collectors.toSet(), Set::size));
System.out.println(unique);
```

**Output:**
```text
2
```

**Concept:** collectingAndThen can adapt a container result into a scalar.

## Question 9 — Collector reuse as a recipe

**Task:** Reuse one toList collector recipe on separate streams [A, B] and [C].

```java
Collector<String, ?, List<String>> recipe = Collectors.toList();
System.out.println(Stream.of("A", "B").collect(recipe));
System.out.println(Stream.of("C").collect(recipe));
```

**Output:**
```text
[A, B]
[C]
```

**Concept:** A standard collector is a reduction recipe, not a shared result container.

## Question 10 — Grouping versus map merging

**Task:** For [Ada, Alan, Bob], print all A-initial names from grouping and the first A-initial name from a map merge.

```java
List<String> names = Arrays.asList("Ada", "Alan", "Bob");
Map<Character, List<String>> groups = names.stream().collect(Collectors.groupingBy(s -> s.charAt(0)));
Map<Character, String> first = names.stream()
        .collect(Collectors.toMap(s -> s.charAt(0), Function.identity(), (a, b) -> a));
System.out.println(groups.get('A'));
System.out.println(first.get('A'));
```

**Output:**
```text
[Ada, Alan]
Ada
```

**Concept:** Grouping retains all group elements unless its downstream reduction says otherwise; map merging chooses one combined value.

---

## Interview Questions and Answers

### Interview Question 1 — What is a downstream collector?

**Answer:** A reduction used within another collector, such as counting the elements assigned to each grouping key.

### Interview Question 2 — Does using a collector imply concurrent accumulation?

**Answer:** No. Most ordinary collectors use isolated containers and merging; concurrent collectors have explicit contracts.

### Interview Question 3 — What is the benefit of summarizing collectors?

**Answer:** They accumulate related numeric statistics in one reduction with well-defined empty-input behavior.

### Interview Question 4 — What is IDENTITY_FINISH?

**Answer:** A collector characteristic indicating that the accumulator result can serve as the final result without a separate finishing conversion, subject to the collector contract.

### Interview Question 5 — Which newer collectors are unavailable on Java 8?

**Answer:** Examples include filtering and flatMapping from Java 9, unmodifiable collection collectors from Java 10, and teeing from Java 12.

---

## Key Takeaways

| Skill | What to remember |
|---|---|
| Numeric summary | One summary collector accumulates several related statistics. |
| Empty summary policy | Check the count before interpreting empty summary extremes as observations. |
| Average lengths | averagingInt produces a Double-valued reduction. |
| Grouped counts | A downstream collector changes group values from lists to counts. |
| Grouped projection and joining | Downstream adapters compose classification, projection, and formatting. |

[← Road Map lesson](../../Road%20Map/11_Stream_API/172_Collectors.md)

[← 171. collect()](171_collect.md) | [Course Index](../README.md) | [173. groupingBy() →](173_groupingBy.md)
