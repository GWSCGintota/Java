[← 062. static](062_static.md) | [Course Index](../README.md) | [064. Encapsulation →](064_Encapsulation.md)

# Java final Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/063_final.md)

These exercises follow [063. final](../../Road%20Map/05_Object_Oriented_Programming/063_final.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete, independent Main.java. Helper types are nested to keep examples in one file. Compile and run each question separately. Inputs are non-null unless explicitly tested, and arithmetic uses small values.

## Question 1 — Read a class constant

**Task:** Read a class constant.

```java
public class Main {
static final class Settings { static final int LIMIT=3; final int id; final StringBuilder text=new StringBuilder(); Settings(int id) { this.id=id; } final int getId() { return id; } }
public static void main(String[] args) {
System.out.println(Settings.LIMIT);
}
}
```

**Expected result:**
```text
3
```

**Concept:** static final can define shared fixed configuration.

## Question 2 — Initialize a final field

**Task:** Initialize a final field.

```java
public class Main {
static final class Settings { static final int LIMIT=3; final int id; final StringBuilder text=new StringBuilder(); Settings(int id) { this.id=id; } final int getId() { return id; } }
public static void main(String[] args) {
System.out.println(new Settings(7).id);
}
}
```

**Expected result:**
```text
7
```

**Concept:** Constructors can initialize blank final fields.

## Question 3 — Read a final method result

**Task:** Read a final method result.

```java
public class Main {
static final class Settings { static final int LIMIT=3; final int id; final StringBuilder text=new StringBuilder(); Settings(int id) { this.id=id; } final int getId() { return id; } }
public static void main(String[] args) {
System.out.println(new Settings(9).getId());
}
}
```

**Expected result:**
```text
9
```

**Concept:** A final instance method cannot be overridden.

## Question 4 — Mutate behind a final reference

**Task:** Mutate behind a final reference.

```java
public class Main {
static final class Settings { static final int LIMIT=3; final int id; final StringBuilder text=new StringBuilder(); Settings(int id) { this.id=id; } final int getId() { return id; } }
public static void main(String[] args) {
Settings settings=new Settings(1); settings.text.append("A"); System.out.println(settings.text);
}
}
```

**Expected result:**
```text
A
```

**Concept:** final does not freeze referenced object contents.

## Question 5 — Use a final local

**Task:** Use a final local.

```java
public class Main {
static final class Settings { static final int LIMIT=3; final int id; final StringBuilder text=new StringBuilder(); Settings(int id) { this.id=id; } final int getId() { return id; } }
public static void main(String[] args) {
final int value=4; System.out.println(value*2);
}
}
```

**Expected result:**
```text
8
```

**Concept:** Final locals permit one assignment under definite-assignment rules.

## Question 6 — Assign a blank final local by branch

**Task:** Assign a blank final local by branch.

```java
public class Main {
static final class Settings { static final int LIMIT=3; final int id; final StringBuilder text=new StringBuilder(); Settings(int id) { this.id=id; } final int getId() { return id; } }
public static void main(String[] args) {
boolean premium=true; final int limit; if(premium) { limit=10; } else { limit=3; } System.out.println(limit);
}
}
```

**Expected result:**
```text
10
```

**Concept:** Exclusive branches can each initialize a final local.

## Question 7 — Capture a final value

**Task:** Capture a final value.

```java
public class Main {
static final class Settings { static final int LIMIT=3; final int id; final StringBuilder text=new StringBuilder(); Settings(int id) { this.id=id; } final int getId() { return id; } }
public static void main(String[] args) {
final int factor=3; java.util.function.IntUnaryOperator times=n->n*factor; System.out.println(times.applyAsInt(4));
}
}
```

**Expected result:**
```text
12
```

**Concept:** Explicit final is compatible with lambda capture.

## Question 8 — Change a final array component

**Task:** Change a final array component.

```java
public class Main {
static final class Settings { static final int LIMIT=3; final int id; final StringBuilder text=new StringBuilder(); Settings(int id) { this.id=id; } final int getId() { return id; } }
public static void main(String[] args) {
final int[] values={1,2}; values[0]=9; System.out.println(values[0]);
}
}
```

**Expected result:**
```text
9
```

**Concept:** Final reference assignment and mutable component storage are separate restrictions.

## Question 9 — Inspect final class metadata

**Task:** Inspect final class metadata.

```java
public class Main {
static final class Settings { static final int LIMIT=3; final int id; final StringBuilder text=new StringBuilder(); Settings(int id) { this.id=id; } final int getId() { return id; } }
public static void main(String[] args) {
System.out.println(java.lang.reflect.Modifier.isFinal(Settings.class.getModifiers()));
}
}
```

**Expected result:**
```text
true
```

**Concept:** A final class cannot be subclassed.

## Question 10 — Distinguish final values per instance

**Task:** Distinguish final values per instance.

```java
public class Main {
static final class Settings { static final int LIMIT=3; final int id; final StringBuilder text=new StringBuilder(); Settings(int id) { this.id=id; } final int getId() { return id; } }
public static void main(String[] args) {
Settings a=new Settings(1),b=new Settings(2); System.out.println(a.id+":"+b.id);
}
}
```

**Expected result:**
```text
1:2
```

**Concept:** Final instance fields can hold different values on different objects.

---

## Interview Questions and Answers

### Interview Question 1 — What does final mean for a variable, method, and class?

**Answer:** It restricts variable reassignment, method overriding, and subclassing respectively. These are different language constraints.

### Interview Question 2 — Is a final reference an immutable object?

**Answer:** No. The referenced object may still support mutation.

### Interview Question 3 — Is every final value a compile-time constant?

**Answer:** No. Constant-variable rules require a final primitive or String variable initialized with a constant expression.

### Interview Question 4 — Can a final class implement interfaces?

**Answer:** Yes. final prohibits subclassing that class, not implementing contracts within its own declaration.

### Interview Question 5 — How do final fields relate to safe publication?

**Answer:** Correctly constructed objects have special final-field visibility guarantees under the Java Memory Model, but leaking this during construction or mutating referenced state still needs careful design.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Read a class constant | static final can define shared fixed configuration. |
| Initialize a final field | Constructors can initialize blank final fields. |
| Read a final method result | A final instance method cannot be overridden. |
| Mutate behind a final reference | final does not freeze referenced object contents. |
| Use a final local | Final locals permit one assignment under definite-assignment rules. |

[← 062. static](062_static.md) | [Course Index](../README.md) | [064. Encapsulation →](064_Encapsulation.md)
