package valoracaoverdade.Expression;

import java.util.HashSet;
import java.util.Set;

public class ExpressionSet {
    private Set<Expression> expressions;

    public ExpressionSet() {
        expressions = new HashSet<>();
    }

    public void insert(String expression) {
        expressions.add(new Expression(expression));
    }

    public void insert(Expression expression) {
        expressions.add(expression);
    }

    public Set<Expression> getExpressions() {
        return expressions;
    }

    @Override
    public String toString() {
        String set = "{";
        int numExpressions = 0;
        for(Expression e : expressions) {
            numExpressions++;

            set += e;
            if(numExpressions < expressions.size())
                set += ", ";
        }
        set += "}";

        return set;
    }

}
