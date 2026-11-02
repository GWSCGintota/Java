[← 071. Packages](071_Packages.md) | [Course Index](../README.md) | [073. extends →](073_extends.md)

# Java Inheritance Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/072_Inheritance.md)

These exercises follow [072. Inheritance](../../Road%20Map/05_Object_Oriented_Programming/072_Inheritance.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete Main.java with nested helper types. Compile and run each independently. Inputs are non-null unless explicitly tested; arithmetic uses small values. Nested fixtures keep examples focused and do not replace cross-package access exercises.

## Question 1 — Construct a subclass

**Task:** Construct a subclass.

```java
public class Main {
static class Animal { final String name; Animal(String name) { this.name=name; } String name() { return name; } String sound() { return "Animal"; } } static class Dog extends Animal { Dog(String name) { super(name); } @Override String sound() { return "Woof"; } String fetch() { return "Fetched"; } }
public static void main(String[] args) {
Dog dog=new Dog("Rex"); System.out.println(dog.name());
}
}
```

**Expected result:**
```text
Rex
```

**Concept:** A subclass object includes initialized superclass state.

## Question 2 — Call inherited behavior

**Task:** Call inherited behavior.

```java
public class Main {
static class Animal { final String name; Animal(String name) { this.name=name; } String name() { return name; } String sound() { return "Animal"; } } static class Dog extends Animal { Dog(String name) { super(name); } @Override String sound() { return "Woof"; } String fetch() { return "Fetched"; } }
public static void main(String[] args) {
System.out.println(new Dog("Rex").name());
}
}
```

**Expected result:**
```text
Rex
```

**Concept:** Accessible superclass behavior can be inherited.

## Question 3 — Call an override

**Task:** Call an override.

```java
public class Main {
static class Animal { final String name; Animal(String name) { this.name=name; } String name() { return name; } String sound() { return "Animal"; } } static class Dog extends Animal { Dog(String name) { super(name); } @Override String sound() { return "Woof"; } String fetch() { return "Fetched"; } }
public static void main(String[] args) {
System.out.println(new Dog("Rex").sound());
}
}
```

**Expected result:**
```text
Woof
```

**Concept:** A subclass can specialize an inherited instance operation.

## Question 4 — Use a superclass reference

**Task:** Use a superclass reference.

```java
public class Main {
static class Animal { final String name; Animal(String name) { this.name=name; } String name() { return name; } String sound() { return "Animal"; } } static class Dog extends Animal { Dog(String name) { super(name); } @Override String sound() { return "Woof"; } String fetch() { return "Fetched"; } }
public static void main(String[] args) {
Animal animal=new Dog("Rex"); System.out.println(animal.sound());
}
}
```

**Expected result:**
```text
Woof
```

**Concept:** Overridden behavior dispatches on the runtime receiver.

## Question 5 — Call subclass-specific behavior

**Task:** Call subclass-specific behavior.

```java
public class Main {
static class Animal { final String name; Animal(String name) { this.name=name; } String name() { return name; } String sound() { return "Animal"; } } static class Dog extends Animal { Dog(String name) { super(name); } @Override String sound() { return "Woof"; } String fetch() { return "Fetched"; } }
public static void main(String[] args) {
System.out.println(new Dog("Rex").fetch());
}
}
```

**Expected result:**
```text
Fetched
```

**Concept:** A subclass can add operations beyond the superclass contract.

## Question 6 — Compare parent and child behavior

**Task:** Compare parent and child behavior.

```java
public class Main {
static class Animal { final String name; Animal(String name) { this.name=name; } String name() { return name; } String sound() { return "Animal"; } } static class Dog extends Animal { Dog(String name) { super(name); } @Override String sound() { return "Woof"; } String fetch() { return "Fetched"; } }
public static void main(String[] args) {
System.out.println(new Animal("A").sound()); System.out.println(new Dog("D").sound());
}
}
```

**Expected result:**
```text
Animal
Woof
```

**Concept:** Different runtime classes can implement one inherited operation differently.

## Question 7 — Inspect the parent class

**Task:** Inspect the parent class.

```java
public class Main {
static class Animal { final String name; Animal(String name) { this.name=name; } String name() { return name; } String sound() { return "Animal"; } } static class Dog extends Animal { Dog(String name) { super(name); } @Override String sound() { return "Woof"; } String fetch() { return "Fetched"; } }
public static void main(String[] args) {
System.out.println(Dog.class.getSuperclass().getSimpleName());
}
}
```

**Expected result:**
```text
Animal
```

**Concept:** Class inheritance establishes a direct superclass relationship.

## Question 8 — Test substitutability by type

**Task:** Test substitutability by type.

```java
public class Main {
static class Animal { final String name; Animal(String name) { this.name=name; } String name() { return name; } String sound() { return "Animal"; } } static class Dog extends Animal { Dog(String name) { super(name); } @Override String sound() { return "Woof"; } String fetch() { return "Fetched"; } }
public static void main(String[] args) {
Animal animal=new Dog("Rex"); System.out.println(animal instanceof Animal);
}
}
```

**Expected result:**
```text
true
```

**Concept:** A subclass instance is also an instance of its superclass.

## Question 9 — Traverse mixed instances

**Task:** Traverse mixed instances.

```java
public class Main {
static class Animal { final String name; Animal(String name) { this.name=name; } String name() { return name; } String sound() { return "Animal"; } } static class Dog extends Animal { Dog(String name) { super(name); } @Override String sound() { return "Woof"; } String fetch() { return "Fetched"; } }
public static void main(String[] args) {
Animal[] animals={new Animal("A"),new Dog("D")}; for(Animal animal:animals) { System.out.println(animal.sound()); }
}
}
```

**Expected result:**
```text
Animal
Woof
```

**Concept:** A superclass array can hold subtype instances.

## Question 10 — Keep inherited state per instance

**Task:** Keep inherited state per instance.

```java
public class Main {
static class Animal { final String name; Animal(String name) { this.name=name; } String name() { return name; } String sound() { return "Animal"; } } static class Dog extends Animal { Dog(String name) { super(name); } @Override String sound() { return "Woof"; } String fetch() { return "Fetched"; } }
public static void main(String[] args) {
Dog first=new Dog("Rex"),second=new Dog("Spot"); System.out.println(first.name()+":"+second.name());
}
}
```

**Expected result:**
```text
Rex:Spot
```

**Concept:** Inheritance does not merge separate object identities or state.

---

## Interview Questions and Answers

### Interview Question 1 — What is inheritance?

**Answer:** A subtype relationship through which a class can inherit accessible state and behavior and specialize supported operations.

### Interview Question 2 — How many direct class superclasses can a Java class have?

**Answer:** At most one. A class can additionally implement multiple interfaces.

### Interview Question 3 — Are constructors inherited?

**Answer:** No. Subclass construction must invoke an appropriate superclass constructor.

### Interview Question 4 — Does inheritance expose private superclass fields directly?

**Answer:** No. Superclass state still exists, but private fields are not directly accessible through ordinary subclass source access.

### Interview Question 5 — When should composition be preferred?

**Answer:** When the relationship is about using a collaborator rather than satisfying a meaningful behavioral is-a contract, or when inheritance would create tight coupling to representation.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Construct a subclass | A subclass object includes initialized superclass state. |
| Call inherited behavior | Accessible superclass behavior can be inherited. |
| Call an override | A subclass can specialize an inherited instance operation. |
| Use a superclass reference | Overridden behavior dispatches on the runtime receiver. |
| Call subclass-specific behavior | A subclass can add operations beyond the superclass contract. |

[← 071. Packages](071_Packages.md) | [Course Index](../README.md) | [073. extends →](073_extends.md)
