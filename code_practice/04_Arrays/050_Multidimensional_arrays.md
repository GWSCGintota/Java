[← 049. Iterating through arrays](049_Iterating_through_arrays.md) | [Course Index](../README.md) | [051. Arrays of objects →](051_Arrays_of_objects.md)

# Java Multidimensional arrays Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/04_Arrays/050_Multidimensional_arrays.md)

These exercises follow [050. Multidimensional arrays](../../Road%20Map/04_Arrays/050_Multidimensional_arrays.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Rectangular matrix

**Task:** Create a 2-by-3 int matrix and inspect dimensions.

```java
int[][] matrix = new int[2][3];
System.out.println(matrix.length + ":" + matrix[0].length);
```

**Expected result:**
```text
2:3
```

**Concept:** A Java multidimensional array is an array whose components are arrays.

## Question 2 — Read a cell

**Task:** Read row one, column zero.

```java
int[][] matrix = {{1, 2}, {3, 4}};
System.out.println(matrix[1][0]);
```

**Expected result:**
```text
3
```

**Concept:** Each dimension uses its own zero-based index.

## Question 3 — Update a cell

**Task:** Replace one matrix component.

```java
int[][] matrix = {{1, 2}, {3, 4}};
matrix[0][1] = 9;
System.out.println(Arrays.deepToString(matrix));
```

**Expected result:**
```text
[[1, 9], [3, 4]]
```

**Concept:** A cell assignment accesses its row and then that row's component.

## Question 4 — Jagged rows

**Task:** Create rows with different lengths and print those lengths.

```java
int[][] matrix = {{1}, {2, 3, 4}};
System.out.println(matrix[0].length + ":" + matrix[1].length);
```

**Expected result:**
```text
1:3
```

**Concept:** Rows need not have equal lengths.

## Question 5 — Null rows

**Task:** Allocate only the outer dimension and inspect a row reference.

```java
int[][] matrix = new int[2][];
System.out.println(matrix[0]);
```

**Expected result:**
```text
null
```

**Concept:** Unallocated inner arrays remain null references.

## Question 6 — Sum matrix

**Task:** Sum all components of a non-null jagged matrix.

```java
int[][] matrix = {{1, 2}, {3}}; int total = 0;
for (int[] row : matrix) { for (int value : row) { total += value; } }
System.out.println(total);
```

**Expected result:**
```text
6
```

**Concept:** Use each row's actual structure during traversal.

## Question 7 — Skip missing rows

**Task:** Sum a matrix containing a null row.

```java
int[][] matrix = {{1, 2}, null, {3}}; int total = 0;
for (int[] row : matrix) { if (row == null) { continue; } for (int value : row) { total += value; } }
System.out.println(total);
```

**Expected result:**
```text
6
```

**Concept:** Row nullability requires its own policy.

## Question 8 — Transpose

**Task:** Transpose a fixed rectangular 2-by-3 matrix.

```java
int[][] matrix = {{1, 2, 3}, {4, 5, 6}}, transpose = new int[3][2];
for (int r = 0; r < matrix.length; r++) { for (int c = 0; c < matrix[r].length; c++) { transpose[c][r] = matrix[r][c]; } }
System.out.println(Arrays.deepToString(transpose));
```

**Expected result:**
```text
[[1, 4], [2, 5], [3, 6]]
```

**Concept:** Transposition swaps row and column positions for a rectangular matrix.

## Question 9 — Shallow outer clone

**Task:** Clone the outer array and show that an inner row is shared.

```java
int[][] original = {{1, 2}};
int[][] copy = original.clone();
copy[0][0] = 9;
System.out.println(original[0][0]);
```

**Expected result:**
```text
9
```

**Concept:** Cloning an outer reference array does not clone its row objects.

## Question 10 — Copy primitive rows

**Task:** Clone each non-null row for independent primitive component storage.

```java
int[][] original = {{1, 2}, {3}}, copy = new int[original.length][];
for (int r = 0; r < original.length; r++) { copy[r] = original[r] == null ? null : original[r].clone(); }
copy[0][0] = 9;
System.out.println(original[0][0] + ":" + copy[0][0]);
```

**Expected result:**
```text
1:9
```

**Concept:** Copy depth must match the nested mutable structure.

---

## Interview Questions and Answers

### Interview Question 1 — Are Java two-dimensional arrays always rectangular?

**Answer:** No. They are arrays of arrays; rows can have different lengths or be null.

### Interview Question 2 — What does new int[3][] allocate?

**Answer:** An outer array of three int-array references, initially null, without allocating the inner arrays.

### Interview Question 3 — How do length values relate to dimensions?

**Answer:** outer.length counts rows; outer[row].length counts that particular row's components after validating the row reference.

### Interview Question 4 — Does clone deeply copy a matrix?

**Answer:** No. Cloning the outer array copies its row references. Clone primitive rows separately if independent primitive component storage is required.

### Interview Question 5 — What assumptions does a simple transpose require?

**Answer:** A rectangular non-null matrix with a defined empty-dimension policy. Jagged or missing rows need additional semantics rather than blindly using the first row's length.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Rectangular matrix | A Java multidimensional array is an array whose components are arrays. |
| Read a cell | Each dimension uses its own zero-based index. |
| Update a cell | A cell assignment accesses its row and then that row's component. |
| Jagged rows | Rows need not have equal lengths. |
| Null rows | Unallocated inner arrays remain null references. |

[← 049. Iterating through arrays](049_Iterating_through_arrays.md) | [Course Index](../README.md) | [051. Arrays of objects →](051_Arrays_of_objects.md)
