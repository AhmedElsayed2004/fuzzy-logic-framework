package operators;

public class AndOperator implements LogicalOperator {
    @Override
    public double calculate(double a, double b) {
        return Math.min(a, b);
    }
}
