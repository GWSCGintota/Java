[← 041. Variable scope](041_Variable_scope.md) | [Course Index](../README.md) | [043. Static methods →](043_Static_methods.md)

# Java Local vs instance variables Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/03_Methods/042_Local_vs_instance_variables.md)

These exercises follow [042. Local vs instance variables](../../Road%20Map/03_Methods/042_Local_vs_instance_variables.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each model solution is a complete Main.java. Compile and run it independently. Arithmetic examples use small values; inputs are non-null unless explicitly tested. Standard-library classes are fully qualified when needed.

## Question 1 — Local initialization

**Task:** Initialize a local before reading it.

```java
public class Main {
    static int value() { int local=5; return local; }
    public static void main(String[] args) {
        System.out.println(value());
    }
}
```

**Expected result:**
```text
5
```

**Concept:** Locals require definite assignment.

## Question 2 — Default instance int

**Task:** Read an uninitialized int field.

```java
public class Main {
    int count;
    public static void main(String[] args) {
        System.out.println(new Main().count);
    }
}
```

**Expected result:**
```text
0
```

**Concept:** Instance fields receive type-specific defaults.

## Question 3 — Default reference

**Task:** Read an uninitialized reference field.

```java
public class Main {
    String name;
    public static void main(String[] args) {
        System.out.println(new Main().name);
    }
}
```

**Expected result:**
```text
null
```

**Concept:** Reference fields default to null.

## Question 4 — Separate instances

**Task:** Increment one object field and compare two instances.

```java
public class Main {
    int count;
    public static void main(String[] args) {
        Main a=new Main(), b=new Main(); a.count++; System.out.println(a.count+":"+b.count);
    }
}
```

**Expected result:**
```text
1:0
```

**Concept:** Each instance has its own instance fields.

## Question 5 — Local reset

**Task:** Compare a method-local counter with an instance counter across calls.

```java
public class Main {
    int count; int next() { int local=0; local++; count++; return local*10+count; }
    public static void main(String[] args) {
        Main object=new Main(); System.out.println(object.next()); System.out.println(object.next());
    }
}
```

**Expected result:**
```text
11
12
```

**Concept:** Instance state survives calls while each invocation initializes fresh locals.

## Question 6 — Shadowed field

**Task:** Print a local and the same-named instance field.

```java
public class Main {
    int value=8; void show() { int value=3; System.out.println(value); System.out.println(this.value); }
    public static void main(String[] args) {
        new Main().show();
    }
}
```

**Expected result:**
```text
3
8
```

**Concept:** A local can hide an instance field name.

## Question 7 — Constructor assignment

**Task:** Initialize an instance field from a parameter.

```java
public class Main {
    final String name; Main(String name) { this.name=name; }
    public static void main(String[] args) {
        System.out.println(new Main("Ada").name);
    }
}
```

**Expected result:**
```text
Ada
```

**Concept:** Constructor parameters and fields are distinct variables.

## Question 8 — Field mutation

**Task:** Modify state through an instance method.

```java
public class Main {
    int balance=10; void deposit(int amount) { balance+=amount; }
    public static void main(String[] args) {
        Main account=new Main(); account.deposit(5); System.out.println(account.balance);
    }
}
```

**Expected result:**
```text
15
```

**Concept:** Methods can update receiver-owned instance state.

## Question 9 — Local copies

**Task:** Copy a field into a local and modify only the local.

```java
public class Main {
    int value=7; void show() { int copy=value; copy=9; System.out.println(copy+":"+value); }
    public static void main(String[] args) {
        new Main().show();
    }
}
```

**Expected result:**
```text
9:7
```

**Concept:** A primitive local copy does not become an alias for a field.

## Question 10 — Field array default

**Task:** Read initial array-element defaults through an instance field.

```java
public class Main {
    int[] values=new int[2];
    public static void main(String[] args) {
        System.out.println(java.util.Arrays.toString(new Main().values));
    }
}
```

**Expected result:**
```text
[0, 0]
```

**Concept:** Field initialization can allocate an array whose elements also receive defaults.

---

## Interview Questions and Answers

### Interview Question 1 — Do locals and instance fields have the same initialization rules?

**Answer:** No. Fields receive defaults before explicit initialization. Locals must be definitely assigned before use.

### Interview Question 2 — Does every object share instance fields?

**Answer:** No. Each instance owns its own instance-field values, though their references may point to shared objects.

### Interview Question 3 — How do instance and static fields differ?

**Answer:** An instance field belongs to an object. A static field belongs to the class as loaded, rather than separately to each instance.

### Interview Question 4 — What does this refer to?

**Answer:** The current receiver instance within instance-context code. It is unavailable in a static context.

### Interview Question 5 — Can a local reference outlive its method through an object?

**Answer:** Objects remain alive based on reachability, not merely the scope of the local that once referenced them. Returning or capturing a reference can keep an object reachable.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Local initialization | Locals require definite assignment. |
| Default instance int | Instance fields receive type-specific defaults. |
| Default reference | Reference fields default to null. |
| Separate instances | Each instance has its own instance fields. |
| Local reset | Instance state survives calls while each invocation initializes fresh locals. |

[← 041. Variable scope](041_Variable_scope.md) | [Course Index](../README.md) | [043. Static methods →](043_Static_methods.md)
