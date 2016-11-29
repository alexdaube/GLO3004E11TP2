package ca.ulaval.glo3004.tp1.e2;

import java.util.ArrayList;
import java.util.List;

public class Intersection extends Thread {
	public Voitures voitures;
	public Pietons pietons;
	private Lumiere lumiereEst;
	private Lumiere lumiereOuest;
	private Lumiere lumiereSud;
	private ValidateurEtatLumiere validateurEtatLumiere;
	public List<Commande> commandeVoitureEnAttente = new ArrayList<>();

	public Intersection() {
		this.lumiereEst = new Lumiere();
		this.lumiereOuest = new Lumiere();
		this.lumiereSud = new Lumiere();
		voitures = new Voitures();
		pietons = new Pietons();
		this.validateurEtatLumiere = new ValidateurEtatLumiere();
	}

	public EtatIntersection getEtatIntersection() {
		String etatEst = lumiereEst.getCouleur().substring(0, 1);
		String etatOuest = lumiereOuest.getCouleur().substring(0, 1);
		String etatSud = lumiereSud.getCouleur().substring(0, 1);
		String etat = etatEst.concat(etatOuest).concat(etatSud).toUpperCase();
		return EtatIntersection.valueOf(etat);
	}

	public void changerLumieres(Commande commande) throws CommandeNonCompleteException {
		if (commande.getDirection().equals("est")) {
			handleDirectionEst(commande.getAction());
		}
		if (commande.getDirection().equals("ouest")) {
			handleDirectionOuest(commande.getAction());
		}
		if (commande.getDirection().equals("sud")) {
			handleDirectionSud(commande.getAction());
		}
		if (commande.getDirection().equals("tous")) {
			handleDirectionTous(commande.getAction());
		}
	}

	private void handleDirectionEst(String action) throws CommandeNonCompleteException {
		CouleurLumiere couleurLumiere = CouleurLumiere.valueOf(action.toUpperCase());
		validateurEtatLumiere.validateEst(couleurLumiere, getEtatIntersection());
		this.lumiereEst.changerPour(couleurLumiere);
	}

	private void handleDirectionOuest(String action) throws CommandeNonCompleteException {
		CouleurLumiere couleurLumiere = CouleurLumiere.valueOf(action.toUpperCase());
		validateurEtatLumiere.validateOuest(couleurLumiere, getEtatIntersection());
		this.lumiereOuest.changerPour(couleurLumiere);
	}

	private void handleDirectionSud(String action) throws CommandeNonCompleteException {
		CouleurLumiere couleurLumiere = CouleurLumiere.valueOf(action.toUpperCase());
		validateurEtatLumiere.validateSud(couleurLumiere, getEtatIntersection());
		this.lumiereSud.changerPour(couleurLumiere);
	}

	private void handleDirectionTous(String action) throws CommandeNonCompleteException {
		CouleurLumiere couleurLumiere = CouleurLumiere.valueOf(action.toUpperCase());
		validateurEtatLumiere.validateTous(couleurLumiere, getEtatIntersection());
		lumiereEst.changerPour(couleurLumiere);
		lumiereOuest.changerPour(couleurLumiere);
		lumiereSud.changerPour(couleurLumiere);
	}

	public boolean recoitCommandePieton(Commande commande) {
		try {
			if (this.pietons.faireActionPieton(commande, this)) {
				this.pietons.mettrePietonTraverse();
				return true;
			} else {
				this.pietons.mettrePietonAttente();
				return false;
			}
		} catch (CommandeNonCompleteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean recoitCommandeVoiture(Commande commande) {
		try {
			if (this.voitures.faireActionVoiture(commande, this)) {
				if (!commande.getAction().equals("arrive")) {
					switch (commande.getDirection()) {
					case "est":
						this.traiterVoitureEst(commande.getAction());
						break;
					case "ouest":
						this.traiterVoitureOuest(commande.getAction());
						break;
					case "sud":
						this.traiterVoitureSud(commande.getAction());
						break;
					}
				}
				return true;
			} else {
				if (commande.getDirection().equals("est")) {
					voitures.mettreVoitureEstEnAttente();
				} else if (commande.getDirection().equals("ouest")) {
					voitures.mettreVoitureOuestEnAttente();
				} else if (commande.getDirection().equals("sud")) {
					voitures.mettreVoitureSudEnAttente();
				}
				return false;
			}
		} catch (CommandeNonCompleteException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void traiterVoitureOuest(String action) {
		switch (action) {
		case "continue":
			voitures.voitureOuestContinue();
			break;
		case "tourneDroite":
			voitures.voitureOuestTourneDroite();
			break;
		}
	}

	private void traiterVoitureEst(String action) {
		switch (action) {
		case "continue":
			voitures.voitureEstContinue();
			break;
		case "tourneGauche":
			voitures.voitureEstTourneGauche();
			break;
		}
	}

	private void traiterVoitureSud(String action) {
		switch (action) {
		case "tourneGauche":
			voitures.voitureSudTourneGauche();
			break;
		case "tourneDroite":
			voitures.voitureSudTourneDroite();
			break;
		}
	}

	public void printEtatLumieres() {
		System.out.println("Lumiere ouest: " + this.lumiereOuest.getCouleur());
		System.out.println("Lumiere est: " + this.lumiereEst.getCouleur());
		System.out.println("Lumiere sud: " + this.lumiereSud.getCouleur());
	}

	public List<Commande> getCommandesAttente() {
		return this.commandeVoitureEnAttente;
	}

}
