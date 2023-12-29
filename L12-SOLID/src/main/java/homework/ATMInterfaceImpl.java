package homework;

import com.google.common.collect.Lists;
import homework.model.ATM;
import homework.model.Banknote;
import homework.model.Currency;
import homework.model.MoneyCell;

import java.util.ArrayList;
import java.util.List;

public class ATMInterfaceImpl implements ATMInterface {
    private final ATM atm = new ATM();

    @Override
    public List<Banknote> addBanknotesToATMOrReturn(List<Banknote> banknoteList) {
        List<MoneyCell> moneyCells = atm.getMoneyCells();
        List<Banknote> toReturn = new ArrayList<>();

        System.out.println("Принимаем банкноты " + banknoteList.size() + " шт.");

        for (Banknote banknote : banknoteList) {
            MoneyCell cellForCurrentBanknote = moneyCells.stream()
                    .filter(c -> c.getCurrency() == banknote.getCurrency())
                    .findFirst().orElse(null);
            if (cellForCurrentBanknote == null) {
                toReturn.add(banknote);
                continue;
            }
            cellForCurrentBanknote.addBanknote(banknote);
        }

        System.out.println("Не принято банкнот " + toReturn.size() + " шт.");
        return toReturn;
    }

    @Override
    public List<Banknote> getBanknotesFromATM(Integer sumToGet) {
        List<MoneyCell> moneyCells = atm.getMoneyCells();
        List<Banknote> result = new ArrayList<>();

        for (MoneyCell moneyCell : Lists.reverse(moneyCells)) {

            List<Banknote> cellBanknotes = moneyCell.getBanknoteList();
            if (moneyCell.getDignity() > sumToGet || cellBanknotes.isEmpty())
                continue;

            int needToTake = sumToGet / moneyCell.getDignity();
            int takingFromCell = getNeedToTakeFromCell(cellBanknotes, needToTake);
            result.addAll(moneyCell.getBanknotesFromCell(takingFromCell));
            sumToGet = sumToGet - (takingFromCell * moneyCell.getDignity());

        }

        if (sumToGet != 0) {
            throw new ArithmeticException("Ошибка в выдаче банкнот. Невозможно найти банкноты для суммы: " + sumToGet);
        }

        System.out.println("Отдаём банкноты...");
        return result;
    }

    private int getNeedToTakeFromCell(List<Banknote> cellBanknotes, int needToTake) {
        return Math.min(cellBanknotes.size(), needToTake);
    }

    @Override
    public Integer getAmountLeft() {
        Integer sum = 0;
        for (MoneyCell moneyCell : atm.getMoneyCells()) {
            sum += moneyCell.getDignity() * moneyCell.getBanknoteList().size();
        }
        System.out.println("В банкомате осталось " + sum);
        return sum;
    }
}
