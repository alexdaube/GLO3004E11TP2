package ca.ulaval.glo3004.tp1.e2;

public class Circulation {
	private Voitures voitures;
	private Pietons pietons;
	private Intersection intersection;

	public Circulation() {
		intersection = new Intersection();
		voitures = new Voitures();
		pietons = new Pietons();
	}

	public void execute(Commande commande) {
		System.out.println("Commande en cours: " + commande.reconstruireLaCommande());
		try {
			if (commande.estPourLumiere()) {
				intersection.changerLumieres(commande);
			} else if (commande.estPourPieton()) {
				pietons.faireActionPieton(commande, intersection);
			} else if (commande.estPourVoiture()) {
				voitures.faireActionVoiture(commande, intersection);
			} else {
				throw new CommandeNonCompleteException("Commande invalide : " + commande.reconstruireLaCommande());
			}
			System.out.println("La commande a été effectuée avec succès!");
		} catch (CommandeNonCompleteException e) {
			System.out.println("La commande n'a pas été effectuée avec succès.");
			System.out.println(e.getMessage());
		}
		printEtatSysteme();
	}

	private void printEtatSysteme() {
		System.out.println("\nVoici l'état courant du système:");
		intersection.printEtatLumieres();
		voitures.printEtatVoitures();
		pietons.printEtatPietons();
		System.out.println("\n");
	}
}
