package ca.ulaval.glo3004.tp1.e2;

public class ValidateurEtatLumiere {
	public boolean validateEst(CouleurLumiere couleurLumiere, EtatIntersection etatCourant) {
        switch (couleurLumiere) {
            case CLIGNOTANT:
                if (etatCourant == EtatIntersection.VRV || etatCourant == EtatIntersection.RRR ||
                        etatCourant == EtatIntersection.RRV || etatCourant == EtatIntersection.CRV ||
                        etatCourant == EtatIntersection.CRR) {
                    return true;
                } else {
                    return false;
                }
            case ROUGE:
                if (etatCourant == EtatIntersection.VVR || etatCourant == EtatIntersection.VRV) {
                    return true;
                } else {
                    return false;
                }
            case VERT:
                return !(etatCourant == EtatIntersection.RRC);
//                if (etatCourant == EtatIntersection.RRC) {
//                    return false;
//                } else {
//                    return true;
//                }
            default:
                return false;
        }
    }

    public boolean validateOuest(CouleurLumiere couleurLumiere, EtatIntersection etatCourant) {
        switch (couleurLumiere) {
            case ROUGE:
                return true;
            case VERT:
                if (etatCourant == EtatIntersection.RRR || etatCourant == EtatIntersection.VVR ||
                        etatCourant == EtatIntersection.VRR || etatCourant == EtatIntersection.RVR) {
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }

    public boolean validateSud(CouleurLumiere couleurLumiere, EtatIntersection etatCourant) {
        switch (couleurLumiere) {
            case CLIGNOTANT:
                if (etatCourant == EtatIntersection.RRV || etatCourant == EtatIntersection.RRR ||
                        etatCourant == EtatIntersection.RRC) {
                    return true;
                } else {
                    return false;
                }
            case ROUGE:
                    return true;
            case VERT:
                if (etatCourant == EtatIntersection.CRR || etatCourant == EtatIntersection.VRV ||
                        etatCourant == EtatIntersection.RRV || etatCourant == EtatIntersection.RRR ||
                        etatCourant == EtatIntersection.RRC || etatCourant == EtatIntersection.CRV) {
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }

    public boolean validateTous(CouleurLumiere couleurLumiere, EtatIntersection etatCourant) {
        switch (couleurLumiere) {
            case ROUGE:
                return true;
            case PIETON:
                if (etatCourant == EtatIntersection.RRR) {
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }
}
