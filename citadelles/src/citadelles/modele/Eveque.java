package citadelles.modele;

public class Eveque extends Personnage {

	public Eveque() {
		super("Eveque",5,Caracteristiques.EVEQUE);
	}
	public void percevoirRessourceSpecifique() {
		//Si le Quartier Religieux est present je fait tresor = NbdeType Quartier*2+nbpieces+nb
		if(getJoueur() != null && getAssassine() != true) {
			if(Quartier.TYPE_QUARTIERS[0].equals("RELIGIEUX")) {
				int nbQuartierDansCite = joueur.nbQuartiersDansCite();
				joueur.tresor += nbQuartierDansCite;

				System.out.print(nbQuartierDansCite+" pi�ce(s) ajout�(s) aux tresors du "+getNom());
			}
		}
	}
	
// L'�v�que est prot�g� des attaque du personnage de rank 8.
	public void utiliserPouvoir() {

	}

}