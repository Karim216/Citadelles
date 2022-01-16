package citadelles.modele;

public class Roi extends Personnage {

	public Roi() {
		super("Roi", 4, Caracteristiques.ROI);
		// TODO Auto-generated constructor stub
	}

	public void utiliserPouvoir() {

		for(int i=0; i<getPlateau().getNombreJoueurs(); i++) {
			
			
			if (getPlateau().getJoueur(i).getPersonnage().getNom().equals("Roi")) {
				getPlateau().getJoueur(i).setPossedeCouronne(true);
				System.out.println("Vous prennez la couronne");
				
			}else {
				getPlateau().getJoueur(i).setPossedeCouronne(false);
			}
		}

	}

	public void percevoirRessourcesSpecifiques() {
		for(int i=0; i<getPlateau().getNombreJoueurs(); i++) {
			if (getPlateau().getJoueur(i).getPersonnage().getAssassine() != true) {
				for (int j = 0; j<getPlateau().getJoueur(i).nbQuartiersDansCite(); j++) {
					if (getPlateau().getJoueur(i).getCite()[j].getType().equals("NOBLE")) {
						getPlateau().getJoueur(i).ajouterPieces(1);
						
					}
				}
			}
		}

	}

}
