package com.neil.typedtuples;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static com.neil.typedtuples.DemoOrInterestingUsageTest.Titles.MR;

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

    // TODO concat

    System.out.println(joesRecord);
    System.out.println(joesBrother);
    System.out.println(brothersName);
    System.out.println(brothersDiet.reverse());
  }
}
