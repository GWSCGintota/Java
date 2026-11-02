[← 077. Abstraction](077_Abstraction.md) | [Course Index](../README.md) | [079. Abstract methods →](079_Abstract_methods.md)

# Java Abstract classes Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/078_Abstract_classes.md)

These exercises follow [078. Abstract classes](../../Road%20Map/05_Object_Oriented_Programming/078_Abstract_classes.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete Main.java with nested helper types. Compile and run each independently. Inputs are non-null unless explicitly tested; arithmetic uses small values. Nested fixtures keep examples focused and do not replace cross-package access exercises.

## Question 1 — Instantiate a concrete subclass

**Task:** Instantiate a concrete subclass.

```java
public class Main {
static abstract class Shape { private final String name; Shape(String name) { this.name=name; } String name() { return name; } abstract int area(); String describe() { return name()+":"+area(); } } static class Square extends Shape { final int side; Square(int side) { super("Square"); this.side=side; } @Override int area() { return side*side; } } static class Rectangle extends Shape { final int width,height; Rectangle(int width,int height) { super("Rectangle"); this.width=width; this.height=height; } @Override int area() { return width*height; } }
public static void main(String[] args) {
System.out.println(new Square(3).area());
}
}
```

**Expected result:**
```text
9
```

**Concept:** An abstract base cannot be instantiated directly, but a concrete subclass can.

## Question 2 — Run inherited concrete behavior

**Task:** Run inherited concrete behavior.

```java
public class Main {
static abstract class Shape { private final String name; Shape(String name) { this.name=name; } String name() { return name; } abstract int area(); String describe() { return name()+":"+area(); } } static class Square extends Shape { final int side; Square(int side) { super("Square"); this.side=side; } @Override int area() { return side*side; } } static class Rectangle extends Shape { final int width,height; Rectangle(int width,int height) { super("Rectangle"); this.width=width; this.height=height; } @Override int area() { return width*height; } }
public static void main(String[] args) {
System.out.println(new Square(3).name());
}
}
```

**Expected result:**
```text
Square
```

**Concept:** Abstract classes can contain ordinary implemented methods.

## Question 3 — Initialize base state

**Task:** Initialize base state.

```java
public class Main {
static abstract class Shape { private final String name; Shape(String name) { this.name=name; } String name() { return name; } abstract int area(); String describe() { return name()+":"+area(); } } static class Square extends Shape { final int side; Square(int side) { super("Square"); this.side=side; } @Override int area() { return side*side; } } static class Rectangle extends Shape { final int width,height; Rectangle(int width,int height) { super("Rectangle"); this.width=width; this.height=height; } @Override int area() { return width*height; } }
public static void main(String[] args) {
System.out.println(new Rectangle(2,4).name());
}
}
```

**Expected result:**
```text
Rectangle
```

**Concept:** An abstract class can have a constructor and instance fields.

## Question 4 — Combine concrete and abstract operations

**Task:** Combine concrete and abstract operations.

```java
public class Main {
static abstract class Shape { private final String name; Shape(String name) { this.name=name; } String name() { return name; } abstract int area(); String describe() { return name()+":"+area(); } } static class Square extends Shape { final int side; Square(int side) { super("Square"); this.side=side; } @Override int area() { return side*side; } } static class Rectangle extends Shape { final int width,height; Rectangle(int width,int height) { super("Rectangle"); this.width=width; this.height=height; } @Override int area() { return width*height; } }
public static void main(String[] args) {
System.out.println(new Square(3).describe());
}
}
```

**Expected result:**
```text
Square:9
```

**Concept:** A concrete base method can call an abstract operation implemented by its receiver.

## Question 5 — Use an abstract reference

**Task:** Use an abstract reference.

```java
public class Main {
static abstract class Shape { private final String name; Shape(String name) { this.name=name; } String name() { return name; } abstract int area(); String describe() { return name()+":"+area(); } } static class Square extends Shape { final int side; Square(int side) { super("Square"); this.side=side; } @Override int area() { return side*side; } } static class Rectangle extends Shape { final int width,height; Rectangle(int width,int height) { super("Rectangle"); this.width=width; this.height=height; } @Override int area() { return width*height; } }
public static void main(String[] args) {
Shape shape=new Rectangle(2,4); System.out.println(shape.area());
}
}
```

**Expected result:**
```text
8
```

**Concept:** Abstract types can be declared reference types.

## Question 6 — Traverse several concrete types

**Task:** Traverse several concrete types.

```java
public class Main {
static abstract class Shape { private final String name; Shape(String name) { this.name=name; } String name() { return name; } abstract int area(); String describe() { return name()+":"+area(); } } static class Square extends Shape { final int side; Square(int side) { super("Square"); this.side=side; } @Override int area() { return side*side; } } static class Rectangle extends Shape { final int width,height; Rectangle(int width,int height) { super("Rectangle"); this.width=width; this.height=height; } @Override int area() { return width*height; } }
public static void main(String[] args) {
Shape[] values={new Square(2),new Rectangle(2,3)}; for(Shape value:values) { System.out.println(value.describe()); }
}
}
```

**Expected result:**
```text
Square:4
Rectangle:6
```

**Concept:** Shared base behavior can dispatch to different subclass implementations.

## Question 7 — Inspect abstract metadata

**Task:** Inspect abstract metadata.

```java
public class Main {
static abstract class Shape { private final String name; Shape(String name) { this.name=name; } String name() { return name; } abstract int area(); String describe() { return name()+":"+area(); } } static class Square extends Shape { final int side; Square(int side) { super("Square"); this.side=side; } @Override int area() { return side*side; } } static class Rectangle extends Shape { final int width,height; Rectangle(int width,int height) { super("Rectangle"); this.width=width; this.height=height; } @Override int area() { return width*height; } }
public static void main(String[] args) {
System.out.println(java.lang.reflect.Modifier.isAbstract(Shape.class.getModifiers()));
}
}
```

**Expected result:**
```text
true
```

**Concept:** abstract is a declaration property, not a separate runtime object kind.

## Question 8 — Create an anonymous concrete subclass

**Task:** Create an anonymous concrete subclass.

```java
public class Main {
static abstract class Shape { private final String name; Shape(String name) { this.name=name; } String name() { return name; } abstract int area(); String describe() { return name()+":"+area(); } } static class Square extends Shape { final int side; Square(int side) { super("Square"); this.side=side; } @Override int area() { return side*side; } } static class Rectangle extends Shape { final int width,height; Rectangle(int width,int height) { super("Rectangle"); this.width=width; this.height=height; } @Override int area() { return width*height; } }
public static void main(String[] args) {
Shape shape=new Shape("Unit") { @Override int area() { return 1; } }; System.out.println(shape.describe());
}
}
```

**Expected result:**
```text
Unit:1
```

**Concept:** An anonymous subclass can implement the required abstract operation.

## Question 9 — Test a valid zero dimension

**Task:** Test a valid zero dimension.

```java
public class Main {
static abstract class Shape { private final String name; Shape(String name) { this.name=name; } String name() { return name; } abstract int area(); String describe() { return name()+":"+area(); } } static class Square extends Shape { final int side; Square(int side) { super("Square"); this.side=side; } @Override int area() { return side*side; } } static class Rectangle extends Shape { final int width,height; Rectangle(int width,int height) { super("Rectangle"); this.width=width; this.height=height; } @Override int area() { return width*height; } }
public static void main(String[] args) {
System.out.println(new Square(0).describe());
}
}
```

**Expected result:**
```text
Square:0
```

**Concept:** The concrete constructor defines supported dimension values.

## Question 10 — Keep base state per object

**Task:** Keep base state per object.

```java
public class Main {
static abstract class Shape { private final String name; Shape(String name) { this.name=name; } String name() { return name; } abstract int area(); String describe() { return name()+":"+area(); } } static class Square extends Shape { final int side; Square(int side) { super("Square"); this.side=side; } @Override int area() { return side*side; } } static class Rectangle extends Shape { final int width,height; Rectangle(int width,int height) { super("Rectangle"); this.width=width; this.height=height; } @Override int area() { return width*height; } }
public static void main(String[] args) {
Shape first=new Square(2),second=new Rectangle(1,2); System.out.println(first.name()+":"+second.name());
}
}
```

**Expected result:**
```text
Square:Rectangle
```

**Concept:** Abstract base fields still belong to individual instances.

---

## Interview Questions and Answers

### Interview Question 1 — Can an abstract class have constructors?

**Answer:** Yes. They initialize its portion of a concrete subclass object; the abstract class itself cannot be directly instantiated.

### Interview Question 2 — Must an abstract class declare an abstract method?

**Answer:** No. It can be abstract solely to prevent direct instantiation or require specialized subclasses.

### Interview Question 3 — Can an abstract class contain fields and implemented methods?

**Answer:** Yes. It can provide shared state, constructors, concrete methods, and abstract extension points.

### Interview Question 4 — Can a class be both abstract and final?

**Answer:** No. abstract requires the possibility of completing behavior in subclasses, while final prohibits subclassing.

### Interview Question 5 — When choose an abstract class over an interface?

**Answer:** When shared implementation or instance-state construction is integral to a meaningful class hierarchy; use interfaces for contracts without requiring one class superclass.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Instantiate a concrete subclass | An abstract base cannot be instantiated directly, but a concrete subclass can. |
| Run inherited concrete behavior | Abstract classes can contain ordinary implemented methods. |
| Initialize base state | An abstract class can have a constructor and instance fields. |
| Combine concrete and abstract operations | A concrete base method can call an abstract operation implemented by its receiver. |
| Use an abstract reference | Abstract types can be declared reference types. |

[← 077. Abstraction](077_Abstraction.md) | [Course Index](../README.md) | [079. Abstract methods →](079_Abstract_methods.md)
