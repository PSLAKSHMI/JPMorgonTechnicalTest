package trade_logic;




import model.calculation.Calculate;
import trade_logic.ranking.Grade;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class OutputGenerator {
    private StringBuilder stringBuilder = new StringBuilder();

    public String generateInstructionsReport(Set<Calculate> instructions) {
        // first calculate the correct settlement dates
    	CalculateSettlementDate.calculateSettlementDates(instructions);

        // Build the report string
        return generateDailyOutgoingRanking(instructions,
                generateDailyIncomingRanking(instructions,
                generateDailyIncomingAmount(instructions,
                generateDailyOutgoingAmount(instructions, stringBuilder))))
            .toString();
    }

    private static StringBuilder generateDailyOutgoingAmount(Set<Calculate> instructions, StringBuilder stringBuilder) {
        final Map<LocalDate, BigDecimal> dailyOutgoingAmount =
        		CalculateSettlementStatus.calculateDailyOutgoingAmount(instructions);

        stringBuilder
                .append("\n----------------------------------------\n")
                .append("         Outgoing Daily Amount          \n")
                .append("----------------------------------------\n")
                .append("      Date       |    Trade Amount      \n")
                .append("----------------------------------------\n");

        for (LocalDate date : dailyOutgoingAmount.keySet()) {
            stringBuilder
                .append(date).append("       |      ").append(dailyOutgoingAmount.get(date)).append("\n");
        }

        return stringBuilder;
    }

    private static StringBuilder generateDailyIncomingAmount(Set<Calculate> instructions, StringBuilder stringBuilder) {
        final Map<LocalDate, BigDecimal> dailyOutgoingAmount =
        		CalculateSettlementStatus.calculateDailyIncomingAmount(instructions);

        stringBuilder
                .append("\n----------------------------------------\n")
                .append("         Incoming Daily Amount          \n")
                .append("----------------------------------------\n")
                .append("      Date       |    Trade Amount      \n")
               
                .append("----------------------------------------\n");

        for (LocalDate date : dailyOutgoingAmount.keySet()) {
            stringBuilder
                    .append(date).append("       |      ").append(dailyOutgoingAmount.get(date)).append("\n");
            
        }

        return stringBuilder;
    }

    private static StringBuilder generateDailyOutgoingRanking(Set<Calculate> instructions, StringBuilder stringBuilder) {
        final Map<LocalDate, LinkedList<Grade>> dailyOutgoingRanking =
        		CalculateSettlementStatus.calculateDailyOutgoingRanking(instructions);

        stringBuilder
                .append("\n----------------------------------------\n")
                .append("         Outgoing Daily Ranking          \n")
                .append("----------------------------------------\n")
                .append("     Date    |     Rank   |   Entity     \n")
                .append("----------------------------------------\n");

        for (LocalDate date : dailyOutgoingRanking.keySet()) {
            for (Grade rank : dailyOutgoingRanking.get(date)) {
                stringBuilder
                    .append(date).append("   |      ")
                    .append(rank.getRank()).append("     |    ")
                    .append(rank.getEntity()).append("\n")
                .append("----------------------------------------\n");
            }
        }

        return stringBuilder;
    }

    private static StringBuilder generateDailyIncomingRanking(Set<Calculate> instructions, StringBuilder stringBuilder) {
        final Map<LocalDate, LinkedList<Grade>> dailyIncomingRanking =
        		CalculateSettlementStatus.calculateDailyIncomingRanking(instructions);

        stringBuilder
                .append("\n----------------------------------------\n")
                .append("         Incoming Daily Ranking          \n")
                .append("----------------------------------------\n")
                .append("     Date    |     Rank   |   Entity     \n")
                .append("----------------------------------------\n");

        for (LocalDate date : dailyIncomingRanking.keySet()) {
            for (Grade rank : dailyIncomingRanking.get(date)) {
                stringBuilder
                        .append(date).append("   |      ")
                        .append(rank.getRank()).append("     |    ")
                        .append(rank.getEntity()).append("\n")
                .append("----------------------------------------\n");
            }
        }

        return stringBuilder;
    }
}
