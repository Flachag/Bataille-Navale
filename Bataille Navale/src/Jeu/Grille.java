package Jeu;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Chagras Flavien
 *
 */

public class Grille implements Serializable{
	private Cases[][] plateau;
	private ArrayList<Bateau> flote;
	private int tailleX, tailleY;

	/**
	 * Constructeur de grille
	 * @param x largeur de la grille
	 * @param y hauteur de la grille
	 * @throws GrilleException Exceptions liees a la class Grille
	 */
	public Grille(int x, int y) throws GrilleException {
		if (x >= 5 && y >= 5) {
			this.plateau = new Cases[x][y];
			this.flote = new ArrayList<Bateau>();
			this.tailleX = x;
			this.tailleY = y;
			for (int i = 0; i < this.plateau.length; i++) {
				for (int j = 0; j < this.plateau[i].length; j++) {
					this.plateau[i][j] = new Cases(i, j);
				}
			}
		} else {
			throw new GrilleException("taille grille incorrecte");
		}
	}

	/**
	 * Methode qui verifie si le bateau est placable sur la grille
	 * @param b bateau a verifier
	 * @return true si le bateau peut etre placé
	 * @throws BateauException renvoie une erreur sinon
	 */
	public boolean isPlacable(Bateau b) throws BateauException {
		boolean res = true;
		for (int i = 0; i < b.getPos().size(); i++) {
			int x = b.getPos().get(i).getX();
			int y = b.getPos().get(i).getY();
			if (x >= this.plateau.length || y >= this.plateau[x].length)
				throw new BateauException("Bateau hors de la grille");
			if (!this.plateau[x][y].isLibre()) {
				throw new BateauException("Bateau mal placé");
			}
		}
		return res;
	}

	/**
	 * Methode qui place le bateau sur la grille
	 * @param b bateau a placer
	 * @throws BateauException renvoie une erreur si le bateau ne peut pas etre place 
	 */
	public void placerBateau(Bateau b) throws BateauException {
		if (this.isPlacable(b)) {
			for (int j = 0; j < b.getPos().size(); j++) {
				int x = b.getPos().get(j).getX();
				int y = b.getPos().get(j).getY();
				this.plateau[x][y] = b.getPos().get(j);
			}
			this.flote.add(b);
		}
	}

	/*
	 * Place une case de "terre" sur la grille, on ne peut ni tirer dessus, ni placer un bateau dessus
	 */
	public void placerTerre(int x, int y) throws GrilleException {
		if(x >= this.tailleX || y>= this.tailleY)
			throw new GrilleException("Terre placee en dehors de la grille");
		this.plateau[x][y].setLibre(false);
		this.plateau[x][y].setIle(true);
		
	}

	/**
	 * Tire sur une case donnee
	 * @param x coordonnee X de la case
	 * @param y coordonnee Y de la case
	 * @return retourne True si le tir touche un bateau, false sinon
	 * @throws GrilleException si le tir touche une case non libre ou tir en dehor de la map
	 */
	public boolean tirer(int x, int y) throws GrilleException {
		boolean touche = false;
		//Teste si le tir est sur le plateau
		if (x < 0 || x >= this.plateau.length || y < 0 || y >= this.plateau[x].length)
			throw new GrilleException("Tir en dehors de la grille");

		//Teste si le tir est sur un bateau
		if (x >= 0 && y >= 0 && this.plateau[x][y].getBateau() != null) {
			Bateau cible = this.plateau[x][y].getBateau();
			cible.subirTir(x, y);
			System.out.println("");
			//Si le bateau est mort apres le tir, on affiche coulé + le nom du bateau
			if (cible.isDead()) {
				System.out.println(cible.getNom() + " coulé");
				for (int i = 0; i < this.flote.size(); i++) {
					//On trouve quel bateau est coule et on le remove de la flote
					if (flote.get(i).getNom() == cible.getNom()) {
						this.flote.remove(i);
					}
				}
			//Si le bateau n'est pas mort on affiche touche + le nom du bateau
			} else {
				System.out.println(cible.getNom() + " touché");
				System.out.println("Vie restante: " + cible.getVie() + "%");
			}
			//la case ou le tir a eu lieu ne possede plus de bateau
			this.plateau[x][y].setBateau(null);
			//le tir a touche, on retourne true
			touche = true;
			
		//Si le tir est sur le plateau ET sur une case libre, on affiche plouf
		} else if (x >= 0 && y >= 0 && this.plateau[x][y].isLibre()) {
			System.out.println("Plouf");
			this.plateau[x][y].setLibre(false);
			
		//Sinon, on est sur une case non libre donc on throw une erreur
		} else {
			throw new GrilleException("Case non libre");
		}
		//si on arrive la le tir n'a pas touche donc on retourne false
		return touche;
	}

