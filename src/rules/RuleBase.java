package rules;

import java.util.ArrayList;
import java.util.List;

public class RuleBase {
    List<Rule> rules;

    RuleBase() {
        rules = new ArrayList<Rule>();
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public void editRule(int index, Rule rule) {
        rules.set(index, rule);
    }

    public void removeRule(int index) {
        rules.remove(index);
    }

    public void enableRule(int index) {
        rules.get(index).enable();
    }

    public void disableRule(int index) {
        rules.get(index).disable();
    }

    public List<Rule> getRules() {
        return rules;
    }
}
