package utils;




import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

import model.calculation.Calculate;
import model.calculation.CalculateBuySell;

public class ErrorInstructionGenerator {
    public static Set<Calculate> getFakeInstructions() {
        return new HashSet<>(Arrays.asList(

            new Calculate(
                "FOO",
                CalculateBuySell.BUY,
                Currency.getInstance("SGD"),
                LocalDate.of(2016, 1, 01),
                LocalDate.of(2016, 1, 02),
                BigDecimal.valueOf(0.50),
                200,
                BigDecimal.valueOf(100.25)),
            
            new Calculate(
                    "CHERRY",
                    CalculateBuySell.BUY,
                    Currency.getInstance("SGD"),
                    LocalDate.of(2016, 1, 01),
                    LocalDate.of(2016, 1, 02),
                    BigDecimal.valueOf(0.55),
                    175,
                    BigDecimal.valueOf(80.25)),
            new Calculate(
                    "LISA",
                    CalculateBuySell.BUY,
                    Currency.getInstance("SGD"),
                    LocalDate.of(2016, 1, 01),
                    LocalDate.of(2016, 1, 02),
                    BigDecimal.valueOf(0.75),
                    75,
                    BigDecimal.valueOf(120.25)),

            new Calculate(
                "BAR",
                CalculateBuySell.SELL,
                Currency.getInstance("AED"),
                LocalDate.of(2016, 1, 05),
                LocalDate.of(2016, 1, 07),
                BigDecimal.valueOf(0.22),
                450,
                BigDecimal.valueOf(150.50)),
            
            new Calculate(
                    "MARK",
                    CalculateBuySell.SELL,
                    Currency.getInstance("AED"),
                    LocalDate.of(2016, 1, 05),
                    LocalDate.of(2016, 1, 07),
                    BigDecimal.valueOf(0.25),
                    350,
                    BigDecimal.valueOf(100.50)),
            
            new Calculate(
                    "BENNY",
                    CalculateBuySell.SELL,
                    Currency.getInstance("AED"),
                    LocalDate.of(2016, 1, 05),
                    LocalDate.of(2016, 1, 07),
                    BigDecimal.valueOf(0.22),
                    450,
                    BigDecimal.valueOf(120.50)),

            new Calculate(
                "RAM",
                CalculateBuySell.SELL,
                Currency.getInstance("SAR"),
                LocalDate.of(2016, 3, 10),
                LocalDate.of(2016, 3, 18),
                BigDecimal.valueOf(0.27),
                150,
                BigDecimal.valueOf(400.8)),

            new Calculate(
                "JOHN",
                CalculateBuySell.SELL,
                Currency.getInstance("EUR"),
                LocalDate.of(2016, 3, 10),
                LocalDate.of(2016, 3, 21),
                BigDecimal.valueOf(0.34),
                50,
                BigDecimal.valueOf(500.6)),

            new Calculate(
                    "DAVID",
                    CalculateBuySell.BUY,
                    Currency.getInstance("EUR"),
                    LocalDate.of(2016, 3, 10),
                    LocalDate.of(2016, 3, 21),
                    BigDecimal.valueOf(0.34),
                    20,
                    BigDecimal.valueOf(40.6)),

                new Calculate(
                    "TOM",
                    CalculateBuySell.BUY,
                    Currency.getInstance("EUR"),
                    LocalDate.of(2016, 3, 10),
                    LocalDate.of(2016, 3, 21),
                    BigDecimal.valueOf(0.34),
                    20,
                    BigDecimal.valueOf(40.6)),

                new Calculate(
                    "WILSON",
                    CalculateBuySell.SELL,
                    Currency.getInstance("EUR"),
                    LocalDate.of(2016, 3, 10),
                    LocalDate.of(2016, 3, 21),
                    BigDecimal.valueOf(0.34),
                    1000,
                    BigDecimal.valueOf(160.6)),

                new Calculate(
                    "MOSSES",
                    CalculateBuySell.SELL,
                    Currency.getInstance("EUR"),
                    LocalDate.of(2016, 3, 10),
                    LocalDate.of(2016, 3, 21),
                    BigDecimal.valueOf(0.34),
                    120,
                    BigDecimal.valueOf(500.6))
             
        ));
    }
}
