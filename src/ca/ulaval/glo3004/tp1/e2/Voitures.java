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
	private static final String IMPOSSIBLE_ATTENDRE = "Impossible d'attendre à partir ";

	private Queue<Voiture> estVoitures = new LinkedList<Voiture>();
	private Queue<Voiture> ouestVoitures = new LinkedList<Voiture>();
	private Queue<Voiture> sudVoitures = new LinkedList<Voiture>();

	private List<String> direction = Arrays.asList("est", "ouest", "sud", "tous");

	public boolean faireActionVoiture(Commande commande, Intersection intersection)
			throws CommandeNonCompleteException {

		if (commande.getAction().equals("arrive")) {
			creerVoiture(commande.getDirection());
			return true;
		}
		if (commande.getAction().equals("tourneDroite")) {
			return tournerADroite(commande, intersection);
		}
		if (commande.getAction().equals("tourneGauche")) {
			return tournerAGauche(commande, intersection);
		}
		if (commande.getAction().equals("continue")) {
			return continuer(commande, intersection);
		}
		return false;
	}

	public void mettreVoitureEstEnAttente() {
		for (Voiture voiture : estVoitures) {
			voiture.setEtat("attente");
		}
	}

	public void mettreVoitureOuestEnAttente() {
		for (Voiture voiture : ouestVoitures) {
			voiture.setEtat("attente");
		}
	}

	public void mettreVoitureSudEnAttente() {
		for (Voiture voiture : sudVoitures) {
			voiture.setEtat("attente");
		}
	}

	public void voitureEstContinue() {
		for (Voiture voiture : estVoitures) {
			voiture.setEtat("continue");
		}
	}

	public void voitureEstTourneGauche() {
		for (Voiture voiture : estVoitures) {
			voiture.setEtat("tourne gauche");
		}
	}

	public void voitureOuestContinue() {
		for (Voiture voiture : ouestVoitures) {
			voiture.setEtat("continue");
		}
	}

	public void voitureOuestTourneDroite() {
		for (Voiture voiture : ouestVoitures) {
			voiture.setEtat("tourne droite");
		}
	}

	public void voitureSudTourneDroite() {
		for (Voiture voiture : sudVoitures) {
			voiture.setEtat("tourne droite");
		}
	}

	public void voitureSudTourneGauche() {
		for (Voiture voiture : sudVoitures) {
			voiture.setEtat("tourne gauche");
		}
	}

	private boolean tournerADroiteOuest(Intersection intersection) throws CommandeNonCompleteException {
		switch (intersection.getEtatIntersection()) {
		case VVR:
		case RVR:
			return true;
		default:
			return false;
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
			return false;
		}
	}

	private boolean tournerAGaucheEst(Intersection intersection) throws CommandeNonCompleteException {
		switch (intersection.getEtatIntersection()) {
		case VRR:
		case CRV:
		case CRR:
		case CRJ:
			return true;
		default:
			return false;
		}
	}

	private boolean tournerAGaucheSud(Intersection intersection) throws CommandeNonCompleteException {
		switch (intersection.getEtatIntersection()) {
		case RRV:
		case RRC:
			return true;
		default:
			return false;
		}
	}

	private boolean tournerADroite(Commande commande, Intersection intersection) throws CommandeNonCompleteException {
		verifieVoituresPresente(commande, IMPOSSIBLE_DROITE);
		if (commande.getDirection().equals("est")) {
			throw new CommandeNonCompleteException(IMPOSSIBLE_DROITE + "de l'est.");
		}
		if (commande.getDirection().equals("ouest")) {
			return tournerADroiteOuest(intersection);
		}
		if (commande.getDirection().equals("sud")) {
			return tournerADroiteSud(intersection);
		}
		return false;
	}

	private boolean tournerAGauche(Commande commande, Intersection intersection) throws CommandeNonCompleteException {
		verifieVoituresPresente(commande, IMPOSSIBLE_GAUCHE);
		if (commande.getDirection().equals("est")) {
			return tournerAGaucheEst(intersection);

		}
		if (commande.getDirection().equals("ouest")) {
			throw new CommandeNonCompleteException(IMPOSSIBLE_GAUCHE + "de l'ouest.");
		}
		if (commande.getDirection().equals("sud")) {
			return tournerAGaucheSud(intersection);
		}
		return false;
	}

	private boolean continuer(Commande commande, Intersection intersection) throws CommandeNonCompleteException {
		verifieVoituresPresente(commande, IMPOSSIBLE_CONTINUE);
		if (commande.getDirection().equals("est")) {
			return continuerDeEst(intersection);
		}
		if (commande.getDirection().equals("ouest")) {
			return continuerDeOuest(intersection);

		}
		if (commande.getDirection().equals("sud")) {
			throw new CommandeNonCompleteException(IMPOSSIBLE_CONTINUE + "du sud.");
		}
		return false;
	}

	private boolean continuerDeEst(Intersection intersection) throws CommandeNonCompleteException {
		switch (intersection.getEtatIntersection()) {
		case CRV:
		case CRR:
		case VRV:
		case VVR:
		case VRR:
		case VRJ:
			return true;
		default:
			return false;
		}
	}

	private boolean continuerDeOuest(Intersection intersection) throws CommandeNonCompleteException {
		switch (intersection.getEtatIntersection()) {
		case RVR:
		case VVR:
			return true;
		default:
			return false;
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
		if (ouestVoitures.isEmpty()) {
			throw new CommandeNonCompleteException(AUCUNE_VOITURE_MESSAGE + "de l'ouest. " + message);
		}
	}

	private void verifieVoiturePresenteSud(String message) throws CommandeNonCompleteException {
		if (sudVoitures.isEmpty()) {
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
