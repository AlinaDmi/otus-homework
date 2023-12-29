package homework;

import homework.model.Banknote;
import homework.model.Currency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

class ATMInterfaceImplTest {

    @org.junit.jupiter.api.Test
    void atmTest() {
        ATMInterface atmInterface = new ATMInterfaceImpl();
        List<Banknote> banknotes = new ArrayList<>(getBanknotesForTest());

        List<Banknote> notAccepted = atmInterface.addBanknotesToATMOrReturn(banknotes);

        Assertions.assertEquals(1, notAccepted.size());
        Assertions.assertEquals(1650, atmInterface.getAmountLeft());

        List<Banknote> tookFromAtm = atmInterface.getBanknotesFromATM(600);

        Assertions.assertEquals(2, tookFromAtm.size());

        Assertions.assertEquals(1050, atmInterface.getAmountLeft());
        Exception exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            atmInterface.getBanknotesFromATM(12);
        });

        String expectedMessage = "Ошибка в выдаче банкнот. Невозможно найти банкноты для суммы";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    private List<Banknote> getBanknotesForTest() {
        List<Banknote> banknotes = new ArrayList<>();
        Banknote banknote1 = new Banknote(Currency.ONE_HUNDRED, 1l);
        Banknote banknote2 = new Banknote(Currency.TWO_HUNDREDS, 2l);
        Banknote banknote3 = new Banknote(Currency.FIFTY, 3l);
        Banknote banknote4 = new Banknote(Currency.FIVE_HUNDREDS, 4l);
        Banknote banknote5 = new Banknote(Currency.ONE_THOUSAND, 5l);

        banknotes.add(banknote1);
        banknotes.add(banknote2);
        banknotes.add(banknote3);
        banknotes.add(banknote4);
        banknotes.add(banknote5);
        return banknotes;
    }

}