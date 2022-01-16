package citadelles.modele;

public class Marchande extends Personnage {

	public Marchande() {
		super("Marchande", 6, Caracteristiques.MARCHANDE);
	}

//	public void percevoirRessourceSpecifique() {
//		if (getJoueur() != null && getAssassine() != true) {
//			if (Quartier.TYPE_QUARTIERS[3].equals("COMMERCANT")) {
//				int nbQuartierDansCite = getJoueur().nbQuartiersDansCite();
//				getJoueur().tresor += nbQuartierDansCite;
//
//				System.out.print(nbQuartierDansCite + " pièce(s) ajouté(s) aux tresors du " + getNom());
//			}
//		}
//	}

	public void utiliserPouvoir() {

		for (int i = 0; i < getPlateau().getNombreJoueurs(); i++) {
			if (getPlateau().getJoueur(i).getPersonnage().getNom().equals("Marchande")) {
				getPlateau().getJoueur(i).ajouterPieces(1);
				
				System.out.println("1 pièce ajouté dans votre trésor");
				System.out.print("\tTotal: "+getPlateau().getJoueur(i).nbPieces()+" pièce(s) d'or\n");
			}
		}

	}

	@Override
	public void percevoirRessourcesSpecifiques() {
		// TODO Auto-generated method stub

		for(int i=0; i<getPlateau().getNombreJoueurs(); i++) {
			if (getPlateau().getJoueur(i).getPersonnage().getAssassine() != true) {
				for (int j = 0; j<getPlateau().getJoueur(i).nbQuartiersDansCite(); j++) {
					if (getPlateau().getJoueur(i).getCite()[j].getType().equals("COMMERCANT")) {
						getPlateau().getJoueur(i).ajouterPieces(1);
						
					}
				}
			}
		}

	}
}