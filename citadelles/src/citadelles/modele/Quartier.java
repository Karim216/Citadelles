package citadelles.modele;

public class Quartier {
	public static final String[] TYPE_QUARTIERS = {"RELIGIEUX", "MILITAIRE", "NOBLE", "COMMERCANT", "MERVEILLE"};
	private String nom;
	private String type;
	private int coutConstruction;
	private String caractéristiques;
	
	public String getNom(){
		return this.nom;
	}
	public void setNom(String nom){
		this.nom = nom;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Quartier(String nom, String type, int cout, String caracteristiques) {
		
	}

}
