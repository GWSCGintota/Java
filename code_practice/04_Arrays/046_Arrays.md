[← 045. Pass-by-value in Java](../03_Methods/045_Pass-by-value_in_Java.md) | [Course Index](../README.md) | [047. Creating arrays →](047_Creating_arrays.md)

# Java Arrays Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/04_Arrays/046_Arrays.md)

These exercises follow [046. Arrays](../../Road%20Map/04_Arrays/046_Arrays.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Literal array

**Task:** Create an int array containing 1, 2, and 3 and print it.

```java
int[] values = {1, 2, 3};
System.out.println(Arrays.toString(values));
```

**Expected result:**
```text
[1, 2, 3]
```

**Concept:** An array holds a fixed number of components of one declared component type.

## Question 2 — Array length

**Task:** Print the number of elements in a three-value array.

```java
int[] values = {4, 7, 9};
System.out.println(values.length);
```

**Expected result:**
```text
3
```

**Concept:** length is a field, not a method call.

## Question 3 — Read endpoints

**Task:** Print the first and last values.

```java
int[] values = {4, 7, 9};
System.out.println(values[0] + ":" + values[values.length - 1]);
```

**Expected result:**
```text
4:9
```

**Concept:** Valid indices run from zero to length minus one.

## Question 4 — Update an element

**Task:** Replace the middle value with 8.

```java
int[] values = {1, 2, 3};
values[1] = 8;
System.out.println(Arrays.toString(values));
```

**Expected result:**
```text
[1, 8, 3]
```

**Concept:** Fixed length does not make elements immutable.

## Question 5 — Sum

**Task:** Sum every element in an array.

```java
int total = 0;
for (int value : new int[]{2, 3, 4}) { total += value; }
System.out.println(total);
```

**Expected result:**
```text
9
```

**Concept:** Enhanced for can traverse array components directly.

## Question 6 — Empty array

**Task:** Create a zero-length array and inspect its length.

```java
int[] values = new int[0];
System.out.println(values.length);
```

**Expected result:**
```text
0
```

**Concept:** A zero-length array is valid and distinct from null.

## Question 7 — Default values

**Task:** Create a three-element int array without explicit element values.

```java
System.out.println(Arrays.toString(new int[3]));
```

**Expected result:**
```text
[0, 0, 0]
```

**Concept:** Array components receive type-specific defaults.

## Question 8 — Shared reference

**Task:** Assign an array to a second reference and mutate it.

```java
int[] first = {1, 2};
int[] second = first;
second[0] = 9;
System.out.println(first[0]);
```

**Expected result:**
```text
9
```

**Concept:** Reference assignment shares the array object.

## Question 9 — Independent copy

**Task:** Clone an int array and mutate the clone.

```java
int[] first = {1, 2};
int[] second = first.clone();
second[0] = 9;
System.out.println(first[0] + ":" + second[0]);
```

**Expected result:**
```text
1:9
```

**Concept:** Cloning a primitive array creates independent element storage.

## Question 10 — Out-of-bounds policy

**Task:** Catch an invalid access at index length.

```java
int[] values = {1, 2};
try { System.out.println(values[values.length]); } catch (ArrayIndexOutOfBoundsException e) { System.out.println("Invalid index"); }
```

**Expected result:**
```text
Invalid index
```

**Concept:** Java checks array bounds at runtime.

---

## Interview Questions and Answers

### Interview Question 1 — Can an array change length after creation?

**Answer:** No. Allocate another array or use a resizable collection when the number of elements must grow or shrink.

### Interview Question 2 — Is an array a primitive or reference type?

**Answer:** Every array is an object and has a reference type, even when its components are primitive values.

### Interview Question 3 — What is the first valid index?

**Answer:** Zero for a non-empty array. A zero-length array has no valid element index.

### Interview Question 4 — Does assigning one array variable to another copy elements?

**Answer:** No. It copies the reference. Use clone, Arrays.copyOf, or another explicit copy operation when required.

### Interview Question 5 — How do null and an empty array differ?

**Answer:** null is no array object; an empty array is a valid object with length zero and safe traversal with zero iterations.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Literal array | An array holds a fixed number of components of one declared component type. |
| Array length | length is a field, not a method call. |
| Read endpoints | Valid indices run from zero to length minus one. |
| Update an element | Fixed length does not make elements immutable. |
| Sum | Enhanced for can traverse array components directly. |

[← 045. Pass-by-value in Java](../03_Methods/045_Pass-by-value_in_Java.md) | [Course Index](../README.md) | [047. Creating arrays →](047_Creating_arrays.md)
