package operators;

public class MinAndOperator implements AndOperator {

    @Override
    public double calculate(double a, double b) {
        return Math.min(a, b);
    }
}
