import variables.LinguisticVariable;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class fuzzifier {
    List<LinguisticVariable> linguisticVariables;

    public fuzzifier(List<LinguisticVariable> linguisticVariables) {
        this.linguisticVariables = linguisticVariables;
    }

    public FuzzificationResult fuzzify(Map<LinguisticVariable, Double> input) {
        return new FuzzificationResult(linguisticVariables.stream()
                .collect(Collectors.toMap(
                        lv -> lv,
                        lv -> lv.fuzzify(input.getOrDefault(lv, 0D))
                )));
    }

}
