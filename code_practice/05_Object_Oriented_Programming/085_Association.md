[← 084. Aggregation](084_Aggregation.md) | [Course Index](../README.md) | [086. Object class →](086_Object_class.md)

# Java Association Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/085_Association.md)

These exercises follow [085. Association](../../Road%20Map/05_Object_Oriented_Programming/085_Association.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete Main.java with nested helper types. Run each question independently. Examples define their own ownership and null policies; Java references alone do not enforce UML lifecycle relationships.

## Question 1 — Create an optional association

**Task:** Create an optional association.

```java
public class Main {
static class Person { final String name; private Person mentor; Person(String name) { this.name=name; } void assignMentor(Person mentor) { if(mentor==this) { throw new IllegalArgumentException("Self mentor"); } this.mentor=mentor; } String mentorName() { return mentor==null ? "None" : mentor.name; } }
public static void main(String[] args) throws Exception {
System.out.println(new Person("Ada").mentorName());
}
}
```

**Expected result:**
```text
None
```

**Concept:** A relationship can be absent under an explicit null policy.

## Question 2 — Assign a mentor

**Task:** Assign a mentor.

```java
public class Main {
static class Person { final String name; private Person mentor; Person(String name) { this.name=name; } void assignMentor(Person mentor) { if(mentor==this) { throw new IllegalArgumentException("Self mentor"); } this.mentor=mentor; } String mentorName() { return mentor==null ? "None" : mentor.name; } }
public static void main(String[] args) throws Exception {
Person learner=new Person("Ada"),mentor=new Person("Grace"); learner.assignMentor(mentor); System.out.println(learner.mentorName());
}
}
```

**Expected result:**
```text
Grace
```

**Concept:** A field can represent a unidirectional association.

## Question 3 — Show that the reverse link is not automatic

**Task:** Show that the reverse link is not automatic.

```java
public class Main {
static class Person { final String name; private Person mentor; Person(String name) { this.name=name; } void assignMentor(Person mentor) { if(mentor==this) { throw new IllegalArgumentException("Self mentor"); } this.mentor=mentor; } String mentorName() { return mentor==null ? "None" : mentor.name; } }
public static void main(String[] args) throws Exception {
Person learner=new Person("Ada"),mentor=new Person("Grace"); learner.assignMentor(mentor); System.out.println(mentor.mentorName());
}
}
```

**Expected result:**
```text
None
```

**Concept:** Assigning one direction does not establish a reverse association.

## Question 4 — Share a mentor

**Task:** Share a mentor.

```java
public class Main {
static class Person { final String name; private Person mentor; Person(String name) { this.name=name; } void assignMentor(Person mentor) { if(mentor==this) { throw new IllegalArgumentException("Self mentor"); } this.mentor=mentor; } String mentorName() { return mentor==null ? "None" : mentor.name; } }
public static void main(String[] args) throws Exception {
Person first=new Person("Ada"),second=new Person("Bob"),mentor=new Person("Grace"); first.assignMentor(mentor); second.assignMentor(mentor); System.out.println(first.mentorName()+":"+second.mentorName());
}
}
```

**Expected result:**
```text
Grace:Grace
```

**Concept:** Multiple objects can associate with one collaborator.

## Question 5 — Replace an association

**Task:** Replace an association.

```java
public class Main {
static class Person { final String name; private Person mentor; Person(String name) { this.name=name; } void assignMentor(Person mentor) { if(mentor==this) { throw new IllegalArgumentException("Self mentor"); } this.mentor=mentor; } String mentorName() { return mentor==null ? "None" : mentor.name; } }
public static void main(String[] args) throws Exception {
Person learner=new Person("Ada"); learner.assignMentor(new Person("Grace")); learner.assignMentor(new Person("Alan")); System.out.println(learner.mentorName());
}
}
```

**Expected result:**
```text
Alan
```

**Concept:** Reassigning a link changes the relationship without changing the old mentor.

## Question 6 — Clear an optional association

**Task:** Clear an optional association.

```java
public class Main {
static class Person { final String name; private Person mentor; Person(String name) { this.name=name; } void assignMentor(Person mentor) { if(mentor==this) { throw new IllegalArgumentException("Self mentor"); } this.mentor=mentor; } String mentorName() { return mentor==null ? "None" : mentor.name; } }
public static void main(String[] args) throws Exception {
Person learner=new Person("Ada"); learner.assignMentor(new Person("Grace")); learner.assignMentor(null); System.out.println(learner.mentorName());
}
}
```

**Expected result:**
```text
None
```

**Concept:** Clearing is a deliberate operation permitted by this contract.

## Question 7 — Reject a self-association

**Task:** Reject a self-association.

```java
public class Main {
static class Person { final String name; private Person mentor; Person(String name) { this.name=name; } void assignMentor(Person mentor) { if(mentor==this) { throw new IllegalArgumentException("Self mentor"); } this.mentor=mentor; } String mentorName() { return mentor==null ? "None" : mentor.name; } }
public static void main(String[] args) throws Exception {
Person person=new Person("Ada"); try { person.assignMentor(person); } catch(IllegalArgumentException e) { System.out.println(e.getMessage()); }
}
}
```

**Expected result:**
```text
Self mentor
```

**Concept:** Relationship rules can validate identity.

## Question 8 — Keep an associated object usable independently

**Task:** Keep an associated object usable independently.

```java
public class Main {
static class Person { final String name; private Person mentor; Person(String name) { this.name=name; } void assignMentor(Person mentor) { if(mentor==this) { throw new IllegalArgumentException("Self mentor"); } this.mentor=mentor; } String mentorName() { return mentor==null ? "None" : mentor.name; } }
public static void main(String[] args) throws Exception {
Person mentor=new Person("Grace"),learner=new Person("Ada"); learner.assignMentor(mentor); learner.assignMentor(null); System.out.println(mentor.name);
}
}
```

**Expected result:**
```text
Grace
```

**Concept:** An association does not by itself imply ownership or destruction.

## Question 9 — Allow a longer association chain

**Task:** Allow a longer association chain.

```java
public class Main {
static class Person { final String name; private Person mentor; Person(String name) { this.name=name; } void assignMentor(Person mentor) { if(mentor==this) { throw new IllegalArgumentException("Self mentor"); } this.mentor=mentor; } String mentorName() { return mentor==null ? "None" : mentor.name; } }
public static void main(String[] args) throws Exception {
Person first=new Person("Ada"),second=new Person("Grace"),third=new Person("Alan"); first.assignMentor(second); second.assignMentor(third); System.out.println(first.mentorName()); System.out.println(second.mentorName());
}
}
```

**Expected result:**
```text
Grace
Alan
```

**Concept:** One-step navigation differs from traversing an entire relationship graph.

## Question 10 — Document the cycle policy

**Task:** Document the cycle policy.

```java
public class Main {
static class Person { final String name; private Person mentor; Person(String name) { this.name=name; } void assignMentor(Person mentor) { if(mentor==this) { throw new IllegalArgumentException("Self mentor"); } this.mentor=mentor; } String mentorName() { return mentor==null ? "None" : mentor.name; } }
public static void main(String[] args) throws Exception {
Person first=new Person("Ada"),second=new Person("Grace"); first.assignMentor(second); second.assignMentor(first); System.out.println(first.mentorName()+":"+second.mentorName());
}
}
```

**Expected result:**
```text
Grace:Ada
```

**Concept:** This fixture rejects only self-links and permits longer cycles; a domain may need stronger validation.

---

## Interview Questions and Answers

### Interview Question 1 — What is an association?

**Answer:** A relationship between objects that interact or hold references to one another, without necessarily implying whole-part ownership.

### Interview Question 2 — What is a unidirectional association?

**Answer:** One object can navigate to another, but the reverse reference is not automatically present.

### Interview Question 3 — How are bidirectional relationships maintained?

**Answer:** Application operations must keep both ends consistent, including replacement, removal, and invalid-input behavior.

### Interview Question 4 — Does a reference imply ownership?

**Answer:** No. Ownership, lifecycle responsibility, optionality, multiplicity, and permitted sharing must be stated separately.

### Interview Question 5 — Why consider cycles?

**Answer:** Association graphs can contain cycles. Traversal, serialization, equality, and validation need policies that avoid infinite recursion or inconsistent relationships.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Create an optional association | A relationship can be absent under an explicit null policy. |
| Assign a mentor | A field can represent a unidirectional association. |
| Show that the reverse link is not automatic | Assigning one direction does not establish a reverse association. |
| Share a mentor | Multiple objects can associate with one collaborator. |
| Replace an association | Reassigning a link changes the relationship without changing the old mentor. |

[← 084. Aggregation](084_Aggregation.md) | [Course Index](../README.md) | [086. Object class →](086_Object_class.md)
