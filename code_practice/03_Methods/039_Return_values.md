[← 038. Parameters](038_Parameters.md) | [Course Index](../README.md) | [040. Method overloading →](040_Method_overloading.md)

# Java Return values Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/03_Methods/039_Return_values.md)

These exercises follow [039. Return values](../../Road%20Map/03_Methods/039_Return_values.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each model solution is a complete Main.java. Compile and run it independently. Arithmetic examples use small values; inputs are non-null unless explicitly tested. Standard-library classes are fully qualified when needed.

## Question 1 — Integer return

**Task:** Return the sum of two values.

```java
public class Main {
    static int add(int a,int b) { return a+b; }
    public static void main(String[] args) {
        System.out.println(add(2,3));
    }
}
```

**Expected result:**
```text
5
```

**Concept:** A return expression must be compatible with the declared result type.

## Question 2 — String return

**Task:** Return a formatted greeting.

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

**Concept:** Reference results are returned as reference values.

## Question 3 — Boolean return

**Task:** Return a divisibility check.

```java
public class Main {
    static boolean even(int n) { return n%2==0; }
    public static void main(String[] args) {
        System.out.println(even(7));
    }
}
```

**Expected result:**
```text
false
```

**Concept:** Boolean results can feed later conditions.

## Question 4 — Early return

**Task:** Return zero for null before reading a length.

```java
public class Main {
    static int length(String text) { if (text==null) { return 0; } return text.length(); }
    public static void main(String[] args) {
        System.out.println(length(null));
    }
}
```

**Expected result:**
```text
0
```

**Concept:** A guard return handles a boundary case before the main path.

## Question 5 — All paths

**Task:** Return one of two labels based on age.

```java
public class Main {
    static String label(int age) { if (age>=18) { return "Adult"; } else { return "Minor"; } }
    public static void main(String[] args) {
        System.out.println(label(16));
    }
}
```

**Expected result:**
```text
Minor
```

**Concept:** All normally completing paths of a non-void method need a result.

## Question 6 — Void return

**Task:** Stop a void method before a later print.

```java
public class Main {
    static void print(String text) { if (text==null) { return; } System.out.println(text); }
    public static void main(String[] args) {
        print(null); System.out.println("Done");
    }
}
```

**Expected result:**
```text
Done
```

**Concept:** A bare return exits a void invocation.

## Question 7 — New object

**Task:** Return a newly constructed builder each time.

```java
public class Main {
    static StringBuilder create() { return new StringBuilder(); }
    public static void main(String[] args) {
        System.out.println(create()==create());
    }
}
```

**Expected result:**
```text
false
```

**Concept:** Returning objects can transfer new independent instances to callers.

## Question 8 — Existing reference

**Task:** Return the supplied builder unchanged.

```java
public class Main {
    static StringBuilder identity(StringBuilder value) { return value; }
    public static void main(String[] args) {
        StringBuilder text=new StringBuilder("A"); System.out.println(identity(text)==text);
    }
}
```

**Expected result:**
```text
true
```

**Concept:** Returning an existing reference does not copy the object.

## Question 9 — Optional absence

**Task:** Return an optional first element with explicit empty handling.

```java
public class Main {
    static java.util.Optional<Integer> first(int[] values) { return values.length==0 ? java.util.Optional.empty() : java.util.Optional.of(values[0]); }
    public static void main(String[] args) {
        System.out.println(first(new int[0]).isPresent());
    }
}
```

**Expected result:**
```text
false
```

**Concept:** An optional result can represent absence without an arbitrary numeric sentinel.

## Question 10 — Defensive result

**Task:** Return a copy of an array so caller mutation does not change the original.

```java
public class Main {
    static int[] copy(int[] values) { return values.clone(); }
    public static void main(String[] args) {
        int[] original={1,2}; int[] result=copy(original); result[0]=9; System.out.println(original[0]);
    }
}
```

**Expected result:**
```text
1
```

**Concept:** Result ownership and copying depth are part of the contract.

---

## Interview Questions and Answers

### Interview Question 1 — What does return do?

**Answer:** It ends the current method or lambda invocation and, for a value-returning method, supplies a compatible result.

### Interview Question 2 — Can overloads differ only by return type?

**Answer:** No. The return type alone does not distinguish ordinary method overloads.

### Interview Question 3 — Does returning an object copy it?

**Answer:** No. The reference value is returned. A method must explicitly construct or copy objects if independent ownership is required.

### Interview Question 4 — Must a non-void method contain a return on every textual branch?

**Answer:** Every path that can complete normally must produce a result; paths that always throw or loop indefinitely do not complete normally.

### Interview Question 5 — Why return Optional rather than null?

**Answer:** Optional can make possible absence explicit at selected API boundaries. It is a design choice, not a universal replacement for every nullable field or argument.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Integer return | A return expression must be compatible with the declared result type. |
| String return | Reference results are returned as reference values. |
| Boolean return | Boolean results can feed later conditions. |
| Early return | A guard return handles a boundary case before the main path. |
| All paths | All normally completing paths of a non-void method need a result. |

[← 038. Parameters](038_Parameters.md) | [Course Index](../README.md) | [040. Method overloading →](040_Method_overloading.md)
