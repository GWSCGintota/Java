[← 042. Local vs instance variables](042_Local_vs_instance_variables.md) | [Course Index](../README.md) | [044. Recursion →](044_Recursion.md)

# Java Static methods Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/03_Methods/043_Static_methods.md)

These exercises follow [043. Static methods](../../Road%20Map/03_Methods/043_Static_methods.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each model solution is a complete Main.java. Compile and run it independently. Arithmetic examples use small values; inputs are non-null unless explicitly tested. Standard-library classes are fully qualified when needed.

## Question 1 — Static utility

**Task:** Define and call a static square helper.

```java
public class Main {
    static int square(int n) { return n*n; }
    public static void main(String[] args) {
        System.out.println(Main.square(5));
    }
}
```

**Expected result:**
```text
25
```

**Concept:** A static method can be called through the class name.

## Question 2 — Static-to-static call

**Task:** Call one static helper from another.

```java
public class Main {
    static int twice(int n) { return n*2; } static int result(int n) { return twice(n)+1; }
    public static void main(String[] args) {
        System.out.println(result(4));
    }
}
```

**Expected result:**
```text
9
```

**Concept:** Static methods in the same class can directly invoke one another.

## Question 3 — Read static state

**Task:** Return a static field from a static helper.

```java
public class Main {
    static int count=3; static int count() { return count; }
    public static void main(String[] args) {
        System.out.println(count());
    }
}
```

**Expected result:**
```text
3
```

**Concept:** Static methods can access static members directly.

## Question 4 — Explicit instance

**Task:** Read instance state from a static helper through a supplied object.

```java
public class Main {
    int value=7; static int read(Main object) { return object.value; }
    public static void main(String[] args) {
        System.out.println(read(new Main()));
    }
}
```

**Expected result:**
```text
7
```

**Concept:** Static code needs a receiver for instance-field access.

## Question 5 — No instance required

**Task:** Call a static factory returning a new instance.

```java
public class Main {
    int value; Main(int value) { this.value=value; } static Main of(int value) { return new Main(value); }
    public static void main(String[] args) {
        System.out.println(Main.of(9).value);
    }
}
```

**Expected result:**
```text
9
```

**Concept:** A static factory can create and return objects.

## Question 6 — Method reference

**Task:** Use a static helper as an IntUnaryOperator.

```java
public class Main {
    static int triple(int n) { return n*3; }
    public static void main(String[] args) {
        java.util.function.IntUnaryOperator operator=Main::triple; System.out.println(operator.applyAsInt(4));
    }
}
```

**Expected result:**
```text
12
```

**Concept:** Static methods can supply compatible functional behavior.

## Question 7 — Static overloading

**Task:** Overload static methods for int and String.

```java
public class Main {
    static String kind(int n) { return "int"; } static String kind(String n) { return "String"; }
    public static void main(String[] args) {
        System.out.println(kind(1)); System.out.println(kind("A"));
    }
}
```

**Expected result:**
```text
int
String
```

**Concept:** Static methods participate in overload resolution.

## Question 8 — Static hiding

**Task:** Show class-based selection for hidden static methods.

```java
public class Main {
    static class Base { static String name() { return "Base"; } } static class Child extends Base { static String name() { return "Child"; } }
    public static void main(String[] args) {
        Base value=new Child(); System.out.println(value.name()); System.out.println(Child.name());
    }
}
```

**Expected result:**
```text
Base
Child
```

**Concept:** Static methods are hidden, not dynamically overridden; prefer class-qualified calls.

## Question 9 — Instance to static

**Task:** Call a static helper from an instance method.

```java
public class Main {
    static int twice(int n) { return n*2; } int result() { return Main.twice(3); }
    public static void main(String[] args) {
        System.out.println(new Main().result());
    }
}
```

**Expected result:**
```text
6
```

**Concept:** An instance method can call an accessible static method.

## Question 10 — Validate inputs

**Task:** Write a stateless static helper with an explicit null contract.

```java
public class Main {
    static int length(String text) { return java.util.Objects.requireNonNull(text,"text").length(); }
    public static void main(String[] args) {
        System.out.println(length("Java"));
    }
}
```

**Expected result:**
```text
4
```

**Concept:** Static does not remove the need to define input behavior.

---

## Interview Questions and Answers

### Interview Question 1 — Does a static method have this?

**Answer:** No. It has no implicit receiver instance. Access instance members through an explicit object reference.

### Interview Question 2 — Can static methods be overloaded?

**Answer:** Yes. Distinct parameter signatures can form overloads just as for instance methods.

### Interview Question 3 — Can static methods be overridden?

**Answer:** They are hidden by class declarations rather than dynamically overridden. Selection depends on the compile-time class reference used.

### Interview Question 4 — Does static mean thread-safe?

**Answer:** No. A static method can access unsafe shared mutable state. Safety depends on its data and synchronization design.

### Interview Question 5 — When is a static helper appropriate?

**Answer:** For an operation that does not depend on receiver-specific state or polymorphic instance behavior, such as a focused utility or factory.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Static utility | A static method can be called through the class name. |
| Static-to-static call | Static methods in the same class can directly invoke one another. |
| Read static state | Static methods can access static members directly. |
| Explicit instance | Static code needs a receiver for instance-field access. |
| No instance required | A static factory can create and return objects. |

[← 042. Local vs instance variables](042_Local_vs_instance_variables.md) | [Course Index](../README.md) | [044. Recursion →](044_Recursion.md)
