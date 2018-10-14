package valoracaoverdade.Generator;

import valoracaoverdade.Expression.ExpressionSet;
import valoracaoverdade.Grammar.*;

import java.util.*;

public class Generator {

    private Grammar legitExpressionsGrammar;
    private Grammar allExpressionsGrammar;
    private Random rand = new Random();

    private static final double legitThreshold = 0.05;
    private static final double setThreshold = 0.4;
    private static final int setSizeThreshold = 10;
    private static final double atomicThreshold = 0.05;
    private static final int complexityThreshold = 10;

    public Generator(Grammar legitExpressionsGrammar, Grammar allExpressionsGrammar) {
        this.legitExpressionsGrammar = legitExpressionsGrammar;
        this.allExpressionsGrammar = allExpressionsGrammar;
    }

    public String generateProblem(double isSet) {

        String expression = "";
        if(isSet <= setThreshold)
            expression += generateSet();
        else
            expression += generateExpression();

        return expression += " " + generateTruthValues(expression);
    }

    private String generateSet() {
        ExpressionSet set = new ExpressionSet();

        int numExpressions = rand.nextInt(setSizeThreshold) + 1;
        for (int i = 0; i < numExpressions; i++)
            set.insert(generateExpression());

        return set.toString();
    }

    private String generateExpression() {

        double isLegit = rand.nextDouble();
        double isAtomic = rand.nextDouble();
        if(isLegit <= legitThreshold) {
            String expression = generateNonLegitExpression();
            while(expression.length() <= 1)
                expression = generateNonLegitExpression();
            return expression;
        } else {
            String expression = generateLegitExpression();
            while(isAtomic > atomicThreshold && expression.length() == 1)
                expression = generateLegitExpression();
            return expression;
        }
    }

    private String generateTruthValues(String expression) {
        Set<Character> variables = new HashSet<>();
        for(int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) >= 'A' && expression.charAt(i) <= 'Z')
                variables.add(expression.charAt(i));
        }

        String truthValues = "";
        int numTruthValues = 0;
        for(Character c : variables) {
            numTruthValues++;

            truthValues += rand.nextBoolean() ? 1 : 0;
            if(numTruthValues < variables.size())
                truthValues += " ";
        }

        return truthValues;
    }

    private String generateLegitExpression() {
        return legitExpressionsGrammar.generateWord(rand.nextInt(complexityThreshold));
    }

    private String generateNonLegitExpression() {
        return allExpressionsGrammar.generateWord(rand.nextInt(40));
    }

}
