package homework.model;

import java.util.ArrayList;
import java.util.List;

public class ATM {
    private List<MoneyCell> moneyCells;

    public ATM() {
        List<MoneyCell> newCells = new ArrayList<>();
        newCells.add(new MoneyCell(Currency.TEN));
        newCells.add(new MoneyCell(Currency.FIFTY));
        newCells.add(new MoneyCell(Currency.ONE_HUNDRED));
        newCells.add(new MoneyCell(Currency.FIVE_HUNDREDS));
        newCells.add(new MoneyCell(Currency.ONE_THOUSAND));

        this.moneyCells = newCells;
    }

    public List<MoneyCell> getMoneyCells() {
        return List.copyOf(moneyCells);
    }

    public void setMoneyCells(List<MoneyCell> moneyCells) {
        this.moneyCells = moneyCells;
    }
}
