import java.io.Serializable;
import java.util.Scanner;

public class Duo extends Partie implements Serializable {

	protected Joueur j2;

	/**
	 * Constructeur de duo (partie 2 joueurs)
	 * 
	 * @param j1 Joueur 1
	 * @param j2 Joueur 2
	 * @throws GrilleException Exceptions liees a la class Grille
	 */
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
		if (!this.j1.getGrilleBateau().floteDetruite() && !this.j2.getGrilleBateau().floteDetruite())
			tirer(this.j2, this.j1);
	}

	/*
	 * methode interne qui fait tirer un joueur sur un autre
	 * 
	 * @param tireur joueur qui tir
	 * 
	 * @param cible joueur cible
	 */
	private static void tirer(Joueur tireur, Joueur cible) {
		int x = 0, y = 0;
		boolean erreur = true;
		while (erreur) {
			try {
				System.out.println("");
				tireur.getGrilleTir().afficher();
				System.out.println(tireur.getNom() + " entrez la position x y du tir");
				System.out.println("");
				Scanner sc = new Scanner(System.in);
				x = Integer.parseInt(sc.next());
				y = Integer.parseInt(sc.next());
				erreur = false;
			} catch (Exception e) {
				System.out.println("Entrez un nombre !");
			}
		}
		boolean res = false;
		// permet de recommencer le tir tant que celui ci n'est pas valide (Hors de la
		// map ou tir sur case indisponible)
		try {
			while (!res) {
				boolean tir = cible.getGrilleBateau().tirer(x, y);
				tireur.getGrilleTir().getPlateau()[x][y].setLibre(false);
				res = true;
				if (tir && !cible.getGrilleBateau().floteDetruite() && !tireur.getGrilleBateau().floteDetruite())
					tirer(tireur, cible);
				break;
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
