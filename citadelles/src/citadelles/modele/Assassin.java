package citadelles.modele;

import controleur.Interaction;
import application.*;

public class Assassin extends Personnage {

	public Assassin() {

		super("Assassin", 1, Caracteristiques.ASSASSIN);
	}

	@Override
	public void utiliserPouvoir() {

		boolean continu = false;
		System.out.print("Quel personnage voulez-vous assassiner ?\n");

		for (int i = 0; i < getPlateau().getNombreJoueurs(); i++) {
			System.out.println("\t" + (i + 1) + " " + getPlateau().getJoueur(i).getPersonnage().getNom());
		}

		do {
			System.out.print("Votre choix: ");
			int choix = Interaction.lireUnEntier(1, getPlateau().getNombrePersonnages() + 1);

			if (getPlateau().getJoueur(choix - 1).getPersonnage().getNom().equals("Assassin")) {
				System.out.print("Vous ne pouvez pas vous assassiner\n");
				continu = true;
			} else {
				getPlateau().getJoueur(choix - 1).getPersonnage().setAssassine();
				continu = false;
			}
		} while (continu);

	}

	@Override
	public void percevoirRessourcesSpecifiques() {
		// TODO Auto-generated method stub
		System.out.print("------------------\n");
	}

}
