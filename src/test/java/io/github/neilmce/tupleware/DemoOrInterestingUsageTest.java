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

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static io.github.neilmce.tupleware.DemoOrInterestingUsageTest.Titles.MR;

class DemoOrInterestingUsageTest {
  enum Titles {
    MR, MRS, DR, PROF
  }
  @Test void funDemo() {
    var joesRecord = Tuple6.of(MR, "Joe", 'A', "Aardvark", LocalDate.of(1970, 1, 1), List.of("Apples", "Ice cream", "Chocolate"));

    Month birthMonth = joesRecord.elem5().getMonth();
    String favouriteFood = joesRecord.elem6().get(0);

    var joesBrother = joesRecord.withElem1(Titles.PROF)
                                .withElem2("Charles")
                                .withElem3('X')
                                .mapElem5(date -> date.minusYears(2))
                                .withElem6(List.of("Crisps"))
                                .append(List.of("Guinness", "Milk"));

    var brothersName = joesBrother.take4().dropElem1();
    var brothersDiet = joesBrother.drop5();

    System.out.println(joesRecord);
    System.out.println(joesBrother);
    System.out.println(brothersName);
    System.out.println(brothersDiet.reverse());
    System.out.println(joesRecord.drop1()
                                 .take3()
                                 .concat(brothersName));
  }
}
