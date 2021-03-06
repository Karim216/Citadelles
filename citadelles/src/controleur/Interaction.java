package controleur;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Interaction {
	private static Scanner sc = new Scanner(System.in);

	public static int lireUnEntier() {
		int i = 0;
		boolean continu = true;
		do {
			try {
				i = sc.nextInt();
				continu = false;
			} catch (InputMismatchException e) {
				System.out.print("Veuillez rentrer un chiffre : ");
				sc.next(); // passe l'entier pour éviter de boucler
			}
		} while (continu);
		return i;
	}

	// renvoie un entier lu au clavier compris dans l'intervalle
	// [borneMin, borneMax[
	public static int lireUnEntier(int borneMin, int borneMax) {
		int i = 0;
		// ...
		boolean continu = true;
		do {
			i = lireUnEntier();
			if (i >= borneMin && i < borneMax) {
				continu = false;
			} else {
				// continu = true;
				System.out.print("L'entier doit être dans l'intevalle [" + borneMin + ";" + borneMax + "[");
				System.out.print("\nEntrez un chiffre : ");
				// sc.next(); // passe l'entier pour éviter de boucler
			}
		} while (continu);
		return i;
	}

	// lit les réponses "oui", "non", "o" ou "n" et renvoie un booléen
	public static boolean lireOuiOuNon() {
		boolean retour = true;
		boolean continu = true;
		// ...
		do {
			String reponse = lireUneChaine().toLowerCase();
			// System.out.print(reponse);
			if (reponse.equals("oui") == true || reponse.equals("o") == true) {
				retour = true;
				continu = false;
			} else if (reponse.equals("non") == true || reponse.equals("n") == true) {
				retour = false;
				continu = false;
			} else {
				System.out.print("Veuillez rentrer \"oui\", \"o\", \"non\" ou \"n\" : ");
			}
		} while (continu);

		return retour;
	}

	// renvoie une chaine de caractère lue au clavier:
	public static String lireUneChaine() {
		String retour = "";
		boolean continu = true;
		do {
			try {
				retour = sc.nextLine();
				continu = false;
			} catch (InputMismatchException e) {
				System.out.print("Votre entrée doit être un caractère ou une chaine de caractère : ");
				sc.next(); // passe l'entier pour éviter de boucler
			}
		} while (continu);
		return retour;
	}

}
