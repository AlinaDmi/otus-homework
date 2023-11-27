package homework;


import java.util.ArrayDeque;

public class CustomerReverseOrder {

    // todo: 2. надо реализовать методы этого класса
    // надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    private final ArrayDeque<Customer> customers = new ArrayDeque<>();

    public void add(Customer customer) {
        customers.addLast(customer);
    }

    public Customer take() {
        Customer customer = customers.getLast();
        customers.removeLast();
        return customer;
    }
}
