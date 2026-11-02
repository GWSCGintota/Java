[← 072. Inheritance](072_Inheritance.md) | [Course Index](../README.md) | [074. Method overriding →](074_Method_overriding.md)

# Java extends Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/073_extends.md)

These exercises follow [073. extends](../../Road%20Map/05_Object_Oriented_Programming/073_extends.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete Main.java with nested helper types. Compile and run each independently. Inputs are non-null unless explicitly tested; arithmetic uses small values. Nested fixtures keep examples focused and do not replace cross-package access exercises.

## Question 1 — Extend a concrete base class

**Task:** Extend a concrete base class.

```java
public class Main {
static class Base { int value; Base(int value) { this.value=value; } int read() { return value; } } static class Middle extends Base { Middle(int value) { super(value); } int doubled() { return read()*2; } } static class Leaf extends Middle { Leaf(int value) { super(value); } }
public static void main(String[] args) {
System.out.println(new Middle(3).read());
}
}
```

**Expected result:**
```text
3
```

**Concept:** extends establishes class inheritance.

## Question 2 — Add behavior in a subclass

**Task:** Add behavior in a subclass.

```java
public class Main {
static class Base { int value; Base(int value) { this.value=value; } int read() { return value; } } static class Middle extends Base { Middle(int value) { super(value); } int doubled() { return read()*2; } } static class Leaf extends Middle { Leaf(int value) { super(value); } }
public static void main(String[] args) {
System.out.println(new Middle(3).doubled());
}
}
```

**Expected result:**
```text
6
```

**Concept:** A subclass can combine inherited operations with new behavior.

## Question 3 — Use multilevel inheritance

**Task:** Use multilevel inheritance.

```java
public class Main {
static class Base { int value; Base(int value) { this.value=value; } int read() { return value; } } static class Middle extends Base { Middle(int value) { super(value); } int doubled() { return read()*2; } } static class Leaf extends Middle { Leaf(int value) { super(value); } }
public static void main(String[] args) {
System.out.println(new Leaf(4).read());
}
}
```

**Expected result:**
```text
4
```

**Concept:** Inheritance can span more than one generation.

## Question 4 — Call a middle-level method from a leaf

**Task:** Call a middle-level method from a leaf.

```java
public class Main {
static class Base { int value; Base(int value) { this.value=value; } int read() { return value; } } static class Middle extends Base { Middle(int value) { super(value); } int doubled() { return read()*2; } } static class Leaf extends Middle { Leaf(int value) { super(value); } }
public static void main(String[] args) {
System.out.println(new Leaf(4).doubled());
}
}
```

**Expected result:**
```text
8
```

**Concept:** Accessible inherited methods can reach deeper descendants.

## Question 5 — Upcast to the root type

**Task:** Upcast to the root type.

```java
public class Main {
static class Base { int value; Base(int value) { this.value=value; } int read() { return value; } } static class Middle extends Base { Middle(int value) { super(value); } int doubled() { return read()*2; } } static class Leaf extends Middle { Leaf(int value) { super(value); } }
public static void main(String[] args) {
Base value=new Leaf(5); System.out.println(value.read());
}
}
```

**Expected result:**
```text
5
```

**Concept:** A subtype reference can widen to a superclass type.

## Question 6 — Check an intermediate type

**Task:** Check an intermediate type.

```java
public class Main {
static class Base { int value; Base(int value) { this.value=value; } int read() { return value; } } static class Middle extends Base { Middle(int value) { super(value); } int doubled() { return read()*2; } } static class Leaf extends Middle { Leaf(int value) { super(value); } }
public static void main(String[] args) {
Base value=new Leaf(5); System.out.println(value instanceof Middle);
}
}
```

**Expected result:**
```text
true
```

**Concept:** A leaf object is also an instance of intermediate superclasses.

## Question 7 — Inspect the direct superclass

**Task:** Inspect the direct superclass.

```java
public class Main {
static class Base { int value; Base(int value) { this.value=value; } int read() { return value; } } static class Middle extends Base { Middle(int value) { super(value); } int doubled() { return read()*2; } } static class Leaf extends Middle { Leaf(int value) { super(value); } }
public static void main(String[] args) {
System.out.println(Leaf.class.getSuperclass().getSimpleName());
}
}
```

**Expected result:**
```text
Middle
```

**Concept:** getSuperclass identifies the direct parent, not the entire chain.

## Question 8 — Inspect root membership

**Task:** Inspect root membership.

```java
public class Main {
static class Base { int value; Base(int value) { this.value=value; } int read() { return value; } } static class Middle extends Base { Middle(int value) { super(value); } int doubled() { return read()*2; } } static class Leaf extends Middle { Leaf(int value) { super(value); } }
public static void main(String[] args) {
System.out.println(Base.class.isAssignableFrom(Leaf.class));
}
}
```

**Expected result:**
```text
true
```

**Concept:** Assignability describes a compatible reference-type relationship.

## Question 9 — Keep base instances distinct from descendants

**Task:** Keep base instances distinct from descendants.

```java
public class Main {
static class Base { int value; Base(int value) { this.value=value; } int read() { return value; } } static class Middle extends Base { Middle(int value) { super(value); } int doubled() { return read()*2; } } static class Leaf extends Middle { Leaf(int value) { super(value); } }
public static void main(String[] args) {
Base value=new Base(1); System.out.println(value instanceof Middle);
}
}
```

**Expected result:**
```text
false
```

**Concept:** A superclass instance is not automatically an instance of every subtype.

## Question 10 — Use inherited mutable state

**Task:** Use inherited mutable state.

```java
public class Main {
static class Base { int value; Base(int value) { this.value=value; } int read() { return value; } } static class Middle extends Base { Middle(int value) { super(value); } int doubled() { return read()*2; } } static class Leaf extends Middle { Leaf(int value) { super(value); } }
public static void main(String[] args) {
Leaf leaf=new Leaf(3); leaf.value=7; System.out.println(leaf.doubled());
}
}
```

**Expected result:**
```text
14
```

**Concept:** Inherited operations read the same superclass portion of that leaf object.

---

## Interview Questions and Answers

### Interview Question 1 — What does extends mean for a class?

**Answer:** It declares its superclass. An ordinary class without an explicit superclass extends Object unless special language rules apply.

### Interview Question 2 — Can a class extend two classes?

**Answer:** No. Use one superclass and interfaces or composition to combine additional contracts and collaborators.

### Interview Question 3 — Can an interface extend other interfaces?

**Answer:** Yes, an interface can extend multiple interfaces, subject to compatible inherited contracts and conflict rules.

### Interview Question 4 — What is multilevel inheritance?

**Answer:** A chain such as Leaf extends Middle extends Base. Each direct superclass remains a separate type relationship.

### Interview Question 5 — Why avoid deep hierarchies without clear need?

**Answer:** They increase coupling and make inherited behavior and invariants harder to track. Prefer a hierarchy that represents a stable behavioral contract.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Extend a concrete base class | extends establishes class inheritance. |
| Add behavior in a subclass | A subclass can combine inherited operations with new behavior. |
| Use multilevel inheritance | Inheritance can span more than one generation. |
| Call a middle-level method from a leaf | Accessible inherited methods can reach deeper descendants. |
| Upcast to the root type | A subtype reference can widen to a superclass type. |

[← 072. Inheritance](072_Inheritance.md) | [Course Index](../README.md) | [074. Method overriding →](074_Method_overriding.md)
