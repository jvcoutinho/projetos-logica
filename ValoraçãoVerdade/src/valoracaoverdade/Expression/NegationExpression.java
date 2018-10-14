package valoracaoverdade.Expression;

public class NegationExpression extends Expression {
    private Expression w;

    public NegationExpression(Expression w) {
        super("(~" + w + ")");
        this.w = w;
        this.operator = '~';
    }

    public Expression getW() {
        return w;
    }
}
