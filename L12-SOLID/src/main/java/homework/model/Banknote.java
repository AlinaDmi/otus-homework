package homework.model;

public class Banknote {
    private final Currency currency;
    private final Long uniqueCode;

    public Banknote(Currency currency, Long uniqueCode) {
        this.currency = currency;
        this.uniqueCode = uniqueCode;
    }

    public Currency getCurrency() {
        return currency;
    }
}
