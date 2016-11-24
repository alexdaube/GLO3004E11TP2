package ca.ulaval.glo3004.tp1.e2;

public class Voiture {
	private String from = "Voiture ";
    private String destination = "DESTINATION-";
    private String etat = "";


    public Voiture(String provenance) {
        etat = "arrive";
        this.from = this.from  + provenance + ": ";
    }


    public void start() {
        System.out.println(this.from + " avec " + this.destination + " est creee");
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

    public void attendreDroitPassage() throws InterruptedException {
        System.out.println(this.from + this.etat);
        this.wait(5);
    }

    public void printEtat() {
        System.out.println(this.from + this.etat);
    }
}
