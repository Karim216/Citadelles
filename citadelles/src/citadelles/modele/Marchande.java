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

		getJoueur().ajouterPieces(1);

	}

	@Override
	public void percevoirRessourcesSpecifiques() {
		// TODO Auto-generated method stub

		if (getJoueur() != null && getAssassine() != true) {
			for (int i = 0; i < getJoueur().nbQuartiersDansCite(); i++) {
				if (getJoueur().getCite()[i].getType().equals("COMMERCANT")) {
					getJoueur().ajouterPieces(1);

				}
			}
		}

	}
}