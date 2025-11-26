package rules;

import variables.LinguisticVariable;

public class RuleTerm {
    private final LinguisticVariable variable;
    private final String setName; // the name of the fuzzy set inside the variable

    public RuleTerm(LinguisticVariable variable, String setName) {
        this.variable = variable;
        this.setName = setName;
    }

    public double evaluate(double x) {
        return variable.fuzzify(x).get(setName);
    }

    public String getSetName() {
        return setName;
    }

    public LinguisticVariable getVariable() {
        return variable;
    }
}
