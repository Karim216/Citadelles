package citadelles.modele;

public class Marchande extends Personnage{
	
public Marchande() {
	super("Marchande",6,Caracteristiques.MARCHANDE);
}
public void percevoirRessourceSpecifique() {
	if(getJoueur() != null && getAssassine() != true) {
		if(Quartier.TYPE_QUARTIERS[3].equals("COMMERCANT")) {
			int nbQuartierDansCite = joueur.nbQuartiersDansCite();
			joueur.tresor += nbQuartierDansCite;

			System.out.print(nbQuartierDansCite+" pièce(s) ajouté(s) aux tresors du "+getNom());
		}
	}
}
	public void utiliserPouvoir() {

}
	@Override
	public void percevoirRessourcesSpecifiques() {
		// TODO Auto-generated method stub
		
	}
}