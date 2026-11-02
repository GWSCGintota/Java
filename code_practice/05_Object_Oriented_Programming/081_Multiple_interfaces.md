[← 080. Interfaces](080_Interfaces.md) | [Course Index](../README.md) | [082. Interface default methods →](082_Interface_default_methods.md)

# Java Multiple interfaces Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/081_Multiple_interfaces.md)

These exercises follow [081. Multiple interfaces](../../Road%20Map/05_Object_Oriented_Programming/081_Multiple_interfaces.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete Main.java with nested helper types. Compile and run each independently. Inputs are non-null unless explicitly tested; arithmetic uses small values. Nested fixtures keep examples focused and do not replace cross-package access exercises.

## Question 1 — Implement two contracts

**Task:** Implement two contracts.

```java
public class Main {
interface Named { String name(); } interface Sized { int size(); } static class Document implements Named,Sized { final String title,text; Document(String title,String text) { this.title=title; this.text=text; } public String name() { return title; } public int size() { return text.length(); } }
public static void main(String[] args) {
Document document=new Document("Notes","Java"); System.out.println(document.name()+":"+document.size());
}
}
```

**Expected result:**
```text
Notes:4
```

**Concept:** A class can implement multiple interfaces.

## Question 2 — Use the first interface view

**Task:** Use the first interface view.

```java
public class Main {
interface Named { String name(); } interface Sized { int size(); } static class Document implements Named,Sized { final String title,text; Document(String title,String text) { this.title=title; this.text=text; } public String name() { return title; } public int size() { return text.length(); } }
public static void main(String[] args) {
Named named=new Document("Notes","Java"); System.out.println(named.name());
}
}
```

**Expected result:**
```text
Notes
```

**Concept:** The declared interface controls directly accessible operations.

## Question 3 — Use the second interface view

**Task:** Use the second interface view.

```java
public class Main {
interface Named { String name(); } interface Sized { int size(); } static class Document implements Named,Sized { final String title,text; Document(String title,String text) { this.title=title; this.text=text; } public String name() { return title; } public int size() { return text.length(); } }
public static void main(String[] args) {
Sized sized=new Document("Notes","Java"); System.out.println(sized.size());
}
}
```

**Expected result:**
```text
4
```

**Concept:** One object can be viewed through several independent contracts.

## Question 4 — Check both roles

**Task:** Check both roles.

```java
public class Main {
interface Named { String name(); } interface Sized { int size(); } static class Document implements Named,Sized { final String title,text; Document(String title,String text) { this.title=title; this.text=text; } public String name() { return title; } public int size() { return text.length(); } }
public static void main(String[] args) {
Object value=new Document("Notes","Java"); System.out.println(value instanceof Named); System.out.println(value instanceof Sized);
}
}
```

**Expected result:**
```text
true
true
```

**Concept:** Type membership can include multiple implemented interfaces.

## Question 5 — Keep one identity across views

**Task:** Keep one identity across views.

```java
public class Main {
interface Named { String name(); } interface Sized { int size(); } static class Document implements Named,Sized { final String title,text; Document(String title,String text) { this.title=title; this.text=text; } public String name() { return title; } public int size() { return text.length(); } }
public static void main(String[] args) {
Document document=new Document("Notes","Java"); Named named=document; Sized sized=document; System.out.println((Object)named==(Object)sized);
}
}
```

**Expected result:**
```text
true
```

**Concept:** Different interface references can identify the same object.

## Question 6 — Handle empty content

**Task:** Handle empty content.

```java
public class Main {
interface Named { String name(); } interface Sized { int size(); } static class Document implements Named,Sized { final String title,text; Document(String title,String text) { this.title=title; this.text=text; } public String name() { return title; } public int size() { return text.length(); } }
public static void main(String[] args) {
System.out.println(new Document("Empty","").size());
}
}
```

**Expected result:**
```text
0
```

**Concept:** Each contract still needs defined boundary behavior.

## Question 7 — Use both roles through the concrete type

**Task:** Use both roles through the concrete type.

```java
public class Main {
interface Named { String name(); } interface Sized { int size(); } static class Document implements Named,Sized { final String title,text; Document(String title,String text) { this.title=title; this.text=text; } public String name() { return title; } public int size() { return text.length(); } }
public static void main(String[] args) {
Document document=new Document("Notes","Java"); System.out.println(document.name().length()+document.size());
}
}
```

**Expected result:**
```text
9
```

**Concept:** A concrete implementer can offer all of its interface operations at once.

## Question 8 — Convert between verified roles

**Task:** Convert between verified roles.

```java
public class Main {
interface Named { String name(); } interface Sized { int size(); } static class Document implements Named,Sized { final String title,text; Document(String title,String text) { this.title=title; this.text=text; } public String name() { return title; } public int size() { return text.length(); } }
public static void main(String[] args) {
Named named=new Document("Notes","Java"); if(named instanceof Sized) { System.out.println(((Sized)named).size()); }
}
}
```

**Expected result:**
```text
4
```

**Concept:** A runtime check can establish another supported interface view.

## Question 9 — Traverse one role uniformly

**Task:** Traverse one role uniformly.

```java
public class Main {
interface Named { String name(); } interface Sized { int size(); } static class Document implements Named,Sized { final String title,text; Document(String title,String text) { this.title=title; this.text=text; } public String name() { return title; } public int size() { return text.length(); } }
public static void main(String[] args) {
Named[] values={new Document("A","x"),new Document("B","yz")}; for(Named value:values) { System.out.println(value.name()); }
}
}
```

**Expected result:**
```text
A
B
```

**Concept:** Callers need only the interface relevant to their operation.

## Question 10 — Inspect directly implemented interfaces

**Task:** Inspect directly implemented interfaces.

```java
public class Main {
interface Named { String name(); } interface Sized { int size(); } static class Document implements Named,Sized { final String title,text; Document(String title,String text) { this.title=title; this.text=text; } public String name() { return title; } public int size() { return text.length(); } }
public static void main(String[] args) {
System.out.println(Document.class.getInterfaces().length);
}
}
```

**Expected result:**
```text
2
```

**Concept:** Metadata describes directly implemented interface declarations.

---

## Interview Questions and Answers

### Interview Question 1 — Can a Java class implement multiple interfaces?

**Answer:** Yes, while still having at most one direct class superclass.

### Interview Question 2 — What if two interfaces declare the same compatible abstract method?

**Answer:** One implementation can satisfy both when their signatures and return contracts are compatible.

### Interview Question 3 — What if interface return types conflict?

**Answer:** Incompatible inherited method contracts can make the combined type impossible to implement; the declarations must satisfy Java's compatibility rules.

### Interview Question 4 — Does each interface view create another object?

**Answer:** No. Different compatible reference views can refer to the same instance.

### Interview Question 5 — How can unrelated default implementations be resolved?

**Answer:** A class can provide an explicit override and, where legal, delegate to a chosen direct superinterface default with InterfaceName.super.method().

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Implement two contracts | A class can implement multiple interfaces. |
| Use the first interface view | The declared interface controls directly accessible operations. |
| Use the second interface view | One object can be viewed through several independent contracts. |
| Check both roles | Type membership can include multiple implemented interfaces. |
| Keep one identity across views | Different interface references can identify the same object. |

[← 080. Interfaces](080_Interfaces.md) | [Course Index](../README.md) | [082. Interface default methods →](082_Interface_default_methods.md)
