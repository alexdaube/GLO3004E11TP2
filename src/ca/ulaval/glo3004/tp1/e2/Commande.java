package ca.ulaval.glo3004.tp1.e2;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Commande {
	 private final List<String> LUMIERES = Arrays.asList("rouge", "jaune", "vert", "clignotant", "pieton");
	    private static final String PIETON = "pieton";
	    private final List<String> DIRECTIONS_LUMIERES = Arrays.asList("est", "ouest", "sud", "tous");
	    private final List<String> DIRECTIONS_VOITURES = Arrays.asList("est", "ouest", "sud");
	    private final List<String> VOITURES_ACTIONS = Arrays.asList("attendV", "arrive", "tourneDroite", "tourneGauche", "continue");
	    private final List<String> PIETONS_ACTIONS = Arrays.asList("arrive", "attend", "traverse");

	    private String direction;
	    private String action;

	    public Commande(String command) {
	        String[] actions = command.trim().split(Pattern.quote("."));
	        direction = actions[0];
	        action = actions[1].equals("vClignotant") ? "clignotant" : actions[1];
	    }

	    public String reconstruireLaCommande() {
		return direction.concat(".").concat(action);
	    }

	    public boolean estValide() {
	        return (estPourLumiere() || estPourLumierePieton() ||
	                estPourVoiture() || estPourPieton());
	    }

	    public boolean estPourLumiere() {
	        return DIRECTIONS_LUMIERES.contains(direction) && LUMIERES.contains(action);
	    }

	    public boolean estPourLumierePieton() {
	        return DIRECTIONS_LUMIERES.contains(direction) && PIETON.equals(action);
	    }

	    public boolean estPourVoiture() {
	        return DIRECTIONS_VOITURES.contains(direction) && VOITURES_ACTIONS.contains(action);
	    }

	    public boolean estPourPieton() {
	        return PIETON.equals(direction) && PIETONS_ACTIONS.contains(action);
	    }

	    public String getAction() {
	        return action;
	    }

	    public String getDirection() {
	        return direction;
	    }
}
