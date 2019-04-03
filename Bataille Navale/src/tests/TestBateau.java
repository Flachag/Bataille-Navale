package tests;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import Jeu.Bateau;
import Jeu.BateauException;
import Jeu.Grille;
import Jeu.GrilleException;

public class TestBateau {
	Grille g;
	Bateau b, b2;

	@Before
	public void avant() throws BateauException, GrilleException {
		g = new Grille(10, 10);
		b = new Bateau(0, 0, "porte avion", "verticale");
		b2 = new Bateau(1, 0, "torpilleur", "verticale");
	}

	@Test
	public void testTirer() throws GrilleException, BateauException {
		g.placerBateau(b);
		g.tirer(0, 1);
		boolean res = g.getPlateau()[0][1].getBateau() == b;
		assertEquals("Echec du tir", false, res);
		assertEquals("La vie n'a pas baissé", 80, b.getVie());
	}

	@Test
	public void testIsDead() throws BateauException, GrilleException {
		g.placerBateau(b);
		g.tirer(0, 0);
		g.tirer(0, 1);
		g.tirer(0, 2);
		g.tirer(0, 3);
		g.tirer(0, 4);

		assertEquals("Le bateau est censé être détruit", true, b.isDead());
	}
}
