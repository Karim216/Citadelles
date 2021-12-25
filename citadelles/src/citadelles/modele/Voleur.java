package citadelles.modele;

import controleur.Interaction;

public class Voleur extends Personnage {

	public Voleur() {

		super("Voleur", 2, Caracteristiques.VOLEUR);
	}

	@Override
	public void utiliserPouvoir() {

		boolean continu = false;
		System.out.print("à quel personnage voulez vous derober tous ses biens ?\n");

		for (int i = 0; i < getPlateau().getNombrePersonnages(); i++) {
			System.out.println("\t" + (i + 1) + " " + getPlateau().getPersonnage(i).getNom());
		}

		do {
			System.out.print("Votre choix: ");
			int choix = Interaction.lireUnEntier(1, getPlateau().getNombrePersonnages() + 1);

			if (getPlateau().getPersonnage(choix - 1).getNom().equals("Voleur")) {
				System.out.print("Sacre bleu ! vous ne pouvez point vous voler\n");
				continu = true;
			}

			else if (getPlateau().getPersonnage(choix - 1).getRang() == 1) { // innexistant dans personnage ... idée
																				// reccupérer les exeptions pour les
																				// perso
																				// de rang 1
				System.out.print("Vous ne pourriez derober les biens de ce personage\n");
				continu = true; // cette instruction est pour les joueurs de niveau 1 la methode "getNiveau()"
								// n'existe pas dans personages et je ne veux pas l'autogeneré de peur de causer
								// une eurreur.

			} else {
				//vole du personnage
				getPlateau().getPersonnage(choix - 1).setVole();
				getJoueur().ajouterPieces(getPlateau().getPersonnage(choix - 1).getJoueur().nbPieces());

				getPlateau().getPersonnage(choix - 1).getJoueur().tresor = 0;
				continu = false;
				
				//on remet vole à false
				getPlateau().getPersonnage(choix - 1).setVole();
			}

		} while (continu);
	}

	@Override
	public void percevoirRessourcesSpecifiques() {
		// TODO Auto-generated method stub

	}

}
