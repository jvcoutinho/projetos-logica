package resolucao.Grammar;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

// G = (V, E, R, S)
public class Grammar {

    private Set<Variable> V;
    private Set<Terminal> E;
    private Set<Rule> R;
    private Variable S;
    private Random rand = new Random();

    public Grammar(Set<Rule> R, Variable S) {
        this.R = R;
        this.S = S;
        E = new HashSet<>();
        V = new HashSet<>();

        for (Rule r : R) {
            V.add(r.getVariable());
            for (Symbol s : r.getProduction())
                if (s.isTerminal())
                    E.add((Terminal) s);
                else
                    V.add((Variable) s);
        }
    }

    public Variable getS() {
        return S;
    }

    public Set<Rule> getR() {
        return R;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Rule r: this.R) {
            builder.append(r.toString());
            builder.append("\n");
        }
        return builder.toString();
    }

    public String generateWord(Variable variable, int threshold) {

        if(threshold == 0)
            return '(' + (rand.nextBoolean() ? "~" : "") + (char) (rand.nextInt('E' - 'A') + 'A') + ')';
        String word = "";

        // Pick a random rule.
        Set<Rule> R = limitRules(this.R, variable);
        Rule r = (Rule) R.toArray()[rand.nextInt(R.size())];

        for(Symbol s : r.getProduction()) {

            // If it's a non-terminal, recurse.
            if(!s.isTerminal()) {
                if(s.equals('S'))
                    word += generateWord((Variable) s, threshold - 1);
                else
                    word += generateWord((Variable) s, threshold);
            }
            else if(s.equals('x'))
                word += (char) (rand.nextInt('E' - 'A') + 'A');
            else if(s.getSymbol() == 'ε')
                word += "";
            else
                word += (Terminal) s;
        }
        return word;
    }

    private Set<Rule> limitRules(Set<Rule> R, Variable V) {
        Set<Rule> rules = new HashSet<>();
        for (Rule r : R)
            if(r.getVariable().equals(V))
                rules.add(r);
        return rules;
    }

}
