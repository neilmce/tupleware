# Tupleware Usage Examples Manual

## Creating tuples
For Tuples of size 0 to 10, you can construct a tuple using an `of` method as follows:
```java
Tuple2<String, Integer> productAndQuantity = Tuple2.of("Coffee", 1);
```
This code is perfectly readable for tuples with less than about 4 elements, but for larger tuples it can
become quite verbose and in such cases we recommend using Java's local variable type inference if possible.
There is no loss of type safety when using type inference.
```java
var productData = Tuple5.of("Apple pie", 1, 2.99, List.of("Cream", "Sugar"), 3.00);
```

`Tuple2` instances can also be created directly from a `java.util.Map.Entry<K, V>`:
```java
Map.Entry<String, Integer> mapEntry = // initialised somewhere else.
Tuple2<String, Integer> t2 = Tuple2.from(mapEntry);
```

## Accessing tuple elements
Tuple elements can be retrieved using an `elemN` method.

```java
var productData = Tuple5.of("Apple pie", 1, 2.99, List.of("Cream", "Sugar"), 3.00);

String       name     = productData.elem1();
int          quantity = productData.elem2();
double       price    = produceData.elem3();
List<String> toppings = productData.elem4();
```
> **Note:** elements are indexed starting at 1, not 0.

> **Note:** there is no method like `elem(int index)`, as that would mean the loss of
  type safety. How would the compiler know what type the result of `elem(3)` had?

## Changing an element value
Tupleware tuples are immutable and so you cannot give a particular element a new value. Neither can you add or remove
elements from a tuple. However, both of these changes can be achieved by creating new tuple instances from old ones:

Creating a new tuple with new, fixed values for particular elements:
```java
var fixedPriceReduction = Tuple3.of("Ice cream cone", 1, 1.99)
                                .withElem2(2);
                                .withElem3(1.50);
// == Tuple3.of("Ice cream cone", 2, 1.50)
```
or a new tuple whose element values are based on the previous values:
```java
var percentageReduction = Tuple3.of("Ice cream cone", 1, 1.99)
                                .mapElem3(price -> price * 0.75);
// == Tuple3.of("Ice cream cone", 1, 1.4925)
// You don't use floating point types for currency, right?
```

## Reversing a tuple
You can reverse any tuple like so:
```java
Tuple3<Double, Integer, String> reversed = Tuple3.of("Ice cream cone", 1, 1.99).reverse();
// == Tuple3.of(1.99, 1, "Ice cream cone")
```
Pairs also have a `swap` method which does the same thing:
```java
Tuple2<Integer, String> swapped = Tuple2.of("Ice cream cone", 1).swap();
// == Tuple2.of(1, "Ice cream cone")
```

## Adding new elements to a tuple

You can add new elements to the beginning or end of a tuple like so
```java
Tuple2<String, Integer> nameAndNumber = Tuple1.of("Coffee").append(3);
// == Tuple2.of("Coffee", 3)

Tuple3<Boolean, String, Integer> paidNameAndNumber = nameAndNumber.prepend(true);
// == Tuple3.of(true, "Coffee", 3)
```

## Splitting tuples
Tuples can be split into two smaller tuples like so (we leave the full types in here to demonstrate):
```java
var personalInfo = Tuple4.of("Mary", "Beery", List.of("Cakes", "Pastries"), Set.of("Coffee", "Tea"));

Tuple2<Tuple2<String, String>, Tuple2<List<String>, Set<String>>> namesAndFavourites = personalInfo.splitAfterElement2();
// == Tuple2.of(
//      Tuple2.of("Mary", "Beery"),
//      Tuple2.of(List.of("Cakes", "Pastries"), Set.of("Coffee", "Tea"))
//    )

Tuple2<String, String> names = nameAndFavourites.elem1();
// == Tuple2.of("Mary", "Beery")
    
Tuple2<List<String, Set<String>>> favourites = nameAndFavourites.elem2();
// == Tuple2.of(List.of("Cakes", "Pastries"), Set.of("Coffee", "Tea"))

String firstName = names.elem1();
// == "Mary"
Set<String> favouriteDrinks = favourites.elem2();
// == Set.of("Coffee", "Tea")
```

## Concatenating tuples
Tuples can also be concatenated, although it isn't possible to have a tuple with more than 10 elements.
> **Note:** any attempt to exceed the element limit _will not compile_.
```java
var names      = Tuple2.of("Mary", "Beery");
var favourites = Tuple2.of(List.of("Cakes", "Pastries"), Set.of("Coffee", "Tea"));

var combined = names.concat(favourites);
// == Tuple4.of("Mary", "Beery", List.of("Cakes", "Pastries"), Set.of("Coffee", "Tea"))
```

## Reducing the 'shape' of a tuple
TODO

## Zipping and unzipping tuples
TODO
