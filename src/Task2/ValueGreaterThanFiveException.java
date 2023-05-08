package Task2;

public class ValueGreaterThanFiveException extends ArithmeticException {
    public ValueGreaterThanFiveException() {
        super("The value is greater than 5");
    }
}
