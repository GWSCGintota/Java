[← 058. Methods](058_Methods.md) | [Course Index](../README.md) | [060. Constructor overloading →](060_Constructor_overloading.md)

# Java Constructors Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/059_Constructors.md)

These exercises follow [059. Constructors](../../Road%20Map/05_Object_Oriented_Programming/059_Constructors.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete, independent Main.java. Helper types are nested to keep examples in one file. Compile and run each question separately. Inputs are non-null unless explicitly tested, and arithmetic uses small values.

## Question 1 — Initialize both fields

**Task:** Initialize both fields.

```java
public class Main {
static class User { final String name; final int age; User(String name,int age) { if(name==null) { throw new IllegalArgumentException("Missing name"); } if(age<0) { throw new IllegalArgumentException("Negative age"); } this.name=name; this.age=age; } }
public static void main(String[] args) {
User user=new User("Ada",20); System.out.println(user.name+":"+user.age);
}
}
```

**Expected result:**
```text
Ada:20
```

**Concept:** Constructors establish initial instance state.

## Question 2 — Construct a different instance

**Task:** Construct a different instance.

```java
public class Main {
static class User { final String name; final int age; User(String name,int age) { if(name==null) { throw new IllegalArgumentException("Missing name"); } if(age<0) { throw new IllegalArgumentException("Negative age"); } this.name=name; this.age=age; } }
public static void main(String[] args) {
User user=new User("Grace",25); System.out.println(user.name);
}
}
```

**Expected result:**
```text
Grace
```

**Concept:** Constructor arguments vary each instance's initial values.

## Question 3 — Accept a boundary age

**Task:** Accept a boundary age.

```java
public class Main {
static class User { final String name; final int age; User(String name,int age) { if(name==null) { throw new IllegalArgumentException("Missing name"); } if(age<0) { throw new IllegalArgumentException("Negative age"); } this.name=name; this.age=age; } }
public static void main(String[] args) {
System.out.println(new User("Ada",0).age);
}
}
```

**Expected result:**
```text
0
```

**Concept:** Validate according to the domain rather than rejecting valid endpoints.

## Question 4 — Reject negative age

**Task:** Reject negative age.

```java
public class Main {
static class User { final String name; final int age; User(String name,int age) { if(name==null) { throw new IllegalArgumentException("Missing name"); } if(age<0) { throw new IllegalArgumentException("Negative age"); } this.name=name; this.age=age; } }
public static void main(String[] args) {
try { new User("Ada",-1); } catch(IllegalArgumentException e) { System.out.println(e.getMessage()); }
}
}
```

**Expected result:**
```text
Negative age
```

**Concept:** A constructor can reject an invalid initial state.

## Question 5 — Reject a missing name

**Task:** Reject a missing name.

```java
public class Main {
static class User { final String name; final int age; User(String name,int age) { if(name==null) { throw new IllegalArgumentException("Missing name"); } if(age<0) { throw new IllegalArgumentException("Negative age"); } this.name=name; this.age=age; } }
public static void main(String[] args) {
try { new User(null,20); } catch(IllegalArgumentException e) { System.out.println(e.getMessage()); }
}
}
```

**Expected result:**
```text
Missing name
```

**Concept:** Fail construction before exposing an invalid object.

## Question 6 — Compare construction identity

**Task:** Compare construction identity.

```java
public class Main {
static class User { final String name; final int age; User(String name,int age) { if(name==null) { throw new IllegalArgumentException("Missing name"); } if(age<0) { throw new IllegalArgumentException("Negative age"); } this.name=name; this.age=age; } }
public static void main(String[] args) {
System.out.println(new User("Ada",20)==new User("Ada",20));
}
}
```

**Expected result:**
```text
false
```

**Concept:** Matching arguments do not imply shared identity.

## Question 7 — Initialize final fields

**Task:** Initialize final fields.

```java
public class Main {
static class User { final String name; final int age; User(String name,int age) { if(name==null) { throw new IllegalArgumentException("Missing name"); } if(age<0) { throw new IllegalArgumentException("Negative age"); } this.name=name; this.age=age; } }
public static void main(String[] args) {
User user=new User("Bob",30); System.out.println(user.age);
}
}
```

**Expected result:**
```text
30
```

**Concept:** Constructors can assign blank final fields.

## Question 8 — Build an object array

**Task:** Build an object array.

```java
public class Main {
static class User { final String name; final int age; User(String name,int age) { if(name==null) { throw new IllegalArgumentException("Missing name"); } if(age<0) { throw new IllegalArgumentException("Negative age"); } this.name=name; this.age=age; } }
public static void main(String[] args) {
User[] users={new User("Ada",20),new User("Bob",30)}; System.out.println(users.length);
}
}
```

**Expected result:**
```text
2
```

**Concept:** Each new expression invokes the constructor separately.

## Question 9 — Use explicit empty-name policy

**Task:** Use explicit empty-name policy.

```java
public class Main {
static class User { final String name; final int age; User(String name,int age) { if(name==null) { throw new IllegalArgumentException("Missing name"); } if(age<0) { throw new IllegalArgumentException("Negative age"); } this.name=name; this.age=age; } }
public static void main(String[] args) {
System.out.println("["+new User("",20).name+"]");
}
}
```

**Expected result:**
```text
[]
```

**Concept:** This constructor rejects null but deliberately permits empty text.

## Question 10 — Observe validation order

**Task:** Observe validation order.

```java
public class Main {
static class User { final String name; final int age; User(String name,int age) { if(name==null) { throw new IllegalArgumentException("Missing name"); } if(age<0) { throw new IllegalArgumentException("Negative age"); } this.name=name; this.age=age; } }
public static void main(String[] args) {
try { new User(null,-1); } catch(IllegalArgumentException e) { System.out.println(e.getMessage()); }
}
}
```

**Expected result:**
```text
Missing name
```

**Concept:** The first failed check determines the reported failure.

---

## Interview Questions and Answers

### Interview Question 1 — Does a constructor declare a return type?

**Answer:** No. Its name matches its class and it has no return type, including no void keyword.

### Interview Question 2 — When does the compiler provide a default constructor?

**Answer:** For an ordinary class with no declared constructor, it can provide a no-argument constructor subject to superclass-constructor accessibility and other language rules.

### Interview Question 3 — Are constructors inherited?

**Answer:** No. Subclass constructors initialize the subclass and invoke an appropriate superclass constructor.

### Interview Question 4 — Why validate in a constructor?

**Answer:** To establish invariants before a newly constructed object becomes usable. Avoid leaking this during construction.

### Interview Question 5 — Can construction fail?

**Answer:** Yes. A thrown exception can prevent the new expression from producing a successfully constructed result; resource cleanup still needs an intentional design.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Initialize both fields | Constructors establish initial instance state. |
| Construct a different instance | Constructor arguments vary each instance's initial values. |
| Accept a boundary age | Validate according to the domain rather than rejecting valid endpoints. |
| Reject negative age | A constructor can reject an invalid initial state. |
| Reject a missing name | Fail construction before exposing an invalid object. |

[← 058. Methods](058_Methods.md) | [Course Index](../README.md) | [060. Constructor overloading →](060_Constructor_overloading.md)
