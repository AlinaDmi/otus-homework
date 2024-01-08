package homework;

public class App {

    public static void main(String[] args) {
        CalculatorInterface calculator = (CalculatorInterface) Ioc.createClass(Calculator.class, CalculatorInterface.class);
        calculator.calculation(1);
        calculator.calculation(1, 2);
        calculator.calculation(1, 2, "3");
        calculator.writing("sssss");
    }
}
