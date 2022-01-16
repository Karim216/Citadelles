package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import citadelles.modele.*;
import controleur.Interaction;

public class Jeu {

	private PlateauDeJeu plateauDeJeu;
	private int numeroConfiguration;
	private Random generateur;
	
	//varible pour recupérer le joueur
	private int numJoueur;

	public Jeu() {

		this.plateauDeJeu = null;
		this.numeroConfiguration = 0;
		this.generateur = new Random();
	}

	public PlateauDeJeu getPlateauDeJeu() {
		return this.plateauDeJeu;
	}
	
	public void jouer() {
		System.out.println("==========BIENVENUE DANS LE JEU CITADELLES DE BRUNO FAIDUTTI============");
		System.out.println("UN GRAND CLASSIQUE DES JEUX DE SOCIÉTÉ");
		System.out.println("JEU DE CARTES \n 2 A 8 JOUEURS \n 60 MINUTES");

		System.out.println("Votre choix:");
		System.out.println(1 + " Jouer une partie"); // Boutton
		System.out.println(2 + " Afficher les règles"); // boutton
		System.out.println(3 + " Quitter"); // boutton

		numeroConfiguration = Interaction.lireUnEntier(1, 4);

		if (numeroConfiguration == 1) {
			jouerPartie();
		} else if (numeroConfiguration == 2) {
			afficherLesRegles();
		} else {
			System.out.println("Quitter");
			partieFinie();
		}
	}

	private void afficherLesRegles() {
		System.out.println("COMPOSITION DU JEU");
		System.out.println("REGLE DU JEU:");
		
		System.out.println("Lancer une partie ? ");
		Boolean choix = Interaction.lireOuiOuNon();
		
		if(choix) {
			jouerPartie();
		}
		else {
			System.out.println("Quitter");
			partieFinie();
		}
	}

	private void jouerPartie() {
		//int nbPartie = 0;
		initialisation();
		if (partieFinie()) {
			calculDesPoints();
			initialisation();
		} else {
			//nbPartie++;
//			if(nbPartie + 1 == 1) {
//				System.out.println((nbPartie+1) +"ère partie");
//			}
//			else {
//				System.out.println((nbPartie+1) +"ème partie");
//			}
			
			tourDeJeu();
		}
		
		

	}

	private void initialisation() {

		
		plateauDeJeu = Configuration.configurationDeBase(Configuration.nouvellePioche());
		plateauDeJeu.setPioche(Configuration.nouvellePioche());
		plateauDeJeu.getPioche().melanger();
		
		for(int i=0; i<plateauDeJeu.getNombreJoueurs(); i++) {
			plateauDeJeu.getJoueur(i).ajouterPieces(2);
			for(int j=0; j<4; j++) {
				plateauDeJeu.getJoueur(i).ajouterQuartierDansMain(plateauDeJeu.getPioche().piocher());
				
			}
		}
		
		plateauDeJeu.getJoueur(0).setPossedeCouronne(true);
	}

	
	private void gestionCouronne() {
	
		for(int i=0; i<plateauDeJeu.getNombreJoueurs(); i++) {
			if(plateauDeJeu.getJoueur(i).getPersonnage().getNom().equals("Roi")) {
				plateauDeJeu.getJoueur(i).setPossedeCouronne(true);
			}
			else {
				plateauDeJeu.getJoueur(i).setPossedeCouronne(false);
			}
			
		}
	}

	private void reinitialisationPersonnages() {
		for(int i=0; i<plateauDeJeu.getNombreJoueurs(); i++) {
			plateauDeJeu.getJoueur(i).getPersonnage().reinitialiser(i);
		}
		
		System.out.println("Réinitialisation des personnages");
		for(int i=0; i<plateauDeJeu.getNombreJoueurs(); i++) {
			
			System.out.println("\t"+plateauDeJeu.getJoueur(i).getNom()+": "+plateauDeJeu.getJoueur(i).getPersonnage()+
								"Personnage("+plateauDeJeu.getJoueur(i).getPersonnage()+") "+
								"Trésor("+plateauDeJeu.getJoueur(i).nbPieces()+") ");
		}
	}

