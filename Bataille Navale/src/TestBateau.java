import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestBateau {
	Grille g;
	
	@Before
	public void avant() {
		g = new Grille(10,10);
	}
	
	@Test
	public void testConstructeur() {
		boolean res = g.getPlateau()[0][0].isLibre();
		g.placerTerre(0,0);
		assertEquals("Bateau mal placé",false, res);
	}
	
}
