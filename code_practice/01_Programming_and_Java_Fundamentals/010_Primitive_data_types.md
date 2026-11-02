[← 009. Constants with final](009_Constants_with_final.md) | [Course Index](../README.md) | [011. Reference data types →](011_Reference_data_types.md)

# Java Primitive data types Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/01_Programming_and_Java_Fundamentals/010_Primitive_data_types.md)

These exercises follow [010. Primitive data types](../../Road%20Map/01_Programming_and_Java_Fundamentals/010_Primitive_data_types.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Eight primitive types

**Task:** Declare and print one value of every primitive type.

```java
byte b = 1; short s = 2; int i = 3; long l = 4L; float f = 1.5F; double d = 2.5; char c = 'A'; boolean ok = true;
System.out.println(b + ":" + s + ":" + i + ":" + l + ":" + f + ":" + d + ":" + c + ":" + ok);
```

**Expected result:**
```text
1:2:3:4:1.5:2.5:A:true
```

**Concept:** Java has eight primitive types with distinct value domains.

## Question 2 — Integer division

**Task:** Compare integer and floating-point division of 5 by 2.

```java
System.out.println(5 / 2);
System.out.println(5 / 2.0);
```

**Expected result:**
```text
2
2.5
```

**Concept:** Operand types determine the arithmetic operation.

## Question 3 — Long literal

**Task:** Store three billion in a long.

```java
long population = 3_000_000_000L;
System.out.println(population);
```

**Expected result:**
```text
3000000000
```

**Concept:** The L suffix makes the literal long.

## Question 4 — Byte promotion

**Task:** Add two bytes and store their sum in an int.

```java
byte a = 100, b = 20;
int total = a + b;
System.out.println(total);
```

**Expected result:**
```text
120
```

**Concept:** Arithmetic promotes byte and short operands to int.

## Question 5 — Character code

**Task:** Print the integer code unit for A.

```java
char letter = 'A';
System.out.println((int) letter);
```

**Expected result:**
```text
65
```

**Concept:** char represents an unsigned UTF-16 code unit, not a complete Unicode character in every case.

## Question 6 — Boolean logic

**Task:** Combine two boolean values.

```java
boolean paid = true, shipped = false;
System.out.println(paid && !shipped);
```

**Expected result:**
```text
true
```

**Concept:** boolean is a logical type rather than an integer alias.

## Question 7 — Integer bounds

**Task:** Print signed int minimum and maximum.

```java
System.out.println(Integer.MIN_VALUE);
System.out.println(Integer.MAX_VALUE);
```

**Expected result:**
```text
-2147483648
2147483647
```

**Concept:** Primitive integer types have fixed ranges.

## Question 8 — Overflow

**Task:** Add one to the maximum int and inspect wrapping.

```java
int value = Integer.MAX_VALUE;
System.out.println(value + 1);
```

**Expected result:**
```text
-2147483648
```

**Concept:** Ordinary integer arithmetic can wrap without throwing.

## Question 9 — Floating-point precision

**Task:** Compare a decimal calculation with its expected decimal literal.

```java
System.out.println(0.1 + 0.2 == 0.3);
```

**Expected result:**
```text
false
```

**Concept:** Binary floating-point cannot represent many decimal fractions exactly.

## Question 10 — Default array values

**Task:** Print an int array's first value and a boolean array's first value.

```java
int[] numbers = new int[1];
boolean[] flags = new boolean[1];
System.out.println(numbers[0]);
System.out.println(flags[0]);
```

**Expected result:**
```text
0
false
```

**Concept:** Array elements receive defaults, unlike unassigned local variables.

---

## Interview Questions and Answers

### Interview Question 1 — What are Java's primitive types?

**Answer:** byte, short, int, long, float, double, char, and boolean. String is a reference type.

### Interview Question 2 — How big is int?

**Answer:** It is a signed 32-bit integer. Its value range is independent of the operating system's native word size.

### Interview Question 3 — Why does byte plus byte produce int?

**Answer:** Binary numeric promotion promotes byte, short, and char operands to int before ordinary integer arithmetic.

### Interview Question 4 — Are floating-point calculations exact decimal arithmetic?

**Answer:** No. float and double use binary floating-point and can round. Use a suitable decimal representation and explicit rounding policy when decimal exactness is required.

### Interview Question 5 — Can primitive variables hold null?

**Answer:** No. Wrapper references such as Integer can be null, and unboxing null throws NullPointerException.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Eight primitive types | Java has eight primitive types with distinct value domains. |
| Integer division | Operand types determine the arithmetic operation. |
| Long literal | The L suffix makes the literal long. |
| Byte promotion | Arithmetic promotes byte and short operands to int. |
| Character code | char represents an unsigned UTF-16 code unit, not a complete Unicode character in every case. |

[← 009. Constants with final](009_Constants_with_final.md) | [Course Index](../README.md) | [011. Reference data types →](011_Reference_data_types.md)
