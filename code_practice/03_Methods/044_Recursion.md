[← 043. Static methods](043_Static_methods.md) | [Course Index](../README.md) | [045. Pass-by-value in Java →](045_Pass-by-value_in_Java.md)

# Java Recursion Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/03_Methods/044_Recursion.md)

These exercises follow [044. Recursion](../../Road%20Map/03_Methods/044_Recursion.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each model solution is a complete Main.java. Compile and run it independently. Arithmetic examples use small values; inputs are non-null unless explicitly tested. Standard-library classes are fully qualified when needed.

## Question 1 — Factorial

**Task:** Compute factorial recursively for 5 with a zero base case.

```java
public class Main {
    static int factorial(int n) { if(n<0) { throw new IllegalArgumentException("Negative"); } return n==0 ? 1 : n*factorial(n-1); }
    public static void main(String[] args) {
        System.out.println(factorial(5));
    }
}
```

**Expected result:**
```text
120
```

**Concept:** A base case stops recursive descent.

## Question 2 — Zero factorial

**Task:** Test the factorial base case directly.

```java
public class Main {
    static int factorial(int n) { if(n<0) { throw new IllegalArgumentException("Negative"); } return n==0 ? 1 : n*factorial(n-1); }
    public static void main(String[] args) {
        System.out.println(factorial(0));
    }
}
```

**Expected result:**
```text
1
```

**Concept:** The base result must match the mathematical contract.

## Question 3 — Reject negatives

**Task:** Reject an invalid recursive factorial input.

```java
public class Main {
    static int factorial(int n) { if(n<0) { throw new IllegalArgumentException("Negative"); } return n==0 ? 1 : n*factorial(n-1); }
    public static void main(String[] args) {
        try { factorial(-1); } catch(IllegalArgumentException e) { System.out.println(e.getMessage()); }
    }
}
```

**Expected result:**
```text
Negative
```

**Concept:** Reject inputs that would move away from the base case.

## Question 4 — Range sum

**Task:** Recursively sum 1 through 4.

```java
public class Main {
    static int sum(int n) { if(n<0) { throw new IllegalArgumentException(); } return n==0 ? 0 : n+sum(n-1); }
    public static void main(String[] args) {
        System.out.println(sum(4));
    }
}
```

**Expected result:**
```text
10
```

**Concept:** Each call reduces the remaining subproblem.

## Question 5 — Countdown

**Task:** Recursively print 3 through 1.

```java
public class Main {
    static void countdown(int n) { if(n<=0) { return; } System.out.println(n); countdown(n-1); }
    public static void main(String[] args) {
        countdown(3);
    }
}
```

**Expected result:**
```text
3
2
1
```

**Concept:** Work before recursion appears in descent order.

## Question 6 — Count upward

**Task:** Move printing after the recursive call.

```java
public class Main {
    static void count(int n) { if(n<=0) { return; } count(n-1); System.out.println(n); }
    public static void main(String[] args) {
        count(3);
    }
}
```

**Expected result:**
```text
1
2
3
```

**Concept:** Work after recursion appears while calls unwind.

## Question 7 — Greatest common divisor

**Task:** Implement Euclid recursion for nonnegative small integers.

```java
public class Main {
    static int gcd(int a,int b) { return b==0 ? a : gcd(b,a%b); }
    public static void main(String[] args) {
        System.out.println(gcd(48,18));
    }
}
```

**Expected result:**
```text
6
```

**Concept:** The remainder reduces the Euclidean subproblem.

## Question 8 — String reversal

**Task:** Reverse a short non-null string recursively.

```java
public class Main {
    static String reverse(String s) { return s.isEmpty() ? s : reverse(s.substring(1))+s.charAt(0); }
    public static void main(String[] args) {
        System.out.println(reverse("abc"));
    }
}
```

**Expected result:**
```text
cba
```

**Concept:** This educational algorithm creates intermediate strings and is unsuitable for very large inputs.

## Question 9 — Array sum

**Task:** Sum an array recursively using an index.

```java
public class Main {
    static int sum(int[] values,int index) { return index==values.length ? 0 : values[index]+sum(values,index+1); }
    public static void main(String[] args) {
        System.out.println(sum(new int[]{2,3,4},0));
    }
}
```

**Expected result:**
```text
9
```

**Concept:** An index can represent the remaining suffix without copying arrays.

## Question 10 — Fibonacci

**Task:** Compute a small Fibonacci value with explicit base cases.

```java
public class Main {
    static int fib(int n) { if(n<0) { throw new IllegalArgumentException(); } return n<2 ? n : fib(n-1)+fib(n-2); }
    public static void main(String[] args) {
        System.out.println(fib(7));
    }
}
```

**Expected result:**
```text
13
```

**Concept:** Naive branching recursion repeats subproblems and grows exponentially.

---

## Interview Questions and Answers

### Interview Question 1 — What must a recursive method provide?

**Answer:** A base case and a reduction step that reaches it for every supported input.

### Interview Question 2 — What causes StackOverflowError in recursion?

**Answer:** Too many active frames, often from excessive depth or a missing reachable base case. It is not limited to infinite recursion.

### Interview Question 3 — Does Java guarantee tail-call optimization?

**Answer:** No. Do not rely on tail recursion to avoid stack growth on ordinary JVM execution.

### Interview Question 4 — How can recursive Fibonacci be improved?

**Answer:** Use memoization or an iterative dynamic-programming formulation to avoid recomputing the same subproblems.

### Interview Question 5 — When is recursion appropriate?

**Answer:** When the problem naturally decomposes into smaller similar problems, such as trees or backtracking, and depth and resource costs are controlled.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Factorial | A base case stops recursive descent. |
| Zero factorial | The base result must match the mathematical contract. |
| Reject negatives | Reject inputs that would move away from the base case. |
| Range sum | Each call reduces the remaining subproblem. |
| Countdown | Work before recursion appears in descent order. |

[← 043. Static methods](043_Static_methods.md) | [Course Index](../README.md) | [045. Pass-by-value in Java →](045_Pass-by-value_in_Java.md)
