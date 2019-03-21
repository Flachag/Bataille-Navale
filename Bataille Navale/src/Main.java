
public class Main {

	public static void main(String[] args) {
		Grille g;
		Bateau b;
		g = new Grille(10, 10);
		try {
			b = new Bateau(0, 0, "porte avion", "bas");
			g.placerBateau(b);
		} catch (BateauException t) {
			System.out.println("Bateau mal placé");
		}
		try {
			g.afficher();
			g.tirer(0, 0);
			g.tirer(0, 1);
			g.tirer(3, 1);
			g.afficher();
		} catch (GrilleException e) {
		}
	}
}
