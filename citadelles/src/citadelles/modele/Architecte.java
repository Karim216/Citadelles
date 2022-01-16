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
		
		Quartier q1;
		Quartier q2;
		
		for(int i=0; i<getPlateau().getNombreJoueurs(); i++) {
			if(getPlateau().getJoueur(i).getPersonnage().getNom().equals("Architecte")) {
				//for(int j=0; j<2; j++) {
				q1 = getPlateau().getPioche().piocher();
				q2 = getPlateau().getPioche().piocher();
				getPlateau().getJoueur(i).ajouterQuartierDansMain(q1);
				getPlateau().getJoueur(i).ajouterQuartierDansMain(q2);
				//}
				System.out.println("Vous avez pioché: ");
				System.out.println("\t"+getPlateau().getJoueur(i).getMain().get(getPlateau().getJoueur(i).nbQuartiersDansMain() - 2).getNom());
				System.out.println("\t"+getPlateau().getJoueur(i).getMain().get(getPlateau().getJoueur(i).nbQuartiersDansMain() - 1).getNom());
			}
			
		}
			
	}

}