	private boolean partieFinie() {
		try {
			for (int i = 0; i < plateauDeJeu.getNombreJoueurs(); i++) {
				if (plateauDeJeu.getJoueur(i).nbQuartiersDansCite() == 8) {
					System.out.println("Partie terminer");
					System.out.println(plateauDeJeu.getJoueur(i).getNom() + " remporte la partie");
					return true;
				}
			}
		}catch(NullPointerException e) {
			System.out.print("Aucun joueur pour une partie");
		}
		return false;
		
		
	}

	private void tourDeJeu() {
		boolean choixPouvoir;
		boolean choixConstruire;
		choixPersonnages();
		System.out.println("\nLe joueur qui à la couronne commence");
		
		for(int i=0; i<plateauDeJeu.getNombreJoueurs(); i++) {
			//System.out.println(plateauDeJeu.getJoueur(0).getPersonnage());
			if(!plateauDeJeu.getJoueur(i).getPersonnage().getAssassine()) {
				System.out.println("\n####################"+plateauDeJeu.getJoueur(i).getPersonnage().getNom()+"##########################\n");
				numJoueur = i;
				percevoirRessource();
				plateauDeJeu.getJoueur(i).getPersonnage().percevoirRessourcesSpecifiques();
				
				System.out.println("Vos ressources");
				System.out.println(plateauDeJeu.getJoueur(i).nbPieces()+" pièce(s)");
				System.out.println("Main:");
				for(int j=0; j<plateauDeJeu.getJoueur(i).nbQuartiersDansMain(); j++) {
					System.out.println("\t"+(j + 1) + " " + plateauDeJeu.getJoueur(i).getMain().get(j).getNom() + " - type: "
							+ plateauDeJeu.getJoueur(i).getMain().get(j).getType() + " - coût: "
							+ plateauDeJeu.getJoueur(i).getMain().get(j).getCout());
				}

				System.out.println("Cité:");
				if(plateauDeJeu.getJoueur(i).nbQuartiersDansCite() > 0) {
					for(int j=0; j<plateauDeJeu.getJoueur(i).nbQuartiersDansCite(); j++) {
						System.out.println("Cité:\t"+(j + 1) + " " + plateauDeJeu.getJoueur(i).getCite()[j].getNom() + " - type: "
								+ plateauDeJeu.getJoueur(i).getCite()[j].getType() + " - pièce(s): "
								+ plateauDeJeu.getJoueur(i).getCite()[j].getCout());
					}
				}
				else {
					System.out.println("\tVous n'avez pas encore contruit dans vote cité\n");
				}
				
				//System.out.println("Carte(s) "+plateauDeJeu.getJoueur(i));
				
				System.out.println("Souhaitez-vous utiliser votre pouvoir ? ");
				choixPouvoir = Interaction.lireOuiOuNon();
				
				if(choixPouvoir) {
					plateauDeJeu.getJoueur(i).getPersonnage().utiliserPouvoir();
					
				}
				
				System.out.println("\nVoulez-vous construire ? ");
				choixConstruire = Interaction.lireOuiOuNon();
				if(choixConstruire) {
					
					if(plateauDeJeu.getJoueur(i).getPersonnage().getNom().equals("Architecte")) {
						System.out.println("Vous avez la possibilité de contruire trois quartiers successif dans votre cité ");
						int nbQ = 0;
						
						//Cas de l'architecte, construction de trois quartiers successif
						do {
							System.out.println("Choisissez le quartier à construire dans votre cité ");
							for (int j = 0; j < plateauDeJeu.getJoueur(i).nbQuartiersDansMain(); j++) {
								System.out.println("\t"+(j + 1) + " " + plateauDeJeu.getJoueur(i).getMain().get(j).getNom() + " - type: "
										+ plateauDeJeu.getJoueur(i).getMain().get(j).getType() + " - coût: "
										+ plateauDeJeu.getJoueur(i).getMain().get(j).getCout());
							}
							
							System.out.print("Votre choix: ");
							int valeur = Interaction.lireUnEntier(1, plateauDeJeu.getJoueur(i).nbQuartiersDansMain() + 1);
							plateauDeJeu.getJoueur(i).getPersonnage().construire(plateauDeJeu.getJoueur(i).getMain().get(valeur - 1), i);
							
//							plateauDeJeu.getJoueur(i).getPersonnage().construire(plateauDeJeu.getPioche().piocher());
							System.out.println("\nVotre cité : ");
							for (int j = 0; j < plateauDeJeu.getJoueur(i).nbQuartiersDansCite(); j++) {
								System.out.println("\t"+(j + 1) +" "+ plateauDeJeu.getJoueur(i).getCite()[j].getNom());
							}
							
							System.out.println("Votre main ");
							for (int j = 0; j < plateauDeJeu.getJoueur(i).nbQuartiersDansMain(); j++) {
								System.out.println("\t"+(j + 1) + " " + plateauDeJeu.getJoueur(i).getMain().get(j).getNom() + " - type: "
										+ plateauDeJeu.getJoueur(i).getMain().get(j).getType() + " - coût: "
										+ plateauDeJeu.getJoueur(i).getMain().get(j).getCout());
							}
							
							nbQ++;
						}while(nbQ < 3);
					}
					else {
						System.out.println("Choisissez le quartier à construire dans votre cité ");
						for (int j = 0; j < plateauDeJeu.getJoueur(i).nbQuartiersDansMain(); j++) {
							System.out.println("\t"+(j + 1) + " " + plateauDeJeu.getJoueur(i).getMain().get(j).getNom() + " - type: "
									+ plateauDeJeu.getJoueur(i).getMain().get(j).getType() + " - coût: "
									+ plateauDeJeu.getJoueur(i).getMain().get(j).getCout());
						}
						
						System.out.print("Votre choix: ");
						int valeur = Interaction.lireUnEntier(1, plateauDeJeu.getJoueur(i).nbQuartiersDansMain() + 1);
						plateauDeJeu.getJoueur(i).getPersonnage().construire(plateauDeJeu.getJoueur(i).getMain().get(valeur - 1), i);
						
//						plateauDeJeu.getJoueur(i).getPersonnage().construire(plateauDeJeu.getPioche().piocher());
						System.out.println("\nVotre cité : ");
						for (int j = 0; j < plateauDeJeu.getJoueur(i).nbQuartiersDansCite(); j++) {
							System.out.println("\t"+(j + 1) +" "+ plateauDeJeu.getJoueur(i).getCite()[j].getNom());
						}
						
						System.out.println("Votre main ");
						for (int j = 0; j < plateauDeJeu.getJoueur(i).nbQuartiersDansMain(); j++) {
							System.out.println("\t"+(j + 1) + " " + plateauDeJeu.getJoueur(i).getMain().get(j).getNom() + " - type: "
									+ plateauDeJeu.getJoueur(i).getMain().get(j).getType() + " - coût: "
									+ plateauDeJeu.getJoueur(i).getMain().get(j).getCout());
						}
					}
					
				}
			}
			else {
				System.out.println("On vous a assassiné, passez votre tour");
			}
		}
		
		gestionCouronne();
		reinitialisationPersonnages();
		jouerPartie();
	}

