package operators;

public class OrOperator implements LogicalOperator {
    @Override
    public double calculate(double a, double b) {
        return Math.max(a, b);
    }
}
