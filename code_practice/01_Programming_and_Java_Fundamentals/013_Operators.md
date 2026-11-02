[← 012. Type casting](012_Type_casting.md) | [Course Index](../README.md) | [014. User input with Scanner →](014_User_input_with_Scanner.md)

# Java Operators Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/01_Programming_and_Java_Fundamentals/013_Operators.md)

These exercises follow [013. Operators](../../Road%20Map/01_Programming_and_Java_Fundamentals/013_Operators.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Arithmetic

**Task:** Calculate sum, difference, product, quotient, and remainder for 10 and 3.

```java
int a = 10, b = 3;
System.out.println((a+b) + ":" + (a-b) + ":" + (a*b) + ":" + (a/b) + ":" + (a%b));
```

**Expected result:**
```text
13:7:30:3:1
```

**Concept:** Integer division truncates and remainder records the residual value.

## Question 2 — Precedence

**Task:** Compare 2+3*4 with the parenthesized sum first.

```java
System.out.println(2 + 3 * 4);
System.out.println((2 + 3) * 4);
```

**Expected result:**
```text
14
20
```

**Concept:** Multiplication binds more tightly than addition.

## Question 3 — Comparison

**Task:** Check whether an age is at least 18.

```java
int age = 18;
System.out.println(age >= 18);
```

**Expected result:**
```text
true
```

**Concept:** Comparison produces a boolean value.

## Question 4 — Short-circuit guard

**Task:** Safely check that a nullable string is non-empty.

```java
String text = null;
System.out.println(text != null && !text.isEmpty());
```

**Expected result:**
```text
false
```

**Concept:** && skips the right operand when the left is false.

## Question 5 — Conditional operator

**Task:** Choose the larger of two numbers with ?: .

```java
int a = 7, b = 4;
System.out.println(a > b ? a : b);
```

**Expected result:**
```text
7
```

**Concept:** The conditional operator selects one of two expressions.

## Question 6 — Prefix and postfix

**Task:** Compare postfix and prefix increment on separate variables.

```java
int a = 2, b = 2;
System.out.println(a++);
System.out.println(++b);
System.out.println(a + ":" + b);
```

**Expected result:**
```text
2
3
3:3
```

**Concept:** Postfix yields the old value; prefix yields the updated value.

## Question 7 — Compound assignment

**Task:** Accumulate into a total using += .

```java
int total = 5;
total += 3;
System.out.println(total);
```

**Expected result:**
```text
8
```

**Concept:** Compound assignment reads, operates, and assigns to its left-hand variable.

## Question 8 — Bit operations

**Task:** Compute bitwise AND and OR for 6 and 3.

```java
System.out.println(6 & 3);
System.out.println(6 | 3);
```

**Expected result:**
```text
2
7
```

**Concept:** Bitwise operations act on corresponding integer bits.

## Question 9 — Shift

**Task:** Shift positive integer 3 left by two places.

```java
System.out.println(3 << 2);
```

**Expected result:**
```text
12
```

**Concept:** Left shift moves bits and can overflow; this small positive case equals multiplication by four.

## Question 10 — Concatenation order

**Task:** Compare string-first concatenation with a parenthesized numeric sum.

```java
System.out.println("Total: " + 2 + 3);
System.out.println("Total: " + (2 + 3));
```

**Expected result:**
```text
Total: 23
Total: 5
```

**Concept:** + performs string concatenation when a string operand participates.

---

## Interview Questions and Answers

### Interview Question 1 — How do == and equals differ?

**Answer:** == compares primitive values or reference identity. equals is a method for logical equality when a class defines it appropriately.

### Interview Question 2 — How do && and & differ with booleans?

**Answer:** Both compute logical AND, but && short-circuits while & evaluates both operands. With integers, & performs bitwise AND.

### Interview Question 3 — What is the difference between ++x and x++?

**Answer:** Both increment x; prefix yields the new value and postfix yields the previous value. Avoid complicated expressions that depend on these side effects.

### Interview Question 4 — Is integer division the same as floating-point division?

**Answer:** No. Integer division truncates toward zero. A floating-point operand changes the calculation to floating-point division.

### Interview Question 5 — Can ordinary integer arithmetic overflow?

**Answer:** Yes. Most integer operations wrap within the type's representation. Use exact arithmetic methods or a larger representation when the domain requires detection or a wider range.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Arithmetic | Integer division truncates and remainder records the residual value. |
| Precedence | Multiplication binds more tightly than addition. |
| Comparison | Comparison produces a boolean value. |
| Short-circuit guard | && skips the right operand when the left is false. |
| Conditional operator | The conditional operator selects one of two expressions. |

[← 012. Type casting](012_Type_casting.md) | [Course Index](../README.md) | [014. User input with Scanner →](014_User_input_with_Scanner.md)
