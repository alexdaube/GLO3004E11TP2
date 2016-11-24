package ca.ulaval.glo3004.tp1.e2;

public class Voiture {
	private String from = "Voiture ";
    private String destination = "DESTINATION-";
    private String etat = "";
    
    public void setEtat(String etat) {
    	this.etat = etat;
    }

    public Voiture(String provenance) {
        etat = "arrive";
        this.from = this.from  + provenance + ": ";
    }

    public void tournerDroite() {
        System.out.println(this.from + " toune a droite...");
    }

    public void tournerGauche() {
        System.out.println(this.from + " toune a gauche...");
    }

    public void continuer() {
        System.out.println(this.from + " continue...");
    }

    public void printEtat() {
        System.out.println(this.from + this.etat);
    }
}
