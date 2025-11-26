package inference;

import variables.LinguisticVariable;

import java.util.HashMap;
import java.util.Map;

public class InferenceResult {
    private Map<LinguisticVariable, Map<String, Double>> values;

    public InferenceResult() {
    }

    public InferenceResult(Map<LinguisticVariable, Map<String, Double>> values) {
        this.values = values;
    }

    public Map<LinguisticVariable, Map<String, Double>> getValues() {
        return values;
    }

    public boolean contains(LinguisticVariable var) {
        return values.containsKey(var);
    }

    public Map<String, Double> get(LinguisticVariable var) {
        return values.get(var);
    }

    public void addMembership(LinguisticVariable var,
                              String fuzzySetName,
                              double membershipValue) {
        values.computeIfAbsent(var, k -> new HashMap<>())
                .merge(fuzzySetName, membershipValue, Math::max);
    }

}
