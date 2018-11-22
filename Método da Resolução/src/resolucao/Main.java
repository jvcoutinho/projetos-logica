package resolucao;

import resolucao.Generator.Generator;
import resolucao.Generator.GrammarBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

/** Método da Resolução
 *  Este projeto consiste em resolver instâncias do problema SAT na Forma Normal Conjuntiva,
 *  pelo Método da Resolução.
 *
 * @author João Victor de Sá Ferraz Coutinho (jvsfc@cin.ufpe.br)
 */

public class Main {

    public static void main(String[] args) {
        try {

            /* Entrada. O argumento do método é o número de expressões. */
            if(args.length == 0)
                generateInput(1000);
            else if(!args[0].equals("--solve"))
                generateInput(Integer.parseInt(args[0]));

            /* Saída. */
           // generateOutput();

        } catch (FileNotFoundException e) {
            System.out.println("Abertura de arquivo mal sucedida!");
        }
    }

    private static void generateInput(int numOfExpressions) throws FileNotFoundException {
        PrintWriter input = new PrintWriter(new File("Entrada.in"));
        input.println(numOfExpressions);
        Generator problemGenerator = new Generator(GrammarBuilder.generateFNCExpressionsGrammar());

        for (int i = 0; i < numOfExpressions; i++)
            input.println(problemGenerator.generateProblem());
        input.close();
    }
}
