# Collections Interview Questions

 1. What is the collection?
 2. What are the interface and classes in collection framework?
 3. Iterable Interface?


# Java Collections Framework --- Interview Questions

A comprehensive interview-question bank covering beginner, intermediate,
advanced, practical, coding, and scenario-based questions about the Java
Collections Framework.

## 1. Collections Framework Fundamentals

1.  What is the Java Collections Framework?
2.  Why was the Collections Framework introduced in Java?
3.  What are the main benefits of using the Collections Framework?
4.  What is the difference between a collection and the Collections
    Framework?
5.  What is the difference between `Collection` and `Collections`?
6.  What are the main interfaces in the Java Collections Framework?
7.  Explain the Java Collections Framework hierarchy.
8.  What is the root interface of the collection hierarchy?
9.  Why does `Map` not extend the `Collection` interface?
10. What is the difference between `Iterable` and `Collection`?
11. What is the difference between a collection and an array?
12. When would you prefer an array over a collection?
13. Can Java collections store primitive data types?
14. How are primitives stored in collections?
15. What role do generics play in collections?
16. What are heterogeneous collections?
17. What is a raw collection type?
18. Why should raw collection types generally be avoided?
19. What is the difference between mutable and immutable collections?
20. What is an unmodifiable collection?
21. Is an unmodifiable collection necessarily immutable?
22. What are the major implementations of `List`, `Set`, `Queue`, and
    `Map`?
23. Which collection should you use when insertion order matters?
24. Which collection should you use when duplicate elements are not
    allowed?
25. Which collection should you use for key-value pairs?
26. Which collection should you use for FIFO processing?
27. Which collection should you use for LIFO processing?
28. What is the purpose of the `java.util` package in relation to
    collections?

## 2. Collection Interface

29. What is the `Collection` interface?
30. What important methods are defined by `Collection`?
31. What does `add()` return?
32. What is the difference between `add()` and `addAll()`?
33. What is the difference between `remove()` and `removeAll()`?
34. What does `retainAll()` do?
35. What does `contains()` do?
36. What is the difference between `contains()` and `containsAll()`?
37. What does `clear()` do?
38. What is the difference between `size()` and `isEmpty()`?
39. What does `toArray()` do?
40. What happens when an implementation does not support a modification
    operation?
41. What is `UnsupportedOperationException` in collections?

## 3. List Interface

42. What is the `List` interface?
43. What are the characteristics of a `List`?
44. Does a `List` allow duplicate elements?
45. Does a `List` preserve insertion order?
46. Can a `List` contain `null`?
47. What are the major `List` implementations?
48. What is the difference between `List` and `Set`?
49. What is the difference between `List.of()` and `Arrays.asList()`?
50. What happens if you modify a list returned by `List.of()`?
51. What are indexed operations in a `List`?
52. What does `subList()` return?
53. Is `subList()` independent of the original list?
54. What problems can occur when modifying a list while working with a
    `subList()` view?

## 4. ArrayList

55. What is `ArrayList`?
56. How does `ArrayList` work internally?
57. What data structure is used internally by `ArrayList`?
58. What is the default capacity behavior of `ArrayList`?
59. What is the difference between size and capacity in an `ArrayList`?
60. How does an `ArrayList` grow when it runs out of capacity?
61. What is the time complexity of accessing an element in an
    `ArrayList`?
62. What is the time complexity of adding an element to the end of an
    `ArrayList`?
63. What is the time complexity of inserting an element in the middle?
64. What is the time complexity of removing an element from the middle?
65. Why is random access fast in `ArrayList`?
66. Why can insertion at the beginning of an `ArrayList` be expensive?
67. What is `ensureCapacity()`?
68. What is `trimToSize()`?
69. When should you specify the initial capacity of an `ArrayList`?
70. Is `ArrayList` synchronized?
71. How can you make an `ArrayList` thread-safe?
72. What is the difference between `ArrayList` and `Vector`?
73. What is the difference between `ArrayList` and `LinkedList`?
74. Can an `ArrayList` contain duplicate values?
75. Can an `ArrayList` contain `null`?
76. How do you remove duplicates from an `ArrayList`?
77. How do you sort an `ArrayList`?
78. How do you reverse an `ArrayList`?
79. How do you convert an array to an `ArrayList`?
80. How do you convert an `ArrayList` to an array?
81. What is the difference between `remove(int index)` and
    `remove(Object object)`?
82. What happens with `list.remove(1)` when the list contains `Integer`
    objects?
83. How would you remove the integer value `1` rather than index `1`?

## 5. LinkedList

84. What is `LinkedList`?
85. How does `LinkedList` work internally?
86. Is Java's `LinkedList` singly linked or doubly linked?
87. What is the time complexity of adding an element at the beginning of
    a `LinkedList`?
