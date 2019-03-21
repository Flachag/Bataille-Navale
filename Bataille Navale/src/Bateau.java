import java.util.ArrayList;

public class Bateau {
	private ArrayList<Cases> pos;
	private String nom;

	public Bateau(int x, int y, String nom, String orientation) {
		pos.add(this.setCase(x, y));
		
		int taille = 1;
		switch (nom) {
		case "porte avion":
			taille = 5;
			break;
		case "croiseur":
			taille = 4;
			break;
		case "contre torpilleur":
		case "sous marin":
			taille = 3;
			break;
		case "torpilleur":
			taille = 2;
			break;
		default:
			
			break;
		}
		this.nom = nom;
		
		orientation = orientation.toLowerCase();
		switch (orientation) {
		case "gauche":
			for (int i = 1; i < taille; i++) {
				x++;
				pos.add(this.setCase(x, y));
			}
			break;
		case "droite":
			for (int i = 1; i < taille; i++) {
				x--;
				pos.add(this.setCase(x, y));
			}
			break;
		case "haut":
			for (int i = 1; i < taille; i++) {
				y++;
				pos.add(this.setCase(x, y));
			}
			break;
		case "bas":
			for (int i = 1; i < taille; i++) {
				y--;
				pos.add(this.setCase(x, y));
			}
			break;

		default:
			break;
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
	

	public void setNom(String nom) {
		this.nom = nom;
	}	
	
	public void subirTir(int x, int y) {
		for (Cases cases : this.pos) {
			if(cases.getX() == x && cases.getY() == y) {
				this.pos.remove(cases);
			}
		}
	}
}
