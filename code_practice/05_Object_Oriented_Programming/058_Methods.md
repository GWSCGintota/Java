[← 057. Fields / attributes](057_Fields_attributes.md) | [Course Index](../README.md) | [059. Constructors →](059_Constructors.md)

# Java Methods Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/058_Methods.md)

These exercises follow [058. Methods](../../Road%20Map/05_Object_Oriented_Programming/058_Methods.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete, independent Main.java. Helper types are nested to keep examples in one file. Compile and run each question separately. Inputs are non-null unless explicitly tested, and arithmetic uses small values.

## Question 1 — Call a void method

**Task:** Call a void method.

```java
public class Main {
static class Counter { private int value; void increment() { value++; } void add(int amount) { value+=amount; } int get() { return value; } boolean positive() { return value>0; } void reset() { value=0; } static int twice(int n) { return n*2; } }
public static void main(String[] args) {
Counter counter=new Counter(); counter.increment(); System.out.println(counter.get());
}
}
```

**Expected result:**
```text
1
```

**Concept:** An action method can mutate receiver state without returning a result.

## Question 2 — Pass a method argument

**Task:** Pass a method argument.

```java
public class Main {
static class Counter { private int value; void increment() { value++; } void add(int amount) { value+=amount; } int get() { return value; } boolean positive() { return value>0; } void reset() { value=0; } static int twice(int n) { return n*2; } }
public static void main(String[] args) {
Counter counter=new Counter(); counter.add(5); System.out.println(counter.get());
}
}
```

**Expected result:**
```text
5
```

**Concept:** Arguments configure a particular invocation.

## Question 3 — Read a method result

**Task:** Read a method result.

```java
public class Main {
static class Counter { private int value; void increment() { value++; } void add(int amount) { value+=amount; } int get() { return value; } boolean positive() { return value>0; } void reset() { value=0; } static int twice(int n) { return n*2; } }
public static void main(String[] args) {
System.out.println(new Counter().get());
}
}
```

**Expected result:**
```text
0
```

**Concept:** Accessor methods can expose selected state.

## Question 4 — Use a boolean query

**Task:** Use a boolean query.

```java
public class Main {
static class Counter { private int value; void increment() { value++; } void add(int amount) { value+=amount; } int get() { return value; } boolean positive() { return value>0; } void reset() { value=0; } static int twice(int n) { return n*2; } }
public static void main(String[] args) {
Counter counter=new Counter(); counter.add(2); System.out.println(counter.positive());
}
}
```

**Expected result:**
```text
true
```

**Concept:** Query methods express conditions.

## Question 5 — Call a method repeatedly

**Task:** Call a method repeatedly.

```java
public class Main {
static class Counter { private int value; void increment() { value++; } void add(int amount) { value+=amount; } int get() { return value; } boolean positive() { return value>0; } void reset() { value=0; } static int twice(int n) { return n*2; } }
public static void main(String[] args) {
Counter counter=new Counter(); counter.increment(); counter.increment(); System.out.println(counter.get());
}
}
```

**Expected result:**
```text
2
```

**Concept:** Method effects can accumulate across invocations.

## Question 6 — Reset state

**Task:** Reset state.

```java
public class Main {
static class Counter { private int value; void increment() { value++; } void add(int amount) { value+=amount; } int get() { return value; } boolean positive() { return value>0; } void reset() { value=0; } static int twice(int n) { return n*2; } }
public static void main(String[] args) {
Counter counter=new Counter(); counter.add(7); counter.reset(); System.out.println(counter.get());
}
}
```

**Expected result:**
```text
0
```

**Concept:** A focused mutation can define a state transition.

## Question 7 — Call a static utility

**Task:** Call a static utility.

```java
public class Main {
static class Counter { private int value; void increment() { value++; } void add(int amount) { value+=amount; } int get() { return value; } boolean positive() { return value>0; } void reset() { value=0; } static int twice(int n) { return n*2; } }
public static void main(String[] args) {
System.out.println(Counter.twice(6));
}
}
```

**Expected result:**
```text
12
```

**Concept:** A static operation does not need a Counter receiver.

## Question 8 — Combine results

**Task:** Combine results.

```java
public class Main {
static class Counter { private int value; void increment() { value++; } void add(int amount) { value+=amount; } int get() { return value; } boolean positive() { return value>0; } void reset() { value=0; } static int twice(int n) { return n*2; } }
public static void main(String[] args) {
Counter a=new Counter(),b=new Counter(); a.add(2); b.add(3); System.out.println(a.get()+b.get());
}
}
```

**Expected result:**
```text
5
```

**Concept:** Returned values can participate in expressions.

## Question 9 — Use independent receivers

**Task:** Use independent receivers.

```java
public class Main {
static class Counter { private int value; void increment() { value++; } void add(int amount) { value+=amount; } int get() { return value; } boolean positive() { return value>0; } void reset() { value=0; } static int twice(int n) { return n*2; } }
public static void main(String[] args) {
Counter a=new Counter(),b=new Counter(); a.increment(); System.out.println(a.get()+":"+b.get());
}
}
```

**Expected result:**
```text
1:0
```

**Concept:** The receiver determines which instance state is accessed.

## Question 10 — Guard an absent receiver

**Task:** Guard an absent receiver.

```java
public class Main {
static class Counter { private int value; void increment() { value++; } void add(int amount) { value+=amount; } int get() { return value; } boolean positive() { return value>0; } void reset() { value=0; } static int twice(int n) { return n*2; } }
public static void main(String[] args) {
Counter counter=null; System.out.println(counter==null ? "Missing" : counter.get());
}
}
```

**Expected result:**
```text
Missing
```

**Concept:** An instance call needs a non-null receiver.

---

## Interview Questions and Answers

### Interview Question 1 — How do commands and queries differ?

**Answer:** A query returns information; a command performs an action or changes state. Keeping their intent clear improves API reasoning.

### Interview Question 2 — Can a method both mutate and return a result?

**Answer:** Yes. The language permits it, but the contract should make both effects clear.

### Interview Question 3 — What is a receiver?

**Answer:** The object on which an instance method is invoked, represented by this inside instance-context code.

### Interview Question 4 — How do parameters differ from fields?

**Answer:** Parameters are invocation-local inputs; fields hold object or class state that can survive multiple invocations.

### Interview Question 5 — When should behavior become an instance method?

**Answer:** When it naturally belongs to and operates on receiver-specific state or participates in polymorphic behavior.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Call a void method | An action method can mutate receiver state without returning a result. |
| Pass a method argument | Arguments configure a particular invocation. |
| Read a method result | Accessor methods can expose selected state. |
| Use a boolean query | Query methods express conditions. |
| Call a method repeatedly | Method effects can accumulate across invocations. |

[← 057. Fields / attributes](057_Fields_attributes.md) | [Course Index](../README.md) | [059. Constructors →](059_Constructors.md)
