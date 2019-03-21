import java.util.ArrayList;

public class Grille {
	private Cases[][] plateau;
	private ArrayList<Bateau> flote;

	public Grille(int x, int y) {
		if (x >= 5 && y >= 5) {
			this.plateau = new Cases[x][y];

			for (int i = 0; i < this.plateau.length; i++) {
				for (int j = 0; j < this.plateau.length; j++) {
					this.plateau[x][y] = new Cases(x, y);
				}
			}
		}
	}

	public void placerBateau(Bateau b) {
		for (Cases pos : b.getPos()) {
			int x = pos.getX();
			int y = pos.getY();

			this.plateau[x][y] = pos;
		}
	}

	public void placerTerre(int x, int y) {
		this.plateau[x][y].setLibre(false);
		;
	}

	public void tirer(int x, int y){
		if(x >= 0 && y >= 0 && this.plateau[x][y].getBateau() != null) {
			Bateau cible = this.plateau[x][y].getBateau();
			System.out.println(cible.getNom()+" Touché");
			
			
			if (cible.getPos().size()==1) {
				System.out.println(cible.getNom()+" Coulé");
			}
			
			for (Bateau bateau : flote) {
				if (bateau.getNom()==cible.getNom()) {
					this.flote.remove(bateau);
				}
			}
			this.plateau[x][y].setBateau(null);
		}
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

	public void setFlote(ArrayList<Bateau> flote) {
		this.flote = flote;
	}



}
