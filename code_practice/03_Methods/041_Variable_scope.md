[← 040. Method overloading](040_Method_overloading.md) | [Course Index](../README.md) | [042. Local vs instance variables →](042_Local_vs_instance_variables.md)

# Java Variable scope Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/03_Methods/041_Variable_scope.md)

These exercises follow [041. Variable scope](../../Road%20Map/03_Methods/041_Variable_scope.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each model solution is a complete Main.java. Compile and run it independently. Arithmetic examples use small values; inputs are non-null unless explicitly tested. Standard-library classes are fully qualified when needed.

## Question 1 — Local scope

**Task:** Read a local within its declaring method.

```java
public class Main {
    static int calculate() { int value=7; return value; }
    public static void main(String[] args) {
        System.out.println(calculate());
    }
}
```

**Expected result:**
```text
7
```

**Concept:** A local is available only within its defined scope.

## Question 2 — Block scope

**Task:** Use a temporary in a nested block.

```java
public class Main {

    public static void main(String[] args) {
        int outer=2; { int inner=3; System.out.println(outer+inner); } System.out.println(outer);
    }
}
```

**Expected result:**
```text
5
2
```

**Concept:** Block locals do not escape the block.

## Question 3 — Independent blocks

**Task:** Reuse a local name in separate non-overlapping blocks.

```java
public class Main {

    public static void main(String[] args) {
        { int value=1; System.out.println(value); } { int value=2; System.out.println(value); }
    }
}
```

**Expected result:**
```text
1
2
```

**Concept:** Non-overlapping local scopes can reuse a name.

## Question 4 — Parameter scope

**Task:** Return a parameter plus one.

```java
public class Main {
    static int increment(int value) { return value+1; }
    public static void main(String[] args) {
        System.out.println(increment(4));
    }
}
```

**Expected result:**
```text
5
```

**Concept:** A parameter is in scope throughout its method body.

## Question 5 — Loop scope

**Task:** Declare a counter outside a loop to inspect it afterward.

```java
public class Main {

    public static void main(String[] args) {
        int index=0; for (; index<3; index++) { } System.out.println(index);
    }
}
```

**Expected result:**
```text
3
```

**Concept:** Declaration placement controls availability after the loop.

## Question 6 — Field shadowing

**Task:** Distinguish a parameter from an instance field with this.

```java
public class Main {
    int value=7; void set(int value) { this.value=value; }
    public static void main(String[] args) {
        Main object=new Main(); object.set(9); System.out.println(object.value);
    }
}
```

**Expected result:**
```text
9
```

**Concept:** this qualifies the instance field hidden by a parameter name.

## Question 7 — Class qualification

**Task:** Distinguish a local from a static field.

```java
public class Main {
    static int count=10;
    public static void main(String[] args) {
        int count=3; System.out.println(count); System.out.println(Main.count);
    }
}
```

**Expected result:**
```text
3
10
```

**Concept:** A class qualifier identifies a shadowed static field.

## Question 8 — Catch parameter

**Task:** Read an exception variable inside its catch block.

```java
public class Main {

    public static void main(String[] args) {
        try { throw new IllegalArgumentException("Bad"); } catch (IllegalArgumentException failure) { System.out.println(failure.getMessage()); }
    }
}
```

**Expected result:**
```text
Bad
```

**Concept:** The catch parameter is scoped to its catch block.

## Question 9 — Lambda capture

**Task:** Capture an effectively final local.

```java
public class Main {

    public static void main(String[] args) {
        int factor=3; java.util.function.IntUnaryOperator multiply=n->n*factor; System.out.println(multiply.applyAsInt(4));
    }
}
```

**Expected result:**
```text
12
```

**Concept:** Captured locals must remain final or effectively final.

## Question 10 — Per-invocation local

**Task:** Show that a local starts fresh on each call.

```java
public class Main {
    static int one() { int count=0; return ++count; }
    public static void main(String[] args) {
        System.out.println(one()); System.out.println(one());
    }
}
```

**Expected result:**
```text
1
1
```

**Concept:** Each invocation gets separate local state.

---

## Interview Questions and Answers

### Interview Question 1 — What determines scope?

**Answer:** The language region in which a declaration can be referenced, determined by its kind and position in enclosing constructs.

### Interview Question 2 — How do scope and lifetime differ?

**Answer:** Scope concerns where a name is available in source; lifetime concerns how long associated runtime state or an object remains relevant or reachable.

### Interview Question 3 — Can a local shadow a field?

**Answer:** Yes. Use this.field or ClassName.field to distinguish the field. A local cannot simply redeclare an overlapping local name in a nested block.

### Interview Question 4 — Can a lambda redeclare an enclosing local name?

**Answer:** Not as an overlapping lambda parameter or local. Lambdas do not introduce anonymous-class-style shadowing rules for enclosing locals.

### Interview Question 5 — Why minimize local scope?

**Answer:** It reduces accidental use, clarifies dependencies, and makes initialization and state changes easier to reason about.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Local scope | A local is available only within its defined scope. |
| Block scope | Block locals do not escape the block. |
| Independent blocks | Non-overlapping local scopes can reuse a name. |
| Parameter scope | A parameter is in scope throughout its method body. |
| Loop scope | Declaration placement controls availability after the loop. |

[← 040. Method overloading](040_Method_overloading.md) | [Course Index](../README.md) | [042. Local vs instance variables →](042_Local_vs_instance_variables.md)
