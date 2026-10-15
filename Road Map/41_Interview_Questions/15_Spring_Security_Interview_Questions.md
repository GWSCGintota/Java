# Spring Security Interview Questions

Use these as revision prompts. Try answering each question aloud before opening the answer.

## 1. Authentication vs authorization?

**Answer guide:** Authentication verifies identity; authorization decides what an identity may do.

## 2. What is Spring Security?

**Answer guide:** A framework for authentication, authorization, and common web security protections in Spring applications.

## 3. What is BCrypt?

**Answer guide:** A password-hashing algorithm designed to be computationally expensive and salted.

## 4. Should passwords be encrypted or hashed?

**Answer guide:** Passwords should normally be stored using a slow password hash, not reversible encryption.

## 5. What is JWT?

**Answer guide:** A signed token format containing claims; it is not encryption by default.

## 6. Access token vs refresh token?

**Answer guide:** Access tokens are short-lived credentials for APIs; refresh tokens obtain new access tokens.

## 7. What is OAuth 2.0?

**Answer guide:** An authorization framework that delegates access using tokens.

## 8. What is OpenID Connect?

**Answer guide:** An identity layer on top of OAuth 2.0 for user authentication.

## 9. What is CORS?

**Answer guide:** A browser security mechanism controlling cross-origin requests.

## 10. What is CSRF?

**Answer guide:** An attack that tricks a user's browser into sending an unwanted authenticated request.

## 11. What is XSS?

**Answer guide:** Injection of malicious script into content executed by a user's browser.

## 12. How do you secure a REST API?

**Answer guide:** Use TLS, strong authentication/authorization, input validation, least privilege, safe token handling, rate limiting where appropriate, and secure error/logging practices.

---

[← Back to Java Roadmap](../README.md)
