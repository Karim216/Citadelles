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
		
		//liste qui va contenir la copie des quartiers dans la main du joueur
		//cette liste ne contiendra pas les quartiers que le joueur veut changer
		ArrayList<Quartier> copieTableau = new ArrayList<Quartier>();

		boolean continu = false;
		
		do {
			System.out.print("Voulez-vous échanger vos cartes avec celles d’un autre joueur ?o\\n\n");
			Boolean choix = Interaction.lireOuiOuNon();

			if (choix) {
				System.out.print("Vous ne pouvez pas vous assassiner\n");
				
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
						copieTableau.add(getJoueur().getMain().get(i));
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
