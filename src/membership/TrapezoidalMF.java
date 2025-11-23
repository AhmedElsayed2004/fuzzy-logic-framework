package membership;

public class TrapezoidalMF implements MembershipFunction {
    private double leftFoot;
    private double leftShoulder;
    private double rightShoulder;
    private double rightFoot;

    @Override
    public double evaluate(double x) {
        if (x <= leftFoot || x >= rightFoot) return 0;
        if (x <= leftShoulder)
            return (x - leftFoot) / (leftShoulder - leftFoot);
        if (x <= rightShoulder)
            return 1;
        return (x - rightFoot) / (rightShoulder - rightFoot);
    }
}
