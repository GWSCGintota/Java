[← 008. Variables](008_Variables.md) | [Course Index](../README.md) | [010. Primitive data types →](010_Primitive_data_types.md)

# Java Constants with final Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/01_Programming_and_Java_Fundamentals/009_Constants_with_final.md)

These exercises follow [009. Constants with final](../../Road%20Map/01_Programming_and_Java_Fundamentals/009_Constants_with_final.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Local constant

**Task:** Declare a final tax rate and use it with a small amount.

```java
final double TAX_RATE = 0.1;
System.out.println(100 * TAX_RATE);
```

**Expected result:**
```text
10.0
```

**Concept:** A final variable cannot be assigned again after initialization.

## Question 2 — Named class constant

**Task:** Define a public static final maximum and print it.

```java
public class Main { public static final int MAX_ATTEMPTS = 3; public static void main(String[] args) { System.out.println(MAX_ATTEMPTS); } }
```

**Expected result:**
```text
3
```

**Concept:** A static constant belongs to the class.

## Question 3 — Blank final local

**Task:** Assign an uninitialized final local on both branches.

```java
boolean premium = true;
final int limit;
if (premium) { limit = 10; } else { limit = 3; }
System.out.println(limit);
```

**Expected result:**
```text
10
```

**Concept:** Definite assignment permits one assignment on each exclusive path.

## Question 4 — Final parameter

**Task:** Use a final parameter in a helper.

```java
public class Main { static int twice(final int value) { return value * 2; } public static void main(String[] args) { System.out.println(twice(4)); } }
```

**Expected result:**
```text
8
```

**Concept:** final parameters cannot be reassigned inside their method.

## Question 5 — Mutable final reference

**Task:** Append to a builder held by a final variable.

```java
final StringBuilder text = new StringBuilder("A");
text.append("B");
System.out.println(text);
```

**Expected result:**
```text
AB
```

**Concept:** The reference stays fixed while the object changes.

## Question 6 — Final array contents

**Task:** Change an element in a final array.

```java
final int[] values = {1, 2};
values[0] = 9;
System.out.println(Arrays.toString(values));
```

**Expected result:**
```text
[9, 2]
```

**Concept:** final does not freeze array elements.

## Question 7 — Constructor initialization

**Task:** Initialize a final field in a constructor.

```java
public class Main { private final int id; Main(int id) { this.id = id; } public static void main(String[] args) { System.out.println(new Main(7).id); } }
```

**Expected result:**
```text
7
```

**Concept:** Constructors can initialize blank final instance fields.

## Question 8 — Derived constant

**Task:** Define a seconds-per-minute constant and calculate seconds in three minutes.

```java
final int SECONDS_PER_MINUTE = 60;
System.out.println(3 * SECONDS_PER_MINUTE);
```

**Expected result:**
```text
180
```

**Concept:** Named constants make units and intent visible.

## Question 9 — Runtime final value

**Task:** Initialize a final local using a method call.

```java
final int length = "Java".length();
System.out.println(length);
```

**Expected result:**
```text
4
```

**Concept:** A final variable need not be a compile-time constant.

## Question 10 — Repair reassignment

**Task:** Replace an attempt to increment a final limit with a separate counter.

```java
final int LIMIT = 3;
int attempts = 0;
while (attempts < LIMIT) { attempts++; }
System.out.println(attempts);
```

**Expected result:**
```text
3
```

**Concept:** Separate fixed configuration from changing execution state.

---

## Interview Questions and Answers

### Interview Question 1 — What does final mean for a variable?

**Answer:** It can be assigned only once under definite-assignment rules. For a reference variable, that restricts reassignment of the reference, not object mutation.

### Interview Question 2 — What is a constant variable in the language specification?

**Answer:** A final variable of primitive type or String initialized with a constant expression. Not every final variable qualifies.

### Interview Question 3 — Why use static final for shared constants?

**Answer:** static associates the field with the class, and final prevents reassignment after initialization. Public compile-time constants may be inlined into compiled clients.

### Interview Question 4 — Can a blank final field be assigned in a constructor?

**Answer:** Yes, provided every constructor path that completes normally initializes it exactly once, directly or through constructor chaining.

### Interview Question 5 — How does final differ for classes and methods?

**Answer:** A final class cannot be subclassed; a final method cannot be overridden. Those are distinct restrictions from final variable assignment.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Local constant | A final variable cannot be assigned again after initialization. |
| Named class constant | A static constant belongs to the class. |
| Blank final local | Definite assignment permits one assignment on each exclusive path. |
| Final parameter | final parameters cannot be reassigned inside their method. |
| Mutable final reference | The reference stays fixed while the object changes. |

[← 008. Variables](008_Variables.md) | [Course Index](../README.md) | [010. Primitive data types →](010_Primitive_data_types.md)
