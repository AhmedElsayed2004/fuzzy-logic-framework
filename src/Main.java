import core.FuzzySystem;
import membership.MembershipFunction;
import membership.TriangularMF;
import operators.AndOperator;
import operators.OrOperator;
import rules.Rule;
import rules.RuleTerm;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        FuzzySystem fuzzySystem = new FuzzySystem();

// Input and output variables
        fuzzySystem.addInputVariable("AltitudeError", -2, 2);
        fuzzySystem.addInputVariable("RateChange", -1, 1);
        fuzzySystem.addOutputVariable("Throttle", -100, 100);

// Fuzzy sets
// Altitude Error (E)
        fuzzySystem.addFuzzySet("AltitudeError", "N", new TriangularMF(-2, -2, 0));
        fuzzySystem.addFuzzySet("AltitudeError", "Z", new TriangularMF(-0.5, 0, 0.5));
        fuzzySystem.addFuzzySet("AltitudeError", "P", new TriangularMF(0, 2, 2));

// Rate of Change (dE/dt)
        fuzzySystem.addFuzzySet("RateChange", "DF", new TriangularMF(-1, -1, -0.4));
        fuzzySystem.addFuzzySet("RateChange", "S", new TriangularMF(-0.6, 0, 0.6));
        fuzzySystem.addFuzzySet("RateChange", "AF", new TriangularMF(0.4, 1, 1));

// Throttle Adjustment (T)
        fuzzySystem.addFuzzySet("Throttle", "DH", new TriangularMF(-100, -100, -60));
        fuzzySystem.addFuzzySet("Throttle", "DL", new TriangularMF(-80, -40, 0));
        fuzzySystem.addFuzzySet("Throttle", "NC", new TriangularMF(-10, 0, 10));
        fuzzySystem.addFuzzySet("Throttle", "IL", new TriangularMF(0, 40, 80));
        fuzzySystem.addFuzzySet("Throttle", "IH", new TriangularMF(60, 100, 100));

// Rules with only two antecedents (AltitudeError and RateChange)
        fuzzySystem.addRule("AltitudeError", "N", "RateChange", "DF", new AndOperator(), "Throttle", "IH");
        fuzzySystem.addRule("AltitudeError", "N", "RateChange", "S", new AndOperator(), "Throttle", "IL");
        fuzzySystem.addRule("AltitudeError", "N", "RateChange", "AF", new AndOperator(), "Throttle", "NC");
        fuzzySystem.addRule("AltitudeError", "Z", "RateChange", "DF", new AndOperator(), "Throttle", "IL");
        fuzzySystem.addRule("AltitudeError", "Z", "RateChange", "S", new AndOperator(), "Throttle", "NC");
        fuzzySystem.addRule("AltitudeError", "Z", "RateChange", "AF", new AndOperator(), "Throttle", "DL");
        fuzzySystem.addRule("AltitudeError", "P", "RateChange", "DF", new AndOperator(), "Throttle", "NC");
        fuzzySystem.addRule("AltitudeError", "P", "RateChange", "S", new AndOperator(), "Throttle", "DL");
        fuzzySystem.addRule("AltitudeError", "P", "RateChange", "AF", new AndOperator(), "Throttle", "DH");

// Example input
        Map<String, Double> inputs = new HashMap<>();
        inputs.put("AltitudeError", -1.0);
        inputs.put("RateChange", -0.5);

        var result = fuzzySystem.run(inputs);
        System.out.println(result);

    }
}