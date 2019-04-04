package Jeu;

import java.io.*;
import java.util.Scanner;

/**
 * @author Chagras Flavien
 *
 */

public abstract class Partie implements Serializable {
	protected Joueur j1;

	public Partie(Joueur j) {
		this.j1 = j;
	}

	public abstract void commencer();

	public abstract void jouer();

	public Joueur getJ1() {
		return this.j1;
	}

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
