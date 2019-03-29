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
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		this.j1.getGrilleTir().afficher();
		System.out.println("1-- Tirer \n2-- Trier\n3-- Sauvegarder \n4-- Quitter");
		int choix = sc.nextInt();
		switch (choix) {
		case 1:
			System.out.println("Entrez la position x y du tir");
			System.out.println("");
			int x = sc.nextInt();
			int y = sc.nextInt();
			try {
				this.j1.getGrilleBateau().tirer(x, y);
				this.j1.getGrilleTir().getPlateau()[x][y].setLibre(false);

			} catch (GrilleException e) {
				System.out.println("Tir incorrect, recommencez");
				this.jouer();
			}	
			break;
		case 3:
			this.sauve("batailleNavale.jeu");
			break;
		default:
			break;
		}

	}

}
