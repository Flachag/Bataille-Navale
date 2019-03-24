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
		int x, y;
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		System.out.println("Entrez la position x y du tir");
		x = sc.nextInt();
		y = sc.nextInt();
		boolean res = false;
		while (!res) {
			try {
				boolean tir = this.j1.getGrilleBateau().tirer(x, y);
				this.j1.getGrilleTir().getPlateau()[x][y].setLibre(false);
				this.j1.getGrilleTir().afficher();
				res = true;
				if (tir)
					this.jouer();

			} catch (GrilleException e) {
				System.out.println("Tir incorrect");
				this.jouer();
			}
		}
	}

}
