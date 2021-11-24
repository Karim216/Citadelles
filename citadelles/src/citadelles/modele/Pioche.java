package citadelles.modele;

import java.util.ArrayList;
import java.util.Random;

public class Pioche {
	private ArrayList<Quartier> liste;
	private Random gen = new Random();
	
	public Pioche() {
		liste = new ArrayList<Quartier>();
	}
	
	public Quartier piocher() {
		
		if(liste.size() != 0) {
			Quartier q = liste.get(0);
			liste.remove(0);
			return q;
		}
		return null;
	}
	
	public void ajouter(Quartier nouveau) {
		liste.add(nouveau);
	}
	
	public int nombreElements() {
		return liste.size();
	}
	
	public void melanger() {
		
		int i=0;
		int j=0;
		for(int k=0; k<liste.size(); k++) {
			
			i = gen.nextInt(liste.size());
			j = gen.nextInt(liste.size());
			
			Quartier q1 = liste.get(i);
			Quartier q2 = liste.get(j);
			
			liste.set(i, q2);
			liste.set(j, q1);
		}
		
		
		
	}
}
