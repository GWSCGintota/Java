[← 076. Polymorphism](076_Polymorphism.md) | [Course Index](../README.md) | [078. Abstract classes →](078_Abstract_classes.md)

# Java Abstraction Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/077_Abstraction.md)

These exercises follow [077. Abstraction](../../Road%20Map/05_Object_Oriented_Programming/077_Abstraction.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete Main.java with nested helper types. Compile and run each independently. Inputs are non-null unless explicitly tested; arithmetic uses small values. Nested fixtures keep examples focused and do not replace cross-package access exercises.

## Question 1 — Use an abstract sending contract

**Task:** Use an abstract sending contract.

```java
public class Main {
interface Sender { String send(String message); } static class EmailSender implements Sender { public String send(String message) { return "email:"+message; } } static class SmsSender implements Sender { public String send(String message) { return "sms:"+message; } } static class Notification { private final Sender sender; Notification(Sender sender) { this.sender=java.util.Objects.requireNonNull(sender,"sender"); } String notify(String text) { return sender.send(text); } }
public static void main(String[] args) {
Sender sender=new EmailSender(); System.out.println(sender.send("Ready"));
}
}
```

**Expected result:**
```text
email:Ready
```

**Concept:** Abstraction exposes what an operation does while hiding its implementation choice.

## Question 2 — Substitute an SMS implementation

**Task:** Substitute an SMS implementation.

```java
public class Main {
interface Sender { String send(String message); } static class EmailSender implements Sender { public String send(String message) { return "email:"+message; } } static class SmsSender implements Sender { public String send(String message) { return "sms:"+message; } } static class Notification { private final Sender sender; Notification(Sender sender) { this.sender=java.util.Objects.requireNonNull(sender,"sender"); } String notify(String text) { return sender.send(text); } }
public static void main(String[] args) {
Sender sender=new SmsSender(); System.out.println(sender.send("Ready"));
}
}
```

**Expected result:**
```text
sms:Ready
```

**Concept:** Different implementations can satisfy one contract.

## Question 3 — Inject a dependency

**Task:** Inject a dependency.

```java
public class Main {
interface Sender { String send(String message); } static class EmailSender implements Sender { public String send(String message) { return "email:"+message; } } static class SmsSender implements Sender { public String send(String message) { return "sms:"+message; } } static class Notification { private final Sender sender; Notification(Sender sender) { this.sender=java.util.Objects.requireNonNull(sender,"sender"); } String notify(String text) { return sender.send(text); } }
public static void main(String[] args) {
System.out.println(new Notification(new EmailSender()).notify("Ready"));
}
}
```

**Expected result:**
```text
email:Ready
```

**Concept:** A consumer can depend on an abstraction through constructor injection.

## Question 4 — Change implementation without changing the consumer

**Task:** Change implementation without changing the consumer.

```java
public class Main {
interface Sender { String send(String message); } static class EmailSender implements Sender { public String send(String message) { return "email:"+message; } } static class SmsSender implements Sender { public String send(String message) { return "sms:"+message; } } static class Notification { private final Sender sender; Notification(Sender sender) { this.sender=java.util.Objects.requireNonNull(sender,"sender"); } String notify(String text) { return sender.send(text); } }
public static void main(String[] args) {
System.out.println(new Notification(new SmsSender()).notify("Ready"));
}
}
```

**Expected result:**
```text
sms:Ready
```

**Concept:** The consumer's policy is separate from the transport implementation.

## Question 5 — Provide a small test double

**Task:** Provide a small test double.

```java
public class Main {
interface Sender { String send(String message); } static class EmailSender implements Sender { public String send(String message) { return "email:"+message; } } static class SmsSender implements Sender { public String send(String message) { return "sms:"+message; } } static class Notification { private final Sender sender; Notification(Sender sender) { this.sender=java.util.Objects.requireNonNull(sender,"sender"); } String notify(String text) { return sender.send(text); } }
public static void main(String[] args) {
Sender fake=message->"test:"+message; System.out.println(new Notification(fake).notify("Ready"));
}
}
```

**Expected result:**
```text
test:Ready
```

**Concept:** A functional contract can be implemented with a focused test double.

## Question 6 — Reject a missing collaborator

**Task:** Reject a missing collaborator.

```java
public class Main {
interface Sender { String send(String message); } static class EmailSender implements Sender { public String send(String message) { return "email:"+message; } } static class SmsSender implements Sender { public String send(String message) { return "sms:"+message; } } static class Notification { private final Sender sender; Notification(Sender sender) { this.sender=java.util.Objects.requireNonNull(sender,"sender"); } String notify(String text) { return sender.send(text); } }
public static void main(String[] args) {
try { new Notification(null); } catch(NullPointerException e) { System.out.println(e.getMessage()); }
}
}
```

**Expected result:**
```text
sender
```

**Concept:** An abstraction still needs an explicit null contract.

## Question 7 — Process several implementations uniformly

**Task:** Process several implementations uniformly.

```java
public class Main {
interface Sender { String send(String message); } static class EmailSender implements Sender { public String send(String message) { return "email:"+message; } } static class SmsSender implements Sender { public String send(String message) { return "sms:"+message; } } static class Notification { private final Sender sender; Notification(Sender sender) { this.sender=java.util.Objects.requireNonNull(sender,"sender"); } String notify(String text) { return sender.send(text); } }
public static void main(String[] args) {
Sender[] senders={new EmailSender(),new SmsSender()}; for(Sender sender:senders) { System.out.println(sender.send("A")); }
}
}
```

**Expected result:**
```text
email:A
sms:A
```

**Concept:** Clients can use a common operation without inspecting implementation classes.

## Question 8 — Preserve separate configured consumers

**Task:** Preserve separate configured consumers.

```java
public class Main {
interface Sender { String send(String message); } static class EmailSender implements Sender { public String send(String message) { return "email:"+message; } } static class SmsSender implements Sender { public String send(String message) { return "sms:"+message; } } static class Notification { private final Sender sender; Notification(Sender sender) { this.sender=java.util.Objects.requireNonNull(sender,"sender"); } String notify(String text) { return sender.send(text); } }
public static void main(String[] args) {
Notification email=new Notification(new EmailSender()),sms=new Notification(new SmsSender()); System.out.println(email.notify("A")); System.out.println(sms.notify("B"));
}
}
```

**Expected result:**
```text
email:A
sms:B
```

**Concept:** Each object can receive its own abstraction implementation.

## Question 9 — Use an empty-message policy explicitly

**Task:** Use an empty-message policy explicitly.

```java
public class Main {
interface Sender { String send(String message); } static class EmailSender implements Sender { public String send(String message) { return "email:"+message; } } static class SmsSender implements Sender { public String send(String message) { return "sms:"+message; } } static class Notification { private final Sender sender; Notification(Sender sender) { this.sender=java.util.Objects.requireNonNull(sender,"sender"); } String notify(String text) { return sender.send(text); } }
public static void main(String[] args) {
System.out.println(new EmailSender().send(""));
}
}
```

**Expected result:**
```text
email:
```

**Concept:** This fixture permits empty messages; production contracts should define that policy.

## Question 10 — Separate the client from construction

**Task:** Separate the client from construction.

```java
public class Main {
interface Sender { String send(String message); } static class EmailSender implements Sender { public String send(String message) { return "email:"+message; } } static class SmsSender implements Sender { public String send(String message) { return "sms:"+message; } } static class Notification { private final Sender sender; Notification(Sender sender) { this.sender=java.util.Objects.requireNonNull(sender,"sender"); } String notify(String text) { return sender.send(text); } }
public static void main(String[] args) {
Sender factoryResult=new SmsSender(); Notification client=new Notification(factoryResult); System.out.println(client.notify("Done"));
}
}
```

**Expected result:**
```text
sms:Done
```

**Concept:** Construction decisions can live outside the abstraction consumer.

---

## Interview Questions and Answers

### Interview Question 1 — What is abstraction?

**Answer:** Exposing a useful contract while hiding implementation details that callers do not need to depend on.

### Interview Question 2 — How does abstraction differ from encapsulation?

**Answer:** Abstraction focuses on the contract and essential behavior; encapsulation controls access to representation and state changes. They commonly reinforce each other.

### Interview Question 3 — Does abstraction require an abstract class?

**Answer:** No. Interfaces, focused methods, modules, and other boundaries can all express abstractions.

### Interview Question 4 — What makes an abstraction useful?

**Answer:** A stable, cohesive contract with clear semantics that its implementations can satisfy without callers knowing their details.

### Interview Question 5 — Can too many abstractions hurt?

**Answer:** Yes. Indirection without a clear boundary or variation point can make code harder to understand. Introduce abstractions for concrete needs.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Use an abstract sending contract | Abstraction exposes what an operation does while hiding its implementation choice. |
| Substitute an SMS implementation | Different implementations can satisfy one contract. |
| Inject a dependency | A consumer can depend on an abstraction through constructor injection. |
| Change implementation without changing the consumer | The consumer's policy is separate from the transport implementation. |
| Provide a small test double | A functional contract can be implemented with a focused test double. |

[← 076. Polymorphism](076_Polymorphism.md) | [Course Index](../README.md) | [078. Abstract classes →](078_Abstract_classes.md)
