package ca.ulaval.glo3004.tp1.e2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Voitures {
	private static final String AUCUNE_VOITURE_MESSAGE = "Il n'y a pas de voiture dans la voie ";
    private static final String IMPOSSIBLE_DROITE = "Impossible de tourner à droite à partir ";
    private static final String IMPOSSIBLE_GAUCHE = "Impossible de tourner à gauche à partir ";
    private static final String IMPOSSIBLE_CONTINUE = "Impossible de continuer à partir ";

    private Queue<Voiture> estVoitures = new LinkedList<Voiture>();
    private Queue<Voiture> ouestVoitures = new LinkedList<Voiture>();
    private Queue<Voiture> sudVoitures = new LinkedList<Voiture>();

    private List<String> direction = Arrays.asList("est", "ouest", "sud", "tous");

    public Queue<Voiture> getEstVoitures() {
        return estVoitures;
    }

    public Queue<Voiture> getOuestVoitures() {
        return ouestVoitures;
    }

    public Queue<Voiture> getSudVoitures() {
        return sudVoitures;
    }

    public void faireActionVoiture(Commande commande, Intersection intersection) throws CommandeNonCompleteException {
        if (commande.getAction().equals("arrive")) {
            creerVoiture(commande.getDirection());
        }
        if (commande.getAction().equals("attendV")) {
            // Doit checker si il y a une voiture dans la queue approprié
            // Change le status de la voiture pour attend si oui, si non throw l'erreur
        }
        if (commande.getAction().equals("tourneDroite")) {
            tournerADroite(commande, intersection);
        }
        if (commande.getAction().equals("tourneGauche")) {
            tournerAGauche(commande, intersection);
        }
        if (commande.getAction().equals("continue")) {
            continuer(commande, intersection);
        }
    }


    private void tournerADroite(Commande commande, Intersection intersection) throws CommandeNonCompleteException {
        verifieVoituresPresente(commande, IMPOSSIBLE_DROITE);
        if (commande.getDirection().equals("est")) {
            throw new CommandeNonCompleteException(IMPOSSIBLE_DROITE + "de l'est.");
        }
        if (commande.getDirection().equals("ouest")) {
            tournerADroiteOuest(intersection);
        }
        if (commande.getDirection().equals("sud")) {
            tournerADroiteSud(intersection);
        }
    }

    private boolean tournerADroiteOuest(Intersection intersection) throws CommandeNonCompleteException {
        switch (intersection.getEtatIntersection()) {
            case VVR:
            case RVR:
                return true;
            default:
                throw new CommandeNonCompleteException(IMPOSSIBLE_DROITE + "de l'ouest.");
        }
    }

    private boolean tournerADroiteSud(Intersection intersection) throws CommandeNonCompleteException {
        switch (intersection.getEtatIntersection()) {
            case RRC:
            case CRV:
            case VRV:
            case RRV:
                return true;
            default:
                throw new CommandeNonCompleteException(IMPOSSIBLE_DROITE + "du sud.");
        }
    }

    private void tournerAGauche(Commande commande, Intersection intersection) throws CommandeNonCompleteException {
        verifieVoituresPresente(commande, IMPOSSIBLE_GAUCHE);
        if (commande.getDirection().equals("est")) {
            tournerAGaucheEst(intersection);
        }
        if (commande.getDirection().equals("ouest")) {
            throw new CommandeNonCompleteException(IMPOSSIBLE_GAUCHE + "de l'ouest.");
        }
        if (commande.getDirection().equals("sud")) {
            tournerAGaucheSud(intersection);
        }
    }

    private boolean tournerAGaucheEst(Intersection intersection) throws CommandeNonCompleteException {
        switch (intersection.getEtatIntersection()) {
            case VRR:
            case CRV:
            case CRR:
                return true;
            default:
                throw new CommandeNonCompleteException(IMPOSSIBLE_GAUCHE + "de l'est.");
        }
    }

    private boolean tournerAGaucheSud(Intersection intersection) throws CommandeNonCompleteException {
        switch (intersection.getEtatIntersection()) {
            case RRV:
            case RRC:
                return true;
            default:
                throw new CommandeNonCompleteException(IMPOSSIBLE_GAUCHE + "du sud.");
        }
    }


    private void continuer(Commande commande, Intersection intersection) throws CommandeNonCompleteException {
        verifieVoituresPresente(commande, IMPOSSIBLE_CONTINUE);
        if (commande.getDirection().equals("est")) {
            continuerDeEst(intersection);
        }
        if (commande.getDirection().equals("ouest")) {
            continuerDeOuest(intersection);
        }
        if (commande.getDirection().equals("sud")) {
            throw new CommandeNonCompleteException(IMPOSSIBLE_CONTINUE + "du sud.");
        }
    }


    private boolean continuerDeEst(Intersection intersection) throws CommandeNonCompleteException {
        switch (intersection.getEtatIntersection()) {
            case CRV:
            case CRR:
            case VRV:
            case VVR:
            case VRR:
                return true;
            default:
                throw new CommandeNonCompleteException(IMPOSSIBLE_CONTINUE + "de l'est.");
        }
    }

    private boolean continuerDeOuest(Intersection intersection) throws CommandeNonCompleteException {
        switch (intersection.getEtatIntersection()) {
            case RVR:
            case VVR:
                return true;
            default:
                throw new CommandeNonCompleteException(IMPOSSIBLE_CONTINUE + "de l'ouest.");
        }
    }

    private void verifieVoituresPresente(Commande commande, String message) throws CommandeNonCompleteException {
        if (commande.getDirection().equals("est")) {
            verifieVoiturePresenteEst(message);
        }
        if (commande.getDirection().equals("ouest")) {
            verifieVoiturePresenteOuest(message);
        }
        if (commande.getDirection().equals("sud")) {
            verifieVoiturePresenteSud(message);
        }
    }

    private void verifieVoiturePresenteEst(String message) throws CommandeNonCompleteException {
        if (estVoitures.isEmpty()) {
            throw new CommandeNonCompleteException(AUCUNE_VOITURE_MESSAGE + "de l'est. " + message);
        }
    }

    private void verifieVoiturePresenteOuest(String message) throws CommandeNonCompleteException {
        if (estVoitures.isEmpty()) {
            throw new CommandeNonCompleteException(AUCUNE_VOITURE_MESSAGE + "de l'ouest. " + message);
        }
    }

    private void verifieVoiturePresenteSud(String message) throws CommandeNonCompleteException {
        if (estVoitures.isEmpty()) {
            throw new CommandeNonCompleteException(AUCUNE_VOITURE_MESSAGE + "du sud. " + message);
        }
    }

    public void printEtatVoitures() {
        printEtatVoitureDeListe(estVoitures, "est");
        printEtatVoitureDeListe(ouestVoitures, "ouest");
        printEtatVoitureDeListe(sudVoitures, "sud");
    }

    private void printEtatVoitureDeListe(Queue<Voiture> listeVoitures, String direction) {
        if (listeVoitures.isEmpty()) {
            System.out.println("Voiture " + direction + ": aucune");
        } else {
            listeVoitures.peek().printEtat();
        }
    }

    private void creerVoiture(String direction) {
        Voiture voiture = new Voiture(direction);
        if (direction.equals("est")) {
            estVoitures.add(voiture);
        }
        if (direction.equals("ouest")) {
            ouestVoitures.add(voiture);
        }
        if (direction.equals("sud")) {
            sudVoitures.add(voiture);
        }
    }
}
