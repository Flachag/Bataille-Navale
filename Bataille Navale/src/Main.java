
public class Main {

	public static void main(String[] args) throws GrilleException, BateauException {
		Grille g;
		Grille j2;

		g = new Grille(10, 10);
		j2 = new Grille(10, 10);
		Joueur j1 = new Joueur("J1", g ,j2);
		
		j1.commencer();
		System.out.println("");
		j1.getGrilleTir().afficher();
		while(!j1.getGrilleBateau().floteDetruite()) {
			j1.jouer();
		}
		
	}
}