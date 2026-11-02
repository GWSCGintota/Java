[← 060. Constructor overloading](060_Constructor_overloading.md) | [Course Index](../README.md) | [062. static →](062_static.md)

# Java this Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/061_this.md)

These exercises follow [061. this](../../Road%20Map/05_Object_Oriented_Programming/061_this.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete, independent Main.java. Helper types are nested to keep examples in one file. Compile and run each question separately. Inputs are non-null unless explicitly tested, and arithmetic uses small values.

## Question 1 — Disambiguate a constructor parameter

**Task:** Disambiguate a constructor parameter.

```java
public class Main {
static class Box { int value; Box() { this(0); } Box(int value) { this.value=value; } Box set(int value) { this.value=value; return this; } int get() { return this.value; } Box self() { return this; } }
public static void main(String[] args) {
System.out.println(new Box(7).get());
}
}
```

**Expected result:**
```text
7
```

**Concept:** this.value names the instance field hidden by value.

## Question 2 — Disambiguate a setter parameter

**Task:** Disambiguate a setter parameter.

```java
public class Main {
static class Box { int value; Box() { this(0); } Box(int value) { this.value=value; } Box set(int value) { this.value=value; return this; } int get() { return this.value; } Box self() { return this; } }
public static void main(String[] args) {
Box box=new Box(); box.set(9); System.out.println(box.get());
}
}
```

**Expected result:**
```text
9
```

**Concept:** Explicit receiver qualification distinguishes fields from parameters.

## Question 3 — Delegate a constructor

**Task:** Delegate a constructor.

```java
public class Main {
static class Box { int value; Box() { this(0); } Box(int value) { this.value=value; } Box set(int value) { this.value=value; return this; } int get() { return this.value; } Box self() { return this; } }
public static void main(String[] args) {
System.out.println(new Box().get());
}
}
```

**Expected result:**
```text
0
```

**Concept:** this(...) invokes another constructor in the same class.

## Question 4 — Return the current receiver

**Task:** Return the current receiver.

```java
public class Main {
static class Box { int value; Box() { this(0); } Box(int value) { this.value=value; } Box set(int value) { this.value=value; return this; } int get() { return this.value; } Box self() { return this; } }
public static void main(String[] args) {
Box box=new Box(1); System.out.println(box.self()==box);
}
}
```

**Expected result:**
```text
true
```

**Concept:** this is the current receiver reference.

## Question 5 — Chain mutating methods

**Task:** Chain mutating methods.

```java
public class Main {
static class Box { int value; Box() { this(0); } Box(int value) { this.value=value; } Box set(int value) { this.value=value; return this; } int get() { return this.value; } Box self() { return this; } }
public static void main(String[] args) {
System.out.println(new Box().set(2).set(5).get());
}
}
```

**Expected result:**
```text
5
```

**Concept:** Returning this enables fluent mutation of one object.

## Question 6 — Show independent receiver identity

**Task:** Show independent receiver identity.

```java
public class Main {
static class Box { int value; Box() { this(0); } Box(int value) { this.value=value; } Box set(int value) { this.value=value; return this; } int get() { return this.value; } Box self() { return this; } }
public static void main(String[] args) {
Box a=new Box(),b=new Box(); System.out.println(a.self()==b.self());
}
}
```

**Expected result:**
```text
false
```

**Concept:** Each object invocation has its own current receiver.

## Question 7 — Read a field through this

**Task:** Read a field through this.

```java
public class Main {
static class Box { int value; Box() { this(0); } Box(int value) { this.value=value; } Box set(int value) { this.value=value; return this; } int get() { return this.value; } Box self() { return this; } }
public static void main(String[] args) {
Box box=new Box(4); System.out.println(box.get());
}
}
```

**Expected result:**
```text
4
```

**Concept:** this can explicitly qualify instance members even without shadowing.

## Question 8 — Observe fluent identity

**Task:** Observe fluent identity.

```java
public class Main {
static class Box { int value; Box() { this(0); } Box(int value) { this.value=value; } Box set(int value) { this.value=value; return this; } int get() { return this.value; } Box self() { return this; } }
public static void main(String[] args) {
Box box=new Box(); System.out.println(box.set(3)==box);
}
}
```

**Expected result:**
```text
true
```

**Concept:** Fluent methods need not allocate a replacement object.

## Question 9 — Mutate through a returned receiver

**Task:** Mutate through a returned receiver.

```java
public class Main {
static class Box { int value; Box() { this(0); } Box(int value) { this.value=value; } Box set(int value) { this.value=value; return this; } int get() { return this.value; } Box self() { return this; } }
public static void main(String[] args) {
Box box=new Box(1); Box alias=box.self(); alias.set(8); System.out.println(box.get());
}
}
```

**Expected result:**
```text
8
```

**Concept:** Returning this can expose the same mutable object to callers.

## Question 10 — Separate chained construction from later mutation

**Task:** Separate chained construction from later mutation.

```java
public class Main {
static class Box { int value; Box() { this(0); } Box(int value) { this.value=value; } Box set(int value) { this.value=value; return this; } int get() { return this.value; } Box self() { return this; } }
public static void main(String[] args) {
Box box=new Box(7); Box alias=box.set(9); System.out.println(alias.get()+":"+box.get());
}
}
```

**Expected result:**
```text
9:9
```

**Concept:** Chained mutation has shared-reference effects.

---

## Interview Questions and Answers

### Interview Question 1 — What does this mean?

**Answer:** The current receiver instance in an instance context. It is not available as an instance reference in a static method.

### Interview Question 2 — Why write this.field = field?

**Answer:** The parameter hides the field name, so this.field explicitly selects the instance member.

### Interview Question 3 — What does this(...) mean in a constructor?

**Answer:** It delegates to another constructor of the same class. These Java 8 exercises place constructor delegation first.

### Interview Question 4 — Does return this create an object?

**Answer:** No. It returns the existing receiver reference, which is useful for fluent APIs but can expose mutable state.

### Interview Question 5 — How does a lambda use this?

**Answer:** A lambda uses the enclosing meaning of this, while an anonymous class has its own instance meaning of this.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Disambiguate a constructor parameter | this.value names the instance field hidden by value. |
| Disambiguate a setter parameter | Explicit receiver qualification distinguishes fields from parameters. |
| Delegate a constructor | this(...) invokes another constructor in the same class. |
| Return the current receiver | this is the current receiver reference. |
| Chain mutating methods | Returning this enables fluent mutation of one object. |

[← 060. Constructor overloading](060_Constructor_overloading.md) | [Course Index](../README.md) | [062. static →](062_static.md)
