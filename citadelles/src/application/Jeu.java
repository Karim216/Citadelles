package application;

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
		}
	}

	private void afficherLesRegles() {
		System.out.println("COMPOSITION DU JEU");
		System.out.println("REGLE DU JEU:");
	}

	private void jouerPartie() {
		int nbPartie = 0;
		initialisation();
		if (partieFinie()) {
			calculDesPoints();
			initialisation();
		} else {
			nbPartie++;
			if(nbPartie + 1 == 1) {
				System.out.println((nbPartie+1) +"ère partie");
			}
			else {
				System.out.println((nbPartie+1) +"ème partie");
			}
			
			tourDeJeu();
		}
		
		

	}

	private void initialisation() {

		
		plateauDeJeu = Configuration.configurationDeBase(Configuration.nouvellePioche());
		plateauDeJeu.getPioche().melanger();
		plateauDeJeu.setPioche(Configuration.nouvellePioche());
		
		for(int i=0; i<plateauDeJeu.getNombreJoueurs(); i++) {
			plateauDeJeu.getJoueur(i).ajouterPieces(2);
			for(int j=0; j<4; j++) {
				plateauDeJeu.getJoueur(i).ajouterQuartierDansMain(plateauDeJeu.getPioche().piocher());
				
			}
		}
		plateauDeJeu.getJoueur(0).setPossedeCouronne(true);
	}

	@SuppressWarnings("unlikely-arg-type")
	private void gestionCouronne() {
	
		for(int i=0; i<plateauDeJeu.getNombreJoueurs(); i++) {
			if(plateauDeJeu.getJoueur(i).getPersonnage().equals("Roi")) {
				plateauDeJeu.getJoueur(i).setPossedeCouronne(true);
			}
			else {
				plateauDeJeu.getJoueur(i).setPossedeCouronne(false);
			}
			
		}
	}

	private void reinitialisationPersonnages() {
		
	}

	private boolean partieFinie() {
		for (int i = 0; i < plateauDeJeu.getNombreJoueurs(); i++) {
			if (plateauDeJeu.getJoueur(i).nbQuartiersDansCite() == 8) {
				System.out.println("Partie terminer");
				System.out.println(plateauDeJeu.getJoueur(i).getNom() + " remporte la partie");
				return true;
			}
		}
		return false;
	}

	private void tourDeJeu() {
		boolean choixPouvoir;
		boolean choixConstruire;
		choixPersonnages();
		System.out.println("Le joueur qui à la couronne commence");
		
		for(int i=0; i<plateauDeJeu.getNombreJoueurs(); i++) {
			System.out.println(plateauDeJeu.getJoueur(i).getPersonnage().getNom());
			if(!plateauDeJeu.getJoueur(i).getPersonnage().getAssassine()) {
				
				numJoueur = i;
				percevoirRessource();
				plateauDeJeu.getJoueur(i).getPersonnage().percevoirRessourcesSpecifiques();
				System.out.println("Souhaitez-vous utiliser votre pouvoir ? ");
				choixPouvoir = Interaction.lireOuiOuNon();
				
				if(choixPouvoir) {
					plateauDeJeu.getJoueur(i).getPersonnage().utiliserPouvoir();
				}
				
				System.out.println("Voulez-vous construire ? ");
				choixConstruire = Interaction.lireOuiOuNon();
				if(choixConstruire) {
					plateauDeJeu.getJoueur(i).getPersonnage().construire(plateauDeJeu.getPioche().piocher());
					
					System.out.println("Voici les cartes dans votre main : ");
					System.out.println("Choisissez le quartier à construire dans votre cité ");
					for (int j = 0; j < plateauDeJeu.getJoueur(i).nbQuartiersDansMain(); j++) {
						System.out.println((j + 1) + " " + plateauDeJeu.getJoueur(i).getMain().get(j).getNom() + " - type: "
								+ plateauDeJeu.getJoueur(i).getMain().get(j).getType() + " - pièce(s): "
								+ plateauDeJeu.getJoueur(i).getMain().get(j).getCout());
					}
					
					int valeur = Interaction.lireUnEntier(1, plateauDeJeu.getJoueur(i).nbQuartiersDansMain() + 1);
					plateauDeJeu.getJoueur(i).getPersonnage().construire(plateauDeJeu.getJoueur(i).getMain().get(valeur - 1));
					
					System.out.println("Voici votre cité : ");
					for (int j = 0; j < plateauDeJeu.getJoueur(i).nbQuartiersDansCite(); j++) {
						System.out.println((j + 1) + " " + plateauDeJeu.getJoueur(i).getCite()[j].getNom());
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
		int[] nextPersonnage = new int[3];
		int choixPersonnage;
		
		boolean continu = false;

		System.out.println("Choix des personnages :");

		// recupération aléatoire de trois nombre pour la sélection des personnages écartés
		for (int i = 0; i < nextPersonnage.length; i++) {
			nextPersonnage[i] = generateur.nextInt(9);
		}

		System.out.println(
				"Le personnage " + plateauDeJeu.getPersonnage(nextPersonnage[0]).getNom() + " est écarté face visible");
		System.out.println(
				"Le personnage " + plateauDeJeu.getPersonnage(nextPersonnage[1]).getNom() + " est écarté face visible");
		System.out.println("Un personnage est écarté face cachée");
		System.out.println("Vous avez la couronne !");

		for (int j = 0; j < plateauDeJeu.getNombrePersonnages(); j++) {
			if (j != nextPersonnage[0] && j != nextPersonnage[1] && j != nextPersonnage[2]) {
				System.out.println((j + 1) + " " + plateauDeJeu.getPersonnage(j).getNom());

			}
		}
		
		System.out.println("Quel personnage choisissez-vous ? ");
		int i = 0;
		do {
			choixPersonnage = Interaction.lireUnEntier(1, 9);

			

			if (choixPersonnage != nextPersonnage[0]+1 && choixPersonnage != nextPersonnage[1]+1 && choixPersonnage != nextPersonnage[2]+1) {
				
				//plateauDeJeu.getPersonnage(choixPersonnage - 1).setJoueur(plateauDeJeu.getJoueur(i));
				
				plateauDeJeu.getJoueur(i).setPersonnage(plateauDeJeu.getPersonnage(choixPersonnage - 1));
				
				System.out.println(plateauDeJeu.getJoueur(i).getPersonnage().getNom()+" est votre personnage");
				
				i++;
				if (i < plateauDeJeu.getNombreJoueurs()) {
					
					System.out.println("Personnages disponible :");
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

		} while (continu);

	}

	private void percevoirRessource() {
		int choixRessource;
		int choixQuartier;
		Quartier q1;
		Quartier q2;
		
		if(numJoueur == 0) {
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
			
		}
		else {
			for(int i=0; i<plateauDeJeu.getNombreJoueurs() - 1; i++) {
				if(generateur.nextInt(2) == 0) {
					plateauDeJeu.getJoueur(numJoueur).ajouterPieces(2);
				}
				else {
					plateauDeJeu.getJoueur(numJoueur).ajouterQuartierDansMain(plateauDeJeu.getPioche().piocher());
				}
			}		
		
		}
		
	}

	private void calculDesPoints() {
		System.out.println("Partie terminer, calcul du score");
		for (int i = 0; i < plateauDeJeu.getNombreJoueurs(); i++) {
			System.out.println((i + 1) +" "+ plateauDeJeu.getJoueur(i).getNom() + ": Nombre de pièce total("
					+ plateauDeJeu.getJoueur(i).nbPieces() + ")");
		}

	}

}
