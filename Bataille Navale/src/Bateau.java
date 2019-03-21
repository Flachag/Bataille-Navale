import java.util.ArrayList;

public class Bateau {
	private ArrayList<Cases> pos;
	private String nom;
	private int vie = 100;
	private int taille;
	
	public Bateau(int x, int y, String nom, String orientation) throws BateauException {
		this.pos = new ArrayList<Cases>();
		this.pos.add(this.setCase(x, y));
		
		switch (nom) {
		case "porte avion":
			this.taille = 5;
			break;
		case "croiseur":
			this.taille = 4;
			break;
		case "contre torpilleur":
		case "sous marin":
			this.taille = 3;
			break;
		case "torpilleur":
			this.taille = 2;
			break;
		default:
			throw new BateauException("Type de bateau innexistant");
		}
		this.nom = nom;
		
		orientation = orientation.toLowerCase();
		switch (orientation) {
		case "gauche":
			for (int i = 1; i < this.taille; i++) {
				x--;
				pos.add(this.setCase(x, y));
			}
			break;
		case "droite":
			for (int i = 1; i < this.taille; i++) {
				x++;
				pos.add(this.setCase(x, y));
			}
			break;
		case "haut":
			for (int i = 1; i < this.taille; i++) {
				y--;
				pos.add(this.setCase(x, y));
			}
			break;
		case "bas":
			for (int i = 1; i < this.taille; i++) {
				y++;
				pos.add(this.setCase(x, y));
			}
			break;

		default:
			throw new BateauException("Mauvaise orientation");
		}
		
		if (x <0 || y <0) {
			throw new BateauException(this.nom + " mal placé");
		}
	}


	private Cases setCase(int x, int y) {
		Cases caseBateau = new Cases(x,y);
		caseBateau.setBateau(this);
		caseBateau.setLibre(false);
		return caseBateau;
	}
	
	public ArrayList<Cases> getPos() {
		return pos;
	}
	

	public void setPos(ArrayList<Cases> pos) {
		this.pos = pos;
	}
	

	public String getNom() {
		return nom;
	}
	
	public int getVie() {
		return this.vie;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}	
	
	public void subirTir(int x, int y) {
		for (int i = 0; i < this.pos.size(); i++) {
			if(pos.get(i).getX() == x && pos.get(i).getY() == y) {
				this.pos.remove(i);
			}
		}
		this.vie = (int)(((double)this.pos.size()/(double)this.taille)*100);
	}
	
	public boolean isDead() {
		boolean res = false;
		if(this.vie == 0)
			res = true;
		return res;
	}		
}
