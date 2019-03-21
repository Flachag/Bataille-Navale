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

	public void demanderBateau(ArrayList<Bateau> b, String nomBateau) throws BateauException {
		int x,y;
		String ori;
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrez la position x y et l'orientation (verticale ou horizontale) du "+nomBateau);
		x = sc.nextInt();
		y = sc.nextInt();
		ori = sc.next();
		b.add(new Bateau(x, y, nomBateau, ori));
	}

	public void commencer() throws BateauException {
		ArrayList <Bateau> bateaux = new ArrayList<Bateau>();
		
		this.demanderBateau(bateaux, "porte avion");
		this.demanderBateau(bateaux, "croiseur");
		this.demanderBateau(bateaux, "contre torpilleur");
		this.demanderBateau(bateaux, "sous marin");
		this.demanderBateau(bateaux, "torpilleur");
		
		for (int i = 0; i < bateaux.size(); i++) {
			this.grilleBateau.placerBateau(bateaux.get(i));
		}
		this.grilleBateau.afficher();
	}

	public void jouer() {
		
	}
}
