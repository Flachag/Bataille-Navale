import java.util.ArrayList;
import java.util.Scanner;

public class Joueur {
	private String nom;
	private Grille grilleBateau, grilleTir;

	public Joueur(String nom, Grille gBateau, Grille gTir) throws GrilleException {
		this.nom = nom;
		this.grilleBateau = gBateau;
		this.grilleTir = gTir;
	}

	public void demanderBateau(Grille g) {
		int x, y;
		String ori;
		Scanner sc = new Scanner(System.in);
		String[] bateaux = { "porte avion", "croiseur", "contre torpilleur", "sous marin", "torpilleur" };
		g.afficher();
		for (int i = 0; i < bateaux.length; i++) {
			System.out.println("");
			System.out.println("Entrez la position x y et l'orientation (verticale ou horizontale) du " + bateaux[i]);
			x = sc.nextInt();
			y = sc.nextInt();
			ori = sc.next();
			Bateau b;
			try {
				b = new Bateau(x, y, bateaux[i], ori);
				g.placerBateau(b);
				g.afficher();
			} catch (BateauException e) {
				System.out.println("Erreur de placement, replacez le bateau:");
				i--;
			}
		}
	}

	public void commencer() {
		this.demanderBateau(this.grilleBateau);
	}

	public void jouer() {
		System.out.println("");
		int x, y;
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		System.out.println("Entrez la position x y du tir");
		x = sc.nextInt();
		y = sc.nextInt();
		boolean res = false;
		while (!res) {
			try {
				boolean tir = this.grilleBateau.tirer(x, y);
				this.grilleTir.getPlateau()[x][y].setLibre(false);
				this.grilleTir.afficher();
				res = true;
				if (tir)
					this.jouer();
				
			} catch (GrilleException e) {
				System.out.println("Tir incorrect");
			}
		}
	}
	
	public Grille getGrilleBateau() {
		return grilleBateau;
	}
	
	public Grille getGrilleTir() {
		return grilleTir;
	}
	
	public void setGrilleBateau(Grille g) {
		this.grilleBateau = g;
	}
	
	public void setGrilleTir(Grille g) {
		this.grilleTir = g;
	}
}
