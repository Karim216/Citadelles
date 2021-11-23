package citadelles.modele;

public abstract class Personnage {
	private String nom;
	private int rang;
	private String caracteristiques;
	public Joueur joueur = null;
	private boolean assassine = false;
	private boolean vole = false;
	//private PlateauDeJeu plateau;
	
	//constructeur parametre
	public Personnage(String nom, int rang, String caracteristiques) {
		//super();
		this.nom = nom;
		this.rang = rang;
		this.caracteristiques = caracteristiques;
	}
	
	//les accesseurs
	public String getNom() {
		return nom;
	}
	
	public int getRang() {
		return rang;
	}
	
	public String getCaracteristiques() {
		return caracteristiques;
	}
	
	public Joueur getJoueur() {
		return joueur;
	}
	
	public boolean getAssassine() {
		return assassine;
	}
	
	public boolean getVole() {
		return vole;
	}
	
	//les mutateurs
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	public void setAssassine() {
		this.assassine = true;
	}
	public void setVole() {
		this.vole = true;
	}
	
	public void ajouterPieces() {
		
		//dans le cas o� le personnage est associ� � un joueur ou qu'il n'est pas assassin�
		if(getJoueur() != null && assassine != true) {
			joueur.tresor += 2;
		}
		
		//dans le cas o� le personnage n'est associ� � aucun joueur ou qu'il est assassin�
		//else {}
	}
	
	public void ajouterQuartier(Quartier nouveau) {
		
		//dans le cas o� le personnage est associ� � un joueur ou qu'il n'est pas assassin�
		if(getJoueur() != null && assassine != true) {
			joueur.ajouterQuartierDansMain(nouveau);
		}
		
		//dans le cas o� le personnage n'est associ� � aucun joueur ou qu'il est assassin�
		//else {}
		
	}
	
	public void construire(Quartier nouveau) {
		
		//dans le cas o� le personnage est associ� � un joueur ou qu'il n'est pas assassin�
		if(getJoueur() != null && assassine != true) {
			//joueur.cite[joueur.cite.length] = nouveau;
			joueur.ajouterQuartierDansCite(nouveau);
			//joueur.nbQuartiers++;
		}
		
		//dans le cas o� le personnage n'est associ� � aucun joueur ou qu'il est assassin�
		//else {}
		
	}
	
	public void percevoirRessourcesSpecifiques() {
		
		//dans le cas o� le personnage est associ� � un joueur ou qu'il n'est pas assassin�
		if(getJoueur() != null && assassine != true) {
			System.out.print("aucune ressource sp�cifique");
		}
		
		//dans le cas o� le personnage n'est associ� � aucun joueur ou qu'il est assassin�
		//else {}
	}
	
	//classe abstraite qui sera utilis� dans les classes roi et autres
	public abstract void utiliserPouvoir();
	
	public void reinitialiser() {
		joueur = null;
		vole = false;
		assassine = false;
	}
}
