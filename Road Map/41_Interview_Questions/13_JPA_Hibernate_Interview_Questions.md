# JPA & Hibernate Interview Questions

Use these as revision prompts. Try answering each question aloud before opening the answer.

## 1. What is ORM?

**Answer guide:** Mapping objects in code to relational database structures.

## 2. JPA vs Hibernate?

**Answer guide:** JPA is a specification/API; Hibernate is a popular implementation.

## 3. What is an entity?

**Answer guide:** A persistent Java class mapped to a database table or view-like structure.

## 4. What is the persistence context?

**Answer guide:** A managed set of entity instances tracked for identity and changes.

## 5. Lazy vs eager loading?

**Answer guide:** Lazy loads related data on demand; eager loads it immediately.

## 6. What is the N+1 query problem?

**Answer guide:** One query loads parents, then additional queries are executed for each parent's related data.

## 7. What is cascading?

**Answer guide:** Propagation of persistence operations from one entity to related entities.

## 8. What is orphanRemoval?

**Answer guide:** It can delete child entities removed from an owned relationship.

## 9. JPQL vs native query?

**Answer guide:** JPQL queries entities and fields; native queries use database SQL directly.

## 10. What is dirty checking?

**Answer guide:** Hibernate detects changes to managed entities and writes updates automatically at flush/commit.

## 11. What does @Transactional do?

**Answer guide:** It defines transaction boundaries around method execution.

## 12. What is optimistic locking?

**Answer guide:** A concurrency strategy usually using a version field to detect conflicting updates.

---

[← Back to Java Roadmap](../README.md)
