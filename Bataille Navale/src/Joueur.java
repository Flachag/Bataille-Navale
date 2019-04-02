import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Joueur implements Serializable {
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
			try {
				System.out.println("");
				System.out
						.println("Entrez la position x y et l'orientation (verticale ou horizontale) du " + bateaux[i]);
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
