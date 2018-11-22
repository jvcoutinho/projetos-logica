package valoracaoverdade.Expression;

public class BinaryExpression extends Expression {
    private Expression w1;
    private Expression w2;

    public BinaryExpression(Expression w1, Expression w2, char operator) {
        super("(" + w1 + " " + operator + " " + w2 + ")");
        this.w1 = w1;
        this.w2 = w2;
        this.operator = operator;
    }

    public Expression getW1() {
        return w1;
    }

    public Expression getW2() {
        return w2;
    }

}
