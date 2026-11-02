[← 039. Return values](039_Return_values.md) | [Course Index](../README.md) | [041. Variable scope →](041_Variable_scope.md)

# Java Method overloading Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/03_Methods/040_Method_overloading.md)

These exercises follow [040. Method overloading](../../Road%20Map/03_Methods/040_Method_overloading.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each model solution is a complete Main.java. Compile and run it independently. Arithmetic examples use small values; inputs are non-null unless explicitly tested. Standard-library classes are fully qualified when needed.

## Question 1 — Different arity

**Task:** Overload addition for two and three integers.

```java
public class Main {
    static int add(int a,int b) { return a+b; } static int add(int a,int b,int c) { return a+b+c; }
    public static void main(String[] args) {
        System.out.println(add(1,2)); System.out.println(add(1,2,3));
    }
}
```

**Expected result:**
```text
3
6
```

**Concept:** Different parameter counts can distinguish overloads.

## Question 2 — Different types

**Task:** Overload a formatter for int and String.

```java
public class Main {
    static String describe(int value) { return "int:"+value; } static String describe(String value) { return "text:"+value; }
    public static void main(String[] args) {
        System.out.println(describe(7)); System.out.println(describe("Ada"));
    }
}
```

**Expected result:**
```text
int:7
text:Ada
```

**Concept:** Parameter types distinguish overloads.

## Question 3 — Parameter order

**Task:** Overload a label method with reversed parameter types.

```java
public class Main {
    static String label(String name,int age) { return name+":"+age; } static String label(int age,String name) { return age+":"+name; }
    public static void main(String[] args) {
        System.out.println(label("Ada",20)); System.out.println(label(20,"Ada"));
    }
}
```

**Expected result:**
```text
Ada:20
20:Ada
```

**Concept:** Parameter order is part of the signature.

## Question 4 — Exact match

**Task:** Choose an int overload over a long overload for an int literal.

```java
public class Main {
    static String kind(int n) { return "int"; } static String kind(long n) { return "long"; }
    public static void main(String[] args) {
        System.out.println(kind(7));
    }
}
```

**Expected result:**
```text
int
```

**Concept:** An exact applicable primitive match is more specific in this case.

## Question 5 — Long literal

**Task:** Choose the long overload for 7L.

```java
public class Main {
    static String kind(int n) { return "int"; } static String kind(long n) { return "long"; }
    public static void main(String[] args) {
        System.out.println(kind(7L));
    }
}
```

**Expected result:**
```text
long
```

**Concept:** Literal type affects overload selection.

## Question 6 — Widening before boxing

**Task:** Choose between long and Integer for an int argument.

```java
public class Main {
    static String kind(long n) { return "long"; } static String kind(Integer n) { return "Integer"; }
    public static void main(String[] args) {
        System.out.println(kind(7));
    }
}
```

**Expected result:**
```text
long
```

**Concept:** Applicable strict invocation conversions are considered before boxing-based applicability.

## Question 7 — Null specificity

**Task:** Choose between Object and String for a null argument.

```java
public class Main {
    static String kind(Object n) { return "Object"; } static String kind(String n) { return "String"; }
    public static void main(String[] args) {
        System.out.println(kind(null));
    }
}
```

**Expected result:**
```text
String
```

**Concept:** The more specific applicable reference overload wins here.

## Question 8 — Resolve null ambiguity

**Task:** Use a cast to choose between unrelated String and Integer overloads.

```java
public class Main {
    static String kind(String n) { return "String"; } static String kind(Integer n) { return "Integer"; }
    public static void main(String[] args) {
        System.out.println(kind((String)null));
    }
}
```

**Expected result:**
```text
String
```

**Concept:** An uncast null would be ambiguous between these unrelated types.

## Question 9 — Fixed arity first

**Task:** Choose between a fixed int overload and an int varargs overload.

```java
public class Main {
    static String kind(int n) { return "fixed"; } static String kind(int... n) { return "varargs"; }
    public static void main(String[] args) {
        System.out.println(kind(7)); System.out.println(kind(7,8));
    }
}
```

**Expected result:**
```text
fixed
varargs
```

**Concept:** Variable-arity applicability is considered after applicable fixed-arity phases.

## Question 10 — Declared argument type

**Task:** Show compile-time overload choice for an Object variable holding a String.

```java
public class Main {
    static String kind(Object n) { return "Object"; } static String kind(String n) { return "String"; }
    public static void main(String[] args) {
        Object value="Ada"; System.out.println(kind(value));
    }
}
```

**Expected result:**
```text
Object
```

**Concept:** Overload selection uses compile-time types, unlike overridden instance dispatch.

---

## Interview Questions and Answers

### Interview Question 1 — What is method overloading?

**Answer:** Methods share a name but have distinct signatures, such as different parameter counts or types. It is resolved at compile time.

### Interview Question 2 — Can return type alone distinguish overloads?

**Answer:** No. Two declarations with the same signature and incompatible or different return types do not become valid overloads merely because of their returns.

### Interview Question 3 — How does overloading differ from overriding?

**Answer:** Overloading selects a declaration using compile-time argument types. Overriding supplies runtime receiver-specific behavior for an inherited instance-method contract.

### Interview Question 4 — Why can passing null be ambiguous?

**Answer:** Several unrelated reference-type overloads may all accept null without one being more specific. A typed variable or cast can express the intended overload.

### Interview Question 5 — Why avoid excessive overloads?

**Answer:** They can create ambiguous calls and surprising conversion choices. Prefer a small, coherent API and meaningful names when operations have different intent.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Different arity | Different parameter counts can distinguish overloads. |
| Different types | Parameter types distinguish overloads. |
| Parameter order | Parameter order is part of the signature. |
| Exact match | An exact applicable primitive match is more specific in this case. |
| Long literal | Literal type affects overload selection. |

[← 039. Return values](039_Return_values.md) | [Course Index](../README.md) | [041. Variable scope →](041_Variable_scope.md)