88. What is the time complexity of accessing an element by index?
89. Why is random access slower in `LinkedList`?
90. When should you choose `LinkedList` over `ArrayList`?
91. What interfaces does `LinkedList` implement?
92. How can `LinkedList` be used as a queue?
93. How can `LinkedList` be used as a deque?
94. Can `LinkedList` contain duplicates?
95. Can `LinkedList` contain `null`?
96. What is the difference between `addFirst()` and `offerFirst()`?
97. What is the difference between `getFirst()` and `peekFirst()`?
98. What is the difference between `removeFirst()` and `pollFirst()`?
99. Is `LinkedList` synchronized?

## 6. Vector and Stack

100. What is `Vector`?
101. How is `Vector` different from `ArrayList`?
102. Why is `Vector` considered a legacy collection?
103. Is `Vector` synchronized?
104. What is `Stack` in Java?
105. Why is the legacy `Stack` class generally discouraged?
106. What should be used instead of `Stack`?
107. How can `ArrayDeque` implement stack behavior?
108. What is the difference between `push()`, `pop()`, and `peek()`?

## 7. Set Interface

109. What is the `Set` interface?
110. What are the characteristics of a `Set`?
111. Why does a `Set` not allow duplicate elements?
112. What are the major implementations of `Set`?
113. What is the difference between `HashSet`, `LinkedHashSet`, and
     `TreeSet`?
114. Can a `Set` contain `null`?
115. How does a `Set` determine whether two elements are duplicates?
116. What is the relationship between `equals()` and `hashCode()` when
     using sets?
117. How do you remove duplicates from a list using a `Set`?
118. How do you preserve original order while removing duplicates?
119. How can you perform union, intersection, and difference operations
     using sets?

## 8. HashSet

120. What is `HashSet`?
121. How does `HashSet` work internally?
122. What data structure does `HashSet` use internally?
123. Does `HashSet` maintain insertion order?
124. Does `HashSet` maintain sorted order?
125. Can `HashSet` contain `null`?
126. How many `null` values can a `HashSet` contain?
127. What is the average time complexity of `add()`, `remove()`, and
     `contains()`?
128. What happens when two objects have the same hash code?
129. What happens if `equals()` is overridden but `hashCode()` is not?
130. What happens if `hashCode()` is overridden but `equals()` is not?
131. Why should objects used in a `HashSet` ideally be immutable with
     respect to equality fields?
132. What can happen if an object's hash-related fields change after
     insertion?
133. How is `HashSet` related to `HashMap`?
134. Is `HashSet` thread-safe?
135. How can you create a synchronized set?

## 9. LinkedHashSet

136. What is `LinkedHashSet`?
137. How is `LinkedHashSet` different from `HashSet`?
138. How does `LinkedHashSet` preserve insertion order?
139. What is the performance impact of maintaining insertion order?
140. When would you choose `LinkedHashSet`?
141. Can `LinkedHashSet` contain `null`?
142. Is `LinkedHashSet` synchronized?

## 10. SortedSet, NavigableSet, and TreeSet

143. What is `SortedSet`?
144. What is `NavigableSet`?
145. What is `TreeSet`?
146. How does `TreeSet` work internally?
147. What data structure is generally used internally by `TreeSet`?
148. Does `TreeSet` allow duplicate elements?
149. Does `TreeSet` preserve insertion order?
150. What order does `TreeSet` maintain?
151. What is the time complexity of `add()`, `remove()`, and
     `contains()` in `TreeSet`?
152. Can `TreeSet` store `null`?
153. What happens when incomparable objects are inserted into a
     `TreeSet`?
154. What is the difference between natural ordering and custom
     ordering?
155. How do `Comparable` and `Comparator` affect `TreeSet`?
156. What are `first()` and `last()`?
157. What are `higher()`, `lower()`, `ceiling()`, and `floor()`?
158. What are `headSet()`, `tailSet()`, and `subSet()`?
159. What is `descendingSet()`?
160. What is the difference between `TreeSet` and `HashSet`?
161. What is the difference between `TreeSet` and `LinkedHashSet`?

## 11. Queue Interface

162. What is the `Queue` interface?
163. What is FIFO?
164. What are the common implementations of `Queue`?
165. What is the difference between `add()` and `offer()`?
166. What is the difference between `remove()` and `poll()`?
167. What is the difference between `element()` and `peek()`?
168. When should `offer()`, `poll()`, and `peek()` be preferred?
169. Can queues contain `null`?
170. Why do many queue implementations prohibit `null`?
171. How do you implement a queue using `LinkedList`?
172. How do you implement a queue using `ArrayDeque`?

## 12. PriorityQueue

173. What is `PriorityQueue`?
174. How does `PriorityQueue` work internally?
175. What data structure is generally used internally by
     `PriorityQueue`?
