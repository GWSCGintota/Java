[← 047. Creating arrays](047_Creating_arrays.md) | [Course Index](../README.md) | [049. Iterating through arrays →](049_Iterating_through_arrays.md)

# Java Accessing array elements Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/04_Arrays/048_Accessing_array_elements.md)

These exercises follow [048. Accessing array elements](../../Road%20Map/04_Arrays/048_Accessing_array_elements.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — First element

**Task:** Read index zero of an int array.

```java
int[] values = {7, 8};
System.out.println(values[0]);
```

**Expected result:**
```text
7
```

**Concept:** Array indices start at zero.

## Question 2 — Last element

**Task:** Read the last element using length.

```java
int[] values = {7, 8, 9};
System.out.println(values[values.length - 1]);
```

**Expected result:**
```text
9
```

**Concept:** The last valid index is length minus one.

## Question 3 — Replace a value

**Task:** Assign 20 to index one.

```java
int[] values = {1, 2, 3};
values[1] = 20;
System.out.println(Arrays.toString(values));
```

**Expected result:**
```text
[1, 20, 3]
```

**Concept:** Indexed access can denote a writable component.

## Question 4 — Increment in place

**Task:** Increment the first component.

```java
int[] values = {4};
values[0]++;
System.out.println(values[0]);
```

**Expected result:**
```text
5
```

**Concept:** Compound updates can target array components.

## Question 5 — Negative index

**Task:** Catch an access at -1.

```java
int[] values = {1};
try { System.out.println(values[-1]); } catch (ArrayIndexOutOfBoundsException e) { System.out.println("Invalid index"); }
```

**Expected result:**
```text
Invalid index
```

**Concept:** Negative array indices are invalid in Java.

## Question 6 — Upper boundary

**Task:** Catch an access exactly at length.

```java
int[] values = {1};
try { System.out.println(values[1]); } catch (ArrayIndexOutOfBoundsException e) { System.out.println("Invalid index"); }
```

**Expected result:**
```text
Invalid index
```

**Concept:** An exclusive upper bound is a common source of indexing bugs.

## Question 7 — Guard an empty array

**Task:** Print Empty without accessing index zero.

```java
int[] values = {};
System.out.println(values.length == 0 ? "Empty" : values[0]);
```

**Expected result:**
```text
Empty
```

**Concept:** Check length before endpoint access.

## Question 8 — Guard a nullable array

**Task:** Validate the reference before reading length.

```java
int[] values = null;
System.out.println(values == null ? "Missing" : values.length);
```

**Expected result:**
```text
Missing
```

**Concept:** Reading length also dereferences the array object.

## Question 9 — Validate a supplied index

**Task:** Read a component only when its index is valid.

```java
int[] values = {3, 6}; int index = 1;
if (index >= 0 && index < values.length) { System.out.println(values[index]); }
```

**Expected result:**
```text
6
```

**Concept:** Index validity requires both lower and upper constraints.

## Question 10 — Swap endpoints

**Task:** Swap the first and last components of a non-empty array.

```java
int[] values = {1, 2, 3};
int last = values.length - 1, temporary = values[0];
values[0] = values[last]; values[last] = temporary;
System.out.println(Arrays.toString(values));
```

**Expected result:**
```text
[3, 2, 1]
```

**Concept:** Preserve an overwritten component with a temporary during a swap.

---

## Interview Questions and Answers

### Interview Question 1 — What exceptions can array access produce?

**Answer:** NullPointerException for a null array reference and ArrayIndexOutOfBoundsException for an index outside the valid range, among other failures in evaluating expressions.

### Interview Question 2 — Is the array length an index?

**Answer:** No. It counts components. For a non-empty array, the highest valid index is length minus one.

### Interview Question 3 — Can an array index be negative to read from the end?

**Answer:** No. Java does not use that convention; calculate a valid index explicitly.

### Interview Question 4 — Does reading a primitive component return an alias to its slot?

**Answer:** No. It yields the primitive value. A copied local can change without changing the component unless assigned back.

### Interview Question 5 — Why validate both index bounds?

**Answer:** An upper-bound-only check still allows negative values, while a lower-bound-only check allows positions at or beyond length.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| First element | Array indices start at zero. |
| Last element | The last valid index is length minus one. |
| Replace a value | Indexed access can denote a writable component. |
| Increment in place | Compound updates can target array components. |
| Negative index | Negative array indices are invalid in Java. |

[← 047. Creating arrays](047_Creating_arrays.md) | [Course Index](../README.md) | [049. Iterating through arrays →](049_Iterating_through_arrays.md)
