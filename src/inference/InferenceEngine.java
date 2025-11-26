package inference;

import fuzzy.FuzzificationResult;
import rules.RuleBase;

public interface InferenceEngine {
    public InferenceResult infer(FuzzificationResult fuzz, RuleBase ruleBase);
}
