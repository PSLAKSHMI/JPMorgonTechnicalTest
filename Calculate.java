package model.calculation;



import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import model.calculation.CalculationDetails;
import model.calculation.CalculateBuySell;

public class Calculate {

    // A financial entity whose shares are to be bought or sold
    private final String entity;

    // What action should the Instruction represents (Buy or Sell)
    private final CalculateBuySell action;

    // LocalDate on which the instruction was sent by various clients
    private final LocalDate instructionDate;

    // The Date on which the client wished for the instruction to be settled with respect to Instruction LocalDate
    // The only non-final since it can be recalculated from business logic.
    private LocalDate settlementDate;

    private final CalculationDetails details;

    public Calculate(
            String entity,
            CalculateBuySell action,
            Currency currency,
            LocalDate instructionDate,
            LocalDate settlementDate,
            BigDecimal agreedFx,
            int units,
            BigDecimal pricePerUnit)
    {
        this(
            entity, action,
                instructionDate, settlementDate,
            new CalculationDetails(currency, agreedFx, units, pricePerUnit));
    }

    public Calculate(
            String entity,
            CalculateBuySell action,
            LocalDate instructionDate,
            LocalDate settlementDate,
            CalculationDetails details)
    {
        this.entity = entity;
        this.action = action;
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
        this.details = details;
    }

    public String getEntity() {
        return entity;
    }

    public CalculateBuySell getAction() {
        return action;
    }

    public LocalDate getInstructionDate() {
        return instructionDate;
    }

    public void setSettlementDate(LocalDate newDate) {
        settlementDate = newDate;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public CalculationDetails getDetails() {
        return details;
    }

    public Currency getCurrency() {
        return getDetails().getCurrency();
    }

    public BigDecimal getAgreedFx() {
        return getDetails().getAgreedFx();
    }

    public int getUnits() {
        return getDetails().getUnits();
    }

    public BigDecimal getPricePerUnit() {
        return getDetails().getPricePerUnit();
    }

    public BigDecimal getTradeAmount() {
        return getDetails().getTradeAmount()
                .setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    @Override
    public String toString() {
        return entity;
    }
}
