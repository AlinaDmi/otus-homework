package homework;

public class App {
    public static void main(String[] args) {
        CalculatorInterface calculator = Ioc.createCalculator();
        calculator.calculation(1);
        calculator.calculation(1, 2);
        calculator.calculation(1, 2, "3");
        calculator.writing("sssss");
    }
}
