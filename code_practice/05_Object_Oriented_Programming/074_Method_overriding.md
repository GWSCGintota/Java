[← 073. extends](073_extends.md) | [Course Index](../README.md) | [075. super →](075_super.md)

# Java Method overriding Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/074_Method_overriding.md)

These exercises follow [074. Method overriding](../../Road%20Map/05_Object_Oriented_Programming/074_Method_overriding.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete Main.java with nested helper types. Compile and run each independently. Inputs are non-null unless explicitly tested; arithmetic uses small values. Nested fixtures keep examples focused and do not replace cross-package access exercises.

## Question 1 — Call a concrete override

**Task:** Call a concrete override.

```java
public class Main {
static class Base { String label() { return "Base"; } Number number() { return 1; } static String kind() { return "Base static"; } } static class Child extends Base { @Override String label() { return "Child"; } @Override Integer number() { return 2; } static String kind() { return "Child static"; } }
public static void main(String[] args) {
System.out.println(new Child().label());
}
}
```

**Expected result:**
```text
Child
```

**Concept:** The subclass supplies specialized instance behavior.

## Question 2 — Dispatch through the superclass

**Task:** Dispatch through the superclass.

```java
public class Main {
static class Base { String label() { return "Base"; } Number number() { return 1; } static String kind() { return "Base static"; } } static class Child extends Base { @Override String label() { return "Child"; } @Override Integer number() { return 2; } static String kind() { return "Child static"; } }
public static void main(String[] args) {
Base value=new Child(); System.out.println(value.label());
}
}
```

**Expected result:**
```text
Child
```

**Concept:** Runtime receiver type selects an override.

## Question 3 — Compare a base instance

**Task:** Compare a base instance.

```java
public class Main {
static class Base { String label() { return "Base"; } Number number() { return 1; } static String kind() { return "Base static"; } } static class Child extends Base { @Override String label() { return "Child"; } @Override Integer number() { return 2; } static String kind() { return "Child static"; } }
public static void main(String[] args) {
System.out.println(new Base().label());
}
}
```

**Expected result:**
```text
Base
```

**Concept:** A base object continues to use its own implementation.

## Question 4 — Use a covariant return

**Task:** Use a covariant return.

```java
public class Main {
static class Base { String label() { return "Base"; } Number number() { return 1; } static String kind() { return "Base static"; } } static class Child extends Base { @Override String label() { return "Child"; } @Override Integer number() { return 2; } static String kind() { return "Child static"; } }
public static void main(String[] args) {
Integer result=new Child().number(); System.out.println(result);
}
}
```

**Expected result:**
```text
2
```

**Concept:** An override can narrow a reference result type covariantly.

## Question 5 — Read a covariant result through the parent contract

**Task:** Read a covariant result through the parent contract.

```java
public class Main {
static class Base { String label() { return "Base"; } Number number() { return 1; } static String kind() { return "Base static"; } } static class Child extends Base { @Override String label() { return "Child"; } @Override Integer number() { return 2; } static String kind() { return "Child static"; } }
public static void main(String[] args) {
Base value=new Child(); Number result=value.number(); System.out.println(result);
}
}
```

**Expected result:**
```text
2
```

**Concept:** The runtime override still executes through the broader return contract.

## Question 6 — Contrast static hiding

**Task:** Contrast static hiding.

```java
public class Main {
static class Base { String label() { return "Base"; } Number number() { return 1; } static String kind() { return "Base static"; } } static class Child extends Base { @Override String label() { return "Child"; } @Override Integer number() { return 2; } static String kind() { return "Child static"; } }
public static void main(String[] args) {
Base value=new Child(); System.out.println(value.kind());
}
}
```

**Expected result:**
```text
Base static
```

**Concept:** Static method selection is not overridden instance dispatch.

## Question 7 — Call the subclass static method explicitly

**Task:** Call the subclass static method explicitly.

```java
public class Main {
static class Base { String label() { return "Base"; } Number number() { return 1; } static String kind() { return "Base static"; } } static class Child extends Base { @Override String label() { return "Child"; } @Override Integer number() { return 2; } static String kind() { return "Child static"; } }
public static void main(String[] args) {
System.out.println(Child.kind());
}
}
```

**Expected result:**
```text
Child static
```

**Concept:** Class qualification identifies the hidden static declaration.

## Question 8 — Traverse polymorphic receivers

**Task:** Traverse polymorphic receivers.

```java
public class Main {
static class Base { String label() { return "Base"; } Number number() { return 1; } static String kind() { return "Base static"; } } static class Child extends Base { @Override String label() { return "Child"; } @Override Integer number() { return 2; } static String kind() { return "Child static"; } }
public static void main(String[] args) {
Base[] values={new Base(),new Child()}; for(Base value:values) { System.out.println(value.label()); }
}
}
```

**Expected result:**
```text
Base
Child
```

**Concept:** One contract can dispatch to different overrides for each element.

## Question 9 — Check a returned runtime result type

**Task:** Check a returned runtime result type.

```java
public class Main {
static class Base { String label() { return "Base"; } Number number() { return 1; } static String kind() { return "Base static"; } } static class Child extends Base { @Override String label() { return "Child"; } @Override Integer number() { return 2; } static String kind() { return "Child static"; } }
public static void main(String[] args) {
Base value=new Child(); System.out.println(value.number() instanceof Integer);
}
}
```

**Expected result:**
```text
true
```

**Concept:** Covariance preserves reference compatibility.

## Question 10 — Use an overridden method reference

**Task:** Use an overridden method reference.

```java
public class Main {
static class Base { String label() { return "Base"; } Number number() { return 1; } static String kind() { return "Base static"; } } static class Child extends Base { @Override String label() { return "Child"; } @Override Integer number() { return 2; } static String kind() { return "Child static"; } }
public static void main(String[] args) {
Base value=new Child(); java.util.function.Supplier<String> label=value::label; System.out.println(label.get());
}
}
```

**Expected result:**
```text
Child
```

**Concept:** Compatible method references retain instance-dispatch behavior.

---

## Interview Questions and Answers

### Interview Question 1 — What is method overriding?

**Answer:** A subclass supplies a compatible implementation of an inherited instance-method contract, enabling runtime receiver-specific dispatch.

### Interview Question 2 — Why use @Override?

**Answer:** It asks the compiler to check that a declaration actually overrides or implements an inherited method, catching signature mistakes.

### Interview Question 3 — Can an override reduce visibility?

**Answer:** No. Its access cannot be more restrictive than the overridden method's permitted access.

### Interview Question 4 — What is a covariant return?

**Answer:** An override can return a more specific reference type while remaining compatible with the inherited return contract.

### Interview Question 5 — Can an override broaden checked exceptions arbitrarily?

**Answer:** No. Its checked exceptions must remain compatible with the inherited throws contract; unchecked exceptions have different declaration rules.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Call a concrete override | The subclass supplies specialized instance behavior. |
| Dispatch through the superclass | Runtime receiver type selects an override. |
| Compare a base instance | A base object continues to use its own implementation. |
| Use a covariant return | An override can narrow a reference result type covariantly. |
| Read a covariant result through the parent contract | The runtime override still executes through the broader return contract. |

[← 073. extends](073_extends.md) | [Course Index](../README.md) | [075. super →](075_super.md)
