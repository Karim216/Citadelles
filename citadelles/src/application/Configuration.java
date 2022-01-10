package application;

import citadelles.modele.*;

public class Configuration {
	public Configuration() {

	}

	public static Pioche nouvellePioche() {
		// Quartier religeux
		// Religieux
		Quartier rel = new Quartier("temple", Quartier.TYPE_QUARTIERS[0], 1);
		Quartier rel1 = new Quartier("église", Quartier.TYPE_QUARTIERS[0], 2);
		Quartier rel2 = new Quartier("monastère", Quartier.TYPE_QUARTIERS[0], 3);
		Quartier rel3 = new Quartier("cathèdrale", Quartier.TYPE_QUARTIERS[0], 5);
		// Militaire
		Quartier mil = new Quartier("tour de guet", Quartier.TYPE_QUARTIERS[1], 1);
		Quartier mil1 = new Quartier("prison", Quartier.TYPE_QUARTIERS[1], 2);
		Quartier mil2 = new Quartier("caserne", Quartier.TYPE_QUARTIERS[1], 3);
		Quartier mil3 = new Quartier("forteresse", Quartier.TYPE_QUARTIERS[1], 5);
		// nobles
		Quartier nob = new Quartier("manoir", Quartier.TYPE_QUARTIERS[2], 3);
		Quartier nob1 = new Quartier("château", Quartier.TYPE_QUARTIERS[2], 4);
		Quartier nob3 = new Quartier("palais", Quartier.TYPE_QUARTIERS[2], 5);
		// comercant
		Quartier commer = new Quartier("taverne", Quartier.TYPE_QUARTIERS[3], 1);
		Quartier commer1 = new Quartier("échoppe", Quartier.TYPE_QUARTIERS[3], 2);
		Quartier commer2 = new Quartier("marché", Quartier.TYPE_QUARTIERS[3], 2);
		Quartier commer3 = new Quartier("comptoir", Quartier.TYPE_QUARTIERS[3], 3);
		Quartier commer4 = new Quartier("port", Quartier.TYPE_QUARTIERS[3], 4);
		Quartier commer5 = new Quartier("hôtel de ville", Quartier.TYPE_QUARTIERS[3], 5);
		// création de la piche
		Pioche p1 = new Pioche();
		// ajout des Quartier dans la pioche
		for (int i = 0; i < 2; i++) {
			p1.ajouter(rel3);
			p1.ajouter(mil3);
			p1.ajouter(commer5);
		}
		for (int i = 0; i < 3; i++) {
			p1.ajouter(rel);
			p1.ajouter(rel1);
			p1.ajouter(rel2);
			p1.ajouter(mil);
			p1.ajouter(mil1);
			p1.ajouter(mil2);
			p1.ajouter(nob3);
			p1.ajouter(commer1);
			p1.ajouter(commer3);
			p1.ajouter(commer4);
		}
		for (int i = 0; i < 4; i++) {
			p1.ajouter(nob1);
			p1.ajouter(commer2);
		}
		for (int i = 0; i < 5; i++) {
			p1.ajouter(nob);
			p1.ajouter(commer);
		}
		return p1;
	}
	
	public static PlateauDeJeu configurationDeBase(Pioche p) {
		// Implémentez la méthode statique conf igurationDeBase(P ioche p) qui renvoie un
		//nouveau plateau de jeu comportant les 8 personnages et 4 joueurs de la configuration de base décrite dans la partie 3.1 du document présentation du jeu Citadelles.
		//Cette méthode ajoutera é la pioche passée en paramètre, les 14 quartiers Merveille
		//que l’on doit utiliser dans cette configuration de base.
		//plateau de jeu 
		PlateauDeJeu pj = new PlateauDeJeu();
		//ajouter les personnages
		pj.ajouterPersonnage(new Assassin());
		pj.ajouterPersonnage(new Architecte());
		pj.ajouterPersonnage(new Condottiere());
		pj.ajouterPersonnage(new Eveque());
		pj.ajouterPersonnage(new Magicienne());
		pj.ajouterPersonnage(new Marchande());
		pj.ajouterPersonnage(new Roi());
		pj.ajouterPersonnage(new Voleur());

		//ajout des 4 joueurs on pourra par la suite demander à l'utilisateur d'enter le nombre de joueur et même leur nom
		pj.ajouterJoueur(new Joueur("joueur1"));
		pj.ajouterJoueur(new Joueur("joueur2"));
		pj.ajouterJoueur(new Joueur("joueur3"));
		pj.ajouterJoueur(new Joueur("joueur4"));

		//ajout des merveille dans la pioche Les merveilles sont à codé dans les autres classes
		Quartier bibliotheque=new Quartier("Bibliothèque",Quartier.TYPE_QUARTIERS[4],6);
		Quartier carrière=new Quartier("Carrière",Quartier.TYPE_QUARTIERS[4],5);
		Quartier courDesMiracles =new Quartier("Cour des miracles",Quartier.TYPE_QUARTIERS[4],2);
		Quartier donjon=new Quartier("Donjon",Quartier.TYPE_QUARTIERS[4],3);
		Quartier draco=new Quartier("Dracoport",Quartier.TYPE_QUARTIERS[4],6);
		Quartier ecoleDe =new Quartier("Ecole de magie",Quartier.TYPE_QUARTIERS[4],6);
		Quartier fontaineAux=new Quartier("Fontaine aux souhaits",Quartier.TYPE_QUARTIERS[4],5);
		Quartier forge=new Quartier("Forge",Quartier.TYPE_QUARTIERS[4],5);
		Quartier laboratoire=new Quartier("Laboratoire",Quartier.TYPE_QUARTIERS[4],5);
		Quartier manufacture=new Quartier("Manufacture",Quartier.TYPE_QUARTIERS[4],5);
		Quartier salleSes=new Quartier("Salle des cartes",Quartier.TYPE_QUARTIERS[4],5);
		Quartier statue=new Quartier("Statue Equestre",Quartier.TYPE_QUARTIERS[4],3);
		Quartier tresorImp=new Quartier("Trésor imprérial",Quartier.TYPE_QUARTIERS[4],5);
		Quartier tripot=new Quartier("Tripot",Quartier.TYPE_QUARTIERS[4],6);
		p.ajouter(bibliotheque);
		p.ajouter(carrière);
		p.ajouter(courDesMiracles);
		p.ajouter(donjon);
		p.ajouter(draco);
		p.ajouter(ecoleDe);
		p.ajouter(tripot);
		p.ajouter(fontaineAux);
		p.ajouter(forge);
		p.ajouter(laboratoire);
		p.ajouter(manufacture);
		p.ajouter(salleSes);
		p.ajouter(statue);
		p.ajouter(tresorImp);
		
		return pj;

	}
}
