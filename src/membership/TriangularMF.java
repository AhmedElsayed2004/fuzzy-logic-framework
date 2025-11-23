package membership;

public class TriangularMF implements MembershipFunction {
    private double left;
    private double peak;
    private double right;

    public TriangularMF(double left, double peak, double right) {
        this.left = left;
        this.peak = peak;
        this.right = right;
    }

    @Override
    public double evaluate(double x) {
        if (x <= left || x >= right) return 0;
        if (x <= peak)
            return (x - left) / (peak - left);
        return (x - right) / (peak - right);
    }
}
