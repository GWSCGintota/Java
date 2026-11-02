[← 053. Sorting arrays](053_Sorting_arrays.md) | [Course Index](../README.md) | [055. Classes →](../05_Object_Oriented_Programming/055_Classes.md)

# Java Searching arrays Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/04_Arrays/054_Searching_arrays.md)

These exercises follow [054. Searching arrays](../../Road%20Map/04_Arrays/054_Searching_arrays.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Linear first match

**Task:** Find the first index containing 7 in an unsorted array.

```java
int[] values = {4, 7, 2, 7}; int found = -1;
for (int i = 0; i < values.length; i++) { if (values[i] == 7) { found = i; break; } }
System.out.println(found);
```

**Expected result:**
```text
1
```

**Concept:** Linear search does not require sorted input.

## Question 2 — Absent target

**Task:** Return -1 when a linear search has no match.

```java
int[] values = {1, 3, 5}; int found = -1;
for (int i = 0; i < values.length; i++) { if (values[i] == 7) { found = i; break; } }
System.out.println(found);
```

**Expected result:**
```text
-1
```

**Concept:** An absence policy must be distinct from valid indices.

## Question 3 — Count matches

**Task:** Count every occurrence of 2.

```java
int count = 0;
for (int value : new int[]{2, 1, 2, 2}) { if (value == 2) { count++; } }
System.out.println(count);
```

**Expected result:**
```text
3
```

**Concept:** Counting all matches differs from stopping at the first.

## Question 4 — Last match

**Task:** Find the final index containing 2.

```java
int[] values = {2, 1, 2}; int found = -1;
for (int i = 0; i < values.length; i++) { if (values[i] == 2) { found = i; } }
System.out.println(found);
```

**Expected result:**
```text
2
```

**Concept:** A repeated assignment can retain the last matching position.

## Question 5 — Binary search hit

**Task:** Search a sorted array for 5.

```java
System.out.println(Arrays.binarySearch(new int[]{1, 3, 5, 7}, 5));
```

**Expected result:**
```text
2
```

**Concept:** Binary search eliminates parts of an ordered range.

## Question 6 — Insertion point

**Task:** Decode the insertion point for missing 4 in a sorted array.

```java
int result = Arrays.binarySearch(new int[]{1, 3, 5, 7}, 4);
System.out.println(result);
System.out.println(-result - 1);
```

**Expected result:**
```text
-3
2
```

**Concept:** A negative result encodes the insertion point as minus insertion point minus one.

## Question 7 — Empty search

**Task:** Search an empty sorted array.

```java
System.out.println(Arrays.binarySearch(new int[0], 7));
```

**Expected result:**
```text
-1
```

**Concept:** The insertion point is zero when the array is empty.

## Question 8 — String content

**Task:** Find a string by content with a null-safe comparison.

```java
String[] names = {null, new String("Ada"), "Grace"}; int found = -1;
for (int i = 0; i < names.length; i++) { if ("Ada".equals(names[i])) { found = i; break; } }
System.out.println(found);
```

**Expected result:**
```text
1
```

**Concept:** Reference identity is not a text-search policy.

## Question 9 — Sorted copy

**Task:** Search a sorted copy while preserving the original array.

```java
int[] original = {7, 1, 5}, sorted = original.clone();
Arrays.sort(sorted);
System.out.println(Arrays.binarySearch(sorted, 5));
System.out.println(Arrays.toString(original));
```

**Expected result:**
```text
1
[7, 1, 5]
```

**Concept:** A search index in a sorted copy is not automatically the original index.

## Question 10 — Manual binary search

**Task:** Implement binary search for a unique target in a sorted array.

```java
int[] values = {1, 3, 5, 7, 9}; int target = 7, low = 0, high = values.length - 1, found = -1;
while (low <= high) { int middle = low + (high - low) / 2; if (values[middle] == target) { found = middle; break; } if (values[middle] < target) { low = middle + 1; } else { high = middle - 1; } }
System.out.println(found);
```

**Expected result:**
```text
3
```

**Concept:** Inclusive bounds and a progress-preserving update ensure the search range shrinks.

---

## Interview Questions and Answers

### Interview Question 1 — How do linear and binary search differ?

**Answer:** Linear search scans candidates and works on unsorted arrays. Binary search uses compatible sorted order to narrow a range, requiring O(log n) comparisons rather than O(n) in the usual worst case.

### Interview Question 2 — What does a negative Arrays.binarySearch result mean?

**Answer:** The target is absent. The result is -(insertion point)-1, not simply a universal -1 sentinel.

### Interview Question 3 — Does binary search guarantee the first duplicate?

**Answer:** No. When multiple components compare equal to the target, its contract does not select the first occurrence. Implement a lower-bound search if that is required.

### Interview Question 4 — Why is sorting for one search not always an improvement?

**Answer:** Sorting has its own cost and changes ordering unless copied. For a single query, a linear scan can be simpler and asymptotically cheaper overall than sorting first.

### Interview Question 5 — What does an index from a sorted copy refer to?

**Answer:** The sorted copy's position. Preserve original-index metadata if the caller needs a position in the original sequence.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Linear first match | Linear search does not require sorted input. |
| Absent target | An absence policy must be distinct from valid indices. |
| Count matches | Counting all matches differs from stopping at the first. |
| Last match | A repeated assignment can retain the last matching position. |
| Binary search hit | Binary search eliminates parts of an ordered range. |

[← 053. Sorting arrays](053_Sorting_arrays.md) | [Course Index](../README.md) | [055. Classes →](../05_Object_Oriented_Programming/055_Classes.md)
