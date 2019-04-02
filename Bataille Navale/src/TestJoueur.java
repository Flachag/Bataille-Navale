
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestJoueur {

	Joueur j;
	Grille gBateau, gTir;

	@Before
	public void avant() throws BateauException, GrilleException {
		gBateau = new Grille(10, 10);
		gTir = new Grille(10, 10);
		j = new Joueur("Flavien", gBateau, gTir);
	}

	@Test
	public void testDemanderBateau() {
		j.demanderBateau(gBateau);
		assertEquals("Il devrait y avoir 5 bateaux", true, gBateau.getFlote().size() == 5);
		gBateau.getFlote().clear();
		assertEquals("", true, gBateau.floteDetruite());
	}
}
