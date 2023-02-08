// Copyright 2023 Neil Mc Erlean.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package io.github.neilmce.tupleware;

import org.junit.jupiter.api.Test;

import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static io.github.neilmce.tupleware.TestData.*;
import static java.time.Month.APRIL;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ElementChangeTest {

  @Test
  void mappingElementsShouldWork() {
    assertEquals(2, tuple1.mapElem1(String::length).elem1());

    assertEquals(2, tuple2.mapElem1(String::length).elem1());
    assertEquals(3, tuple2.mapElem2(i -> i + 1).elem2());

    assertEquals(2, tuple3.mapElem1(String::length).elem1());
    assertEquals(3, tuple3.mapElem2(i -> i + 1).elem2());
    assertEquals(6.2, tuple3.mapElem3(d -> d * 2).elem3());

    assertEquals(2, tuple4.mapElem1(String::length).elem1());
    assertEquals(3, tuple4.mapElem2(i -> i + 1).elem2());
    assertEquals(6.2, tuple4.mapElem3(d -> d * 2).elem3());
    assertEquals("true", tuple4.mapElem4(Object::toString).elem4());

    assertEquals(2, tuple5.mapElem1(String::length).elem1());
    assertEquals(3, tuple5.mapElem2(i -> i + 1).elem2());
    assertEquals(6.2, tuple5.mapElem3(d -> d * 2).elem3());
    assertEquals("true", tuple5.mapElem4(Object::toString).elem4());
    assertEquals("JANUARY", tuple5.mapElem5(Enum::toString).elem5());

    assertEquals(2, tuple6.mapElem1(String::length).elem1());
    assertEquals(3, tuple6.mapElem2(i -> i + 1).elem2());
    assertEquals(6.2, tuple6.mapElem3(d -> d * 2).elem3());
    assertEquals("true", tuple6.mapElem4(Object::toString).elem4());
    assertEquals("JANUARY", tuple6.mapElem5(Enum::toString).elem5());
    assertEquals("Z", tuple6.mapElem6(ZoneOffset::getId).elem6());

    assertEquals(2, tuple7.mapElem1(String::length).elem1());
    assertEquals(3, tuple7.mapElem2(i -> i + 1).elem2());
    assertEquals(6.2, tuple7.mapElem3(d -> d * 2).elem3());
    assertEquals("true", tuple7.mapElem4(Object::toString).elem4());
    assertEquals("JANUARY", tuple7.mapElem5(Enum::toString).elem5());
    assertEquals("Z", tuple7.mapElem6(ZoneOffset::getId).elem6());
    assertEquals('X', tuple7.mapElem7(Character::toUpperCase).elem7());

    assertEquals(2, tuple8.mapElem1(String::length).elem1());
    assertEquals(3, tuple8.mapElem2(i -> i + 1).elem2());
    assertEquals(6.2, tuple8.mapElem3(d -> d * 2).elem3());
    assertEquals("true", tuple8.mapElem4(Object::toString).elem4());
    assertEquals("JANUARY", tuple8.mapElem5(Enum::toString).elem5());
    assertEquals("Z", tuple8.mapElem6(ZoneOffset::getId).elem6());
    assertEquals('X', tuple8.mapElem7(Character::toUpperCase).elem7());
    assertEquals("[1]", tuple8.mapElem8(Object::toString).elem8());

    assertEquals(2, tuple9.mapElem1(String::length).elem1());
    assertEquals(3, tuple9.mapElem2(i -> i + 1).elem2());
    assertEquals(6.2, tuple9.mapElem3(d -> d * 2).elem3());
    assertEquals("true", tuple9.mapElem4(Object::toString).elem4());
    assertEquals("JANUARY", tuple9.mapElem5(Enum::toString).elem5());
    assertEquals("Z", tuple9.mapElem6(ZoneOffset::getId).elem6());
    assertEquals('X', tuple9.mapElem7(Character::toUpperCase).elem7());
    assertEquals("[1]", tuple9.mapElem8(Object::toString).elem8());
    assertEquals(1, tuple9.mapElem9(Set::size).elem9());

    assertEquals(2, tuple10.mapElem1(String::length).elem1());
    assertEquals(3, tuple10.mapElem2(i -> i + 1).elem2());
    assertEquals(6.2, tuple10.mapElem3(d -> d * 2).elem3());
    assertEquals("true", tuple10.mapElem4(Object::toString).elem4());
    assertEquals("JANUARY", tuple10.mapElem5(Enum::toString).elem5());
    assertEquals("Z", tuple10.mapElem6(ZoneOffset::getId).elem6());
    assertEquals('X', tuple10.mapElem7(Character::toUpperCase).elem7());
    assertEquals("[1]", tuple10.mapElem8(Object::toString).elem8());
    assertEquals(1, tuple10.mapElem9(Set::size).elem9());
    assertEquals(2, tuple10.mapElem10(map -> map.get("One") + 1).elem10());
  }

  @Test void foo() {
    var data = Tuple4.of("Joe", "Jo", 20, 33);

    var expected = Tuple6.of("Joe", "Jo", 20, 33, "JoeJo", 53);

    assertEquals(expected, data.append(data.elem1() + data.elem2())
                               .append(data.elem3() + data.elem4()));
  }

  @Test void bar() {
    var data = Tuple4.of("Joe", "Jo", 20, 33);

    var expected = Tuple4.of("Joe", "Jo", 20, 53);

    assertEquals(expected, data.withElem4(data.elem3() + data.elem4()));
  }

  @Test void baz() {
    var data = List.of(
        Tuple3.of("Foo", 11, 42.0),
        Tuple3.of("Bar", 12, 101.0),
        Tuple3.of("Baz", 13, -6.0),
        Tuple3.of("Qux", 14, 0.0)
    );

    var aggs = Tuple3.unzip(data)
                     .mapElem2(ints -> Collections.min(ints))
                     .mapElem3(doubs -> Collections.max(doubs));

    assertEquals(Tuple3.of(List.of("Foo", "Bar", "Baz", "Qux"), 11, 101.0), aggs);
  }

  @Test
  void replacementShouldWork() {
    assertEquals(APRIL, tuple1.withElem1(APRIL).elem1());

    assertEquals(APRIL, tuple2.withElem1(APRIL).elem1());
    assertEquals(APRIL, tuple2.withElem2(APRIL).elem2());

    assertEquals(APRIL, tuple3.withElem1(APRIL).elem1());
    assertEquals(APRIL, tuple3.withElem2(APRIL).elem2());
    assertEquals(APRIL, tuple3.withElem3(APRIL).elem3());

    assertEquals(APRIL, tuple4.withElem1(APRIL).elem1());
    assertEquals(APRIL, tuple4.withElem2(APRIL).elem2());
    assertEquals(APRIL, tuple4.withElem3(APRIL).elem3());
    assertEquals(APRIL, tuple4.withElem4(APRIL).elem4());

    assertEquals(APRIL, tuple5.withElem1(APRIL).elem1());
    assertEquals(APRIL, tuple5.withElem2(APRIL).elem2());
    assertEquals(APRIL, tuple5.withElem3(APRIL).elem3());
    assertEquals(APRIL, tuple5.withElem4(APRIL).elem4());
    assertEquals(APRIL, tuple5.withElem5(APRIL).elem5());

    assertEquals(APRIL, tuple6.withElem1(APRIL).elem1());
    assertEquals(APRIL, tuple6.withElem2(APRIL).elem2());
    assertEquals(APRIL, tuple6.withElem3(APRIL).elem3());
    assertEquals(APRIL, tuple6.withElem4(APRIL).elem4());
    assertEquals(APRIL, tuple6.withElem5(APRIL).elem5());
    assertEquals(APRIL, tuple6.withElem6(APRIL).elem6());

    assertEquals(APRIL, tuple7.withElem1(APRIL).elem1());
    assertEquals(APRIL, tuple7.withElem2(APRIL).elem2());
    assertEquals(APRIL, tuple7.withElem3(APRIL).elem3());
    assertEquals(APRIL, tuple7.withElem4(APRIL).elem4());
    assertEquals(APRIL, tuple7.withElem5(APRIL).elem5());
    assertEquals(APRIL, tuple7.withElem6(APRIL).elem6());
    assertEquals(APRIL, tuple7.withElem7(APRIL).elem7());

    assertEquals(APRIL, tuple8.withElem1(APRIL).elem1());
    assertEquals(APRIL, tuple8.withElem2(APRIL).elem2());
    assertEquals(APRIL, tuple8.withElem3(APRIL).elem3());
    assertEquals(APRIL, tuple8.withElem4(APRIL).elem4());
    assertEquals(APRIL, tuple8.withElem5(APRIL).elem5());
    assertEquals(APRIL, tuple8.withElem6(APRIL).elem6());
    assertEquals(APRIL, tuple8.withElem7(APRIL).elem7());
    assertEquals(APRIL, tuple8.withElem8(APRIL).elem8());

    assertEquals(APRIL, tuple9.withElem1(APRIL).elem1());
    assertEquals(APRIL, tuple9.withElem2(APRIL).elem2());
    assertEquals(APRIL, tuple9.withElem3(APRIL).elem3());
    assertEquals(APRIL, tuple9.withElem4(APRIL).elem4());
    assertEquals(APRIL, tuple9.withElem5(APRIL).elem5());
    assertEquals(APRIL, tuple9.withElem6(APRIL).elem6());
    assertEquals(APRIL, tuple9.withElem7(APRIL).elem7());
    assertEquals(APRIL, tuple9.withElem8(APRIL).elem8());
    assertEquals(APRIL, tuple9.withElem9(APRIL).elem9());

    assertEquals(APRIL, tuple10.withElem1(APRIL).elem1());
    assertEquals(APRIL, tuple10.withElem2(APRIL).elem2());
    assertEquals(APRIL, tuple10.withElem3(APRIL).elem3());
    assertEquals(APRIL, tuple10.withElem4(APRIL).elem4());
    assertEquals(APRIL, tuple10.withElem5(APRIL).elem5());
    assertEquals(APRIL, tuple10.withElem6(APRIL).elem6());
    assertEquals(APRIL, tuple10.withElem7(APRIL).elem7());
    assertEquals(APRIL, tuple10.withElem8(APRIL).elem8());
    assertEquals(APRIL, tuple10.withElem9(APRIL).elem9());
    assertEquals(APRIL, tuple10.withElem10(APRIL).elem10());
  }
}