176. Does `PriorityQueue` follow strict FIFO order?
177. What determines the head of a `PriorityQueue`?
178. How do you create a min-heap using `PriorityQueue`?
179. How do you create a max-heap using `PriorityQueue`?
180. What is the time complexity of insertion and removal?
181. Can `PriorityQueue` contain `null`?
182. Can `PriorityQueue` contain duplicate elements?
183. Does iterating over a `PriorityQueue` return elements in priority
     order?
184. How can you retrieve all elements in actual priority order?
185. How do you use a custom `Comparator` with `PriorityQueue`?

## 13. Deque and ArrayDeque

186. What is `Deque`?
187. What does double-ended queue mean?
188. What is `ArrayDeque`?
189. How is `ArrayDeque` different from `LinkedList`?
190. Why is `ArrayDeque` usually preferred over `Stack`?
191. How can `ArrayDeque` be used as a stack?
192. How can `ArrayDeque` be used as a queue?
193. Can `ArrayDeque` contain `null`?
194. What is the difference between `addFirst()` and `offerFirst()`?
195. What is the difference between `removeFirst()` and `pollFirst()`?
196. What is the difference between `getFirst()` and `peekFirst()`?
197. Is `ArrayDeque` thread-safe?

## 14. Map Interface

198. What is the `Map` interface?
199. Why does `Map` not extend `Collection`?
200. What are the major `Map` implementations?
201. What is a key-value pair?
202. Can a map contain duplicate keys?
203. Can a map contain duplicate values?
204. What happens when you insert a value using an existing key?
205. What is the difference between `put()` and `putIfAbsent()`?
206. What does `putAll()` do?
207. What is the difference between `get()` and `getOrDefault()`?
208. What is the difference between `containsKey()` and
     `containsValue()`?
209. What do `keySet()`, `values()`, and `entrySet()` return?
210. What is `Map.Entry`?
211. What is the best way to iterate over both keys and values?
212. What does `replace()` do?
213. What does `replaceAll()` do?
214. What does `compute()` do?
215. What does `computeIfAbsent()` do?
216. What does `computeIfPresent()` do?
217. What does `merge()` do?
218. When is `computeIfAbsent()` particularly useful?
219. How do you create an immutable map?
220. What happens when duplicate keys are provided to `Map.of()`?

## 15. HashMap

221. What is `HashMap`?
222. Explain how `HashMap` works internally.
223. How are keys stored in a `HashMap`?
224. What are buckets in a `HashMap`?
225. What is hashing?
226. How is a bucket selected for a key?
227. What is a hash collision?
228. How does `HashMap` handle collisions?
229. What role does `hashCode()` play in `HashMap`?
230. What role does `equals()` play in `HashMap`?
231. Explain the `equals()` and `hashCode()` contract.
232. What happens when two different keys have the same hash code?
233. Can two equal objects have different hash codes?
234. Can two unequal objects have the same hash code?
235. What is the default load factor of `HashMap`?
236. What is the purpose of the load factor?
237. What is the initial capacity of a `HashMap`?
238. What happens when the resize threshold is reached?
239. What is rehashing/resizing?
240. Why can resizing a `HashMap` be expensive?
241. What is the average time complexity of `put()` and `get()`?
242. What is the worst-case complexity of `HashMap` operations?
243. How did collision handling change in Java 8?
244. Under what circumstances can a heavily populated bucket be
     treeified?
245. Why are balanced trees useful for severe collision cases?
246. Can `HashMap` contain a `null` key?
247. How many `null` keys can a `HashMap` contain?
248. Can `HashMap` contain `null` values?
249. Does `HashMap` maintain insertion order?
250. Is `HashMap` synchronized?
251. Is `HashMap` thread-safe?
252. Why is using a mutable object as a `HashMap` key dangerous?
253. What properties should a good `HashMap` key have?
254. Can a custom class be used as a `HashMap` key?
255. What must you consider when using a custom class as a key?
256. What happens if a key's fields used by `equals()` and `hashCode()`
     are modified after insertion?
257. How do you iterate over a `HashMap` efficiently?
258. Why is iterating over `entrySet()` often preferable to iterating
     over `keySet()` and calling `get()`?
259. What is the difference between `HashMap` and `Hashtable`?
260. What is the difference between `HashMap` and `ConcurrentHashMap`?

## 16. LinkedHashMap

261. What is `LinkedHashMap`?
262. How is `LinkedHashMap` different from `HashMap`?
263. Does `LinkedHashMap` maintain insertion order?
264. What is access-order mode in `LinkedHashMap`?
265. What is the difference between insertion order and access order?
266. How can `LinkedHashMap` be used to implement an LRU cache?
267. What is `removeEldestEntry()`?
268. Can `LinkedHashMap` contain a `null` key?
269. Is `LinkedHashMap` thread-safe?

## 17. SortedMap, NavigableMap, and TreeMap

