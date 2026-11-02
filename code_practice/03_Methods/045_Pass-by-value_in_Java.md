[← 044. Recursion](044_Recursion.md) | [Course Index](../README.md) | [046. Arrays →](../04_Arrays/046_Arrays.md)

# Java Pass-by-value in Java Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/03_Methods/045_Pass-by-value_in_Java.md)

These exercises follow [045. Pass-by-value in Java](../../Road%20Map/03_Methods/045_Pass-by-value_in_Java.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each model solution is a complete Main.java. Compile and run it independently. Arithmetic examples use small values; inputs are non-null unless explicitly tested. Standard-library classes are fully qualified when needed.

## Question 1 — Primitive parameter

**Task:** Reassign a primitive parameter and inspect the caller.

```java
public class Main {
    static void change(int value) { value=99; }
    public static void main(String[] args) {
        int value=5; change(value); System.out.println(value);
    }
}
```

**Expected result:**
```text
5
```

**Concept:** The callee receives a copied primitive value.

## Question 2 — Mutate an object

**Task:** Append through a builder parameter.

```java
public class Main {
    static void change(StringBuilder value) { value.append("B"); }
    public static void main(String[] args) {
        StringBuilder value=new StringBuilder("A"); change(value); System.out.println(value);
    }
}
```

**Expected result:**
```text
AB
```

**Concept:** A copied reference still identifies the same mutable object.

## Question 3 — Reassign a reference

**Task:** Replace the local parameter with a new builder.

```java
public class Main {
    static void change(StringBuilder value) { value=new StringBuilder("B"); }
    public static void main(String[] args) {
        StringBuilder value=new StringBuilder("A"); change(value); System.out.println(value);
    }
}
```

**Expected result:**
```text
A
```

**Concept:** Reassigning the copied reference does not rebind the caller variable.

## Question 4 — Array mutation

**Task:** Change an array element through a parameter.

```java
public class Main {
    static void change(int[] values) { values[0]=9; }
    public static void main(String[] args) {
        int[] values={1,2}; change(values); System.out.println(values[0]);
    }
}
```

**Expected result:**
```text
9
```

**Concept:** Array objects are mutable through shared references.

## Question 5 — Array replacement

**Task:** Assign a new array to the parameter.

```java
public class Main {
    static void change(int[] values) { values=new int[]{9}; }
    public static void main(String[] args) {
        int[] values={1,2}; change(values); System.out.println(values.length+":"+values[0]);
    }
}
```

**Expected result:**
```text
2:1
```

**Concept:** Parameter rebinding does not replace the caller array reference.

## Question 6 — String immutability

**Task:** Concatenate inside a string parameter without returning the new value.

```java
public class Main {
    static void change(String value) { value += "B"; }
    public static void main(String[] args) {
        String value="A"; change(value); System.out.println(value);
    }
}
```

**Expected result:**
```text
A
```

**Concept:** Concatenation rebinds the local parameter to another immutable value.

## Question 7 — Return replacement

**Task:** Return a replacement string and assign it in the caller.

```java
public class Main {
    static String change(String value) { return value+"B"; }
    public static void main(String[] args) {
        String value="A"; value=change(value); System.out.println(value);
    }
}
```

**Expected result:**
```text
AB
```

**Concept:** The caller can deliberately adopt a returned replacement value.

## Question 8 — Failed swap

**Task:** Attempt to swap two integer parameters and inspect the caller values.

```java
public class Main {
    static void swap(int a,int b) { int temporary=a; a=b; b=temporary; }
    public static void main(String[] args) {
        int a=1,b=2; swap(a,b); System.out.println(a+":"+b);
    }
}
```

**Expected result:**
```text
1:2
```

**Concept:** Swapping parameter copies does not swap caller variables.

## Question 9 — Mutable holder

**Task:** Swap two elements inside a caller-owned array.

```java
public class Main {
    static void swap(int[] pair) { int temporary=pair[0]; pair[0]=pair[1]; pair[1]=temporary; }
    public static void main(String[] args) {
        int[] pair={1,2}; swap(pair); System.out.println(java.util.Arrays.toString(pair));
    }
}
```

**Expected result:**
```text
[2, 1]
```

**Concept:** Mutation of an explicit shared container is caller-visible.

## Question 10 — Wrapper rebinding

**Task:** Increment an Integer parameter and inspect the original wrapper reference.

```java
public class Main {
    static void increment(Integer value) { value++; }
    public static void main(String[] args) {
        Integer value=7; increment(value); System.out.println(value);
    }
}
```

**Expected result:**
```text
7
```

**Concept:** Unboxing, arithmetic, and boxing reassign the local wrapper parameter.

---

## Interview Questions and Answers

### Interview Question 1 — Is Java ever pass-by-reference for object parameters?

**Answer:** No. Java always passes argument values by value. For objects, that value is a reference.

### Interview Question 2 — Why can a callee change an object?

**Answer:** The copied reference and the caller reference can identify the same mutable object. That permits mutation without sharing the variable itself.

### Interview Question 3 — Why does assigning null to a parameter not clear the caller variable?

**Answer:** The callee changes only its local reference copy. The caller variable is a separate storage location.

### Interview Question 4 — How can a method supply a replacement object?

**Answer:** Return it and let the caller assign the result, or use an explicit mutable holder when that API design is appropriate.

### Interview Question 5 — Does final on a parameter change passing semantics?

**Answer:** No. It only restricts reassignment of the local parameter, and does not by itself prevent mutation of an object reached through it.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Primitive parameter | The callee receives a copied primitive value. |
| Mutate an object | A copied reference still identifies the same mutable object. |
| Reassign a reference | Reassigning the copied reference does not rebind the caller variable. |
| Array mutation | Array objects are mutable through shared references. |
| Array replacement | Parameter rebinding does not replace the caller array reference. |

[← 044. Recursion](044_Recursion.md) | [Course Index](../README.md) | [046. Arrays →](../04_Arrays/046_Arrays.md)
