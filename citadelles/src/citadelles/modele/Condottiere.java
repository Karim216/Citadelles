package citadelles.modele;


import citadelles.modele.Personnage;
import controleur.Interaction;

public class Condottiere extends Personnage {
  
   
    public Condottiere(){
       
        super("Condottiere",8,Caracteristiques.CONDOTTIERE);
    }

    public void utiliserPouvoir(){
    	System.out.println("Voulez-vous détruire un quartier d'une cité ?");
    	boolean choix1 = Interaction.lireOuiOuNon();
    	if(choix1) {
    		System.out.println("Voici la liste des joueurs et leurs quartier");
    		int numCondo =-1;
    		int numEveque =-1;
    		for(int i=0; i<getPlateau().getNombreJoueurs(); i++) {
    			Joueur j = getPlateau().getPersonnage(i).getJoueur();
    			Personnage p = getPlateau().getPersonnage(i);
    			
    			if(p.getNom().equals("Condottiere")) {
    				numCondo=i;
    			}
    			if(p.getNom().equals("Eveque")) {
    				numEveque=i;
    			}
    			Quartier[] cite = j.getCite();
    			System.out.println(i+1+"  "+j.getNom()+" :");
    			for(int k=0; k<j.nbQuartiersDansCite();k++) {
    				System.out.println(k+1+"  "+cite[k].getNom()+ " coût "+ cite[k].getCout());
    			}
    		}
    		System.out.println("Pour information vous avez "+ getPlateau().getJoueur(numCondo).nbPieces()+ " pièces");
    		int t;
    		boolean continu;
    		do {
    		System.out.println("Quel joueur choisissez-vous ? (0 si vous ne voulez rien faire)");
    		
    			 t = Interaction.lireUnEntier(0,getPlateau().getNombreJoueurs()+1);
    			 continu = true;
    		for(int i=0; i<getPlateau().getJoueur(t-1).nbQuartiersDansCite();i++) {
//    			if(getJoueur().getCite().getCout()-1<=getPlateau().getJoueur(numCondo).nbPieces()) {
//    				continu = false;
//    				
//    			}
    		}
    		}while((getPlateau().getJoueur(t-1).getNom().equals("Eveque") && !getPlateau().getJoueur(t-1).getPersonnage().getAssassine()) || continu  );
    		
    		if(t==0) {
    			System.out.println("Vous ne faites rien");
    		}
    		else {
    			
    			continu=true;
    			Joueur j=getPlateau().getJoueur(t-1);
    			System.out.println("Vous attaquez "+ getPlateau().getJoueur(t-1).getNom());
    			do {
    			System.out.println("Quel Quartier voulez-vous détruire ?");
    			int q = Interaction.lireUnEntier(0,getPlateau().getNombreJoueurs()+1);
//    			if(j.getCite(q-1).getCout()-1<=getPlateau().getJoueur(numCondo).nbPieces()) {
//    				getPlateau().getJoueur(numCondo).retirerPieces(j.getCite(q-1).getCout()-1);
//    				j.retirerQuartierDansCite(j.getCite(q-1).getNom());
//    				continu=true;
//    				System.out.println("il vous reste "+getPlateau().getJoueur(numCondo).nbPieces()+ " pièces");
//    			}
//    			else {
//    				System.out.println("Vous ne pouvez pas détruire ce quartier pour manque de pièces");
//    				continu = false;
//    			}
    			}while(!continu);
    		
    		}
    	}
    	else {
    		System.out.println("Vous n'attaquez pas de cité");
    	}
    	
    }

    
    public void percevoirRessourcesSpecifiques(){
        int nbPiece = 0;
        
        if(getJoueur()!=null){
            if(getAssassine()==false){
        for(int i=0;i<8;i++){
        

           
//            if(getJoueur().getCite(i).getType().equals("MILITAIRE")){
//                nbPiece =nbPiece +1;
//     
//            }
    }
       
        System.out.println(" Vous avez reÃ§u "+ nbPiece +" piece(s)");
        getJoueur().ajouterPieces(nbPiece);
        }
        }
    }
 
}
