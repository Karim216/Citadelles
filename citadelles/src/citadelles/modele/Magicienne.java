package citadelles.modele;

import java.util.ArrayList;

import controleur.Interaction;

public class Magicienne extends Personnage {

	public Magicienne() {
		super("Magicienne", 3, Caracteristiques.MAGICIENNE);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void utiliserPouvoir() {
		// TODO Auto-generated method stub

		// liste qui va contenir la copie des quartiers dans la main de la magicienne
		ArrayList<Quartier> copieTableauMagicienne = new ArrayList<Quartier>();

		// liste qui va contenir la copie des quartiers dans la main du joueur � voler
		ArrayList<Quartier> copieTableauJoueurVole = new ArrayList<Quartier>();

		boolean continu = false;
		boolean continuChoixDuJoueur = false;

		do {
			System.out.print("Voulez-vous �changer vos cartes avec celles d�un autre joueur ?o\\n\n");
			Boolean choix = Interaction.lireOuiOuNon();

			// si le joueur veut �changer ses cartes avec celles d'un joueur dans le plateau
			// de jeu
			if (choix) {
				for (int i = 0; i < getPlateau().getNombreJoueurs(); i++) {
					System.out.println((i + 1) + ") " + getPlateau().getJoueur(i).getNom()
							+ ", nombre de quartier(s) => " + getPlateau().getJoueur(i).nbQuartiersDansMain());
				}

				// boucle pour faire le choix du joueur que le magicient veut voler
				do {
					System.out.print("Choisissez un joueur diff�rent de vous: ");
					int joueurChoisit = Interaction.lireUnEntier(1, getPlateau().getNombreJoueurs() + 1);

					if (getPlateau().getPersonnage(joueurChoisit - 1).getNom().equals("Magicienne")) {
						System.out.println("Vous ne pouvez pas vous choisir");

						// relancer la boucle
						continuChoixDuJoueur = true;
					} else {

						// copie d la liste des quartiers du joueur � voler dans la variable
						// copieTableauJoueurVole
						for (int i = 0; i < getPlateau().getJoueur(joueurChoisit - 1).nbQuartiersDansMain(); i++) {
							copieTableauJoueurVole.add(getPlateau().getJoueur(joueurChoisit - 1).getMain().get(i));
						}

						// copie du tableau des quartiers de la magicienne
						for (int i = 0; i < getPlateau().getNombreJoueurs(); i++) {
							if (getPlateau().getPersonnage(i).getNom().equals("Magicienne")) {

								// dans le cas o� la main de la magicienne est vide
								if (getPlateau().getJoueur(i).getMain().isEmpty()) {

									// copie de tous les quartiers dans la main de la magicienne dans
									// copieTableauMagicienne
									copieTableauMagicienne.addAll(getPlateau().getJoueur(joueurChoisit - 1).getMain());

									// vider le tableau des quartiers du joueur vol�
									getPlateau().getJoueur(joueurChoisit - 1).main.clear();

									// on ajoute dans la main de la magicienne toute la main du joueur � voler
									getPlateau().getJoueur(i).main.addAll(copieTableauJoueurVole);
								}

								// dans le cas o� la main de la magicienne n'est pas vide
								else {

									System.out.println(getPlateau().getJoueur(i).main);
									System.out.println(getPlateau().getJoueur(joueurChoisit - 1).main);

									// copie de tous les quartiers dans la main de la magicienne dans
									// copieTableauMagicienne
									copieTableauMagicienne.addAll(getPlateau().getJoueur(i).getMain());

									// vider la liste des quartiers de la magicienne
									getPlateau().getJoueur(i).main.clear();

									// vider le tableau des quartiers du joueur vol�
									getPlateau().getJoueur(joueurChoisit - 1).main.clear();

									// on ajoute copieTableauJoueurVole qui contient l'ensemble des quartiers du
									// joueur vol�
									// dans la main actulelle de la magicienne
									getPlateau().getJoueur(i).main.addAll(copieTableauJoueurVole);

									// on remplace la main du joueur vol� par celle de la magicienne
									getPlateau().getJoueur(joueurChoisit - 1).main.addAll(copieTableauMagicienne);

									System.out.println(getPlateau().getJoueur(i).main);
									System.out.println(getPlateau().getJoueur(joueurChoisit - 1).main);

								}

							}
						}

						// on sort de la boucle
						continuChoixDuJoueur = false;
					}
				} while (continuChoixDuJoueur);

			} else {
				for (int i = 0; i < getPlateau().getNombreJoueurs(); i++) {
					if (getPlateau().getPersonnage(i).getNom().equals("Magicienne")) {

						// si la main de la maicienne est vide, on ne fait rien
						if (getPlateau().getJoueur(i).getMain().isEmpty()) {
							System.out.println("Vous n'avez aucune carte dans votre main � �changer. ");
						}

						// dans le cas contraire on proc�de � l'�change
						else {
							System.out.print("Combien de cartes voulez-vous prendre dans la pioche ? ");
							int nbCarte = Interaction.lireUnEntier(1, getJoueur().nbQuartiersDansMain() + 1);

							System.out.println("Voici les cartes de votre main : ");
							for (int j = 0; j < getJoueur().nbQuartiersDansMain(); j++) {
								System.out.println((j + 1) + " " + getJoueur().getMain().get(j).getNom() + " - type: "
										+ getJoueur().getMain().get(j).getType() + " - pi�ce(s): "
										+ getJoueur().getMain().get(j).getCout());
							}

							
							

							for (int k = 0; k < nbCarte; k++) {
								System.out.println("choix"+(k+1));
								int numRetirer = Interaction.lireUnEntier(1, getJoueur().nbQuartiersDansMain() + 1);
								copieTableauMagicienne.add(getJoueur().getMain().get(numRetirer - 1));
							}
						}
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
