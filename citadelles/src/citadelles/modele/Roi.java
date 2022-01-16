package citadelles.modele;

public class Roi extends Personnage {

	public Roi() {
		super("Roi", 4, Caracteristiques.ROI);
		// TODO Auto-generated constructor stub
	}

	public void utiliserPouvoir() {
//		System.out.println(getPlateau().getPersonnage(2).getJoueur());
		
		//getPlateau().getJoueur(i).setPossedeCouronne(true);
		
//		for(int i=0; i<getPlateau().getNombreJoueurs(); i++) {
//			
//			if (!getPlateau().getJoueur(i).getPersonnage().getNom().equals("Roi")) {
//				getPlateau().getJoueur(i).setPossedeCouronne(true);
//				System.out.println("Vous prennez la couronne");
//				
//			}else {
//				getPlateau().getJoueur(i).setPossedeCouronne(false);
//			}
//		}

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
		if (getJoueur() != null && getAssassine() != true) {
			for (int i = 0; i<getJoueur().nbQuartiersDansCite(); i++) {
				if (getJoueur().getCite()[i].getType().equals("NOBLE")) {
					getJoueur().ajouterPieces(1);
					
				}
			}
		}

	}

}
