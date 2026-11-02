[← 055. Classes](055_Classes.md) | [Course Index](../README.md) | [057. Fields / attributes →](057_Fields_attributes.md)

# Java Objects Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/056_Objects.md)

These exercises follow [056. Objects](../../Road%20Map/05_Object_Oriented_Programming/056_Objects.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete, independent Main.java. Helper types are nested to keep examples in one file. Compile and run each question separately. Inputs are non-null unless explicitly tested, and arithmetic uses small values.

## Question 1 — Construct an object

**Task:** Construct an object.

```java
public class Main {
static class Box { int value; Box(int value) { this.value=value; } }
public static void main(String[] args) {
Box box=new Box(7); System.out.println(box.value);
}
}
```

**Expected result:**
```text
7
```

**Concept:** new invokes construction and produces a reference.

## Question 2 — Create two distinct objects

**Task:** Create two distinct objects.

```java
public class Main {
static class Box { int value; Box(int value) { this.value=value; } }
public static void main(String[] args) {
System.out.println(new Box(7)==new Box(7));
}
}
```

**Expected result:**
```text
false
```

**Concept:** Separate new expressions create distinct ordinary instances.

## Question 3 — Create an alias

**Task:** Create an alias.

```java
public class Main {
static class Box { int value; Box(int value) { this.value=value; } }
public static void main(String[] args) {
Box first=new Box(7), second=first; System.out.println(first==second);
}
}
```

**Expected result:**
```text
true
```

**Concept:** Reference assignment can alias one object.

## Question 4 — Mutate through an alias

**Task:** Mutate through an alias.

```java
public class Main {
static class Box { int value; Box(int value) { this.value=value; } }
public static void main(String[] args) {
Box first=new Box(7), second=first; second.value=9; System.out.println(first.value);
}
}
```

**Expected result:**
```text
9
```

**Concept:** Aliases observe the same mutable state.

## Question 5 — Rebind an alias

**Task:** Rebind an alias.

```java
public class Main {
static class Box { int value; Box(int value) { this.value=value; } }
public static void main(String[] args) {
Box first=new Box(7), second=first; second=new Box(9); System.out.println(first.value+":"+second.value);
}
}
```

**Expected result:**
```text
7:9
```

**Concept:** Rebinding a variable does not modify its former object.

## Question 6 — Store objects in an array

**Task:** Store objects in an array.

```java
public class Main {
static class Box { int value; Box(int value) { this.value=value; } }
public static void main(String[] args) {
Box[] values={new Box(1),new Box(2)}; System.out.println(values[1].value);
}
}
```

**Expected result:**
```text
2
```

**Concept:** Arrays of objects store references.

## Question 7 — Handle a null object reference

**Task:** Handle a null object reference.

```java
public class Main {
static class Box { int value; Box(int value) { this.value=value; } }
public static void main(String[] args) {
Box box=null; System.out.println(box==null);
}
}
```

**Expected result:**
```text
true
```

**Concept:** null represents no object.

## Question 8 — Use Object as the declared type

**Task:** Use Object as the declared type.

```java
public class Main {
static class Box { int value; Box(int value) { this.value=value; } }
public static void main(String[] args) {
Object value=new Box(7); System.out.println(value instanceof Box);
}
}
```

**Expected result:**
```text
true
```

**Concept:** Declared supertypes can refer to subtype instances.

## Question 9 — Observe default identity equality

**Task:** Observe default identity equality.

```java
public class Main {
static class Box { int value; Box(int value) { this.value=value; } }
public static void main(String[] args) {
Box first=new Box(7), second=new Box(7); System.out.println(first.equals(second));
}
}
```

**Expected result:**
```text
false
```

**Concept:** This class inherits Object.equals, which uses identity.

## Question 10 — Release one reference without invalidating another

**Task:** Release one reference without invalidating another.

```java
public class Main {
static class Box { int value; Box(int value) { this.value=value; } }
public static void main(String[] args) {
Box first=new Box(7), second=first; first=null; System.out.println(second.value);
}
}
```

**Expected result:**
```text
7
```

**Concept:** An object remains accessible through other live references.

---

## Interview Questions and Answers

### Interview Question 1 — What is object identity?

**Answer:** The distinction between one particular runtime instance and another, independent of their field values.

### Interview Question 2 — Does assigning null destroy an object immediately?

**Answer:** No. It changes one reference variable. Reachability and garbage-collection decisions determine later memory reclamation.

### Interview Question 3 — What is aliasing?

**Answer:** Two or more references identify the same object, making mutations visible through each alias.

### Interview Question 4 — How is logical equality defined?

**Answer:** By a class's equals implementation and its contract. Object.equals provides identity equality unless overridden.

### Interview Question 5 — Does copying a reference copy the object's fields?

**Answer:** No. It copies a reference value; independent object state requires an explicit construction or copying policy.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Construct an object | new invokes construction and produces a reference. |
| Create two distinct objects | Separate new expressions create distinct ordinary instances. |
| Create an alias | Reference assignment can alias one object. |
| Mutate through an alias | Aliases observe the same mutable state. |
| Rebind an alias | Rebinding a variable does not modify its former object. |

[← 055. Classes](055_Classes.md) | [Course Index](../README.md) | [057. Fields / attributes →](057_Fields_attributes.md)
