package Jeu;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Scanner;

public class Solo extends Partie implements Serializable {

	public Solo(Joueur j1) throws GrilleException {
		super(j1);
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
				if(choix == 1) {
					this.j1.getGrilleBateau().afficher();
					System.out.println("Entrez les coordonnees x y de l'ile");
					int x = Integer.parseInt(sc.next());
					int y = Integer.parseInt(sc.next());
					this.j1.getGrilleBateau().placerTerre(x, y);
					this.j1.getGrilleTir().placerTerre(x, y);
				}
			}catch(GrilleException e) {
				System.out.println("Ile en dehors de la grille \n");
			} catch (Exception e) {
				System.out.println("Entrez un chiffre !\n");
			}
		}
		this.j1.demanderBateau(this.j1.getGrilleBateau());
	}

	@Override
	public void jouer() {
		Scanner sc = new Scanner(System.in);
		int choix = 0;
		while (choix != 1 && choix != 2 && choix != 3 && choix != 4) {
			System.out.println("");
			System.out.println("1-- Tirer \n2-- Trier \n3-- Sauvegarder \n4-- Quitter");
			try {
				choix = Integer.parseInt(sc.next());
			} catch (Exception e) {
				System.out.println("Entrez un nombre !");
			}
		}
		switch (choix) {
		case 1:
			boolean erreur = true;
			while (erreur) {
				try {
					this.j1.getGrilleTir().afficher();
					System.out.println("Entrez la position x y du tir");
					System.out.println("");
					int x = Integer.parseInt(sc.next());
					int y = Integer.parseInt(sc.next());
					boolean succes = this.j1.getGrilleBateau().tirer(x, y);
					if(succes)
						this.j1.getGrilleTir().getPlateau()[x][y].setBateauTouche(true);
					this.j1.getGrilleTir().getPlateau()[x][y].setLibre(false);
					erreur = false;

				} catch (GrilleException e) {
					System.out.println("Tir incorrect, recommencez");
				} catch (Exception e) {
					System.out.println("Entrez un nombre !");
				}
			}
			break;
		case 2:
			int trier = 0;
			while (trier != 1 && trier != 2 && trier != 3) {
				System.out.println("");
				System.out.println("1-- Trier par nom \n2-- Trier par vie \n3-- Retour");
				try {
					trier = Integer.parseInt(sc.next());
				} catch (Exception e) {
					System.out.println("Entrez un nombre !");
				}
			}
			switch (trier) {
			case 1:
				this.j1.getGrilleBateau().trierNom();
				break;
			case 2:
				this.j1.getGrilleBateau().trierVie();
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
			System.out.println("Commande non reconnue");
			break;
		}

	}	
}
