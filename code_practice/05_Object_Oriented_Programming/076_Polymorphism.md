[← 075. super](075_super.md) | [Course Index](../README.md) | [077. Abstraction →](077_Abstraction.md)

# Java Polymorphism Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/076_Polymorphism.md)

These exercises follow [076. Polymorphism](../../Road%20Map/05_Object_Oriented_Programming/076_Polymorphism.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete Main.java with nested helper types. Compile and run each independently. Inputs are non-null unless explicitly tested; arithmetic uses small values. Nested fixtures keep examples focused and do not replace cross-package access exercises.

## Question 1 — Call through an interface reference

**Task:** Call through an interface reference.

```java
public class Main {
interface Shape { int area(); } static class Square implements Shape { final int side; Square(int side) { this.side=side; } public int area() { return side*side; } } static class Rectangle implements Shape { final int width,height; Rectangle(int width,int height) { this.width=width; this.height=height; } public int area() { return width*height; } } static int total(Shape[] shapes) { int total=0; for(Shape shape:shapes) { total+=shape.area(); } return total; }
public static void main(String[] args) {
Shape shape=new Square(3); System.out.println(shape.area());
}
}
```

**Expected result:**
```text
9
```

**Concept:** A declared contract can hide the concrete implementation.

## Question 2 — Substitute another implementation

**Task:** Substitute another implementation.

```java
public class Main {
interface Shape { int area(); } static class Square implements Shape { final int side; Square(int side) { this.side=side; } public int area() { return side*side; } } static class Rectangle implements Shape { final int width,height; Rectangle(int width,int height) { this.width=width; this.height=height; } public int area() { return width*height; } } static int total(Shape[] shapes) { int total=0; for(Shape shape:shapes) { total+=shape.area(); } return total; }
public static void main(String[] args) {
Shape shape=new Rectangle(2,4); System.out.println(shape.area());
}
}
```

**Expected result:**
```text
8
```

**Concept:** Callers can use another implementation through the same contract.

## Question 3 — Traverse mixed implementations

**Task:** Traverse mixed implementations.

```java
public class Main {
interface Shape { int area(); } static class Square implements Shape { final int side; Square(int side) { this.side=side; } public int area() { return side*side; } } static class Rectangle implements Shape { final int width,height; Rectangle(int width,int height) { this.width=width; this.height=height; } public int area() { return width*height; } } static int total(Shape[] shapes) { int total=0; for(Shape shape:shapes) { total+=shape.area(); } return total; }
public static void main(String[] args) {
Shape[] shapes={new Square(2),new Rectangle(2,3)}; for(Shape shape:shapes) { System.out.println(shape.area()); }
}
}
```

**Expected result:**
```text
4
6
```

**Concept:** Each receiver dispatches to its own implementation.

## Question 4 — Aggregate without type checks

**Task:** Aggregate without type checks.

```java
public class Main {
interface Shape { int area(); } static class Square implements Shape { final int side; Square(int side) { this.side=side; } public int area() { return side*side; } } static class Rectangle implements Shape { final int width,height; Rectangle(int width,int height) { this.width=width; this.height=height; } public int area() { return width*height; } } static int total(Shape[] shapes) { int total=0; for(Shape shape:shapes) { total+=shape.area(); } return total; }
public static void main(String[] args) {
System.out.println(total(new Shape[]{new Square(2),new Rectangle(2,3)}));
}
}
```

**Expected result:**
```text
10
```

**Concept:** Contract-based algorithms can avoid branching on concrete classes.

## Question 5 — Handle an empty collection of shapes

**Task:** Handle an empty collection of shapes.

```java
public class Main {
interface Shape { int area(); } static class Square implements Shape { final int side; Square(int side) { this.side=side; } public int area() { return side*side; } } static class Rectangle implements Shape { final int width,height; Rectangle(int width,int height) { this.width=width; this.height=height; } public int area() { return width*height; } } static int total(Shape[] shapes) { int total=0; for(Shape shape:shapes) { total+=shape.area(); } return total; }
public static void main(String[] args) {
System.out.println(total(new Shape[0]));
}
}
```

**Expected result:**
```text
0
```

**Concept:** The aggregation identity defines empty-input behavior.

## Question 6 — Change a reference implementation

**Task:** Change a reference implementation.

```java
public class Main {
interface Shape { int area(); } static class Square implements Shape { final int side; Square(int side) { this.side=side; } public int area() { return side*side; } } static class Rectangle implements Shape { final int width,height; Rectangle(int width,int height) { this.width=width; this.height=height; } public int area() { return width*height; } } static int total(Shape[] shapes) { int total=0; for(Shape shape:shapes) { total+=shape.area(); } return total; }
public static void main(String[] args) {
Shape shape=new Square(2); shape=new Rectangle(3,4); System.out.println(shape.area());
}
}
```

**Expected result:**
```text
12
```

**Concept:** A variable can refer to different compatible implementations over time.

## Question 7 — Use a polymorphic method reference

**Task:** Use a polymorphic method reference.

```java
public class Main {
interface Shape { int area(); } static class Square implements Shape { final int side; Square(int side) { this.side=side; } public int area() { return side*side; } } static class Rectangle implements Shape { final int width,height; Rectangle(int width,int height) { this.width=width; this.height=height; } public int area() { return width*height; } } static int total(Shape[] shapes) { int total=0; for(Shape shape:shapes) { total+=shape.area(); } return total; }
public static void main(String[] args) {
Shape shape=new Square(4); java.util.function.IntSupplier area=shape::area; System.out.println(area.getAsInt());
}
}
```

**Expected result:**
```text
16
```

**Concept:** A bound reference retains receiver-specific behavior.

## Question 8 — Check concrete type only when needed

**Task:** Check concrete type only when needed.

```java
public class Main {
interface Shape { int area(); } static class Square implements Shape { final int side; Square(int side) { this.side=side; } public int area() { return side*side; } } static class Rectangle implements Shape { final int width,height; Rectangle(int width,int height) { this.width=width; this.height=height; } public int area() { return width*height; } } static int total(Shape[] shapes) { int total=0; for(Shape shape:shapes) { total+=shape.area(); } return total; }
public static void main(String[] args) {
Shape shape=new Square(2); System.out.println(shape instanceof Square);
}
}
```

**Expected result:**
```text
true
```

**Concept:** Runtime type checks are available but are not required for ordinary contract calls.

## Question 9 — Use zero-sized valid geometry

**Task:** Use zero-sized valid geometry.

```java
public class Main {
interface Shape { int area(); } static class Square implements Shape { final int side; Square(int side) { this.side=side; } public int area() { return side*side; } } static class Rectangle implements Shape { final int width,height; Rectangle(int width,int height) { this.width=width; this.height=height; } public int area() { return width*height; } } static int total(Shape[] shapes) { int total=0; for(Shape shape:shapes) { total+=shape.area(); } return total; }
public static void main(String[] args) {
Shape shape=new Rectangle(0,4); System.out.println(shape.area());
}
}
```

**Expected result:**
```text
0
```

**Concept:** This small fixture permits zero dimensions explicitly.

## Question 10 — Compare identical results from different implementations

**Task:** Compare identical results from different implementations.

```java
public class Main {
interface Shape { int area(); } static class Square implements Shape { final int side; Square(int side) { this.side=side; } public int area() { return side*side; } } static class Rectangle implements Shape { final int width,height; Rectangle(int width,int height) { this.width=width; this.height=height; } public int area() { return width*height; } } static int total(Shape[] shapes) { int total=0; for(Shape shape:shapes) { total+=shape.area(); } return total; }
public static void main(String[] args) {
Shape first=new Square(2),second=new Rectangle(1,4); System.out.println(first.area()==second.area());
}
}
```

**Expected result:**
```text
true
```

**Concept:** Equal results do not imply equal runtime classes or identities.

---

## Interview Questions and Answers

### Interview Question 1 — What is runtime polymorphism?

**Answer:** Invoking an instance contract through a common type while the receiver's runtime class supplies the implementation.

### Interview Question 2 — What is the difference between subtype polymorphism and overloading?

**Answer:** Subtype dispatch chooses an overriding instance implementation at runtime; overloading selects among declarations using compile-time types.

### Interview Question 3 — Why program to an interface?

**Answer:** It lets callers depend on a focused behavioral contract rather than one implementation, enabling substitution and independent testing.

### Interview Question 4 — Does every polymorphic use need instanceof?

**Answer:** No. Ordinary contract calls should usually dispatch directly. Repeated concrete-type branching can indicate a missing operation in the abstraction.

### Interview Question 5 — What must substitutable implementations preserve?

**Answer:** The expectations of the shared contract, including valid inputs, results, visible effects, and failure behavior.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Call through an interface reference | A declared contract can hide the concrete implementation. |
| Substitute another implementation | Callers can use another implementation through the same contract. |
| Traverse mixed implementations | Each receiver dispatches to its own implementation. |
| Aggregate without type checks | Contract-based algorithms can avoid branching on concrete classes. |
| Handle an empty collection of shapes | The aggregation identity defines empty-input behavior. |

[← 075. super](075_super.md) | [Course Index](../README.md) | [077. Abstraction →](077_Abstraction.md)
