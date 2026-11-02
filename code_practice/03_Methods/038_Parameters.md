[← 037. Calling methods](037_Calling_methods.md) | [Course Index](../README.md) | [039. Return values →](039_Return_values.md)

# Java Parameters Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/03_Methods/038_Parameters.md)

These exercises follow [038. Parameters](../../Road%20Map/03_Methods/038_Parameters.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each model solution is a complete Main.java. Compile and run it independently. Arithmetic examples use small values; inputs are non-null unless explicitly tested. Standard-library classes are fully qualified when needed.

## Question 1 — One argument

**Task:** Pass a learner name to a greeting method.

```java
public class Main {
    static String greet(String name) { return "Hello, " + name; }
    public static void main(String[] args) {
        System.out.println(greet("Ada"));
    }
}
```

**Expected result:**
```text
Hello, Ada
```

**Concept:** A parameter is the local name receiving an argument value.

## Question 2 — Argument order

**Task:** Subtract the second argument from the first in both orders.

```java
public class Main {
    static int subtract(int first, int second) { return first-second; }
    public static void main(String[] args) {
        System.out.println(subtract(8,3)); System.out.println(subtract(3,8));
    }
}
```

**Expected result:**
```text
5
-5
```

**Concept:** Positional argument order matters.

## Question 3 — Primitive copy

**Task:** Reassign an int parameter and show the caller value is unchanged.

```java
public class Main {
    static void replace(int value) { value = 99; }
    public static void main(String[] args) {
        int value = 5; replace(value); System.out.println(value);
    }
}
```

**Expected result:**
```text
5
```

**Concept:** A primitive argument is copied into the parameter.

## Question 4 — Mutable input

**Task:** Append to a supplied builder.

```java
public class Main {
    static void append(StringBuilder text) { text.append("B"); }
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder("A"); append(text); System.out.println(text);
    }
}
```

**Expected result:**
```text
AB
```

**Concept:** A copied reference can still reach the caller-visible object.

## Question 5 — Reference rebinding

**Task:** Reassign a builder parameter and inspect the caller object.

```java
public class Main {
    static void replace(StringBuilder text) { text = new StringBuilder("B"); }
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder("A"); replace(text); System.out.println(text);
    }
}
```

**Expected result:**
```text
A
```

**Concept:** Rebinding a parameter does not rebind the caller variable.

## Question 6 — Final parameter

**Task:** Use a final parameter in a calculation.

```java
public class Main {
    static int square(final int value) { return value*value; }
    public static void main(String[] args) {
        System.out.println(square(4));
    }
}
```

**Expected result:**
```text
16
```

**Concept:** final restricts parameter reassignment, not argument passing semantics.

## Question 7 — Varargs

**Task:** Sum a variable number of integer arguments.

```java
public class Main {
    static int sum(int... values) { int total=0; for (int value:values) { total+=value; } return total; }
    public static void main(String[] args) {
        System.out.println(sum(1,2,3)); System.out.println(sum());
    }
}
```

**Expected result:**
```text
6
0
```

**Concept:** A varargs parameter is represented as an array.

## Question 8 — Array input

**Task:** Return the first element of a non-empty array.

```java
public class Main {
    static int first(int[] values) { if (values.length == 0) { throw new IllegalArgumentException("Empty"); } return values[0]; }
    public static void main(String[] args) {
        System.out.println(first(new int[]{7,8}));
    }
}
```

**Expected result:**
```text
7
```

**Concept:** Array inputs need a defined empty-input policy.

## Question 9 — Widening argument

**Task:** Pass an int argument to a long parameter.

```java
public class Main {
    static long twice(long value) { return value*2; }
    public static void main(String[] args) {
        System.out.println(twice(7));
    }
}
```

**Expected result:**
```text
14
```

**Concept:** Applicable invocation conversions can widen primitive arguments.

## Question 10 — Null contract

**Task:** Reject a null name explicitly.

```java
public class Main {
    static String greet(String name) { return "Hello, " + java.util.Objects.requireNonNull(name, "name"); }
    public static void main(String[] args) {
        try { greet(null); } catch (NullPointerException e) { System.out.println(e.getMessage()); }
    }
}
```

**Expected result:**
```text
name
```

**Concept:** A contract can validate input at its boundary.

---

## Interview Questions and Answers

### Interview Question 1 — How do parameters and arguments differ?

**Answer:** A parameter is declared by the method; an argument is the expression or value supplied by its caller.

### Interview Question 2 — Is Java pass-by-value?

**Answer:** Yes, for primitives and references. Object mutation through a copied reference does not imply pass-by-reference.

### Interview Question 3 — Where must a varargs parameter appear?

**Answer:** It must be the last parameter, and a method can declare only one varargs parameter.

### Interview Question 4 — Can Java methods have default parameter values?

**Answer:** Java does not provide default arguments in ordinary method declarations. Use overloads, builders, or a configuration object when appropriate.

### Interview Question 5 — Does final on a parameter affect the caller?

**Answer:** It prevents reassignment of the local parameter inside the method. It does not make the caller variable or referenced object immutable.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| One argument | A parameter is the local name receiving an argument value. |
| Argument order | Positional argument order matters. |
| Primitive copy | A primitive argument is copied into the parameter. |
| Mutable input | A copied reference can still reach the caller-visible object. |
| Reference rebinding | Rebinding a parameter does not rebind the caller variable. |

[← 037. Calling methods](037_Calling_methods.md) | [Course Index](../README.md) | [039. Return values →](039_Return_values.md)
