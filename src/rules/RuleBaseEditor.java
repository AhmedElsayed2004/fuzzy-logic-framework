package rules;

public class RuleBaseEditor {
    RuleBase ruleBase;

    public RuleBaseEditor(RuleBase ruleBase) {
        this.ruleBase = ruleBase;
    }

    public void create(Rule rule) {
        ruleBase.addRule(rule);
    }

    public void edit(int index, Rule rule) {
        ruleBase.editRule(index, rule);
    }

    public void delete(int index) {
        ruleBase.removeRule(index);
    }

    public void enable(int index) {
        ruleBase.enableRule(index);
    }

    public void disable(int index) {
        ruleBase.disableRule(index);
    }
}
