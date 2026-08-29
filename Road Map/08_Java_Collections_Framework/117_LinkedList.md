# 117. LinkedList

**Section:** Java Collections Framework  
**Level:** Beginner → Intermediate

## 1. What is Java LinkedList?

Java **LinkedList** is a class that implements a doubly linked list data structure. It is part of the Java Collections Framework and is used to store elements dynamically.

Unlike arrays, LinkedList does not store elements in continuous memory. Instead, it stores elements as nodes, where each node contains:

- The actual data
- A reference to the next node
- A reference to the previous node
- This makes insertion and deletion operations faster and more efficient.

It also allows duplicate elements, maintains insertion order, and can grow or shrink dynamically based on the data.


## 2. LinkedList Class Declaration

```
public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>, Deque<E>, Cloneable, Serializable  


LinkedList<Type> list = new LinkedList<>();  
```

## 3. Traversing LinkedList Elements

You can create a LinkedList and traverse its elements using an iterator.
```
import java.util.*;    
public class Main{    
 public static void main(String args[]){    
    
  LinkedList<String> al=new LinkedList<String>();    
  al.add("Ravi");    
  al.add("Vijay");    
  al.add("Ravi");    
  al.add("Ajay");    
    
  Iterator<String> itr=al.iterator();    
  while(itr.hasNext()){    
   System.out.println(itr.next());    
  }    
 }    
}  
```

**Output:**

```
Ravi
Vijay
Ravi
Ajay
```

## 4. Adding Elements to LinkedList

You can add elements to a LinkedList by using different ways like add(E e), add(int index, E element), addAll(Collection c), addAll(int index, Collection c), addFirst(E e), and addLast(E e) methods.
```
import java.util.*;    
public class Main{    
 public static void main(String args[]){    
 LinkedList<String> ll=new LinkedList<String>();    
 System.out.println("Initial list of elements: "+ll);    
  
 ll.add("Ravi");    
 ll.add("Vijay");    
 ll.add("Ajay");    
 System.out.println("After invoking add(E e) method: "+ll);    
  
 ll.add(1, "Gaurav");    
 System.out.println("After invoking add(int index, E element) method: "+ll);    
  
 LinkedList<String> ll2=new LinkedList<String>();    
 ll2.add("Sonoo");    
 ll2.add("Hanumat");    
  
 ll.addAll(ll2);    
 System.out.println("After invoking addAll(Collection<? extends E> c) method: "+ll);    
  
 LinkedList<String> ll3=new LinkedList<String>();    
 ll3.add("John");    
 ll3.add("Rahul");    
  
 ll.addAll(1, ll3);    
 System.out.println("After invoking addAll(int index, Collection<? extends E> c) method: "+ll);    
  
 ll.addFirst("Lokesh");    
 System.out.println("After invoking addFirst(E e) method: "+ll);    
  
 ll.addLast("Harsh");    
 System.out.println("After invoking addLast(E e) method: "+ll);    
 }    
}  
```

**Output**
```
Initial list of elements: []
After invoking add(E e) method: [Ravi, Vijay, Ajay]
After invoking add(int index, E element) method: [Ravi, Gaurav, Vijay, Ajay]
After invoking addAll(Collection c) method:
[Ravi, Gaurav, Vijay, Ajay, Sonoo, Hanumat]
After invoking addAll(int index, Collection c) method:
[Ravi, John, Rahul, Gaurav, Vijay, Ajay, Sonoo, Hanumat]
After invoking addFirst(E e) method:
[Lokesh, Ravi, John, Rahul, Gaurav, Vijay, Ajay, Sonoo, Hanumat]
After invoking addLast(E e) method:
[Lokesh, Ravi, John, Rahul, Gaurav, Vijay, Ajay, Sonoo, Hanumat, Harsh]
```

## 5. Removing Elements from LinkedList

The LinkedList elements can be removed by using different methods like remove(Object o), remove(int index), removeAll(Collection c), removeFirst(), removeLast(), removeFirstOccurrence(Object o), removeLastOccurrence(Object o), and clear().
```
import java.util.*;    
public class Main {    
 public static void main(String [] args){    
  
 LinkedList<String> ll=new LinkedList<String>();    
 ll.add("Ravi");    
 ll.add("Vijay");    
 ll.add("Ajay");    
 ll.add("Anuj");    
 ll.add("Gaurav");    
 ll.add("Harsh");    
 ll.add("Virat");    
 ll.add("Gaurav");    
 ll.add("Harsh");    
 ll.add("Amit");    

  
 System.out.println("Initial list of elements: "+ll);    
  
 ll.remove("Vijay");    
 System.out.println("After invoking remove(object) method: "+ll);     
  
 ll.remove(0);    
 System.out.println("After invoking remove(index) method: "+ll);     
  
 LinkedList<String> ll2=new LinkedList<String>();    
 ll2.add("Ravi");    
 ll2.add("Hanumat");    
  
 ll.addAll(ll2);    
 System.out.println("Updated list : "+ll);     
  
 ll.removeAll(ll2);    
 System.out.println("After invoking removeAll() method: "+ll);     
  
 ll.removeFirst();    
 System.out.println("After invoking removeFirst() method: "+ll);    
  
 ll.removeLast();    
 System.out.println("After invoking removeLast() method: "+ll);    
  
 ll.removeFirstOccurrence("Gaurav");    
 System.out.println("After invoking removeFirstOccurrence() method: "+ll);    
  
 ll.removeLastOccurrence("Harsh");    
 System.out.println("After invoking removeLastOccurrence() method: "+ll);    
  
 ll.clear();    
 System.out.println("After invoking clear() method: "+ll);     
 }    
}  

```
**Output**
```
Initial list of elements: [Ravi, Vijay, Ajay, Anuj, Gaurav, Harsh, Virat, Gaurav, Harsh, Amit]
After invoking remove(object) method: [Ravi, Ajay, Anuj, Gaurav, Harsh, Virat, Gaurav, Harsh, Amit]
After invoking remove(index) method: [Ajay, Anuj, Gaurav, Harsh, Virat, Gaurav, Harsh, Amit]
Updated list : [Ajay, Anuj, Gaurav, Harsh, Virat, Gaurav, Harsh, Amit, Ravi, Hanumat]
After invoking removeAll() method: [Ajay, Anuj, Gaurav, Harsh, Virat, Gaurav, Harsh, Amit]
After invoking removeFirst() method: [Gaurav, Harsh, Virat, Gaurav, Harsh, Amit]
After invoking removeLast() method: [Gaurav, Harsh, Virat, Gaurav, Harsh]
After invoking removeFirstOccurrence() method: [Harsh, Virat, Gaurav, Harsh]
After invoking removeLastOccurrence() method: [Harsh, Virat, Gaurav]
After invoking clear() method: []
```

## 6. Reversing LinkedList Elements

You can traverse the LinkedList in reverse order using a descending iterator.
```
import java.util.*;    
public class Main{    
 public static void main(String args[]){    
  
 LinkedList<String> ll=new LinkedList<String>();    
 ll.add("Ravi");    
 ll.add("Vijay");    
 ll.add("Ajay");    
  
 Iterator i=ll.descendingIterator();    
 while(i.hasNext()){    
  System.out.println(i.next());    
 }    
 }    
}  

```
**Output**
```
Ajay
Vijay
Ravi

```


---

[← 116. ArrayList](116_ArrayList.md) | [Course Index](../README.md) | [118. Set →](118_Set.md)
