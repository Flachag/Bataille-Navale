package Jeu;
import java.io.Serializable;

public class Cases implements Serializable{
	private int x, y;
	private Bateau b;
	private boolean libre = true;
	private boolean bateauTouche = false;
	private boolean ile = false;
	
	/*
	 * Constructeur de Cases
	 * @param x coordonnee en X de la case
	 * @param y coordonnee en Y de la case
	 */
	public Cases(int x, int y) {
		if (x >= 0 && y >= 0) {
			this.x = x;
			this.y = y;
		}
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public Bateau getBateau() {
		return b;
	}
	
	public void setBateau(Bateau b) {
		this.b = b;
	}
	
	public boolean isLibre() {
		return libre;
	}
	
	public void setLibre(boolean libre) {
		this.libre = libre;
	}	
	
	public boolean getBateauTouche() {
		return this.bateauTouche;
	}
	
	public void setBateauTouche(boolean bateau) {
		this.bateauTouche = bateau;
	}
	
	public void setIle(boolean ile) {
		this.ile = ile;
	}
	
	public boolean getIle() {
		return this.ile;
	}
}
