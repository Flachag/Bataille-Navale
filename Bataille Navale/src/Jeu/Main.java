package Jeu;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Chagras Flavien
 *
 */

public class Main {
	public static void main(String[] args) {
		Grille bateauJ1, tirJ1;
		Grille bateauJ2, tirJ2;
		int tailleX = 0, tailleY = 0;
		Scanner sc = new Scanner(System.in);

		int choix = choisirMenu();
		switch (choix) {
		case 2:
			try {
				if (charger("batailleNavale.jeu") instanceof Duo) {
					Duo partie = (Duo) charger("batailleNavale.save");
					partie.jouer();
				} else if (charger("batailleNavale.jeu") instanceof Solo) {
					Solo partie = (Solo) charger("batailleNavale.jeu");
					partie.jouer();
				}
			} catch (Exception e) {
				System.out.println("Aucune partie trouvée, début d'une nouvelle partie\n");
			}
		case 1:
			Grille taille = choisirTaille();
			tailleX = taille.getTailleX();
			tailleY = taille.getTailleY();
			try {
				bateauJ1 = new Grille(tailleX, tailleY);
				tirJ1 = new Grille(tailleX, tailleY);
				boolean joueur = false;
				while (!joueur) {
					int nb = demanderNbJoueur();
					switch (nb) {
					case 1:
						sc = new Scanner(System.in);
						joueur = true;
						System.out.println("Entrez le nom du joueur:");
						Joueur j = new Joueur(sc.next(), bateauJ1, tirJ1);
						Solo solo = new Solo(j);
						solo.commencer();
						while (!solo.j1.getGrilleBateau().floteDetruite()) {
							solo.jouer();
						}
						System.out.println("Partie Terminée");
						break;

					case 2:
						sc = new Scanner(System.in);
						joueur = true;
						bateauJ2 = new Grille(tailleX, tailleY);
						tirJ2 = new Grille(tailleX, tailleY);
						System.out.println("Entrez le nom du joueur 1:");
						Joueur j1 = new Joueur(sc.next(), bateauJ1, tirJ1);
						System.out.println("Entrez le nom du joueur 2:");
						Joueur j2 = new Joueur(sc.next(), bateauJ2, tirJ2);
						Duo duo = new Duo(j1, j2);
						duo.commencer();
						while (!duo.j1.getGrilleBateau().floteDetruite() && !duo.j2.getGrilleBateau().floteDetruite()) {
							duo.jouer();
						}
						System.out.println("");
						if (duo.j1.getGrilleBateau().floteDetruite())
							System.out.println(j2.getNom() + " gagne la partie !");
						else
							System.out.println(j1.getNom() + " gagne la partie !");
						break;
					default:
						System.out.println("Erreur nb Joueur");
						break;
					}
				}
			} catch (GrilleException e) {
				System.out.println("Taille grille incorrecte");
				e.printStackTrace();
			}
			break;
		case 3:
			System.out.println("Jeu quitté");
			System.exit(0);
			break;
		default:
			break;
		}
	}

	public static int choisirMenu() {
		int choix = 0;
		Scanner sc = new Scanner(System.in);
		while (choix != 1 && choix != 2 && choix != 3) {
			System.out.println("1-- Jouer \n2-- Charger une partie\n3-- Quitter");
			try {
				choix = Integer.parseInt(sc.next());
				if (choix != 1 && choix != 2 && choix != 3)
					System.out.println("Choisissez 1, 2 ou 3");
			} catch (Exception e) {
				System.out.println("Entrez un nombre !");
			}
		}
		return choix;
	}

	public static Grille choisirTaille() {
		int tailleX = 0, tailleY = 0;
		Grille g = null;
		Scanner sc = new Scanner(System.in);
		while (tailleX < 5 || tailleY < 5 || tailleX > 10 || tailleY > 10) {
			System.out.println("Entrez la largeur et la hauteur de la grille (comprise entre 5 et 10):");
			try {
				tailleX = Integer.parseInt(sc.next());
				tailleY = Integer.parseInt(sc.next());
				g = new Grille(tailleX, tailleY);
			} catch (GrilleException e) {
				System.out.println("Taille grille incorecte");
			} catch (Exception e) {
				System.out.println("Entrez un chiffre !");
			}
		}
		return g;
	}

	public static Partie charger(String source) throws IOException, ClassNotFoundException {
		Partie partie = null;
		ObjectInputStream di = new ObjectInputStream(new FileInputStream(source));
		partie = (Partie) (di.readObject());
		di.close();
		return partie;
	}
	
	public static int demanderNbJoueur() {
		int nb = 0;
		System.out.println("Entrez le nombre de joueurs (1 ou 2):");
		Scanner sc = new Scanner(System.in);
		try {
			nb = Integer.parseInt(sc.next());
		} catch (Exception e) {
			System.out.println("Entrez un chiffre !\n");
		}
		return nb;
	}
}