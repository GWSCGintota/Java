[← 149. Type erasure](../09_Generics/149_Type_erasure.md) | [Course Index](../README.md) | [151. Lambda expressions →](151_Lambda_expressions.md)
<span title="A functional interface that takes one argument and returns a boolean value.">
Predicate
</span>

<abbr title="A functional interface that represents a boolean-valued function of one argument.">
    Predicate<T>
</abbr>

### Predicate<T>

> 💡 **Tip:** `Predicate<T>` represents a boolean-valued function that takes one argument.

```java
Predicate<Integer> even = n -> n % 2 == 0;



Or, if you want something that visually behaves like a tooltip, we can use an HTML `<details>` section:

```markdown
<details>
<summary>💡 What is Predicate?</summary>

`Predicate<T>` is a functional interface that takes one argument
and returns a boolean.

```java
Predicate<Integer> even = n -> n % 2 == 0;