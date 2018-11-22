package resolucao.Generator;

import resolucao.Grammar.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public final class GrammarBuilder {

    /*
        Gramática Livre-de-Contexto das Proposições na Forma Normal Conjuntiva da Lógica Proposicional
        S -> (C) & S | (C)
        C -> L v C | L
        L -> Atômicas | ~Atômicas
     */
    public static Grammar generateFNCExpressionsGrammar() {

        Set<Rule> rules = new HashSet<Rule>();

        // Multiple clauses.
        List<Symbol> production = new LinkedList<Symbol>();
        production.add(new Terminal('('));
        production.add(new Variable('C'));
        production.add(new Terminal(')'));
        production.add(new Terminal(' '));
        production.add(new Terminal('&'));
        production.add(new Terminal(' '));
        production.add(new Variable('S'));
        rules.add(new Rule(new Variable('S'), production));

        production = new LinkedList<Symbol>();
        production.add(new Variable('S'));
        production.add(new Terminal(' '));
        production.add(new Terminal('&'));
        production.add(new Terminal(' '));
        production.add(new Terminal('('));
        production.add(new Variable('C'));
        production.add(new Terminal(')'));
        rules.add(new Rule(new Variable('S'), production));

        // Multiple literals.
        production = new LinkedList<Symbol>();
        production.add(new Variable('L'));
        production.add(new Terminal(' '));
        production.add(new Terminal('v'));
        production.add(new Terminal(' '));
        production.add(new Variable('C'));
        rules.add(new Rule(new Variable('C'), production));

        // Single literal.
        production = new LinkedList<Symbol>();
        production.add(new Variable('L'));
        rules.add(new Rule(new Variable('C'), production));

        // Literals.
        production = new LinkedList<Symbol>();
        production.add(new Terminal('x'));
        rules.add(new Rule(new Variable('L'), production));

        production = new LinkedList<Symbol>();
        production.add(new Terminal('~'));
        production.add(new Terminal('x'));
        rules.add(new Rule(new Variable('L'), production));

        return new Grammar(rules, new Variable('S'));
    }

}
