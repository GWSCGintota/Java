[← 046. Arrays](046_Arrays.md) | [Course Index](../README.md) | [048. Accessing array elements →](048_Accessing_array_elements.md)

# Java Creating arrays Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/04_Arrays/047_Creating_arrays.md)

These exercises follow [047. Creating arrays](../../Road%20Map/04_Arrays/047_Creating_arrays.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Allocate by size

**Task:** Allocate a four-element int array.

```java
int[] values = new int[4];
System.out.println(values.length);
```

**Expected result:**
```text
4
```

**Concept:** Allocation fixes the array length.

## Question 2 — Literal initializer

**Task:** Create an array with explicit values.

```java
String[] names = {"Ada", "Grace"};
System.out.println(Arrays.toString(names));
```

**Expected result:**
```text
[Ada, Grace]
```

**Concept:** An initializer determines both values and length.

## Question 3 — Array expression

**Task:** Assign a newly initialized array after declaring the variable.

```java
int[] values;
values = new int[]{2, 4};
System.out.println(Arrays.toString(values));
```

**Expected result:**
```text
[2, 4]
```

**Concept:** A later assignment needs an array-creation expression rather than a bare initializer.

## Question 4 — Computed size

**Task:** Allocate an array from a calculated nonnegative size.

```java
int rows = 2, columns = 3;
int[] values = new int[rows * columns];
System.out.println(values.length);
```

**Expected result:**
```text
6
```

**Concept:** Size expressions are evaluated when the array is created.

## Question 5 — Reference defaults

**Task:** Create a string array and inspect its uninitialized elements.

```java
String[] names = new String[2];
System.out.println(Arrays.toString(names));
```

**Expected result:**
```text
[null, null]
```

**Concept:** Creating a reference array does not construct its elements.

## Question 6 — Boolean defaults

**Task:** Create two boolean components.

```java
System.out.println(Arrays.toString(new boolean[2]));
```

**Expected result:**
```text
[false, false]
```

**Concept:** Primitive components receive their specified defaults.

## Question 7 — Zero size

**Task:** Allocate an empty array.

```java
System.out.println(new int[0].length);
```

**Expected result:**
```text
0
```

**Concept:** A size of zero is supported.

## Question 8 — Negative size

**Task:** Catch a negative allocation size.

```java
int size = -1;
try { int[] values = new int[size]; System.out.println(values.length); } catch (NegativeArraySizeException e) { System.out.println("Negative size"); }
```

**Expected result:**
```text
Negative size
```

**Concept:** Runtime size expressions must not be negative.

## Question 9 — Fill after allocation

**Task:** Allocate three slots and fill them with 7.

```java
int[] values = new int[3];
Arrays.fill(values, 7);
System.out.println(Arrays.toString(values));
```

**Expected result:**
```text
[7, 7, 7]
```

**Concept:** Allocation and application-specific initialization are separate operations.

## Question 10 — Independent allocations

**Task:** Create two arrays of equal length and compare identity.

```java
int[] first = new int[2], second = new int[2];
System.out.println(first == second);
System.out.println(Arrays.equals(first, second));
```

**Expected result:**
```text
false
true
```

**Concept:** Separate allocations can have equal component values without being the same object.

---

## Interview Questions and Answers

### Interview Question 1 — When is an array's length chosen?

**Answer:** At creation, from its size expression or initializer. The object's length then remains fixed.

### Interview Question 2 — Does new User[3] create three User objects?

**Answer:** No. It creates three reference slots, initially null. Construct each User separately when needed.

### Interview Question 3 — Can the array-creation size be calculated at runtime?

**Answer:** Yes, with an integral expression accepted by array-creation rules. Negative results throw NegativeArraySizeException.

### Interview Question 4 — What is the difference between declaration and allocation?

**Answer:** Declaring a reference variable introduces its name and type. Allocating creates an array object, which can then be assigned to that reference.

### Interview Question 5 — Why might allocation fail even for a nonnegative size?

**Answer:** Resource limits or implementation limits can prevent allocation, typically reporting an OutOfMemoryError. Validate domain sizes before attempting unreasonable allocations.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Allocate by size | Allocation fixes the array length. |
| Literal initializer | An initializer determines both values and length. |
| Array expression | A later assignment needs an array-creation expression rather than a bare initializer. |
| Computed size | Size expressions are evaluated when the array is created. |
| Reference defaults | Creating a reference array does not construct its elements. |

[← 046. Arrays](046_Arrays.md) | [Course Index](../README.md) | [048. Accessing array elements →](048_Accessing_array_elements.md)
