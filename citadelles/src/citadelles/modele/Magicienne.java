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
		
		//liste qui va contenir la copie des quartiers dans la main de la magicienne
		ArrayList<Quartier> copieTableauMagicienne = new ArrayList<Quartier>();
		
		//liste qui va contenir la copie des quartiers dans la main du joueur à voler
		ArrayList<Quartier> copieTableauJoueurVole = new ArrayList<Quartier>();

		boolean continu = false;
		boolean continuChoixDuJoueur = false;
		
		do {
			System.out.print("Voulez-vous échanger vos cartes avec celles d’un autre joueur ?o\\n\n");
			Boolean choix = Interaction.lireOuiOuNon();

			//si le joueur veut échanger ses cartes avec celles d'un joueur dans le plateau de jeu
			if (choix) {
				for(int i=0; i<getPlateau().getNombreJoueurs(); i++) {
					System.out.println((i+1)+") "+getPlateau().getJoueur(i).getNom()+", nombre de quartier(s) => "+
																getPlateau().getJoueur(i).nbQuartiersDansMain());
				}
				
				//boucle pour faire le choix du joueur que le magicient veut voler
				do {
					System.out.print("Choisissez un joueur différent de vous: ");
					int joueurChoisit = Interaction.lireUnEntier(1, getPlateau().getNombreJoueurs() + 1);
					
					if(getPlateau().getPersonnage(joueurChoisit  - 1).getNom().equals("Magicienne")) {
						System.out.println("Vous ne pouvez pas vous choisir");
						
						//relancer la boucle
						continuChoixDuJoueur = true;
					}
					else {
						
						//copie du tableau des quartiers du joueur à voler dans la variable copieTableauJoueurVole
						for(int i=0; i<getPlateau().getJoueur(joueurChoisit - 1).nbQuartiersDansMain() ; i++) {
							copieTableauJoueurVole.add(getPlateau().getJoueur(joueurChoisit - 1).getMain().get(i));
						}
						
						//vider le tableau des quartiers du joueur volé
						getPlateau().getJoueur(joueurChoisit - 1).main = null;
						
						//copie du tableau des quartiers de la magicienne
						for(int i=0; i<getPlateau().getNombreJoueurs(); i++) {
							if(getPlateau().getPersonnage(i).getNom().equals("Magicienne")) {
								
								//dans le cas où la main de la magicienne est vide
								if(getPlateau().getJoueur(i).getMain() == null){
									
									//copie de tous les quartiers dans la main de la magicienne dans copieTableauMagicienne
									copieTableauMagicienne.addAll(getPlateau().getJoueur(joueurChoisit - 1).getMain());
									
									//on ajoute dans la main de la magicienne toute la main du joueur à voler
									getPlateau().getJoueur(i).main.addAll(copieTableauJoueurVole);
								}
								
								//dans le cas où la main de la magicienne n'est pas vide
								else {
									//copie de tous les quartiers dans la main de la magicienne dans copieTableauMagicienne
									copieTableauMagicienne.addAll(getPlateau().getJoueur(i).getMain());
																	
									//vider la liste des quartiers de la magicienne
									getPlateau().getJoueur(i).main = null;
									
									System.out.println(copieTableauMagicienne);
									
									//on ajoute copieTableauJoueurVole qui contient l'ensemble des quartiers du joueur volé
									//dans la main actulelle de la magicienne
									getPlateau().getJoueur(i).main.addAll(copieTableauJoueurVole);
									
									//on remplace la main du joueur volé par celle de la magicienne
									getPlateau().getJoueur(joueurChoisit - 1).main.addAll(copieTableauMagicienne);
									
									
								}
								
								
							}
						}
						
						//on sort de la boucle
						continuChoixDuJoueur = true;
					}
				}while(continuChoixDuJoueur);
				
				
				
				
			} else {
				System.out.print("Combien de cartes voulez-vous prendre dans la pioche ? ");
				Interaction.lireUnEntier(1, getJoueur().nbQuartiersDansMain() + 1);
				
				System.out.println("Voici les cartes de votre main : ");
				for(int i = 0; i<getJoueur().nbQuartiersDansMain(); i++) {
					System.out.println((i+1)+" "+getJoueur().getMain().get(i).getNom()
												+" - type: "+getJoueur().getMain().get(i).getType()
												+" - pièce(s): "+getJoueur().getMain().get(i).getCout());
				}
				
				System.out.println("Quel est le numéro de la carte que vous voulez retirer ? ");
				int numRetirer = Interaction.lireUnEntier(1, getJoueur().nbQuartiersDansMain() + 1);
				
				
				for(int i = 0; i<getJoueur().nbQuartiersDansMain(); i++) {
					
					if(i != numRetirer) {
						copieTableauMagicienne.add(getJoueur().getMain().get(i));
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
