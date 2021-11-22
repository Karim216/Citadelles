package citadelles.modele;
import java.util.ArrayList;

public class Joueur {
	private String nom="";
	private int tresor=0;
	// dans le tableau cité j'ai des objets quartier 
	private Quartier[] cite;
	private int nbQuartiers=0;
	private ArrayList<Quartier> main = new ArrayList<Quartier>();
	private boolean possedeCouronne=false;
	//Constructeur Nom
	public Joueur(String nom) {
		super();
		this.nom = nom;
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
		else {
			tresor=tresor;
		}
	}
	public void retirerPieces(int i) {
		if(i>0 && i<=tresor){
			tresor = tresor - i;
		}
		else {
			tresor=tresor;
		}
	}
	//Méthode Quartier 
	public void ajouterQuartierDansCite(Quartier q) {
		if(nbQuartiers<=8) {
			this.cite[nbQuartiers]=q;
			nbQuartiers++;
		}
	}	
	//question 7
	public boolean quartierPresentDansCite(String qpdc){
		int i=0;
		boolean quartierpres = false;
		while(i<cite.length && !quartierpres){
			if(qpdc.equals(cite[i])) {
				quartierpres=true;
			}
			i++;
		}
		return true;
	}
	//question 8
	public Quartier retirerQuartierDansCite(String rqdc) {
	
		
		int i=0;
		boolean retquar =false;
		while(i<cite.length && !retquar) {
			if (rqdc.equals(cite[i])){
				retquar=true;
				//this.cite[i]=null;
				
				return cite[i];
			}
			i++;
		}
		
		return null;
	}
	//question 9
	public void ajouterQuartierDansMain(Quartier aqdm) {
		main.add(aqdm);
	}
	
	public Quartier retirerQuartierDansMain() {
		
	}
}