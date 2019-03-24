import java.util.ArrayList;

public class Grille {
	private Cases[][] plateau;
	private ArrayList<Bateau> flote;

	public Grille(int x, int y) throws GrilleException {
		if (x >= 5 && y >= 5) {
			this.plateau = new Cases[x][y];
			this.flote = new ArrayList<Bateau>();

			for (int i = 0; i < this.plateau.length; i++) {
				for (int j = 0; j < this.plateau.length; j++) {
					this.plateau[i][j] = new Cases(i, j);
				}
			}
		} else {
			throw new GrilleException("taille grille incorrecte");
		}
	}

	public boolean isPlacable(Bateau b) throws BateauException {
		boolean res = true;
		for (int i = 0; i < b.getPos().size(); i++) {
			int x = b.getPos().get(i).getX();
			int y = b.getPos().get(i).getY();
			if (x >= this.plateau.length || y >= this.plateau[x].length)
				throw new BateauException("Bateau hors de la grille");
			if (!this.plateau[x][y].isLibre()) {
				throw new BateauException("Bateau mal placé");
			}
		}

		return res;
	}

	public void placerBateau(Bateau b) throws BateauException {
		if (this.isPlacable(b)) {
			for (int j = 0; j < b.getPos().size(); j++) {
				int x = b.getPos().get(j).getX();
				int y = b.getPos().get(j).getY();
				this.plateau[x][y] = b.getPos().get(j);
			}
			this.flote.add(b);
		}
	}

	public void placerTerre(int x, int y) {
		this.plateau[x][y].setLibre(false);
	}

	public boolean tirer(int x, int y) throws GrilleException {
		boolean touche = false;
		if (x < 0 || x >= this.plateau.length || y < 0 || y >= this.plateau[x].length)
			throw new GrilleException("Tir en dehors de la grille");

		if (x >= 0 && y >= 0 && this.plateau[x][y].getBateau() != null) {
			Bateau cible = this.plateau[x][y].getBateau();
			cible.subirTir(x, y);
			System.out.println("");
			if (cible.isDead()) {
				System.out.println(cible.getNom() + " coulé");
				for (int i = 0; i < this.flote.size(); i++) {
					if (flote.get(i).getNom() == cible.getNom()) {
						this.flote.remove(i);
					}
				}
			} else {
				System.out.println(cible.getNom() + " touché");
				System.out.println("Vie restante: " + cible.getVie() + "%");
			}
			this.plateau[x][y].setBateau(null);
			touche = true;
		} else if (x >= 0 && y >= 0 && this.plateau[x][y].isLibre()) {
			System.out.println("Plouf");
			this.plateau[x][y].setLibre(false);
		} else {
			throw new GrilleException("Case non libre");
		}
		return touche;
	}

	public void afficher() {
		System.out.print(" |");
		for (int i = 0; i < plateau.length; i++) {
			System.out.print(i+"|");
		}
		System.out.println("");
		for (int i = 0; i < this.plateau.length; i++) {
			System.out.print(i + "|");
			for (int j = 0; j < this.plateau[i].length; j++) {
				if (this.plateau[j][i].getBateau() != null)
					System.out.print("B");
				else if (!this.plateau[j][i].isLibre())
					System.out.print("X");
				else
					System.out.print("O");
				System.out.print("|");
			}
			System.out.println("");
		}
	}

	public boolean floteDetruite() {
		boolean res = false;
		if (this.flote.size() == 0)
			res = true;
		return res;
	}

	public Cases[][] getPlateau() {
		return plateau;
	}

	public void setPlateau(Cases[][] plateau) {
		this.plateau = plateau;
	}

	public ArrayList<Bateau> getFlote() {
		return flote;
	}
}
