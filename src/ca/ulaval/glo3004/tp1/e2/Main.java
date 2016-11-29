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
		Scanner reader = new Scanner(System.in);
		boolean programmeRoule = true;

		while (programmeRoule) {
			int numeroTest = getDemanderNumeroTest(simulations, reader);

			Circulation circulation = new Circulation(simulations.get(numeroTest));
			circulation.start();

			try {
				circulation.join();
			} catch (InterruptedException e) {
				programmeRoule = demanderSortie(reader);
			}
			programmeRoule = demanderSortie(reader);
		}

		System.out.println("L'application ne roule plus! Ciao!");
		reader.close();
		System.exit(0);
	}

	private static boolean demanderSortie(Scanner reader) {
		System.out.println(
				"'q' ou 'quitter' pour sortir de l'application. Appuyez sur une autre touche pour continuer : ");
		try {
			reader.nextLine();
			String input = reader.nextLine().toLowerCase();
			return (input.equals("quitter") || input.equals("q")) ? false : true;
		} catch (Exception e) {
			return true;
		}
	}

	private static int getDemanderNumeroTest(List<List<String>> simulations, Scanner reader) {
		int numeroTest = 0;
		boolean valeurNonEnregistre = true;
		while (valeurNonEnregistre) {
			try {
				System.out.println(
						"Entrez le numéro du test à rouler (Chiffre entre 0 et " + (simulations.size() - 1) + "): ");
				numeroTest = reader.nextInt();
				if (numeroTest >= 0 && numeroTest < simulations.size()) {
					valeurNonEnregistre = false;
				} else {
					System.out.println("La valeur entrée doit être entre 0 et " + (simulations.size() - 1));
				}
			} catch (InputMismatchException e) {
				System.out.println("La valeur entrée doit être une valeur numérique entière positive.");
				reader.next();
			}
		}
		return numeroTest;
	}
}
