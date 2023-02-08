Tupleware
===
Tupleware is a small library which provides simple tuples for Java 11 and above.
Tuples are containers that hold a fixed sequence of strongly-typed elements e.g. a simple 'triple' type might
hold a commercial product, a quantity and a price, like so:
```java
var cone = Tuple3.of("Ice cream cone", 1, 1.99);
```
The type of this expression is `Tuple3<String, Integer, Double>`.

Quick introduction
===
Some of the things that Tupleware allows you to do include:
* access tuple elements without losing type safety:
```java
String name  = cone.elem1();
int quantity = cone.elem2();
double price = cone.elem3();
```
* alter tuple elements (although see note below on immutability):
```java
var coneWithReducedPrice = cone.withElem3(1.50);
```
* Change the 'shape' of tuples:
```java
var coneWithToppings = cone.append(List.of("Toffee Sauce", "Wafer"));
```
and more.

Design goals
===
* Immutability. The tuples provided by this library are immutable. (Of course, the library cannot guarantee that
  what you keep in your tuples is immutable. That's up to you.)
* Type safety. We want to support users who believe in strongly typed designs.
* No external dependencies. We want the library to be as small as possible and easy to integrate.

Requirements & limitations.
===
* The library requires Java 11 or higher.
* The library supports Tuples with 0 to 10 elements. (10 is an arbitrary limit which could be increased in the future.)

Creating tuples
==
You can construct a tuple with an explicit type using an `of` method as follows:
```java
Tuple2<String, Integer> productAndQuantity = Tuple2.of("Coffee", 1);
```
and this works up to the size limit of `Tuple10`. However, specifying the types of all ten elements is very verbose and
in such cases we recommend using Java's local variable type inference if possible:
```java
var productData = Tuple5.of("Apple pie", 1, 2.99, List.of("Cream", "Sugar"), 3.00);
```

`Tuple2` instances are a special case which also support creation direct from a `java.util.Map.Entry<K, V>`:
```java
Map.Entry<String, Integer> mapEntry = // initialised somewhere.
Tuple2<String, Integer> t2 = Tuple2.from(mapEntry);
```

Accessing elements
==
Tuple elements can be retrieved using an `elemN` method, where N is a 1-based index.
```java
var productData = Tuple5.of("Apple pie", 1, 2.99, List.of("Cream", "Sugar"), 3.00);

String name = productData.elem1();
int quantity = productData.elem2();
List<String> toppings = productData.elem4();
```
There is no method like `elem(int index)` which takes the index as a runtime value, as that would mean the loss of
type safety. How would the compiler know what type the result of `elem(3)` had?

Simple data processing
==
Tupleware tuples are immutable and so you cannot give a particular element a new value. Neither can you add or remove
elements from a tuple. However, both of these changes can be achieved by creating new tuple instances based on the old:

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

Splitting tuples
--
Tuples can be split into two smaller tuples like so (we leave the full types in here to demonstrate):
```java
var personalInfo = Tuple4.of("Mary", "Beery", List.of("Cakes", "Pastries"), Set.of("Coffee", "Tea"));

Tuple2<Tuple2<String, String>, Tuple2<List<String>, Set<String>>> namesAndFavourites = personalInfo.splitAfterElement2();

Tuple2<String, String>            names      = nameAndFavourites.elem1();
Tuple2<List<String, Set<String>>> favourites = nameAndFavourites.elem2();

String      firstName       = names.elem1();
Set<String> favouriteDrinks = favourites.elem2();
```

Concatenating tuples
--
Tuples can also be concatenated, although it isn't possible to have a tuple with more than 10 elements. Note that any
attempt to exceed the element limit will not compile.
```java
var names      = Tuple2.of("Mary", "Beery");
var favourites = Tuple2.of(List.of("Cakes", "Pastries"), Set.of("Coffee", "Tea"));

var combined = names.concat(favourites); // This is a Tuple4.
```

Reducing the 'shape' of a tuple
--

Zipping and unzipping tuples
--


Tuples vs Java records
===
Java has had record types since Java 16, which are quite similar to tuples. When should we use tuples and when
should we use records?
```java
record Product(String name, int quantity, double price) {}
```

Records are 'named tuples', which means that each tuple element is a named field. So records are real Java classes
in the software design sense. They represent something fixed in our application type system.
Tuples on the other hand are not named like this. Their elements _are_ strongly typed (like records' fields), but
the elements are referred to by their index. So tuples are a container you use to pass some data around but instances
are not likely to be long-lived in your application.

When we are dealing with types where the element names are known and fixed, Java records are better. For example,
small data classes representing domain objects are probably best modelled as records.

But when you have a small (10 elements or fewer) group of data objects that you want to pass from one method to
another, a tuple may be just what you need.

They're also useful when you need to do some very simple data transformations, as we'll see below.
