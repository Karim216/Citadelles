package citadelles.modele;

public class Architecte extends Personnage{

	public Architecte() {
		super("Architecte", 6, Caracteristiques.ARCHITECTE);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void percevoirRessourcesSpecifiques() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void utiliserPouvoir() {
		// TODO Auto-generated method stub
		
		for (int i=0; i<2; i++) {
			this.getJoueur().main.add(getPlateau().getPioche().piocher());
		}
		
	}

}
