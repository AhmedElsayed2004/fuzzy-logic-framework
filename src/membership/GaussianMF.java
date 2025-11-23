package membership;

public class GaussianMF implements MembershipFunction {
    private double mean;
    private double sigma;

    @Override
    public double evaluate(double x) {
        return Math.exp(-(x - mean) * (x - mean) / (2 * sigma * sigma));
    }
}
