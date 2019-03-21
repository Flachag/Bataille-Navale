
public class Cases {
	private int x, y;
	private Bateau b;
	private boolean libre = true;
	
	public Cases(int x, int y) {
		if (x >= 0 && y >= 0) {
			this.x = x;
			this.y = y;
		}
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public Bateau getBateau() {
		return b;
	}
	
	public void setBateau(Bateau b) {
		this.b = b;
	}
	
	public boolean isLibre() {
		return libre;
	}
	
	public void setLibre(boolean libre) {
		this.libre = libre;
	}	
}
