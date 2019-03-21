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
		}else {
			throw new GrilleException("taille grille incorrecte");
		}
	}

	public void placerBateau(Bateau b) throws BateauException {
		for (Cases pos : b.getPos()) {
			int x = pos.getX();
			int y = pos.getY();
			if(x >= this.plateau.length || y >= this.plateau[x].length ) 
				throw new BateauException("Bateau hors de la grille");
			if (this.plateau[x][y].isLibre()) {
				this.plateau[x][y] = pos;
			} else {
				throw new BateauException("Bateau mal placé");
			}
		}
		this.flote.add(b);
	}

	public void placerTerre(int x, int y) {
		this.plateau[x][y].setLibre(false);
	}

	public void tirer(int x, int y) throws GrilleException {
		if (x >= 0 && y >= 0 && this.plateau[x][y].getBateau() != null) {
			Bateau cible = this.plateau[x][y].getBateau();
			cible.subirTir(x, y);

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
			
		} else if (x >= 0 && y >= 0) {
			System.out.println("Plouf");
			this.plateau[x][y].setLibre(false);
		} else {
			throw new GrilleException("Tir en dehors de la grille");
		}
		this.afficher();
	}

	public void afficher() {
		for (int i=0; i < this.plateau.length; i++) {
			System.out.print("|");
			for(int j=0; j<this.plateau[i].length; j++) {
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
