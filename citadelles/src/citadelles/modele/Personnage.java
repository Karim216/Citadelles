package citadelles.modele;

public abstract class Personnage {

	private String nom;
	private int rang;
	private String caracteristiques;

	// attribut du joueur auquel le personnage est attribué
	public Joueur joueur;

	// l'état du personnage
	private boolean assassine = false; // pour indiquer que le personnage n'est pas volé
	private boolean vole = false; // pour indiquer que le personnage n'est pas assassiné

	private PlateauDeJeu plateau;

	// constructeur parametre
	public Personnage(String nom, int rang, String caracteristiques) {
		// super();
		this.nom = nom;
		this.rang = rang;
		this.caracteristiques = caracteristiques;
		joueur = null;

	}

	// les accesseurs

	// renvoie le nom du personnage
	public String getNom() {
		return nom;
	}

	// renvoie le rang du personnage
	public int getRang() {
		return rang;
	}

	// renvoie le carractéristique du personnage
	public String getCaracteristiques() {
		return caracteristiques;
	}

	// renvoie le joueur auquel le personnage est attribué
	public Joueur getJoueur() {
		return joueur;
	}

	public boolean getAssassine() {
		return assassine;
	}

	public boolean getVole() {
		return vole;
	}

	public PlateauDeJeu getPlateau() {
		return plateau;
	}

	// les mutateurs
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public void setJoueur() {
		this.joueur.monPersonnage = this;
	}

	public void setAssassine() {
		this.assassine = true;
	}

	public void setVole() {
		this.vole = true;
	}

	public void setPlateau(PlateauDeJeu plateau) {
		this.plateau = plateau;
	}

	public void ajouterPieces() {

		// dans le cas où le personnage est associé à un joueur ou qu'il n'est pas
		// assassiné
		if (getJoueur() != null && assassine != true) {
			joueur.tresor += 2;
		}

		// dans le cas où le personnage n'est associé à aucun joueur ou qu'il est
		// assassiné
		// else {}
	}

	public void ajouterQuartier(Quartier nouveau) {

		// dans le cas où le personnage est associé à un joueur ou qu'il n'est pas
		// assassiné
		if (getJoueur() != null && assassine != true) {
			joueur.ajouterQuartierDansMain(nouveau);
		}

		// dans le cas où le personnage n'est associé à aucun joueur ou qu'il est
		// assassiné
		// else {}

	}

	public void construire(Quartier nouveau) {

		// dans le cas où le personnage est associé à un joueur ou qu'il n'est pas
		// assassiné
		if (getJoueur() != null && assassine != true) {
			// joueur.cite[joueur.cite.length] = nouveau;
			joueur.ajouterQuartierDansCite(nouveau);
			// joueur.nbQuartiers++;
		}

		// dans le cas où le personnage n'est associé à aucun joueur ou qu'il est
		// assassiné
		// else {}

	}

	public abstract void percevoirRessourcesSpecifiques();

	// classe abstraite qui sera utilisé dans les classes roi et autres
	public abstract void utiliserPouvoir();

	public void reinitialiser() {
		joueur = null;
		if (this.joueur != null) {
			this.joueur.monPersonnage = null;
		}
		vole = false;
		assassine = false;
	}
}
