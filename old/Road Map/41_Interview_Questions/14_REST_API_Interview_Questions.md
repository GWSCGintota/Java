# REST API Interview Questions

Use these as revision prompts. Try answering each question aloud before opening the answer.

## 1. What is REST?

**Answer guide:** An architectural style for resource-oriented network APIs using standard HTTP semantics.

## 2. GET vs POST vs PUT vs PATCH vs DELETE?

**Answer guide:** GET reads; POST usually creates/actions; PUT replaces/upserts; PATCH partially updates; DELETE removes.

## 3. What does idempotent mean?

**Answer guide:** Repeating the same request has the same intended server-side effect as making it once.

## 4. Which HTTP methods are typically idempotent?

**Answer guide:** GET, PUT, DELETE, HEAD, and OPTIONS are defined as idempotent; POST generally is not.

## 5. What is a DTO?

**Answer guide:** An object designed to transfer data across boundaries without exposing persistence/domain internals directly.

## 6. What status code for successful creation?

**Answer guide:** 201 Created, commonly with a Location header when appropriate.

## 7. 400 vs 401 vs 403 vs 404?

**Answer guide:** 400 bad request, 401 unauthenticated, 403 authenticated but forbidden, 404 resource not found.

## 8. How do you paginate an API?

**Answer guide:** Use parameters such as page/size or cursor-based pagination and return navigation metadata when useful.

## 9. What is API versioning?

**Answer guide:** A strategy to evolve contracts while supporting older clients, such as URL, header, or media-type versioning.

## 10. What is OpenAPI?

**Answer guide:** A machine-readable specification for describing HTTP APIs.

---

[← Back to Java Roadmap](../README.md)
