package homework.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MoneyCell {
    private final Currency currency;
    private LinkedList<Banknote> banknotes;

    public MoneyCell(Currency currency) {
        this.currency = currency;
        this.banknotes = new LinkedList<>();
    }

    public Integer getDignity() {
        return currency.getDignity();
    }

    public Currency getCurrency() {
        return currency;
    }

    public List<Banknote> getBanknoteList() {
        return List.copyOf(banknotes);
    }

    public void addBanknotes(List<Banknote> newBanknotes) {
        this.banknotes.addAll(newBanknotes);
    }

    public void addBanknote(Banknote newBanknote) {
        this.banknotes.add(newBanknote);
    }

    public List<Banknote> getBanknotesFromCell(int amountToRemove) {
        List<Banknote> toRemove = new ArrayList<>();
        for (int i = 0; i<amountToRemove; i++){
            toRemove.add(this.banknotes.getFirst());
            this.banknotes.removeFirst();
        }
        return toRemove;
    }

}
