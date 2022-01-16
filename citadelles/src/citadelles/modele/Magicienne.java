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

		// liste qui va contenir la copie des quartiers dans la main du joueur à voler
		ArrayList<Quartier> copieTableauJoueurVole = new ArrayList<Quartier>();

		boolean continu = false;
		boolean continuChoixDuJoueur = false;

		do {
			System.out.print("Voulez-vous échanger vos cartes avec celles d’un autre joueur ?o\\n\n");
			Boolean choix = Interaction.lireOuiOuNon();

			// si le joueur veut échanger ses cartes avec celles d'un joueur dans le plateau de jeu
			if (choix) {
				
				// Affichage des joueurs présents dans le plateau de jeu
				for (int i = 0; i < getPlateau().getNombreJoueurs(); i++) {
					System.out.println((i + 1) + ") " + getPlateau().getJoueur(i).getNom()
							+ ", nombre de quartier(s) => " + getPlateau().getJoueur(i).nbQuartiersDansMain());
				}

				// boucle pour faire le choix du joueur que le magicient veut voler
				do {
					System.out.print("Choisissez un joueur différent de vous: ");
					int joueurChoisit = Interaction.lireUnEntier(1, getPlateau().getNombreJoueurs() + 1);

					if (getPlateau().getJoueur(joueurChoisit - 1).getPersonnage().getNom().equals("Magicienne")) {
						System.out.println("Vous ne pouvez pas vous choisir");

						// relancer la boucle
						continuChoixDuJoueur = true;
					} else {

						// copie de la liste des quartiers du joueur à voler dans la variable copieTableauJoueurVole
						for (int i = 0; i < getPlateau().getJoueur(joueurChoisit - 1).nbQuartiersDansMain(); i++) {
							copieTableauJoueurVole.add(getPlateau().getJoueur(joueurChoisit - 1).getMain().get(i));
						}

						// copie de la liste des quartiers du joueur dans la liste des quartiers de la magicienne
						for (int i = 0; i < getPlateau().getNombreJoueurs(); i++) {
							if (getPlateau().getJoueur(i).getPersonnage().getNom().equals("Magicienne")) {

								// dans le cas où la main de la magicienne est vide
								if (getPlateau().getJoueur(i).getMain().isEmpty()) {

									// copie de la liste des quartiers de la magicienne dans copieTableauMagicienne
									copieTableauMagicienne.addAll(getPlateau().getJoueur(joueurChoisit - 1).getMain());

									// vider le tableau des quartiers du joueur volé
									getPlateau().getJoueur(joueurChoisit - 1).main.clear();

									// on ajoute dans la main de la magicienne toute la main du joueur à voler
									getPlateau().getJoueur(i).main.addAll(copieTableauJoueurVole);
								}

								// dans le cas où la main de la magicienne n'est pas vide
								else {

//									System.out.println(getPlateau().getJoueur(i).main);
//									System.out.println(getPlateau().getJoueur(joueurChoisit - 1).main);

									// copie de la liste des quartiers de la magicienne dans copieTableauMagicienne
									copieTableauMagicienne.addAll(getPlateau().getJoueur(i).getMain());

									// vider la liste des quartiers de la magicienne
									getPlateau().getJoueur(i).main.clear();

									// vider le tableau des quartiers du joueur volé
									getPlateau().getJoueur(joueurChoisit - 1).main.clear();

									// on ajoute copieTableauJoueurVole qui contient l'ensemble des quartiers du
									// joueur volé dans la main actulelle de la magicienne
									getPlateau().getJoueur(i).main.addAll(copieTableauJoueurVole);

									// on remplace la main du joueur volé par celle de la magicienne
									getPlateau().getJoueur(joueurChoisit - 1).main.addAll(copieTableauMagicienne);

//									System.out.println(getPlateau().getJoueur(i).main);
//									System.out.println(getPlateau().getJoueur(joueurChoisit - 1).main);

								}

							}
						}

						// on sort de la boucle
						continuChoixDuJoueur = false;
					}
				} while (continuChoixDuJoueur);

			} else {
				for (int i = 0; i < getPlateau().getNombreJoueurs(); i++) {
					if (getPlateau().getJoueur(i).getPersonnage().getNom().equals("Magicienne")) {

						// si la main de la maicienne est vide, on ne fait rien
						if (getPlateau().getJoueur(i).getMain().isEmpty()) {
							System.out.println("Vous n'avez aucune carte dans votre main à échanger. ");
						}

						// dans le cas contraire on procède à l'échange
						else {
							System.out.print("Combien de cartes voulez-vous prendre dans la pioche ? ");
							int nbCarte = Interaction.lireUnEntier(0, getPlateau().getJoueur(i).nbQuartiersDansMain() + 1);
							
							System.out.println("Voici les cartes dans votre main : ");
							for (int j = 0; j < getPlateau().getJoueur(i).nbQuartiersDansMain(); j++) {
								System.out.println((j + 1) + " " + getPlateau().getJoueur(i).getMain().get(j).getNom() + " - type: "
										+ getPlateau().getJoueur(i).getMain().get(j).getType() + " - pièce(s): "
										+ getPlateau().getJoueur(i).getMain().get(j).getCout());
							}
							
							
							//si la magicienne veut échanger 0 carte
							if(nbCarte == 0) {
								System.out.println("Aucun échange de carte avec la pioche ");
							}
							
							//si la magicienne veut échanger toutes les cartes presentes dans sa main
							else if(nbCarte == getPlateau().getJoueur(i).nbQuartiersDansMain()) {
//								System.out.println("teste nb cartes égaux");
								// copie de la liste des quartiers de la magicienne dans copieTableauMagicienne
								copieTableauMagicienne.addAll(getPlateau().getJoueur(i).getMain());
								
								// vider la liste des quartiers dans la main de la magicienne
								getPlateau().getJoueur(i).main.clear();
								
								for(int k = 0; k<nbCarte; k++) {
									
									//ajouter la liste des quartiers de la magicienne dans la pioche
									getPlateau().getPioche().ajouter(copieTableauMagicienne.get(k));
									
									//piocher les nouvelles cartes quartier et les ajouter dans la main de la magicienne
									getPlateau().getJoueur(i).main.add(getPlateau().getPioche().piocher());
									
								}
								
								//Affichage de la nouvelle des quartiers dans la main de la magicienne
								for (int j = 0; j < getPlateau().getJoueur(i).nbQuartiersDansMain(); j++) {
									System.out.println((j + 1) + " " + getJoueur().getMain().get(j).getNom() + " - type: "
											+ getPlateau().getJoueur(i).getMain().get(j).getType() + " - pièce(s): "
											+getPlateau().getJoueur(i).getMain().get(j).getCout());
								}
							}
							
							//si la magicienne veut échanger un nombre défini des cartes présentes dans sa main
							else {
								// copie de la liste des quartiers de la magicienne dans copieTableauMagicienne
								copieTableauMagicienne.addAll(getPlateau().getJoueur(i).getMain());
								
								
								
								System.out.println("faites vos choix des cartes à echanger: ");
								for (int k = 0; k < nbCarte; k++) {
									System.out.print("choix"+(k+1)+": ");
									int numRetirer = Interaction.lireUnEntier(1, getPlateau().getJoueur(i).nbQuartiersDansMain() + 1);
									getPlateau().getPioche().ajouter(copieTableauMagicienne.get(numRetirer - 1));
									copieTableauMagicienne.remove(numRetirer - 1);
									
									//une nouvelle carte quartier et l'ajouter dans la main de la magicienne
									copieTableauMagicienne.add(getPlateau().getPioche().piocher());
									
								}
								
								// vider la liste des quartiers dans la main de la magicienne
								getJoueur().main.clear();
								
								//Ajout de toutes les cartes de la copie dans la main originale de la magicienne
								getJoueur().main.addAll(copieTableauMagicienne);
								
								
								//Affichage de la nouvelle des quartiers dans la main de la magicienne
								for (int j = 0; j < getPlateau().getJoueur(i).nbQuartiersDansMain(); j++) {
									System.out.println((j + 1) + " " + getPlateau().getJoueur(i).getMain().get(j).getNom() + " - type: "
											+ getPlateau().getJoueur(i).getMain().get(j).getType() + " - pièce(s): "
											+ getPlateau().getJoueur(i).getMain().get(j).getCout());
								}
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
		
		System.out.print("------------------\n");
	}

}
