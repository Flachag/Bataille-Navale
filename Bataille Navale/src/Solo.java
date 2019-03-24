import java.util.Scanner;

public class Solo extends Partie {

	public Solo(Joueur j1) throws GrilleException {
		super(j1);
	}
	
	@Override
	public void commencer() {
		this.j1.demanderBateau(this.j1.getGrilleBateau());
	}

	@Override
	public void jouer() {
		System.out.println("");
		this.j1.getGrilleTir().afficher();
		System.out.println("Entrez la position x y du tir");
		System.out.println("");
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		boolean res = false;
		while (true) {
			try {
				boolean tir = this.j1.getGrilleBateau().tirer(x, y);
				this.j1.getGrilleTir().getPlateau()[x][y].setLibre(false);
				if (tir) {
					break;
				}

			} catch (GrilleException e) {
				System.out.println("Tir incorrect, recommencez");
				this.jouer();
			}
		}
	}

}
