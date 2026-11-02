[← 083. Composition](083_Composition.md) | [Course Index](../README.md) | [085. Association →](085_Association.md)

# Java Aggregation Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/084_Aggregation.md)

These exercises follow [084. Aggregation](../../Road%20Map/05_Object_Oriented_Programming/084_Aggregation.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete Main.java with nested helper types. Run each question independently. Examples define their own ownership and null policies; Java references alone do not enforce UML lifecycle relationships.

## Question 1 — Create an independent part before its group

**Task:** Create an independent part before its group.

```java
public class Main {
static class Member { final String name; Member(String name) { this.name=name; } } static class Team { private final java.util.List<Member> members=new java.util.ArrayList<>(); void add(Member member) { members.add(java.util.Objects.requireNonNull(member,"member")); } void remove(Member member) { members.remove(member); } int size() { return members.size(); } String firstName() { return members.get(0).name; } }
public static void main(String[] args) throws Exception {
Member member=new Member("Ada"); Team team=new Team(); team.add(member); System.out.println(team.firstName());
}
}
```

**Expected result:**
```text
Ada
```

**Concept:** Members are constructed outside the team and can exist independently.

## Question 2 — Share one part between groups

**Task:** Share one part between groups.

```java
public class Main {
static class Member { final String name; Member(String name) { this.name=name; } } static class Team { private final java.util.List<Member> members=new java.util.ArrayList<>(); void add(Member member) { members.add(java.util.Objects.requireNonNull(member,"member")); } void remove(Member member) { members.remove(member); } int size() { return members.size(); } String firstName() { return members.get(0).name; } }
public static void main(String[] args) throws Exception {
Member member=new Member("Ada"); Team first=new Team(),second=new Team(); first.add(member); second.add(member); System.out.println(first.firstName()+":"+second.firstName());
}
}
```

**Expected result:**
```text
Ada:Ada
```

**Concept:** This aggregation policy permits shared membership.

## Question 3 — Remove a member without destroying it

**Task:** Remove a member without destroying it.

```java
public class Main {
static class Member { final String name; Member(String name) { this.name=name; } } static class Team { private final java.util.List<Member> members=new java.util.ArrayList<>(); void add(Member member) { members.add(java.util.Objects.requireNonNull(member,"member")); } void remove(Member member) { members.remove(member); } int size() { return members.size(); } String firstName() { return members.get(0).name; } }
public static void main(String[] args) throws Exception {
Member member=new Member("Ada"); Team team=new Team(); team.add(member); team.remove(member); System.out.println(team.size()); System.out.println(member.name);
}
}
```

**Expected result:**
```text
0
Ada
```

**Concept:** Removing an association does not invalidate the independently referenced part.

## Question 4 — Start with an empty group

**Task:** Start with an empty group.

```java
public class Main {
static class Member { final String name; Member(String name) { this.name=name; } } static class Team { private final java.util.List<Member> members=new java.util.ArrayList<>(); void add(Member member) { members.add(java.util.Objects.requireNonNull(member,"member")); } void remove(Member member) { members.remove(member); } int size() { return members.size(); } String firstName() { return members.get(0).name; } }
public static void main(String[] args) throws Exception {
System.out.println(new Team().size());
}
}
```

**Expected result:**
```text
0
```

**Concept:** A group can exist without current members under this contract.

## Question 5 — Add multiple members

**Task:** Add multiple members.

```java
public class Main {
static class Member { final String name; Member(String name) { this.name=name; } } static class Team { private final java.util.List<Member> members=new java.util.ArrayList<>(); void add(Member member) { members.add(java.util.Objects.requireNonNull(member,"member")); } void remove(Member member) { members.remove(member); } int size() { return members.size(); } String firstName() { return members.get(0).name; } }
public static void main(String[] args) throws Exception {
Team team=new Team(); team.add(new Member("Ada")); team.add(new Member("Bob")); System.out.println(team.size());
}
}
```

**Expected result:**
```text
2
```

**Concept:** Aggregation can represent a collection of collaborators.

## Question 6 — Preserve another group on removal

**Task:** Preserve another group on removal.

```java
public class Main {
static class Member { final String name; Member(String name) { this.name=name; } } static class Team { private final java.util.List<Member> members=new java.util.ArrayList<>(); void add(Member member) { members.add(java.util.Objects.requireNonNull(member,"member")); } void remove(Member member) { members.remove(member); } int size() { return members.size(); } String firstName() { return members.get(0).name; } }
public static void main(String[] args) throws Exception {
Member member=new Member("Ada"); Team first=new Team(),second=new Team(); first.add(member); second.add(member); first.remove(member); System.out.println(first.size()+":"+second.size());
}
}
```

**Expected result:**
```text
0:1
```

**Concept:** Each group's membership storage is independent.

## Question 7 — Reject a null member

**Task:** Reject a null member.

```java
public class Main {
static class Member { final String name; Member(String name) { this.name=name; } } static class Team { private final java.util.List<Member> members=new java.util.ArrayList<>(); void add(Member member) { members.add(java.util.Objects.requireNonNull(member,"member")); } void remove(Member member) { members.remove(member); } int size() { return members.size(); } String firstName() { return members.get(0).name; } }
public static void main(String[] args) throws Exception {
try { new Team().add(null); } catch(NullPointerException e) { System.out.println(e.getMessage()); }
}
}
```

**Expected result:**
```text
member
```

**Concept:** A reference relationship still needs validation.

## Question 8 — State the duplicate-membership policy

**Task:** State the duplicate-membership policy.

```java
public class Main {
static class Member { final String name; Member(String name) { this.name=name; } } static class Team { private final java.util.List<Member> members=new java.util.ArrayList<>(); void add(Member member) { members.add(java.util.Objects.requireNonNull(member,"member")); } void remove(Member member) { members.remove(member); } int size() { return members.size(); } String firstName() { return members.get(0).name; } }
public static void main(String[] args) throws Exception {
Member member=new Member("Ada"); Team team=new Team(); team.add(member); team.add(member); System.out.println(team.size());
}
}
```

**Expected result:**
```text
2
```

**Concept:** This list-based fixture permits duplicates; use a different policy if the domain forbids them.

## Question 9 — Remove one duplicate occurrence

**Task:** Remove one duplicate occurrence.

```java
public class Main {
static class Member { final String name; Member(String name) { this.name=name; } } static class Team { private final java.util.List<Member> members=new java.util.ArrayList<>(); void add(Member member) { members.add(java.util.Objects.requireNonNull(member,"member")); } void remove(Member member) { members.remove(member); } int size() { return members.size(); } String firstName() { return members.get(0).name; } }
public static void main(String[] args) throws Exception {
Member member=new Member("Ada"); Team team=new Team(); team.add(member); team.add(member); team.remove(member); System.out.println(team.size());
}
}
```

**Expected result:**
```text
1
```

**Concept:** List.remove removes one matching occurrence under this fixture's equality policy.

## Question 10 — Keep independent member identities

**Task:** Keep independent member identities.

```java
public class Main {
static class Member { final String name; Member(String name) { this.name=name; } } static class Team { private final java.util.List<Member> members=new java.util.ArrayList<>(); void add(Member member) { members.add(java.util.Objects.requireNonNull(member,"member")); } void remove(Member member) { members.remove(member); } int size() { return members.size(); } String firstName() { return members.get(0).name; } }
public static void main(String[] args) throws Exception {
Member first=new Member("Ada"),second=new Member("Ada"); System.out.println(first==second);
}
}
```

**Expected result:**
```text
false
```

**Concept:** Matching names do not imply one shared member identity.

---

## Interview Questions and Answers

### Interview Question 1 — What is aggregation in object modeling?

**Answer:** A whole-part association in which parts can exist independently and may be shared, according to the chosen domain model.

### Interview Question 2 — How is aggregation different from composition?

**Answer:** Composition usually emphasizes stronger exclusive ownership; aggregation models weaker ownership. Java references do not enforce either interpretation automatically.

### Interview Question 3 — Does removing an object from a collection destroy it?

**Answer:** No. Other references can continue to use it; resource disposal and domain deletion are separate decisions.

### Interview Question 4 — How should duplicate membership be handled?

**Answer:** Define whether membership is a list, set, counted relationship, or another domain structure rather than letting a collection choice accidentally decide it.

### Interview Question 5 — Why separate group membership from member lifetime?

**Answer:** It allows membership changes without conflating them with creation, deletion, or ownership of the independent entity.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Create an independent part before its group | Members are constructed outside the team and can exist independently. |
| Share one part between groups | This aggregation policy permits shared membership. |
| Remove a member without destroying it | Removing an association does not invalidate the independently referenced part. |
| Start with an empty group | A group can exist without current members under this contract. |
| Add multiple members | Aggregation can represent a collection of collaborators. |

[← 083. Composition](083_Composition.md) | [Course Index](../README.md) | [085. Association →](085_Association.md)
