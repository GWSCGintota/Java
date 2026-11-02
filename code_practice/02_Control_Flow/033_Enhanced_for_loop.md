[← 032. do-while](032_do-while.md) | [Course Index](../README.md) | [034. break →](034_break.md)

# Java Enhanced for loop Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/02_Control_Flow/033_Enhanced_for_loop.md)

These exercises follow [033. Enhanced for loop](../../Road%20Map/02_Control_Flow/033_Enhanced_for_loop.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Array values

**Task:** Print every value in an int array.

```java
for (int value : new int[]{2, 4, 6}) { System.out.println(value); }
```

**Expected result:**
```text
2
4
6
```

**Concept:** Enhanced for visits elements without an explicit index.

## Question 2 — List values

**Task:** Print names in list order.

```java
for (String name : Arrays.asList("Ada", "Grace")) { System.out.println(name); }
```

**Expected result:**
```text
Ada
Grace
```

**Concept:** Iterable collections support enhanced for traversal.

## Question 3 — Sum values

**Task:** Sum integers in an array.

```java
int total = 0;
for (int value : new int[]{1, 2, 3}) { total += value; }
System.out.println(total);
```

**Expected result:**
```text
6
```

**Concept:** A local accumulator can summarize visited elements.

## Question 4 — Count matches

**Task:** Count even elements.

```java
int count = 0;
for (int value : new int[]{1, 2, 4, 5}) { if (value % 2 == 0) { count++; } }
System.out.println(count);
```

**Expected result:**
```text
2
```

**Concept:** The body can combine traversal with a predicate.

## Question 5 — Empty input

**Task:** Traverse an empty array and print the visit count.

```java
int count = 0;
for (int value : new int[0]) { count++; }
System.out.println(count);
```

**Expected result:**
```text
0
```

**Concept:** Empty input produces zero iterations.

## Question 6 — Primitive reassignment

**Task:** Show that reassigning the loop variable does not replace array elements.

```java
int[] values = {1, 2};
for (int value : values) { value = 9; }
System.out.println(Arrays.toString(values));
```

**Expected result:**
```text
[1, 2]
```

**Concept:** The loop variable receives a copy of each primitive element value.

## Question 7 — Object mutation

**Task:** Mutate builders reached through an enhanced-for variable.

```java
List<StringBuilder> values = Arrays.asList(new StringBuilder("A"), new StringBuilder("B"));
for (StringBuilder value : values) { value.append("!"); }
System.out.println(values);
```

**Expected result:**
```text
[A!, B!]
```

**Concept:** Copying a reference still permits mutation of the shared object.

## Question 8 — Nested arrays

**Task:** Sum every element of a non-null rectangular matrix.

```java
int[][] matrix = {{1, 2}, {3, 4}}; int total = 0;
for (int[] row : matrix) { for (int value : row) { total += value; } }
System.out.println(total);
```

**Expected result:**
```text
10
```

**Concept:** Nested enhanced loops traverse rows and then values.

## Question 9 — Map entries

**Task:** Print ordered map entries using entrySet.

```java
Map<String, Integer> scores = new LinkedHashMap<>(); scores.put("Ada", 95); scores.put("Grace", 98);
for (Map.Entry<String, Integer> entry : scores.entrySet()) { System.out.println(entry.getKey() + "=" + entry.getValue()); }
```

**Expected result:**
```text
Ada=95
Grace=98
```

**Concept:** A map is traversed through a suitable collection view.

## Question 10 — Nullable elements

**Task:** Ignore null elements before reading string length.

```java
int total = 0;
for (String value : Arrays.asList("Ada", null, "Grace")) { if (value != null) { total += value.length(); } }
System.out.println(total);
```

**Expected result:**
```text
8
```

**Concept:** Traversal does not automatically validate element nullability.

---

## Interview Questions and Answers

### Interview Question 1 — What can enhanced for iterate over?

**Answer:** Arrays and objects implementing Iterable. A Map itself is not Iterable, but its key, value, and entry views are iterable collections.

### Interview Question 2 — Does reassigning the loop variable replace the element?

**Answer:** No. The variable is a local holding the current element value or reference. Use indexed assignment or an appropriate replacement API to replace elements.

### Interview Question 3 — Can a loop variable mutate an object element?

**Answer:** Yes, when the element is mutable. Reassigning a local reference and mutating its referenced object are different operations.

### Interview Question 4 — When is an indexed loop preferable?

**Answer:** When the index, reverse traversal, neighboring positions, or indexed element replacement is part of the task.

### Interview Question 5 — Can you structurally modify an ArrayList directly while enhanced-for traversing it?

**Answer:** Doing so commonly causes ConcurrentModificationException through its iterator. Use an appropriate iterator operation, removeIf, or another deliberate traversal strategy.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Array values | Enhanced for visits elements without an explicit index. |
| List values | Iterable collections support enhanced for traversal. |
| Sum values | A local accumulator can summarize visited elements. |
| Count matches | The body can combine traversal with a predicate. |
| Empty input | Empty input produces zero iterations. |

[← 032. do-while](032_do-while.md) | [Course Index](../README.md) | [034. break →](034_break.md)
