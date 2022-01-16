package citadelles.modele;

import controleur.Interaction;

public class Voleur extends Personnage {

	public Voleur() {

		super("Voleur", 2, Caracteristiques.VOLEUR);
	}

	@Override
	public void utiliserPouvoir() {

		boolean continu = false;
		System.out.print("Quel personnage voulez vous derober tous ses biens ?\n");

		for (int i = 0; i < getPlateau().getNombreJoueurs(); i++) {
			System.out.println("\t" + (i + 1) + " " + getPlateau().getJoueur(i).getPersonnage().getNom());
		}

		do {
			System.out.print("Votre choix: ");
			int choix = Interaction.lireUnEntier(1, getPlateau().getNombrePersonnages() + 1);

			if (getPlateau().getJoueur(choix - 1).getPersonnage().getNom().equals("Voleur")) {
				System.out.print("Sacre bleu ! vous ne pouvez point vous voler\n");
				continu = true;
			}

			else if (getPlateau().getJoueur(choix - 1).getPersonnage().getRang() == 1) { // innexistant dans personnage ... idée
																				// reccupérer les exeptions pour les
																				// perso
																				// de rang 1
				System.out.print("Vous ne pourriez derober les biens de ce personage\n");
				continu = true; // cette instruction est pour les joueurs de niveau 1 la methode "getNiveau()"
								// n'existe pas dans personages et je ne veux pas l'autogeneré de peur de causer
								// une eurreur.

			} else {
				//vole du personnage
				
				for(int i=0; i<getPlateau().getNombreJoueurs(); i++) {
					if(getPlateau().getJoueur(i).getPersonnage().getNom().equals("Voleur")){
						getPlateau().getJoueur(i).ajouterPieces(getPlateau().getJoueur(choix - 1).nbPieces());
						getPlateau().getPersonnage(choix - 1).setVole();
						
						getPlateau().getJoueur(choix - 1).tresor = 0;
						
						//on remet vole à false
						getPlateau().getJoueur(choix - 1).getPersonnage().setVole();
						
						continu = false;
						
						
					}
				}
				

				
			}

		} while (continu);
	}

	@Override
	public void percevoirRessourcesSpecifiques() {
		// TODO Auto-generated method stub

	}

}
