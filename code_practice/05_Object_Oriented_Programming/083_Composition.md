[← 082. Interface default methods](082_Interface_default_methods.md) | [Course Index](../README.md) | [084. Aggregation →](084_Aggregation.md)

# Java Composition Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/083_Composition.md)

These exercises follow [083. Composition](../../Road%20Map/05_Object_Oriented_Programming/083_Composition.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete Main.java with nested helper types. Run each question independently. Examples define their own ownership and null policies; Java references alone do not enforce UML lifecycle relationships.

## Question 1 — Construct a whole with an owned part

**Task:** Construct a whole with an owned part.

```java
public class Main {
static class Engine { private boolean running; void start() { running=true; } boolean running() { return running; } } static class Car { private final Engine engine=new Engine(); void start() { engine.start(); } boolean running() { return engine.running(); } }
public static void main(String[] args) throws Exception {
System.out.println(new Car().running());
}
}
```

**Expected result:**
```text
false
```

**Concept:** Car constructs its own Engine in this ownership model.

## Question 2 — Delegate behavior to an owned part

**Task:** Delegate behavior to an owned part.

```java
public class Main {
static class Engine { private boolean running; void start() { running=true; } boolean running() { return running; } } static class Car { private final Engine engine=new Engine(); void start() { engine.start(); } boolean running() { return engine.running(); } }
public static void main(String[] args) throws Exception {
Car car=new Car(); car.start(); System.out.println(car.running());
}
}
```

**Expected result:**
```text
true
```

**Concept:** Composition can forward operations to a contained collaborator.

## Question 3 — Keep parts separate between whole objects

**Task:** Keep parts separate between whole objects.

```java
public class Main {
static class Engine { private boolean running; void start() { running=true; } boolean running() { return running; } } static class Car { private final Engine engine=new Engine(); void start() { engine.start(); } boolean running() { return engine.running(); } }
public static void main(String[] args) throws Exception {
Car first=new Car(),second=new Car(); first.start(); System.out.println(first.running()+":"+second.running());
}
}
```

**Expected result:**
```text
true:false
```

**Concept:** Each Car creates an independent Engine.

## Question 4 — Repeat an idempotent domain action

**Task:** Repeat an idempotent domain action.

```java
public class Main {
static class Engine { private boolean running; void start() { running=true; } boolean running() { return running; } } static class Car { private final Engine engine=new Engine(); void start() { engine.start(); } boolean running() { return engine.running(); } }
public static void main(String[] args) throws Exception {
Car car=new Car(); car.start(); car.start(); System.out.println(car.running());
}
}
```

**Expected result:**
```text
true
```

**Concept:** This start operation establishes a state rather than incrementing a counter.

## Question 5 — Use only the whole-object API

**Task:** Use only the whole-object API.

```java
public class Main {
static class Engine { private boolean running; void start() { running=true; } boolean running() { return running; } } static class Car { private final Engine engine=new Engine(); void start() { engine.start(); } boolean running() { return engine.running(); } }
public static void main(String[] args) throws Exception {
Car car=new Car(); System.out.println(car.running()); car.start(); System.out.println(car.running());
}
}
```

**Expected result:**
```text
false
true
```

**Concept:** Callers need not manipulate the Engine directly.

## Question 6 — Store several whole objects

**Task:** Store several whole objects.

```java
public class Main {
static class Engine { private boolean running; void start() { running=true; } boolean running() { return running; } } static class Car { private final Engine engine=new Engine(); void start() { engine.start(); } boolean running() { return engine.running(); } }
public static void main(String[] args) throws Exception {
Car[] cars={new Car(),new Car()}; for(Car car:cars) { car.start(); } System.out.println(cars[0].running()+":"+cars[1].running());
}
}
```

**Expected result:**
```text
true:true
```

**Concept:** Containment can scale to collections of separate aggregates.

## Question 7 — Observe aliasing of the whole

**Task:** Observe aliasing of the whole.

```java
public class Main {
static class Engine { private boolean running; void start() { running=true; } boolean running() { return running; } } static class Car { private final Engine engine=new Engine(); void start() { engine.start(); } boolean running() { return engine.running(); } }
public static void main(String[] args) throws Exception {
Car first=new Car(),alias=first; alias.start(); System.out.println(first.running());
}
}
```

**Expected result:**
```text
true
```

**Concept:** Two references to the whole share its owned state.

## Question 8 — Replace a whole-object reference

**Task:** Replace a whole-object reference.

```java
public class Main {
static class Engine { private boolean running; void start() { running=true; } boolean running() { return running; } } static class Car { private final Engine engine=new Engine(); void start() { engine.start(); } boolean running() { return engine.running(); } }
public static void main(String[] args) throws Exception {
Car first=new Car(); first.start(); Car second=new Car(); System.out.println(second.running());
}
}
```

**Expected result:**
```text
false
```

**Concept:** Creating a replacement whole creates fresh contained state in this fixture.

## Question 9 — Check encapsulated representation

**Task:** Check encapsulated representation.

```java
public class Main {
static class Engine { private boolean running; void start() { running=true; } boolean running() { return running; } } static class Car { private final Engine engine=new Engine(); void start() { engine.start(); } boolean running() { return engine.running(); } }
public static void main(String[] args) throws Exception {
System.out.println(java.lang.reflect.Modifier.isPrivate(Car.class.getDeclaredField("engine").getModifiers()));
}
}
```

**Expected result:**
```text
true
```

**Concept:** Hiding the part helps control access through the whole.

## Question 10 — Inspect fixed part ownership

**Task:** Inspect fixed part ownership.

```java
public class Main {
static class Engine { private boolean running; void start() { running=true; } boolean running() { return running; } } static class Car { private final Engine engine=new Engine(); void start() { engine.start(); } boolean running() { return engine.running(); } }
public static void main(String[] args) throws Exception {
System.out.println(java.lang.reflect.Modifier.isFinal(Car.class.getDeclaredField("engine").getModifiers()));
}
}
```

**Expected result:**
```text
true
```

**Concept:** final fixes the part reference but does not make the Engine immutable.

---

## Interview Questions and Answers

### Interview Question 1 — What is composition?

**Answer:** A design in which a whole is built from collaborating parts, often with strong ownership and lifecycle responsibility defined by the application.

### Interview Question 2 — Does Java have a composition keyword?

**Answer:** No. Fields, construction, access control, and ownership policy express the relationship.

### Interview Question 3 — How does composition differ from inheritance?

**Answer:** Composition delegates to collaborators; inheritance establishes a subtype contract. A has-a relationship does not automatically justify an is-a hierarchy.

### Interview Question 4 — Does garbage collection enforce UML composition lifetimes?

**Answer:** No. Reachability determines memory reclamation; aliases and resource ownership must still be designed explicitly.

### Interview Question 5 — Why hide owned parts?

**Answer:** It prevents callers from bypassing the whole's invariants and reduces dependence on internal representation.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Construct a whole with an owned part | Car constructs its own Engine in this ownership model. |
| Delegate behavior to an owned part | Composition can forward operations to a contained collaborator. |
| Keep parts separate between whole objects | Each Car creates an independent Engine. |
| Repeat an idempotent domain action | This start operation establishes a state rather than incrementing a counter. |
| Use only the whole-object API | Callers need not manipulate the Engine directly. |

[← 082. Interface default methods](082_Interface_default_methods.md) | [Course Index](../README.md) | [084. Aggregation →](084_Aggregation.md)
