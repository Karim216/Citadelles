package citadelles.modele;

import controleur.Interaction;

public class Assassin extends Personnage {

	public Assassin() {

		super("Assassin", 1, Caracteristiques.ASSASSIN);
	}

	@Override
	public void utiliserPouvoir() {

		boolean continu = false;
		System.out.print("Quel personnage voulez-vous assassiner ?\n");

		for (int i = 0; i < getPlateau().getNombrePersonnages(); i++) {
			System.out.println("\t" + (i + 1) + " " + getPlateau().getPersonnage(i).getNom());
		}

		do {
			System.out.print("Votre choix: ");
			int choix = Interaction.lireUnEntier(1, getPlateau().getNombrePersonnages() + 1);

			if (getPlateau().getPersonnage(choix - 1).getNom().equals("Assassin")) {
				System.out.print("Vous ne pouvez pas vous assassiner\n");
				continu = true;
			} else {
				getPlateau().getPersonnage(choix - 1).setAssassine();
				continu = false;
			}
		} while (continu);

	}

}
