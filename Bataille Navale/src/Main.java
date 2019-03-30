import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Grille bateauJ1, tirJ1;
		Grille bateauJ2, tirJ2;
		int tailleX = 0, tailleY = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("1-- Jouer \n2-- Charger une partie");
		int choix = sc.nextInt();
		switch (choix) {
		case 2:
			try {
				charge("batailleNavale.jeu");
			}catch(Exception e) {
				System.out.println("Aucune partie trouvée, début d'une nouvelle partie");
			}
		case 1:
			while (tailleX < 5 || tailleY < 5 || tailleX > 10 || tailleY > 10) {
				System.out.println("Entrez la largeur et la hauteur de la grille (comprise entre 5 et 10):");
				tailleX = sc.nextInt();
				tailleY = sc.nextInt();
				if (tailleX < 5 || tailleY < 5 || tailleX > 10 || tailleY > 10)
					System.out.println("Taille incorrecte, recommencez");
			}

			try {
				bateauJ1 = new Grille(tailleX, tailleY);
				tirJ1 = new Grille(tailleX, tailleY);
				boolean joueur = false;
				while (!joueur) {
					System.out.println("Entrez le nombre de joueurs (1 ou 2):");
					int nb = sc.nextInt();
					switch (nb) {
					case 1:
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
		default:
			break;
		}
	}

	public static void charge(String nom) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nom));
			Partie p = (Partie) (ois.readObject());
			ois.close();
		} catch (IOException e) {
			System.out.println("erreur d’E/S");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("erreur hors E/S");
			e.printStackTrace();
		}
	}
}