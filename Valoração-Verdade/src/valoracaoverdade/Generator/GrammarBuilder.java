package valoracaoverdade.Generator;

import valoracaoverdade.Grammar.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public final class GrammarBuilder {

    /*
        Gramática Livre-de-Contexto das Expressões Legítimas da Lógica Proposicional
        S -> Atômicas | (~S) | (S v S) | (S & S) | (S > S)
     */
    public static Grammar generateLegitExpressionsGrammar() {

        Set<Rule> rules = new HashSet<Rule>();

        // Atomic.
        List<Symbol> production = new LinkedList<Symbol>();
        production.add(new Terminal('x'));
        rules.add(new Rule(new Variable('S'), production));

        // Negation.
        production = new LinkedList<Symbol>();
        production.add(new Terminal('('));
        production.add(new Terminal('~'));
        production.add(new Variable('S'));
        production.add(new Terminal(')'));
        rules.add(new Rule(new Variable('S'), production));

        // Conjunction.
        rules.add(new Rule(new Variable('S'), binaryOperatorProduction('&')));

        // Disjunction.
        rules.add(new Rule(new Variable('S'), binaryOperatorProduction('v')));

        // Implication.
        rules.add(new Rule(new Variable('S'), binaryOperatorProduction('>')));

        return new Grammar(rules, new Variable('S'));
    }

    private static List<Symbol> binaryOperatorProduction(char operator) {
        List<Symbol> production = new LinkedList<Symbol>();
        production.add(new Terminal('('));
        production.add(new Variable('S'));
        production.add(new Terminal(' '));
        production.add(new Terminal(operator));
        production.add(new Terminal(' '));
        production.add(new Variable('S'));
        production.add(new Terminal(')'));
        return production;
    }

    /*
        Gramática Regular da Lógica Proposicional
        S -> SímboloS | e
     */
    public static Grammar generateNonLegitExpressionsGrammar() {

        Set<Rule> rules = new HashSet<Rule>();

        List<Symbol> production = new LinkedList<>();
        production.add(new Terminal('x'));
        production.add(new Variable('S'));
        rules.add(new Rule(new Variable('S'), production));

        rules.add(new Rule(new Variable('S'), nonLegitProduction('(')));
        rules.add(new Rule(new Variable('S'), nonLegitProduction(')')));
        rules.add(new Rule(new Variable('S'), nonLegitProduction('&')));
        rules.add(new Rule(new Variable('S'), nonLegitProduction('v')));
        rules.add(new Rule(new Variable('S'), nonLegitProduction('~')));
        rules.add(new Rule(new Variable('S'), nonLegitProduction('>')));

        production = new LinkedList<>();
        production.add(new Terminal('ε'));
        rules.add(new Rule(new Variable('S'), production));

        return new Grammar(rules, new Variable('S'));
    }

    private static List<Symbol> nonLegitProduction(char symbol) {
        List<Symbol> production = new LinkedList<>();
        production.add(new Terminal(symbol));
        production.add(new Variable('S'));
        return production;
    }
}
