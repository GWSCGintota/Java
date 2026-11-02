[← 061. this](061_this.md) | [Course Index](../README.md) | [063. final →](063_final.md)

# Java static Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/062_static.md)

These exercises follow [062. static](../../Road%20Map/05_Object_Oriented_Programming/062_static.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete, independent Main.java. Helper types are nested to keep examples in one file. Compile and run each question separately. Inputs are non-null unless explicitly tested, and arithmetic uses small values.

## Question 1 — Read class state without objects

**Task:** Read class state without objects.

```java
public class Main {
static class Counter { static int created; int value; Counter() { created++; } void increment() { value++; } static int count() { return created; } static void reset() { created=0; } }
public static void main(String[] args) {
System.out.println(Counter.count());
}
}
```

**Expected result:**
```text
0
```

**Concept:** Static state exists independently of any particular instance.

## Question 2 — Count constructions

**Task:** Count constructions.

```java
public class Main {
static class Counter { static int created; int value; Counter() { created++; } void increment() { value++; } static int count() { return created; } static void reset() { created=0; } }
public static void main(String[] args) {
new Counter(); new Counter(); System.out.println(Counter.count());
}
}
```

**Expected result:**
```text
2
```

**Concept:** All instances interact with the same class field.

## Question 3 — Separate instance state

**Task:** Separate instance state.

```java
public class Main {
static class Counter { static int created; int value; Counter() { created++; } void increment() { value++; } static int count() { return created; } static void reset() { created=0; } }
public static void main(String[] args) {
Counter a=new Counter(),b=new Counter(); a.increment(); System.out.println(a.value+":"+b.value);
}
}
```

**Expected result:**
```text
1:0
```

**Concept:** Instance fields remain separate even when the class also has static fields.

## Question 4 — Access a static field by class name

**Task:** Access a static field by class name.

```java
public class Main {
static class Counter { static int created; int value; Counter() { created++; } void increment() { value++; } static int count() { return created; } static void reset() { created=0; } }
public static void main(String[] args) {
Counter.created=7; System.out.println(Counter.created);
}
}
```

**Expected result:**
```text
7
```

**Concept:** Class qualification is the clear static-access form.

## Question 5 — Reset shared state

**Task:** Reset shared state.

```java
public class Main {
static class Counter { static int created; int value; Counter() { created++; } void increment() { value++; } static int count() { return created; } static void reset() { created=0; } }
public static void main(String[] args) {
new Counter(); Counter.reset(); System.out.println(Counter.count());
}
}
```

**Expected result:**
```text
0
```

**Concept:** A static method can mutate shared class state.

## Question 6 — Observe class state through two instances

**Task:** Observe class state through two instances.

```java
public class Main {
static class Counter { static int created; int value; Counter() { created++; } void increment() { value++; } static int count() { return created; } static void reset() { created=0; } }
public static void main(String[] args) {
Counter a=new Counter(),b=new Counter(); System.out.println(a.created+":"+b.created);
}
}
```

**Expected result:**
```text
2:2
```

**Concept:** Instance-qualified static access is legal but misleading; prefer Counter.created.

## Question 7 — Combine class and instance state

**Task:** Combine class and instance state.

```java
public class Main {
static class Counter { static int created; int value; Counter() { created++; } void increment() { value++; } static int count() { return created; } static void reset() { created=0; } }
public static void main(String[] args) {
Counter counter=new Counter(); counter.increment(); System.out.println(Counter.count()+":"+counter.value);
}
}
```

**Expected result:**
```text
1:1
```

**Concept:** Class ownership and receiver ownership are distinct.

## Question 8 — Call a static method reference

**Task:** Call a static method reference.

```java
public class Main {
static class Counter { static int created; int value; Counter() { created++; } void increment() { value++; } static int count() { return created; } static void reset() { created=0; } }
public static void main(String[] args) {
new Counter(); java.util.function.IntSupplier supplier=Counter::count; System.out.println(supplier.getAsInt());
}
}
```

**Expected result:**
```text
1
```

**Concept:** Static operations can implement compatible functional targets.

## Question 9 — Avoid assuming static means immutable

**Task:** Avoid assuming static means immutable.

```java
public class Main {
static class Counter { static int created; int value; Counter() { created++; } void increment() { value++; } static int count() { return created; } static void reset() { created=0; } }
public static void main(String[] args) {
Counter.created=3; Counter.created++; System.out.println(Counter.created);
}
}
```

**Expected result:**
```text
4
```

**Concept:** static affects ownership, not mutability.

## Question 10 — Show that reference loss does not reset class state

**Task:** Show that reference loss does not reset class state.

```java
public class Main {
static class Counter { static int created; int value; Counter() { created++; } void increment() { value++; } static int count() { return created; } static void reset() { created=0; } }
public static void main(String[] args) {
Counter counter=new Counter(); counter=null; System.out.println(Counter.count());
}
}
```

**Expected result:**
```text
1
```

**Concept:** Changing an instance variable does not reset static fields.

---

## Interview Questions and Answers

### Interview Question 1 — What does static change about a member?

**Answer:** It associates the member with its class rather than each receiver instance.

### Interview Question 2 — Does static mean one value across every class loader?

**Answer:** No. Separate loaded class identities can have separate static state. Avoid treating a static field as a universal process-wide registry in all environments.

### Interview Question 3 — Are static fields automatically thread-safe?

**Answer:** No. Concurrent access to shared mutable state needs an appropriate safety design.

### Interview Question 4 — Can a static method access an instance field directly?

**Answer:** Not without an explicit receiver reference. It has no implicit this.

### Interview Question 5 — When can shared static state harm tests?

**Answer:** State can leak across tests, making outcomes order-dependent. Prefer explicit dependencies or carefully controlled reset and isolation policies.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Read class state without objects | Static state exists independently of any particular instance. |
| Count constructions | All instances interact with the same class field. |
| Separate instance state | Instance fields remain separate even when the class also has static fields. |
| Access a static field by class name | Class qualification is the clear static-access form. |
| Reset shared state | A static method can mutate shared class state. |

[← 061. this](061_this.md) | [Course Index](../README.md) | [063. final →](063_final.md)
