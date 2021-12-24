package citadelles.modele;

public class Quartier {
	// attributs private:
	private String nom = "";
	private String type = "";
	private String caracteristiques = "";
	private int coutConstruction = 0;

	// attributs public:
	public static final String[] TYPE_QUARTIERS = { "RELIGIEUX", "MILITAIRE", "NOBLE", "COMMERCANT", "MERVEILLE" };

	// Methodes
	public Quartier(String nom, String type, int cout, String carac) {
		this.nom = nom;
		this.type = type;
		this.coutConstruction = cout;
		this.caracteristiques = carac;
	}

	public Quartier(String nom, String type, int cout) {
		this.nom = nom;
		this.type = type;
		this.coutConstruction = cout;
	}

	public Quartier() {

	}

	public boolean getEmbelli() {
		return true;
	}

	public void embellir() {

	}

	// accesseurs Nom
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	// accesseurs Type
	public String getType() {
		return type;
	}

	public void setType(String type) {
		int i = 0;
		boolean isPresent = false;
		while (i < TYPE_QUARTIERS.length && !isPresent) {
			if (type.equals(TYPE_QUARTIERS[i])) {
				this.type = type;
				isPresent = true;
			}
			i = i++;
		}
		if (!isPresent) {
			this.type = "";
		}
	}

	// accesseurs CoutConstuction
	public int getCout() {
		return this.coutConstruction;
	}

	public void setCout(int cout) {
		if (cout >= 1 && cout <= 6) {
			this.coutConstruction = cout;
		} else {
			this.coutConstruction = 0;
		}

	}

	// Accesseur caractéristique
	public String getCaracteristiques() {
		return this.caracteristiques;
	}

	public void setCaracteristiques(String carac) {
		this.caracteristiques = carac;
	}

}