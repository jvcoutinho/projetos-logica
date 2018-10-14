package valoracaoverdade;

import valoracaoverdade.Generator.Generator;
import valoracaoverdade.Generator.GrammarBuilder;
import valoracaoverdade.Solver.Solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {

            /* Entrada. O argumento do método é o número de expressões. */
            if(args.length == 0)
                generateInput(1000);
            else if(!args[0].equals("--solve"))
                generateInput(Integer.parseInt(args[0]));

            /* Saída. */
            generateOutput();

        } catch (FileNotFoundException e) {
            System.out.println("Abertura de arquivo mal sucedida!");
        }
    }

    private static void generateInput(int numOfExpressions) throws FileNotFoundException {
        PrintWriter input = new PrintWriter(new File("Entrada.in"));
        input.println(numOfExpressions);
        Generator problemGenerator = new Generator(
                GrammarBuilder.generateLegitExpressionsGrammar(),
                GrammarBuilder.generateNonLegitExpressionsGrammar());

        Random rand = new Random();
        for (int i = 0; i < numOfExpressions; i++) {
            double isSet = rand.nextDouble();
            input.println(problemGenerator.generateProblem(isSet));
        }
        input.close();
    }

    private static void generateOutput() throws FileNotFoundException {
        Scanner input = new Scanner(new File("Entrada.in"));
        PrintWriter output = new PrintWriter(new File("SaidaVerdadeira.out"));
        Solver problemSolver = new Solver();

        int numExpressions = Integer.parseInt(input.nextLine());
        for(int i = 0; i < numExpressions; i++) {
            output.println("Problema #" + (i + 1));
            output.println(problemSolver.solve(input.nextLine()));
            if(i < numExpressions - 1)
                output.println();
        }

        input.close();
        output.close();
    }
}