	private void choixPersonnages() {
//		int[] nextPersonnage = new int[3];
		ArrayList<Integer> nextPersonnage = new ArrayList<Integer>();
		int choixPersonnage;
		
		boolean continu = false;

		System.out.println("Choix des personnages :");

		// recupération aléatoire de trois nombre pour la sélection des personnages écartés
//		for (int i = 0; i < nextPersonnage.length; i++) {
//			nextPersonnage[i] = generateur.nextInt(9);
//		}
		
		 
		
        for (int i=0; i<8; i++) {
        	nextPersonnage.add(i);
        }
        Collections.shuffle(nextPersonnage);
//        for(int j=0; j<nextPersonnage.size(); j++) {
//			
//			System.out.print(nextPersonnage.get(j)+" ");
//		}
//        System.out.println(" ");
        for(int i=0; i<5; i++) {
        	nextPersonnage.remove(nextPersonnage.size() - 1);
        }
        
//        for(int j=0; j<nextPersonnage.size(); j++) {
//			
//			System.out.print(nextPersonnage.get(j)+" ");
//		}

		System.out.println(
				"Le personnage " + plateauDeJeu.getPersonnage(nextPersonnage.get(0)).getNom() + " est écarté face visible");
		System.out.println(
				"Le personnage " + plateauDeJeu.getPersonnage(nextPersonnage.get(1)).getNom() + " est écarté face visible");
		System.out.println("Un personnage est écarté face cachée");
		System.out.println("Vous avez la couronne !");

		for (int j = 0; j < plateauDeJeu.getNombrePersonnages(); j++) {
			if (j != nextPersonnage.get(0) && j != nextPersonnage.get(1) && j != nextPersonnage.get(2)) {
				System.out.println((j + 1) + " " + plateauDeJeu.getPersonnage(j).getNom());

			}
		}
		
		System.out.print("\nQuel personnage choisissez-vous ? ");
		int i = 0;
		do {
			choixPersonnage = Interaction.lireUnEntier(1, 9);
			
			int per = 0;
			for(int j=0; j<nextPersonnage.size(); j++) {
				if(nextPersonnage.get(j)+1 != choixPersonnage) {
					//System.out.print(nextPersonnage.get(j)+" = "+choixPersonnage+"\n");
					per++;
				}
				else {
					per -= 8;
				}
				
			}
			if(per > 0) {
				
				nextPersonnage.add(choixPersonnage-1);
				//plateauDeJeu.getPersonnage(choixPersonnage - 1).setJoueur(plateauDeJeu.getJoueur(i));
				
				plateauDeJeu.getJoueur(i).setPersonnage(plateauDeJeu.getPersonnage(choixPersonnage - 1));
				//plateauDeJeu.getPersonnage(choixPersonnage - 1).setJoueur(plateauDeJeu.getJoueur(i));
				
				System.out.print("\nVous avez choisit '"+plateauDeJeu.getPersonnage(choixPersonnage - 1).getNom()+"'");
				
				i++;
				if (i < plateauDeJeu.getNombreJoueurs()) {
					
					System.out.print("\nReste des personnages :\n");
//					for(int j=0; j<plateauDeJeu.getNombrePersonnages(); j++) {
//						if(nextPersonnage.get(j) != j) {
//							System.out.println((j + 1) + " " + plateauDeJeu.getPersonnage(j).getNom());
//						}
//							
//					}
					System.out.println(plateauDeJeu.getJoueur(i).getNom()+" faites votre choix du personnage");
					continu = true;
				}
				else {
					continu = false;
				}

			} else {
				System.out.println("Ce personnage est déjà écarté ");
				System.out.println("Autre choix ");
				continu = true;
			}
			//}
			//if (choixPersonnage != nextPersonnage.get(0)+1 && choixPersonnage != nextPersonnage.get(1)+1 && choixPersonnage != nextPersonnage.get(2)+1) {
			
		} while (continu);

	}

