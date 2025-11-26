package defuzzification;

import variables.LinguisticVariable;

import java.util.HashMap;
import java.util.Map;

public class DefuzzificationResult {
    Map<LinguisticVariable, Double> crispValues;

    public DefuzzificationResult() {
        crispValues = new HashMap<LinguisticVariable, Double>();
    }

    public void add(LinguisticVariable var, double value) {
        crispValues.put(var, value);
    }

    public double getValue(LinguisticVariable var) {
        return crispValues.get(var);
    }
}
