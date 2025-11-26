package variables;

import membership.MembershipFunction;

public class FuzzySet {
    private String name;
    private MembershipFunction membershipFunction;

    public FuzzySet(String name, MembershipFunction membershipFunction) {
        this.name = name;
        this.membershipFunction = membershipFunction;
    }

    public double evaluate(double x) {
        return membershipFunction.evaluate(x);
    }

    public double centroid() {
        return membershipFunction.centroid();
    }

    public double meanOfMaximum() {
        return membershipFunction.meanOfMaximum();
    }

    public String getName() {
        return name;
    }
}
