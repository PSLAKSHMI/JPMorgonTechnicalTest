package trade_logic;





import model.calculation.Calculate;
import model.calculation.CalculateBuySell;


import trade_logic.ranking.Grade;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;

/**
 * Describes a mapping between dates and the trade amount of those dates, based on instructions
 */
public class CalculateSettlementStatus {

    // Create a predicate for outgoing
    private final static Predicate<Calculate> outgoingInstructionsPredicate =
            instruction -> instruction.getAction().equals(CalculateBuySell.BUY);

    // Create a predicate for incoming
    private final static Predicate<Calculate> incomingInstructionsPredicate =
            instruction -> instruction.getAction().equals(CalculateBuySell.SELL);

    /**
     * Calculates the daily outgoing (BUY) trade amount in USD
     * @param instructions the instruction to calculate the stats from
     * @return a map from date to total amount
     */
    public static Map<LocalDate, BigDecimal> calculateDailyOutgoingAmount(Set<Calculate> instructions) {
        return calculateDailyAmount(instructions, outgoingInstructionsPredicate);
    }

    /**
     * Calculates the daily incoming (SELL) trade amount in USD
     * @param instructions the instruction to calculate the stats from
     * @return a map from date to total amount
     */
    public static Map<LocalDate, BigDecimal> calculateDailyIncomingAmount(Set<Calculate> instructions) {
        return calculateDailyAmount(instructions, incomingInstructionsPredicate);
    }

    /**
     * Ranks the daily outgoing (BUY) by trade amount in USD
     * @param instructions the instruction to calculate the stats from
     * @return a map from date to a list of ranks (ranking)
     */
    public static Map<LocalDate, LinkedList<Grade>> calculateDailyOutgoingRanking(Set<Calculate> instructions) {
        return calculateRanking(instructions, outgoingInstructionsPredicate);
    }

    /**
     * Ranks the daily incoming (SELL) by trade amount in USD
     * @param instructions the instruction to calculate the stats from
     * @return a map from date to a list of ranks (ranking)
     */
    public static Map<LocalDate, LinkedList<Grade>> calculateDailyIncomingRanking(Set<Calculate> instructions) {
        return calculateRanking(instructions, incomingInstructionsPredicate);
    }

    private static Map<LocalDate, BigDecimal> calculateDailyAmount(
            Set<Calculate> instructions, Predicate<Calculate> predicate)
    {
        return instructions.stream()
                .filter(predicate)
                .collect(groupingBy(Calculate::getSettlementDate,
                    mapping(Calculate::getTradeAmount,
                        reducing(BigDecimal.ZERO, BigDecimal::add))));
    }

    private static Map<LocalDate, LinkedList<Grade>> calculateRanking(
            Set<Calculate> instructions, Predicate<Calculate> predicate)
    {
        final Map<LocalDate, LinkedList<Grade>> ranking = new HashMap<>();

        instructions.stream()
            .filter(predicate)
            .collect(groupingBy(Calculate::getSettlementDate, toSet()))
            .forEach((date, dailyInstructionSet) -> {
                final AtomicInteger rank = new AtomicInteger(1);

                final LinkedList<Grade> ranks = dailyInstructionSet.stream()
                    .sorted((a, b) -> b.getTradeAmount().compareTo(a.getTradeAmount()))
                    .map(instruction -> new Grade(rank.getAndIncrement(), instruction.getEntity(), date))
                    .collect(toCollection(LinkedList::new));

                ranking.put(date, ranks);
            });

        return ranking;
    }
}
