package citadelles.modele;

import citadelles.modele.Personnage;
import controleur.Interaction;

public class Condottiere extends Personnage {

	public Condottiere() {

		super("Condottiere", 8, Caracteristiques.CONDOTTIERE);
	}

	public void utiliserPouvoir() {
		System.out.println("Voulez-vous d�truire un quartier d'une cit� ? ");
		boolean choix1 = Interaction.lireOuiOuNon();
		if (choix1) {
			System.out.println("Voici la liste des joueurs et les quartiers de leur cit�");
			int numCondo = -1;
			int numEveque = -1;
			for (int i = 0; i < getPlateau().getNombreJoueurs(); i++) {
				Joueur j = getPlateau().getJoueur(i);
				Personnage p = getPlateau().getJoueur(i).getPersonnage();
				
//				Joueur j = getPlateau().getPersonnage(i).getJoueur();
//				Personnage p = getPlateau().getPersonnage(i);

				if (p.getNom().equals("Condottiere")) {
					numCondo = i;
				}
				if (p.getNom().equals("Eveque")) {
					numEveque = i;
				}
				
				Quartier[] cite = j.getCite();
				System.out.print(i + 1 + "  " + j.getNom() + " : ");
				if(j.nbQuartiersDansCite() != 0) {
					for (int k = 0; k < j.nbQuartiersDansCite(); k++) {
						System.out.print(k + 1 + "  " + cite[k].getNom() + "(co�t " + cite[k].getCout()+"), ");
					}
				}
				else {
					
					System.out.print(j.getNom()+" n'a aucun quartier dans sa cit�");
					
				}
				
				System.out.print("\n");
			}
			System.out.println("Pour information vous avez " + getPlateau().getJoueur(numCondo).nbPieces() + " pi�ces");
			int t;
			boolean continu = true;
			do {
				System.out.println("Quel joueur choisissez-vous ? (0 si vous ne voulez rien faire)");

				t = Interaction.lireUnEntier(0, getPlateau().getNombreJoueurs() + 1);
				
				if(t == 0) {
					System.out.println("Vous ne faites rien");
					continu = false;
				}

				else if(getPlateau().getJoueur(t - 1).getNom().equals("Eveque") && !getPlateau().getJoueur(t - 1).getPersonnage().getAssassine()) {
					System.out.println("Ce personnage est prot�g� par votre pouvoir");
					continu = true;
				}
				else {
					boolean continu2 = false;
					System.out.println("Quel quartier choisissez-vous ? ");
					int nQuartier = Interaction.lireUnEntier(0, getPlateau().getJoueur(t - 1).nbQuartiersDansCite());
					do {
						
						if (getPlateau().getJoueur(t - 1).getCite()[nQuartier - 1].getCout()-1 <= getPlateau().getJoueur(numCondo).nbPieces()) {
							
							//
							getPlateau().getJoueur(numCondo).tresor -= (getPlateau().getJoueur(t - 1).getCite()[nQuartier - 1].getCout()-1);
							
							System.out.println("=> On retire "+getPlateau().getJoueur(t - 1).getCite()[nQuartier - 1].getNom());
							
							//Destruction du quartier dans la cit� du joueur
							getPlateau().getJoueur(t - 1).retirerQuartierDansCite(getPlateau().getJoueur(t - 1).getCite()[nQuartier - 1].getNom());
							
							System.out.println("Pour information, votre tr�sor est constitu� "
												+getPlateau().getJoueur(numCondo).nbPieces() +" pi�ce(s) d�or");
		    				continu2 = false;
		    				continu = false;
		    				
		    			}
						else {
							System.out.println("Votre tresor n'est pas suffisant ");

							System.out.print("Votre choix ? ");
							nQuartier = Interaction.lireUnEntier(0, getPlateau().getJoueur(t - 1).nbQuartiersDansCite());
							continu2 = true;
						}
					} while (continu2);
				}
				
			} while (continu);

			
		} else {
			System.out.println("Vous n'attaquez pas de cit�");
		}

	}

	public void percevoirRessourcesSpecifiques() {
		if (getJoueur() != null && getAssassine() != true) {
			for (int i = 0; i<getJoueur().nbQuartiersDansCite(); i++) {
				if (getJoueur().getCite()[i].getType().equals("MILITAIRE")) {
					getJoueur().ajouterPieces(1);
					
				}
			}
		}
	}

}
