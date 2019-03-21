import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestBateau {
	Grille g;
	Bateau b;
	
	@Before
	public void avant() throws BateauException {
		g = new Grille(10,10);
		b = new Bateau(0,0,"porte avion", "bas");
	}
	
	@Test
	public void testPlacerBateau() {
		g.placerBateau(b);
		boolean res = g.getPlateau()[0][0].getBateau() == b;
		assertEquals("Bateau mal placé",true , res);
		res = g.getPlateau()[0][1].getBateau() == b;
		assertEquals("Bateau mal placé",true , res);
		res = g.getPlateau()[0][2].getBateau() == b;
		assertEquals("Bateau mal placé",true , res);
		res = g.getPlateau()[0][3].getBateau() == b;
		assertEquals("Bateau mal placé",true , res);
		res = g.getPlateau()[0][4].getBateau() == b;
		assertEquals("Bateau mal placé",true , res);
	}
	
	@Test
	public void testTirer() throws GrilleException {
		g.placerBateau(b);
		g.tirer(0,1);
		boolean res = g.getPlateau()[0][1].getBateau() == b;
		assertEquals("Echec du tir",false , res);
		assertEquals("La vie n'a pas baissé", 80, b.getVie());
	}
}
