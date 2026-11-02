[← 051. Arrays of objects](051_Arrays_of_objects.md) | [Course Index](../README.md) | [053. Sorting arrays →](053_Sorting_arrays.md)

# Java Arrays utility class Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/04_Arrays/052_Arrays_utility_class.md)

These exercises follow [052. Arrays utility class](../../Road%20Map/04_Arrays/052_Arrays_utility_class.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Readable output

**Task:** Print a primitive array with Arrays.toString.

```java
System.out.println(Arrays.toString(new int[]{3, 1, 2}));
```

**Expected result:**
```text
[3, 1, 2]
```

**Concept:** Arrays.toString formats one-dimensional component values.

## Question 2 — Content equality

**Task:** Compare two separate primitive arrays.

```java
System.out.println(Arrays.equals(new int[]{1, 2}, new int[]{1, 2}));
```

**Expected result:**
```text
true
```

**Concept:** Arrays.equals compares corresponding components.

## Question 3 — Fill

**Task:** Set every component to 5.

```java
int[] values = new int[3];
Arrays.fill(values, 5);
System.out.println(Arrays.toString(values));
```

**Expected result:**
```text
[5, 5, 5]
```

**Concept:** fill writes a chosen value into existing storage.

## Question 4 — Extend a copy

**Task:** Copy two values into a longer array.

```java
int[] copy = Arrays.copyOf(new int[]{1, 2}, 4);
System.out.println(Arrays.toString(copy));
```

**Expected result:**
```text
[1, 2, 0, 0]
```

**Concept:** Extending a primitive copy pads new components with defaults.

## Question 5 — Range copy

**Task:** Copy indices one through two from an array.

```java
System.out.println(Arrays.toString(Arrays.copyOfRange(new int[]{1, 2, 3, 4}, 1, 3)));
```

**Expected result:**
```text
[2, 3]
```

**Concept:** The range start is inclusive and end exclusive.

## Question 6 — Sort

**Task:** Sort an int array ascending.

```java
int[] values = {3, 1, 2};
Arrays.sort(values);
System.out.println(Arrays.toString(values));
```

**Expected result:**
```text
[1, 2, 3]
```

**Concept:** sort mutates the supplied array.

## Question 7 — Binary search

**Task:** Search for 5 in an ascending array.

```java
System.out.println(Arrays.binarySearch(new int[]{1, 3, 5, 7}, 5));
```

**Expected result:**
```text
2
```

**Concept:** Binary search requires the relevant range to be sorted consistently.

## Question 8 — Deep formatting

**Task:** Print a nested array's contents.

```java
System.out.println(Arrays.deepToString(new int[][]{{1, 2}, {3}}));
```

**Expected result:**
```text
[[1, 2], [3]]
```

**Concept:** deepToString recursively formats nested arrays.

## Question 9 — Deep equality

**Task:** Compare two nested primitive-row arrays.

```java
System.out.println(Arrays.deepEquals(new int[][]{{1, 2}}, new int[][]{{1, 2}}));
```

**Expected result:**
```text
true
```

**Concept:** deepEquals handles nested array components rather than just their identities.

## Question 10 — Stream summary

**Task:** Sum an int array through Arrays.stream.

```java
System.out.println(Arrays.stream(new int[]{2, 3, 4}).sum());
```

**Expected result:**
```text
9
```

**Concept:** Primitive array streams provide primitive operations such as sum.

---

## Interview Questions and Answers

### Interview Question 1 — Why does printing an array directly not show its elements?

**Answer:** Arrays inherit an object-style toString representation. Use Arrays.toString for one dimension or deepToString for nested arrays.

### Interview Question 2 — How do equals and deepEquals differ?

**Answer:** Arrays.equals compares one level of components; deepEquals handles nested array contents recursively for supported array structures.

### Interview Question 3 — Does copyOf preserve the original array?

**Answer:** It creates another array, truncating or padding to the requested length. Reference components remain shallow copies.

### Interview Question 4 — What precondition does binarySearch require?

**Answer:** The searched range must be sorted according to the same ordering used by the search. Otherwise, results are not meaningful under its contract.

### Interview Question 5 — Is Arrays.asList a resizable copy?

**Answer:** No. For reference-array input it is a fixed-size list backed by that array. Also, passing an int[] yields a one-element List<int[]> rather than a list of boxed integers.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Readable output | Arrays.toString formats one-dimensional component values. |
| Content equality | Arrays.equals compares corresponding components. |
| Fill | fill writes a chosen value into existing storage. |
| Extend a copy | Extending a primitive copy pads new components with defaults. |
| Range copy | The range start is inclusive and end exclusive. |

[← 051. Arrays of objects](051_Arrays_of_objects.md) | [Course Index](../README.md) | [053. Sorting arrays →](053_Sorting_arrays.md)
