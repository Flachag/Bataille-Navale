import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws GrilleException {
		Grille bateauJ1, tirJ1;
		Grille bateauJ2, tirJ2;

		Scanner sc = new Scanner(System.in);

		System.out.println("Entrez la largeur de la grille (ce sera un carré)");
		int taille = sc.nextInt();

		bateauJ1 = new Grille(taille, taille);
		tirJ1 = new Grille(taille, taille);

		System.out.println("Entrez le nombre de joueurs (1 ou 2)");
		int nb = sc.nextInt();
		switch (nb) {
		case 1:
			System.out.println("Entrez le nom du joueur");
			Joueur j = new Joueur(sc.next(), bateauJ1, tirJ1);
			Solo solo = new Solo(j);
			solo.commencer();
			System.out.println("");
			solo.j1.getGrilleTir().afficher();
			while (!solo.j1.getGrilleBateau().floteDetruite()) {
				if (solo.j1.getGrilleBateau().floteDetruite())
					solo.jouer();
			}
			break;

		case 2:
			bateauJ2 = new Grille(taille, taille);
			tirJ2 = new Grille(taille, taille);
			System.out.println("Entrez le nom du joueur 1");
			Joueur j1 = new Joueur(sc.next(), bateauJ1, tirJ1);
			System.out.println("Entrez le nom du joueur 2");
			Joueur j2 = new Joueur(sc.next(), bateauJ2, tirJ2);
			Duo duo = new Duo(j1, j2);
			duo.commencer();
			System.out.println("");
			duo.j1.getGrilleTir().afficher();
			while (!duo.j1.getGrilleBateau().floteDetruite() && !duo.j2.getGrilleBateau().floteDetruite()) {
				duo.jouer();
			}
			break;
		default:
			System.out.println("Erreur nb Joueur");
			break;
		}

	}
}