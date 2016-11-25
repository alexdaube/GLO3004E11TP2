package ca.ulaval.glo3004.tp1.e2;

public class Voiture {
	private String from = "Voiture ";
	private String etat = "";

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Voiture(String provenance) {
		etat = "arrive";
		this.from = this.from + provenance + ": ";
	}

	public void printEtat() {
		System.out.println(this.from + this.etat);
	}
}
