[← 056. Objects](056_Objects.md) | [Course Index](../README.md) | [058. Methods →](058_Methods.md)

# Java Fields / attributes Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/057_Fields_attributes.md)

These exercises follow [057. Fields / attributes](../../Road%20Map/05_Object_Oriented_Programming/057_Fields_attributes.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete, independent Main.java. Helper types are nested to keep examples in one file. Compile and run each question separately. Inputs are non-null unless explicitly tested, and arithmetic uses small values.

## Question 1 — Read an int field default

**Task:** Read an int field default.

```java
public class Main {
static class State { int count; boolean ready; String name; static int shared; final int id; State(int id) { this.id=id; } }
public static void main(String[] args) {
System.out.println(new State(1).count);
}
}
```

**Expected result:**
```text
0
```

**Concept:** int fields default to zero.

## Question 2 — Read a boolean field default

**Task:** Read a boolean field default.

```java
public class Main {
static class State { int count; boolean ready; String name; static int shared; final int id; State(int id) { this.id=id; } }
public static void main(String[] args) {
System.out.println(new State(1).ready);
}
}
```

**Expected result:**
```text
false
```

**Concept:** boolean fields default to false.

## Question 3 — Read a reference field default

**Task:** Read a reference field default.

```java
public class Main {
static class State { int count; boolean ready; String name; static int shared; final int id; State(int id) { this.id=id; } }
public static void main(String[] args) {
System.out.println(new State(1).name);
}
}
```

**Expected result:**
```text
null
```

**Concept:** Reference fields default to null.

## Question 4 — Assign a field

**Task:** Assign a field.

```java
public class Main {
static class State { int count; boolean ready; String name; static int shared; final int id; State(int id) { this.id=id; } }
public static void main(String[] args) {
State state=new State(1); state.count=7; System.out.println(state.count);
}
}
```

**Expected result:**
```text
7
```

**Concept:** Non-final fields support assignment.

## Question 5 — Increment a field

**Task:** Increment a field.

```java
public class Main {
static class State { int count; boolean ready; String name; static int shared; final int id; State(int id) { this.id=id; } }
public static void main(String[] args) {
State state=new State(1); state.count++; System.out.println(state.count);
}
}
```

**Expected result:**
```text
1
```

**Concept:** Compound updates change stored instance state.

## Question 6 — Compare separate instance fields

**Task:** Compare separate instance fields.

```java
public class Main {
static class State { int count; boolean ready; String name; static int shared; final int id; State(int id) { this.id=id; } }
public static void main(String[] args) {
State a=new State(1),b=new State(2); a.count=9; System.out.println(b.count);
}
}
```

**Expected result:**
```text
0
```

**Concept:** Instance fields belong to individual objects.

## Question 7 — Access a static field through its class

**Task:** Access a static field through its class.

```java
public class Main {
static class State { int count; boolean ready; String name; static int shared; final int id; State(int id) { this.id=id; } }
public static void main(String[] args) {
State.shared=5; System.out.println(State.shared);
}
}
```

**Expected result:**
```text
5
```

**Concept:** A static field is associated with its class.

## Question 8 — Read a constructor-initialized final field

**Task:** Read a constructor-initialized final field.

```java
public class Main {
static class State { int count; boolean ready; String name; static int shared; final int id; State(int id) { this.id=id; } }
public static void main(String[] args) {
System.out.println(new State(42).id);
}
}
```

**Expected result:**
```text
42
```

**Concept:** Blank final fields can be initialized by a constructor.

## Question 9 — Read several fields as a snapshot

**Task:** Read several fields as a snapshot.

```java
public class Main {
static class State { int count; boolean ready; String name; static int shared; final int id; State(int id) { this.id=id; } }
public static void main(String[] args) {
State state=new State(1); state.name="Ada"; state.ready=true; System.out.println(state.name+":"+state.ready);
}
}
```

**Expected result:**
```text
Ada:true
```

**Concept:** Field combinations describe object state at a point in execution.

## Question 10 — Distinguish a local from a field

**Task:** Distinguish a local from a field.

```java
public class Main {
static class State { int count; boolean ready; String name; static int shared; final int id; State(int id) { this.id=id; } }
public static void main(String[] args) {
State state=new State(1); int count=9; System.out.println(count+":"+state.count);
}
}
```

**Expected result:**
```text
9:0
```

**Concept:** A local with the same spelling is not the object's field.

---

## Interview Questions and Answers

### Interview Question 1 — What is a field?

**Answer:** A variable declared as a member of a class or interface, with instance or static ownership depending on its declaration.

### Interview Question 2 — How are fields initialized?

**Answer:** They receive defaults before explicit field initializers and constructor work, subject to class and instance initialization rules.

### Interview Question 3 — How do fields differ from local variables?

**Answer:** Fields belong to objects or classes and receive defaults; locals belong to invocation scopes and need definite assignment before use.

### Interview Question 4 — Should mutable fields normally be public?

**Answer:** Usually hide them behind a focused API so the object can protect invariants and control changes.

### Interview Question 5 — Does a final reference field make its value immutable?

**Answer:** It prevents rebinding that field, but the referenced object can still be mutable.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Read an int field default | int fields default to zero. |
| Read a boolean field default | boolean fields default to false. |
| Read a reference field default | Reference fields default to null. |
| Assign a field | Non-final fields support assignment. |
| Increment a field | Compound updates change stored instance state. |

[← 056. Objects](056_Objects.md) | [Course Index](../README.md) | [058. Methods →](058_Methods.md)
