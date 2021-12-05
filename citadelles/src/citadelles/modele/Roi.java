package citadelles.modele;

public class Roi extends Personnage{
	
	public Roi() {
		super("Roi", 4, Caracteristiques.ROI);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void utiliserPouvoir() {	
		
		if(getJoueur() != null && getAssassine() != true) {
			System.out.print("Je prends la couronne");
			joueur.possedeCouronne = true;
		}
		
	}
	
	public void percevoirRessourcesSpecifiques() {
		
		if(getJoueur() != null && getAssassine() != true) {
			if(Quartier.TYPE_QUARTIERS[2].equals("NOBLE")) {
				int nbQuartierDansCite = joueur.nbQuartiersDansCite();
				joueur.tresor += nbQuartierDansCite;
				
				System.out.print(nbQuartierDansCite+" pièce(s) ajouté(s) aux tresors du "+getNom());
			}
		}
		
	}
	
}
