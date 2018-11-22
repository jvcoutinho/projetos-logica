package valoracaoverdade.Grammar;

import java.util.List;
import java.util.regex.Pattern;

public class Rule {
    private Variable variable;
    private List<Symbol> production;

    public Rule(Variable variable, List<Symbol> production) {
        this.variable = variable;
        this.production = production;
    }

    public List<Symbol> getProduction() {
        return production;
    }

    public Variable getVariable() {
        return variable;
    }

    @Override
    public String toString() {
        return variable + " -> " + production;
    }
}
