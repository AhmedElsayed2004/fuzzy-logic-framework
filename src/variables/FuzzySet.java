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

    public String getName() {
        return name;
    }
}
