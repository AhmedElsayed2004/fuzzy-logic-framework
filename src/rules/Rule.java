package rules;

import operators.LogicalOperator;
import utils.Pair;

public class Rule {
    private final RuleTerm antecedent1;
    private final RuleTerm antecedent2;
    private final LogicalOperator logicalOperator;
    private final RuleTerm consequent;
    private boolean enabled = true;

    public Rule(RuleTerm antecedent1, RuleTerm antecedent2, LogicalOperator logicalOperator, RuleTerm consequent) {
        this.antecedent1 = antecedent1;
        this.antecedent2 = antecedent2;
        this.logicalOperator = logicalOperator;
        this.consequent = consequent;
    }

    public Pair<String, Double> infer(double x, double y) {
        double a = antecedent1.evaluate(x);
        double b = antecedent2.evaluate(y);

        double firingStrength = logicalOperator.calculate(a, b);


        return new Pair<>(consequent.getSetName(), firingStrength);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void enable() {
        enabled = true;
    }

    public void disable() {
        enabled = false;
    }

    public RuleTerm getAntecedent1() {
        return antecedent1;
    }

    public RuleTerm getAntecedent2() {
        return antecedent2;
    }

    public LogicalOperator getLogicalOperator() {
        return logicalOperator;
    }

    public RuleTerm getConsequent() {
        return consequent;
    }
}
