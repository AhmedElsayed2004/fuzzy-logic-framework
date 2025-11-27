package fuzzy;

import variables.LinguisticVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Fuzzifier {
    public FuzzificationResult fuzzify(Map<LinguisticVariable, Double> input) {
        return new FuzzificationResult(input.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getKey().fuzzify(entry.getValue())
                )));
    }
}
