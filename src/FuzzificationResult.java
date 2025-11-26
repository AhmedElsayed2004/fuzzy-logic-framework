import variables.LinguisticVariable;

import java.util.Map;

public class FuzzificationResult {
    private final Map<LinguisticVariable, Map<String, Double>> values;

    public FuzzificationResult(Map<LinguisticVariable, Map<String, Double>> values) {
        this.values = values;
    }

    public Map<String, Double> get(LinguisticVariable variable) {
        return values.get(variable);
    }
}