270. What is `SortedMap`?
271. What is `NavigableMap`?
272. What is `TreeMap`?
273. How does `TreeMap` work internally?
274. What data structure is generally used by `TreeMap`?
275. How are keys ordered in a `TreeMap`?
276. What is the difference between natural ordering and comparator
     ordering?
277. Can `TreeMap` contain a `null` key?
278. Can `TreeMap` contain `null` values?
279. What is the time complexity of `put()`, `get()`, and `remove()`?
280. What is the difference between `HashMap` and `TreeMap`?
281. What is the difference between `TreeMap` and `LinkedHashMap`?
282. What are `firstKey()` and `lastKey()`?
283. What are `higherKey()`, `lowerKey()`, `ceilingKey()`, and
     `floorKey()`?
284. What are `headMap()`, `tailMap()`, and `subMap()`?
285. What does `descendingMap()` do?
286. What happens if keys are not mutually comparable?
287. How can a custom `Comparator` be supplied to a `TreeMap`?

## 18. Hashtable

288. What is `Hashtable`?
289. Why is `Hashtable` considered a legacy class?
290. Is `Hashtable` synchronized?
291. Does `Hashtable` allow `null` keys?
292. Does `Hashtable` allow `null` values?
293. What is the difference between `Hashtable` and `HashMap`?
294. What should generally be used instead of `Hashtable` for concurrent
     applications?

## 19. WeakHashMap, IdentityHashMap, and EnumMap

295. What is `WeakHashMap`?
296. What is a weak reference?
297. When can entries disappear from a `WeakHashMap`?
298. What are practical uses of `WeakHashMap`?
299. What is `IdentityHashMap`?
300. How does `IdentityHashMap` compare keys?
301. What is the difference between `==` and `equals()` in this context?
302. When might `IdentityHashMap` be useful?
303. What is `EnumMap`?
304. Why is `EnumMap` efficient?
305. What type of keys can `EnumMap` contain?
306. When should `EnumMap` be preferred over `HashMap`?

## 20. Comparable and Comparator

307. What is `Comparable`?
308. What is `Comparator`?
309. What is the difference between `Comparable` and `Comparator`?
310. What method is defined by `Comparable`?
311. What method is defined by `Comparator`?
312. What is natural ordering?
313. How do you sort objects using multiple fields?
314. What is `Comparator.comparing()`?
315. What is `thenComparing()`?
316. What is `reversed()`?
317. What are `nullsFirst()` and `nullsLast()`?
318. Can a class have multiple comparators?
319. Can a class have multiple natural orderings?
320. What happens if a comparator is inconsistent with `equals()`?
321. Why can comparator consistency matter in `TreeSet` and `TreeMap`?
322. How do you sort strings by length?
323. How do you sort employees by salary and then by name?

## 21. Iterator and ListIterator

324. What is an `Iterator`?
325. Why do collections provide iterators?
326. What methods are available in `Iterator`?
327. What does `hasNext()` do?
328. What does `next()` do?
329. What does `remove()` do?
330. What is `ListIterator`?
331. What is the difference between `Iterator` and `ListIterator`?
332. Can an `Iterator` move backward?
333. Can a `ListIterator` move backward?
334. Which collections support `ListIterator`?
335. What are `nextIndex()` and `previousIndex()`?
336. How can elements be added or replaced through a `ListIterator`?
337. What is `ConcurrentModificationException`?
338. Why can removing an element directly from a collection during
     iteration cause an exception?
339. What is the correct way to remove elements while iterating with an
     `Iterator`?
340. Can `Iterator.remove()` always be used?
341. What is the difference between an iterator and an enhanced `for`
     loop?

## 22. Fail-Fast and Weakly Consistent Iteration

342. What is a fail-fast iterator?
343. Give examples of collections whose iterators are typically
     fail-fast.
344. How does modification detection generally work?
345. Is fail-fast behavior guaranteed?
346. What is a weakly consistent iterator?
347. How are concurrent collection iterators different from normal
     collection iterators?
348. Why does `ConcurrentHashMap` iteration generally not throw
     `ConcurrentModificationException`?
349. What is a snapshot-style iterator?
350. How does `CopyOnWriteArrayList` iteration behave?

## 23. Collections Utility Class

351. What is the `Collections` class?
352. What is the difference between `Collection` and `Collections`?
353. What does `Collections.sort()` do?
354. What does `Collections.reverse()` do?
355. What does `Collections.shuffle()` do?
356. What does `Collections.swap()` do?
357. What do `Collections.min()` and `Collections.max()` do?
358. What does `Collections.frequency()` do?
359. What does `Collections.binarySearch()` do?
360. What requirement must generally be satisfied before using
     `binarySearch()`?
361. What does `Collections.fill()` do?
362. What does `Collections.copy()` do?
363. What does `Collections.unmodifiableList()` do?
364. What is the difference between `Collections.unmodifiableList()` and
     `List.of()`?
