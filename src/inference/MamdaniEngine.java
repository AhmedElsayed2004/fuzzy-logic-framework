package inference;

import fuzzy.FuzzificationResult;
import rules.Rule;
import rules.RuleBase;

public class MamdaniEngine implements InferenceEngine {
    @Override
    public InferenceResult infer(FuzzificationResult fuzz, RuleBase ruleBase) {
        InferenceResult result = new InferenceResult();

        for (Rule rule : ruleBase.getRules()) {
            if (rule.isEnabled()) {
                double a = fuzz.get(rule.getAntecedent1().getVariable()).get(rule.getAntecedent1().getSetName());
                double b = fuzz.get(rule.getAntecedent2().getVariable()).get(rule.getAntecedent2().getSetName());
                double membershipValue = rule.getLogicalOperator().calculate(a, b);
                result.addMembership(rule.getConsequent().getVariable(),
                        rule.getConsequent().getSetName(),
                        membershipValue);
            }
        }
        return result;
    }
}
