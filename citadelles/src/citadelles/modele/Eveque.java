package citadelles.modele;

public class Eveque extends Personnage {

	public Eveque() {
		super("Eveque",5,Caracteristiques.EVEQUE);
	}
	
// L'évéque est protégé des attaque du personnage de rank 8.
	public void utiliserPouvoir() {
		System.out.println("Vous êtes protégé du pouvoir des personnages : ");
		for(int i=0; i<getPlateau().getNombreJoueurs(); i++) {
			if(getPlateau().getJoueur(i).getPersonnage().getRang() == 8) {
				System.out.println("\t- "+getPlateau().getJoueur(i).getNom()+": "+getPlateau().getJoueur(i).getPersonnage().getNom());
			}
		}
	}
	@Override
	public void percevoirRessourcesSpecifiques() {
		// TODO Auto-generated method stub
		
		//Si le Quartier Religieux est present je fait tresor = NbdeType Quartier*2+nbpieces+nb
		
		for(int i=0; i<getPlateau().getNombreJoueurs(); i++) {
			if (getPlateau().getJoueur(i).getPersonnage().getAssassine() != true) {
				for (int j = 0; j<getPlateau().getJoueur(i).nbQuartiersDansCite(); j++) {
					if (getPlateau().getJoueur(i).getCite()[j].getType().equals("RELIGIEUX")) {
						getPlateau().getJoueur(i).ajouterPieces(1);
						
					}
				}
			}
		}
		
	}
	
	
}