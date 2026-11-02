[← 018. StringBuilder](018_StringBuilder.md) | [Course Index](../README.md) | [020. Random numbers →](020_Random_numbers.md)

# Java Math functions Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/01_Programming_and_Java_Fundamentals/019_Math_functions.md)

These exercises follow [019. Math functions](../../Road%20Map/01_Programming_and_Java_Fundamentals/019_Math_functions.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Absolute value

**Task:** Print the absolute value of -7.

```java
System.out.println(Math.abs(-7));
```

**Expected result:**
```text
7
```

**Concept:** abs computes magnitude for ordinary in-range inputs.

## Question 2 — Minimum and maximum

**Task:** Select the smaller and larger of 3 and 9.

```java
System.out.println(Math.min(3, 9));
System.out.println(Math.max(3, 9));
```

**Expected result:**
```text
3
9
```

**Concept:** min and max express a two-value selection rule.

## Question 3 — Power

**Task:** Calculate two cubed with Math.pow.

```java
System.out.println(Math.pow(2, 3));
```

**Expected result:**
```text
8.0
```

**Concept:** pow returns double even when the result is mathematically integral.

## Question 4 — Square root

**Task:** Calculate the square root of 81.

```java
System.out.println(Math.sqrt(81));
```

**Expected result:**
```text
9.0
```

**Concept:** sqrt returns a floating-point result.

## Question 5 — Floor and ceiling

**Task:** Compare floor and ceil for 2.3.

```java
System.out.println(Math.floor(2.3));
System.out.println(Math.ceil(2.3));
```

**Expected result:**
```text
2.0
3.0
```

**Concept:** Floor rounds toward negative infinity; ceiling rounds toward positive infinity.

## Question 6 — Negative rounding

**Task:** Compare truncation and floor for -2.3.

```java
System.out.println((int) -2.3);
System.out.println(Math.floor(-2.3));
```

**Expected result:**
```text
-2
-3.0
```

**Concept:** Truncation toward zero is not the same as floor.

## Question 7 — Round

**Task:** Round 2.4 and 2.5 with Math.round.

```java
System.out.println(Math.round(2.4));
System.out.println(Math.round(2.5));
```

**Expected result:**
```text
2
3
```

**Concept:** Math.round(double) returns a long using its documented tie rule.

## Question 8 — Exact arithmetic

**Task:** Detect overflowing integer addition.

```java
try { System.out.println(Math.addExact(Integer.MAX_VALUE, 1)); } catch (ArithmeticException e) { System.out.println("Overflow"); }
```

**Expected result:**
```text
Overflow
```

**Concept:** Exact arithmetic methods throw instead of silently wrapping.

## Question 9 — Hypotenuse

**Task:** Calculate the hypotenuse for sides 3 and 4.

```java
System.out.println(Math.hypot(3, 4));
```

**Expected result:**
```text
5.0
```

**Concept:** hypot is designed to avoid unnecessary intermediate overflow or underflow.

## Question 10 — Absolute-value boundary

**Task:** Compare int absolute value with a widened calculation for Integer.MIN_VALUE.

```java
System.out.println(Math.abs(Integer.MIN_VALUE));
System.out.println(Math.abs((long) Integer.MIN_VALUE));
```

**Expected result:**
```text
-2147483648
2147483648
```

**Concept:** The positive magnitude of the minimum int is not representable as int; widen before taking abs.

---

## Interview Questions and Answers

### Interview Question 1 — Does Math.pow return an integer?

**Answer:** No. It returns double. Use arithmetic appropriate to the required representation and overflow policy rather than blindly casting a power result.

### Interview Question 2 — How do floor, ceil, and round differ?

**Answer:** floor rounds toward negative infinity, ceil toward positive infinity, and round uses its documented nearest-integer rule with ties toward positive infinity.

### Interview Question 3 — Why can Math.abs return a negative integer?

**Answer:** The minimum signed integer has no positive counterpart in the same type. Its absolute value overflows in ordinary abs.

### Interview Question 4 — When should exact arithmetic methods be used?

**Answer:** When overflow must be reported rather than wrapped, and the integral type's range otherwise fits the domain.

### Interview Question 5 — What does sqrt return for a negative finite input?

**Answer:** NaN in double arithmetic. Floating-point domain failures do not always throw exceptions.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Absolute value | abs computes magnitude for ordinary in-range inputs. |
| Minimum and maximum | min and max express a two-value selection rule. |
| Power | pow returns double even when the result is mathematically integral. |
| Square root | sqrt returns a floating-point result. |
| Floor and ceiling | Floor rounds toward negative infinity; ceiling rounds toward positive infinity. |

[← 018. StringBuilder](018_StringBuilder.md) | [Course Index](../README.md) | [020. Random numbers →](020_Random_numbers.md)
