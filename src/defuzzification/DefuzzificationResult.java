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

    public boolean contains(LinguisticVariable var) {
        return crispValues.containsKey(var);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DefuzzificationResult:\n");
        for (Map.Entry<LinguisticVariable, Double> entry : crispValues.entrySet()) {
            sb.append("  ")
                    .append(entry.getKey().getName())
                    .append(" = ")
                    .append(entry.getValue())
                    .append("\n");
        }
        return sb.toString();
    }
}
