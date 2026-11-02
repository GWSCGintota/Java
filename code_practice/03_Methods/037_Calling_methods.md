[← 036. Creating methods](036_Creating_methods.md) | [Course Index](../README.md) | [038. Parameters →](038_Parameters.md)

# Java Calling methods Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/03_Methods/037_Calling_methods.md)

These exercises follow [037. Calling methods](../../Road%20Map/03_Methods/037_Calling_methods.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each model solution is a complete Main.java. Compile and run it independently. Arithmetic examples use small values; inputs are non-null unless explicitly tested. Standard-library classes are fully qualified when needed.

## Question 1 — Direct static call

**Task:** Call a static helper from main.

```java
public class Main {
    static int answer() { return 42; }
    public static void main(String[] args) {
        System.out.println(answer());
    }
}
```

**Expected result:**
```text
42
```

**Concept:** A static helper in the same class can be called directly.

## Question 2 — Class-qualified call

**Task:** Call a helper using its declaring class name.

```java
public class Main {
    static int twice(int n) { return n * 2; }
    public static void main(String[] args) {
        System.out.println(Main.twice(7));
    }
}
```

**Expected result:**
```text
14
```

**Concept:** Class qualification makes static ownership explicit.

## Question 3 — Instance receiver

**Task:** Invoke a non-static helper through a new instance.

```java
public class Main {
    int triple(int n) { return n * 3; }
    public static void main(String[] args) {
        System.out.println(new Main().triple(4));
    }
}
```

**Expected result:**
```text
12
```

**Concept:** Instance invocation supplies an object receiver.

## Question 4 — Store a result

**Task:** Store a returned value before printing it.

```java
public class Main {
    static int add(int a, int b) { return a + b; }
    public static void main(String[] args) {
        int result = add(2, 5); System.out.println(result);
    }
}
```

**Expected result:**
```text
7
```

**Concept:** The call expression evaluates to the method result.

## Question 5 — Nested calls

**Task:** Pass the result of one call to another.

```java
public class Main {
    static int twice(int n) { return n * 2; }
    public static void main(String[] args) {
        System.out.println(twice(twice(3)));
    }
}
```

**Expected result:**
```text
12
```

**Concept:** Inner calls complete before their results become outer arguments.

## Question 6 — Argument evaluation

**Task:** Show left-to-right argument evaluation with two helper calls.

```java
public class Main {
    static int next(int n) { System.out.print(n); return n; } static int add(int a, int b) { return a+b; }
    public static void main(String[] args) {
        System.out.println(":" + add(next(1), next(2)));
    }
}
```

**Expected result:**
```text
12:3
```

**Concept:** Java evaluates argument expressions from left to right.

## Question 7 — Repeated calls

**Task:** Call a pure square helper with two different values.

```java
public class Main {
    static int square(int n) { return n*n; }
    public static void main(String[] args) {
        System.out.println(square(2)); System.out.println(square(3));
    }
}
```

**Expected result:**
```text
4
9
```

**Concept:** Each invocation has its own parameter values.

## Question 8 — Void invocation

**Task:** Call an action without assigning a result.

```java
public class Main {
    static void printReady() { System.out.println("Ready"); }
    public static void main(String[] args) {
        printReady();
    }
}
```

**Expected result:**
```text
Ready
```

**Concept:** A void call is an action rather than a result expression.

## Question 9 — Null receiver

**Task:** Catch a failure when invoking an instance method on null.

```java
public class Main {
    int value() { return 7; }
    public static void main(String[] args) {
        Main receiver = null; try { System.out.println(receiver.value()); } catch (NullPointerException e) { System.out.println("Missing receiver"); }
    }
}
```

**Expected result:**
```text
Missing receiver
```

**Concept:** Instance invocation requires a non-null receiver.

## Question 10 — Dynamic dispatch

**Task:** Invoke an overridden method through a superclass reference.

```java
public class Main {
    static class Base { String name() { return "Base"; } } static class Child extends Base { @Override String name() { return "Child"; } }
    public static void main(String[] args) {
        Base value = new Child(); System.out.println(value.name());
    }
}
```

**Expected result:**
```text
Child
```

**Concept:** Overridden instance-method dispatch uses the runtime receiver class.

---

## Interview Questions and Answers

### Interview Question 1 — What happens before a method body runs?

**Answer:** The receiver expression, when applicable, and arguments are evaluated under Java rules, and the invocation is resolved and dispatched appropriately.

### Interview Question 2 — What order are arguments evaluated in?

**Answer:** Left to right. Avoid relying on complicated side effects even though the order is defined.

### Interview Question 3 — How do static and instance calls differ?

**Answer:** A static method belongs to a class and is not dynamically overridden; an instance call has a receiver and can dispatch to an override.

### Interview Question 4 — Can the result of a non-void call be ignored?

**Answer:** Yes, a method invocation can be used as an expression statement and its result discarded. Do so only when the action is what matters.

### Interview Question 5 — What is a call stack?

**Answer:** The execution stack of active method invocations. Each call has a frame for invocation state; deep recursion can exhaust stack capacity.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Direct static call | A static helper in the same class can be called directly. |
| Class-qualified call | Class qualification makes static ownership explicit. |
| Instance receiver | Instance invocation supplies an object receiver. |
| Store a result | The call expression evaluates to the method result. |
| Nested calls | Inner calls complete before their results become outer arguments. |

[← 036. Creating methods](036_Creating_methods.md) | [Course Index](../README.md) | [038. Parameters →](038_Parameters.md)
