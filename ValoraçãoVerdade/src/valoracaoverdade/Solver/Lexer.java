package valoracaoverdade.Solver;

final class Lexer {

    private String input;
    private int index;

    Lexer(String input) {
        this.input = input;
        index = 0;
    }

    void setInput(String input) {
        this.input = input;
        index = 0;
    }

    char getNextToken() {
        char token = input.charAt(index);
        index++;
        return token;
    }

    boolean hasNextToken() {
        return index < input.length();
    }

}
