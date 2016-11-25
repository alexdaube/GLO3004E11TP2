package ca.ulaval.glo3004.tp1.e2;

public class ValidateurEtatLumiere {
	public static final String IMPOSSIBLE_CLIGNOTANT = "Impossible de passer au vert clignotant pour la lumière ";
	public static final String IMPOSSIBLE_VERT = "Impossible de passer au vert pour la lumière ";
	public static final String IMPOSSIBLE_ROUGE = "Impossible de passer au rouge pour la lumière ";
	public static final String IMPOSSIBLE_PIETON = "Impossible de passer à la lumière pieton";
	public static final String IMPOSSIBLE_GENERAL = "Impossible changer la lumière ";

	public boolean validateEst(CouleurLumiere couleurLumiere, EtatIntersection etatCourant)
			throws CommandeNonCompleteException {
		switch (couleurLumiere) {
		case CLIGNOTANT:
			if (etatCourant == EtatIntersection.VRV || etatCourant == EtatIntersection.RRR
					|| etatCourant == EtatIntersection.RRV || etatCourant == EtatIntersection.CRV
					|| etatCourant == EtatIntersection.CRR) {
				return true;
			} else {
				throw new CommandeNonCompleteException(IMPOSSIBLE_CLIGNOTANT + "est");
			}
		case ROUGE:
			if (etatCourant == EtatIntersection.VVR || etatCourant == EtatIntersection.VRV) {
				return true;
			} else {
				throw new CommandeNonCompleteException(IMPOSSIBLE_ROUGE + "est");
			}
		case VERT:
			if (etatCourant == EtatIntersection.RRC) {
				throw new CommandeNonCompleteException(IMPOSSIBLE_VERT + "est");
			} else {
				return true;
			}
		default:
			throw new CommandeNonCompleteException(IMPOSSIBLE_GENERAL + "est");
		}
	}

	public boolean validateOuest(CouleurLumiere couleurLumiere, EtatIntersection etatCourant)
			throws CommandeNonCompleteException {
		switch (couleurLumiere) {
		case ROUGE:
			return true;
		case VERT:
			if (etatCourant == EtatIntersection.RRR || etatCourant == EtatIntersection.VVR
					|| etatCourant == EtatIntersection.VRR || etatCourant == EtatIntersection.RVR) {
				return true;
			} else {
				throw new CommandeNonCompleteException(IMPOSSIBLE_VERT + "ouest");
			}
		case CLIGNOTANT:
			throw new CommandeNonCompleteException(IMPOSSIBLE_CLIGNOTANT + "ouest");
		default:
			throw new CommandeNonCompleteException(IMPOSSIBLE_GENERAL + "ouest");
		}
	}

	public boolean validateSud(CouleurLumiere couleurLumiere, EtatIntersection etatCourant)
			throws CommandeNonCompleteException {
		switch (couleurLumiere) {
		case CLIGNOTANT:
			if (etatCourant == EtatIntersection.RRV || etatCourant == EtatIntersection.RRR
					|| etatCourant == EtatIntersection.RRC) {
				return true;
			} else {
				throw new CommandeNonCompleteException(IMPOSSIBLE_CLIGNOTANT + "sud");
			}
		case ROUGE:
			return true;
		case VERT:
			if (etatCourant == EtatIntersection.CRR || etatCourant == EtatIntersection.VRV
					|| etatCourant == EtatIntersection.RRV || etatCourant == EtatIntersection.RRR
					|| etatCourant == EtatIntersection.RRC || etatCourant == EtatIntersection.CRV) {
				return true;
			} else {
				throw new CommandeNonCompleteException(IMPOSSIBLE_VERT + "sud");
			}
		default:
			throw new CommandeNonCompleteException(IMPOSSIBLE_GENERAL + "sud");
		}
	}

	public boolean validateTous(CouleurLumiere couleurLumiere, EtatIntersection etatCourant)
			throws CommandeNonCompleteException {
		switch (couleurLumiere) {
		case ROUGE:
			return true;
		case PIETON:
			if (etatCourant == EtatIntersection.RRR) {
				return true;
			} else {
				throw new CommandeNonCompleteException(IMPOSSIBLE_PIETON);
			}
		default:
			throw new CommandeNonCompleteException(IMPOSSIBLE_GENERAL + "tous");
		}
	}
}
