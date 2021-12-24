package citadelles.modele;

public class Roi extends Personnage {

	public Roi() {
		super("Roi", 4, Caracteristiques.ROI);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void utiliserPouvoir() {

		if (getJoueur() != null && getAssassine() != true) {
			System.out.print("Je prends la couronne");
			getJoueur().possedeCouronne = true;
		}

	}

	public void percevoirRessourcesSpecifiques() {
		if (getJoueur() != null && getAssassine() != true) {
			for (int i = 0; i<getJoueur().nbQuartiersDansCite(); i++) {
				if (getJoueur().getCite()[i].getType().equals("NOBLE")) {
					getJoueur().ajouterPieces(1);
					
				}
			}
		}

	}

}
