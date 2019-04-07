package Jeu;

import java.io.*;
import java.util.Scanner;

/**
 * @author Chagras Flavien
 *
 */

public abstract class Partie implements Serializable {
	protected Joueur j1;

	/**
	 * Constructeur de la class Joueur
	 * @param j Joueur 1
	 */
	public Partie(Joueur j) {
		this.j1 = j;
	}

	/**
	 * Methode qui initialise le plateau
	 */
	public abstract void commencer();

	/**
	 * Methode qui effectue un tour de jeu
	 */
	public abstract void jouer();

	public Joueur getJ1() {
		return this.j1;
	}

	/**
	 * Methode qui permet de sauvegarder la partie
	 * @param nom nom de la sauvegarde
	 */
	public void sauver(String nom) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nom));
			oos.writeObject(this);
			oos.close();
		} catch (IOException e) {
			System.out.println("erreur d’E/S");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("erreur hors E/S");
			e.printStackTrace();
		}
	}
}
