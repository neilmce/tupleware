Tupleware
===
Tupleware is a very small library which provides simple tuples for Java 11 and above.
The tuples are strongly-typed containers that hold a fixed sequence of elements e.g. a simple triple type might
hold a product, a quantity and its price, like so:
```java
Tuple3.of("Ice cream cone", 1, 1.99)
```
The type of this expression is `Tuple3<String, Integer, Double>` so the compiler knows the type of each
element in the tuple.

Tuples vs Java records
--
Java has record types now (since version X), which are quite similar to tuples. When should we use tuples and when
should we use records?

Well first of all, records are 'named tuples':
```java
record Product(String name, int quantity, double price) {}
```
When we are dealing with types where the element names are known and fixed, Java records are better. For example,
small data classes representing domain objects are probably best modelled as records.

But when you are taking small (10 elements or fewer) groups of data objects where the types are fixed, but the names
are not, then it may be better to use tuples. TODO example.
Also, as we'll see below, Tupleware offer various helper methods that let us transform them in a chain.

Requirements & limitations.
--
* The library requires Java 11 or higher.
* The library supports Tuples with 0 to 10 elements. 10 is an arbitrary limit which could be increased in the future.

Design goals
--
* Immutability. The tuples provided by this library are immutable. (Of course, the library cannot guarantee that
  what you keep in your tuples is immutable. That's up to you.)
* Type safety. We want to support users who believe in strongly typed designs.
  (There is only one method which does not preserve type safety: `Tuple.toList()` which returns a `List<Object>`.)
* No external dependencies. We want the library to be as small as possible and easy to use.

Creating tuples
--
You can construct a tuple with an explicit type using an `of` method as follows:
```java
Tuple2<String, Integer> productAndQuantity = Tuple2.of("Coffee", 1);
```
and this works up to the size limit of `Tuple10`. However, specifying the types of all ten elements is very verbose and in
such cases we recommend using local variable type inference if possible:
```java
var productData = Tuple5.of("Apple pie", 1, 2.99, List.of("Cream", "Sugar"), 3.00);
```

`Tuple2` instances are a special case which also support creation direct from a `java.util.Map.Entry<K, V>`:
```java
Map.Entry<String, Integer> mapEntry = // initialised somewhere.
Tuple2<String, Integer> t2 = Tuple2.from(mapEntry);
```

Accessing elements
--
Tuple elements can be retrieved using an `elemN` method, where N is a 1-based index.
```java
var productData = Tuple5.of("Apple pie", 1, 2.99, List.of("Cream", "Sugar"), 3.00);

String name = productData.elem1();
int quantity = productData.elem2();
List<String> toppings = productData.elem4();
```
There is no method like `elem(int index)` which takes the index as a runtime value, as that would mean the loss of
type safety.

Data processing
==
Tupleware tuples are immutable and so you cannot set a particular element to a new value. Neither can you add or remove
elements from a tuple. Both of these changes can be achieved by creating new tuple instances based on the old:

Element level changes
--
Creating a new tuple with new, fixed values for particular elements:
```java
var fixedPriceReduction = Tuple3.of("Ice cream cone", 1, 1.99)
                                .withElem3(1.50);
```
or a new tuple whose element values are based on the previous values:
```java
var percentageReduction = Tuple3.of("Ice cream cone", 1, 1.99)
                                .mapElem3(price -> price * 0.75);
```
TODO What about new tuples where elemX depends on the old values of multiple elems?

Structural changes
--
You can reverse any tuple like so:
```java
Tuple3<Double, Integer, String> reversed = Tuple3.of("Ice cream cone", 1, 1.99).reverse();
```
Pairs have a `swap` method as well as `reverse` (they are equivalent):
```java
Tuple<Integer, String> swapped = Tuple2.of("Ice cream cone", 1).swap();
```

You can add new elements to a tuple like so
```java
Tuple2<String, Integer> nameAndNumber = Tuple1.of("Coffee").append(3);

Tuple3<Boolean, String, Integer> paidNameAndNumber = nameAndNumber.prepend(true);
```

TODO split

TODO concat

TODO take

TODO drop

TODO zip unzip
