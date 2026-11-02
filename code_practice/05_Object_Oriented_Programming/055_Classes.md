[← 054. Searching arrays](../04_Arrays/054_Searching_arrays.md) | [Course Index](../README.md) | [056. Objects →](056_Objects.md)

# Java Classes Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/055_Classes.md)

These exercises follow [055. Classes](../../Road%20Map/05_Object_Oriented_Programming/055_Classes.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete, independent Main.java. Helper types are nested to keep examples in one file. Compile and run each question separately. Inputs are non-null unless explicitly tested, and arithmetic uses small values.

## Question 1 — Create and print a Person label

**Task:** Create and print a Person label.

```java
public class Main {
static class Person { String name; int age; Person(String name,int age) { this.name=name; this.age=age; } String label() { return name+":"+age; } }
public static void main(String[] args) {
Person person=new Person("Ada",20); System.out.println(person.label());
}
}
```

**Expected result:**
```text
Ada:20
```

**Concept:** A class defines the state and behavior of its instances.

## Question 2 — Read a declared field

**Task:** Read a declared field.

```java
public class Main {
static class Person { String name; int age; Person(String name,int age) { this.name=name; this.age=age; } String label() { return name+":"+age; } }
public static void main(String[] args) {
Person person=new Person("Grace",25); System.out.println(person.name);
}
}
```

**Expected result:**
```text
Grace
```

**Concept:** Fields store instance state.

## Question 3 — Update an instance field

**Task:** Update an instance field.

```java
public class Main {
static class Person { String name; int age; Person(String name,int age) { this.name=name; this.age=age; } String label() { return name+":"+age; } }
public static void main(String[] args) {
Person person=new Person("Ada",20); person.age=21; System.out.println(person.age);
}
}
```

**Expected result:**
```text
21
```

**Concept:** Non-final fields can change after construction.

## Question 4 — Create independent instances

**Task:** Create independent instances.

```java
public class Main {
static class Person { String name; int age; Person(String name,int age) { this.name=name; this.age=age; } String label() { return name+":"+age; } }
public static void main(String[] args) {
Person first=new Person("Ada",20), second=new Person("Bob",30); first.age++; System.out.println(second.age);
}
}
```

**Expected result:**
```text
30
```

**Concept:** Each instance owns its instance-field values.

## Question 5 — Call an instance method twice

**Task:** Call an instance method twice.

```java
public class Main {
static class Person { String name; int age; Person(String name,int age) { this.name=name; this.age=age; } String label() { return name+":"+age; } }
public static void main(String[] args) {
Person person=new Person("Ada",20); System.out.println(person.label()); person.age++; System.out.println(person.label());
}
}
```

**Expected result:**
```text
Ada:20
Ada:21
```

**Concept:** Methods can read current receiver state.

## Question 6 — Use a class as an array component type

**Task:** Use a class as an array component type.

```java
public class Main {
static class Person { String name; int age; Person(String name,int age) { this.name=name; this.age=age; } String label() { return name+":"+age; } }
public static void main(String[] args) {
Person[] people={new Person("Ada",20),new Person("Bob",30)}; System.out.println(people.length);
}
}
```

**Expected result:**
```text
2
```

**Concept:** Classes can define reference-array component types.

## Question 7 — Pass an object to a collection

**Task:** Pass an object to a collection.

```java
public class Main {
static class Person { String name; int age; Person(String name,int age) { this.name=name; this.age=age; } String label() { return name+":"+age; } }
public static void main(String[] args) {
java.util.List<Person> people=new java.util.ArrayList<>(); people.add(new Person("Ada",20)); System.out.println(people.get(0).name);
}
}
```

**Expected result:**
```text
Ada
```

**Concept:** Class types can be generic type arguments.

## Question 8 — Inspect the runtime class

**Task:** Inspect the runtime class.

```java
public class Main {
static class Person { String name; int age; Person(String name,int age) { this.name=name; this.age=age; } String label() { return name+":"+age; } }
public static void main(String[] args) {
Person person=new Person("Ada",20); System.out.println(person.getClass().getSimpleName());
}
}
```

**Expected result:**
```text
Person
```

**Concept:** Objects carry runtime class information.

## Question 9 — Compare instance identity

**Task:** Compare instance identity.

```java
public class Main {
static class Person { String name; int age; Person(String name,int age) { this.name=name; this.age=age; } String label() { return name+":"+age; } }
public static void main(String[] args) {
Person first=new Person("Ada",20), second=new Person("Ada",20); System.out.println(first==second);
}
}
```

**Expected result:**
```text
false
```

**Concept:** Equal field values do not imply the same instance.

## Question 10 — Guard a missing instance

**Task:** Guard a missing instance.

```java
public class Main {
static class Person { String name; int age; Person(String name,int age) { this.name=name; this.age=age; } String label() { return name+":"+age; } }
public static void main(String[] args) {
Person person=null; System.out.println(person==null ? "Missing" : person.label());
}
}
```

**Expected result:**
```text
Missing
```

**Concept:** A variable of a class type can hold null.

---

## Interview Questions and Answers

### Interview Question 1 — What does a class define?

**Answer:** A reference type describing fields, methods, constructors, and related members. Instances hold receiver-specific state.

### Interview Question 2 — How does a class differ from an object?

**Answer:** A class defines a type and implementation; an object is a particular runtime instance.

### Interview Question 3 — Is a class declaration the same as constructing an object?

**Answer:** No. A declaration defines the type; a new expression invokes a constructor and creates an instance.

### Interview Question 4 — Can multiple objects use one class?

**Answer:** Yes. Each normally has separate instance fields, while static members belong to the class rather than each object.

### Interview Question 5 — Why keep classes focused?

**Answer:** A cohesive class has a clear responsibility and a manageable contract, making behavior easier to understand and change.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Create and print a Person label | A class defines the state and behavior of its instances. |
| Read a declared field | Fields store instance state. |
| Update an instance field | Non-final fields can change after construction. |
| Create independent instances | Each instance owns its instance-field values. |
| Call an instance method twice | Methods can read current receiver state. |

[← 054. Searching arrays](../04_Arrays/054_Searching_arrays.md) | [Course Index](../README.md) | [056. Objects →](056_Objects.md)
