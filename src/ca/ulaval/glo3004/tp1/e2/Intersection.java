package ca.ulaval.glo3004.tp1.e2;

public class Intersection {
	private Lumiere lumiereEst;
	private Lumiere lumiereOuest;
	private Lumiere lumiereSud;
	private ValidateurEtatLumiere validateurEtatLumiere;

	public Intersection() {
		this.lumiereEst = new Lumiere();
		this.lumiereOuest = new Lumiere();
		this.lumiereSud = new Lumiere();
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

	public void printEtatLumieres() {
		System.out.println("Lumiere ouest: " + this.lumiereOuest.getCouleur());
		System.out.println("Lumiere est: " + this.lumiereEst.getCouleur());
		System.out.println("Lumiere sud: " + this.lumiereSud.getCouleur());
	}
}
