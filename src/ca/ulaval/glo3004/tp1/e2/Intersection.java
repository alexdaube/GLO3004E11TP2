package ca.ulaval.glo3004.tp1.e2;

public class Intersection {
	private Lumiere lumiereEst;
    private Lumiere lumiereOuest;
    private Lumiere lumiereSud;
    private EtatIntersection etat = EtatIntersection.RRR;
    private ValidateurEtatLumiere validateurEtatLumiere;

    public Intersection() {
        this.lumiereEst = new Lumiere();
        this.lumiereOuest = new Lumiere();
        this.lumiereSud = new Lumiere();
        this.validateurEtatLumiere = new ValidateurEtatLumiere();
    }

    public EtatIntersection getEtatIntersection() {
            String etatEst = lumiereEst.getCouleur().substring(0, 0);
            String etatOuest = lumiereOuest.getCouleur().substring(0, 0);
            String etatSud = lumiereSud.getCouleur().substring(0, 0);
            String etat = etatEst.concat(etatOuest).concat(etatSud).toUpperCase();
            return EtatIntersection.valueOf(etat);
    }

    //est.vClignotant
    //tous.rouge
    //tous.pieton
    // sud.vClignotant


    public void changerLumieres(Commande commande) {
        if (commande.getDirection().equals("est")) {
            handleDirectionEst(commande.getAction());
        } else if (commande.getDirection().equals("ouest")) {
            handleDirectionOuest(commande.getAction());
        } else if (commande.getDirection().equals("sud")) {
            handleDirectionSud(commande.getAction());
        } else if (commande.getDirection().equals("tous")) {
            handleDirectionTous(commande.getAction());
        } else {
            //throw new MauvaiseCommandeLumiereException();
        }
    }


    private void handleDirectionEst(String action) {
        try {
            CouleurLumiere couleurLumiere = CouleurLumiere.valueOf(action.toUpperCase());
            if (validateurEtatLumiere.validateEst(couleurLumiere, getEtatIntersection())) {
                this.lumiereEst.changerPour(couleurLumiere);
            }
        } catch (Exception e) {
            //e.getMessage();
        }
    }

    private void handleDirectionOuest(String action) {
        try {
            CouleurLumiere couleurLumiere = CouleurLumiere.valueOf(action.toUpperCase());
            if (validateurEtatLumiere.validateOuest(couleurLumiere, getEtatIntersection())) {
                this.lumiereOuest.changerPour(couleurLumiere);
            }
        } catch (Exception e) {
            //e.getMessage();
        }
    }

    private void handleDirectionSud(String action) {
        try {
            CouleurLumiere couleurLumiere = CouleurLumiere.valueOf(action.toUpperCase());
            if (validateurEtatLumiere.validateSud(couleurLumiere, getEtatIntersection())) {
                this.lumiereSud.changerPour(couleurLumiere);
            }
        } catch (Exception e) {
            //e.getMessage();
        }
    }

    private void handleDirectionTous(String action) {
        try {
            CouleurLumiere couleurLumiere = CouleurLumiere.valueOf(action.toUpperCase());
            if (validateurEtatLumiere.validateTous(couleurLumiere, getEtatIntersection())) {
                lumiereEst.changerPour(couleurLumiere);
                lumiereOuest.changerPour(couleurLumiere);
                lumiereSud.changerPour(couleurLumiere);
            }
        } catch (Exception e) {
            //e.getMessage();
        }
    }

    public void printEtatLumieres() {
        System.out.println("Lumiere ouest: " + this.lumiereOuest.getCouleur());
        System.out.println("Lumiere est: " + this.lumiereEst.getCouleur());
        System.out.println("Lumiere sud: " + this.lumiereSud.getCouleur());
    }
}
