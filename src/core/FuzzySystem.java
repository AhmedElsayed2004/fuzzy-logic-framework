package core;

import defuzzification.CentroidDefuzzifier;
import defuzzification.DefuzzificationResult;
import defuzzification.Defuzzifier;
import fuzzy.FuzzificationResult;
import fuzzy.Fuzzifier;
import inference.InferenceEngine;
import inference.InferenceResult;
import inference.MamdaniEngine;
import membership.MembershipFunction;
import operators.LogicalOperator;
import rules.Rule;
import rules.RuleBase;
import rules.RuleBaseEditor;
import rules.RuleTerm;
import variables.Domain;
import variables.FuzzySet;
import variables.LinguisticVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FuzzySystem {
    private Fuzzifier fuzzifier;
    private InferenceEngine inferenceEngine;
    private Defuzzifier defuzzifier;
    private RuleBase ruleBase;
    private RuleBaseEditor ruleBaseEditor;
    private Map<String, LinguisticVariable> inputVariables;
    private Map<String, LinguisticVariable> outputVariables;

    public FuzzySystem() {
        fuzzifier = new Fuzzifier();
        inferenceEngine = new MamdaniEngine();
        defuzzifier = new CentroidDefuzzifier();
        ruleBase = new RuleBase();
        ruleBaseEditor = new RuleBaseEditor(ruleBase);
        inputVariables = new HashMap<String, LinguisticVariable>();
        outputVariables = new HashMap<String, LinguisticVariable>();
    }

    public void addInputVariable(String variableName, double minimumValue, double maximumValue) {
        if (inputVariables.containsKey(variableName) || outputVariables.containsKey(variableName)) {
            throw new IllegalArgumentException("Variable " + variableName + " already exists");
        }
        inputVariables.put(variableName, new LinguisticVariable(variableName, new Domain(minimumValue, maximumValue)));

    }

    public void addInputVariable(String variableName, double minimumValue, double maximumValue, double defaultValue) {
        if (inputVariables.containsKey(variableName) || outputVariables.containsKey(variableName)) {
            throw new IllegalArgumentException("Variable " + variableName + " already exists");
        }
        inputVariables.put(variableName, new LinguisticVariable(variableName, new Domain(minimumValue, maximumValue), defaultValue));
    }

    public void addOutputVariable(String variableName, double minimumValue, double maximumValue) {
        if (inputVariables.containsKey(variableName) || outputVariables.containsKey(variableName)) {
            throw new IllegalArgumentException("Variable " + variableName + " already exists");
        }
        outputVariables.put(variableName, new LinguisticVariable(variableName, new Domain(minimumValue, maximumValue)));
    }

    public void addOutputVariable(String variableName, double minimumValue, double maximumValue, double defaultValue) {
        if (inputVariables.containsKey(variableName) || outputVariables.containsKey(variableName)) {
            throw new IllegalArgumentException("Variable " + variableName + " already exists");
        }
        outputVariables.put(variableName, new LinguisticVariable(variableName, new Domain(minimumValue, maximumValue), defaultValue));
    }

    public void addFuzzySet(String variableName, String fuzzySetName, MembershipFunction membershipFunction) {
        if (inputVariables.containsKey(variableName))
            inputVariables.get(variableName).addFuzzySet(new FuzzySet(fuzzySetName, membershipFunction));
        else if (outputVariables.containsKey(variableName))
            outputVariables.get(variableName).addFuzzySet(new FuzzySet(fuzzySetName, membershipFunction));
        else
            throw new IllegalArgumentException("Variable " + variableName + " does not exist");
    }

    public void addRule(String antecedent1VariableName,
                        String antecedent1VariableFuzzySetName,
                        String antecedent2VariableName,
                        String antecedent2VariableFuzzySetName,
                        LogicalOperator logicalOperator,
                        String consequentVariableName,
                        String consequentFuzzySetName) {

        if (!inputVariables.containsKey(antecedent1VariableName))
            throw new IllegalArgumentException("Variable " + antecedent1VariableName + " does not exist");
        if (!inputVariables.containsKey(antecedent2VariableName))
            throw new IllegalArgumentException("Variable " + antecedent2VariableName + " does not exist");
        if (!outputVariables.containsKey(consequentVariableName))
            throw new IllegalArgumentException("Variable " + consequentVariableName + " does not exist");

        RuleTerm antecedent1Term = new RuleTerm(inputVariables.get(antecedent1VariableName), antecedent1VariableFuzzySetName);
        RuleTerm antecedent2Term = new RuleTerm(inputVariables.get(antecedent2VariableName), antecedent2VariableFuzzySetName);
        RuleTerm consequentTerm = new RuleTerm(outputVariables.get(consequentVariableName), consequentFuzzySetName);
        Rule rule = new Rule(antecedent1Term, antecedent2Term, logicalOperator, consequentTerm);
        ruleBaseEditor.create(rule);
    }

    public void updateRule(int index, Rule rule) {
        ruleBaseEditor.edit(index, rule);
    }

    public void deleteRule(int index) {
        ruleBaseEditor.delete(index);
    }

    public DefuzzificationResult run(Map<String, Double> inputs) {
        validateInput(inputs);
        fixMissingInputVariables(inputs);


        Map<LinguisticVariable, Double> input = inputs.entrySet().stream()
                .collect(Collectors.toMap(e -> inputVariables.get(e.getKey()), Map.Entry::getValue));

        FuzzificationResult fuzzificationResult = fuzzifier.fuzzify(input);
        InferenceResult inferenceResult = inferenceEngine.infer(fuzzificationResult, ruleBase);
        DefuzzificationResult defuzzificationResult = defuzzifier.defuzzify(inferenceResult);

        fixMissingOutputVariables(defuzzificationResult);
        return defuzzificationResult;
    }


    private void fixMissingInputVariables(Map<String, Double> inputs) {
        inputVariables.forEach((key, value) -> {
            if (!inputs.containsKey(key))
                inputs.put(key, value.getDefaultValue());
        });
    }

    private void fixMissingOutputVariables(DefuzzificationResult outputs) {
        outputVariables.forEach((key, value) -> {
            if (!outputs.contains(value))
                outputs.add(value, value.getDefaultValue());
        });
    }

    private void validateInput(Map<String, Double> inputs) {
        for (String key : inputs.keySet()) {
            if (!inputVariables.containsKey(key)) {
                throw new IllegalArgumentException("Variable " + key + " does not exist");
            }
        }
    }

}
