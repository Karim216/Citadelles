package citadelles.modele;

import controleur.Interaction;
import citadelles.modele.PlateauDeJeu;

public class Assassin extends Personnage {


	public Assassin() {

		super("Assassin", 1, Caracteristiques.ASSASSIN);
	}

	@Override
	public void utiliserPouvoir() {

		boolean continu = false;
		System.out.print("Quel personnage voulez vous assassiner ?\n");

//		System.out.println(PlateauDeJeu.getPersonnage(1));

	}

}