365. What does `Collections.synchronizedList()` do?
366. What synchronization consideration applies when iterating over a
     synchronized wrapper?
367. What are singleton collections?

## 24. Arrays Utility and Collections

368. What is `Arrays.asList()`?
369. Is the list returned by `Arrays.asList()` resizable?
370. Can you call `set()` on a list returned by `Arrays.asList()`?
371. What happens if you call `add()` or `remove()` on it?
372. Is the returned list backed by the original array?
373. What happens to the array if the list is modified using `set()`?
374. What happens to the list if the array is modified?
375. How is `List.of()` different from `Arrays.asList()`?
376. How do you create a mutable `ArrayList` from an array?
377. What is `Arrays.sort()`?
378. What is the difference between sorting arrays and sorting lists?

## 25. equals() and hashCode()

379. What is `equals()`?
380. What is `hashCode()`?
381. What is the contract of `equals()`?
382. What is the contract between `equals()` and `hashCode()`?
383. If two objects are equal, must they have the same hash code?
384. If two objects have the same hash code, must they be equal?
385. Why are `equals()` and `hashCode()` important in `HashMap`?
386. Why are they important in `HashSet`?
387. What happens when `hashCode()` always returns the same value?
388. Will a `HashMap` still work correctly if all keys return the same
     hash code?
389. What happens to performance in that situation?
390. What fields should generally be used when implementing `equals()`
     and `hashCode()`?
391. Why should mutable fields be treated carefully?
392. How do Java records help with equality-based collection use cases?

## 26. Generics and Collections

393. Why are generics important for collections?
394. What is type safety?
395. What is the difference between `List<Object>` and `List<?>`?
396. What is an unbounded wildcard?
397. What is an upper-bounded wildcard?
398. What is a lower-bounded wildcard?
399. Explain PECS: Producer Extends, Consumer Super.
400. Why can't `List<String>` be assigned to `List<Object>`?
401. What is generic invariance?
402. What can be added to `List<?>`?
403. When would you use `List<? extends Number>`?
404. When would you use `List<? super Integer>`?
405. How do generics eliminate many explicit casts?

## 27. Java 8+ Functional Operations on Collections

406. What is `forEach()`?
407. What is `removeIf()`?
408. What is `replaceAll()` on `List`?
409. What is `sort()` on `List`?
410. How are lambda expressions used with collections?
411. How are method references used with collections?
412. How do collections interact with the Stream API?
413. What is the difference between a collection and a stream?
414. Can a stream be reused?
415. What is the difference between `map()` and `flatMap()`?
416. How do you filter a collection using streams?
417. How do you convert a stream back into a list?
418. What is `Collectors.toList()`?
419. What is `Stream.toList()`?
420. What mutability difference can matter between different list
     collectors/results?
421. How do you convert a list into a map using streams?
422. What happens when `Collectors.toMap()` encounters duplicate keys
     without a merge function?
423. How do you handle duplicate keys with `Collectors.toMap()`?
424. How do you group objects using `Collectors.groupingBy()`?
425. How do you partition objects using `Collectors.partitioningBy()`?
426. How do you count grouped values?
427. How do you find duplicates using streams?
428. How do you find the most frequent element using streams and maps?

## 28. Immutable and Unmodifiable Collections

429. What is an immutable collection?
430. What is an unmodifiable view?
431. What is the difference between immutable and unmodifiable?
432. What does `List.of()` create?
433. What does `Set.of()` create?
434. What does `Map.of()` create?
435. Do `List.of()`, `Set.of()`, and `Map.of()` allow `null`?
436. What happens if duplicate values are passed to `Set.of()`?
437. What happens if duplicate keys are passed to `Map.of()`?
438. What are `List.copyOf()`, `Set.copyOf()`, and `Map.copyOf()`?
439. Why are immutable collections useful?
440. Does an immutable collection make mutable objects stored inside it
     immutable?

## 29. Concurrent Collections

441. Why are ordinary collections not necessarily safe for concurrent
     access?
442. What are concurrent collections?
443. What is `ConcurrentHashMap`?
444. How is `ConcurrentHashMap` different from `HashMap`?
445. How is `ConcurrentHashMap` different from `Hashtable`?
446. Does `ConcurrentHashMap` allow `null` keys or values?
447. Why are `null` keys and values prohibited in `ConcurrentHashMap`?
448. What is `CopyOnWriteArrayList`?
449. How does `CopyOnWriteArrayList` work?
450. When is `CopyOnWriteArrayList` appropriate?
451. Why are writes expensive in `CopyOnWriteArrayList`?
452. What is `CopyOnWriteArraySet`?
453. What is `ConcurrentLinkedQueue`?
454. What is `ConcurrentLinkedDeque`?
455. What is `BlockingQueue`?
456. What is the difference between `Queue` and `BlockingQueue`?
457. What are `put()` and `take()`?
458. What is `ArrayBlockingQueue`?
459. What is `LinkedBlockingQueue`?
460. What is `PriorityBlockingQueue`?
461. What is `DelayQueue`?
462. What is `SynchronousQueue`?
463. When would you use a blocking queue?
464. How can `BlockingQueue` support the producer-consumer pattern?
465. What is `ConcurrentSkipListMap`?
466. What is `ConcurrentSkipListSet`?
467. When would you choose `ConcurrentSkipListMap` over
     `ConcurrentHashMap`?

