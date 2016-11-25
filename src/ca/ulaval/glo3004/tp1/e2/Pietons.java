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

    private void creerPieton(){
        Pieton pieton = new Pieton();
        pietons.add(pieton);
    }

    public void faireActionPieton(Commande commande, Intersection intersection) throws CommandeNonCompleteException{
        if (commande.getAction().equals("arrive")) {
            creerPieton();
        }
        if (commande.getAction().equals("attend")) {
        	verifiePietonPresent();
        	pietons.peek().setEtat("attend");
        }
        if (commande.getAction().equals("traverse")) {
            faireTraverser(intersection);
        }
    }

    private void faireTraverser(Intersection intersection) throws CommandeNonCompleteException {
        verifiePietonPresent();
        peutTraverser(intersection);
        pietons.poll();
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
}
