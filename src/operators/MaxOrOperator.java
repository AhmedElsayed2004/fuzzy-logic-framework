package operators;

public class MaxOrOperator implements OrOperator {

    @Override
    public double calculate(double a, double b) {
        return Math.max(a, b);
    }
}
