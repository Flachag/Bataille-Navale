import java.util.Scanner;

public class Duo extends Partie {

	protected Joueur j2;

	public Duo(Joueur j1, Joueur j2) throws GrilleException {
		super(j1);
		this.j2 = j2;
	}

	@Override
	public void commencer() {
		System.out.println(this.j1.getNom() + " placez vos bateaux:");
		this.j1.demanderBateau(this.j1.getGrilleBateau());
		System.out.println("");
		System.out.println(this.j2.getNom() + " placez vos bateaux:");
		this.j2.demanderBateau(this.j2.getGrilleBateau());
	}

	@Override
	public void jouer() {		
		tirer(this.j1, this.j2);
		tirer(this.j2, this.j1);
	}

	private static void tirer(Joueur tireur, Joueur cible) {
		System.out.println("");
		tireur.getGrilleTir().afficher();
		System.out.println(tireur.getNom() + " entrez la position x y du tir");
		System.out.println("");
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		boolean res = false;
		try {
			while (!res) {
				boolean tir = cible.getGrilleBateau().tirer(x, y);
				tireur.getGrilleTir().getPlateau()[x][y].setLibre(false);
				res = true;
				if (tir)
					tirer(tireur, cible);
			}
		} catch (GrilleException e) {
			System.out.println("Tir incorrect, recommencez");
			tirer(tireur, cible);
		}
	}

	public Joueur getJ2() {
		return this.j2;
	}
}
