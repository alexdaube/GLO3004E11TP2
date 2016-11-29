package ca.ulaval.glo3004.tp1.e2;

import java.util.LinkedList;
import java.util.Queue;

public class Pietons {
    private Queue<Pieton> pietons = new LinkedList<Pieton>();

    public Queue<Pieton> getPietons() {
        return pietons;
    }

    public void printEtatPietons() {
        if (pietons.isEmpty()) {
            System.out.println("Pieton: aucun");
        } else {
            pietons.peek().printEtat();
        }
    }

    public void ajouterPieton() {
        creerPieton();
    }

    private void creerPieton() {
        Pieton pieton = new Pieton();
        pietons.add(pieton);
    }

    public boolean faireActionPieton(Commande commande, Intersection intersection) throws CommandeNonCompleteException {
        if (commande.getAction().equals("arrive")) {
            creerPieton();
            return true;
        }
        if (commande.getAction().equals("traverse")) {
            if (faireTraverser(intersection)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean faireTraverser(Intersection intersection) throws CommandeNonCompleteException {
        verifiePietonPresent();
        return peutTraverser(intersection);
    }

    private boolean peutTraverser(Intersection intersection) throws CommandeNonCompleteException {
        switch (intersection.getEtatIntersection()) {
            case RRR:
            case PPP:
                return true;
            default:
                throw new CommandeNonCompleteException("Ne peut pas traverser.");
        }
    }

    private void verifiePietonPresent() throws CommandeNonCompleteException {
        if (pietons.isEmpty()) {
            throw new CommandeNonCompleteException("Aucun pieton présent.");
        }
    }

    public void mettrePietonTraverse() {
        for (Pieton pieton : pietons){
            pieton.setEtat("traverse");
        }
    }

    public void mettrePietonAttente() {
        for (Pieton pieton : pietons){
            pieton.setEtat("attend");
        }
    }
}
