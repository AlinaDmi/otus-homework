package homework;

import homework.model.Banknote;

import java.util.List;

public interface ATMInterface {

    public List<Banknote> addBanknotesToATMOrReturn(List<Banknote> banknoteList);

    public List<Banknote> getBanknotesFromATM(Integer sumToGet);

    public Integer getAmountLeft();
}
