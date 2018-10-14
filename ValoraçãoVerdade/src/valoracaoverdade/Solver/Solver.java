package valoracaoverdade.Solver;

import valoracaoverdade.Expression.Expression;
import valoracaoverdade.Expression.ExpressionSet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solver {

    private Parser parser;
    private Map<Character, Boolean> truthValues;

    private final String ilegitExpression = "A palavra nao e legitima.";
    private final String satisfiedExpression = "A valoracao-verdade satisfaz a proposicao.";
    private final String unsatisfiedExpression = "A valoracao-verdade nao satisfaz a proposicao.";
    private final String ilegitSet = "Ha uma palavra nao legitima no conjunto.";
    private final String satisfiedSet = "A valoracao-verdade satisfaz o conjunto.";
    private final String unsatisfiedSet = "A valoracao-verdade nao satisfaz o conjunto.";

    public String solve(String problem) {
        parser = new Parser();
        String expression = problem.split("( 0)|( 1)")[0];
        truthValues = retrieveTruthValues(problem);

        if(problem.charAt(0) == '{')
            return solveSet(expression);
        else
            return solveExpression(expression);
    }

    private String solveSet(String set) {
        set = set.substring(1, set.length() - 1);
        String[] expressions = set.split(", ");

        for(String s : expressions) {
            String result = solveExpression(s);
            if (result.equals(ilegitExpression))
                return ilegitSet;
        }

        for(String s : expressions) {
            String result = solveExpression(s);
            if(result.equals(unsatisfiedExpression))
                return unsatisfiedSet;
        }

        return satisfiedSet;
    }

    private String solveExpression(String exp) {

        Expression expression = parser.parseExpression(exp);
        if(expression == null || !expression.isLegit() || !expression.toString().equals(exp))
            return ilegitExpression;
        else if(!expression.evaluate(truthValues))
            return unsatisfiedExpression;
        else
            return satisfiedExpression;
    }

    private Map<Character, Boolean> retrieveTruthValues(String problem) {
        List<Character> variables = new LinkedList<>();
        Map<Character, Boolean> truthValues = new HashMap<>();

        int variable = 0;
        for (int i = 0; i < problem.length(); i++) {
            char symbol = problem.charAt(i);

            if (symbol >= 'A' && symbol <= 'Z' && !variables.contains(symbol))
                variables.add(problem.charAt(i));

            else if (symbol == '0') {
                truthValues.put(variables.get(variable), false);
                variable++;
            } else if (symbol == '1') {
                truthValues.put(variables.get(variable), true);
                variable++;
            }
        }
        return truthValues;
    }

}
