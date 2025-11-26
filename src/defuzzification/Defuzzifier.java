package defuzzification;

import inference.InferenceResult;

public interface Defuzzifier {
    DefuzzificationResult defuzzify(InferenceResult fuzzyOutput);
}
