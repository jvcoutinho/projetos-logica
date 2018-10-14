package valoracaoverdade.Expression;

import java.util.Map;

public class Expression {
    private String expression;
    char operator;

    public Expression(){};

    public Expression(String expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return expression;
    }

    public boolean isLegit() {
        try {
            if (this instanceof AtomicExpression)
                return true;
            else if (this instanceof NegationExpression)
                return ((NegationExpression) this).getW().isLegit();
            else if (this instanceof BinaryExpression)
                return ((BinaryExpression) this).getW1().isLegit() && ((BinaryExpression) this).getW2().isLegit();
        } catch (NullPointerException e) {
            return false;
        }

        return false;
    }

    public boolean evaluate(Map<Character, Boolean> truthValues) {
        if(this instanceof AtomicExpression)
            return truthValues.get(this.expression.charAt(0));

        else if(this instanceof NegationExpression)
            return !((NegationExpression) this).getW().evaluate(truthValues);

        else if(this instanceof BinaryExpression) {

            switch(this.operator) {
                case '&':
                    return ((BinaryExpression) this).getW1().evaluate(truthValues) && ((BinaryExpression) this).getW2().evaluate(truthValues);
                case 'v':
                    return ((BinaryExpression) this).getW1().evaluate(truthValues) || ((BinaryExpression) this).getW2().evaluate(truthValues);
                case '>':
                    return !((BinaryExpression) this).getW1().evaluate(truthValues) || ((BinaryExpression) this).getW2().evaluate(truthValues);
            }
        }
        return false;
    }
}
