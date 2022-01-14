package citadelles.modele;

public class Roi extends Personnage {

	public Roi() {
		super("Roi", 4, Caracteristiques.ROI);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void utiliserPouvoir() {
		System.out.println(getPlateau().getPersonnage(7).getJoueur());
		for(int i=0; i<getPlateau().getNombrePersonnages(); i++) {
			if (getPlateau().getPersonnage(i).equals("Roi")) {
				System.out.println("Je prends la couronne");
				getPlateau().getPersonnage(i).getJoueur().setPossedeCouronne(true);
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
