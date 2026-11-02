[← 035. continue](../02_Control_Flow/035_continue.md) | [Course Index](../README.md) | [037. Calling methods →](037_Calling_methods.md)

# Java Creating methods Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/03_Methods/036_Creating_methods.md)

These exercises follow [036. Creating methods](../../Road%20Map/03_Methods/036_Creating_methods.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each model solution is a complete Main.java. Compile and run it independently. Arithmetic examples use small values; inputs are non-null unless explicitly tested. Standard-library classes are fully qualified when needed.

## Question 1 — No arguments

**Task:** Define a method returning a fixed greeting.

```java
public class Main {
    static String greet() { return "Hello"; }
    public static void main(String[] args) {
        System.out.println(greet());
    }
}
```

**Expected result:**
```text
Hello
```

**Concept:** A method declaration specifies its result type and body.

## Question 2 — One parameter

**Task:** Define a method returning an integer square.

```java
public class Main {
    static int square(int number) { return number * number; }
    public static void main(String[] args) {
        System.out.println(square(5));
    }
}
```

**Expected result:**
```text
25
```

**Concept:** Parameters provide inputs to reusable behavior.

## Question 3 — Two parameters

**Task:** Define a method adding two integers.

```java
public class Main {
    static int add(int a, int b) { return a + b; }
    public static void main(String[] args) {
        System.out.println(add(3, 4));
    }
}
```

**Expected result:**
```text
7
```

**Concept:** Parameter order and types form part of the method contract.

## Question 4 — Void result

**Task:** Define a method printing a supplied name.

```java
public class Main {
    static void greet(String name) { System.out.println("Hello, " + name); }
    public static void main(String[] args) {
        greet("Ada");
    }
}
```

**Expected result:**
```text
Hello, Ada
```

**Concept:** void indicates that no result value is returned.

## Question 5 — Boolean result

**Task:** Define a method checking whether an integer is even.

```java
public class Main {
    static boolean isEven(int n) { return n % 2 == 0; }
    public static void main(String[] args) {
        System.out.println(isEven(6));
    }
}
```

**Expected result:**
```text
true
```

**Concept:** A boolean helper can name a condition.

## Question 6 — Local calculation

**Task:** Define a rectangle-area method using a local result.

```java
public class Main {
    static int area(int width, int height) { int result = width * height; return result; }
    public static void main(String[] args) {
        System.out.println(area(3, 5));
    }
}
```

**Expected result:**
```text
15
```

**Concept:** Method locals belong to each invocation.

## Question 7 — Conditional return

**Task:** Define a method returning the larger integer.

```java
public class Main {
    static int max(int a, int b) { if (a >= b) { return a; } return b; }
    public static void main(String[] args) {
        System.out.println(max(4, 9));
    }
}
```

**Expected result:**
```text
9
```

**Concept:** Every normally completing path of a value method must produce a compatible result.

## Question 8 — Compose helpers

**Task:** Define double and increment helpers and combine calls.

```java
public class Main {
    static int twice(int n) { return n * 2; } static int increment(int n) { return n + 1; }
    public static void main(String[] args) {
        System.out.println(increment(twice(5)));
    }
}
```

**Expected result:**
```text
11
```

**Concept:** A method result can become another method argument.

## Question 9 — Validate a contract

**Task:** Reject negative ages inside an adulthood-checking method.

```java
public class Main {
    static boolean isAdult(int age) { if (age < 0) { throw new IllegalArgumentException("Negative age"); } return age >= 18; }
    public static void main(String[] args) {
        try { isAdult(-1); } catch (IllegalArgumentException e) { System.out.println(e.getMessage()); }
    }
}
```

**Expected result:**
```text
Negative age
```

**Concept:** A helper should define invalid-input behavior.

## Question 10 — Instance method

**Task:** Define an instance greeting method and call it through an object.

```java
public class Main {
    String greet(String name) { return "Hello, " + name; }
    public static void main(String[] args) {
        System.out.println(new Main().greet("Grace"));
    }
}
```

**Expected result:**
```text
Hello, Grace
```

**Concept:** Instance methods require a receiver.

---

## Interview Questions and Answers

### Interview Question 1 — What is a method signature?

**Answer:** For ordinary Java overload resolution, a method signature includes its name and parameter types, with additional rules for generic signatures; the return type alone does not distinguish overloads.

### Interview Question 2 — Where can ordinary methods be declared?

**Answer:** Inside a class or interface, subject to the applicable declaration rules. Ordinary Java methods cannot be declared directly inside another method body.

### Interview Question 3 — What does void mean?

**Answer:** The method returns no result value. It can still perform actions and can use a bare return to exit.

### Interview Question 4 — Why extract a method?

**Answer:** To give a focused operation a meaningful name, reuse its behavior, define a contract, and reduce complexity at call sites.

### Interview Question 5 — Can a method modify caller-visible state?

**Answer:** Yes, through accessible mutable objects or fields. Java still passes all argument values by value.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| No arguments | A method declaration specifies its result type and body. |
| One parameter | Parameters provide inputs to reusable behavior. |
| Two parameters | Parameter order and types form part of the method contract. |
| Void result | void indicates that no result value is returned. |
| Boolean result | A boolean helper can name a condition. |

[← 035. continue](../02_Control_Flow/035_continue.md) | [Course Index](../README.md) | [037. Calling methods →](037_Calling_methods.md)
