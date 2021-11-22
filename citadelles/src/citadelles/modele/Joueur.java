package citadelles.modele;
import java.util.ArrayList;
import java.util.Random;

public class Joueur {
	private String nom="";
	private int tresor=0;
	final int TAILLE_CITE = 8;
	private Quartier[] cite;
	private int nbQuartiers=0;
	private ArrayList<Quartier> main = new ArrayList<Quartier>();
	private boolean possedeCouronne = false;
	
	//Constructeur Nom
	public Joueur(String nom) {
		super();
		this.nom = nom;
		cite = new Quartier[TAILLE_CITE];
	}
	//Accesseurs nom
	public String getNom() {
		return nom;
	}
	public int nbPieces() {
		return this.tresor;
	}
	public int nbQuartiersDansCite(){
		return this.nbQuartiers;
	}
	
	public Quartier[] getCite() {
		return this.cite;
	}
	public ArrayList<Quartier> getMain() {
		return this.main;
	}
	public int nbQuartiersDansMain() {
		return main.size();
	}
	public boolean getPossedeCouronne() {
		return this.possedeCouronne;
	}
	public void setPossedeCouronne(boolean b) {
		this.possedeCouronne=b;
	}
	// ajouter et  retirer piéces 
	public void ajouterPieces(int i) {
		if(i>0){
			tresor = tresor + i;
		}
		
	}
	public void retirerPieces(int i) {
		if(i>0 && i<=tresor){
			tresor = tresor - i;
		}
		
	}
	//Méthode ajout Quartier dans la cite 
	public void ajouterQuartierDansCite(Quartier q) {
		if(nbQuartiersDansCite() < TAILLE_CITE) {
			cite[nbQuartiersDansCite()] = q;
			nbQuartiers++;
		}
	}	
	
	//recherche d'un quartier dans la cité
	public boolean quartierPresentDansCite(String nom){
		for(int i=0; i < nbQuartiersDansCite(); i++) {
			if(cite[i].getNom().equals(nom))
				return true;
		}
		return false;
	}
	
	//retrait d'un quartier dans la cité
	public Quartier retirerQuartierDansCite(String quartier) {
		Quartier q = null;
		
		for(int i=0; i < nbQuartiersDansCite(); i++) {
			if(cite[i].getNom().equals(quartier)) {
				q = cite[i];
				for(int j = i; j < nbQuartiersDansCite(); j++) {
					cite[i] = cite[i+1];
				}
				nbQuartiers--;
				cite[nbQuartiers] = null;
				return q;
			}
			
		}
		
		return null;
	}

	public void ajouterQuartierDansMain(Quartier quartier) {
		main.add(quartier);
	}
	
	public Quartier retirerQuartierDansMain() {
		
		Quartier q = null;
		Random generateur = new Random();
		int numeroHasard = generateur.nextInt(this.nbQuartiersDansMain());
		
		if(!main.isEmpty()) {
			q = main.get(numeroHasard);
			main.remove(main.get(numeroHasard));
			
			return q;
		}
		else {
			return null;
		}
	}
	
	public void reinitialiser() {
		tresor = 0;
		nbQuartiers = 0;
		cite = new Quartier[nbQuartiers];
		main.clear();
	}
}