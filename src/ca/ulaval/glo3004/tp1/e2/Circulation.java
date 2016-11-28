package ca.ulaval.glo3004.tp1.e2;

import java.util.List;

public class Circulation extends Thread {
    private Voitures voitures;
    private Pietons pietons;
    private Intersection intersection;
    private List<String> commandes;

    public Circulation(List<String> commandes) {
        intersection = new Intersection();
        voitures = new Voitures();
        pietons = new Pietons();
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
    }

    private void printEtatSysteme() {
        System.out.println("\nVoici l'état courant du système:");
        intersection.printEtatLumieres();
        voitures.printEtatVoitures();
        pietons.printEtatPietons();
        System.out.println("\n");
    }
}
