package citadelles.modele;

public class Roi extends Personnage{
	
	public Roi() {
		super("Roi", 4, Caracteristiques.ROI);
		// TODO Auto-generated constructor stub
	}

	public void utiliserPouvoir() {
		System.out.print("Je prends la couronne");
		joueur.possedeCouronne = true;
		
	}
	
	public void percevoirRessourcesSpecifiques() {
		if(Quartier.TYPE_QUARTIERS[2].equals("NOBLE")) {
			//int nbQuartierDansCite = joueur.nbQuartiersDansCite();
			joueur.tresor += 2;
			
			System.out.print(2+" pièce(s) ajouté(s) aux tresors du "+getNom());
		}
	}
	
}
