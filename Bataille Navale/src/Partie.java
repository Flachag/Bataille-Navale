
public abstract class Partie{
	protected Joueur j1;
	public Partie(Joueur j) {
		this.j1 = j;
	}
	
	public abstract void commencer();
	
	public abstract void jouer();
	
	public Joueur getJ1() {
		return this.j1;
	}
}