## 30. Synchronization and Thread Safety

468. What does thread-safe mean?
469. Which legacy collection classes are synchronized?
470. How does `Collections.synchronizedList()` work?
471. Is wrapping a collection with a synchronized wrapper enough for
     every compound operation?
472. Why may external synchronization still be required?
473. What is the difference between synchronized collections and
     concurrent collections?
474. Why are concurrent collections often more scalable?
475. What is a race condition involving collections?
476. Why is check-then-act logic dangerous with shared maps?
477. How can `putIfAbsent()` help?
478. How can `computeIfAbsent()` help with atomic-style map operations?

## 31. Performance and Complexity

479. What is Big-O notation?
480. What is the average complexity of `ArrayList.get()`?
481. What is the complexity of searching an unsorted `ArrayList`?
482. What is the complexity of inserting at the beginning of an
     `ArrayList`?
483. What is the complexity of `LinkedList.get(index)`?
484. What is the complexity of adding at either end of a `LinkedList`?
485. What is the average complexity of `HashMap.get()`?
486. What is the average complexity of `HashSet.contains()`?
487. What is the complexity of `TreeMap.get()`?
488. What is the complexity of `TreeSet.contains()`?
489. What is the complexity of `PriorityQueue.offer()`?
490. Why does choosing the correct collection matter for performance?
491. How does initial capacity affect hash-based collections?
492. How does load factor affect `HashMap` performance and memory usage?
493. Why can excessive hash collisions hurt performance?
494. Why can `LinkedList` use more memory per element than `ArrayList`?
495. Why can `ArrayList` be faster than `LinkedList` even for some
     iteration workloads?
496. What is cache locality, and why can it benefit array-backed
     collections?

## 32. Choosing the Correct Collection

497. Which collection would you choose for fast indexed access?
498. Which collection would you choose for unique unordered values?
499. Which collection would you choose for unique insertion-ordered
     values?
500. Which collection would you choose for sorted unique values?
501. Which map would you choose for general-purpose key-value lookup?
502. Which map would you choose when insertion order matters?
503. Which map would you choose when keys must remain sorted?
504. Which collection would you choose for priority-based processing?
505. Which collection would you choose for stack operations?
506. Which collection would you choose for queue operations?
507. Which collection would you choose for frequent reads and rare
     writes in a concurrent environment?
508. Which map would you choose for high-concurrency lookup/update
     operations?
509. Which map would you choose for enum keys?
510. Which map would you choose when entries should be removable by
     garbage collection after keys are no longer strongly referenced?
511. Which collection would you use to implement an LRU cache?

## 33. Common Tricky Interview Questions

512. Why is `Map` not a `Collection`?
513. Why does `HashSet` use `HashMap` internally?
514. Can you store duplicate keys in a `HashMap`?
515. What happens when the same key is inserted twice?
516. Can a `HashMap` have duplicate values?
517. Can `HashSet` contain custom objects?
518. Why might a `HashSet` appear to contain duplicate custom objects?
519. Why can a `TreeSet` reject two objects that are unequal according
     to `equals()`?
520. Can `null` be inserted into `TreeSet`?
521. Why does `ArrayDeque` prohibit `null`?
522. Why is `ArrayDeque` generally better than `Stack`?
523. Why is `ArrayList` often preferred over `LinkedList`?
524. Can you modify a collection inside an enhanced `for` loop?
525. How can you safely remove items during iteration?
526. What is the difference between structural and non-structural
     modification?
527. Why can `iterator.remove()` work while `list.remove()` during
     iteration may fail?
528. Why can a mutable map key become "lost" after insertion?
529. Can a `HashMap` key be an array?
530. What equality behavior do arrays have by default?
531. What alternatives can be used if array-content equality is required
     for keys?
532. Why is `String` a good `HashMap` key?
533. Can `List` itself be used as a `HashMap` key?
534. What danger exists if that list is modified later?
535. Why should `compareTo()` ideally be consistent with `equals()`?
536. What happens when a comparator always returns `0` in a `TreeSet`?
537. Why doesn't iterating a `PriorityQueue` guarantee sorted output?
538. What happens if you modify the original collection behind an
     unmodifiable view?
539. What is the difference between shallow immutability and deep
     immutability?

## 34. Coding Interview Questions

