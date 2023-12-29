package homework.model;

public enum Currency {
    TEN(10),
    FIFTY(50),
    ONE_HUNDRED(100),
    TWO_HUNDREDS(200),
    FIVE_HUNDREDS(500),
    ONE_THOUSAND(1000),;

    private final int dignity;

    Currency(int dignity) {
        this.dignity = dignity;
    }

    public int getDignity() {
        return dignity;
    }
}
