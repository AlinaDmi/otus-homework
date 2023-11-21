package homework;


import java.util.LinkedList;

public class CustomerReverseOrder {

    // todo: 2. надо реализовать методы этого класса
    // надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    LinkedList<Customer> customers = new LinkedList<>();

    public void add(Customer customer) {
        customers.addLast(customer);
    }

    public Customer take() {
        Customer customer = customers.getLast();
        customers.removeLast();
        return customer;
    }
}