540. Write a program to remove duplicate elements from a list.
541. Remove duplicates while preserving insertion order.
542. Find duplicate elements in a list.
543. Count the frequency of every element in a list.
544. Find the first non-repeated element.
545. Find the first repeated element.
546. Find the most frequently occurring element.
547. Find the least frequently occurring element.
548. Sort a list of integers in ascending order.
549. Sort a list in descending order.
550. Reverse a list.
551. Shuffle a list.
552. Find the maximum and minimum elements in a list.
553. Find the second-largest element in a list.
554. Find the top `K` largest elements.
555. Merge two lists.
556. Find the intersection of two lists.
557. Find the union of two lists.
558. Find the difference between two lists.
559. Convert a list to a set.
560. Convert a set to a list.
561. Convert an array to a list.
562. Convert a list to an array.
563. Convert a list of objects into a map.
564. Group employees by department.
565. Group words by their length.
566. Group anagrams using a map.
567. Count word occurrences in a sentence using `HashMap`.
568. Sort a map by key.
569. Sort a map by value.
570. Find the key with the highest value.
571. Invert a map from key-value to value-key.
572. Handle duplicate values while inverting a map.
573. Iterate through a map using `entrySet()`.
574. Find common keys between two maps.
575. Merge two maps.
576. Merge two maps while resolving duplicate keys.
577. Implement a stack using `ArrayDeque`.
578. Implement a queue using `ArrayDeque`.
579. Implement a priority queue with custom objects.
580. Implement an LRU cache using `LinkedHashMap`.
581. Implement a frequency map.
582. Implement a simple cache using `HashMap`.
583. Find whether two lists contain the same elements with the same
     frequencies.
584. Find whether two sets are equal.
585. Find missing numbers using a set.
586. Find the first unique character using `LinkedHashMap`.
587. Find duplicate characters using a map.
588. Sort custom objects using `Comparable`.
589. Sort custom objects using `Comparator`.
590. Sort employees by salary and then name.
591. Remove elements satisfying a condition using `removeIf()`.
592. Filter a list using streams.
593. Partition numbers into even and odd groups.
594. Find duplicate elements using streams.
595. Convert a nested list into a flat list.
596. Find the frequency of each character using streams.
597. Find the most frequent word using streams and collectors.
598. Build a multimap-like structure using `Map<K, List<V>>`.
599. Build a graph adjacency list using collections.
600. Implement producer-consumer using `BlockingQueue`.

## 35. Scenario-Based Interview Questions

601. You need to store one million records and frequently access them by
     index. Which collection would you choose and why?
602. You need unique user IDs and do not care about ordering. Which
     collection would you choose?
603. You need unique records while preserving insertion order. Which
     collection would you choose?
604. You need unique records maintained in sorted order. Which
     collection would you choose?
605. You need very fast lookup by a unique key. Which map would you
     choose?
606. You need key-value data displayed in insertion order. Which map
     would you choose?
607. You need a sorted phone book keyed by name. Which map would you
     choose?
608. You need to process jobs based on priority. Which collection would
     you choose?
609. You need to process requests in FIFO order. Which collection would
     you choose?
610. You need stack behavior for an algorithm. Which Java collection
     would you choose?
611. You need a thread-safe map with many concurrent readers and
     writers. What would you choose?
612. You need a thread-safe list with very frequent reads and very rare
     writes. What would you choose?
613. You need producers to wait when a queue is full and consumers to
     wait when it is empty. What would you choose?
614. You need a cache that automatically evicts the least recently used
     entry. How would you implement it?
615. You need to store metadata without preventing keys from being
     garbage collected. Which map might be appropriate?
616. You need to use enum constants as map keys. Which implementation is
     best?
617. You have a large `ArrayList` with frequent insertions at index `0`.
     What performance issue would you expect?
618. You have a `LinkedList` but repeatedly call `get(i)` in a loop. Why
     might performance be poor?
619. A `HashMap` suddenly becomes slow with custom keys. What would you
     investigate?
620. A `HashMap.get(key)` returns `null` after a field in the key object
     was changed. Why might this happen?
621. A `HashSet` contains objects that appear identical. What methods
     would you inspect?
622. A `TreeSet` stores fewer objects than expected. What would you
     inspect in the comparator?
623. Your application throws `ConcurrentModificationException`. What are
     the likely causes and solutions?
624. Multiple threads update a normal `HashMap`. What risks exist?
625. You need an immutable configuration list. What Java APIs could you
     use?

## 36. Advanced Design and Conceptual Questions

626. What design patterns can be observed in the Collections Framework?
627. How is the Iterator pattern used in Java collections?
628. How does the Strategy pattern relate to `Comparator`?
629. Why are interfaces heavily used in the Collections Framework?
630. Why should variables often be declared using interface types such
     as `List` instead of implementation types such as `ArrayList`?
631. Explain "program to an interface, not an implementation" using
     collections.
