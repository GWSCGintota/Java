# Spring Boot Interview Questions

Use these as revision prompts. Try answering each question aloud before opening the answer.

## 1. What is Spring Boot?

**Answer guide:** A framework layer that simplifies creating production-ready Spring applications through auto-configuration, starters, and conventions.

## 2. What is auto-configuration?

**Answer guide:** Spring Boot configures beans automatically based on the classpath, existing beans, and configuration.

## 3. What are starters?

**Answer guide:** Curated dependency bundles such as `spring-boot-starter-web`.

## 4. application.properties vs application.yml?

**Answer guide:** Both configure applications; YAML offers hierarchical formatting while properties uses key-value lines.

## 5. What are profiles?

**Answer guide:** Named configuration sets for environments such as dev, test, and prod.

## 6. What does @SpringBootApplication include?

**Answer guide:** Commonly @Configuration, @EnableAutoConfiguration, and @ComponentScan.

## 7. What is @RestController?

**Answer guide:** A controller whose methods write response bodies by default.

## 8. How do you validate request DTOs?

**Answer guide:** Use Bean Validation annotations and `@Valid`/`@Validated`.

## 9. How do you handle global exceptions?

**Answer guide:** Commonly with `@ControllerAdvice` and `@ExceptionHandler`.

## 10. How should secrets be configured?

**Answer guide:** Use environment-specific secret management rather than committing secrets to source control.

---

[← Back to Java Roadmap](../README.md)
