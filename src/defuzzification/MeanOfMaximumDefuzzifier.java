package defuzzification;

import inference.InferenceResult;
import variables.LinguisticVariable;

public class MeanOfMaximumDefuzzifier implements Defuzzifier {
    @Override
    public DefuzzificationResult defuzzify(InferenceResult inferenceResult) {

        DefuzzificationResult defuzzificationResult = new DefuzzificationResult();
        for (var entry : inferenceResult.getValues().entrySet()) {
            LinguisticVariable linguisticVariable = entry.getKey();
            double sum = 0, weightedSum = 0;
            for (var fuzzySet : entry.getValue().entrySet()) {
                sum += fuzzySet.getValue();
                weightedSum += fuzzySet.getValue() * entry.getKey().getFuzzySet(fuzzySet.getKey()).centroid();
            }
            defuzzificationResult.add(linguisticVariable, sum / weightedSum);
        }
        return defuzzificationResult;
    }
}
