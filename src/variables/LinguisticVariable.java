package variables;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LinguisticVariable {
    private String name;
    private Map<String, FuzzySet> fuzzySets;
    private Domain domain;

    public LinguisticVariable(String name, Map<String, FuzzySet> fuzzySets, Domain domain) {
        this.name = name;
        this.fuzzySets = fuzzySets;
        this.domain = domain;
    }

    public LinguisticVariable(String name, Domain domain) {
        this(name, new HashMap<>(), domain);
    }

    // Must ensure that all fuzzy sets is distinct (fuzzy set determines by its name)
    public void addFuzzySet(FuzzySet fuzzySet) {
        String name = fuzzySet.getName();
        if (fuzzySets.containsKey(name)) {
            throw new IllegalArgumentException(
                    "variables.FuzzySet name '" + name + "' already exists."
            );
        }
        fuzzySets.put(name, fuzzySet);
    }

    public Map<String, Double> fuzzify(double x) {
        if(!domain.contains(x))
            throw new IllegalArgumentException(
                    x + " is outside the domain [" + domain.getMin() + ", " + domain.getMax() + "]"
            );
        return fuzzySets.values().stream()
                .collect(Collectors.toMap(
                        FuzzySet::getName,
                        fs -> fs.evaluate(x)
                ));
    }


}
