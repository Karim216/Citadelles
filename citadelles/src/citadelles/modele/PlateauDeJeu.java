package citadelles.modele;

public class PlateauDeJeu {
	private Pioche pioche;
	private int nombrePersonnages = 0;
	private int nombreJoueurs = 0;

	private final int NOMBRE_MAX_JOUEUR = 9;
	private final int NOMBRE_MAX_PERSONNAGE = 9;

	private Joueur[] listeJoueurs;
	private Personnage[] listePersonnages;

	public PlateauDeJeu() {
		listeJoueurs = new Joueur[NOMBRE_MAX_JOUEUR];
		listePersonnages = new Personnage[NOMBRE_MAX_PERSONNAGE];
		pioche = new Pioche();
	}

	public Pioche getPioche() {
		return pioche;
	}

	public void setPioche(Pioche pi) {
		this.pioche = pi;;
	}
	
	public int getNombrePersonnages() {
		return nombrePersonnages;
	}

	public int getNombreJoueurs() {
		return nombreJoueurs;
	}

	public Joueur getJoueur(int i) {
		if (i >= 0 && i < listeJoueurs.length) {
			return listeJoueurs[i];
		} else {
			return null;
		}

	}

	// renvoie le le ième personnage du tableau liste personnage
	public Personnage getPersonnage(int i) {

		if (i >= 0 && i < listePersonnages.length) {
			return listePersonnages[i];
		} else {
			return null;
		}
	}

	public void ajouterJoueur(Joueur nouveau) {

		if (getNombreJoueurs() < NOMBRE_MAX_JOUEUR && nouveau != null) {
			listeJoueurs[getNombreJoueurs()] = nouveau;
			nombreJoueurs++;
		}
	}

	public void ajouterPersonnage(Personnage nouveau) {

		if (getNombrePersonnages() < NOMBRE_MAX_JOUEUR && nouveau != null) {
			listePersonnages[getNombrePersonnages()] = nouveau;
			nombrePersonnages++;

			// association du plateau au personnage ajouté
			nouveau.setPlateau(this);

		}

	}

}