632. What is a collection view?
633. Are `Map.keySet()`, `Map.values()`, and `Map.entrySet()` backed by
     the original map?
634. What happens if an entry is removed through `keySet()`?
635. Can you add an arbitrary key through `keySet()`?
636. What is a backed view?
637. How does `subList()` demonstrate a backed view?
638. What problems can occur when backed views and their original
     collections are modified independently?
639. What is defensive copying?
640. Why is defensive copying important when exposing collections from a
     class?
641. How can `List.copyOf()` be used for defensive copying?
642. What is the difference between returning an unmodifiable view and
     returning a copy?
643. What is shallow copying of a collection?
644. How would you perform a deep copy of objects in a collection?
645. Why doesn't copying a collection automatically clone its elements?
646. What are marker interfaces related to collections?
647. What is `RandomAccess`?
648. Which common list implementation implements `RandomAccess`?
649. Why might an algorithm check whether a list implements
     `RandomAccess`?
650. What is `Spliterator`?
651. How is `Spliterator` different from `Iterator`?
652. How does `Spliterator` support parallel processing?
653. What are spliterator characteristics such as `ORDERED`, `DISTINCT`,
     and `SORTED`?
654. What is a `SequencedCollection` in modern Java?
655. What are `SequencedSet` and `SequencedMap`?
656. What problem do sequenced collection APIs address?
657. What operations are available for accessing first and last elements
     in sequenced collections?
658. What is a reversed view of a sequenced collection?
659. How do modern sequenced APIs improve consistency across ordered
     collections?

## 37. Quick Comparison Questions

660. `ArrayList` vs `LinkedList`?
661. `ArrayList` vs `Vector`?
662. `Vector` vs `CopyOnWriteArrayList`?
663. `HashSet` vs `LinkedHashSet`?
664. `HashSet` vs `TreeSet`?
665. `LinkedHashSet` vs `TreeSet`?
666. `HashMap` vs `Hashtable`?
667. `HashMap` vs `LinkedHashMap`?
668. `HashMap` vs `TreeMap`?
669. `HashMap` vs `ConcurrentHashMap`?
670. `TreeMap` vs `ConcurrentSkipListMap`?
671. `Queue` vs `Deque`?
672. `LinkedList` vs `ArrayDeque`?
673. `Stack` vs `ArrayDeque`?
674. `PriorityQueue` vs ordinary FIFO queue?
675. `Comparable` vs `Comparator`?
676. `Iterator` vs `ListIterator`?
677. Fail-fast vs weakly consistent iterator?
678. `Collection` vs `Collections`?
679. `List` vs `Set`?
680. `Set` vs `Map`?
681. `List.of()` vs `Arrays.asList()`?
682. `List.of()` vs `Collections.unmodifiableList()`?
683. Immutable collection vs unmodifiable view?
684. `HashMap.keySet()` vs `HashMap.entrySet()`?
685. `put()` vs `putIfAbsent()`?
686. `computeIfAbsent()` vs `putIfAbsent()`?
687. `get()` vs `getOrDefault()`?
688. `add()` vs `offer()`?
689. `remove()` vs `poll()`?
690. `element()` vs `peek()`?

## 38. High-Priority Questions to Master

If you are preparing for an interview, make sure you can explain these
especially well:

1.  Explain the Java Collections Framework hierarchy.
2.  `ArrayList` vs `LinkedList`.
3.  `List` vs `Set` vs `Map`.
4.  `HashSet` vs `LinkedHashSet` vs `TreeSet`.
5.  Explain how `HashMap` works internally.
6.  Explain `equals()` and `hashCode()`.
7.  Explain hash collisions and how `HashMap` handles them.
8.  Why should map keys generally be immutable?
9.  `HashMap` vs `LinkedHashMap` vs `TreeMap`.
10. `HashMap` vs `ConcurrentHashMap`.
11. `HashMap` vs `Hashtable`.
12. `Comparable` vs `Comparator`.
13. `Iterator` vs `ListIterator`.
14. Explain `ConcurrentModificationException`.
15. Explain fail-fast and concurrent/weakly consistent iteration.
16. `ArrayDeque` vs `Stack`.
17. Explain `PriorityQueue`.
18. `List.of()` vs `Arrays.asList()`.
19. Immutable vs unmodifiable collections.
20. How do you choose the right collection for a particular problem?
21. Explain the common time complexities of `ArrayList`, `HashMap`,
    `HashSet`, `TreeMap`, and `TreeSet`.
22. Explain `ConcurrentHashMap`, `CopyOnWriteArrayList`, and
    `BlockingQueue`.
23. Explain PECS with Java collection generics.
24. Explain backed views such as `subList()`, `keySet()`, and
    `entrySet()`.
25. Solve common coding problems involving lists, sets, maps, queues,
    and streams.


[← Back to Java Roadmap](../README.md)
