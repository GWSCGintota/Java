[← 078. Abstract classes](078_Abstract_classes.md) | [Course Index](../README.md) | [080. Interfaces →](080_Interfaces.md)

# Java Abstract methods Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/079_Abstract_methods.md)

These exercises follow [079. Abstract methods](../../Road%20Map/05_Object_Oriented_Programming/079_Abstract_methods.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete Main.java with nested helper types. Compile and run each independently. Inputs are non-null unless explicitly tested; arithmetic uses small values. Nested fixtures keep examples focused and do not replace cross-package access exercises.

## Question 1 — Implement addition

**Task:** Implement addition.

```java
public class Main {
static abstract class Operation { abstract int apply(int a,int b); int twiceResult(int a,int b) { return apply(a,b)*2; } } static class Add extends Operation { @Override int apply(int a,int b) { return a+b; } } static class Multiply extends Operation { @Override int apply(int a,int b) { return a*b; } }
public static void main(String[] args) {
System.out.println(new Add().apply(2,3));
}
}
```

**Expected result:**
```text
5
```

**Concept:** A concrete subclass supplies the abstract method body.

## Question 2 — Implement multiplication

**Task:** Implement multiplication.

```java
public class Main {
static abstract class Operation { abstract int apply(int a,int b); int twiceResult(int a,int b) { return apply(a,b)*2; } } static class Add extends Operation { @Override int apply(int a,int b) { return a+b; } } static class Multiply extends Operation { @Override int apply(int a,int b) { return a*b; } }
public static void main(String[] args) {
System.out.println(new Multiply().apply(2,3));
}
}
```

**Expected result:**
```text
6
```

**Concept:** Different implementations share the same abstract signature.

## Question 3 — Invoke through an abstract type

**Task:** Invoke through an abstract type.

```java
public class Main {
static abstract class Operation { abstract int apply(int a,int b); int twiceResult(int a,int b) { return apply(a,b)*2; } } static class Add extends Operation { @Override int apply(int a,int b) { return a+b; } } static class Multiply extends Operation { @Override int apply(int a,int b) { return a*b; } }
public static void main(String[] args) {
Operation value=new Add(); System.out.println(value.apply(4,5));
}
}
```

**Expected result:**
```text
9
```

**Concept:** Abstract declarations support ordinary instance dispatch.

## Question 4 — Call an abstract operation from a concrete helper

**Task:** Call an abstract operation from a concrete helper.

```java
public class Main {
static abstract class Operation { abstract int apply(int a,int b); int twiceResult(int a,int b) { return apply(a,b)*2; } } static class Add extends Operation { @Override int apply(int a,int b) { return a+b; } } static class Multiply extends Operation { @Override int apply(int a,int b) { return a*b; } }
public static void main(String[] args) {
System.out.println(new Add().twiceResult(2,3));
}
}
```

**Expected result:**
```text
10
```

**Concept:** Concrete base behavior can use a subclass-provided operation.

## Question 5 — Reuse the helper with another implementation

**Task:** Reuse the helper with another implementation.

```java
public class Main {
static abstract class Operation { abstract int apply(int a,int b); int twiceResult(int a,int b) { return apply(a,b)*2; } } static class Add extends Operation { @Override int apply(int a,int b) { return a+b; } } static class Multiply extends Operation { @Override int apply(int a,int b) { return a*b; } }
public static void main(String[] args) {
System.out.println(new Multiply().twiceResult(2,3));
}
}
```

**Expected result:**
```text
12
```

**Concept:** A template operation can vary through dynamic dispatch.

## Question 6 — Supply an anonymous implementation

**Task:** Supply an anonymous implementation.

```java
public class Main {
static abstract class Operation { abstract int apply(int a,int b); int twiceResult(int a,int b) { return apply(a,b)*2; } } static class Add extends Operation { @Override int apply(int a,int b) { return a+b; } } static class Multiply extends Operation { @Override int apply(int a,int b) { return a*b; } }
public static void main(String[] args) {
Operation value=new Operation() { @Override int apply(int a,int b) { return a-b; } }; System.out.println(value.apply(8,3));
}
}
```

**Expected result:**
```text
5
```

**Concept:** Anonymous subclasses must implement remaining abstract operations.

## Question 7 — Use zero as an input

**Task:** Use zero as an input.

```java
public class Main {
static abstract class Operation { abstract int apply(int a,int b); int twiceResult(int a,int b) { return apply(a,b)*2; } } static class Add extends Operation { @Override int apply(int a,int b) { return a+b; } } static class Multiply extends Operation { @Override int apply(int a,int b) { return a*b; } }
public static void main(String[] args) {
System.out.println(new Multiply().apply(0,7));
}
}
```

**Expected result:**
```text
0
```

**Concept:** Abstractness does not define domain-specific numeric behavior.

## Question 8 — Use negative inputs

**Task:** Use negative inputs.

```java
public class Main {
static abstract class Operation { abstract int apply(int a,int b); int twiceResult(int a,int b) { return apply(a,b)*2; } } static class Add extends Operation { @Override int apply(int a,int b) { return a+b; } } static class Multiply extends Operation { @Override int apply(int a,int b) { return a*b; } }
public static void main(String[] args) {
System.out.println(new Add().apply(-2,3));
}
}
```

**Expected result:**
```text
1
```

**Concept:** Each implementation must satisfy the documented input contract.

## Question 9 — Traverse implementations

**Task:** Traverse implementations.

```java
public class Main {
static abstract class Operation { abstract int apply(int a,int b); int twiceResult(int a,int b) { return apply(a,b)*2; } } static class Add extends Operation { @Override int apply(int a,int b) { return a+b; } } static class Multiply extends Operation { @Override int apply(int a,int b) { return a*b; } }
public static void main(String[] args) {
Operation[] values={new Add(),new Multiply()}; for(Operation value:values) { System.out.println(value.apply(2,4)); }
}
}
```

**Expected result:**
```text
6
8
```

**Concept:** One abstract operation can drive a uniform algorithm.

## Question 10 — Inspect abstract method metadata

**Task:** Inspect abstract method metadata.

```java
public class Main {
static abstract class Operation { abstract int apply(int a,int b); int twiceResult(int a,int b) { return apply(a,b)*2; } } static class Add extends Operation { @Override int apply(int a,int b) { return a+b; } } static class Multiply extends Operation { @Override int apply(int a,int b) { return a*b; } }
public static void main(String[] args) {
try { System.out.println(java.lang.reflect.Modifier.isAbstract(Operation.class.getDeclaredMethod("apply",int.class,int.class).getModifiers())); } catch(ReflectiveOperationException e) { throw new RuntimeException(e); }
}
}
```

**Expected result:**
```text
true
```

**Concept:** An abstract method declaration has no implementation body.

---

## Interview Questions and Answers

### Interview Question 1 — Can an abstract method have a body?

**Answer:** No. Its declaration ends without an implementation body; concrete subclasses must provide one unless they remain abstract.

### Interview Question 2 — Can an abstract method be private?

**Answer:** No. A subclass must be able to implement the inherited operation, which conflicts with private-method inheritance rules.

### Interview Question 3 — Can an abstract method be static or final?

**Answer:** No. Static methods are not dynamically overridden, and final methods cannot be overridden.

### Interview Question 4 — Does every subclass need to implement every abstract method immediately?

**Answer:** Only a concrete subclass must complete all remaining abstract contracts. An abstract intermediate subclass can leave operations unimplemented.

### Interview Question 5 — Why use @Override on implementations?

**Answer:** It verifies the inherited signature relationship and catches accidental overloads or spelling mistakes.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Implement addition | A concrete subclass supplies the abstract method body. |
| Implement multiplication | Different implementations share the same abstract signature. |
| Invoke through an abstract type | Abstract declarations support ordinary instance dispatch. |
| Call an abstract operation from a concrete helper | Concrete base behavior can use a subclass-provided operation. |
| Reuse the helper with another implementation | A template operation can vary through dynamic dispatch. |

[← 078. Abstract classes](078_Abstract_classes.md) | [Course Index](../README.md) | [080. Interfaces →](080_Interfaces.md)
