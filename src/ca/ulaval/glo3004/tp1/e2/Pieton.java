package ca.ulaval.glo3004.tp1.e2;

public class Pieton {
	private boolean isWaiting = true;
    private String etat = "arrive";

    public void setEtat(String nouvelleEtat) {
        etat = nouvelleEtat;
    }

    public void traverser() {
        System.out.println("Pieton traverse...");
    }

    public void attendreLumieresRouge() throws InterruptedException {
        System.out.println("Pieton en attente de lumiere rouge...");
        this.wait(5);
    }

    public void printEtat() {
        System.out.println("Pieton: " + this.etat);
    }
}
