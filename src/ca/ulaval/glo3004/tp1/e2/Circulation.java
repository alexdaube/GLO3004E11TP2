package ca.ulaval.glo3004.tp1.e2;

import java.util.ArrayList;
import java.util.List;

public class Circulation extends Thread {

	private Intersection intersection;
	private List<String> commandes;
	private List<Commande> commandesEnAttente = new ArrayList<>();

	public Circulation(List<String> commandes) {
		intersection = new Intersection();

		this.commandes = commandes;
	}

	public void run() {
		for (String action : this.commandes) {
			Commande commande = new Commande(action);
			this.execute(commande);
			printEtatSysteme();
		}
	}

	public void execute(Commande commande) {
		List<Commande> successfull = new ArrayList<>();
		System.out.println("Commande en cours: " + commande.reconstruireLaCommande());

		try {
			if (commande.estPourLumiere()) {
				intersection.changerLumieres(commande);
				if (!commandesEnAttente.isEmpty()) {
					for (Commande com : commandesEnAttente) {
						if (com.estPourVoiture()) {
							if (intersection.recoitCommandeVoiture(com)) {
								successfull.add(com);
							}
						} else if (com.estPourPieton()) {
							if (intersection.recoitCommandePieton(com)) {
								successfull.add(com);
							}
						}
					}
				}

				System.out.println("La commande a été effectuée avec succès!");

			} else if (commande.estPourPieton()) {
				if (!intersection.recoitCommandePieton(commande)) {
					commandesEnAttente.add(commande);
				} else {
					System.out.println("La commande a été effectuée avec succès!");
				}

			} else if (commande.estPourVoiture()) {
				if (!intersection.recoitCommandeVoiture(commande)) {
					commandesEnAttente.add(commande);
				} else {
					System.out.println("La commande a été effectuée avec succès!");
				}
			} else {
				throw new CommandeNonCompleteException("Commande invalide : " + commande.reconstruireLaCommande());
			}

		} catch (CommandeNonCompleteException e) {
			System.out.println("La commande n'a pas été effectuée avec succès.");
			System.out.println(e.getMessage());
		}
		if (!successfull.isEmpty()) {
			printCommandeReussies(successfull);
		}
	}

	private void printCommandeReussies(List<Commande> commandeReussie) {
		for (Commande com : commandeReussie) {
			if (com.estPourVoiture()) {
				System.out.println("\nLes voitures bougent!");
				System.out.println("La commande " + com.reconstruireLaCommande() + " a été effectuée");
			} else if (com.estPourPieton()) {
				System.out.println("\nLes pietons bougent!");
				System.out.println("La commande " + com.reconstruireLaCommande() + " a été effectuée");
			}
		}
		commandesEnAttente.removeAll(commandeReussie);
	}

	private void printEtatSysteme() {
		System.out.println("\nVoici l'état courant du système:");
		intersection.printEtatLumieres();
		intersection.voitures.printEtatVoitures();
		intersection.pietons.printEtatPietons();
		System.out.println("\n");
	}
}
