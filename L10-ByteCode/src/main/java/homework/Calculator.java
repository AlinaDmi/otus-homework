package homework;

public class Calculator implements CalculatorInterface {

    @Override
    public String toString() {
        return "Calculator{}";
    }

    @Log
    public void calculation(int param) {
        System.out.println("calculating " + param);
    }

    @Log
    public void calculation(int param, int param2) {
        System.out.println("calculating " + param + param2);
    }

    public void calculation(int param, int param2, String param3) {
        System.out.println("calculating " + param + param2 + param3);
    }

    @Log
    public void writing(String param) {
        System.out.println("writing " + param);
    }

}
