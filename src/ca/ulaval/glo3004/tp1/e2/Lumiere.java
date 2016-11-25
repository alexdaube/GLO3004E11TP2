package ca.ulaval.glo3004.tp1.e2;

public class Lumiere {
	private CouleurLumiere couleur;

	public String getCouleur() {
		return this.couleur.toString().toLowerCase();
	}

	public CouleurLumiere getCouleurEnum() {
		return this.couleur;
	}

	public Lumiere() {
		this.couleur = CouleurLumiere.ROUGE;
	}

	public void changerPour(CouleurLumiere couleurLumiere) {
		this.couleur = couleurLumiere;
	}
}
