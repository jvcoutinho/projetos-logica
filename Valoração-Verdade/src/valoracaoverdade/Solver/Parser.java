package valoracaoverdade.Solver;

import valoracaoverdade.Expression.*;

final class Parser {

    private Lexer lexer;
    private char nextToken;

    Expression parseExpression(String expression) {
        lexer = new Lexer(expression);
        nextToken = lexer.getNextToken();

        return S();
    }

    /*
        Gramática LL1 Livre-de-Contexto das Expressões Legítimas da Lógica Proposicional
        S -> Atômicas | (C
        C -> ~S) | S B
        B -> & S) | v S) | > S)
     */
    private Expression S() {

        if (nextToken >= 'A' && nextToken <= 'Z') {
            char token = nextToken;
            consume(nextToken);
            return new AtomicExpression("" + token);
        } else if (nextToken == '(') {
            consume('(');
            return C();
        } else
            return error();

    }

    private Expression C() {

        if(nextToken == '~') {
            consume('~');
            Expression exp = S();
            consume(')');
            return new NegationExpression(exp);
        } else if(nextToken == '(') {
            Expression w1 = S();
            consume(' ');
            return B(w1);
        } else if(nextToken >= 'A' && nextToken <= 'Z') {
            Expression w1 = S();
            consume(' ');
            return B(w1);
        } else
            return error();
    }

    private Expression B(Expression w1) {
        Expression w2;
        switch (nextToken) {
            case '&':
                consume('&');
                consume(' ');
                w2 = S();
                consume(')');
                return new BinaryExpression(w1, w2, '&');

            case 'v':
                consume('v');
                consume(' ');
                w2 = S();
                consume(')');
                return new BinaryExpression(w1, w2, 'v');

            case '>':
                consume('>');
                consume(' ');
                w2 = S();
                consume(')');
                return new BinaryExpression(w1, w2, '>');

            default:
                return error();
        }
    }

    private void consume(char token) {
        if(nextToken == token && lexer.hasNextToken()) {
            nextToken = lexer.getNextToken();
        } else
            nextToken = '$';
    }

    private Expression error() {
        return null;
    }

}
