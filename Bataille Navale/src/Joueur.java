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
	
	public Grille getGrilleBateau() {
		return grilleBateau;
	}
	
	public Grille getGrilleTir() {
		return grilleTir;
	}
	
	public String getNom() {
		return this.nom;
	}
	public void setGrilleBateau(Grille g) {
		this.grilleBateau = g;
	}
	
	public void setGrilleTir(Grille g) {
		this.grilleTir = g;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}	
}
