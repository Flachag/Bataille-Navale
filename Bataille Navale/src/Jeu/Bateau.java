package Jeu;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Chagras Flavien
 *
 */

public class Bateau implements Serializable {
	private ArrayList<Cases> pos;
	private String nom;
	private int vie = 100;
	private int taille;

	/**
	 * Constructeur de Bateau
	 * 
	 * @param x coordonnee en ordonnee de l'avant du bateau
	 * @param y coordonnee en abscisse de l'avant du bateau
	 * @param nom du bateau
	 * @param orientation du bateau (verticale ou horizontale)
	 * @throws BateauException Exception liees a la classe Bateau
	 */
	public Bateau(int x, int y, String nom, String orientation) throws BateauException {
		this.pos = new ArrayList<Cases>();
		this.pos.add(this.setCase(x, y));

		// Permet de savoir quelle taille pour quel type de bateau
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
		// permet de donner les cases ou est le bateau en fonction de son orientation
		switch (orientation) {
		case "droite":
			for (int i = 1; i < this.taille; i++) {
				x++;
				pos.add(this.setCase(x, y));
			}
			break;
		case "gauche":
			for (int i = 1; i < this.taille; i++) {
				x--;
				pos.add(this.setCase(x, y));
			}
			break;
		case "bas":
			for (int i = 1; i < this.taille; i++) {
				y++;
				pos.add(this.setCase(x, y));
			}
			break;
		case "haut":
			for (int i = 1; i < this.taille; i++) {
				y--;
				pos.add(this.setCase(x, y));
			}
			break;
		default:
			throw new BateauException("Mauvaise orientation");
		}

		if (x < 0 || y < 0)
			throw new BateauException(this.nom + " mal placé");
	}


	/**
	 * Fait subir un tir au bateau aux coordonnees donnees
	 * 
	 * @param x coordonnee X du tir
	 * @param y coordonnee Y du tir
	 */
	public void subirTir(int x, int y) {
		for (int i = 0; i < this.pos.size(); i++) {
			if (pos.get(i).getX() == x && pos.get(i).getY() == y) {
				// Supprime la case touchee de la pos du bateau
				this.pos.remove(i);
			}
		}
		// Actualise la vie du bateau
		this.vie = (int) (((double) this.pos.size() / (double) this.taille) * 100);
	}

	/**
	 * Creer une case avec un bateau dessus
	 * 
	 * @param x coordonnee X de la case
	 * @param y coordonnee Y de la case
	 * @return retourne la case cree
	 */
	private Cases setCase(int x, int y) {
		Cases caseBateau = new Cases(x, y);
		caseBateau.setBateau(this);
		caseBateau.setLibre(false);
		return caseBateau;
	}
	
	/**
	 * Verifie si le bateau est ou non detruit
	 * @return true si le bateau est detruit, false sinon
	 */
	public boolean isDead() {
		boolean res = false;
		if (this.vie == 0)
			res = true;
		return res;
	}

	/**
	 * Getter de position
	 * @return la position du bateau
	 */
	public ArrayList<Cases> getPos() {
		return pos;
	}

	/**
	 * setter de position
	 * 
	 * @param pos position du bateau
	 */
	public void setPos(ArrayList<Cases> pos) {
		this.pos = pos;
	}

	/**
	 * getter du nom du bateau
	 * 
	 * @return le nom du bateau
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * setter du nom du bateau
	 * 
	 * @param nom nom du bateau
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * getter la vie du bateau
	 * 
	 * @return la vie du bateau
	 */
	public int getVie() {
		return this.vie;
	}
	
	/**
	 * Getter de la taille du bateau
	 * @return taille du bateau
	 */
	public int getTaille() {
		return this.taille;
	}
}
