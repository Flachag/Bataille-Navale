package Jeu;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Scanner;

public class Duo extends Partie implements Serializable {

	protected Joueur j2;

	/**
	 * Constructeur de duo (partie 2 joueurs)
	 * 
	 * @param j1 Joueur 1
	 * @param j2 Joueur 2
	 * @throws GrilleException Exceptions liees a la class Grille
	 */
	public Duo(Joueur j1, Joueur j2) throws GrilleException {
		super(j1);
		this.j2 = j2;
	}

	@Override
	public void commencer() {
		System.out.println("");
		int choix = 0;
		Scanner sc = new Scanner(System.in);
		while (choix != 2) {
			System.out.println("1-- Placer des iles \n2-- Placer les bateaux");
			try {
				choix = Integer.parseInt(sc.next());
				if (choix == 1) {
					System.out.println("");
					this.j1.getGrilleBateau().afficher();
					System.out.println("Entrez les coordonnees x y de l'ile");
					int x = Integer.parseInt(sc.next());
					int y = Integer.parseInt(sc.next());
					this.j1.getGrilleBateau().placerTerre(x, y);
					this.j1.getGrilleTir().placerTerre(x, y);
					this.j2.getGrilleBateau().placerTerre(x, y);
					this.j2.getGrilleTir().placerTerre(x, y);
				}
			}catch(GrilleException e) {
				System.out.println("Ile en dehors de la grille \n");
			} catch (Exception e) {
				System.out.println("Entrez un chiffre !\n");
			}
		}
		System.out.println(this.j1.getNom() + " placez vos bateaux:");
		this.j1.demanderBateau(this.j1.getGrilleBateau());
		System.out.println("");
		System.out.println(this.j2.getNom() + " placez vos bateaux:");
		this.j2.demanderBateau(this.j2.getGrilleBateau());
	}

	@Override
	public void jouer() {
		this.deroulerTour(this.j1, this.j2);
		if (!this.j1.getGrilleBateau().floteDetruite() && !this.j2.getGrilleBateau().floteDetruite())
			this.deroulerTour(this.j2, this.j1);
	}

	/*
	 * methode interne qui fait tirer un joueur sur un autre
	 * 
	 * @param tireur joueur qui tir
	 * 
	 * @param cible joueur cible
	 */
	private void deroulerTour(Joueur tireur, Joueur cible) {
		int x = 0, y = 0;
		Scanner sc = new Scanner(System.in);
		int choix = 0;
		while (choix != 1) {
			System.out.println(tireur.getNom()+":");
			System.out.println("1-- Tirer \n2-- Trier \n3-- Sauvegarder \n4-- Quitter");
			try {
				choix = Integer.parseInt(sc.next());
			} catch (Exception e) {
				System.out.println("Entrez un nombre !");
			}
		switch (choix) {
		case 1:
			try {
				System.out.println("");
				tireur.getGrilleTir().afficher();
				System.out.println(tireur.getNom() + " entrez la position x y du tir");
				System.out.println("");
				x = Integer.parseInt(sc.next());
				y = Integer.parseInt(sc.next());

				// permet de recommencer le tir tant que celui ci n'est pas valide (Hors de la
				// map ou tir sur case indisponible)

				boolean tir = cible.getGrilleBateau().tirer(x, y);
				tireur.getGrilleTir().getPlateau()[x][y].setLibre(false);
				if (tir && !cible.getGrilleBateau().floteDetruite() && !tireur.getGrilleBateau().floteDetruite())
					deroulerTour(tireur, cible);
			} catch (GrilleException e) {
				System.out.println("Tir incorrect, recommencez");
				deroulerTour(tireur, cible);
			} catch (Exception e) {
				System.out.println("Entrez un nombre !");
			}
			break;
		case 2:
			int trier = 0;
			while (trier != 1 && trier != 2 && trier != 3) {
				System.out.println(tireur.getNom()+":");
				System.out.println("1-- Trier par nom \n2-- Trier par vie \n3-- Retour");
				try {
					trier = Integer.parseInt(sc.next());
				} catch (Exception e) {
					System.out.println("Entrez un nombre !");
				}
			}
			System.out.println("Bateaux de "+tireur.getNom());
			switch (trier) {
			case 1:
				tireur.getGrilleBateau().trierNom();
				break;
			case 2:
				tireur.getGrilleBateau().trierVie();
				break;
			default:
				break;
			}
			break;
		case 3:
			this.sauver("batailleNavale.jeu");
			break;
		case 4:
			System.out.println("Jeu quitté");
			System.exit(0);
			break;
		default:
			break;
			}
		}
	}
}
