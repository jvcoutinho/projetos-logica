package resolucao.Generator;

import resolucao.Grammar.Grammar;
import java.util.Random;

public class Generator {

    private Grammar fncExpressionsGrammar;
    private Random rand = new Random();

    private static final int complexityThreshold = 30;

    public Generator(Grammar fncExpressionsGrammar) {
        this.fncExpressionsGrammar = fncExpressionsGrammar;
    }

    public String generateProblem() {
        return fncExpressionsGrammar.generateWord(fncExpressionsGrammar.getS(), rand.nextInt(complexityThreshold));
    }

}
