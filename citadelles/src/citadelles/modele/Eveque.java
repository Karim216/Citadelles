package citadelles.modele;

public class Eveque extends Personnage {

	public Eveque() {
		super("Eveque",5,Caracteristiques.EVEQUE);
	}
	
// L'évéque est protégé des attaque du personnage de rank 8.
	public void utiliserPouvoir() {

	}
	@Override
	public void percevoirRessourcesSpecifiques() {
		// TODO Auto-generated method stub
		
		//Si le Quartier Religieux est present je fait tresor = NbdeType Quartier*2+nbpieces+nb
		
		if (getJoueur() != null && getAssassine() != true) {
			for (int i = 0; i<getJoueur().nbQuartiersDansCite(); i++) {
				if (getJoueur().getCite()[i].getType().equals("RELIGIEUX")) {
					getJoueur().ajouterPieces(1);
					
				}
			}
		}
		
	}
	
	
}