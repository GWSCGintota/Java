[← 011. Reference data types](011_Reference_data_types.md) | [Course Index](../README.md) | [013. Operators →](013_Operators.md)

# Java Type casting Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/01_Programming_and_Java_Fundamentals/012_Type_casting.md)

These exercises follow [012. Type casting](../../Road%20Map/01_Programming_and_Java_Fundamentals/012_Type_casting.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Widen int to long

**Task:** Widen 42 to long without an explicit cast.

```java
int small = 42;
long wide = small;
System.out.println(wide);
```

**Expected result:**
```text
42
```

**Concept:** Widening integral conversion can be implicit.

## Question 2 — Narrow a decimal

**Task:** Cast 3.9 to int.

```java
System.out.println((int) 3.9);
```

**Expected result:**
```text
3
```

**Concept:** Floating-point to integer conversion discards the fractional part toward zero for this in-range input.

## Question 3 — Negative truncation

**Task:** Cast -3.9 to int.

```java
System.out.println((int) -3.9);
```

**Expected result:**
```text
-3
```

**Concept:** Truncation toward zero differs from mathematical floor.

## Question 4 — Byte narrowing

**Task:** Narrow integer 130 to byte.

```java
int number = 130;
System.out.println((byte) number);
```

**Expected result:**
```text
-126
```

**Concept:** Narrowing an integer to byte retains the low eight bits.

## Question 5 — Fix division timing

**Task:** Compare casting before and after integer division.

```java
int a = 5, b = 2;
System.out.println((double) (a / b));
System.out.println((double) a / b);
```

**Expected result:**
```text
2.0
2.5
```

**Concept:** A cast after integer division cannot restore a discarded fraction.

## Question 6 — Character conversion

**Task:** Convert numeric code unit 65 to char.

```java
System.out.println((char) 65);
```

**Expected result:**
```text
A
```

**Concept:** Numeric-to-char conversion concerns UTF-16 code units.

## Question 7 — Widening precision loss

**Task:** Widen an int that float cannot represent exactly and compare after converting back.

```java
int original = 16_777_217;
float rounded = original;
System.out.println((int) rounded);
```

**Expected result:**
```text
16777216
```

**Concept:** Some widening conversions, such as int to float, can lose precision.

## Question 8 — Safe downcast

**Task:** Check a reference type before casting it.

```java
Object value = "Java";
if (value instanceof String) { System.out.println(((String) value).length()); }
```

**Expected result:**
```text
4
```

**Concept:** A checked type guard can protect a reference downcast.

## Question 9 — Invalid downcast

**Task:** Catch an invalid reference cast.

```java
Object value = Integer.valueOf(7);
try { String text = (String) value; System.out.println(text); } catch (ClassCastException e) { System.out.println("Wrong type"); }
```

**Expected result:**
```text
Wrong type
```

**Concept:** A cast does not transform an Integer object into a String.

## Question 10 — Parse rather than cast

**Task:** Convert numeric text into an integer correctly.

```java
String text = "42";
int value = Integer.parseInt(text);
System.out.println(value + 1);
```

**Expected result:**
```text
43
```

**Concept:** Parsing text is different from a numeric or reference cast.

---

## Interview Questions and Answers

### Interview Question 1 — What is widening conversion?

**Answer:** Conversion to a type with a broader range or supported reference supertype. Numeric widening can still lose precision, such as int to float or long to double.

### Interview Question 2 — What is narrowing conversion?

**Answer:** Conversion to a more restricted numeric representation or a more specific reference type. It may lose information or fail a runtime reference-type check.

### Interview Question 3 — Does a cast create a new object of the target type?

**Answer:** A reference cast does not. It changes the compile-time view and performs any required runtime type check.

### Interview Question 4 — How does casting differ from parsing?

**Answer:** Casting converts supported numeric representations or reference views. Parsing interprets characters according to a format and can fail if the text is invalid.

### Interview Question 5 — Why cast before division when a fractional answer is required?

**Answer:** At least one floating-point operand is needed for floating-point division. Casting the result after integer division happens too late.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Widen int to long | Widening integral conversion can be implicit. |
| Narrow a decimal | Floating-point to integer conversion discards the fractional part toward zero for this in-range input. |
| Negative truncation | Truncation toward zero differs from mathematical floor. |
| Byte narrowing | Narrowing an integer to byte retains the low eight bits. |
| Fix division timing | A cast after integer division cannot restore a discarded fraction. |

[← 011. Reference data types](011_Reference_data_types.md) | [Course Index](../README.md) | [013. Operators →](013_Operators.md)
