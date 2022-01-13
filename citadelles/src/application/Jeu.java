package application;

import java.util.Random;

import citadelles.modele.*;
import controleur.Interaction;

public class Jeu {

	private PlateauDeJeu plateauDeJeu;
	private int numeroConfiguration;
	private Random generateur;

	public Jeu() {

		this.plateauDeJeu = null;
		this.numeroConfiguration = 0;
		this.generateur = new Random();
	}

	public void jouer() {
		System.out.println("==========BIENVENUE DANS LE JEU CITADELLES DE BRUNO FAIDUTTI============");
		System.out.println("UN GRAND CLASSIQUE DES JEUX DE SOCIÉTÉ");
		System.out.println("JEU DE CARTES \n 2 A 8 JOUEURS \n 60 MINUTES");

		System.out.println("Votre choix:");
		System.out.println(1 + " Jouer une partie"); // Boutton
		System.out.println(2 + " Afficher les règles"); // boutton
		System.out.println(3 + " Quitter"); // boutton

		numeroConfiguration = Interaction.lireUnEntier(0, 3);

		if (numeroConfiguration + 1 == 1) {
			jouerPartie();
		} else if (numeroConfiguration + 1 == 2) {
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
		initialisation();
		if (partieFinie()) {
			calculDesPoints();
			initialisation();
		} else {
			tourDeJeu();
		}

	}

	private void initialisation() {

		plateauDeJeu = Configuration.configurationDeBase(Configuration.nouvellePioche());
	}

	private void gestionCouronne() {
		plateauDeJeu.getJoueur(0).setPossedeCouronne(true);
	}

	private void reinitialisationPersonnages() {

	}

	private boolean partieFinie() {
		for (int i = 0; i < plateauDeJeu.getNombreJoueurs(); i++) {
			if (plateauDeJeu.getJoueur(i).getCite().length == 8) {
				System.out.println("Partie terminer");
				System.out.println(plateauDeJeu.getJoueur(i).getNom() + " remporte la partie");
				return true;
			}
		}
		return false;
	}

	private void tourDeJeu() {
		choixPersonnages();
		System.out.println("Le joueur qui à la coronne commence");
	}

	private void choixPersonnages() {
		int[] nextPersonnage = new int[3];
		int choixPersonnage;
		boolean continu = false;

		System.out.println("Choix des personnages :");

		// recupération aléatoire de trois nombre pour la sélection des joueurs écartés
		for (int i = 0; i < nextPersonnage.length; i++) {
			nextPersonnage[i] = generateur.nextInt(9);
		}

		System.out.println(
				"Le personnage " + plateauDeJeu.getPersonnage(nextPersonnage[0]).getNom() + " est écarté face visible");
		System.out.println(
				"Le personnage " + plateauDeJeu.getPersonnage(nextPersonnage[1]).getNom() + " est écarté face visible");
		System.out.println("Un personnage est écarté face cachée");
		System.out.println("Vous avez la couronne !");
		gestionCouronne();

		for (int j = 0; j < plateauDeJeu.getNombreJoueurs(); j++) {
			if (j != nextPersonnage[0] && j != nextPersonnage[1] && j != nextPersonnage[2]) {
				System.out.println((j + 1) + " " + plateauDeJeu.getPersonnage(j).getNom());

			}
		}
		System.out.println("Quel personnage choisissez-vous ? ");
		do {
			choixPersonnage = Interaction.lireUnEntier(1, 9);

			int i = 0;

			if (choixPersonnage != nextPersonnage[0 + 1] && choixPersonnage != nextPersonnage[1 + 1] && choixPersonnage != nextPersonnage[2 + 1]) {
				
				plateauDeJeu.getPersonnage(choixPersonnage - 1).setJoueur(plateauDeJeu.getJoueur(i));
				
				if (i < plateauDeJeu.getNombreJoueurs()) {
					i++;
					System.out.println(plateauDeJeu.getJoueur(i)+" faites votre choix du personnage");
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

	}

	private void calculDesPoints() {
		System.out.println("Partie terminer, calcul du score");
		for (int i = 0; i < plateauDeJeu.getNombreJoueurs(); i++) {
			System.out.println("1 " + plateauDeJeu.getJoueur(i).getNom() + ": Nombre de pièce total("
					+ plateauDeJeu.getJoueur(i).nbPieces() + ")");
		}

	}

}
