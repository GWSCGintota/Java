# Spring Framework Interview Questions

Use these as revision prompts. Try answering each question aloud before opening the answer.

## 1. What is IoC?

**Answer guide:** Inversion of Control means object creation and wiring are managed by the framework/container.

## 2. What is dependency injection?

**Answer guide:** Providing an object's dependencies from outside instead of constructing them internally.

## 3. What is a Spring bean?

**Answer guide:** An object managed by the Spring container.

## 4. What is ApplicationContext?

**Answer guide:** The central Spring container interface providing bean management and framework services.

## 5. @Component vs @Service vs @Repository?

**Answer guide:** All register beans; the specialized annotations communicate intent, and @Repository also supports persistence exception translation.

## 6. Why prefer constructor injection?

**Answer guide:** It makes required dependencies explicit, supports immutability, and simplifies testing.

## 7. What is component scanning?

**Answer guide:** Spring searches configured packages for annotated components to register as beans.

## 8. What is bean scope?

**Answer guide:** It controls bean lifecycle and visibility, such as singleton, prototype, request, or session.

## 9. What is @Autowired?

**Answer guide:** An annotation used to request dependency injection, though explicit constructor injection often needs no annotation.

## 10. What is AOP in Spring?

**Answer guide:** Aspect-oriented programming applies cross-cutting behavior such as logging or transactions around methods.

---

[← Back to Java Roadmap](../README.md)