	/*
	 * Affiche la grille dans la console (but purement visuel)
	 * affiche B sur les cases ou il y a un bateau
	 * affiche X sur les cases ou il y a eu un tir
	 * affiche * sur les case ou il y a eu un tir sur un bateau
	 * affiche ~ sinon
	 */
	public void afficher() {
		System.out.print(" |");
		for (int i = 0; i < this.tailleX; i++) {
			System.out.print(i+"|");
		}
		System.out.println("");
		for (int i = 0; i < this.tailleY; i++) {
			System.out.print(i + "|");
			for (int j = 0; j < this.tailleX; j++) {
				if (this.plateau[j][i].getBateau() != null)
					System.out.print("B");
				else if (this.plateau[j][i].getBateauTouche())
					System.out.print("*");
				else if (this.plateau[j][i].getIle())
					System.out.print("O");
				else if (!this.plateau[j][i].isLibre())
					System.out.print("X");
				else
					System.out.print("~");
				System.out.print("|");
			}
			System.out.println("");
		}
	}

	/**
	 * Methode qui verifie si la flote de la grille est detruite
	 * @return retourne true si la flote est detruite, false sinon
	 */
	public boolean floteDetruite() {
		boolean res = false;
		if (this.flote.size() == 0)
			res = true;
		return res;
	}
	
	/*
	 * Trie les bateau par ordre croissant de vie, le bateau le plus endommage se retrouve en 1er
	 */
	public void trierVie() {
		Collections.sort(this.getFlote(), new Comparator<Bateau>() {
			public int compare(Bateau b1, Bateau b2) {
				Integer vie1 = (Integer) b1.getVie();
				Integer vie2 = (Integer) b2.getVie();
				int result = vie1.compareTo(vie2);
				return result;
			}
		});
		System.out.println("Bateaux triés du plus endommagé au moins endommagé:");
		for (int i = 0; i < this.getFlote().size(); i++)
			System.out.println(this.getFlote().get(i).getNom());
		System.out.println("");
	}

	/*
	 * Trie les bateau par ordre alphabetique
	 */
	public void trierTaille() {
		Collections.sort(this.getFlote(), new Comparator<Bateau>() {
			public int compare(Bateau b1, Bateau b2) {
				Integer taille1 = (Integer) b1.getTaille();
				Integer taille2 = (Integer) b2.getTaille();
				int result = taille1.compareTo(taille2);
				return result;
			}
		});
		System.out.println("Bateaux triés par taille:");
		for (int i = 0; i < this.getFlote().size(); i++)
			System.out.println(this.getFlote().get(i).getNom());
		System.out.println("");
	}
	
	public Cases[][] getPlateau() {
		return plateau;
	}

	public void setPlateau(Cases[][] plateau) {
		this.plateau = plateau;
	}

	public int getTailleX() {
		return this.tailleX;
	}
	
	public int getTailleY() {
		return this.tailleY;
	}
	public ArrayList<Bateau> getFlote() {
		return flote;
	}
}
