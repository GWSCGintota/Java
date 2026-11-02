[← 007. Comments](007_Comments.md) | [Course Index](../README.md) | [009. Constants with final →](009_Constants_with_final.md)

# Java Variables Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/01_Programming_and_Java_Fundamentals/008_Variables.md)

These exercises follow [008. Variables](../../Road%20Map/01_Programming_and_Java_Fundamentals/008_Variables.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Declare a value

**Task:** Declare age as 25 and print it.

```java
int age = 25;
System.out.println(age);
```

**Expected result:**
```text
25
```

**Concept:** A variable has a declared type and a value.

## Question 2 — Reassign a local

**Task:** Initialize score to 10, then change it to 15.

```java
int score = 10;
score = 15;
System.out.println(score);
```

**Expected result:**
```text
15
```

**Concept:** Ordinary local variables can be reassigned.

## Question 3 — Independent copies

**Task:** Copy an integer, change the copy, and print both values.

```java
int first = 5;
int second = first;
second = 9;
System.out.println(first + ":" + second);
```

**Expected result:**
```text
5:9
```

**Concept:** Assigning a primitive copies its value.

## Question 4 — Multiple types

**Task:** Store and print an integer, decimal, and boolean.

```java
int count = 3;
double price = 2.5;
boolean available = true;
System.out.println(count + ":" + price + ":" + available);
```

**Expected result:**
```text
3:2.5:true
```

**Concept:** Types determine permitted values and operations.

## Question 5 — Swap variables

**Task:** Swap two integers with a temporary variable.

```java
int a = 3, b = 8;
int temporary = a; a = b; b = temporary;
System.out.println(a + ":" + b);
```

**Expected result:**
```text
8:3
```

**Concept:** A temporary preserves a value while another variable changes.

## Question 6 — Accumulate a total

**Task:** Accumulate 3, 4, and 5 into a total variable.

```java
int total = 0;
for (int value : new int[]{3, 4, 5}) { total += value; }
System.out.println(total);
```

**Expected result:**
```text
12
```

**Concept:** Initialize accumulators to a suitable starting value.

## Question 7 — Definite assignment

**Task:** Assign a local on both branches before reading it.

```java
int age = 16;
String label;
if (age >= 18) { label = "Adult"; } else { label = "Minor"; }
System.out.println(label);
```

**Expected result:**
```text
Minor
```

**Concept:** A local must be definitely assigned before use.

## Question 8 — Block scope

**Task:** Use distinct variables inside and outside a nested block.

```java
int outer = 1;
{ int inner = 2; System.out.println(outer + inner); }
System.out.println(outer);
```

**Expected result:**
```text
3
1
```

**Concept:** A block-local variable is unavailable outside its scope.

## Question 9 — Shared reference

**Task:** Assign one mutable object reference to another variable and observe mutation.

```java
StringBuilder first = new StringBuilder("A");
StringBuilder second = first;
second.append("B");
System.out.println(first);
```

**Expected result:**
```text
AB
```

**Concept:** Copying a reference can make two variables point to one object.

## Question 10 — Meaningful names

**Task:** Calculate remaining stock using descriptive variable names.

```java
int initialStock = 12;
int soldItems = 5;
int remainingStock = initialStock - soldItems;
System.out.println(remainingStock);
```

**Expected result:**
```text
7
```

**Concept:** Names should explain a value's role and units.

---

## Interview Questions and Answers

### Interview Question 1 — What is a variable?

**Answer:** A typed storage location identified by a name. Its kind and scope determine its lifetime, initialization rules, and permitted access.

### Interview Question 2 — Do local variables receive automatic defaults?

**Answer:** No. A local must be definitely assigned before use. Fields and array elements receive type-specific default values.

### Interview Question 3 — How does primitive assignment differ from reference assignment?

**Answer:** Primitive assignment copies the primitive value. Reference assignment copies a reference value, potentially sharing the referenced object.

### Interview Question 4 — What determines local variable scope?

**Answer:** The declaration position and enclosing block or language construct. A variable is not available everywhere in the program merely because it was declared.

### Interview Question 5 — Does final make an object immutable?

**Answer:** No. final prevents reassignment of that variable, while the referenced object may still support mutation.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Declare a value | A variable has a declared type and a value. |
| Reassign a local | Ordinary local variables can be reassigned. |
| Independent copies | Assigning a primitive copies its value. |
| Multiple types | Types determine permitted values and operations. |
| Swap variables | A temporary preserves a value while another variable changes. |

[← 007. Comments](007_Comments.md) | [Course Index](../README.md) | [009. Constants with final →](009_Constants_with_final.md)
