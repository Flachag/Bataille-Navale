package Jeu;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Chagras Flavien
 *
 */

public class Joueur implements Serializable {
	private String nom;
	private Grille grilleBateau, grilleTir;

	
	/**
	 * Constructeur de la classe joueur
	 * @param nom nom du joueur
	 * @param gBateau grille ou les bateau du joueur seront mis
	 * @param gTir grille ou le joueur va tirer
	 * @throws GrilleException exception liees a la classe Grille (hors de la grille le plus souvent)
	 */
	public Joueur(String nom, Grille gBateau, Grille gTir) throws GrilleException {
		this.nom = nom;
		this.grilleBateau = gBateau;
		this.grilleTir = gTir;
	}

	
	/**
	 * Methode qui demande a l'utilisateur de placer ses bateaux
	 * @param g grille ou l'on veut placer les bateaux
	 */
	public void demanderBateau(Grille g) {
		int x, y;
		String ori;
		String[] bateaux = { "porte avion", "croiseur", "contre torpilleur", "sous marin", "torpilleur" };
		g.afficher();
		for (int i = 0; i < bateaux.length; i++) {
			Scanner sc = new Scanner(System.in);
			try {
				System.out.println("");
				System.out.println("Entrez la position x y et l'orientation (gauche, droite, haut, bas) du " + bateaux[i]);
				x = Integer.parseInt(sc.next());
				y = Integer.parseInt(sc.next());
				ori = sc.next();
				Bateau b;

				b = new Bateau(x, y, bateaux[i], ori);
				g.placerBateau(b);
				g.afficher();
			} catch (BateauException e) {
				System.out.println("Erreur de placement, replacez le bateau.");
				i--;
			} catch (Exception e) {
				System.out.println("Entrez un nombre !");
				i--;
			}
		}

	}

	
	/**
	 * Getter de la grille Bateau
	 * @return grilleBateau 
	 */
	public Grille getGrilleBateau() {
		return this.grilleBateau;
	}

	
	/**
	 * Getter de la grille de tir
	 * @return grilleTir
	 */
	public Grille getGrilleTir() {
		return this.grilleTir;
	}

	/**
	 * Getter du nom du joueur
	 * @return nom du joueur
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Setter de la grille bateau
	 * @param g Grille qui va devenir la grille bateau
	 */
	public void setGrilleBateau(Grille g) {
		this.grilleBateau = g;
	}

	/**
	 * Setter de la grille de tir
	 * @param g
	 */
	public void setGrilleTir(Grille g) {
		this.grilleTir = g;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
