package trade_logic;

import model.calculation.Calculate;
import trade_logic.workingdays.ArabiaWorkingDays;
import trade_logic.workingdays.FirstWorkingDay;
import trade_logic.workingdays.UsualWorkingDays;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Set;

public class CalculateSettlementDate {

	public static void calculateSettlementDates(Set<Calculate> calculations) {
		calculations.forEach(CalculateSettlementDate::calculateSettlementDate);
	}

	public static void calculateSettlementDate(Calculate calculation) {
		// Select proper strategy based on the Currency
		final FirstWorkingDay workingDaysMechanism = getWorkingDaysStrategy(calculation.getCurrency());

		// find the correct settlement date
		final LocalDate newSettlementDate = workingDaysMechanism.findFirstWorkingDate(calculation.getSettlementDate());

		if (newSettlementDate != null) {
			// set the correct settlement date
			calculation.setSettlementDate(newSettlementDate);
		}
	}

	private static FirstWorkingDay getWorkingDaysStrategy(Currency currency) {
		if ((currency.getCurrencyCode().equals("AED")) || (currency.getCurrencyCode().equals("SAR"))) {
			return ArabiaWorkingDays.getInstance();
		}
		return UsualWorkingDays.getInstance();
	}
}