	private void percevoirRessource() {
		int choixRessource;
		int choixQuartier;
		Quartier q1;
		Quartier q2;
		
		//if(numJoueur == 0) {
			System.out.println("Faites un choix pour percevoir vos ressources ");
			System.out.println("1 pour deux pièces d'or");
			System.out.println("2 pour piocher une carte ");
			choixRessource = Interaction.lireUnEntier(1, 3);
			if(choixRessource == 1) {
				plateauDeJeu.getJoueur(numJoueur).ajouterPieces(2);
			}
			else {
				q1 = plateauDeJeu.getPioche().piocher();
				q2 = plateauDeJeu.getPioche().piocher();
				System.out.println("Choisisser une parmi les deux cartes suivante");
				System.out.println("1 "+q1.getNom());
				System.out.println("2 "+q2.getNom());
				choixQuartier = Interaction.lireUnEntier(1, 3);
				
				if(choixQuartier == 1) {
					plateauDeJeu.getJoueur(numJoueur).ajouterQuartierDansMain(q1);
					plateauDeJeu.getPioche().ajouter(q2);
				}
				else {
					plateauDeJeu.getJoueur(numJoueur).ajouterQuartierDansMain(q2);
					plateauDeJeu.getPioche().ajouter(q1);
				}
				
			}
			
//		}
//		else {
//			for(int i=0; i<plateauDeJeu.getNombreJoueurs() - 1; i++) {
//				if(generateur.nextInt(2) == 0) {
//					plateauDeJeu.getJoueur(numJoueur).ajouterPieces(2);
//				}
//				else {
//					plateauDeJeu.getJoueur(numJoueur).ajouterQuartierDansMain(plateauDeJeu.getPioche().piocher());
//				}
//			}		
//		
//		}
//		
	}

	private void calculDesPoints() {
		System.out.println("Partie terminer, calcul du score");
		for (int i = 0; i < plateauDeJeu.getNombreJoueurs(); i++) {
			System.out.println((i + 1) +" "+ plateauDeJeu.getJoueur(i).getNom() + ": Nombre de pièce total("
					+ plateauDeJeu.getJoueur(i).nbPieces() + ")");
		}

	}
}
