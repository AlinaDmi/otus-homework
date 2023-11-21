package homework;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;


public class CustomerService {

    NavigableMap<Customer, String> scores = new TreeMap<>((o1, o2) -> (int) (o1.getScores() - o2.getScores()));

    // todo: 3. надо реализовать методы этого класса
    // важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> found = scores.firstEntry();
        return getCopyOfEntry(found);
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> found = scores.higherEntry(customer);
        return getCopyOfEntry(found);
    }

    public void add(Customer customer, String data) {
        scores.put(customer, data);
    }

    private Map.Entry<Customer, String> getCopyOfEntry(Map.Entry<Customer, String> found) {
        if (found == null) {
            return null;
        }
        Customer foundCustomer = found.getKey();
        return Map.entry(new Customer(foundCustomer.getId(), foundCustomer.getName(), foundCustomer.getScores()), found.getValue());
    }
}
