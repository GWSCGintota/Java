[← 064. Encapsulation](064_Encapsulation.md) | [Course Index](../README.md) | [066. Access modifiers →](066_Access_modifiers.md)

# Java Getters and setters Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/065_Getters_and_setters.md)

These exercises follow [065. Getters and setters](../../Road%20Map/05_Object_Oriented_Programming/065_Getters_and_setters.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete, independent Main.java. Helper types are nested to keep examples in one file. Compile and run each question separately. Inputs are non-null unless explicitly tested, and arithmetic uses small values.

## Question 1 — Read a name through its getter

**Task:** Read a name through its getter.

```java
public class Main {
static class Profile { private String name; private boolean active; Profile(String name) { setName(name); } String getName() { return name; } void setName(String name) { if(name==null || name.trim().isEmpty()) { throw new IllegalArgumentException("Name required"); } this.name=name.trim(); } boolean isActive() { return active; } void setActive(boolean active) { this.active=active; } }
public static void main(String[] args) {
System.out.println(new Profile("Ada").getName());
}
}
```

**Expected result:**
```text
Ada
```

**Concept:** A getter exposes a selected property value.

## Question 2 — Update a name through its setter

**Task:** Update a name through its setter.

```java
public class Main {
static class Profile { private String name; private boolean active; Profile(String name) { setName(name); } String getName() { return name; } void setName(String name) { if(name==null || name.trim().isEmpty()) { throw new IllegalArgumentException("Name required"); } this.name=name.trim(); } boolean isActive() { return active; } void setActive(boolean active) { this.active=active; } }
public static void main(String[] args) {
Profile profile=new Profile("Ada"); profile.setName("Grace"); System.out.println(profile.getName());
}
}
```

**Expected result:**
```text
Grace
```

**Concept:** Setters can control property updates.

## Question 3 — Normalize a supplied name

**Task:** Normalize a supplied name.

```java
public class Main {
static class Profile { private String name; private boolean active; Profile(String name) { setName(name); } String getName() { return name; } void setName(String name) { if(name==null || name.trim().isEmpty()) { throw new IllegalArgumentException("Name required"); } this.name=name.trim(); } boolean isActive() { return active; } void setActive(boolean active) { this.active=active; } }
public static void main(String[] args) {
System.out.println(new Profile(" Ada ").getName());
}
}
```

**Expected result:**
```text
Ada
```

**Concept:** Assignment can apply a deliberate normalization policy.

## Question 4 — Reject a null name

**Task:** Reject a null name.

```java
public class Main {
static class Profile { private String name; private boolean active; Profile(String name) { setName(name); } String getName() { return name; } void setName(String name) { if(name==null || name.trim().isEmpty()) { throw new IllegalArgumentException("Name required"); } this.name=name.trim(); } boolean isActive() { return active; } void setActive(boolean active) { this.active=active; } }
public static void main(String[] args) {
try { new Profile(null); } catch(IllegalArgumentException e) { System.out.println(e.getMessage()); }
}
}
```

**Expected result:**
```text
Name required
```

**Concept:** Validate before dereferencing nullable input.

## Question 5 — Reject blank text

**Task:** Reject blank text.

```java
public class Main {
static class Profile { private String name; private boolean active; Profile(String name) { setName(name); } String getName() { return name; } void setName(String name) { if(name==null || name.trim().isEmpty()) { throw new IllegalArgumentException("Name required"); } this.name=name.trim(); } boolean isActive() { return active; } void setActive(boolean active) { this.active=active; } }
public static void main(String[] args) {
Profile profile=new Profile("Ada"); try { profile.setName("  "); } catch(IllegalArgumentException e) { System.out.println(e.getMessage()); }
}
}
```

**Expected result:**
```text
Name required
```

**Concept:** A setter can enforce a nonblank invariant.

## Question 6 — Preserve state after invalid input

**Task:** Preserve state after invalid input.

```java
public class Main {
static class Profile { private String name; private boolean active; Profile(String name) { setName(name); } String getName() { return name; } void setName(String name) { if(name==null || name.trim().isEmpty()) { throw new IllegalArgumentException("Name required"); } this.name=name.trim(); } boolean isActive() { return active; } void setActive(boolean active) { this.active=active; } }
public static void main(String[] args) {
Profile profile=new Profile("Ada"); try { profile.setName(""); } catch(IllegalArgumentException ignored) { } System.out.println(profile.getName());
}
}
```

**Expected result:**
```text
Ada
```

**Concept:** Validation before assignment preserves the previous valid value.

## Question 7 — Read a boolean property

**Task:** Read a boolean property.

```java
public class Main {
static class Profile { private String name; private boolean active; Profile(String name) { setName(name); } String getName() { return name; } void setName(String name) { if(name==null || name.trim().isEmpty()) { throw new IllegalArgumentException("Name required"); } this.name=name.trim(); } boolean isActive() { return active; } void setActive(boolean active) { this.active=active; } }
public static void main(String[] args) {
System.out.println(new Profile("Ada").isActive());
}
}
```

**Expected result:**
```text
false
```

**Concept:** Boolean getters often use the is prefix by convention.

## Question 8 — Set a boolean property

**Task:** Set a boolean property.

```java
public class Main {
static class Profile { private String name; private boolean active; Profile(String name) { setName(name); } String getName() { return name; } void setName(String name) { if(name==null || name.trim().isEmpty()) { throw new IllegalArgumentException("Name required"); } this.name=name.trim(); } boolean isActive() { return active; } void setActive(boolean active) { this.active=active; } }
public static void main(String[] args) {
Profile profile=new Profile("Ada"); profile.setActive(true); System.out.println(profile.isActive());
}
}
```

**Expected result:**
```text
true
```

**Concept:** A setter can expose a deliberate state change.

## Question 9 — Keep properties independent

**Task:** Keep properties independent.

```java
public class Main {
static class Profile { private String name; private boolean active; Profile(String name) { setName(name); } String getName() { return name; } void setName(String name) { if(name==null || name.trim().isEmpty()) { throw new IllegalArgumentException("Name required"); } this.name=name.trim(); } boolean isActive() { return active; } void setActive(boolean active) { this.active=active; } }
public static void main(String[] args) {
Profile profile=new Profile("Ada"); profile.setActive(true); profile.setName("Grace"); System.out.println(profile.getName()+":"+profile.isActive());
}
}
```

**Expected result:**
```text
Grace:true
```

**Concept:** Changing one property should preserve unrelated state unless specified otherwise.

## Question 10 — Keep instances independent

**Task:** Keep instances independent.

```java
public class Main {
static class Profile { private String name; private boolean active; Profile(String name) { setName(name); } String getName() { return name; } void setName(String name) { if(name==null || name.trim().isEmpty()) { throw new IllegalArgumentException("Name required"); } this.name=name.trim(); } boolean isActive() { return active; } void setActive(boolean active) { this.active=active; } }
public static void main(String[] args) {
Profile a=new Profile("Ada"),b=new Profile("Bob"); a.setName("Grace"); System.out.println(b.getName());
}
}
```

**Expected result:**
```text
Bob
```

**Concept:** Accessors operate on their receiving instance.

---

## Interview Questions and Answers

### Interview Question 1 — Must every field have a getter and setter?

**Answer:** No. Expose only operations required by the object's contract. Read-only properties and domain-specific actions are often clearer.

### Interview Question 2 — What getter naming is common for booleans?

**Answer:** isActive is conventional for a primitive boolean property. Framework property-introspection rules may have additional details.

### Interview Question 3 — Where should setter validation happen?

**Answer:** Before mutating stored state, with an explicit policy for null, blank, range, and cross-field constraints as needed.

### Interview Question 4 — Can a getter return mutable internal data safely?

**Answer:** Only under an explicit sharing policy. Otherwise return a defensive copy or an appropriate immutable result to preserve ownership boundaries.

### Interview Question 5 — How do immutable objects use accessors?

**Answer:** They can provide getters or value accessors without mutation setters, establishing valid state through construction or factories.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Read a name through its getter | A getter exposes a selected property value. |
| Update a name through its setter | Setters can control property updates. |
| Normalize a supplied name | Assignment can apply a deliberate normalization policy. |
| Reject a null name | Validate before dereferencing nullable input. |
| Reject blank text | A setter can enforce a nonblank invariant. |

[← 064. Encapsulation](064_Encapsulation.md) | [Course Index](../README.md) | [066. Access modifiers →](066_Access_modifiers.md)
