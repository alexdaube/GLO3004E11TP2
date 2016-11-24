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
        try {
            if(commande.estPourLumiere()) {
                // if(commande.estPourLumierePieton()) ...
                intersection.changerLumieres(commande);
            }
            else if (commande.estPourPieton()) {
                voitures.faireActionVoiture(commande, intersection);
            }
            else if (commande.estPourVoiture()) {
                pietons.faireActionPieton(commande, intersection);
            }
            else {
                System.out.println("Commande invalide : " + commande.reconstruireLaCommande());
            }
        } catch (CommandeNonCompleteException e) {
            System.out.println(e.getMessage());
        }
        printEtatSysteme();
    }

    private void printEtatSysteme() {
        System.out.println("Voici l'état courant du système:");
        intersection.printEtatLumieres();
        voitures.printEtatVoitures();
        pietons.printEtatPietons();
    }
}
