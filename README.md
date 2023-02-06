Typed Tuples
===
This project provides a lightweight, strongly-typed tuples library for Java.
Some of the benefits of tuples include:
* effectively returning multiple things from a method call
* TODO

```java
public Tuple2<String, Integer> getNameAndAge() {
  return Tuple2.of("Joe", 42);
}
```
It makes it easy to add new elements to tuples:
```java
Tuple3<String, Integer, String> nameAgeAndEmployer = getNameAndAge().append("MegaCorp");
```
and makes it easy to get updated copies of tuples:
```java
var upperCaseEmployer = nameAgeAndEmployer.mapElem3(String::toUpperCase);

var renamed = nameAgeAndEmployer.withElem1("Anne");
```

Of course, the bigger your tuples, the more 'noisy' your Java code can become. So we recommend using vars & type
inference - and you can still be sure that the compiler is checking the types of all your elements.
```java
var bigTuple = Tuple5.of("Will", TUESDAY, JANUARY, WRITE, "/home/will/file.txt");
var biggerTuple = bigTuple.prepend("Project X").append(true);
Month m = biggerTuple.elem4();
```

There are many more features, see below.

TODO How does it play with Java 17 pattern matching? Should I move to Java 17 & have my tuples be records?

Requirements & limitations.
--
* The library requires Java 17 or higher.
* The library supports Tuples with up to 10 elements. 10 is an arbitrary limit. It could be increased in a future version
  of this library if there is a compelling reason to do so.

Design goals
--
* Immutability. The tuples provided by this library are immutable. (Of course, the library cannot guarantee that
  what you keep in your tuples is immutable. That's up to you.)
* Type safety. We want to support users who believe in strongly typed designs.
  (There is only one method which does not preserve type safety: `Tuple.toList()` which returns a `List<Object>`.)
* No external dependencies. We want the library to be as small as possible and easy to use.

// TODO Thought: Change 'of' method to 'tuple'. Or have both. Then a static import could allow for nice code: tuple(2. "Hi")
//               But could you static import Tuple2 and Tuple3? No, you couldn't. So methods would have to be tuple2(), tuple3() etc.


Creating Tuples
--
Tuples are constructed with the `of` methods:
```java
Tuple2.of("Hello", 42); // A pair.
Tuple4.of("Hello", 2, 3.1, true)
Tuple0.of(); // An empty tuple.
```

Instances of `Tuple2` can also be created from Java `Map` entries like so:
```java
Map.Entry<String, Integer> mapEntry = // initialised somewhere.
Tuple2<String, Integer> t2 = Tuple2.from(mapEntry);
```

You can turn a Map into a List of Tuples like so:
```java
var map = Map.of("Hello", 42, "Goodbye", 101, "Java", 1995);
List<Tuple2<String, Integer>> entryTuples = map.entrySet()
                                               .stream()
                                               .map(Tuple2::from)
                                               .collect(toList());
```

Using Tuples
--
Component fields are retrieved like so:
```java
var nameAndAge = Tuple2.of("Fred", 42);
String name = nameAndAge.elem1(); // Note the 1-based index.
int age = nameAndAge.elem2();
```

Pairs can be swapped:
```java
assertEquals(Tuple2.of(42, "Fred"), nameAndAge.swap());
```

and tuples of any 'arity' can be reversed:
Pairs can be swapped:
```java
var reversed = Tuple4.of(42, "Fred", true, Instant.now()).reverse();
int age = reversed.elem4();
```
