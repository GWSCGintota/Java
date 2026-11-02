[← 074. Method overriding](074_Method_overriding.md) | [Course Index](../README.md) | [076. Polymorphism →](076_Polymorphism.md)

# Java super Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/075_super.md)

These exercises follow [075. super](../../Road%20Map/05_Object_Oriented_Programming/075_super.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete Main.java with nested helper types. Compile and run each independently. Inputs are non-null unless explicitly tested; arithmetic uses small values. Nested fixtures keep examples focused and do not replace cross-package access exercises.

## Question 1 — Initialize the superclass

**Task:** Initialize the superclass.

```java
public class Main {
static class Base { int value; Base(int value) { this.value=value; } String label() { return "Base"; } } static class Child extends Base { int value=9; Child(int initial) { super(initial); } @Override String label() { return super.label()+" Child"; } int baseValue() { return super.value; } int ownValue() { return this.value; } }
public static void main(String[] args) {
System.out.println(new Child(3).baseValue());
}
}
```

**Expected result:**
```text
3
```

**Concept:** super(...) delegates to a superclass constructor.

## Question 2 — Call the superclass implementation

**Task:** Call the superclass implementation.

```java
public class Main {
static class Base { int value; Base(int value) { this.value=value; } String label() { return "Base"; } } static class Child extends Base { int value=9; Child(int initial) { super(initial); } @Override String label() { return super.label()+" Child"; } int baseValue() { return super.value; } int ownValue() { return this.value; } }
public static void main(String[] args) {
System.out.println(new Child(3).label());
}
}
```

**Expected result:**
```text
Base Child
```

**Concept:** super.method selects the superclass implementation used by this subclass code.

## Question 3 — Read the subclass field

**Task:** Read the subclass field.

```java
public class Main {
static class Base { int value; Base(int value) { this.value=value; } String label() { return "Base"; } } static class Child extends Base { int value=9; Child(int initial) { super(initial); } @Override String label() { return super.label()+" Child"; } int baseValue() { return super.value; } int ownValue() { return this.value; } }
public static void main(String[] args) {
System.out.println(new Child(3).ownValue());
}
}
```

**Expected result:**
```text
9
```

**Concept:** this.value selects the subclass field in this declaration.

## Question 4 — Distinguish hidden fields

**Task:** Distinguish hidden fields.

```java
public class Main {
static class Base { int value; Base(int value) { this.value=value; } String label() { return "Base"; } } static class Child extends Base { int value=9; Child(int initial) { super(initial); } @Override String label() { return super.label()+" Child"; } int baseValue() { return super.value; } int ownValue() { return this.value; } }
public static void main(String[] args) {
Child child=new Child(3); System.out.println(child.baseValue()+":"+child.ownValue());
}
}
```

**Expected result:**
```text
3:9
```

**Concept:** Hidden fields remain distinct storage locations.

## Question 5 — Read a hidden field through a base reference

**Task:** Read a hidden field through a base reference.

```java
public class Main {
static class Base { int value; Base(int value) { this.value=value; } String label() { return "Base"; } } static class Child extends Base { int value=9; Child(int initial) { super(initial); } @Override String label() { return super.label()+" Child"; } int baseValue() { return super.value; } int ownValue() { return this.value; } }
public static void main(String[] args) {
Base value=new Child(3); System.out.println(value.value);
}
}
```

**Expected result:**
```text
3
```

**Concept:** Field selection depends on the declared reference type.

## Question 6 — Read the child field through a child reference

**Task:** Read the child field through a child reference.

```java
public class Main {
static class Base { int value; Base(int value) { this.value=value; } String label() { return "Base"; } } static class Child extends Base { int value=9; Child(int initial) { super(initial); } @Override String label() { return super.label()+" Child"; } int baseValue() { return super.value; } int ownValue() { return this.value; } }
public static void main(String[] args) {
Child value=new Child(3); System.out.println(value.value);
}
}
```

**Expected result:**
```text
9
```

**Concept:** Fields are hidden, not dynamically overridden.

## Question 7 — Dispatch an override that uses super

**Task:** Dispatch an override that uses super.

```java
public class Main {
static class Base { int value; Base(int value) { this.value=value; } String label() { return "Base"; } } static class Child extends Base { int value=9; Child(int initial) { super(initial); } @Override String label() { return super.label()+" Child"; } int baseValue() { return super.value; } int ownValue() { return this.value; } }
public static void main(String[] args) {
Base value=new Child(4); System.out.println(value.label());
}
}
```

**Expected result:**
```text
Base Child
```

**Concept:** Runtime dispatch and explicit superclass invocation can cooperate.

## Question 8 — Vary superclass initialization

**Task:** Vary superclass initialization.

```java
public class Main {
static class Base { int value; Base(int value) { this.value=value; } String label() { return "Base"; } } static class Child extends Base { int value=9; Child(int initial) { super(initial); } @Override String label() { return super.label()+" Child"; } int baseValue() { return super.value; } int ownValue() { return this.value; } }
public static void main(String[] args) {
Child first=new Child(1),second=new Child(2); System.out.println(first.baseValue()+":"+second.baseValue());
}
}
```

**Expected result:**
```text
1:2
```

**Concept:** Constructor arguments initialize each object's superclass state.

## Question 9 — Mutate only the subclass field

**Task:** Mutate only the subclass field.

```java
public class Main {
static class Base { int value; Base(int value) { this.value=value; } String label() { return "Base"; } } static class Child extends Base { int value=9; Child(int initial) { super(initial); } @Override String label() { return super.label()+" Child"; } int baseValue() { return super.value; } int ownValue() { return this.value; } }
public static void main(String[] args) {
Child child=new Child(3); child.value=7; System.out.println(child.baseValue()+":"+child.ownValue());
}
}
```

**Expected result:**
```text
3:7
```

**Concept:** Hiding does not alias the inherited field.

## Question 10 — Mutate the superclass field through a base view

**Task:** Mutate the superclass field through a base view.

```java
public class Main {
static class Base { int value; Base(int value) { this.value=value; } String label() { return "Base"; } } static class Child extends Base { int value=9; Child(int initial) { super(initial); } @Override String label() { return super.label()+" Child"; } int baseValue() { return super.value; } int ownValue() { return this.value; } }
public static void main(String[] args) {
Child child=new Child(3); ((Base)child).value=5; System.out.println(child.baseValue()+":"+child.ownValue());
}
}
```

**Expected result:**
```text
5:9
```

**Concept:** A cast changes the compile-time field view rather than creating another object.

---

## Interview Questions and Answers

### Interview Question 1 — What are the main uses of super?

**Answer:** Invoking an accessible superclass constructor and qualifying an accessible superclass member from subclass code.

### Interview Question 2 — Does super refer to a separate parent object?

**Answer:** No. It selects superclass behavior or state within the same constructed subclass object.

### Interview Question 3 — Can super access private superclass fields?

**Answer:** No. The ordinary access rules still apply.

### Interview Question 4 — How do super.method and an ordinary virtual call differ?

**Answer:** super.method explicitly selects the appropriate superclass implementation rather than starting ordinary dispatch at the runtime subclass override.

### Interview Question 5 — Why avoid hiding fields with the same name?

**Answer:** It creates separate storage with compile-time selection rules that can surprise readers. Clear distinct names or encapsulated accessors are usually easier to maintain.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Initialize the superclass | super(...) delegates to a superclass constructor. |
| Call the superclass implementation | super.method selects the superclass implementation used by this subclass code. |
| Read the subclass field | this.value selects the subclass field in this declaration. |
| Distinguish hidden fields | Hidden fields remain distinct storage locations. |
| Read a hidden field through a base reference | Field selection depends on the declared reference type. |

[← 074. Method overriding](074_Method_overriding.md) | [Course Index](../README.md) | [076. Polymorphism →](076_Polymorphism.md)
