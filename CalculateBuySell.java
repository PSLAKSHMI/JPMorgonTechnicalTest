package model.calculation;



public enum CalculateBuySell {
    BUY("B"),
    SELL("S");

    private String text;

    CalculateBuySell(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static CalculateBuySell fromString(String text) {

        if (text != null) {
            for (CalculateBuySell tmp : CalculateBuySell.values()) {
                if (text.equalsIgnoreCase(tmp.text)) {
                    return tmp;
                }
            }

            throw new IllegalArgumentException("No enumeration constant with text " + text + " found!");
        } else {
            throw new NullPointerException("Null pointer supplied.");
        }
    }
}

