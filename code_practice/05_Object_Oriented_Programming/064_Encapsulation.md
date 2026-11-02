[← 063. final](063_final.md) | [Course Index](../README.md) | [065. Getters and setters →](065_Getters_and_setters.md)

# Java Encapsulation Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/064_Encapsulation.md)

These exercises follow [064. Encapsulation](../../Road%20Map/05_Object_Oriented_Programming/064_Encapsulation.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete, independent Main.java. Helper types are nested to keep examples in one file. Compile and run each question separately. Inputs are non-null unless explicitly tested, and arithmetic uses small values.

## Question 1 — Read controlled state

**Task:** Read controlled state.

```java
public class Main {
static class Account { private int balance; Account(int balance) { if(balance<0) { throw new IllegalArgumentException("Negative balance"); } this.balance=balance; } int balance() { return balance; } void deposit(int amount) { if(amount<=0) { throw new IllegalArgumentException("Positive amount required"); } balance+=amount; } boolean withdraw(int amount) { if(amount<=0) { throw new IllegalArgumentException("Positive amount required"); } if(amount>balance) { return false; } balance-=amount; return true; } }
public static void main(String[] args) {
System.out.println(new Account(10).balance());
}
}
```

**Expected result:**
```text
10
```

**Concept:** The public-facing operation exposes selected information.

## Question 2 — Deposit a valid amount

**Task:** Deposit a valid amount.

```java
public class Main {
static class Account { private int balance; Account(int balance) { if(balance<0) { throw new IllegalArgumentException("Negative balance"); } this.balance=balance; } int balance() { return balance; } void deposit(int amount) { if(amount<=0) { throw new IllegalArgumentException("Positive amount required"); } balance+=amount; } boolean withdraw(int amount) { if(amount<=0) { throw new IllegalArgumentException("Positive amount required"); } if(amount>balance) { return false; } balance-=amount; return true; } }
public static void main(String[] args) {
Account account=new Account(10); account.deposit(5); System.out.println(account.balance());
}
}
```

**Expected result:**
```text
15
```

**Concept:** A domain operation controls mutation.

## Question 3 — Withdraw within the balance

**Task:** Withdraw within the balance.

```java
public class Main {
static class Account { private int balance; Account(int balance) { if(balance<0) { throw new IllegalArgumentException("Negative balance"); } this.balance=balance; } int balance() { return balance; } void deposit(int amount) { if(amount<=0) { throw new IllegalArgumentException("Positive amount required"); } balance+=amount; } boolean withdraw(int amount) { if(amount<=0) { throw new IllegalArgumentException("Positive amount required"); } if(amount>balance) { return false; } balance-=amount; return true; } }
public static void main(String[] args) {
Account account=new Account(10); System.out.println(account.withdraw(4)); System.out.println(account.balance());
}
}
```

**Expected result:**
```text
true
6
```

**Concept:** A focused operation can enforce an invariant.

## Question 4 — Reject overdrawing

**Task:** Reject overdrawing.

```java
public class Main {
static class Account { private int balance; Account(int balance) { if(balance<0) { throw new IllegalArgumentException("Negative balance"); } this.balance=balance; } int balance() { return balance; } void deposit(int amount) { if(amount<=0) { throw new IllegalArgumentException("Positive amount required"); } balance+=amount; } boolean withdraw(int amount) { if(amount<=0) { throw new IllegalArgumentException("Positive amount required"); } if(amount>balance) { return false; } balance-=amount; return true; } }
public static void main(String[] args) {
Account account=new Account(10); System.out.println(account.withdraw(11)); System.out.println(account.balance());
}
}
```

**Expected result:**
```text
false
10
```

**Concept:** Rejected operations preserve state.

## Question 5 — Allow a zero opening balance

**Task:** Allow a zero opening balance.

```java
public class Main {
static class Account { private int balance; Account(int balance) { if(balance<0) { throw new IllegalArgumentException("Negative balance"); } this.balance=balance; } int balance() { return balance; } void deposit(int amount) { if(amount<=0) { throw new IllegalArgumentException("Positive amount required"); } balance+=amount; } boolean withdraw(int amount) { if(amount<=0) { throw new IllegalArgumentException("Positive amount required"); } if(amount>balance) { return false; } balance-=amount; return true; } }
public static void main(String[] args) {
System.out.println(new Account(0).balance());
}
}
```

**Expected result:**
```text
0
```

**Concept:** An invariant can allow its valid lower boundary.

## Question 6 — Reject negative opening state

**Task:** Reject negative opening state.

```java
public class Main {
static class Account { private int balance; Account(int balance) { if(balance<0) { throw new IllegalArgumentException("Negative balance"); } this.balance=balance; } int balance() { return balance; } void deposit(int amount) { if(amount<=0) { throw new IllegalArgumentException("Positive amount required"); } balance+=amount; } boolean withdraw(int amount) { if(amount<=0) { throw new IllegalArgumentException("Positive amount required"); } if(amount>balance) { return false; } balance-=amount; return true; } }
public static void main(String[] args) {
try { new Account(-1); } catch(IllegalArgumentException e) { System.out.println(e.getMessage()); }
}
}
```

**Expected result:**
```text
Negative balance
```

**Concept:** Validate invariants at construction.

## Question 7 — Reject a zero deposit

**Task:** Reject a zero deposit.

```java
public class Main {
static class Account { private int balance; Account(int balance) { if(balance<0) { throw new IllegalArgumentException("Negative balance"); } this.balance=balance; } int balance() { return balance; } void deposit(int amount) { if(amount<=0) { throw new IllegalArgumentException("Positive amount required"); } balance+=amount; } boolean withdraw(int amount) { if(amount<=0) { throw new IllegalArgumentException("Positive amount required"); } if(amount>balance) { return false; } balance-=amount; return true; } }
public static void main(String[] args) {
Account account=new Account(10); try { account.deposit(0); } catch(IllegalArgumentException e) { System.out.println(e.getMessage()); }
}
}
```

**Expected result:**
```text
Positive amount required
```

**Concept:** Data hiding alone does not replace validation.

## Question 8 — Reject a negative withdrawal

**Task:** Reject a negative withdrawal.

```java
public class Main {
static class Account { private int balance; Account(int balance) { if(balance<0) { throw new IllegalArgumentException("Negative balance"); } this.balance=balance; } int balance() { return balance; } void deposit(int amount) { if(amount<=0) { throw new IllegalArgumentException("Positive amount required"); } balance+=amount; } boolean withdraw(int amount) { if(amount<=0) { throw new IllegalArgumentException("Positive amount required"); } if(amount>balance) { return false; } balance-=amount; return true; } }
public static void main(String[] args) {
Account account=new Account(10); try { account.withdraw(-1); } catch(IllegalArgumentException e) { System.out.println(e.getMessage()); }
}
}
```

**Expected result:**
```text
Positive amount required
```

**Concept:** Validation blocks operations that could reverse the intended effect.

## Question 9 — Withdraw the exact balance

**Task:** Withdraw the exact balance.

```java
public class Main {
static class Account { private int balance; Account(int balance) { if(balance<0) { throw new IllegalArgumentException("Negative balance"); } this.balance=balance; } int balance() { return balance; } void deposit(int amount) { if(amount<=0) { throw new IllegalArgumentException("Positive amount required"); } balance+=amount; } boolean withdraw(int amount) { if(amount<=0) { throw new IllegalArgumentException("Positive amount required"); } if(amount>balance) { return false; } balance-=amount; return true; } }
public static void main(String[] args) {
Account account=new Account(10); System.out.println(account.withdraw(10)); System.out.println(account.balance());
}
}
```

**Expected result:**
```text
true
0
```

**Concept:** Exact-boundary behavior belongs in the contract.

## Question 10 — Keep account instances independent

**Task:** Keep account instances independent.

```java
public class Main {
static class Account { private int balance; Account(int balance) { if(balance<0) { throw new IllegalArgumentException("Negative balance"); } this.balance=balance; } int balance() { return balance; } void deposit(int amount) { if(amount<=0) { throw new IllegalArgumentException("Positive amount required"); } balance+=amount; } boolean withdraw(int amount) { if(amount<=0) { throw new IllegalArgumentException("Positive amount required"); } if(amount>balance) { return false; } balance-=amount; return true; } }
public static void main(String[] args) {
Account a=new Account(10),b=new Account(10); a.deposit(5); System.out.println(a.balance()+":"+b.balance());
}
}
```

**Expected result:**
```text
15:10
```

**Concept:** Encapsulated state is owned by each account instance.

---

## Interview Questions and Answers

### Interview Question 1 — What is encapsulation?

**Answer:** Keeping state and behavior behind a deliberate boundary so an object can preserve its invariants and expose a focused contract.

### Interview Question 2 — Are private fields plus unrestricted setters sufficient?

**Answer:** Not necessarily. A setter can still permit invalid combinations or leak representation details. Prefer domain operations and validation where appropriate.

### Interview Question 3 — Why can getters break encapsulation?

**Answer:** Returning a mutable internal collection or object can let callers bypass the intended mutation boundary. Use immutable views or defensive copies as the contract requires.

### Interview Question 4 — Why validate before modifying state?

**Answer:** A rejected operation can leave the object unchanged and easier to reason about. Multi-step changes may need stronger transaction or rollback mechanisms.

### Interview Question 5 — Is this small Account a production money model?

**Answer:** No. It uses small integral amounts to demonstrate invariants and omits currency, overflow, concurrency, persistence, and transaction requirements.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Read controlled state | The public-facing operation exposes selected information. |
| Deposit a valid amount | A domain operation controls mutation. |
| Withdraw within the balance | A focused operation can enforce an invariant. |
| Reject overdrawing | Rejected operations preserve state. |
| Allow a zero opening balance | An invariant can allow its valid lower boundary. |

[← 063. final](063_final.md) | [Course Index](../README.md) | [065. Getters and setters →](065_Getters_and_setters.md)
