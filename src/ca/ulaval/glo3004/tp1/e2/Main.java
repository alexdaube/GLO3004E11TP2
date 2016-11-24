package ca.ulaval.glo3004.tp1.e2;

import java.io.File;
import java.util.InputMismatchException;
import java.util.List;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws FileNotFoundException {



        File file = new File("sample.txt");
        Parser parser = new Parser();
        List<List<String>> simulations = parser.parse(file, "txt");

        int numeroTest = getDemanderNumeroTest(simulations);

        Circulation circulation = new Circulation();

        for (String action : simulations.get(numeroTest)) {
            Commande commande = new Commande(action);
            circulation.execute(commande);
        }
     }

     private static int getDemanderNumeroTest(List<List<String>> simulations) {
         int numeroTest = 0;
         boolean valeurNonEnregistre = true;
         Scanner reader = new Scanner(System.in);
         while (valeurNonEnregistre) {
             try{
                 System.out.println("Entrez le numéro du test à rouler: ");
                 numeroTest = reader.nextInt();
                 if (numeroTest >= 0 && numeroTest < simulations.size()) {
                     valeurNonEnregistre = false;
                 } else {
                     System.out.println("La valeur entrée doit être entre 0 et " + (simulations.size() - 1));
                 }
             } catch (InputMismatchException e) {
                 System.out.println("La valeur entrée doit être une valeur numérique entière positive.");
             } finally {
                 reader.close();
             }
         }
         return numeroTest;
     }
}
