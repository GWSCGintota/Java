# SQL & JDBC Interview Questions

Use these as revision prompts. Try answering each question aloud before opening the answer.

## 1. Primary key vs foreign key?

**Answer guide:** A primary key uniquely identifies a row; a foreign key references a key in another table.

## 2. INNER JOIN vs LEFT JOIN?

**Answer guide:** INNER JOIN keeps matches from both sides; LEFT JOIN keeps all rows from the left plus matches.

## 3. What is normalization?

**Answer guide:** Structuring relational data to reduce redundancy and update anomalies.

## 4. What is an index?

**Answer guide:** A data structure that speeds lookups at the cost of storage and write overhead.

## 5. What is a transaction?

**Answer guide:** A logical unit of database work that should follow ACID properties.

## 6. What are ACID properties?

**Answer guide:** Atomicity, Consistency, Isolation, Durability.

## 7. Statement vs PreparedStatement?

**Answer guide:** PreparedStatement supports parameter binding, reuse, and safer protection against SQL injection.

## 8. What is ResultSet?

**Answer guide:** JDBC's representation of rows returned by a query.

## 9. What is connection pooling?

**Answer guide:** Reusing a managed set of database connections rather than creating one per request.

## 10. How do you prevent SQL injection?

**Answer guide:** Use parameterized queries/PreparedStatement and avoid concatenating untrusted input into SQL.

---

[← Back to Java Roadmap](../README.md)
