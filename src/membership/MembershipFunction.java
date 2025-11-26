package membership;

public interface MembershipFunction {
    double evaluate(double x);

    double centroid();

    double meanOfMaximum();
}
