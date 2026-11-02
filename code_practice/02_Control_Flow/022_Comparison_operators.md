[← 021. Boolean expressions](021_Boolean_expressions.md) | [Course Index](../README.md) | [023. Logical operators →](023_Logical_operators.md)

# Java Comparison operators Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/02_Control_Flow/022_Comparison_operators.md)

These exercises follow [022. Comparison operators](../../Road%20Map/02_Control_Flow/022_Comparison_operators.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Equal integers

**Task:** Compare two equal integers.

```java
System.out.println(7 == 7);
```

**Expected result:**
```text
true
```

**Concept:** == compares primitive numeric values after applicable promotion.

## Question 2 — Different integers

**Task:** Check whether two integers differ.

```java
System.out.println(7 != 8);
```

**Expected result:**
```text
true
```

**Concept:** != checks primitive inequality.

## Question 3 — Strict lower bound

**Task:** Compare 5 with 5 using >.

```java
System.out.println(5 > 5);
```

**Expected result:**
```text
false
```

**Concept:** A strict comparison excludes equality.

## Question 4 — Inclusive lower bound

**Task:** Compare 5 with 5 using >=.

```java
System.out.println(5 >= 5);
```

**Expected result:**
```text
true
```

**Concept:** An inclusive comparison includes equality.

## Question 5 — Strict upper bound

**Task:** Check whether 4 is less than 5.

```java
System.out.println(4 < 5);
```

**Expected result:**
```text
true
```

**Concept:** < expresses a strict upper constraint.

## Question 6 — Inclusive upper bound

**Task:** Check whether 5 is at most 5.

```java
System.out.println(5 <= 5);
```

**Expected result:**
```text
true
```

**Concept:** <= includes the upper endpoint.

## Question 7 — Reference identity

**Task:** Compare two separately constructed strings by identity and content.

```java
String a = new String("A"), b = new String("A");
System.out.println(a == b);
System.out.println(a.equals(b));
```

**Expected result:**
```text
false
true
```

**Concept:** Reference comparison and logical equality answer different questions.

## Question 8 — Floating-point NaN

**Task:** Compare NaN with itself using == and Double.isNaN.

```java
double value = Double.NaN;
System.out.println(value == value);
System.out.println(Double.isNaN(value));
```

**Expected result:**
```text
false
true
```

**Concept:** NaN has special comparison rules.

## Question 9 — Signed zero

**Task:** Compare positive and negative floating-point zero using ==.

```java
System.out.println(0.0 == -0.0);
```

**Expected result:**
```text
true
```

**Concept:** Numeric equality treats the two signed zeros as equal.

## Question 10 — Ordering without subtraction

**Task:** Compare the minimum and maximum integers safely.

```java
System.out.println(Integer.compare(Integer.MIN_VALUE, Integer.MAX_VALUE) < 0);
```

**Expected result:**
```text
true
```

**Concept:** Comparison helpers avoid overflow from subtraction-based ordering.

---

## Interview Questions and Answers

### Interview Question 1 — How do > and >= differ?

**Answer:** > excludes equality, while >= includes it. Boundary tests should explicitly cover the equal case.

### Interview Question 2 — Does == compare String content?

**Answer:** No. For references it compares identity. Use String.equals or an explicit null-safe equality policy for content.

### Interview Question 3 — Why is comparator subtraction risky?

**Answer:** Subtracting distant integers can overflow and reverse the intended sign. Use Integer.compare or comparator factories.

### Interview Question 4 — Is NaN equal to itself with ==?

**Answer:** No. Use Double.isNaN or Float.isNaN to recognize it.

### Interview Question 5 — Do reference types support < directly?

**Answer:** No. Compare ordered objects through Comparable, Comparator, or domain-specific methods rather than relational reference operators.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Equal integers | == compares primitive numeric values after applicable promotion. |
| Different integers | != checks primitive inequality. |
| Strict lower bound | A strict comparison excludes equality. |
| Inclusive lower bound | An inclusive comparison includes equality. |
| Strict upper bound | < expresses a strict upper constraint. |

[← 021. Boolean expressions](021_Boolean_expressions.md) | [Course Index](../README.md) | [023. Logical operators →](023_Logical_operators.md)
